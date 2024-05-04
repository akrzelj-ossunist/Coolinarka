package oss.akrzelj.controllers.implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import oss.akrzelj.controllers.interfaces.UserController;
import oss.akrzelj.dtos.LoginResDto;
import oss.akrzelj.dtos.UserDto;
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

    private final UserMapper userMapper;
    private final UserService userService;
    private final TokenGenerator tokenGenerator;

    @Autowired
    public UserControllerImpl(UserService userService, UserMapper userMapper, TokenGenerator tokenGenerator) {
        this.userMapper = userMapper;
        this.userService = userService;
        this.tokenGenerator = tokenGenerator;
    }

    @Override
    @PostMapping("/register")
    public ResponseEntity<UserResDto> register(UserDto userDto) throws EmailMismatchException, PasswordMismatchException, AlreadyExistException, InvalidArgumentsException {
        UserResDto user = userService.addUser(userDto);
        return ResponseEntity.status(HttpStatus.OK).body(user);
    }

    @Override
    @PostMapping("/login")
    public ResponseEntity<LoginResDto> login(UserDto userLoginDto) throws InvalidArgumentsException, ObjectDoesntExistException {
        User user = userService.validateLogin(userLoginDto);

        String jwtToken = tokenGenerator.generateToken(String.valueOf(user.getRole()), user.getId());

        LoginResDto loginResDto = LoginResDto.builder()
                .success(true)
                .token(jwtToken)
                .userResDto(userMapper.userToUserDto(user))
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(loginResDto);
    }

    @Override
    @PutMapping("/{id}")
    public ResponseEntity<UserResDto> edit(@PathVariable String userId, UserDto userDto) throws InvalidArgumentsException, ObjectDoesntExistException, AlreadyExistException {
        UserResDto updatedUser = userService.updateUser(userId, userDto);
        return ResponseEntity.status(HttpStatus.OK).body(updatedUser);
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable String userId) throws ObjectDoesntExistException, AlreadyExistException, InvalidArgumentsException {
        userService.deleteUser(userId);
        return ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @Override
    @PutMapping("/change-password/{id}")
    public ResponseEntity<Boolean> changePassword(@PathVariable String userId, UserDto userPasswordDto) throws InvalidArgumentsException, PasswordMismatchException, ObjectDoesntExistException, AlreadyExistException {
        userService.userChangePassword(userId, userPasswordDto);
        return ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @Override
    @GetMapping("/list")
    public ResponseEntity<List<UserResDto>> list(@RequestParam Map<String, String> allParams) {
        List<UserResDto> userList = userService.findAllUsers(allParams);
        if (userList.isEmpty()) return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        return ResponseEntity.ok().body(userList);
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<UserResDto> profile(@PathVariable String userId) throws ObjectDoesntExistException, InvalidArgumentsException {
        User userProfile = userService.findUserById(userId);
        return ResponseEntity.ok().body(userMapper.userToUserDto(userProfile));
    }

    @Override
    @GetMapping("/filter")
    public ResponseEntity<List<UserResDto>> filterList(@RequestParam Map<String, String> allParams) throws InvalidArgumentsException {
        List<UserResDto> filteredList = userService.filterUsers(allParams);
        return ResponseEntity.ok().body(filteredList);
    }

    @Override
    @GetMapping("/search")
    public ResponseEntity<List<UserResDto>> searchList(@RequestParam String search) throws InvalidArgumentsException {

        if (Objects.equals(search, "")) throw new InvalidArgumentsException("Sent arguments cannot be null!");
        System.out.println(search);
        List<User> searchedUsers = new ArrayList<>();
        //List<User> searchedUsers = userService.findByPartialFullName(search);

        return ResponseEntity.ok().body(userMapper.userListToUserDtoList(searchedUsers));
    }
}
