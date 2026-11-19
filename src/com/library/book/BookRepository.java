package com.library.book;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/** 메모리 저장소 */
public class BookRepository {

    private final Map<Long, Book> store = new LinkedHashMap<>();
    private long sequence = 0L;

    public Book save(Book book) {
        if (book.getId() == null) {
            book.setId(++sequence);
        }
        store.put(book.getId(), book);
        return book;
    }

    public Optional<Book> findById(Long id) {
        return Optional.ofNullable(store.get(id));
    }

    public List<Book> findAll() {
        return new ArrayList<>(store.values());
    }

    public void deleteById(Long id) {
        store.remove(id);
    }
}
