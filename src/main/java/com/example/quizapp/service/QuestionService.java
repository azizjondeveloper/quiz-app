package com.example.quizapp.service;

import com.example.quizapp.payload.*;

import java.util.List;

public interface QuestionService {


    ApiResult<?> add(AddQuestionDTO addQuestionDTO);


    List<QuestionDTO> getAll();


    QuestionDTO get(Integer id);


    ApiResult<?> edit(Integer id, AddQuestionDTO addQuestionDTO);


    ApiResult<?> delete(Integer id);

}
