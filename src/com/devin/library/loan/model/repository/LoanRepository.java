package com.devin.library.loan.model.repository;

import com.devin.library.loan.model.dto.LoanDto;

import java.util.LinkedHashMap;
import java.util.Map;

/* 데이터를 저장하고 꺼내는 일은 Repository에서 */
public class LoanRepository {

    // 넣은 순서대로 나와야 하므로 LinkedHashMap 사용
    private final Map<Long, LoanDto> loans = new LinkedHashMap<>();
    private long nextId = 1;    // 다음 대출 ID

    /**
     * 대출 기록 저장 (새 대출 ID 자동 부여)
     * @param loan 저장할 대출 정보
     * @return ID가 부여된 대출 정보
     */
    public LoanDto save(LoanDto loan) {

        loan.setLoanId(nextId);
        nextId++;
        loans.put(loan.getLoanId(), loan);
        return loan;
    }






}
