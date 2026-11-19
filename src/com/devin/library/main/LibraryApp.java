package com.devin.library.main;

import com.devin.library.common.InputUtil;


public class LibraryApp {


    public LibraryApp() {

    }

    public void run() {

        while (true) {
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
                case 1 -> System.out.println("아직 준비중입니다.");
                case 2 -> System.out.println("아직 준비중입니다.");
                case 3 -> System.out.println("아직 준비중입니다.");
                case 0 -> {
                    System.out.println("프로그램을 종료합니다.");
                    return;
                }
                default -> System.out.println("잘못된 메뉴입니다.");
            }
        }
    }
}

