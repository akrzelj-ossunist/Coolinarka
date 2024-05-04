package oss.akrzelj.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import oss.akrzelj.dtos.UserDto;
import oss.akrzelj.dtos.UserResDto;
import oss.akrzelj.models.User;

import java.util.List;

@Mapper
public interface UserMapper {

    @Mapping(target = "role", expression = "java(oss.akrzelj.models.enums.UserRole.USER)")
    @Mapping(target = "createdAt", expression = "java(new java.util.Date())")
    @Mapping(target = "password", expression = "java(org.mindrot.jbcrypt.BCrypt.hashpw(userDto.getPassword(), org.mindrot.jbcrypt.BCrypt.gensalt(10)))")
    User userDtoToUser(UserDto userDto);

    UserResDto userToUserDto(User user);

    List<UserResDto> userListToUserDtoList(List<User> userList);
}
