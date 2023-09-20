package com.vtortsev.quizapp.service;

public class Valid {
    public static boolean isValidText(String answerText) {
        String regex = "[a-zA-Zа-яА-Я0-9]+(( )?(-)?[a-zA-Zа-яА-Я0-9]([.,!?])?)*";
        return answerText != null && answerText.matches(regex);
    }
    public static boolean isValidCategoryName(String name) {
        return name.length() > 1 && isValidText(name);
    }
}

