package com.devin.library.member.view;

import com.devin.library.common.InputUtil;
import com.devin.library.member.model.dto.MemberDto;

public class MemberView {

    public String memberMenu() {
        System.out.println();
        String menu = """
                \u001B[36m============== 회원 관리 ===============\u001B[0m
                
                1. 회원 가입
                2. 로그인
                0. 뒤로 가기
                """;
        System.out.println(menu);
        return InputUtil.readLine("메뉴 선택 : ");
    }

    // 아이디 입력 후 바로 중복 확인하기 위해 비밀번호와 분리함
    public String signupId() {
        System.out.println();
        System.out.println("--- 회원가입 ---");
        return InputUtil.readNonBlank("아이디 : ");
    }

    public String signupPw() {
        return InputUtil.readNonBlank("비밀번호 : ");
    }

    public MemberDto loginPage() {
        System.out.println();
        System.out.println("--- 로그인 ---");
        String id = InputUtil.readNonBlank("아이디 : ");
        String pw = InputUtil.readNonBlank("비밀번호 : ");
        return new MemberDto(id, pw);
    }

    public void memberSuccess(String sentence) {
        System.out.println(sentence);
    }

    public void memberFailure(String sentence) {
        System.out.println(sentence);
    }

}
