package ru.guwfa.auction.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.guwfa.auction.domain.LotInAu;
import ru.guwfa.auction.domain.Price;

import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class TimeService {
    @Autowired
    private PriceService PriceService;

    public String getTimeText(int xHoursBefore, LotInAu LotInAu) {

        if (isMoreThanXHoursBefore(xHoursBefore, LotInAu)) { // если до начала торгов более xHours часов
            return "Дата начала аукциона: ";

        } else if (isLessThanXHoursBefore(xHoursBefore, LotInAu)) { // если до начала торгов менее xHours часов
            return "До начала аукциона: ";

        } else {                            // если торги уже идут
            return "До конца аукциона: ";
        }
    }

    public String getTimeValue(int xHoursBefore, LotInAu LotInAu) {

        if (isMoreThanXHoursBefore(xHoursBefore, LotInAu)) { // если до начала торгов более xHours часов
            return new SimpleDateFormat("yyyy.MM.dd HH:mm:ss").format(LotInAu.gettimeStart()); // то выводим дату начала аукциона

        } else if (isLessThanXHoursBefore(xHoursBefore, LotInAu)) { // если до начала торгов менее xHours часов

            // время до начала торгов в секундах
            long timeLeft = (LotInAu.gettimeStart().getTime() - new Date().getTime()) / 1000;

            long secondsLeft = timeLeft % 60; // 1..59
            long minutesLeft = (timeLeft - secondsLeft) / 60 % 60; // 1..59
            long hoursLeft = (timeLeft - minutesLeft * 60 - secondsLeft) / 3600 % 60; // 1..23

            return hoursLeft + ":" + minutesLeft + ":" + secondsLeft; // то выводим время до старта аукциона

        } else {              //если торги уже идут
            Price Price = PriceService.getFirst(LotInAu.getId());
            long intervalMillis = LotInAu.getTimeStep() * 60000; // 1 мин = 60 000 мс
            long timeLeft = (Price.getDate().getTime() + intervalMillis - new Date().getTime()) / 1000;

            return String.valueOf(timeLeft);
        }
    }

    private boolean isMoreThanXHoursBefore(int hoursBefore, LotInAu LotInAu) {
        long hoursInMillis = hoursBefore * 3600000;  // 1 час = 3 600 000 мс
        long timeLeft = LotInAu.gettimeStart().getTime() - new Date().getTime(); // время начала торгов - время сейчас

        return timeLeft > hoursInMillis; //до начала торгов более xHours часов
    }

    private boolean isLessThanXHoursBefore(int hoursBefore, LotInAu LotInAu) {
        long hoursInMillis = hoursBefore * 3600000;  // 1 час = 3 600 000 мс
        long timeLeft = LotInAu.gettimeStart().getTime() - new Date().getTime(); // время начала торгов - время сейчас

        return timeLeft < hoursInMillis && timeLeft > 0; // торги еще не начались && до начала менее xHours часов
    }
}
