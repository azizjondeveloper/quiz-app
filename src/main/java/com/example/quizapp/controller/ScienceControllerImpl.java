package com.example.quizapp.controller;

import com.example.quizapp.payload.AddScienceDTO;
import com.example.quizapp.payload.ApiResult;
import com.example.quizapp.payload.ScienceDTO;
import com.example.quizapp.service.ScienceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ScienceControllerImpl implements ScienceController{


    @Autowired
    private ScienceService scienceService;

    @Override
    public ApiResult<?> add(AddScienceDTO addScienceDTO) {
        return scienceService.add(addScienceDTO);
    }

    @Override
    public List<ScienceDTO> getAll() {
        return scienceService.getAll();
    }

    @Override
    public ScienceDTO get(Integer id) {
        return scienceService.get(id);
    }

    @Override
    public ApiResult<?> edit(Integer id, AddScienceDTO addScienceDTO) {
        return scienceService.edit(id, addScienceDTO);
    }

    @Override
    public ApiResult<?> delete(Integer id) {
        return scienceService.delete(id);
    }
}
