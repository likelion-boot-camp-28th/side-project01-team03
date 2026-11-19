package com.devin.library.book.model.service;

import com.devin.library.book.model.dto.Bookdto;

import java.util.ArrayList;
import java.util.List;

public class BookServiceImpl implements BookService {

    private List<Bookdto> books = new ArrayList<>();
    private int nextId;

    public BookServiceImpl() {
        int maxId = 0; // 번호 겹침 방지
        for (Bookdto b : books) {
            if (b.getId() > maxId) {
                maxId = b.getId();
            }
            nextId = maxId + 1;
        }
    }

    @Override // 1. 책 전체 조회
    public List<Bookdto> findAll() {
        return books;
    }

    @Override // 2. 책 등록
    public Bookdto register(int id, String title, String author) {
        Bookdto book = new Bookdto(nextId, title, author, false);
        nextId++;
        books.add(book);        // 중복id가 들어갈수있으니 나중에 조치
        return book;
    }

    @Override // 3. id로 검색하기
    public Bookdto findById(int id) {
        for (Bookdto b : books) {
            if (b.getId() == id) {
                return b;
            }
        }
        return null;
    }

    @Override // 4. 특정 키워드로 검색하기
    public List<Bookdto> search(String keyword) {
        List<Bookdto> result = new ArrayList<>();
        for (Bookdto b : books) {
            if (b.getTilte().contains(keyword) || b.getAuthor().contains(keyword)) {
                result.add(b);
            }
        }
        return result;
    }

    @Override // 5. 책 삭제하기
    public Bookdto delete(int id) {
        // 예외 조건문 수정 예정
        Bookdto book = findById(id);
            books.remove(book);
            return book;
        }
}
