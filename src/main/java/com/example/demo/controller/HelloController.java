package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HelloController {
    @GetMapping("/hello")
    public String sayHello() {
        return "hello";
    }

//    @GetMapping
//    String getPeople(Model model){
//        model.addAttribute("something", "from something");
//        model.addAttribute("people", Arrays.asList(
////                new Person("John", 28),
////                new Person("Sarah", 22),
////                new Person("Connor", 25)
//        ));
//        return "people";
//    }



}
