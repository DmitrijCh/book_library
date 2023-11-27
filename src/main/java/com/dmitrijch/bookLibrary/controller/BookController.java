package com.dmitrijch.bookLibrary.controller;

import com.dmitrijch.bookLibrary.entity.Book;
import com.dmitrijch.bookLibrary.repository.BookRepository;
import com.dmitrijch.bookLibrary.service.BookService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Api(tags = "BookController")
@RestController
@RequestMapping("/books")
public class BookController {

    private final BookRepository bookRepository;
    private final BookService bookService;

    @Autowired
    public BookController(BookRepository bookRepository, BookService bookService) {
        this.bookRepository = bookRepository;
        this.bookService = bookService;
    }

    @GetMapping
    @ApiOperation(value = "Получить список всех книг",
            notes = "Возвращает список всех книг из репозитория")
    public List<Book> getBooks() {
        return new ArrayList<>(bookRepository.findAll());
    }

    @ApiOperation(value = "Добавить новую книгу",
            notes = "Добавляет новую книгу в репозиторий")
    @PostMapping
    public Book addBook(@RequestBody Book book) {
        return bookRepository.save(book);
    }

    @ApiOperation(value = "Получить книги по автору",
            notes = "Возвращает отсортированный список книг по указанному автору")
    @PostMapping("/author")
    public List<Book> getBooksByAuthor(@RequestBody Map<String, String> request) {
        String author = request.get("author");
        return bookService.getBooksByAuthor(author);
    }

    @ApiOperation(value = "Получить книги по названию",
            notes = "Возвращает список книг с заданным названием из репозитория")
    @PostMapping("/title")
    public List<Book> findBooksByTitle(@RequestBody Map<String, String> request) {
        String title = request.get("title");
        return bookService.findBooksByTitle(title);
    }

    @ApiOperation(value = "Получить книги, опубликованные после года",
            notes = "Возвращает список книг, опубликованных после указанного года, из репозитория")
    @PostMapping("/year")
    public List<Book> getBooksPublishedAfter(@RequestBody Map<String, Integer> request) {
        int year = request.get("year");
        return bookService.getBooksPublishedAfter(year);
    }

    @ApiOperation(value = "Получить книги, отсортированные по году",
            notes = "Возвращает список книг, отсортированных по году из репозитория")
    @GetMapping("/sortedByYear")
    public List<Book> getBooksSortedByYear() {
        return bookService.getBooksSortedByYear();
    }

    @ApiOperation(value = "Логировать информацию о каждой книге",
            notes = "Возвращает список книг и выводит информацию о каждой книге в лог")
    @GetMapping("/logBooks")
    public List<Book> logBooks() {
        return bookService.logBooks();
    }

    @ApiOperation(value = "Удалить книгу по ID", notes = "Удаляет книгу с указанным ID из репозитория")
    @DeleteMapping("/bookId")
    public void deleteBook(@RequestBody Map<String, Long> request) {
        Long id = request.get("id");
        bookRepository.deleteById(id);
    }
}