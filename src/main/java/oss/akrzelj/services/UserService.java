package oss.akrzelj.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import oss.akrzelj.repositories.UserRepository;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

}
