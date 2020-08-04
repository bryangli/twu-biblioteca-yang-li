package com.twu.biblioteca;

import java.util.ArrayList;
import java.util.List;

public class User {
    private String name;
    private String email;
    private String phone;
    private String userId;
    private String password;

    public User(String name, String email, String phone, String password, int num) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.password = password;
        String number = Integer.toString(num);
        if (number.length() <= 4) {
            number = "000-" + "0".repeat(4 - number.length()) + number;
        } else {
            number = "0".repeat(7 - number.length()) + number.substring(0, number.length() - 4)
                    + "-" + number.substring(number.length() - 4);
        }
        this.userId = number;
    }

    public String getEmail() { return email; }
    public String getName() { return name; }
    public String getPhone() { return phone; }
    public String getUserId() { return userId; }

    public boolean authentication(String userId, String passcode) {
        if (this.userId.equals(userId) && this.password.equals(passcode)) {
            return true;
        }
        return false;
    }
}