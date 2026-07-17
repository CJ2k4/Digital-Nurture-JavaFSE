package com.library;

import com.library.repository.BookRepository;
import com.library.service.BookService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class LibraryManagementApplication {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext context =
                new ClassPathXmlApplicationContext("applicationContext.xml");

        System.out.println("=== Exercise 1: beans loaded from applicationContext.xml ===");
        for (String name : context.getBeanDefinitionNames()) {
            System.out.println("  bean: " + name + " -> " + context.getBean(name).getClass().getName());
        }

        BookService bookService = context.getBean("bookService", BookService.class);

        System.out.println("\n=== Exercise 2: dependency injection ===");
        BookRepository injected = bookService.getBookRepository();
        System.out.println("BookRepository injected into BookService: " + (injected != null));
        System.out.println("Injected instance is the container's bean: "
                + (injected == context.getBean("bookRepository", BookRepository.class)));

        System.out.println("\n=== BookService delegating to BookRepository ===");
        List<String> books = bookService.getAllBooks();
        for (String book : books) {
            System.out.println("  " + book);
        }

        System.out.println("\nfindBook('Clean Code')   -> " + bookService.findBook("Clean Code"));
        System.out.println("findBook('Missing Book') -> " + bookService.findBook("Missing Book"));

        bookService.addBook("Refactoring");
        System.out.println("after addBook('Refactoring'), count = " + bookService.getAllBooks().size());

        context.close();
    }
}
