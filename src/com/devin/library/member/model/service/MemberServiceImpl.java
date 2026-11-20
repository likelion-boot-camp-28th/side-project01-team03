package com.devin.library.member.model.service;

/*
 * [회원 도메인] 비즈니스 로직.
 * 대출 도메인(LoanService)이 findById를 사용하므로 시그니처 변경 시 공유해주세요.
 */

import com.devin.library.member.model.dto.MemberDto;
import com.devin.library.member.model.repository.MemberRepository;

/** 판단: 중복 아이디(성공/실패), 아이디비번 동일한지 확인() */
// 중복아이디 찾기 -> repository에 아이디가 있는지로 판단
// (있음 -> Controller에서 try-catch  -> Service에서 중복검사 >
// 있음 -> throw Exception(무슨익셉션으로?) -> catch view출력문("중복아이디 있어요") Fail
// 없음 -> repository 저장메서드 사용 -> try 마지막 View출력문("가입되었습니다.) Success
// 로그인 판정 -> Repository-Map에서 id(키값)으로 아이디비번 가져오기 -> 꺼낸 후 판단(null(id 다름), 비번 다름, 전부 동일)
public class MemberServiceImpl implements MemberService {

    // Repository 연결용
    private final MemberRepository memberRepository;

    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public boolean signup(MemberDto member) {
        return false;
    }

    @Override
    public MemberDto login(String id, String pw) {
        return null;
    }

}