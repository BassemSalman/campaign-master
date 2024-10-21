package com.bassem.campaignmaster.mapper;

import org.mapstruct.BeanMapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface BaseMapper<Model, CreateDTO, UpdateDTO, ResponseDTO> {
//    ResponseDTO toResponseDTO(Model model);
//    List<ResponseDTO> toResponseDTOs(List<Model> model);

    Model fromCreateDTO(CreateDTO createDTO);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void mapUpdateDTOToModel(UpdateDTO updateDTO, @MappingTarget Model model);

    default Model fromUpdateDTO(Model model, UpdateDTO updateDTO){
        this.mapUpdateDTOToModel(updateDTO, model);
        return model;
    }
}
