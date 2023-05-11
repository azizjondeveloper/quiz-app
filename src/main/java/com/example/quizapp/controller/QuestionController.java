package com.example.quizapp.controller;


import com.example.quizapp.payload.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("quiz")
public interface QuestionController {


    @PostMapping
    ApiResult<?> add(@RequestBody AddQuestionDTO addQuestionDTO);


    @GetMapping
    List<QuestionDTO> getAll();


    @GetMapping("/{id}")
    QuestionDTO get(@PathVariable Integer id);


    @PutMapping("/{id}")
    ApiResult<?> edit(@PathVariable Integer id,
                      @RequestBody  AddQuestionDTO addQuestionDTO);


    @DeleteMapping("/{id}")
    ApiResult<?> delete(@PathVariable Integer id);

}
