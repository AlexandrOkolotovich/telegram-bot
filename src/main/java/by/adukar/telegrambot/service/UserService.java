package by.adukar.telegrambot.service;

import by.adukar.telegrambot.model.User;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.ArrayList;
import java.util.List;

public class UserService {

    private FileService fileService = new FileService();

    public static List<User> userList = new ArrayList<>();

    public void addUserToList(User user) {
        userList.add(user);
        fileService.writeUserToFile(user);
        System.out.println("Пользователь успешно добавлен");
    }

    public User createUserFromUpdate(Update update) {
        return User.builder()
                .chatId(update.getMessage().getChatId())
                .firstName(update.getMessage().getFrom().getFirstName())
                .lastName(update.getMessage().getFrom().getLastName())
                .userName(update.getMessage().getFrom().getUserName())
                .isAdmin(false)
                .isStudent(true)
                .isTeacher(false)
                .isBlocked(false)
                .build();
    }

    public String getAllUser(String userName) {
       return fileService.readFromFile(userName).toString();
    }

    public String getAllUsers(){
        return fileService.getAllUsers();
    }
}
