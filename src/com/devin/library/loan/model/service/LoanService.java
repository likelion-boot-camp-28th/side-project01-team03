package com.devin.library.loan.model.service;

import com.devin.library.common.LibraryException;
import com.devin.library.loan.model.dto.LoanDto;
import com.devin.library.loan.model.repository.LoanRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/* 대출 가능 여부 판단 같은 로직은 Service에서 */
public class LoanService {

    private static final int LOAN_DAYS = 14;        // 대출 기간 2주
    private final LoanRepository loanRepository;

    /* 객체를 생성하지 않고 생성자로 받아서 객체가 여러개가 생겨 꼬임을 방지 - 의존성 주입 */
    public LoanService(LoanRepository loanRepository) {
        this.loanRepository = loanRepository;
    }

    /**
     * 도서 대출 메서드
     *
     * @param memberId   대출자 ID
     * @param bookId     대출하려는 도서 ID
     * @param bookName   대출하려는 도서 제목
     * @param bookAuthor 대출하려는 도서 저자
     * @return LoanRepository save() 메서드에 저장된 대출 정보 (대출 ID, 대출일, 반납 예정일 포함)
     * @throws LibraryException 이미 대출중인 도서인 경우
     */
    public LoanDto loanBook(String memberId, int bookId, String bookName, String bookAuthor) {

        /* 이미 대출중이면 */
        if (loanRepository.findActiveByBookId(bookId) != null) {
            throw new LibraryException("해당 도서는 이미 대출중인 도서입니다");
        }

        /* 대출중이 아니라면 */
        LocalDate today = LocalDate.now();                      // 대출일
        LocalDate dueDate = today.plusDays(LOAN_DAYS);          // 대출 반납 예정일
        LoanDto loan = new LoanDto(memberId, bookId, bookName, bookAuthor, today, dueDate);
        return loanRepository.save(loan);

    }

    /**
     * 도서 대출 반납 메서드
     *
     * @param loanId 대출 ID
     * @return 반납 처리된 대출 정보 (반납일 포함)
     * @throws LibraryException 존재하지 않는 대출 ID일 경우
     * @throws LibraryException 이미 반납된 대출인 경우
     */
    public LoanDto returnBook(long loanId) {

        LoanDto loan = loanRepository.findById(loanId);

        /* 대출 기록이 없을 때 */
        if (loan == null) {
            throw new LibraryException("존재하지 않는 대출 기록입니다");
        }

        /* 대출이 이미 반납 되었을 때 */
        if (loan.isReturned()) {
            throw new LibraryException("이미 반납된 대출 도서입니다");
        }

        loan.markReturned(LocalDate.now());

        return loan;
    }

    /**
     * 연체된 대출 목록 조회
     *
     * @return 아직 반납하지 않았고 반납 예정일이 지난 대출 도서 목록, 없으면 빈 List
     */
    public List<LoanDto> getOverdueLoans() {

        List<LoanDto> overdueBook = new ArrayList<>();
        LocalDate today = LocalDate.now();

        for (LoanDto loan : loanRepository.findAll()) {
            /* 연체 기준 : 아직 반납하지 않았으면서 반납 예정일이 오늘보다 이전인 경우 */
            if (!loan.isReturned() && loan.getDueDate().isBefore(today)) {
                overdueBook.add(loan);
            }
        }

        return overdueBook;
    }

    /**
     * 전체 대출 목록 메서드
     *
     * @return 전체 대출 목록, 없으면 빈 List
     */
    public List<LoanDto> getAllLoans() {
        return loanRepository.findAll();
    }

    /**
     * 해당 회원의 대출 목록 메서드
     *
     * @param memberId 대출자 ID
     * @return 해당 회원의 대출 목록, 없으면 빈 List
     */
    public List<LoanDto> getLoansByMember(String memberId) {
        return loanRepository.findByMemberId(memberId);
    }

}
