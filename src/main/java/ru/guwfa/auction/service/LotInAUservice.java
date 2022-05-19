package ru.guwfa.auction.service;

import net.coobird.thumbnailator.Thumbnails;
import org.assertj.core.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.guwfa.auction.Repository.LotInAuRepository;
import ru.guwfa.auction.domain.LotInAu;
import ru.guwfa.auction.domain.StatusLot;
import ru.guwfa.auction.domain.User;
import ru.guwfa.auction.domain.dataTransferObject.LotInAuDataTransferObject;
import ru.guwfa.auction.dataAccessObject.LotInAuDataAccessObject;

import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.util.*;

@Service
public class LotInAUservice {
    @Value("${upload.path}")
    private String uploadPath;

    @Autowired
    private LotInAuRepository LotInAuRepository;

    @Autowired
    private PriceService PriceService;

    @Autowired
    private LotInAuDataAccessObject LotInAuDataAccessObject;

    @Autowired
    private SubscriptionService subscriptionService;

    @Autowired
    private MailSender mailSender;

    @Autowired
    private ExchangeService exchangeService;

    @Autowired
    private ResourceBundle resourceBundle;


    public Page<LotInAu> getAllActiveByFilter(String filterName, String filterDescription, Pageable pageable) {
        //если оба фильтра не заданы
        if (Strings.isNullOrEmpty(filterName) && Strings.isNullOrEmpty(filterDescription))
            return LotInAuRepository.findAllByStatusOrderBytimeStartAsc(StatusLot.ACTIVE.name(), pageable);

        return LotInAuDataAccessObject.findByFilter(filterName, filterDescription, pageable);
    }

    public void addLotInAu(User User, LotInAuDataTransferObject LotInAuInAuDataTransferObject, MultipartFile file) {

        LotInAu LotInAu = new LotInAu();

        // перепишем валидируемые поля из LotInAuInAuDataTransferObject
        LotInAu.setName(LotInAuInAuDataTransferObject.getName());
        LotInAu.setDescription(LotInAuInAuDataTransferObject.getDescription());
        LotInAu.setTimeStep(LotInAuInAuDataTransferObject.getTimeStep());
        LotInAu.setinitialRate(LotInAuInAuDataTransferObject.getinitialRate());
        LotInAu.settimeStart(new Date(LotInAuInAuDataTransferObject.gettimeStart()));
        LotInAu.setCreator(User);
        LotInAu.setStatus(StatusLot.ACTIVE.name());

        LotInAu.setFinalrate(LotInAu.getinitialRate());

        // время окончания = время начала + интервал
        LotInAu.setEndTime(new Date(LotInAu.gettimeStart().getTime() + LotInAu.getTimeStep() * 60000));

        //если из формы был получен файл
        if (file != null && !file.getOriginalFilename().isBlank()) {
            File uploadDir = new File(uploadPath);

            //если директория отсутствует
            if (uploadDir.exists() && uploadDir.isFile()) {
                uploadDir.mkdir();
            }

            //Чтобы избежать коллизии имен файлов, сгенерируем рандомный UUID
            String uUIdFile = UUID.randomUUID().toString();

            //объединим uuid и название файла
            String resultFilename = uUIdFile + "." + file.getOriginalFilename();

            try {
                //сохраним файл по указанному пути
                file.transferTo(new File(uploadPath + File.separator + resultFilename));

                //обновим значение поля filename на новое уникальное имя
                LotInAu.setFilename(resultFilename);

                //сохраним уменьшенную до 300px копию исходного изображения
                Thumbnails.of(uploadPath + File.separator + resultFilename)
                        .size(300, 300)
                        .outputQuality(0.85)
                        .toFile(uploadPath + File.separator + "300PX_" + resultFilename);

            } catch (IOException e) {
                e.printStackTrace();
            }

        } else {
            LotInAu.setFilename("DEFAULT.png");
        }

        LotInAuRepository.save(LotInAu);

        //зададим ставку создателя как стартовую
        PriceService.addPrice(User, LotInAu);
    }

    public void updateLastrate(LotInAu LotInAu, Long rate, Date rateDate) {
        LotInAu.setFinalrate(rate);

        Date newEndTime = new Date(
                rateDate.getTime() + LotInAu.getTimeStep() * 60000 // время ставки + интервал между ставками в мс
        );

        //обновляем предполагаемое время окончания торгов (если новых ставок не будет)
        LotInAu.setEndTime(newEndTime);
    }

    public Optional<LotInAu> getById(Long id) {
        return LotInAuRepository.findById(id);
    }



}
