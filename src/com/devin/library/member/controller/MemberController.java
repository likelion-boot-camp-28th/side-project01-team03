package com.devin.library.member.controller;

import com.devin.library.member.model.dto.MemberDto;
import com.devin.library.member.model.service.MemberService;
import com.devin.library.member.view.MemberView;

// view 입력값 -> service 전달 -> try/catch로 성공/실패 -> view 출력 전달

// 중복아이디 찾기 -> repository에 아이디가 있는지로 판단한다.(Repository-맵-키값id로 찾는 메서드 사용)
// 있음 -> Controller에서 try-catch  -> Service에서 중복검사 >
// 있음 -> throw Exception(무슨익셉션으로?) -> catch view출력문("중복아이디 있어요") Fail
// 없음 -> repository 저장메서드 사용 -> try 마지막 View출력문("가입되었습니다.) Success

// 로그인 판정 -> Repository-Map에서 id(키값)으로 아이디비번 가져오기 -> 꺼낸 후 판단(null(id 다름), 비번 다름, 전부 동일)

public class MemberController {

    // service, view 연결용
    private MemberService memberService;
    private MemberView memberView;

    public MemberController(MemberService memberService, MemberView memberView) {
        this.memberService = memberService;
        this.memberView = memberView;
    }

    public void run() {
        String menu = memberView.memberMenu(); // 멤버 메뉴 불러오기
        switch (menu) {
            case "1": signup(); break;
            case "2": login(); break;
            case "0": return;
            default: System.out.println("번호만 입력해주세요.");
        }
    }

    public boolean signup() {
        boolean success = false;        // !false에서 success true까지 반복
        while (!success) {
            try {
                MemberDto memberInfo = memberView.signupPage();             // 회원가입페이지 부르고 값 저장
                memberService.signup(memberInfo);                           // 값을 서비스에서 검사
                memberView.memberSuccess("회원가입 성공!!");      // 성공 시 view성공페이지 출력
                success = true;
            } catch (IllegalArgumentException e) {
                memberView.memberFailure(e.getMessage());                   // 실패 시 view 실패페이지 출력
            }
        }
        return true; // 왜 true더라
    }

    public MemberDto login() {
        // login id, pw갖고와서 판정/출력
        return null;
    }

}
