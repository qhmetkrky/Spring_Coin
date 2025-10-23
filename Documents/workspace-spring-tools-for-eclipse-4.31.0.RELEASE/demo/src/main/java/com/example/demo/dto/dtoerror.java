package com.example.demo.dto;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class dtoerror {


        private List<String> messages;


        public dtoerror(List<String> messages) {
            this.messages = messages;
        }
         @Bean
        public List<String> getMessages() {
            return messages;
        }




}
