package by.adukar.telegrambot;

import by.adukar.telegrambot.buttons.inline.InlineButtons;
import by.adukar.telegrambot.buttons.reply.ReplyButtons;
import by.adukar.telegrambot.consts.Faculties;
import by.adukar.telegrambot.model.User;
import by.adukar.telegrambot.service.UserService;
import by.adukar.telegrambot.service.UserServiceImpl;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Component
public class Bot extends TelegramLongPollingBot {

    ReplyButtons replyButtons = new ReplyButtons();

    InlineButtons inlineButtons = new InlineButtons();

    private static Boolean isFio = false;
    private static Boolean isPointNum = false;
    private static int questionOne = 5;
    private static int questionTwo = 5;

    private final UserService userService;

    @Autowired
    public Bot(UserService userService) {
        this.userService = userService;
    }


    @Override
    @SneakyThrows
    public void onUpdateReceived(Update update) {
        sendAnswerFromBot(update);
    }

    @SneakyThrows
    public void sendAnswerFromBot(Update update) {
        String chatId = update.getMessage().getChatId().toString();
        String message = update.getMessage().getText();

        if (update.getMessage().getText().equals("/start")) {
            sendMsgWithButtons("Вводи эти команды, чтобы бот работал:Пройти тест и Факультеты", replyButtons.keyboardMarkup(), chatId);
        }
        if (update.getMessage().getText().equals("Назад")) {
            sendMsgWithButtons("Назад", replyButtons.keyboardMarkup(), chatId);
        }
        if (update.getMessage().getText().equals("Факультеты")) {
            sendMsgWithButtons("Список факультетов",
                    inlineButtons.keyboardMarkup("Факультеты",
                            "https://www.bsuir.by/ru/fakultety"), chatId);
            sendMsg("ФКП", chatId);
            sendMsg("ФИТУ", chatId);
            sendMsg("ФРЭ", chatId);
            sendMsg("ФКСИС", chatId);
            sendMsg("ФИК", chatId);
            sendMsg("ИЭФ", chatId);
            sendMsg("ВФ", chatId);
            sendMsgWithButtons("Чтобы получить подробную информацию о специальностьях и " +
                    "баллах введите название факультета", replyButtons.keyboardMarkupFaculties(), chatId);

        }
        if (update.getMessage().getText().equals("ФКП")) {
            sendMsgWithButtons(Faculties.FKP,
                    inlineButtons.keyboardMarkup("Факультет компьютерного проектирования (ФКП)",
                            "https://www.bsuir.by/ru/fkp"), chatId);
        }
        if (update.getMessage().getText().equals("ФИТУ")) {
            sendMsgWithButtons(Faculties.FITY,
                    inlineButtons.keyboardMarkup("Факультет информационных технологий и управления (ФИТУ)",
                            "https://www.bsuir.by/ru/fitu"), chatId);
        }
        if (update.getMessage().getText().equals("ФРЭ")) {
            sendMsgWithButtons(Faculties.FRE,
                    inlineButtons.keyboardMarkup("Факультет радиотехники и электроники (ФРЭ)",
                            "https://www.bsuir.by/ru/fre"), chatId);
        }
        if (update.getMessage().getText().equals("ФКСИС")) {
            sendMsgWithButtons(Faculties.FKSIS,
                    inlineButtons.keyboardMarkup("Факультет компьютерных систем и сетей (ФКСиС)",
                            "https://www.bsuir.by/ru/fksis"), chatId);
        }
        if (update.getMessage().getText().equals("ФИК")) {
            sendMsgWithButtons(Faculties.FIK,
                    inlineButtons.keyboardMarkup("Факультет инфокоммуникаций (ФИК)",
                            "https://www.bsuir.by/ru/fik"), chatId);
        }
        if (update.getMessage().getText().equals("ИЭФ")) {
            sendMsgWithButtons(Faculties.IEF,
                    inlineButtons.keyboardMarkup("Инженерно-экономический факультет (ИЭФ)",
                            "https://www.bsuir.by/ru/ief"), chatId);
        }
        if (update.getMessage().getText().equals("ВФ")) {
            sendMsgWithButtons(Faculties.VF,
                    inlineButtons.keyboardMarkup("Военный факультет (ВФ)",
                            "https://www.bsuir.by/ru/vf"), chatId);
        }
        if (update.getMessage().getText().equals("Пройти тест")) {
            sendMsg("Введите ваше имя фамилию", chatId);
            isFio = true;
        }
        if (isFio && !Objects.equals(message, "Пройти тест")){
            if(isAlpha(message)){
                User user = new User();
                user.setChatId(chatId);
                user.setFio(message);
                userService.save(user);
                sendMsg("Введите ваше колличество баллов", chatId);
                isFio = false;
                isPointNum = true;
            }
            else {
                isFio = true;
                message = "Пройти тест";
                sendMsg("Введите ваше имя фамилию, они должны состоять только из символов!", chatId);
            }
        }
        if (isPointNum){
            int pointNum = Integer.parseInt(message);
            if(pointNum>=0 && pointNum<=400) {
                User user = userService.getById(chatId);
                user.setPointNum(pointNum);
                userService.save(user);
                sendMsgWithButtons("В какой предпочтительно сфере вы хотите работать в будущем?",
                        replyButtons.keyboardMarkupFirstQuestion(), chatId);
                isPointNum = false;
            }
            else {
                isPointNum = true;
                sendMsg("Введите ваше колличество баллов. Ваше колличество " +
                        "баллов должно быть не менее 0 и неболее 400", chatId);
            }
        }

        if (update.getMessage().getText().equals("Инженерия")) {
            sendMsgWithButtons("Сколько свободного времени в день вы готовы уделять учебе?",
                    replyButtons.keyboardMarkupSecondQuestion(), chatId);
            questionOne = 1;
        }
        if (update.getMessage().getText().equals("Программирование")) {
            sendMsgWithButtons("Сколько свободного времени в день вы готовы уделять учебе?",
                    replyButtons.keyboardMarkupSecondQuestion(), chatId);
            questionOne = 2;
        }
        if (update.getMessage().getText().equals("Логистика/Экономика")) {
            sendMsgWithButtons("Сколько свободного времени в день вы готовы уделять учебе?",
                    replyButtons.keyboardMarkupSecondQuestion(), chatId);
            questionOne = 3;
        }
        if (update.getMessage().getText().equals("Инфокомуникации")) {
            sendMsgWithButtons("Сколько свободного времени в день вы готовы уделять учебе?",
                    replyButtons.keyboardMarkupSecondQuestion(), chatId);
            questionOne = 4;
        }
        if (update.getMessage().getText().equals("Другая сфера")) {
            sendMsgWithButtons("Сколько свободного времени в день вы готовы уделять учебе?",
                    replyButtons.keyboardMarkupSecondQuestion(), chatId);
            questionOne = 5;
        }
        if (update.getMessage().getText().equals("20%")) {
            sendMsgWithButtons("Если бы вам разрешили исключить из школьной программы все предметы, кроме одного. Что бы оставили вы?",
                    replyButtons.keyboardMarkupThirdQuestion(), chatId);
            questionTwo = 1;
        }
        if (update.getMessage().getText().equals("40%")) {
            sendMsgWithButtons("Если бы вам разрешили исключить из школьной программы все предметы, кроме одного. Что бы оставили вы?",
                    replyButtons.keyboardMarkupThirdQuestion(), chatId);
            questionTwo = 2;
        }
        if (update.getMessage().getText().equals("60%")) {
            sendMsgWithButtons("Если бы вам разрешили исключить из школьной программы все предметы, кроме одного. Что бы оставили вы?",
                    replyButtons.keyboardMarkupThirdQuestion(), chatId);
            questionTwo = 3;
        }
        if (update.getMessage().getText().equals("80%")) {
            sendMsgWithButtons("Если бы вам разрешили исключить из школьной программы все предметы, кроме одного. Что бы оставили вы?",
                    replyButtons.keyboardMarkupThirdQuestion(), chatId);
            questionTwo = 4;
        }
        if (update.getMessage().getText().equals("100%")) {
            sendMsgWithButtons("Если бы вам разрешили исключить из школьной программы все предметы, кроме одного. Что бы оставили вы?",
                    replyButtons.keyboardMarkupThirdQuestion(), chatId);
            questionTwo = 5;
        }
        if (update.getMessage().getText().equals("Математика, физика")) {
            sendMsgWithButtons("Вам подходит:\n" + getAnswer(chatId), replyButtons.keyboardMarkup(), chatId);
        }
        if (update.getMessage().getText().equals("Физика, информатика")) {
            sendMsgWithButtons("Вам подходит:\n" + getAnswer(chatId), replyButtons.keyboardMarkup(), chatId);
        }
        if (update.getMessage().getText().equals("Математика, информатика")) {
            sendMsgWithButtons("Вам подходит:\n" + getAnswer(chatId), replyButtons.keyboardMarkup(), chatId);
        }
        if (update.getMessage().getText().equals("Моя информация")) {
            sendMsgWithButtons(userService.getUserInformation(userService.getById(chatId)), replyButtons.keyboardMarkup(), chatId);
        }

    }

    private String getAnswer(String chatId){
        String[] array =  new String[]{"ФКП", "ФИТУ", "ФРЭ", "ФКСИС", "ФИК", "ИЭФ", "ВФ"};
        List<String> faculties = new ArrayList<>(Arrays.asList(array));
        if(userService.getById(chatId).getPointNum()<=147){
            faculties.remove("ВФ");
        }
        if(userService.getById(chatId).getPointNum()<=269){
            faculties.remove("ИЭФ");
        }
        if(userService.getById(chatId).getPointNum()<=233){
            faculties.remove("ФИК");
        }
        if(userService.getById(chatId).getPointNum()<=256){
            faculties.remove("ФКСИС");
        }
        if(userService.getById(chatId).getPointNum()<=202){
            faculties.remove("ФРЭ");
        }
        if(userService.getById(chatId).getPointNum()<=252){
            faculties.remove("ФИТУ");
        }
        if(userService.getById(chatId).getPointNum()<=237){
            faculties.remove("ФКП");
        }
        if(questionOne==1){
            faculties.remove("ВФ");
            faculties.remove("ФИТУ");
            faculties.remove("ФКСИС");
        }
        if(questionOne==2){
            faculties.remove("ВФ");
            faculties.remove("ФРЭ");
            faculties.remove("ФИК");
            faculties.remove("ФКП");
        }
        if(questionOne==3){
            faculties.remove("ВФ");
            faculties.remove("ФРЭ");
            faculties.remove("ФИК");
            faculties.remove("ФКП");
            faculties.remove("ФКСИС");
            faculties.remove("ФИТУ");
        }
        if(questionOne==4){
            faculties.remove("ВФ");
            faculties.remove("ФРЭ");
            faculties.remove("ИЭФ");
            faculties.remove("ФКП");
            faculties.remove("ФКСИС");
            faculties.remove("ФИТУ");
        }
        if(questionOne==5){
            faculties.remove("ФИК");
            faculties.remove("ФРЭ");
            faculties.remove("ИЭФ");
            faculties.remove("ФКП");
            faculties.remove("ФКСИС");
            faculties.remove("ФИТУ");
        }
        if(questionTwo==1){
            faculties.remove("ВФ");
            faculties.remove("ФРЭ");
            faculties.remove("ФИК");
            faculties.remove("ИЭФ");
            faculties.remove("ФКСИС");
            faculties.remove("ФИТУ");
        }
        if(questionTwo==2){
            faculties.remove("ВФ");
            faculties.remove("ФКП");
            faculties.remove("ФКСИС");
            faculties.remove("ФИТУ");
            faculties.remove("ИЭФ");
        }
        if(questionTwo==3){
            faculties.remove("ВФ");
            faculties.remove("ФКП");
            faculties.remove("ФКСИС");
            faculties.remove("ФРЭ");
            faculties.remove("ФИК");
            faculties.remove("ИЭФ");
        }
        if(questionTwo==4){
            faculties.remove("ВФ");
            faculties.remove("ФКП");
            faculties.remove("ФИК");
            faculties.remove("ФИТУ");
            faculties.remove("ИЭФ");
            faculties.remove("ФРЭ");
        }
        if(questionTwo==5){
            faculties.remove("ФКСИС");
            faculties.remove("ФКП");
            faculties.remove("ФИК");
            faculties.remove("ФИТУ");
            faculties.remove("ИЭФ");
            faculties.remove("ФРЭ");

        }
        if(faculties.size()==1){
            User user = userService.getById(chatId);
            user.setFaculty(faculties.get(0));
            userService.save(user);
            return faculties.get(0);
        }
        if(faculties.isEmpty()){
            return "Вам не подошел ни один из факультетов";
        }
        return faculties.toString();
    }

    private boolean isAlpha(String name) {
        return name.matches("[а-яА-Я ]+");
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

    @Override
    public String getBotUsername() {
        return "YuraskasKPBot";
    }

    @Override
    public String getBotToken() {
        return "5302266375:AAHYpDec2GdDyZ_XRMkTcKrgzdtMKZvUvI8";
    }
}


