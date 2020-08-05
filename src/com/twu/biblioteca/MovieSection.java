package com.twu.biblioteca;

import java.util.Collections;
import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class MovieSection extends BibliotecaSection{
    private static List<Movie> movies = new ArrayList<>();
    private static List<Movie> checkedOutMovies =  new ArrayList<>();

    public MovieSection() {
        load();
    }

    private static void load() {
        movies.add(new Movie("Parasite", 2019, "Bong Joon-ho", 8.6));
        movies.add(new Movie("Booksmart", 2019, "Olivia Wilde", 7.2));
        movies.add(new Movie("Marriage Story", 2019, "Noah Baumbach", 8.0));
        movies.add(new Movie("The Intern", 2015, "Nancy Meyers", 7.1));
        movies.add(new Movie("Love Actually", 2003, "Richard Curtis", 7.6));
    }

    @Override
    public void display() {
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
                    m.getName() + Collections.nCopies(maxTitleLen - m.getName().length(), " ")
                        .stream().collect(Collectors.joining("")) + " | " +
                    m.getDirector() + Collections.nCopies(maxDirectorLen - m.getDirector().length(), " ")
                        .stream().collect(Collectors.joining(""))+ " | " + m.getMovieRating());
        }
    }

    @Override
    public void checkOut(String bookName) {
        for (Movie m: movies) {
            if (m.getName().toLowerCase().equals(bookName.toLowerCase())) {
                checkedOutMovies.add(m);
                movies.remove(m);
                System.out.println("\nThank you! Enjoy the movie :)");
                return;
            }
        }
        System.out.println("\nSorry, that movie is not available :(");
    }

    @Override
    public void returning(String bookName) {
        for (Movie m: checkedOutMovies) {
            if (m.getName().toLowerCase().equals(bookName.toLowerCase())) {
                movies.add(m);
                checkedOutMovies.remove(m);
                System.out.println("\nThank you for returning the movie :)");
                return;
            }
        }
        System.out.println("\nThat is not a valid movie to return :(");
    }

    public int getMovieCount() {
        return movies.size();
    }

}