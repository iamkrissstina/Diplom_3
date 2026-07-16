package data;

import com.github.javafaker.Faker;
import model.UserModelCreate;

public class UserData {
    public static final String CREATE_USER_PATH = "/api/auth/register";
    public static final String LOGIN_USER_PATH = "/api/auth/login";
    public static final String DELETE_USER_PATH = "/api/auth/user";


    static Faker user = new Faker();

    public static UserModelCreate generateValidUser() {
        String email = user.name().lastName() + "_" + System.currentTimeMillis() + "@yandex.ru";
        String password = "password";
        String name = user.name().firstName();
        return new UserModelCreate(email, password, name);
    }
}
