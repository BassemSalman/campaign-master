package com.bassem.campaignmaster.mapper;

import org.mapstruct.BeanMapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;

@Component
public abstract class BaseMapper<Model, CreateDto, UpdateDto, ResponseDto> {
    public abstract ResponseDto toResponseDto(Model model);
    public abstract List<ResponseDto> toResponseDtos(Collection<Model> model);
    public abstract Model fromCreateDto(CreateDto createDto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    abstract void mapUpdateDtoToModel(@MappingTarget Model model, UpdateDto updateDto);

    public Model fromUpdateDto(Model model, UpdateDto updateDto){
        this.mapUpdateDtoToModel(model, updateDto);
        return model;
    }
}
