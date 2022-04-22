package by.adukar.telegrambot.service;

import by.adukar.telegrambot.model.User;
import lombok.SneakyThrows;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class FileService {
    private static FileWriter fileWriter;
    private static File file;
    private static List<User> userList = new LinkedList<>();

    @SneakyThrows
    public void writeUserToFile(User user) {
        JSONObject obj = new JSONObject();
        JSONObject object = new JSONObject();

        obj.put("chatId", user.getChatId());
        obj.put("firstName", user.getFirstName());
        obj.put("lastName", user.getLastName());
        obj.put("userName", user.getUserName());
        obj.put("isAdmin", user.isAdmin());
        obj.put("isTeacher", user.isTeacher());
        obj.put("isStudent", user.isStudent());
        obj.put("isBlocked", user.isBlocked());
        object.put("user", obj);

        JSONArray userList = new JSONArray();
        userList.add(object);
        try {
            file = new File("/home/dev/IdeaProjects/Adukar_4_1_Bots/src/main/resources/users/" + user.getUserName() + ".json");
            file.createNewFile();

            fileWriter = new FileWriter("/home/dev/IdeaProjects/Adukar_4_1_Bots/src/main/resources/users/" + user.getUserName() + ".json", false);
            fileWriter.write(userList.toJSONString());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                fileWriter.flush();
                fileWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @SneakyThrows
    public User readFromFile(String userName) {
        JSONParser jsonParser = new JSONParser();
        try (FileReader reader = new FileReader("/home/dev/IdeaProjects/Adukar_4_1_Bots/src/main/resources/users/"  + userName + ".json")) {
            Object obj = jsonParser.parse(reader);
            JSONArray userList = (JSONArray) obj;
            userList.forEach(emp -> {
                parseUserObject((JSONObject)emp);
            });

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return userList.get(0);
    }

    private void parseUserObject(JSONObject user) {
        JSONObject jsonObject = (JSONObject) user.get("user");

        String firstName = String.valueOf(jsonObject.get("firstName"));
        String lastName = String.valueOf(jsonObject.get("lastName"));
        String chatId = String.valueOf(jsonObject.get("chatId"));
        String userName = String.valueOf(jsonObject.get("userName"));
        boolean isAdmin = Boolean.parseBoolean(String.valueOf(jsonObject.get("chatId")));
        boolean isTeacher = Boolean.parseBoolean(String.valueOf(jsonObject.get("chatId")));
        boolean isStudent = Boolean.parseBoolean(String.valueOf(jsonObject.get("chatId")));
        boolean isBlocked = Boolean.parseBoolean(String.valueOf(jsonObject.get("isBlocked")));

        User newUser =  User.builder()
                .chatId(Long.valueOf(chatId))
                .firstName(firstName)
                .lastName(lastName)
                .userName(userName)
                .isAdmin(isAdmin)
                .isTeacher(isTeacher)
                .isStudent(isStudent)
                .isBlocked(isBlocked)
                .build();
        userList.add(newUser);
    }

    public String getAllUsers(){
        File dir = new File("/home/dev/IdeaProjects/Adukar_4_1_Bots/src/main/resources/users/");
        StringBuilder users = new StringBuilder();
        if(dir.isDirectory())
        {
            for(File item : Objects.requireNonNull(dir.listFiles())){
                if(item.isDirectory()){
                    System.out.println(item.getName() + "  \t folder");
                }
                else{
                    users.append(item.getName().replace(".json", " ")).append(" ");
                }
            }
        }
        return users.toString();
    }
}
