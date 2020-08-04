package com.twu.biblioteca;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class UserTest {

    @Test
    public void testUserIDAssignment_1() {
        User zach = new User("Zach", "zachchchch@hotmail.com", "5105705444", "imzachery", 0);
        User xavier = new User("Xaiver", "xxxxxavier@hotmail.com", "5105555883", "xavierishere", 1);
        assertEquals(xavier.getUserId(), "000-0001");
    }

    @Test
    public void testUserIDAssignment_2() {
        User zach = new User("Zach", "zachchchch@hotmail.com", "5105705444", "imzachery", 55563);
        User xavier = new User("Xaiver", "xxxxxavier@hotmail.com", "5105555883", "xavierishere", 55568);
        assertEquals(xavier.getUserId(), "005-5568");
    }

    @Test
    public void testUserAuthentication() {
        User zach = new User("Zach", "zachchchch@hotmail.com", "5105705444", "imzachery", 55563);
        assertEquals(zach.authentication("005-5563", "imzachery"), true);
    }

    @Test
    public void testUserLocation() {
        UserDatabase userDatabase = new UserDatabase();
        assertEquals(userDatabase.authentication("000-0001", "bryannnnnn").getName(), "Bryan");
    }
}
