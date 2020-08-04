package com.twu.biblioteca;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class MovieTest {

    MovieSection movieSection = new MovieSection();

    @Test
    public void testMovieCheckOut() {
        movieSection.checkOut("Parasite");
        assertEquals(movieSection.getMovieCount(), 4);
    }

    @Test
    public void testMovieReturn() {
        movieSection.returning("Parasite");
        assertEquals(movieSection.getMovieCount(), 5);
    }
}
