package com.devin.library.member.model.repository;

import com.devin.library.member.model.dto.MemberDto;

import java.util.HashMap;
import java.util.Map;

/// 회원 정보 저장소 2/3
// 맵에 저장, id 불러오기(로그인 확인용 아이디로 값 가져오기), id 있는지 확인(회원가입 중복확인용), (미정)삭제, 회원목록보기, 회원정보수정
public class MemberRepository {

    Map<String, MemberDto> members = new HashMap<>();

    // 동일한 id 있는지 확인 메서드(회원가입)
    public boolean existById(String memberId) {
        boolean result = members.keySet().contains(memberId);
        // members.containsKey(memberDto.getId());   // 한번에 물어보는 코드
        // contains가 알아서 순환해서 반복문 쓸 필요x
        return result;      // 결과값이 true(있음)인지 false(없음)인지 돌려준다.
    }

    // 저장, 반환값없음 메서드, service에서 저장 판정 시 사용(회원가입)
    public void saveMember(MemberDto memberInfo) {
        members.put(memberInfo.getId(), memberInfo);  // 키값:id, 값:id,pw
    }

    // id로 비번 가져오기 메서드(로그인)
    public MemberDto findById(String id) {      // 아이디만 있으면 돼서 String 쓰고 MemberDto로 돌려준다, 받는것도 String
        return members.get(id);                 // 전체값(id, pw) 전달
    }

}