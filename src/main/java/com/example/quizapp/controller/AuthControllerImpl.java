package com.example.quizapp.controller;

import com.example.quizapp.payload.ApiResult;
import com.example.quizapp.payload.SignDTO;
import com.example.quizapp.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class AuthControllerImpl implements AuthController{

    private final AuthService authService;

    @Override
    public ApiResult<?> signUp(SignDTO signDTO) {
        return authService.register(signDTO);
    }

    @Override
    public ApiResult<?> signIn(SignDTO signDTO) {
        return authService.signIn(signDTO);
    }

    @Override
    public ApiResult<?> verificationEmail(UUID code) {
        return authService.verificationEmail(code);
    }
}
