package kz.orbitalis.mapper;

import org.mapstruct.Mapper;
import kz.orbitalis.dto.user.UserDto;
import kz.orbitalis.model.User;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDto toDto(User user);

    User toModel(UserDto userDto);

    List<UserDto> toDtoList(List<User> users);
}