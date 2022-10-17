package com.ciclo3.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@Controller
public class UserController {
    @RequestMapping("/box")
    public String box(){
        return "box";
    }
    @RequestMapping("/client")
    public String client(){
        return "client";
    }
    @RequestMapping("/category")
    public String category(){
        return "category";
    }
    @RequestMapping("/message")
    public String message(){
        return "message";
    }
    @RequestMapping("/reservation")
    public String reservation(){
        return "reservation";
    }
    @RequestMapping("/admin")
    public String admin(){
        return "admin";
    }
}
