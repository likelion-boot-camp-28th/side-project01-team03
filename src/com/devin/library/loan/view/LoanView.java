package com.devin.library.loan.view;

import com.devin.library.common.InputUtil;
import com.devin.library.loan.controller.LoanController;

public class LoanView {

    private final LoanController loanController = new LoanController();

    public void show() {
        while (true) {
            String menu = """
                    ===== 대출 관리 =====
                    1. 도서 대출
                    2. 도서 반납
                    3. 전체 대출 목록
                    4. 회원별 대출 내역
                    5. 연체 목록
                    0. 뒤로가기
                    ====================
                    """;
            System.out.print(menu);
            int menuInput = InputUtil.readInt("메뉴 선택 : ");

            switch (menuInput) {
                case 1:
                    System.out.println("도서 대출 준비 중");
                    break;
                case 2:
                    System.out.println("도서 반납 준비 중");
                    break;
                case 3:
                    System.out.println("전체 대출 목록 준비 중");
                    break;
                case 4:
                    System.out.println("회원별 대출 내열 준비 중");
                    break;
                case 5:
                    System.out.println("연체 목록 준비 중");
                    break;
                case 0:
                    return;
                default:
                    System.out.println("잘못된 메뉴입니다");
                    break;
            }
        }
    }

}
