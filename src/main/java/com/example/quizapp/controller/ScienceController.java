package com.example.quizapp.controller;


import com.example.quizapp.payload.AddScienceDTO;
import com.example.quizapp.payload.ApiResult;
import com.example.quizapp.payload.ScienceDTO;
import io.swagger.models.auth.In;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("sciences")
public interface ScienceController {


    @PostMapping
    ApiResult<?> add(@RequestBody AddScienceDTO addScienceDTO);


    @GetMapping
    List<ScienceDTO> getAll();


    @GetMapping("/{id}")
    ScienceDTO get(@PathVariable Integer id);


    @PutMapping("/{id}")
    ApiResult<?> edit(@PathVariable Integer id,
                      @RequestBody  AddScienceDTO addScienceDTO);


    @DeleteMapping("/{id}")
    ApiResult<?> delete(@PathVariable Integer id);


}
