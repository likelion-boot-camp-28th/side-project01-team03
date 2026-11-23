package com.devin.library.member.model.service;

/*
 * [회원 도메인] 비즈니스 로직.
 */

import com.devin.library.common.LibraryException;
import com.devin.library.member.model.dto.MemberDto;
import com.devin.library.member.model.repository.MemberRepository;

/**
 * 판단: 중복 아이디(성공/실패), 아이디비번 동일한지 확인()
 */
// 중복아이디 찾기 -> repository에 아이디가 있는지로 판단
// (있음 -> Controller에서 try-catch  -> Service에서 중복검사 >
// 있음 -> throw Exception(무슨익셉션으로?) -> catch view출력문("중복아이디 있어요") Fail
// 없음 -> repository 저장메서드 사용 -> try 마지막 View출력문("가입되었습니다.) Success
// 로그인 판정 -> Repository-Map에서 id(키값)으로 아이디비번 가져오기 -> 꺼낸 후 판단(null(id 다름)+비번 다름, 전부 동일)
public class MemberServiceImpl implements MemberService {

    // Repository 연결용
    private final MemberRepository memberRepository;

    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public void duplicateCheck(String memberId) throws LibraryException {
        if (memberRepository.existById(memberId)) {
            throw new LibraryException("이미 존재하는 아이디입니다. 다시 시도해주세요."); // Controller 전달 +getMessage
        }
    }

    // 저장
    @Override
    public void signup(MemberDto memberInfo) throws LibraryException {
        if (memberRepository.existById(memberInfo.getId())) {
            throw new LibraryException("이미 존재하는 아이디입니다. 다시 시도해주세요."); // Controller 전달 +getMessage
        }
        memberRepository.saveMember(memberInfo);
    }

    //
    @Override
    public String login(String id, String pw) throws LibraryException {
        // id로 레포지토리에서 값(id,pw) 가져오기
        MemberDto memberInfo = memberRepository.findById(id);
        // id, pw null 확인, pw비교 후 다르면 -> 아이디 또는 비번이 다릅니다. 다시 시도해주세요.
        // id는 이미 같으므로 비교할 필요x
        if (memberInfo == null || !pw.equals(memberInfo.getPw())) {
            throw new LibraryException("아이디 또는 비밀번호가 다릅니다. 다시 시도해주세요."); // Controller 전달 +getMessage
        }
        // 동일하면 아이디를 돌려준다.(성공했음을 확인) 비번 노출 막기 위함
        return memberInfo.getId();
    }

}