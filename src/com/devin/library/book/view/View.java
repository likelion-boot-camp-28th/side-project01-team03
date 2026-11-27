package com.devin.library.book.view;

import com.devin.library.book.controller.BookController;
import com.devin.library.book.model.service.BookService;
import com.devin.library.common.InputUtil;

public class View {

    private final BookController bookController;

    public View(BookService bookService) {
        this.bookController = new BookController(bookService);
    }

    public void show() {
        while (true) {
            System.out.println();
            String menuText = """
                \u001B[36m============== 도서 관리 ===============\u001B[0m
                
                1. 도서 등록
                2. 도서 목록
                3. 도서 검색 (제목/저자)
                4. 도서 삭제
                0. 뒤로 가기
                """;
            System.out.println(menuText);
            int menu = InputUtil.readInt("메뉴 선택 : ");

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
                    String password = InputUtil.readNonBlank("비밀번호를 입력해 주세요 :");
                    if(!bookController.checkPassword(password)){
                        System.out.println("비밀번호가 틀렸습니다");
                        break;
                    }
                    bookController.findAll();
                    System.out.println("0. 뒤로 가기");
                    int id = InputUtil.readInt("삭제할 도서 번호 :");
                    if(id == 0){
                        break;
                    }
                    bookController.delete(id);
                    break;
                case 0 :
                        return;
                default : System.out.println("잘못된 메뉴입니다.");
            }

        }
    }
}
