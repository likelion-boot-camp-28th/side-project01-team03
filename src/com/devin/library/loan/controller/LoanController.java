package com.devin.library.loan.controller;

import com.devin.library.loan.model.dto.LoanDto;
import com.devin.library.loan.model.repository.LoanRepository;
import com.devin.library.loan.model.service.LoanService;

import java.util.List;

public class LoanController {

    private final LoanService loanService = new LoanService(new LoanRepository());

    private void printLoans(List<LoanDto> loans, String emptyMessage) {

        if (loans.isEmpty()) {
            System.out.println(emptyMessage);
            return;
        }

        for (LoanDto loan : loans) {
            System.out.println(loan);
        }

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



}
