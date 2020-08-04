package com.twu.biblioteca;

import java.util.Scanner;

public class BibliotecaApp {
    private static String[] bookOptions = {"List of Books", "Check Out", "Return", "To Movie Section", "EXIT"};
    private static String[] movieOptions = {"List of Movies", "Check Out", "Return", "To Book Section", "EXIT"};
    private static String currentSection;
    private static BibliotecaSection bookSection = new BookSection();
    private static BibliotecaSection movieSection = new MovieSection();

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
            Scanner option = new Scanner(System.in);
            String newCommand = option.nextLine();
            takeAction(newCommand, section);
        } else {
            // use if statements to take care of actions related to different menu options
            if (command.toLowerCase().startsWith("list")) {
                section.display();
            } else if (command.toLowerCase().startsWith("check")) {
                System.out.print("\nPlease enter the item you want to check out: ");
                Scanner checkOutName = new Scanner(System.in);
                String stockName = checkOutName.nextLine();
                section.checkOut(stockName);
            } else if (command.toLowerCase().startsWith("return")) {
                System.out.print("\nPlease enter the item you want to return: ");
                Scanner returnName = new Scanner(System.in);
                String stockName = returnName.nextLine();
                section.returning(stockName);
            } else {
                currentSection = currentSection.equals("Books") ? "Movies" : "Books";
                displayMenu(currentSection);
            }
            System.out.print("\nNext you want to: ");
            Scanner nextCommand = new Scanner(System.in);
            String next = nextCommand.nextLine();
            if (currentSection.equals("Books")) {
                takeAction(next, bookSection);
            } else {
                takeAction(next, movieSection);
            }
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
            Scanner sectionIn = new Scanner(System.in);
            String sectionName = sectionIn.nextLine();
            displayMenu(sectionName);
        }
        System.out.println("\nHere are some options you can explore in this section: ");
        if (currentSection.equals("Books")) {
            for (String s: bookOptions) {
                System.out.println(" ".repeat(10) + s);
            }
        } else{
            for (String s: movieOptions) {
                System.out.println(" ".repeat(10) + s);
            }
        }
    }

    public static void main(String[] args) {
        // Welcome message and guide customers to the right section
        System.out.println("\nWelcome to Biblioteca. Your one-stop-shop for great book titles in Bangalore!\n");
        System.out.println("You want to see BOOKS or MOVIES?");

        // Display the options for different sections
        Scanner in = new Scanner(System.in);
        String sectionName = in.nextLine();
        displayMenu(sectionName);

        System.out.print("\nPlease choose an option: ");
        Scanner option = new Scanner(System.in);
        String command = option.nextLine();
        if (currentSection.equals("Books")) {
            takeAction(command, bookSection);
        } else {
            takeAction(command, movieSection);
        };
    }
}
