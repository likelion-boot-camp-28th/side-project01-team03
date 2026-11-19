package com.library.book;

public class Book {

    private Long id;
    private String title;
    private String author;
    private boolean available = true; // 대출 가능 여부

    public Book(String title, String author) {
        this.title = title;
        this.author = author;
    }

    public Long getId() { return id; }
    void setId(Long id) { this.id = id; }
    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public boolean isAvailable() { return available; }
    public void setAvailable(boolean available) { this.available = available; }

    @Override
    public String toString() {
        return String.format("[%d] %s / %s (%s)", id, title, author, available ? "대출가능" : "대출중");
    }
}
