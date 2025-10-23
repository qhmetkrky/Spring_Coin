package com.example.demo.service;

import com.example.demo.Appconfig.passwordencode;
import com.example.demo.JwtFilter.JwtToken;
import com.example.demo.dto.AuthRequest;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final passwordencode appconfig;
    private final JwtToken jwtToken;



    public UserService(UserRepository userRepository, passwordencode appconfig, JwtToken jwtToken) {
        this.userRepository = userRepository;
        this.appconfig = appconfig;
        this.jwtToken = jwtToken;
    }

    public Optional<User> get(String username) {

        return userRepository.findByUsername(username);

    }

    public ResponseEntity<AuthRequest> CreateUser(AuthRequest authRequest){
        AuthRequest authRequest1 =new AuthRequest();
        BeanUtils.copyProperties(authRequest, authRequest1);

        User user = User.builder()
                .username(authRequest1.getUsername())
                .email(authRequest1.getEmail())
                .password(appconfig.bCryptPasswordEncoder().encode(authRequest1.getPassword()))
                .amount(0.0)
                .build();

        userRepository.save(user);
        return ResponseEntity.ok(authRequest);
    }

    public Double Get_Amount(String token,Double amount){

        String tok=token;

        if(token.startsWith("Bearer ")) {
            token = token.substring(7);
            tok=token;
        }
        String s = jwtToken.extractUser(tok);

        Optional<User> user = userRepository.findByUsername(s);

        if(user.isPresent()){
            user.get().setAmount(amount);
            userRepository.save(user.get());

            return user.get().getAmount();

        }
        else{

            return null;
        }


    }
}
