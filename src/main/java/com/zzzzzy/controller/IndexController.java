package com.zzzzzy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @ClassName IndexController
 * @Description TODO
 * @Author zzzzy
 * @Date 2021/5/7 9:46
 * @Version 1.0
 **/

@Controller
public class IndexController {

    @RequestMapping({"/", "/index"})
    public String index(){
        return "index";
    }

}
