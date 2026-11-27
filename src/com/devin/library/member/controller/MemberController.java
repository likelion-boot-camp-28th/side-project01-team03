package com.devin.library.member.controller;

import com.devin.library.common.LibraryException;
import com.devin.library.member.model.dto.MemberDto;
import com.devin.library.member.model.service.MemberService;
import com.devin.library.member.view.MemberView;

public class MemberController {

    private final MemberService memberService;
    private final MemberView memberView;
    private String loginId = null;

    public MemberController(MemberService memberService, MemberView memberView) {
        this.memberService = memberService;
        this.memberView = memberView;
    }

    /**
     * @return 회원 아이디, 비로그인 시 null
     */
    public String show() {
        while (true) {
            String menu = memberView.memberMenu();
            switch (menu) {
                case "1":
                    signup();
                    break;
                case "2": {
                    if (loginId != null) {
                        memberView.memberFailure("이미 로그인 되어있습니다.");
                        break;
                    }
                    String temp = login();
                    if (temp != null) {
                        loginId = temp;
                        return loginId;
                    }
                    break;
                }
                case "0":
                    return loginId;
                default:
                    memberView.memberFailure("잘못된 메뉴입니다.");
            }
        }
    }

    public void signup() {
        try {
            String signId = memberView.signupId();
            memberService.duplicateCheck(signId);
            String signPw = memberView.signupPw();
            MemberDto memberInfo = new MemberDto(signId, signPw);
            memberService.signup(memberInfo);
            memberView.memberSuccess("회원가입 성공!!");

        } catch (LibraryException e) {
            memberView.memberFailure(e.getMessage());
        }
    }

    /**
     * @return 로그인 아이디, 실패 시 null
     */
    public String login() {
        try {
            MemberDto memberInfo = memberView.loginPage();
            String loginMember = memberService.login(memberInfo.getId(), memberInfo.getPw());
            memberView.memberSuccess("로그인 성공!!");
            return loginMember;
        } catch (LibraryException e) {
            memberView.memberFailure(e.getMessage());
            return null;
        }
    }
}