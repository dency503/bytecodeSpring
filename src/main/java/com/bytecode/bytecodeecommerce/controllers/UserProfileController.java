package com.bytecode.bytecodeecommerce.controllers;

import org.springframework.security.core.annotation.AuthenticationPrincipal;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserProfileController {

    @GetMapping("/profile")
    public String userProfile() {
       return "user";
    }
}
