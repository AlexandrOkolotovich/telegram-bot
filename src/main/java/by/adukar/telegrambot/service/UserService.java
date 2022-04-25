package by.adukar.telegrambot.service;

import by.adukar.telegrambot.model.User;

public interface UserService {
    void save(User user);
    User getById(String id);
    String getUserInformation(User user);
}
