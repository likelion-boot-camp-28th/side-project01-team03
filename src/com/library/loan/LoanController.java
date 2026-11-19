package com.library.loan;

import com.library.common.InputUtil;

/** [대출 도메인] 콘솔 메뉴 */
public class LoanController {

    private final LoanService loanService;

    public LoanController(LoanService loanService) {
        this.loanService = loanService;
    }

    public void run() {
        while (true) {
            System.out.println();
            System.out.println("--- 대출 관리 ---");
            System.out.println("1. 도서 대출");
            System.out.println("2. 도서 반납");
            System.out.println("3. 대출 내역");
            System.out.println("0. 뒤로");
            int menu = InputUtil.readInt("선택 > ");

            switch (menu) {
                case 1, 2 -> System.out.println("준비 중인 기능입니다."); // TODO
                case 3 -> list();
                case 0 -> { return; }
                default -> System.out.println("잘못된 메뉴입니다.");
            }
        }
    }

    private void list() {
        var loans = loanService.findAll();
        if (loans.isEmpty()) {
            System.out.println("대출 내역이 없습니다.");
            return;
        }
        loans.forEach(System.out::println);
    }
}
