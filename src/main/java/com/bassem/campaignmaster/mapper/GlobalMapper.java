package com.bassem.campaignmaster.mapper;

import org.mapstruct.BeanMapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.springframework.stereotype.Component;


@Component
public interface GlobalMapper<Model, CreateDTO, UpdateDTO> {
    Model fromCreateDTO(CreateDTO createDTO);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void mapUpdateDTOToModel(@MappingTarget Model model, UpdateDTO updateDTO);

    default Model fromUpdateDTO(Model model, UpdateDTO updateDTO){
        this.mapUpdateDTOToModel(model, updateDTO);
        return model;
    }
}
