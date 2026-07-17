package com.digitalnurture.spring.service;

import com.digitalnurture.spring.entity.User;
import com.digitalnurture.spring.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("User not found: " + id));
    }

    public User saveUser(User user) {
        return userRepository.save(user);
    }

    public List<User> findUsersByName(String name) {
        return userRepository.findByName(name);
    }
}
