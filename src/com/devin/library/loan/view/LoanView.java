package com.devin.library.loan.view;

import com.devin.library.book.model.service.BookService;
import com.devin.library.common.InputUtil;
import com.devin.library.loan.controller.LoanController;

public class LoanView {

    private final LoanController loanController;

    public LoanView(BookService bookService) {
        this.loanController = new LoanController(bookService);
    }

    public void show(String loginMemberId) {

        /* 로그인 체크 */
        if(loginMemberId == null) {
            System.out.println("대출은 로그인 후 가능합니다");
            return;
        }

        while (true) {
            String menu = """
                    ===== 대출 관리 =====
                    1. 도서 대출
                    2. 도서 반납
                    3. 전체 대출 목록
                    4. 내 대출 내역
                    5. 연체 목록
                    0. 뒤로가기
                    ====================
                    """;
            System.out.print(menu);
            int menuInput = InputUtil.readInt("메뉴 선택 : ");

            switch (menuInput) {
                case 1: // 도서 대출
                    int bookId = InputUtil.readInt("대출할 도서 번호 : ");
                    loanController.loanBook(loginMemberId, bookId);
                    break;
                case 2: // 도서 반납
                    if(!loanController.showMyActiveLoans(loginMemberId)) {
                        break;
                    }
                    long loanId = InputUtil.readLong("반납할 대출 ID : ");
                    loanController.showReturnBook(loginMemberId, loanId);
                    break;
                case 3: // 전체 대출 목록
                    loanController.showAllLoans();
                    break;
                case 4: // 내 대출 내역
                    loanController.showLoansByMember(loginMemberId);
                    break;
                case 5: // 연체 목록
                    loanController.showOverdueLoans();
                    break;
                case 0: // 뒤로가기
                    return;
                default:
                    System.out.println("잘못된 메뉴입니다");
                    break;
            }
        }
    }

}
