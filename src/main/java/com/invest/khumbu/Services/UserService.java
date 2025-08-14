package com.invest.khumbu.Services;

import com.invest.khumbu.Model.User;
import com.invest.khumbu.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

        // Method to delete user by ID
        public void deleteUserById(Long userId) {
            userRepository.deleteById(userId); // Delete the user from the database
        }
}

