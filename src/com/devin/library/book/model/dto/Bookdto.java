package com.devin.library.book.model.dto;

public class Bookdto {
    private int id; // 번호
    private String tilte; // 제목
    private String author; // 저자;
    private boolean borrowed; // 대출 유무

    public int getId() {
        return id;
    }
    public String getTilte() {
        return tilte;
    }
    public String getAuthor() {
        return author;
    }
    public boolean isBorrowed() {
        return borrowed;
    }
    public void setBorrowed(boolean borrowed) {
        this.borrowed = borrowed;
    }
    public Bookdto(int id, String tilte, String author, boolean borrowed) {
        this.id = id;
        this.tilte = tilte;
        this.author = author;
        this.borrowed = borrowed;
    }
    @Override
    public String toString() {
        String status = borrowed ? "대출중" : "대출가능";
        return "Bookdto{" +
                "id=" + id +
                ", tilte='" + tilte + '\'' +
                ", author='" + author + '\'' +
                ", borrowed=" + status +
                '}';
    }
}


