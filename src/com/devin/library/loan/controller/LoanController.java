package com.devin.library.loan.controller;

import com.devin.library.book.model.dto.Bookdto;
import com.devin.library.book.model.service.BookService;
import com.devin.library.common.LibraryException;
import com.devin.library.loan.model.dto.LoanDto;
import com.devin.library.loan.model.repository.LoanRepository;
import com.devin.library.loan.model.service.LoanService;

import java.util.List;

public class LoanController {

    private final LoanService loanService = new LoanService(new LoanRepository());
    private final BookService bookService;   // 도서 조회용 (LibraryApp에서 Book 쪽과 같은 객체를 주입받음)

    public LoanController(BookService bookService) {
        this.bookService = bookService;
    }

    private boolean printLoans(List<LoanDto> loans, String emptyMessage) {

        if (loans.isEmpty()) {
            System.out.println(emptyMessage);
            return false;
        }

        for (LoanDto loan : loans) {
            System.out.println(loan);
        }

        return true;
    }

    /**
     * 연체 목록 출력 메서드
     */
    public void showOverdueLoans() {
        printLoans(loanService.getOverdueLoans(), "연체된 대출이 없습니다");
    }

    /**
     * 전체 대출 목록 출력 메서드
     */
    public void showAllLoans() {
        printLoans(loanService.getAllLoans(), "대출 도서가 없습니다");
    }

    /**
     * 내 대출 내역 출력 메서드
     *
     * @param memberId 조회할 대출자 ID
     */
    public void showLoansByMember(String memberId) {

        printLoans(loanService.getLoansByMember(memberId), memberId + "회원의 대출 기록이 없습니다");
    }

    /**
     * 내 미반납 대출 내역 출력 메서드
     * @param memberId 대출자 ID
     * @return 내 미반납 대출 내역
     */
    public boolean showMyActiveLoans(String memberId) {
        return printLoans(loanService.getActiveLoansByMember(memberId), "반납할 도서가 없습니다");
    }

    /**
     * 반납 메서드
     * @param memberId 대출자 ID
     * @param loanId 대출 ID
     */
    public void showReturnBook(String memberId, long loanId) {
        try {
            LoanDto loan =  loanService.returnBook(memberId, loanId);

            Bookdto book = bookService.findById(loan.getBookId());
            if(book != null) {
                book.setBorrowed(false);
            }

            System.out.println("반납 완료 : " + loan.getBookName() + " (반납일 " + loan.getReturnDate() + ")");

        } catch (LibraryException e) {
            System.out.println(e.getMessage());
        }


    }

    /**
     * 도서 대출 메서드
     * @param memberId 대출자 ID
     * @param bookId 책 ID
     */
    public void loanBook(String memberId, int bookId) {
        try {
            Bookdto book = bookService.findById(bookId);

            if(book == null) {
                System.out.println("없는 도서 번호 입니다");
                return;
            }

            LoanDto loan = loanService.loanBook(memberId, bookId, book.getTilte(), book.getAuthor());
            book.setBorrowed(true);

            System.out.println("대출 완료 : " + loan.getBookName() + " (반납 예정일 : " + loan.getDueDate() + ")");

        } catch (LibraryException e) {
            System.out.println(e.getMessage());
        }
    }




}
