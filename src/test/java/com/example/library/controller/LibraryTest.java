package com.example.library.controller;

import com.example.library.model.Book;
import com.example.library.repository.BookRepository;
import com.example.library.service.LibraryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class LibraryTest {

    @InjectMocks
    private LibraryService libraryService;

    @Mock
    private BookRepository bookRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testAddBook() {
        Book book = new Book("1234567890", "Test Book", "Test Author", "Fiction");
        when(bookRepository.save(book)).thenReturn(book);
        libraryService.addBook(book);
        verify(bookRepository, times(1)).save(book);
    }

    @Test
    public void testSearchBook() {
        Book book = new Book("1234567890", "Test Book", "Test Author", "Fiction");
        when(bookRepository.findByBookNameIgnoreCase("Test Book")).thenReturn(book);
        Book foundBook = libraryService.searchBook("Test Book");
        assertNotNull(foundBook);
        assertEquals("Test Author", foundBook.getAuthor());
    }

    @Test
    public void testCheckOutBook() {
        Book book = new Book("1234567890", "Test Book", "Test Author", "Fiction");
        when(bookRepository.findById("1234567890")).thenReturn(Optional.of(book));
        libraryService.checkOutBook("1234567890");
        assertFalse(book.isAvailable());
    }

    @Test
    public void testReturnBook() {
        Book book = new Book("1234567890", "Test Book", "Test Author", "Fiction");
        when(bookRepository.findById("1234567890")).thenReturn(Optional.of(book));
        libraryService.returnBook("1234567890");
        assertTrue(book.isAvailable());
    }
}