package oss.akrzelj.controllers.implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import oss.akrzelj.controllers.interfaces.UserController;
import oss.akrzelj.dtos.LoginResDto;
import oss.akrzelj.dtos.UserDto;
import oss.akrzelj.dtos.UserPageDto;
import oss.akrzelj.dtos.UserResDto;
import oss.akrzelj.exceptions.*;
import oss.akrzelj.mappers.UserMapper;
import oss.akrzelj.models.User;
import oss.akrzelj.services.interfaces.UserService;
import oss.akrzelj.utils.TokenGenerator;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping("/api/v1/user")
@CrossOrigin
public class UserControllerImpl implements UserController {

    private final UserService userService;
    private final TokenGenerator tokenGenerator;
    private final UserMapper userMapper;

    @Autowired
    public UserControllerImpl(UserService userService, TokenGenerator tokenGenerator, UserMapper userMapper) {
        this.userService = userService;
        this.tokenGenerator = tokenGenerator;
        this.userMapper = userMapper;
    }

    @Override
    @PostMapping("/register")
    public ResponseEntity<UserResDto> register(@RequestBody UserDto userDto) throws EmailMismatchException, PasswordMismatchException, AlreadyExistException, InvalidArgumentsException {
        UserResDto user = userService.addUser(userDto);
        return ResponseEntity.status(HttpStatus.OK).body(user);
    }

    @Override
    @PostMapping("/login")
    public ResponseEntity<LoginResDto> login(@RequestBody UserDto userLoginDto) throws InvalidArgumentsException, ObjectDoesntExistException, PasswordMismatchException {
        UserResDto user = userService.validateLogin(userLoginDto);

        String jwtToken = tokenGenerator.generateToken(String.valueOf(user.getRole()), user.getId());

        LoginResDto loginResDto = LoginResDto.builder()
                .success(true)
                .token(jwtToken)
                .userResDto(user)
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(loginResDto);
    }

    @Override
    @PutMapping("/{id}")
    public ResponseEntity<UserResDto> edit(@PathVariable String id, @RequestBody UserDto userDto) throws InvalidArgumentsException, ObjectDoesntExistException, AlreadyExistException {
        UserResDto updatedUser = userService.updateUser(id, userDto);
        return ResponseEntity.status(HttpStatus.OK).body(updatedUser);
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable String id) throws ObjectDoesntExistException, AlreadyExistException, InvalidArgumentsException {
        userService.deleteUser(id);
        return ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @Override
    @PutMapping("/change-password/{id}")
    public ResponseEntity<Boolean> changePassword(@PathVariable String id, @RequestBody UserDto userPasswordDto) throws InvalidArgumentsException, PasswordMismatchException, ObjectDoesntExistException, AlreadyExistException {
        userService.userChangePassword(id, userPasswordDto);
        return ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @Override
    @GetMapping("/list")
    public ResponseEntity<UserPageDto> list(@RequestParam Map<String, String> allParams) {
        UserPageDto userList = userService.findAllUsers(allParams);

        return ResponseEntity.ok().body(userList);
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<UserResDto> profile(@PathVariable String id) throws ObjectDoesntExistException, InvalidArgumentsException {
        User userProfile = userService.findUserById(id);
        return ResponseEntity.ok().body(userMapper.userToUserDto(userProfile));
    }

    @Override
    @GetMapping("/filter")
    public ResponseEntity<UserPageDto> filterList(@RequestParam Map<String, String> allParams) throws InvalidArgumentsException {
        UserPageDto userPageDto = userService.filterUsers(allParams);
        return ResponseEntity.ok().body(userPageDto);
    }

    @Override
    @GetMapping("/search")
    public ResponseEntity<UserPageDto> searchList(@RequestParam String search) throws InvalidArgumentsException {

        if (Objects.equals(search, "")) throw new InvalidArgumentsException("Sent arguments cannot be null!");
        System.out.println(search);
        UserPageDto searchedUsers = UserPageDto.builder().build();
        //List<User> searchedUsers = userService.findByPartialFullName(search);

        return ResponseEntity.ok().body(searchedUsers);
    }
}
