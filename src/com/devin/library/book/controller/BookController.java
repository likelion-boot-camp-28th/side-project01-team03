package com.devin.library.book.controller;

import com.devin.library.book.model.dto.Bookdto;
import com.devin.library.book.model.service.BookService;
import com.devin.library.book.model.service.BookServiceImpl;

import java.util.List;

public class BookController {
    private BookService bookService = new BookServiceImpl();

    public void registerBook(String title, String author) { //책등록 출력
        Bookdto book = bookService.register(title, author);
        System.out.println("등록 되었습니다" + book);
    }

    public void findAll() {
        List<Bookdto> books = bookService.findAll(); // 전체목록 출력
        printBooks(books);
    }

    public void search(String keyword) { // 특정 키워드 입력 출력
        List<Bookdto> result = bookService.search(keyword);
        printBooks(result);
    }

    public void delete(int id) { // 삭제
        String message = bookService.delete(id);
        System.out.println(message);
    }

    public void printBooks(List<Bookdto> books) { // 출력문 묶음
        if (books.isEmpty()) {
            System.out.println("책이 없습니다");
            return;
        }
        for (Bookdto book : books) {
            System.out.println(book);
        }
    }

}
