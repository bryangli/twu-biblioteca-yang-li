package com.twu.biblioteca;

public class Book {

    private int publicationYear;
    private String title;
    private String author;

    public Book(String title, int publicationYear, String author) {
        this.publicationYear = publicationYear;
        this.title = title;
        this.author = author;
    }

    public int getPublicationYear() { return this.publicationYear; }

    public String getTitle() { return this.title; }

    public String getAuthor() { return this.author; }

}
