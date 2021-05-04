package cn.wyu.aop.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("aop")
public class AopController {
    @GetMapping("getTest")
    public String aopTest() {
        return "getTest Hello";
    }
    @PostMapping("postTest")
    public String postTest() {
        return "postTest Hello ";
    }

    @RequestMapping("hello")
    public String hello() {
        double b = 1/0;
        return "Hello world";
    }
}
