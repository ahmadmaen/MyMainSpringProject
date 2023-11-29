package com.luv2code.springboot.thymeleafdemo.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomePageController {

    @GetMapping("/homepage")
    public String ShowHomePage()  {
        return "HomePage/homepage";
    }

    @GetMapping("/leaders")
    public String ShowLeadersPage()  {
        return "HomePage/leaders";
    }

    @GetMapping("/systems")
    public String ShowSystemPage()  {
        return "HomePage/systems";
    }


}
