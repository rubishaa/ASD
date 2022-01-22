package com.track.expensetrackerapp.service;

import com.track.expensetrackerapp.Repository.UserRepository;
import com.track.expensetrackerapp.dto.UserDto;
import com.track.expensetrackerapp.model.User;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User findUserById(Integer id) {
        Optional<User> user = userRepository.findById(id);
        if(user.isPresent()){
            return user.get();
        }
        return null;
    }

    public Boolean validateUser(UserDto user) {
        User userByName = userRepository.findUserByUsername(user.getUsername());
        return userByName != null && userByName.getUsername().equals(user.getUsername())
                && userByName.getPassword().equals(user.getPassword());
    }
}
