package com.devin.library.member.model.service;

import com.devin.library.member.view.Member;
import com.devin.library.member.view.MemberRepository;

import java.util.List;
import java.util.Optional;

/**
 * [회원 도메인] 비즈니스 로직.
 * 대출 도메인(LoanService)이 findById를 사용하므로 시그니처 변경 시 공유해주세요.
 */
public class MemberService {

    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public Member join(String name, String phone) {
        return memberRepository.save(new Member(name, phone));
    }

    public List<Member> findAll() {
        return memberRepository.findAll();
    }

    public Optional<Member> findById(Long id) {
        return memberRepository.findById(id);
    }

    // TODO: 중복 회원 검증, 수정, 탈퇴 등
}
