package com.library.member;

import com.library.common.InputUtil;

/** [회원 도메인] 콘솔 메뉴 */
public class MemberController {

    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    public void run() {
        while (true) {
            System.out.println();
            System.out.println("--- 회원 관리 ---");
            System.out.println("1. 회원 가입");
            System.out.println("2. 회원 목록");
            System.out.println("0. 뒤로");
            int menu = InputUtil.readInt("선택 > ");

            switch (menu) {
                case 1 -> join();
                case 2 -> list();
                case 0 -> { return; }
                default -> System.out.println("잘못된 메뉴입니다.");
            }
        }
    }

    private void join() {
        String name = InputUtil.readLine("이름: ");
        String phone = InputUtil.readLine("전화번호: ");
        Member member = memberService.join(name, phone);
        System.out.println("가입 완료: " + member);
    }

    private void list() {
        var members = memberService.findAll();
        if (members.isEmpty()) {
            System.out.println("등록된 회원이 없습니다.");
            return;
        }
        members.forEach(System.out::println);
    }
}
