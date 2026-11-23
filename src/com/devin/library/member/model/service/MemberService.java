package com.devin.library.member.model.service;

import com.devin.library.common.LibraryException;
import com.devin.library.member.model.dto.MemberDto;

public interface MemberService {
    boolean signup(MemberDto member) throws LibraryException;
    String login(String id, String pw) throws LibraryException;
}
