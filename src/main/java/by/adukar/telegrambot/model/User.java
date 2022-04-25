package by.adukar.telegrambot.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "user")
public class User {
    @Id
    private String chatId;
    private String fio;
    private Integer pointNum;
    private String faculty;
}
