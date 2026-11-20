package com.devin.library.member.model.repository;

import com.devin.library.member.model.dto.MemberDto;
import java.util.HashMap;
import java.util.Map;

// 저장소
// 맵에 저장, id 불러오기(로그인 확인용 아이디로 값 가져오기), id 있는지 확인(회원가입 중복확인용), (미정)삭제, 회원목록보기, 회원정보수정
public class MemberRepository {

    Map<String, MemberDto> members = new HashMap<>();

    // 저장, 반환값없음 메서드

    // id 불러오기 dto로 가져오고 옮기기 메서드

    // id 있는지 확인 메서드


}
