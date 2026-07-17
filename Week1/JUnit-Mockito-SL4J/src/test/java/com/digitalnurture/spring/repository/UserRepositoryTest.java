package com.digitalnurture.spring.repository;

import com.digitalnurture.spring.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    void findByNameReturnsMatchingUsers() {
        userRepository.save(new User("Eve"));
        userRepository.save(new User("Eve"));
        userRepository.save(new User("Frank"));

        List<User> results = userRepository.findByName("Eve");

        assertEquals(2, results.size());
    }
}
