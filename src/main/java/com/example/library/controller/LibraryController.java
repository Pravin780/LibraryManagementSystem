package com.example.library.controller;

import com.example.library.model.Book;
import com.example.library.service.LibraryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/library")
public class LibraryController {
    @Autowired
    private LibraryService libraryService;

    @PostMapping("/add")
    public void addBook(@RequestBody Book book) {
        libraryService.addBook(book);
    }

    @GetMapping("/search")
    public Book searchBook(@RequestParam String keyword) {
        return libraryService.searchBook(keyword);
    }

    @GetMapping("/catalog")
    public List<Book> displayCatalog() {
        return libraryService.getBooks();
    }

    @PostMapping("/checkout")
    public void checkOutBook(@RequestParam String isbnId) {
        libraryService.checkOutBook(isbnId);
    }

    @PostMapping("/return")
    public void returnBook(@RequestParam String isbnId) {
        libraryService.returnBook(isbnId);
    }
}
