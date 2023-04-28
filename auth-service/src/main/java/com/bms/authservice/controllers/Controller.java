package com.bms.authservice.controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {
    @PostMapping("/auth/validate")
    public boolean validateToken(){
        // validate the token
        return true;
    }
}
