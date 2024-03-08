package oss.akrzelj.services.implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import oss.akrzelj.dtos.UserDto;
import oss.akrzelj.dtos.UserResDto;
import oss.akrzelj.models.User;
import oss.akrzelj.repositories.UserRepository;
import oss.akrzelj.services.interfaces.UserService;

import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;

    @Override
    public UserResDto addUser(UserDto userDto) {
        return null;
    }

    @Override
    public void validateLogin(UserDto userLoginDto) {

    }

    @Override
    public void deleteUser(String userId) {

    }

    @Override
    public UserResDto updateUser(String userId, UserDto userDto) {
        return null;
    }

    @Override
    public User findUserById(String userId) {
        return null;
    }

    @Override
    public UserResDto findUserByEmail(String email) {
        return null;
    }

    @Override
    public List<UserResDto> findAllUsers(Map<String, String> allParams) {
        return null;
    }

    @Override
    public void userChangePassword(String userId, UserDto userDto) {

    }

    @Override
    public List<UserResDto> filterUsers(Map<String, String> allParams) {
        return null;
    }
}
