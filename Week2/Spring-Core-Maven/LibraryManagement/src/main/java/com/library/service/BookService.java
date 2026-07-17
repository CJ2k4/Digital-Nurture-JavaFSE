package com.library.service;

import com.library.repository.BookRepository;

import java.util.List;

public class BookService {

    private BookRepository bookRepository;

    public void setBookRepository(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public BookRepository getBookRepository() {
        return bookRepository;
    }

    public List<String> getAllBooks() {
        return bookRepository.findAll();
    }

    public String findBook(String title) {
        return bookRepository.findByTitle(title);
    }

    public void addBook(String title) {
        bookRepository.save(title);
    }
}
