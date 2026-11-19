package com.devin.library.loan.model.dto;

import java.time.LocalDate;

public class LoanDto {

    private String loanId;          // 대출 id
    private String memberId;        // 대출자 id
    private String bookId;          // 책 id
    private String bookName;        // 책 이름
    private String bookAuthor;      // 책 저자
    private LocalDate loanDate;     // 대출일
    private LocalDate dueDate;      // 반납 예정일
    private LocalDate returnDate;   // 실제 반납일

    public LoanDto() {
    }

    public LoanDto(String loanId, String memberId, String bookId, String bookName, String bookAuthor, LocalDate loanDate, LocalDate dueDate) {
        this.loanId = loanId;
        this.memberId = memberId;
        this.bookId = bookId;
        this.bookName = bookName;
        this.bookAuthor = bookAuthor;
        this.loanDate = loanDate;
        this.dueDate = dueDate;
    }

    public String getLoanId() {
        return loanId;
    }

    public String getMemberId() {
        return memberId;
    }

    public String getBookId() {
        return bookId;
    }

    public String getBookName() {
        return bookName;
    }

    public String getBookAuthor() {
        return bookAuthor;
    }

    public LocalDate getLoanDate() {
        return loanDate;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    @Override
    public String toString() {
        return "LoanDto{" +
                "loanId='" + loanId + '\'' +
                ", memberId='" + memberId + '\'' +
                ", bookId='" + bookId + '\'' +
                ", bookName='" + bookName + '\'' +
                ", bookAuthor='" + bookAuthor + '\'' +
                ", loanDate=" + loanDate +
                ", dueDate=" + dueDate +
                ", returnDate=" + returnDate +
                '}';
    }

    /**
     * 반납 여부 메서드
     * @return 반납 했으면 true, 대출중이면 false
     */
    public boolean isReturned() {
        return returnDate != null;
    }

    /**
     * 반납 처리
     * @param returnDate 실제 반납일
     */
    public void markReturned(LocalDate returnDate) {
        this.returnDate = returnDate;
    }



}
