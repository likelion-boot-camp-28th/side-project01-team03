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