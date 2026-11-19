package com.library.loan;

import com.library.book.BookService;
import com.library.member.model.service.MemberService;

import java.util.List;

/**
 * [대출 도메인] 비즈니스 로직.
 * 책/회원 존재 여부와 도서 대출 상태는 BookService, MemberService를 통해 확인한다.
 */
public class LoanService {

    private final LoanRepository loanRepository;
    private final BookService bookService;
    private final MemberService memberService;

    public LoanService(LoanRepository loanRepository, BookService bookService, MemberService memberService) {
        this.loanRepository = loanRepository;
        this.bookService = bookService;
        this.memberService = memberService;
    }

    public List<Loan> findAll() {
        return loanRepository.findAll();
    }

    // TODO: 대출(loan) - 회원/도서 존재 확인, 대출 가능 여부 확인, 도서 상태 변경, 반납예정일 계산
    // TODO: 반납(returnBook) - 반납 처리, 도서 상태 복구, 연체 여부 확인
    // TODO: 회원별 대출 내역 조회 등
}
