package com.example.quizapp.service;

import com.example.quizapp.payload.AddScienceDTO;
import com.example.quizapp.payload.ApiResult;
import com.example.quizapp.payload.ScienceDTO;

import java.util.List;

public interface ScienceService {


    ApiResult<?> add( AddScienceDTO addScienceDTO);


    List<ScienceDTO> getAll();


        ScienceDTO get( Integer id);


    ApiResult<?> edit( Integer id, AddScienceDTO addScienceDTO);


    ApiResult<?> delete( Integer id);

}
