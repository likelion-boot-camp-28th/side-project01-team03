package com.devin.library.member.view;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/** 메모리 저장소 */
public class MemberRepository {

    private final Map<Long, Member> store = new LinkedHashMap<>();
    private long sequence = 0L;

    public Member save(Member member) {
        if (member.getId() == null) {
            member.setId(++sequence);
        }
        store.put(member.getId(), member);
        return member;
    }

    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id));
    }

    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }

    public void deleteById(Long id) {
        store.remove(id);
    }
}
