package com.example.quizapp.service;

import com.example.quizapp.payload.ApiResult;
import com.example.quizapp.payload.SignDTO;
import com.example.quizapp.payload.TokenDTO;

import java.util.UUID;


public interface AuthService {


    ApiResult<String> register(SignDTO signDTO);

    ApiResult<TokenDTO> signIn(SignDTO signDTO);

    ApiResult<String> verificationEmail(UUID code);


}
