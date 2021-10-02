package com.example.shoppingapplication.controllers;

import com.example.shoppingapplication.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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

    @GetMapping("/")
    public String appPage(Model model) {
        model.addAttribute("title", "App page");
        return "app";
    }
    @GetMapping("/login")
    public String getLoginPage() {
        return "login";
    }

    @GetMapping("/success")
    public String getSuccessPage() {
        return "success";
    }

    @GetMapping("/logout")
    public String getLogoutPage() {
        return "logout";
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
}