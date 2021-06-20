package com.zzzzzy.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zzzzzy.entity.Book;
import com.zzzzzy.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/book")
public class BookController {

    @Autowired
    public BookService bookService;

    @RequestMapping({"", "/list"})
    public String list(Model model, @RequestParam(defaultValue = "1", value = "pageNum") Integer pageNum, @RequestParam(defaultValue = "") String name) {

        PageHelper.startPage(pageNum, 5);
        List<Book> books = bookService.queryBookByName(name);
        PageInfo<Book> pageInfo = new PageInfo<>(books);

        System.out.println(pageInfo.getList().toString());

        model.addAttribute("pageInfo", pageInfo);

        return "book/list";
    }

    @RequestMapping("/toAdd")
    public String toAdd() {
        return "book/add";
    }

    @RequestMapping("/add")
    public String add(Book book) {
        System.out.println(bookService.add(book));
        return "redirect:/book/list";
    }

    @RequestMapping("/delete/{id}")
    public String deleteById(@PathVariable("id") int id) {
        bookService.deleteById(id);
        return "redirect:/book/list";
    }

    @RequestMapping("/toUpdate")
    public String toUpdate(@RequestParam(required = true) Integer id, Model model) {
        Book book = bookService.queryBookById(id);
        System.out.println(book.toString());
        model.addAttribute("updateBook", book);
        return "book/update";
    }

    @RequestMapping("/update")
    public String update(Book book) {
        bookService.update(book);
        return "redirect:/book/list";
    }

    @RequestMapping("/queryBookById/{id}")
    @ResponseBody
    public Book queryBookById(@PathVariable int id) {
        System.out.println(bookService.queryBookById(id));
        return bookService.queryBookById(id);
    }

}
