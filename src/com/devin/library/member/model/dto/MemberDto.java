package com.devin.library.member.model.dto;

public class MemberDto {

    private String id;
    private String pw;

    public MemberDto() {
    }

    public MemberDto(String id, String pw) {
        this.id = id;
        this.pw = pw;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPw() {
        return pw;
    }

    public void setPw(String pw) {
        this.pw = pw;
    }

    @Override
    public String toString() {
        return "아이디 : " + id;
    }

}


/*
* id 기준으로 회원 판단 findById
*
* 회원가입
* View 아이디비번 입력 -> return
* Controller dto에 넣기
* Service repository에 있는지 중복확인
* 있음 -> controller에서 view실패출력메서드사용
* 없음 -> Repository Map에 넣기 put -> view성공출력메서드
* Controller 성공 -> view성공출력메서드, 실패 -> view실패출력메서드
*
* 로그인
 * View 아이디비번 입력 -> return
 * Controller service에서 아이디비번 확인
 * Service repository에서 꺼내기 -> 꺼낸 후 판단(null(id 다름), 비번 다름, 전부 동일)
 * Repository Map에서 불러옴get
 * Controller 성공 -> 메인 메뉴 이동, 실패 -> view실패출력메서드
 *
 * 부르는 순서
 * Main -> Controller -> View -> Service -> Repository -> Map
* */