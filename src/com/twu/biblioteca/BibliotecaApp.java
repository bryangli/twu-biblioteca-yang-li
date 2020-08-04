package com.twu.biblioteca;

import java.util.List;
import java.util.ArrayList;

public class BibliotecaApp {
    private static List<Book> books = new ArrayList<>();

    private static void loadBooks() {
        books.add(new Book("Little Prince", 1943, "Antoine de Saint-Exupery"));
        books.add(new Book("The Great Gatsby", 1925, "F. Scott Fitzgerald"));
        books.add(new Book("Pride and Prejudice", 1813, "Jane Austen"));
    }

    private static void displayBooks() {
        loadBooks();
        int maxTitleLen = 0;
        for (int i = 0; i < books.size(); i++) {
            maxTitleLen = books.get(i).getTitle().length() > maxTitleLen ? books.get(i).getTitle().length() : maxTitleLen;
        }

        System.out.println("\nBelow are the books available here at Biblioteca:\n");
        for (int i = 0; i < books.size(); i++) {
            Book currentBook = books.get(i);
            System.out.println(currentBook.getPublicationYear() + " | " +
                    currentBook.getTitle() + " ".repeat(maxTitleLen - currentBook.getTitle().length()) + " | " +
                    currentBook.getAuthor());
        }
    }

    public static void main(String[] args) {
        System.out.println("\nWelcome to Biblioteca. Your one-stop-shop for great book titles in Bangalore!");
        displayBooks();
    }
}
