package oss.akrzelj.services.interfaces;

import oss.akrzelj.dtos.UserDto;
import oss.akrzelj.dtos.UserPageDto;
import oss.akrzelj.dtos.UserResDto;
import oss.akrzelj.exceptions.*;
import oss.akrzelj.models.User;

import java.util.List;
import java.util.Map;

public interface UserService {

    UserResDto addUser(UserDto userDto) throws InvalidArgumentsException, PasswordMismatchException, AlreadyExistException, EmailMismatchException;

    UserResDto validateLogin(UserDto userLoginDto) throws InvalidArgumentsException, ObjectDoesntExistException, PasswordMismatchException;

    void deleteUser(String userId) throws InvalidArgumentsException, ObjectDoesntExistException;

    UserResDto updateUser(String userId, UserDto userDto) throws InvalidArgumentsException, ObjectDoesntExistException;

    User findUserById(String userId) throws InvalidArgumentsException, ObjectDoesntExistException;

    UserResDto findUserByEmail(String email) throws InvalidArgumentsException, ObjectDoesntExistException;

    UserPageDto findAllUsers(Map<String, String> allParams);

    void userChangePassword(String userId, UserDto userDto) throws InvalidArgumentsException, PasswordMismatchException, ObjectDoesntExistException;

    UserPageDto filterUsers(Map<String, String> allParams);
}