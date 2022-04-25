package by.adukar.telegrambot.buttons.reply;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.ArrayList;

public class ReplyButtons {

    public ReplyKeyboardMarkup keyboardMarkup() {

        ArrayList<KeyboardRow> keyboard = new ArrayList<>();
        KeyboardRow keyboardFirstRow = new KeyboardRow();
        KeyboardRow keyboardSecondRow = new KeyboardRow();
        ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();
        keyboardMarkup.setSelective(true);
        keyboardMarkup.setResizeKeyboard(true);
        keyboardMarkup.setOneTimeKeyboard(false);

            keyboardFirstRow.add("/start");
            keyboardFirstRow.add("Пройти тест");
            keyboardFirstRow.add("Факультеты");
            keyboardSecondRow.add("Моя информация");

        keyboard.add(keyboardFirstRow);
        keyboard.add(keyboardSecondRow);

        keyboardMarkup.setKeyboard(keyboard);
        return keyboardMarkup;

    }

    public ReplyKeyboardMarkup keyboardMarkupFaculties() {

        ArrayList<KeyboardRow> keyboard = new ArrayList<>();
        KeyboardRow keyboardFirstRow = new KeyboardRow();
        KeyboardRow keyboardSecondRow = new KeyboardRow();
        KeyboardRow keyboardThridRow = new KeyboardRow();
        ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();
        keyboardMarkup.setSelective(true);
        keyboardMarkup.setResizeKeyboard(true);
        keyboardMarkup.setOneTimeKeyboard(false);

        keyboardFirstRow.add("ФКП");
        keyboardFirstRow.add("ФИТУ");
        keyboardFirstRow.add("ФРЭ");
        keyboardFirstRow.add("ФИК");
        keyboardSecondRow.add("ФКСИС");
        keyboardSecondRow.add("ИЭФ");
        keyboardSecondRow.add("ВФ");
        keyboardThridRow.add("Назад");

        keyboard.add(keyboardFirstRow);
        keyboard.add(keyboardSecondRow);
        keyboard.add(keyboardThridRow);

        keyboardMarkup.setKeyboard(keyboard);
        return keyboardMarkup;

    }

    public ReplyKeyboardMarkup keyboardMarkupFirstQuestion() {

        ArrayList<KeyboardRow> keyboard = new ArrayList<>();
        KeyboardRow keyboardFirstRow = new KeyboardRow();
        KeyboardRow keyboardSecondRow = new KeyboardRow();
        KeyboardRow keyboardThridRow = new KeyboardRow();
        KeyboardRow keyboardFouthRow = new KeyboardRow();
        KeyboardRow keyboardFifthRow = new KeyboardRow();
        ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();
        keyboardMarkup.setSelective(true);
        keyboardMarkup.setResizeKeyboard(true);
        keyboardMarkup.setOneTimeKeyboard(false);

        keyboardFirstRow.add("Инженерия");
        keyboardSecondRow.add("Программирование");
        keyboardThridRow.add("Логистика/Экономика");
        keyboardFouthRow.add("Инфокомуникации");
        keyboardFifthRow.add("Другая сфера");

        keyboard.add(keyboardFirstRow);
        keyboard.add(keyboardSecondRow);
        keyboard.add(keyboardThridRow);
        keyboard.add(keyboardFouthRow);
        keyboard.add(keyboardFifthRow);

        keyboardMarkup.setKeyboard(keyboard);
        return keyboardMarkup;

    }

    public ReplyKeyboardMarkup keyboardMarkupSecondQuestion() {

        ArrayList<KeyboardRow> keyboard = new ArrayList<>();
        KeyboardRow keyboardFirstRow = new KeyboardRow();
        KeyboardRow keyboardSecondRow = new KeyboardRow();
        KeyboardRow keyboardThridRow = new KeyboardRow();
        KeyboardRow keyboardFouthRow = new KeyboardRow();
        KeyboardRow keyboardFifthRow = new KeyboardRow();
        ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();
        keyboardMarkup.setSelective(true);
        keyboardMarkup.setResizeKeyboard(true);
        keyboardMarkup.setOneTimeKeyboard(false);

        keyboardFirstRow.add("20%");
        keyboardSecondRow.add("40%");
        keyboardThridRow.add("60%");
        keyboardFouthRow.add("80%");
        keyboardFifthRow.add("100%");

        keyboard.add(keyboardFirstRow);
        keyboard.add(keyboardSecondRow);
        keyboard.add(keyboardThridRow);
        keyboard.add(keyboardFouthRow);
        keyboard.add(keyboardFifthRow);

        keyboardMarkup.setKeyboard(keyboard);
        return keyboardMarkup;

    }

    public ReplyKeyboardMarkup keyboardMarkupThirdQuestion() {

        ArrayList<KeyboardRow> keyboard = new ArrayList<>();
        KeyboardRow keyboardFirstRow = new KeyboardRow();
        KeyboardRow keyboardSecondRow = new KeyboardRow();
        KeyboardRow keyboardThridRow = new KeyboardRow();
        ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();
        keyboardMarkup.setSelective(true);
        keyboardMarkup.setResizeKeyboard(true);
        keyboardMarkup.setOneTimeKeyboard(false);

        keyboardFirstRow.add("Математика, физика");
        keyboardSecondRow.add("Физика, информатика");
        keyboardThridRow.add("Математика, информатика");

        keyboard.add(keyboardFirstRow);
        keyboard.add(keyboardSecondRow);
        keyboard.add(keyboardThridRow);

        keyboardMarkup.setKeyboard(keyboard);
        return keyboardMarkup;

    }
}
