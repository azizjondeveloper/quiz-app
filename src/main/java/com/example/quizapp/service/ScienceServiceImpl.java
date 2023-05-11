package com.example.quizapp.service;

import com.example.quizapp.entity.Science;
import com.example.quizapp.payload.AddScienceDTO;
import com.example.quizapp.payload.ApiResult;
import com.example.quizapp.payload.ScienceDTO;
import com.example.quizapp.repository.ScienceRepository;
import com.example.quizapp.utils.RestConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ScienceServiceImpl implements ScienceService{


    @Autowired
    private ScienceRepository scienceRepository;

    @Override
    public ApiResult<?> add(AddScienceDTO addScienceDTO) {
        Science science = mapAddScienceDTOToScience(addScienceDTO, new Science());
        scienceRepository.save(science);
        return ApiResult.successResponse();
    }


    @Override
    public List<ScienceDTO> getAll() {
        return scienceRepository.findAll()
                .stream()
                .map(this::mapSceinceToScienceDTO)
                .collect(Collectors.toList());
    }



    @Override
    public ScienceDTO get(Integer id) {
        Science science = scienceRepository.findById(id).orElseThrow(RuntimeException::new);
        return mapSceinceToScienceDTO(science);
    }

    @Override
    public ApiResult<?> edit(Integer id, AddScienceDTO addScienceDTO) {
        Science science = scienceRepository.findById(id).orElseThrow(RuntimeException::new);

        Science editScience = mapAddScienceDTOToScience(addScienceDTO, science);
        scienceRepository.save(editScience);
        return ApiResult.successResponse();
    }

    @Override
    public ApiResult<?> delete(Integer id) {
        scienceRepository.deleteById(id);
        return ApiResult.successResponse();
    }



    private ScienceDTO mapSceinceToScienceDTO(Science science) {

        return new ScienceDTO(science.getId(),
                                science.getTitle()
                                );

    }


    private Science mapAddScienceDTOToScience(AddScienceDTO addScienceDTO, Science science) {

        science.setTitle(addScienceDTO.getTitle());
        science.setUrl(RestConstants.makeUrl(addScienceDTO.getTitle()));

        return science;

    }

}
