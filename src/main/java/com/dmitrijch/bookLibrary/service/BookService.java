package com.dmitrijch.bookLibrary.service;

import com.dmitrijch.bookLibrary.entity.Book;
import com.dmitrijch.bookLibrary.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookService {

    private final BookRepository bookRepository;

    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<Book> getBooksByAuthor(String author) {
        return bookRepository.findAll().stream()
                .filter(book -> book.getAuthor().equalsIgnoreCase(author))
                .collect(Collectors.toList());
    }

    public List<Book> findBooksByTitle(String title) {
        return bookRepository.findAll().stream()
                .filter(book -> book.getTitle().equalsIgnoreCase(title))
                .collect(Collectors.toList());
    }

    public List<Book> getBooksPublishedAfter(int year) {
        return bookRepository.findAll().stream()
                .filter(book -> book.getYear() > year)
                .collect(Collectors.toList());
    }

    public List<Book> getBooksSortedByYear() {
        return bookRepository.findAll().stream().parallel()
                .sorted(Comparator.comparingInt(Book::getYear))
                .collect(Collectors.toList());
    }

    public List<Book> logBooks() {
        return bookRepository.findAll().stream()
                .peek(book -> System.out.println("Logging book: " + book))
                .collect(Collectors.toList());
    }
}