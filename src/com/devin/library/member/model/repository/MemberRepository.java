package com.devin.library.member.model.repository;

import com.devin.library.member.model.dto.MemberDto;

import java.util.HashMap;
import java.util.Map;

public class MemberRepository {

    // key : 회원 아이디
    Map<String, MemberDto> members = new HashMap<>();

    public boolean existById(String memberId) {
        return members.containsKey(memberId);
    }

    /**
     * 아이디 중복 시 내용을 덮어쓰므로, 호출 전 existById 중복 확인 필수
     */
    public void saveMember(MemberDto memberInfo) {
        members.put(memberInfo.getId(), memberInfo);
    }

    /**
     * @return 회원정보, 없으면 null
     */
    public MemberDto findById(String id) {
        return members.get(id);
    }
}