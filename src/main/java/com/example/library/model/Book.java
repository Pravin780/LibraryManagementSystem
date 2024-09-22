package com.example.library.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Book {
    @Id
    private String isbnId;
    private String bookName;
    private String author;
    private String genre;
    private boolean isAvailable;

    public Book() {}

    public Book(String isbnId, String bookName, String author, String genre) {
        this.isbnId = isbnId;
        this.bookName = bookName;
        this.author = author;
        this.genre = genre;
        this.isAvailable = true;
    }


    public String getIsbnId() { return isbnId; }
    public String getBookName() { return bookName; }
    public String getAuthor() { return author; }
    public String getGenre() { return genre; }
    public boolean isAvailable() { return isAvailable; }
    public void setAvailable(boolean available) { isAvailable = available; }
}
