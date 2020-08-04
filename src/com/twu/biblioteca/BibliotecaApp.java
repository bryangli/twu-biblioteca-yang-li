package com.twu.biblioteca;

import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

public class BibliotecaApp {
    private static List<Book> books = new ArrayList<>();
    private static List<Book> checkedOutBooks =  new ArrayList<>();
    private static List<Movie> movies = new ArrayList<>();
    private static List<Movie> checkedOutMovies =  new ArrayList<>();
    private static String[] options = {"List of Books", "List of Movies", "Check Out", "Return", "EXIT"};

    private static void loadBooks() {
        // An example of three books
        books.add(new Book("Little Prince", 1943, "Antoine de Saint-Exupery"));
        books.add(new Book("The Great Gatsby", 1925, "F. Scott Fitzgerald"));
        books.add(new Book("Pride and Prejudice", 1813, "Jane Austen"));
    }

    private static void loadMovies() {
        // An example of several films
        movies.add(new Movie("Parasite", 2019, "Bong Joon-ho", 8.6));
        movies.add(new Movie("Booksmart", 2019, "Olivia Wilde", 7.2));
        movies.add(new Movie("Marriage Story", 2019, "Noah Baumbach", 8.0));
        movies.add(new Movie("The Intern", 2015, "Nancy Meyers", 7.1));
        movies.add(new Movie("Love Actually", 2003, "Richard Curtis", 7.6));
    }

    private static void displayBooks() {
        // Calculate the longest book name for formatting purposes
        int maxTitleLen = 0;
        for (int i = 0; i < books.size(); i++) {
            maxTitleLen = books.get(i).getTitle().length() > maxTitleLen ? books.get(i).getTitle().length() : maxTitleLen;
        }

        // Print out a list of books
        System.out.println("Below are the books available here at Biblioteca:\n");
        for (int i = 0; i < books.size(); i++) {
            Book currentBook = books.get(i);
            System.out.println(currentBook.getPublicationYear() + " | " +
                    currentBook.getTitle() + " ".repeat(maxTitleLen - currentBook.getTitle().length()) + " | " +
                    currentBook.getAuthor());
        }
    }

    private static void displayMovies() {
        // Calculate the longest movie name for formatting purposes
        int maxTitleLen = 0, maxDirectorLen = 0;
        for (Movie m: movies) {
            maxTitleLen = m.getName().length() > maxTitleLen ? m.getName().length() : maxTitleLen;
            maxDirectorLen = m.getDirector().length() > maxDirectorLen ? m.getDirector().length() : maxDirectorLen;
        }

        // Print out a list of books
        System.out.println("Below are the movies available here at Biblioteca:\n");
        for (Movie m: movies) {
            System.out.println(m.getYearMade() + " | " +
                    m.getName() + " ".repeat(maxTitleLen - m.getName().length()) + " | " +
                    m.getDirector() + " ".repeat(maxDirectorLen - m.getDirector().length()) + " | " +
                    m.getMovieRating());
        }
    }

    private static void checkOut(String bookName) {
        for (Book b: books) {
            if (b.getTitle().toLowerCase().equals(bookName.toLowerCase())) {
                checkedOutBooks.add(b);
                books.remove(b);
                System.out.println("Thank you! Enjoy the book :)");
                return;
            }
        }
        System.out.println("Sorry, that book is not available :(");
    }

    private static void returnBook(String bookName) {
        for (Book b: checkedOutBooks) {
            if (b.getTitle().toLowerCase().equals(bookName.toLowerCase())) {
                books.add(b);
                checkedOutBooks.remove(b);
                System.out.println("Thank you for returning the book :)");
                return;
            }
        }
        System.out.println("That is not a valid book to return :(");
    }

    private static void takeAction(String command) {
        // Check if the actions that customers enter exist in the action library
        boolean availableOption = false;

        if (command.toLowerCase().equals("exit")) {
            return;
        }

        for (String s: options) {
            if (s.toLowerCase().equals(command.toLowerCase())) {
                availableOption = true;
                break;
            }
        }

        // Take corresponding actions for the messages that customers enter; if the action does not exist, re-enter
        if (availableOption) {
            if (command.toLowerCase().equals("list of books")) {
                displayBooks();
            } else if (command.toLowerCase().equals("list of movies")) {
                displayMovies();
            } else if (command.toLowerCase().equals("check out")) {
                System.out.print("\nWhich book would you like to check out? ");
                Scanner inBook = new Scanner(System.in);
                String bookName = inBook.nextLine();
                checkOut(bookName);
            } else if (command.toLowerCase().equals("return")) {
                System.out.print("\nPlease enter the book you are returning: ");
                Scanner inBook = new Scanner(System.in);
                String bookName = inBook.nextLine();
                returnBook(bookName);
            }
            System.out.print("\nNext: ");
            Scanner in = new Scanner(System.in);
            String nextCommand = in.nextLine();
            takeAction(nextCommand);
        } else {
            System.out.print("\nPlease enter a valid option: ");
            Scanner in = new Scanner(System.in);
            String nextCommand = in.nextLine();
            takeAction(nextCommand);
        }
    }

    public static void main(String[] args) {
        // Welcome message and show a list of options that a customer can take
        loadBooks();
        loadMovies();
        System.out.println("\nWelcome to Biblioteca. Your one-stop-shop for great book titles in Bangalore!\n");
        System.out.println("Here are some options to explore:");
        for (String s: options) {
            System.out.println(" ".repeat(10) + s);
        }

        // An input area for the customers to select what they want to do next
        System.out.print("\nPlease enter: ");
        Scanner in = new Scanner(System.in);

        // Read the action that customers input and take corresponding actions
        String command = in.nextLine();
        takeAction(command);
    }
}
