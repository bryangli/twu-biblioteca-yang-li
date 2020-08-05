package com.twu.biblioteca;

import java.util.Collections;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.stream.Collectors;

public class BookSection extends BibliotecaSection {
    private static List<Book> books = new ArrayList<>();
    private static List<Book> checkedOutBooks =  new ArrayList<>();

    public BookSection() {
        load();
    }

    private static void load() {
        // An example of three books
        books.add(new Book("Little Prince", 1943, "Antoine de Saint-Exupery"));
        books.add(new Book("The Great Gatsby", 1925, "F. Scott Fitzgerald"));
        books.add(new Book("Pride and Prejudice", 1813, "Jane Austen"));
    }

    @Override
    public void display() {
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
                    currentBook.getTitle() + Collections.nCopies(maxTitleLen - currentBook.getTitle().length(), " ")
                        .stream().collect(Collectors.joining("")) + " | " + currentBook.getAuthor());
        }
    }

    @Override
    public void checkOut(String bookName) {
        for (Book b: books) {
            if (b.getTitle().toLowerCase().equals(bookName.toLowerCase())) {
                checkedOutBooks.add(b);
                books.remove(b);
                System.out.println("\nThank you! Enjoy the book :)");
                return;
            }
        }
        System.out.println("\nSorry, that book is not available :(");
    }

    @Override
    public void returning(String bookName) {
        for (Book b: checkedOutBooks) {
            if (b.getTitle().toLowerCase().equals(bookName.toLowerCase())) {
                books.add(b);
                checkedOutBooks.remove(b);
                System.out.println("\nThank you for returning the book :)");
                return;
            }
        }
        System.out.println("\nThat is not a valid book to return :(");
    }

    public int getBookCount() {
        return books.size();
    }
}
