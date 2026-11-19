package com.library.book;

import com.library.common.InputUtil;

/** [책 도메인] 콘솔 메뉴 */
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    public void run() {
        while (true) {
            System.out.println();
            System.out.println("--- 도서 관리 ---");
            System.out.println("1. 도서 등록");
            System.out.println("2. 도서 목록");
            System.out.println("0. 뒤로");
            int menu = InputUtil.readInt("선택 > ");

            switch (menu) {
                case 1 -> register();
                case 2 -> list();
                case 0 -> { return; }
                default -> System.out.println("잘못된 메뉴입니다.");
            }
        }
    }

    private void register() {
        String title = InputUtil.readLine("제목: ");
        String author = InputUtil.readLine("저자: ");
        Book book = bookService.register(title, author);
        System.out.println("등록 완료: " + book);
    }

    private void list() {
        var books = bookService.findAll();
        if (books.isEmpty()) {
            System.out.println("등록된 도서가 없습니다.");
            return;
        }
        books.forEach(System.out::println);
    }
}
