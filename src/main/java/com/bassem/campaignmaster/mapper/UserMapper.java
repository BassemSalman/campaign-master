package com.bassem.campaignmaster.mapper;
import com.bassem.campaignmaster.dto.UserDTO;
import com.bassem.campaignmaster.model.User;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
@Mapper(componentModel = "spring")

public interface UserMapper {
    UserDTO toResponseDTO(User user);
    List<UserDTO> toResponseDTOs(List<User> User);
    User fromCreateDTO(UserDTO createDTO);
}
