package com.dmitrijch.bookLibrary.repository;

import com.dmitrijch.bookLibrary.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
}