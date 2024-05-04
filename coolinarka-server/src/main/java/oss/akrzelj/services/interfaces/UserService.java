package oss.akrzelj.services.interfaces;

import oss.akrzelj.dtos.UserDto;
import oss.akrzelj.dtos.UserResDto;
import oss.akrzelj.models.User;

import java.util.List;
import java.util.Map;

public interface UserService {

    UserResDto addUser(UserDto userDto);

    User validateLogin(UserDto userLoginDto);

    void deleteUser(String userId);

    UserResDto updateUser(String userId, UserDto userDto);

    User findUserById(String userId);

    UserResDto findUserByEmail(String email);

    List<UserResDto> findAllUsers(Map<String, String> allParams);

    void userChangePassword(String userId, UserDto userDto);

    List<UserResDto> filterUsers(Map<String, String> allParams);
}