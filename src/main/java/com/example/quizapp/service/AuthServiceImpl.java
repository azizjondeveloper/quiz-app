package com.example.quizapp.service;

import com.example.quizapp.entity.User;
import com.example.quizapp.exception.DataNotfoundException;
import com.example.quizapp.exception.InputDataExistsException;
import com.example.quizapp.payload.ApiResult;
import com.example.quizapp.payload.SignDTO;
import com.example.quizapp.payload.TokenDTO;
import com.example.quizapp.repository.UserRepository;
import com.example.quizapp.security.JwtService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class AuthServiceImpl implements UserDetailsService, AuthService {


    private final JwtService jwtService;

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;

    private final EmailService emailService;

    public AuthServiceImpl(JwtService jwtService,
                           UserRepository userRepository,
                           @Lazy PasswordEncoder passwordEncoder,
                           @Lazy AuthenticationManager authenticationManager,
                           EmailService emailService) {
        this.jwtService = jwtService;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.emailService = emailService;
    }


    @Override
    public UserDetails loadUserByUsername(String username) {
        return userRepository.findByUsername(username).orElseThrow(() ->
                new UsernameNotFoundException(String.format("Bunday %s topilmadi", username)));
    }
    public ApiResult<String> register(SignDTO signDTO) {

        if (userRepository.existsByUsername(signDTO.getUsername()))
            throw new InputDataExistsException("Bunday email mavjud");

        User user = new User(
                signDTO.getUsername(),
                passwordEncoder.encode(signDTO.getPassword()));


        UUID emailCode = UUID.randomUUID();
        user.setEmailCode(emailCode);
        userRepository.save(user);

        new Thread(() -> emailService.sendEmailVerificationCode(user.getUsername(), emailCode)).start();
        return ApiResult.successResponse("OK");
    }

    public ApiResult<TokenDTO> signIn(SignDTO signDTO) {

        if (!userRepository.existsByUsername(signDTO.getUsername()))
            throw new InputDataExistsException("Bunday email mavjud emas");

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        signDTO.getUsername(),
                        signDTO.getPassword()
                )
        );

        User user = userRepository.findByUsername(signDTO.getUsername()).orElseThrow();

        String token = jwtService.generateToken(user);



        TokenDTO tokenDTO = TokenDTO.builder()
                .token(token)
                .build();

        return ApiResult.successResponse(tokenDTO);
    }

    public ApiResult<String> verificationEmail(UUID code) {

        User user = userRepository.findByEmailCode(code)
                .orElseThrow(() -> new DataNotfoundException("Bunday code mavjud emas"));

        user.setEmailCode(null);
        user.setEnabled(true);
        userRepository.save(user);

        return ApiResult.successResponse("Muvaffaqiyatli activ qilindi");
    }





}
