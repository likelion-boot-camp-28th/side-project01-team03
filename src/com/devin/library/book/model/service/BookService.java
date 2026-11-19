package com.devin.library.book.model.service;

import com.devin.library.book.model.dto.Bookdto;

import java.util.ArrayList;
import java.util.List;

public interface BookService {

    // 1. 책 전체 조회
    List<Bookdto> findAll(); //책 전체 조회

    // 2. 책 등록

    Bookdto register(int id, String title, String author);

    // 3. 번호로 찾기

    Bookdto findById(int id);

    // 4. 특정 키워드 책 조회

    List<Bookdto> search(String keyword);

    // 5. 책 삭제
    Bookdto delete(int id);
}

//Todo 1.책 전체 조회 , 2. 책 등록  3. 특정키워드 책 조회  4. 책 삭제


