package by.adukar.telegrambot.service;

import by.adukar.telegrambot.model.User;
import by.adukar.telegrambot.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{
    private final UserRepo userRepo;

    @Autowired
    public UserServiceImpl(UserRepo userRepo) {
        this.userRepo = userRepo;
    }


    @Override
    public void save(User user) {
        userRepo.save(user);
    }

    @Override
    public User getById(String id) {
        return userRepo.getByChatId(id);
    }

    @Override
    public String getUserInformation(User user) {
        if(user.getFaculty() == null){
            user.setFaculty("Ни один");
        }
        return user.getFio() +"\nВаше коллличество баллов: " + user.getPointNum() + "\nВам подходит факультет: " + user.getFaculty();
    }
}
