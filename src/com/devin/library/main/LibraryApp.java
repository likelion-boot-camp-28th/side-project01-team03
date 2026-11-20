package com.devin.library.main;

import com.devin.library.book.view.View;
import com.devin.library.common.InputUtil;
import com.devin.library.common.LibraryException;
import com.devin.library.loan.model.service.LoanService;
import com.devin.library.loan.view.LoanView;
import com.devin.library.member.controller.MemberController;
import com.devin.library.member.model.repository.MemberRepository;
import com.devin.library.member.model.service.MemberService;
import com.devin.library.member.model.service.MemberServiceImpl;
import com.devin.library.member.view.MemberView;

import java.util.PrimitiveIterator;


public class LibraryApp {

    private View bookView = new View();
    private MemberRepository memberRepository = new MemberRepository();
    private MemberService memberService = new MemberServiceImpl(memberRepository);
    private MemberView memberView = new MemberView();
    private MemberController memberController = new MemberController(memberService, memberView);
    private LoanView loanView = new LoanView();

    public LibraryApp() {

    }

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
                    case 1 -> memberController.run();
                    case 2 -> bookView.show();
                    case 3 -> loanView.show("Test"); // TODO: 로그인 기능 완성 후 로그인한 회원 ID로 변경
                    case 0 -> {
                        System.out.println("프로그램을 종료합니다.");
                        return;
                    }
                    default -> System.out.println("잘못된 메뉴입니다.");
                }

            } catch (LibraryException e) {
                System.out.println(e.getMessage());
            } catch (RuntimeException e) {
                System.out.println("알 수 없는 오류가 발생했습니다: " + e.getMessage());
            }
        }
    }
}


