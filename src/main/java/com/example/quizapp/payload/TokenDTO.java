package com.example.quizapp.payload;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class TokenDTO {

    private String token;

    private final String tokenType = "Bearer ";
}
