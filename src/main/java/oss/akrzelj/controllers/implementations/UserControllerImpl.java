package oss.akrzelj.controllers.implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import oss.akrzelj.controllers.interfaces.UserController;
import oss.akrzelj.dtos.UserDto;
import oss.akrzelj.dtos.UserResDto;
import oss.akrzelj.exceptions.*;
import oss.akrzelj.mappers.UserMapper;
import oss.akrzelj.models.User;
import oss.akrzelj.services.interfaces.UserService;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/user")
@CrossOrigin
public class UserControllerImpl implements UserController {

    private final UserMapper userMapper;
    private final UserService userService;

    @Autowired
    public UserControllerImpl(UserService userService, UserMapper userMapper) {
        this.userMapper = userMapper;
        this.userService = userService;
    }

    @Override
    public ResponseEntity<UserResDto> register(UserDto userDto) throws EmailMismatchException, PasswordMismatchException, AlreadyExistException, InvalidArgumentsException {
        UserResDto user = userService.addUser(userDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }

    @Override
    public ResponseEntity<Boolean> login(UserDto userLoginDto) throws InvalidArgumentsException, ObjectDoesntExistException {
        userService.validateLogin(userLoginDto);
        return ResponseEntity.status(HttpStatus.FOUND).body(true);
    }

    @Override
    public ResponseEntity<UserResDto> edit(String userId, UserDto userDto) throws InvalidArgumentsException, ObjectDoesntExistException, AlreadyExistException {
        UserResDto updatedUser = userService.updateUser(userId, userDto);
        return ResponseEntity.status(HttpStatus.OK).body(updatedUser);
    }

    @Override
    public ResponseEntity<Boolean> delete(String userId) throws ObjectDoesntExistException, AlreadyExistException, InvalidArgumentsException {
        userService.deleteUser(userId);
        return ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @Override
    public ResponseEntity<Boolean> changePassword(String userId, UserDto userPasswordDto) throws InvalidArgumentsException, PasswordMismatchException, ObjectDoesntExistException, AlreadyExistException {
        userService.userChangePassword(userId, userPasswordDto);
        return ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @Override
    public ResponseEntity<List<UserResDto>> list(Map<String, String> allParams) {
        List<UserResDto> userList = userService.findAllUsers(allParams);
        if (userList.isEmpty()) return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        return ResponseEntity.ok().body(userList);
    }

    @Override
    public ResponseEntity<UserResDto> profile(String userId) throws ObjectDoesntExistException, InvalidArgumentsException {
        User userProfile = userService.findUserById(userId);
        return ResponseEntity.ok().body(userMapper.userToUserDto(userProfile));
    }

    @Override
    public ResponseEntity<List<UserResDto>> filterList(Map<String, String> allParams) throws InvalidArgumentsException {
        List<UserResDto> filteredList = userService.filterUsers(allParams);
        return ResponseEntity.ok().body(filteredList);
    }
}
