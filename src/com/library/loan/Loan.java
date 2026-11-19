package com.library.loan;

import java.time.LocalDate;

public class Loan {

    private Long id;
    private Long bookId;
    private Long memberId;
    private LocalDate loanDate;
    private LocalDate dueDate;
    private LocalDate returnDate; // null 이면 대출 중

    public Loan(Long bookId, Long memberId, LocalDate loanDate, LocalDate dueDate) {
        this.bookId = bookId;
        this.memberId = memberId;
        this.loanDate = loanDate;
        this.dueDate = dueDate;
    }

    public Long getId() { return id; }
    void setId(Long id) { this.id = id; }
    public Long getBookId() { return bookId; }
    public Long getMemberId() { return memberId; }
    public LocalDate getLoanDate() { return loanDate; }
    public LocalDate getDueDate() { return dueDate; }
    public LocalDate getReturnDate() { return returnDate; }
    public boolean isReturned() { return returnDate != null; }

    public void markReturned(LocalDate date) {
        this.returnDate = date;
    }

    @Override
    public String toString() {
        return String.format("[%d] 도서 %d / 회원 %d / 대출 %s / 반납예정 %s / %s",
                id, bookId, memberId, loanDate, dueDate, isReturned() ? "반납 " + returnDate : "대출중");
    }
}
