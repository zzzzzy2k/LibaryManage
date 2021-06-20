package com.zzzzzy.dao;


import com.zzzzzy.entity.Book;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface BookMapper {

    public int add(Book books);

    public int deleteById(@Param("bookId") int id);

    public int update(Book books);

    public List<Book> allBooks();

    // public List<Book> selectPage();

    public Book queryBookById(@Param("bookId") int id);

    public List<Book> queryBookByName(@Param("bookName") String name);
}
