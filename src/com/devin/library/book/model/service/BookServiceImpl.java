package com.devin.library.book.model.service;

import com.devin.library.book.model.dto.Bookdto;

import java.awt.print.Book;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class BookServiceImpl implements BookService {
    public String FilePath = "src/com/devin/library/data/books.txt";
    private List<Bookdto> books = new ArrayList<>();
    private int nextId = 1;

    public BookServiceImpl() {
        loadFile();
        for (Bookdto b : books) {
            if (b.getId() >= nextId) {
                nextId = b.getId();
            }
            nextId = b.getId() + 1;
        }
    }

    @Override // 1. 책 전체 조회
    public List<Bookdto> findAll() {
        return books;
    }

    @Override // 2. 책 등록
    public Bookdto register(String title, String author) {
        Bookdto book = new Bookdto(nextId, title, author, false);
        nextId++;
        books.add(book);        // 중복id가 들어갈수있으니 나중에 조치
        return book;
    }

    @Override // 3. id로 검색하기
    public Bookdto findById(int id) {
        for (Bookdto b : books) {
            if (b.getId() == id) {
                return b;
            }
        }
        return null;
    }

    @Override // 4. 특정 키워드로 검색하기
    public List<Bookdto> search(String keyword) {
        List<Bookdto> result = new ArrayList<>();
        for (Bookdto b : books) {
            if (b.getTilte().contains(keyword) || b.getAuthor().contains(keyword)) {
                result.add(b);
            }
        }
        return result;
    }

    @Override // 5. 책 삭제하기
    public String delete(int id) {

        Bookdto book = findById(id);
        if(book == null){
            return "없는 도서 번호입니다";
        }
        if(book.isBorrowed()){
            return "대출중입니다";
        }
        books.remove(book);
        return id + "번호의 책이 삭제되었습니다";
    }

    private void loadFile() {
        BufferedReader br = null;
        String line = null;
        try {
            br = new BufferedReader(new FileReader(FilePath));
            while ((line = br.readLine()) != null) {
                //System.out.println(line);

                String[] data = line.split(",");
                int id = Integer.parseInt(data[0]);
                String title = data[1];
                String author = data[2];
                boolean borrowed = Boolean.parseBoolean(data[3]);

                books.add(new Bookdto(id, title, author, borrowed));
            }
        } catch (FileNotFoundException e) {
            System.out.println("파일이 없습니다.");
        } catch (IOException e) {
            System.out.println("파일을 읽지 못했습니다");
        } finally {
            try {
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}