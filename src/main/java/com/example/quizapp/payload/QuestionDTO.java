package com.example.quizapp.payload;


import com.example.quizapp.entity.Science;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class QuestionDTO {

    private Integer id;

    private String title;

    private String option1;

    private String option2;

    private String option3;

    private String option4;

    private String currentOption;

    private Science science;


}
