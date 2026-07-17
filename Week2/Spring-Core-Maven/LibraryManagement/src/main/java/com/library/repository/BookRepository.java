package com.library.repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BookRepository {

    private final List<String> books = new ArrayList<>(Arrays.asList(
            "The Pragmatic Programmer",
            "Effective Java",
            "Clean Code",
            "Design Patterns"
    ));

    public List<String> findAll() {
        return new ArrayList<>(books);
    }

    public String findByTitle(String title) {
        for (String book : books) {
            if (book.equalsIgnoreCase(title)) {
                return book;
            }
        }
        return null;
    }

    public void save(String title) {
        books.add(title);
    }
}
