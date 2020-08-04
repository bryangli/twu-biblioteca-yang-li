package com.twu.biblioteca;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class BookTest {

    BookSection bookSection = new BookSection();

    @Test
    public void testBookCheckOut() {
        bookSection.checkOut("Little Prince");
        assertEquals(bookSection.getBookCount(), 2);
    }

    @Test
    public void testBookReturn() {
        bookSection.returning("Little Prince");
        assertEquals(bookSection.getBookCount(), 3);
    }
}
