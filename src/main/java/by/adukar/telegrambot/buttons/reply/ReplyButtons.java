package by.adukar.telegrambot.buttons.reply;

import by.adukar.telegrambot.consts.Paths;
import by.adukar.telegrambot.service.TextService;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.io.IOException;
import java.util.ArrayList;

public class ReplyButtons {

    TextService textService = new TextService();

    public ReplyKeyboardMarkup keyboardMarkup() {

        ArrayList<KeyboardRow> keyboard = new ArrayList<>();
        KeyboardRow keyboardFirstRow = new KeyboardRow();
        ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();
        keyboardMarkup.setSelective(true);
        keyboardMarkup.setResizeKeyboard(true);
        keyboardMarkup.setOneTimeKeyboard(false);

            keyboardFirstRow.add("/start");
            keyboardFirstRow.add("Дополнительная информация");
            keyboardFirstRow.add("Факультеты");

        keyboard.add(keyboardFirstRow);


        keyboardMarkup.setKeyboard(keyboard);
        return keyboardMarkup;

    }
}
