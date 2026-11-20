package com.devin.library.book.view;

import com.devin.library.book.controller.BookController;
import com.devin.library.common.InputUtil;

public class view {

    private BookController bookController = new BookController();
    public void show() {
        while (true) {
            String menuText = """
                ============도서관리===========
                1. 도서 등록
                2. 도서 목록
                3. 도서 검색 (제목/저자)
                4. 도서 삭제
                0. 뒤로 가기
                """;
            System.out.println(menuText);
            int menu = InputUtil.readInt("메뉴 선택");

            switch (menu){
                case 1 :
                    String title = InputUtil.readLine("제목 :");
                    String author = InputUtil.readLine("저자 :");
                    bookController.registerBook(title,author);
                    break;
                case 2:
                    bookController.findAll();
                    break;
                case 3 :
                    String keyword = InputUtil.readLine("검색어 :");
                    bookController.search(keyword);
                    break;
                case 4 :
                    bookController.findAll();
                    int id = InputUtil.readInt("삭제할 도서 번호");
            }

        }


    }


}
