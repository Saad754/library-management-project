package com.example.library;

public class Book {

    private String title;
    private String author;
    private String category;
    private Integer id;
    public Book(String title, String author, String category, Integer id) {
        this.title = title;
        this.author = author;
        this.category = category;
        this.id = id;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getAuthor() {
        return author;
    }
    public void setAuthor(String author) {
        this.author = author;
    }
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getCategory() {
        return category;
    }
    public void setCategory(String category) {
        this.category = category;
    }
}
