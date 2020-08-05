package com.twu.biblioteca;

import java.util.Scanner;

public class BibliotecaApp {
    private static String[] bookOptions = {"List of Books", "Check Out", "Return", "To Movie Section", "EXIT"};
    private static String[] movieOptions = {"List of Movies", "Check Out", "Return", "To Book Section", "EXIT"};
    private static String currentSection;
    private static BibliotecaSection bookSection = new BookSection();
    private static BibliotecaSection movieSection = new MovieSection();
    private static UserDatabase userDatabase = new UserDatabase();

    private static String readInput() {
        Scanner in = new Scanner(System.in);
        return in.nextLine();
    }

    private static void takeAction(String command, BibliotecaSection section) {
        // Exit the program if customers want to
        if (command.toLowerCase().equals("exit")) { return; }

        // Check if entered option exists in corresponding section
        boolean availableOption = false;
        if (currentSection.equals("Books")) {
            for (String s: bookOptions) {
                if (command.toLowerCase().equals(s.toLowerCase())) {
                    availableOption = true;
                    break;
                }
            }
        } else {
            for (String s: movieOptions) {
                if (command.toLowerCase().equals(s.toLowerCase())) {
                    availableOption = true;
                    break;
                }
            }
        }

        // if option is not available, re-enter an option
        if (!availableOption) {
            System.out.print("\nPlease enter a valid option: ");
            takeAction(readInput(), section);
        } else {
            // use if statements to take care of actions related to different menu options
            if (command.toLowerCase().startsWith("list")) {
                section.display();
            } else if (command.toLowerCase().startsWith("check")) {
                // when users choose check out/return, they will need to log in with maximum 3 attempts
                System.out.println("\nPlease log in!");
                User loggedIn = logIn();
                if (loggedIn != null) {
                    System.out.println("Welcome " + loggedIn.getName() + "!");
                    System.out.println("Your email address: " + loggedIn.getEmail());
                    System.out.println("Your phone number: " + loggedIn.getPhone());
                    System.out.print("\nPlease enter the item you want to check out: ");
                    section.checkOut(readInput());
                }
            } else if (command.toLowerCase().startsWith("return")) {
                System.out.println("\nPlease log in!");
                User loggedIn = logIn();
                if (loggedIn != null) {
                    System.out.println("Welcome " + loggedIn.getName() + "!");
                    System.out.println("Your email address: " + loggedIn.getEmail());
                    System.out.println("Your phone number: " + loggedIn.getPhone());
                    System.out.print("\nPlease enter the item you want to return: ");
                    section.returning(readInput());
                }
            } else {
                // if users want to go to the other section, corresponding menu will be displayed
                currentSection = currentSection.equals("Books") ? "Movies" : "Books";
                displayMenu(currentSection);
            }
            // user will continue to give command until QUIT is given
            System.out.print("\nNext you want to: ");
            if (currentSection.equals("Books")) {
                takeAction(readInput(), bookSection);
            } else {
                takeAction(readInput(), movieSection);
            }
        }
    }

    private static User logIn() {
        int failTimes = 0;
        System.out.print("UserID: ");
        String userId = readInput();
        System.out.print("Password: ");
        String password = readInput();
        User logIn = userDatabase.authentication(userId, password);
        while (failTimes < 2 && logIn == null) {
            failTimes++;
            System.out.println("Your userID/password is incorrect. Please try again. You have "
                    + (3 - failTimes) + " attempts left");
            System.out.print("UserID: ");
            userId = readInput();
            System.out.print("Password: ");
            password = readInput();
            logIn = userDatabase.authentication(userId, password);
        }
        if (logIn == null) {
            return null;
        } else {
            return logIn;
        }
    }

    private static void displayMenu(String sectionType) {
        // display a list of options that can be executed on selected section
        if (sectionType.toLowerCase().equals("books")) {
            currentSection = "Books";
        } else if (sectionType.toLowerCase().equals("movies")) {
            currentSection = "Movies";
        } else {
            System.out.print("\nPlease enter a valid section name: ");
            displayMenu(readInput());
        }
        System.out.println("\nHere are some options you can explore in this section: ");
        if (currentSection.equals("Books")) {
            for (String s: bookOptions) {
                System.out.println("          " + s);
            }
        } else{
            for (String s: movieOptions) {
                System.out.println("          " + s);
            }
        }
    }

    public static void main(String[] args) {
        // Welcome message and guide customers to the right section
        System.out.println("\nWelcome to Biblioteca. Your one-stop-shop for great book titles in Bangalore!\n");
        System.out.println("You want to see BOOKS or MOVIES?");

        // Display the options for different sections
        displayMenu(readInput());

        System.out.print("\nPlease choose an option: ");
        if (currentSection.equals("Books")) {
            takeAction(readInput(), bookSection);
        } else {
            takeAction(readInput(), movieSection);
        }
    }
}
