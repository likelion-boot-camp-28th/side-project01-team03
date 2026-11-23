package com.devin.library.member.model.service;

import com.devin.library.common.LibraryException;
import com.devin.library.member.model.dto.MemberDto;

public interface MemberService {
    void duplicateCheck(String memberId) throws LibraryException;

    void signup(MemberDto memberInfo) throws LibraryException;

    String login(String id, String pw) throws LibraryException;
}
