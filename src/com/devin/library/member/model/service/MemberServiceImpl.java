package com.devin.library.member.model.service;

import com.devin.library.common.LibraryException;
import com.devin.library.member.model.dto.MemberDto;
import com.devin.library.member.model.repository.MemberRepository;

public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;

    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    /**
     * @throws LibraryException 아이디 중복일 때
     */
    @Override
    public void duplicateCheck(String memberId) throws LibraryException {
        if (memberRepository.existById(memberId)) {
            throw new LibraryException("이미 존재하는 아이디입니다. 다시 시도해주세요.");
        }
    }

    /**
     * @throws LibraryException 아이디 중복일 때
     */
    @Override
    public void signup(MemberDto memberInfo) throws LibraryException {
        if (memberRepository.existById(memberInfo.getId())) {
            throw new LibraryException("이미 존재하는 아이디입니다. 다시 시도해주세요.");
        }
        memberRepository.saveMember(memberInfo);
    }

    /**
     * @return 로그인 된 아이디(보안상 아이디만 반환)
     * @throws LibraryException 아이디, 비번 다를 때
     */
    @Override
    public String login(String id, String pw) throws LibraryException {
        MemberDto memberInfo = memberRepository.findById(id);
        if (memberInfo == null || !pw.equals(memberInfo.getPw())) {
            throw new LibraryException("아이디 또는 비밀번호가 다릅니다. 다시 시도해주세요.");
        }
        return memberInfo.getId();
    }

}