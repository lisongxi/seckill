package com.lsx.seckill.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 测试
 *
 * @author LiSongXi
 * @since 1.0.0
 * */

@Controller
@RequestMapping("/demo")
public class DemoController {

    @RequestMapping("/hello")
    public String hello(Model model){
        model.addAttribute("name","lsx");
        return "hello";
    }
}
