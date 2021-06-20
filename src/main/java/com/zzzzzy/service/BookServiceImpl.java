package com.zzzzzy.service;

import com.zzzzzy.dao.BookMapper;
import com.zzzzzy.entity.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService{

    @Autowired
    BookMapper bookMapper;

    @Override
    public int add(Book books) {
        return bookMapper.add(books);
    }

    @Override
    public int deleteById(int id) {
        return bookMapper.deleteById(id);
    }

    @Override
    public int update(Book books) {
        return bookMapper.update(books);
    }

    @Override
    public Book queryBookById(int id) {
        return bookMapper.queryBookById(id);
    }

    @Override
    public List<Book> allBooks() {
        return bookMapper.allBooks();
    }

    @Override
    public List<Book> queryBookByName(String name) {
        return bookMapper.queryBookByName(name);
    }
}
