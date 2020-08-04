package com.twu.biblioteca;

import java.util.ArrayList;
import java.util.List;

public class UserDatabase {
    private static List<User> userList = new ArrayList<>();

    private static void load() {
        userList.add(new User("Alan", "alan1019@gmail.com", "5105485732",
                "alanisme", userList.size()));
        userList.add(new User("Bryan", "bryan820@gmail.com", "5105534332",
                "bryannnnnn", userList.size()));
        userList.add(new User("Christy", "christy81@gmail.com", "5105485758",
                "christinahahaha", userList.size()));
        userList.add(new User("David", "david905@gmail.com", "5106235732",
                "davidishere", userList.size()));
        userList.add(new User("Elon", "elonmusk@gmail.com", "5105485555",
                "iowntesla", userList.size()));
    }

    public UserDatabase() {
        load();
    }

    public User authentication(String userId, String passcode) {
        for (User u: userList) {
            if (u.authentication(userId, passcode)) {
                return u;
            };
        }
        return null;
    }
}
