package com.zzzzzy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@Repository
public class MyController {

    @RequestMapping("/hello")
    public String test(){
        return "hello";
    }

}
