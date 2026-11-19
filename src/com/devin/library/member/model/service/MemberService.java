package com.devin.library.member.model.service;

import com.devin.library.member.model.Member;

public interface MemberService {

    boolean signup(Member member);
    Member login(String id, String pw);

}
