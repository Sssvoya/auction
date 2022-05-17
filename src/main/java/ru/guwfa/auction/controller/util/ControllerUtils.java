package ru.guwfa.auction.controller.util;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.*;

public class ControllerUtils {
    public static Map<String, List<String>> getErrors(BindingResult bindingResult) {

        //хранит по каждому ключу(полю) несколько сообщений об ошибке
        Map<String, List<String>> allErrors = new HashMap<>();

        for (FieldError error : bindingResult.getFieldErrors()) {
            //ключ для Map
            String key = error.getField() + "Error";

            //если в Map уже содержится такой ключ (т.е. это поле содержит несколько ошибок)
            if (allErrors.containsKey(key)) {
                //добавляем по этому ключу новое сообщение с ошибкой
                allErrors.get(key).add(error.getDefaultMessage());

            } else {
                //если такого ключа в Map нет, то просто помещаем сообщение с ошибкой по данному ключу
                allErrors.put(key, new ArrayList<>(Arrays.asList(error.getDefaultMessage())));
            }
        }

        return allErrors;
    }
}
