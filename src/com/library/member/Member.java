package com.library.member;

public class Member {

    private Long id;
    private String name;
    private String phone;

    public Member(String name, String phone) {
        this.name = name;
        this.phone = phone;
    }

    public Long getId() { return id; }
    void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public String getPhone() { return phone; }

    @Override
    public String toString() {
        return String.format("[%d] %s (%s)", id, name, phone);
    }
}
