package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;
import org.hibernate.SessionFactory;
import org.hibernate.internal.SessionFactoryImpl;

import java.lang.module.Configuration;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {




        UserServiceImpl userService = new UserServiceImpl();
        userService.createUsersTable();

        User user1 = new User("Vanya", "baban", (byte) 35);
        User user2 = new User("Viktor", "sherema", (byte) 38);
        User user3 = new User("Vasiliy", "usach", (byte) 18);
        User user4 = new User("Nik", "shishkin", (byte) 44);

       for (User user : Arrays.asList(user1, user2, user3, user4)) {
            userService.saveUser(user.getName(), user.getLastName(), user.getAge());
            System.out.printf("New user – %s добавлен БД %n", user.getName());
        }
        //System.out.println(userService.getAllUsers());
        userService.cleanUsersTable();
        userService.dropUsersTable();


    }
}