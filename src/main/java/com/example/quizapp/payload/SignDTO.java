package com.example.quizapp.payload;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignDTO {

    @NotBlank(message = "emailda xatolik")
    @Email(message = "emailda xatolik", regexp = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$")
    private String username;

    @NotBlank(message = "parolda xatolik")
    @Pattern(message = "parolda xatolik",regexp = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&*()_+=])(?=\\S+$).{8,}$")
    private String password;
}
