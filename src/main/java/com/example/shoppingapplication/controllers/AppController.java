package com.example.shoppingapplication.controllers;

import com.example.shoppingapplication.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@Controller
@RequestMapping("/auth")
public class AppController {


    private final UserService userService;

    public AppController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/sing-up")
    public String getSingUpPage() {
        return "sing_up";
    }

    @PostMapping("/sing-up")
    public String singUp(@RequestBody Map<String, String> body) {
        userService.singUp(body.get("username"), body.get("password"));
        return "/login";
    }
    @GetMapping("/login")
    public String getLoginPage() {
        return "login";
    }

    @GetMapping("/success")
    public String getSuccessPage() {
        return "succes";
    }

    @GetMapping("/logout")
    public String getLogoutPage() {
        return "logout";
    }
}