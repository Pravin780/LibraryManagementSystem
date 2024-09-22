package com.example.library.service;

import com.example.library.model.Book;
import com.example.library.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LibraryService {
    @Autowired
    private BookRepository bookRepository;

    public void addBook(Book book) {
        bookRepository.save(book);
    }

    public Book searchBook(String keyword) {
        Book book = bookRepository.findByBookNameIgnoreCase(keyword);
        return book != null ? book : bookRepository.findByAuthorIgnoreCase(keyword);
    }

    public List<Book> getBooks() {
        return bookRepository.findAll();
    }

    public void checkOutBook(String isbnId) {
        Book book = bookRepository.findById(isbnId).orElse(null);
        if (book != null && book.isAvailable()) {
            book.setAvailable(false);
            bookRepository.save(book);
        }
    }

    public void returnBook(String isbnId) {
        Book book = bookRepository.findById(isbnId).orElse(null);
        if (book != null) {
            book.setAvailable(true);
            bookRepository.save(book);
        }
    }
}
