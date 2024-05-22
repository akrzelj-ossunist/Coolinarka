package oss.akrzelj.services.implementations;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import oss.akrzelj.dtos.UserDto;
import oss.akrzelj.dtos.UserPageDto;
import oss.akrzelj.dtos.UserResDto;
import oss.akrzelj.exceptions.*;
import oss.akrzelj.mappers.UserMapper;
import oss.akrzelj.models.User;
import oss.akrzelj.repositories.UserRepository;
import oss.akrzelj.services.interfaces.UserService;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @Override
    public UserResDto addUser(UserDto userDto) throws InvalidArgumentsException, PasswordMismatchException, AlreadyExistException, EmailMismatchException {
        if(userDto == null) throw new InvalidArgumentsException("Entered arguments are invalid!");

        if(!Objects.equals(userDto.getPassword(), userDto.getConfirmPassword())) throw new PasswordMismatchException("Entered passwords doesn't match!");

        Optional<User> existingUser = userRepository.findByEmail(userDto.getEmail());
        if(existingUser.isPresent()) throw new AlreadyExistException("User already exist in database!");

        User newUser = userRepository.save(userMapper.userDtoToUser(userDto));

        return userMapper.userToUserDto(newUser);
    }

    @Override
    public UserResDto validateLogin(UserDto userLoginDto) throws InvalidArgumentsException, ObjectDoesntExistException, PasswordMismatchException {

        if(userLoginDto == null) throw new InvalidArgumentsException("Sent arguments cannot be null!");

        Optional<User> user = userRepository.findByEmail(userLoginDto.getEmail());
        if(user.isEmpty()) throw new ObjectDoesntExistException("User you wanna login with doesn't exist!");

        if(!BCrypt.checkpw(userLoginDto.getPassword(), user.get().getPassword())) throw new PasswordMismatchException("Invalid password!");

        return userMapper.userToUserDto(user.get());
    }

    @Override
    public void deleteUser(String userId) throws InvalidArgumentsException, ObjectDoesntExistException {

        if(userId == null) throw new InvalidArgumentsException("Entered user id cannot be null");

        userRepository.delete(findUserById(userId));
    }

    @Override
    public UserResDto updateUser(String userId, UserDto userDto) throws InvalidArgumentsException, ObjectDoesntExistException {
        if(userId == null || userDto == null) throw new InvalidArgumentsException("Entered user id or user info cannot be null!");

        User user = findUserById(userId);

        User updatedUser = userRepository.save(updatedUser(user, userDto));

        return userMapper.userToUserDto(updatedUser);
    }

    public User updatedUser(User user, UserDto userDto) {

        user.setUsername(userDto.getUsername());
        user.setBirthday(userDto.getBirthday());
        user.setBio(userDto.getBio());
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());

        return user;
    }

    @Override
    public User findUserById(String userId) throws InvalidArgumentsException, ObjectDoesntExistException {
        if(userId == null) throw new InvalidArgumentsException("Entered user id cannot be null");

        Optional<User> user = userRepository.findById(userId);

        if(user.isEmpty()) throw new ObjectDoesntExistException("User you are looking for doesn't exist!");

        return user.get();
    }

    @Override
    public UserResDto findUserByEmail(String email) throws InvalidArgumentsException, ObjectDoesntExistException {
        if(email == null) throw new InvalidArgumentsException("Entered user id cannot be null");

        Optional<User> user = userRepository.findByEmail(email);

        if(user.isEmpty()) throw new ObjectDoesntExistException("User you are looking for doesn't exist!");

        return userMapper.userToUserDto(user.get());
    }

    @Override
    public UserPageDto findAllUsers(Map<String, String> allParams) {
        int page = allParams.get("page") != null ? Integer.parseInt(allParams.get("page")) - 1 : 0;
        int size = allParams.get("size") != null ? Integer.parseInt(allParams.get("size")) : 10;

        Pageable pageable = (Pageable) PageRequest.of(page, size);

        List<User> userList = userRepository.findAll(pageable).getContent();

        return UserPageDto.builder()
                .userPage(userMapper.userListToUserDtoList(userList))
                .pageNum(page)
                .pageSize(size)
                .lastPage((int) Math.ceil((double) userRepository.count() / size))
                .build();
    }

    @Override
    public void userChangePassword(String userId, UserDto userDto) throws InvalidArgumentsException, PasswordMismatchException, ObjectDoesntExistException {
        if(userId == null || userDto == null) throw new InvalidArgumentsException("Entered user id or user info cannot be null!");

        if(!Objects.equals(userDto.getPassword(), userDto.getConfirmPassword())) throw new PasswordMismatchException("Entered passwords doesn't match!");

        User user = findUserById(userId);
        if(user == null) throw new ObjectDoesntExistException("User doesn't exist in library!");

        user.setPassword(BCrypt.hashpw(userDto.getPassword(), BCrypt.gensalt(10)));

        userRepository.save(user);
    }

    @Override
    public UserPageDto filterUsers(Map<String, String> allParams) {
        int page = allParams.get("page") != null ? Integer.parseInt(allParams.get("page")) - 1 : 0;
        int size = allParams.get("size") != null ? Integer.parseInt(allParams.get("size")) : 10;

        Pageable pageable = (Pageable) PageRequest.of(page, size);

        List<User> userList = userRepository.findByUsernameAndFirstNameAndLastNameAndEmail(
                allParams.get("username"),
                allParams.get("firstName"),
                allParams.get("lastName"),
                allParams.get("email"),
                pageable).getContent();

        return UserPageDto.builder()
                .lastPage((int) Math.ceil((double) userRepository.count() / size))
                .userPage(userMapper.userListToUserDtoList(userList))
                .pageSize(size)
                .pageNum(page)
                .build();

    }
}
