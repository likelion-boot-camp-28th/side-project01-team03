package com.devin.library.member.view;

import com.devin.library.common.InputUtil;
import com.devin.library.member.model.dto.MemberDto;

/**
 * 화면 입출력
 */
public class MemberView {

    /// 회원관리 메뉴
    public String memberMenu() {
        String menu = """
                
                ==== 회원 관리 ====
                1. 회원 가입
                2. 로그인
                0. 뒤로 가기
                """;
        System.out.println(menu);
        return InputUtil.readLine("메뉴 선택 : "); // memberMenu에 값 들어감
        /*
         * 1-> signupPage로 연결
         * 2-> loginPage로 연결
         * 0-> LibaryApp run() 메뉴로 돌아가기
         * */
    }

    /// 회원가입 -> controller -> service -> repository -> controller
    /// 아이디 중복확인용으로 분리
    public String signupId() {
        System.out.println();
        System.out.println("--- 회원가입 ---");
        // 아이디 입력
        return InputUtil.readNonBlank("아이디 : "); // 메서드로 전달
    }

    /// 비번
    public String signupPw() {
        // 비번 입력
        return InputUtil.readNonBlank("비밀번호 : "); // 메서드로 전달
    }

    /// 로그인 -> controller -> service -> -> repository -> controller
    public MemberDto loginPage() {
        System.out.println();
        System.out.println("--- 로그인 ---");
        String id = InputUtil.readNonBlank("아이디 : ");     // 아이디 입력
        String pw = InputUtil.readNonBlank("비밀번호 : ");  // 비번 입력
        return new MemberDto(id, pw);   // dto에 담아서 loginPage를 씀
    }

    /// controller에서 사용할 성공 출력문
    public void memberSuccess(String sentence) {
        System.out.println(sentence);
    }

    /// controller에서 사용할 실패 출력문
    public void memberFailure(String sentence) {
        System.out.println(sentence);
    }

}
