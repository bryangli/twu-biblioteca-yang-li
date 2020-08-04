package com.twu.biblioteca;

public class Movie {

    private int yearMade;
    private String name;
    private String director;
    private double movieRating;

    public Movie(String name, int yearMade, String director, double rating) {
        this.yearMade = yearMade;
        this.name = name;
        this.director = director;
        this.movieRating = rating;
    }

    public int getYearMade() { return this.yearMade; }

    public String getName() { return this.name; }

    public String getDirector() { return director; }

    public double getMovieRating () { return this.movieRating; }

}
