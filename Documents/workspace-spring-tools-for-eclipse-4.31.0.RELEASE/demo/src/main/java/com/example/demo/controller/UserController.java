package com.example.demo.controller;

import com.example.demo.JwtFilter.JwtToken;
import com.example.demo.dto.AuthLogin;
import com.example.demo.dto.AuthRequest;
import com.example.demo.dto.Coin;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
public class UserController {

    private final UserService userService;
    private final JwtToken jwtService;
    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;

    public UserController(UserService userService, JwtToken jwtService, UserRepository userRepository, AuthenticationManager authenticationManager) {
        this.userService = userService;
        this.jwtService = jwtService;
        this.userRepository = userRepository;
        this.authenticationManager = authenticationManager;
    }
    @PostMapping("/register")
    public ResponseEntity<AuthRequest> register(@RequestBody @Valid AuthRequest authRequest) {

        userService.CreateUser(authRequest);
        return ResponseEntity.ok(authRequest);

    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthLogin request) {

        // Username ve password doğrula
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );



        // Doğruysa user'ı al
        User user = (User) authentication.getPrincipal();
        System.out.println("girildi");


        // Token oluştur
        String token = jwtService.generateToken(user.getUsername());

        // Token'ı body içinde döndür
        Map<String, Object> response = new HashMap<>();
        response.put("token", token);
        response.put("username", user.getUsername());
        response.put("amount", user.getAmount());

        return ResponseEntity.ok(response);
    }

    @GetMapping("/public")
    public String hi(){

        return "hi bitchs";
    }

    @PutMapping("/amount")
    public ResponseEntity<Double> Get_Amount(@RequestHeader("Authorization") String token, @RequestBody Coin amount){



        return ResponseEntity.ok(userService.Get_Amount(token,amount.getAmount()));
    }

}
