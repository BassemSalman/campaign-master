package com.bassem.campaignmaster.mapper;
import com.bassem.campaignmaster.dto.UserDto;
import com.bassem.campaignmaster.model.User;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;


@Component
@Mapper(componentModel = "spring")
public abstract class UserMapper extends BaseMapper<User, UserDto, UserDto, UserDto>{
}
