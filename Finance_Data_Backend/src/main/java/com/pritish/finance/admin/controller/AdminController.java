package com.pritish.finance.admin.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @GetMapping
    public String admin() {
        return "Admin Access Only";
    }
}