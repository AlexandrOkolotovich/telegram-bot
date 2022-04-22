package by.adukar.telegrambot.model;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Builder
@Data
public class User implements Serializable {
    private Long chatId;
    private String firstName;
    private String lastName;
    private String userName;
    private boolean isBlocked;
    private boolean isAdmin;
    private boolean isTeacher;
    private boolean isStudent;
}
