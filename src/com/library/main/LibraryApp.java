package com.library.main;

import com.library.book.BookController;
import com.library.book.BookRepository;
import com.library.book.BookService;
import com.library.common.InputUtil;
import com.library.member.MemberController;
import com.library.member.MemberRepository;
import com.library.member.MemberService;

/**
 * [메인 도메인] 객체 조립 + 메인 메뉴 루프.
 */
public class LibraryApp {

    private final BookController bookController;
    private final MemberController memberController;
    private final LoanController loanController;

    public LibraryApp() {
        BookService bookService = new BookService(new BookRepository());
        MemberService memberService = new MemberService(new MemberRepository());
        LoanService loanService = new LoanService(new LoanRepository(), bookService, memberService);

        this.bookController = new BookController(bookService);
        this.memberController = new MemberController(memberService);
        this.loanController = new LoanController(loanService);
    }

    public void run() {
        System.out.println("===== 도서 대출 프로그램 =====");
        while (true) {
            System.out.println();
            System.out.println("1. 도서 관리");
            System.out.println("2. 회원 관리");
            System.out.println("3. 대출 관리");
            System.out.println("0. 종료");
            int menu = InputUtil.readInt("메뉴 선택 > ");

            switch (menu) {
                case 1 -> bookController.run();
                case 2 -> memberController.run();
                case 3 -> loanController.run();
                case 0 -> {
                    System.out.println("프로그램을 종료합니다.");
                    return;
                }
                default -> System.out.println("잘못된 메뉴입니다.");
            }
        }
    }
}
