package com.devin.library.member.model.service;

import com.devin.library.member.model.dto.MemberDto;

public interface MemberService {
    boolean signup(MemberDto member) throws IllegalArgumentException;
    MemberDto login(String id, String pw) throws IllegalArgumentException;
}
