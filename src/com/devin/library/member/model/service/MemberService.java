package com.devin.library.member.model.service;

import com.devin.library.member.model.dto.MemberDto;

public interface MemberService {
    boolean signup(MemberDto member) throws Exception;
    MemberDto login(String id, String pw);
}
