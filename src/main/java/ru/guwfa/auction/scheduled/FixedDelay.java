package ru.guwfa.auction.scheduled;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import ru.guwfa.auction.domain.dataTransferObject.response.TimerResponseDataTransferObject;
import ru.guwfa.auction.service.LotInAUservice;

import java.util.Date;

public class FixedDelay {
    private final static int delayInUpdating = 5000;

    @Autowired
    private SimpMessagingTemplate template;

    @Autowired
    private LotInAUservice LotInAUservice;


    @Scheduled(fixedDelay = 1000)
    public void sendServerTime() {
        template.convertAndSend("/topic/timer", new TimerResponseDataTransferObject(String.valueOf(new Date().getTime())));
    }

    // обновление статуса лотов и удаление пользователей из подписок, если статус лота не "активен"
    @Scheduled(fixedDelay = delayInUpdating)
    public void updateLotInAu() {
        LotInAUservice.finishIfTimeOver(); //автоматическое завершение лотов
    }

}
