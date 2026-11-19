package com.devin.library.member.model.service;

/**
 * [회원 도메인] 비즈니스 로직.
 * 대출 도메인(LoanService)이 findById를 사용하므로 시그니처 변경 시 공유해주세요.
 */

import com.devin.library.member.model.Member;

/** 규칙, 글자X, 판단, 처리 */
public class MemberServiceImpl implements MemberService {

    @Override
    public boolean signup(Member member) {
        return false;
    }

    @Override
    public Member login(String id, String pw) {
        return null;
    }

    // TODO: 중복 회원 검증
}