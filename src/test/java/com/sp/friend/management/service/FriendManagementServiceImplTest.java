package com.sp.friend.management.service;

import com.sp.friend.management.FriendManagementException;
import com.sp.friend.management.domain.User;
import com.sp.friend.management.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class FriendManagementServiceImplTest {

    @Autowired
    UserRepository userRepository;

    @Autowired
    FriendsManagementService friendManagementService;

    @Test
    void test_when_createFriendConnection_then_friendsConnectionBetweenUsersExists() throws FriendManagementException {
        String first = "andy@example.com";
        String second = "john@example.com";
        List<String> users = new ArrayList<>();
        users.add(first);
        users.add(second);
        friendManagementService.createFriendsConnection(users);
        User firstUser = userRepository.findByEmail(first).get(0);
        User secondUser = userRepository.findByEmail(second).get(0);

        assertTrue(userRepository.isFriendsWith(firstUser, secondUser));
        assertTrue(userRepository.isFriendsWith(secondUser, firstUser));
    }

    @Test
    void test_when_createFriendConnectionBetweenOneself_then_noFriendsConnectionExists() throws FriendManagementException {
        String email = "andy@example.com";
        List<String> users = new ArrayList<>();
        users.add(email);
        friendManagementService.createFriendsConnection(users);
        User user = userRepository.findByEmail(email).get(0);

        assertFalse(userRepository.isFriendsWith(user, user));
    }




}