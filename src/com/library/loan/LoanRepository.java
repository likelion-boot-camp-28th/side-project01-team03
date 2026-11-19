package com.library.loan;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/** 메모리 저장소 */
public class LoanRepository {

    private final Map<Long, Loan> store = new LinkedHashMap<>();
    private long sequence = 0L;

    public Loan save(Loan loan) {
        if (loan.getId() == null) {
            loan.setId(++sequence);
        }
        store.put(loan.getId(), loan);
        return loan;
    }

    public Optional<Loan> findById(Long id) {
        return Optional.ofNullable(store.get(id));
    }

    public List<Loan> findAll() {
        return new ArrayList<>(store.values());
    }
}
