package com.example.demo.service;

import com.example.demo.model.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDetais implements UserDetailsService {

    private final   UserService userService;

    public UserDetais(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<User> user = userService.get(username);
        User user1 = user.get();
        if(user1==null){
            System.out.println("null");

        }
        else
        {
            System.out.println("not null");
        }
        System.out.println(user1.getPassword());
        return user1;
    }
}
