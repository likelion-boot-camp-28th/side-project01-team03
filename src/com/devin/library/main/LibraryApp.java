package com.devin.library.main;

import com.devin.library.book.model.service.BookService;
import com.devin.library.book.model.service.BookServiceImpl;
import com.devin.library.book.view.View;
import com.devin.library.common.InputUtil;
import com.devin.library.common.LibraryException;
import com.devin.library.loan.view.LoanView;
import com.devin.library.member.controller.MemberController;
import com.devin.library.member.model.repository.MemberRepository;
import com.devin.library.member.model.service.MemberService;
import com.devin.library.member.model.service.MemberServiceImpl;
import com.devin.library.member.view.MemberView;

import java.util.NoSuchElementException;


public class LibraryApp {

    // 도서 저장소는 하나만 생성해서 도서 관리/대출 관리가 함께 사용
    private final BookService bookService = new BookServiceImpl();
    private final View bookView = new View(bookService);
    private final MemberRepository memberRepository = new MemberRepository();
    private final MemberService memberService = new MemberServiceImpl(memberRepository);
    private final MemberView memberView = new MemberView();
    private final MemberController memberController = new MemberController(memberService, memberView);
    private final LoanView loanView = new LoanView(bookService);
    private String loginMemberId;

    public void run() {

        while (true) {
            try {
                
                System.out.println();
                String menuText = """
                        ==== 도서 대출 프로그램 ====
                        1. 회원관리
                        2. 도서관리
                        3. 대출 관리
                        0. 종료
                        """;
                System.out.print(menuText);
                int menu = InputUtil.readInt("메뉴 선택 : ");

                switch (menu) {
                    case 1 -> {
                        String result = memberController.run();   // 일단 임시 변수에 받기
                        if (result != null) {                     // 로그인에 성공해서 ID가 돌아왔을 때만
                            loginMemberId = result;               // 필드에 저장
                        }
                    }
                    case 2 -> bookView.show();
                    case 3 -> loanView.show(loginMemberId);
                    case 0 -> {
                        System.out.println("프로그램을 종료합니다.");
                        return;
                    }
                    default -> System.out.println("잘못된 메뉴입니다.");
                }

            } catch (LibraryException e) {
                System.out.println(e.getMessage());
            } catch (NoSuchElementException e) {
                System.out.println("입력이 종료되어 프로그램을 끝냅니다.");
                return;
            } catch (RuntimeException e) {
                System.out.println("알 수 없는 오류가 발생했습니다: " + e.getMessage());
            }
        }
    }
}


