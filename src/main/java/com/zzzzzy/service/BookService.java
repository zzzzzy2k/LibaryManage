package com.zzzzzy.service;

import com.zzzzzy.entity.Book;

import java.util.List;

public interface BookService {

    public int add(Book books);

    public int deleteById( int id);

    public int update(Book books);

    public Book queryBookById(int id);

    public List<Book> allBooks();

    public List<Book> queryBookByName(String name);
}
