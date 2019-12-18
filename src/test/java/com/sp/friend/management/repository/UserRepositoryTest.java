package com.sp.friend.management.repository;

import com.sp.friend.management.domain.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.neo4j.DataNeo4jTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataNeo4jTest
public class UserRepositoryTest {

    @Autowired
    UserRepository userRepository;

    @Test
    void test_when_UserExists_thenFindByEmail() {
        String email = "test@gmailcom";
        userRepository.save(User.create(email));
        List<User> byEmail = userRepository.findByEmail(email);
        assertFalse(byEmail.isEmpty());
    }

    @Test
    void test_when_UserNotExists_thenFindEmpty() {
        String email = "test@gmailcom";
        List<User> byEmail = userRepository.findByEmail(email);
        assertTrue(byEmail.isEmpty());
    }

}
