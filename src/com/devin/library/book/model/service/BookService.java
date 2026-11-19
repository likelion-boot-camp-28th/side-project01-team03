package com.devin.library.book.model.service;

import java.util.List;
import java.util.Optional;

/**
 * [책 도메인] 비즈니스 로직.
 * 대출 도메인(LoanService)이 findById / 대출 상태 변경을 사용하므로 메서드 시그니처 변경 시 공유해주세요.
 */
public class BookService {

    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public Book register(String title, String author) {
        return bookRepository.save(new Book(title, author));
    }

    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    public Optional<Book> findById(Long id) {
        return bookRepository.findById(id);
    }

    // TODO: 제목/저자 검색, 수정, 삭제 등
}
