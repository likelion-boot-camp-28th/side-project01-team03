package com.devin.library.loan.model.repository;

import com.devin.library.loan.model.dto.LoanDto;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/* 데이터를 저장하고 꺼내는 일은 Repository에서 */
public class LoanRepository {

    // 넣은 순서대로 나와야 하므로 LinkedHashMap 사용
    private final Map<Long, LoanDto> loans = new LinkedHashMap<>();
    private long nextId = 1;    // 다음 대출 ID

    /**
     * 대출 기록 저장 (새 대출 ID 자동 부여)
     *
     * @param loan 저장할 대출 정보
     * @return ID가 부여된 대출 정보
     */
    public LoanDto save(LoanDto loan) {

        loan.setLoanId(nextId);
        nextId++;
        loans.put(loan.getLoanId(), loan);
        return loan;
    }

    /**
     * 대출 ID로 대출 정보를 가져오는 메서드
     *
     * @param loanId 대출 ID
     * @return 대출 정보, 없으면 null 반환
     */
    public LoanDto findById(long loanId) {
        return loans.get(loanId);
    }

    /**
     * 모든 대출 정보를 불러오는 메서드
     *
     * @return 전체 대출 목록 (등록 순서)
     */
    public List<LoanDto> findAll() {
        return new ArrayList<LoanDto>(loans.values());  // 대출 정보를 얕은 복사해서 새 List 생성
    }

    /**
     * 특정 회원의 대출 기록만 골라서 리스트로 돌려주는 메서드
     *
     * @param memberId 대출자 ID
     * @return 특정 회원의 대출 기록
     */
    public List<LoanDto> findByMemberId(String memberId) {

        List<LoanDto> loanDtos = new ArrayList<>();

        for (LoanDto loan : loans.values()) {
            if (memberId.equals(loan.getMemberId())) {
                loanDtos.add(loan);
            }
        }
        return loanDtos;
    }

    /**
     * 특정 책의 현재 대출 기록(반납 안 된 것)을 찾는 메서드
     *
     * @param bookId 책 ID
     * @return 대출 중인 기록, 대출 중이 아니면 null
     */
    public LoanDto findActiveByBookId(int bookId) {

        for (LoanDto loan : loans.values()) {
            if (loan.getBookId() == bookId && !loan.isReturned()) {
                return loan;
            }
        }
        return null;
    }


}
