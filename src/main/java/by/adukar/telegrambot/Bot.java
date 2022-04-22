package by.adukar.telegrambot;

import by.adukar.telegrambot.buttons.inline.InlineButtons;
import by.adukar.telegrambot.buttons.reply.ReplyButtons;
import by.adukar.telegrambot.service.FileService;
import by.adukar.telegrambot.service.TextService;
import by.adukar.telegrambot.service.UserService;
import lombok.SneakyThrows;
import org.checkerframework.checker.units.qual.A;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.polls.SendPoll;
import org.telegram.telegrambots.meta.api.methods.send.SendContact;
import org.telegram.telegrambots.meta.api.methods.send.SendLocation;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.List;

@Component
public class Bot extends TelegramLongPollingBot {

    ReplyButtons replyButtons = new ReplyButtons();

    UserService userService = new UserService();
    TextService textService = new TextService();
    FileService fileService = new FileService();
    InlineButtons inlineButtons = new InlineButtons();


    @Override
    @SneakyThrows
    public void onUpdateReceived(Update update) {
        sendAnswerFromBot(update);
    }

    @SneakyThrows
    public void sendAnswerFromBot(Update update) {
        String chatId = update.getMessage().getChatId().toString();
        if (update.hasCallbackQuery()) {
            if (update.getCallbackQuery().getData().equals("Интересные места")) {
                String chatIdFromCallBack = update.getCallbackQuery().getFrom().getId().toString();
                sendMsg("https://vandruy.by/40-samyh-krasivyh-mest-na-zemle/", chatIdFromCallBack);
            }
            if (update.getCallbackQuery().getData().equals("Другое")) {
                String chatIdFromCallBack = update.getCallbackQuery().getFrom().getId().toString();
                sendMsg("https://sergeydolya.livejournal.com/1304062.html", chatIdFromCallBack);
            }
            if (update.getCallbackQuery().getData().equals("trc2342n")) {
                String chatIdFromCallBack = update.getCallbackQuery().getFrom().getId().toString();
                sendMsg("chatIdFr23423omCallBack", chatIdFromCallBack);
            }
            if (update.getCallbackQuery().getData().equals("Для туризма")) {
                String chatIdFromCallBack = update.getCallbackQuery().getFrom().getId().toString();
                sendMsg("https://34travel.me/post/best-in-travel-2020", chatIdFromCallBack);
            }
            if (update.getCallbackQuery().getData().equals("Для туризма, но подешевле")) {
                String chatIdFromCallBack = update.getCallbackQuery().getFrom().getId().toString();
                sendMsg("https://www.skyscanner.ru/news/deshevye-strany-dlia-puteshestvii", chatIdFromCallBack);
            }
        }
        else {

            if ((update.getMessage().getText().equals("Куда поехать дёшево")))
                sendPoll(chatId);
        }

        if (update.getMessage().getText().equals("/start")) {
            sendMsgWithButtons("Вводи эти команды, чтобы бот работал:Интересные места, Туризм, Другое, Дешевле", replyButtons.keyboardMarkup(), chatId);
        }
        if (update.getMessage().getText().equals("Дополнительная информация")) {
            sendMsg("Если хочешь узнать что-то про интересующие тебя города-просто напиши их название в чат", chatId);
        }
        if (update.getMessage().getText().equals("Факультеты")) {
            sendMsgWithButtons("Список факультетов", inlineButtons.keyboardMarkup("Факультеты", "https://www.bsuir.by/ru/fakultety"), chatId);
            ArrayList<InlineKeyboardMarkup> faculties = new ArrayList<>();
            faculties.add(inlineButtons.keyboardMarkup("Факультет компьютерного проектирования (ФКП)", "https://www.bsuir.by/ru/fkp"));
            faculties.add(inlineButtons.keyboardMarkup("Факультет информационных технологий и управления (ФИТУ)", "https://www.bsuir.by/ru/fitu"));
            faculties.add(inlineButtons.keyboardMarkup("Факультет радиотехники и электроники (ФРЭ)", "https://www.bsuir.by/ru/fre"));
            faculties.add(inlineButtons.keyboardMarkup("Факультет компьютерных систем и сетей (ФКСиС)", "https://www.bsuir.by/ru/fksis"));
            faculties.add(inlineButtons.keyboardMarkup("Факультет инфокоммуникаций (ФИК)", "https://www.bsuir.by/ru/fik"));
            faculties.add(inlineButtons.keyboardMarkup("Инженерно-экономический факультет (ИЭФ)", "https://www.bsuir.by/ru/ief"));
            faculties.add(inlineButtons.keyboardMarkup("Военный факультет (ВФ)", "https://www.bsuir.by/ru/vf"));
            sendButtons(faculties, chatId);
        }
    }


    public synchronized void sendMsg(String message, String chatId) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        sendMessage.setText(message);
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }


    public synchronized void sendContact(String chatId) {
        SendContact sendContact = new SendContact();
        sendContact.setPhoneNumber("щ");
        sendContact.setFirstName("з");
        sendContact.setLastName("с");
        sendContact.setChatId(chatId);
        try {
            execute(sendContact);
        } catch (TelegramApiException e) {
            System.out.println( "Exception: " + e.toString());
        }
    }

    public synchronized void sendMsgWithButtons(String message, ReplyKeyboardMarkup replyKeyboardMarkup, String chatId) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        sendMessage.setText(message);
        sendMessage.setReplyMarkup(replyKeyboardMarkup);
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            System.out.println( "Exception: " + e.toString());
        }
    }

    public synchronized void sendPoll(String chatId){
        SendPoll sendPoll = new SendPoll();
        sendPoll.enableNotification();
        sendPoll.setQuestion("Куда поехать");
        sendPoll.setIsAnonymous(true);
        sendPoll.setOptions(List.of("Беларусь", "Абхазия", "Турция"));
        sendPoll.setChatId(chatId);
        sendPoll.setType("quiz");
        sendPoll.setCorrectOptionId(2);
        try {
            execute(sendPoll);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
    public synchronized void sendMsgWithButtons(String message, InlineKeyboardMarkup replyKeyboardMarkup, String chatId) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        sendMessage.setText(message);
        sendMessage.setReplyMarkup(replyKeyboardMarkup);
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            System.out.println( "Exception: " + e.toString());
        }
    }

    public synchronized void sendButtons(ArrayList<InlineKeyboardMarkup> replyKeyboardMarkups, String chatId){
        for (InlineKeyboardMarkup replyKeyboardMarkup:
             replyKeyboardMarkups) {
            SendMessage sendMessage = new SendMessage();
            sendMessage.setChatId(chatId);
            sendMessage.setText("Test");
            sendMessage.setReplyMarkup(replyKeyboardMarkup);
            try {
                execute(sendMessage);
            } catch (TelegramApiException e) {
                System.out.println( "Exception: " + e.toString());
            }
        }
    }


    @Override
    public String getBotUsername() {
        return "YuraskasKPBot";
    }

    @Override
    public String getBotToken() {
        return "5302266375:AAHYpDec2GdDyZ_XRMkTcKrgzdtMKZvUvI8";
    }
}


