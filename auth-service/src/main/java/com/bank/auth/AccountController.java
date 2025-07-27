package com.bank.auth;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {
    @GetMapping
    public ResponseEntity<String> getAccountInfo() {
        return ResponseEntity.ok("账户信息：这是受保护资源");
    }
}
