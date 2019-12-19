package com.sp.friend.management.service;

import com.sp.friend.management.FriendsManagementException;
import com.sp.friend.management.domain.User;
import com.sp.friend.management.repository.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class FriendsManagementServiceImplTest {

    @Autowired
    UserRepository userRepository;

    @Autowired
    FriendsManagementService friendManagementService;

    @AfterEach
    void tearDown() {
        userRepository.deleteAll();
    }

    @Test
    void test_when_createFriendConnection_then_friendsConnectionBetweenUsersExists() throws FriendsManagementException {
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
    void test_when_createFriendConnectionBetweenOneself_then_noFriendsConnectionExists() throws FriendsManagementException {
        String email = "andy@example.com";
        List<String> users = new ArrayList<>();
        users.add(email);
        friendManagementService.createFriendsConnection(users);
        User user = userRepository.findByEmail(email).get(0);
        assertFalse(userRepository.isFriendsWith(user, user));
    }

    @Test
    void test_when_retrieveFriendsForUserWithFriends_then_listOfFriendsRetrieved() throws FriendsManagementException {
        String first = "andy@example.com";
        String second = "john@example.com";
        List<String> users = new ArrayList<>();
        users.add(first);
        users.add(second);
        friendManagementService.createFriendsConnection(users);
        List<String> friendsList = friendManagementService.retrieveFriendsList(first);
        assertFalse(friendsList.isEmpty());
    }

    @Test
    void test_when_retrieveFriendsForUserWithNoFriends_then_listOfFriendsEmpty() throws FriendsManagementException {
        String first = "andy@example.com";
        List<String> users = new ArrayList<>();
        users.add(first);
        friendManagementService.createFriendsConnection(users);
        List<String> friendsList = friendManagementService.retrieveFriendsList(first);
        assertTrue(friendsList.isEmpty());
    }

    @Test
    void test_when_retrieveCommonFriends_then_listOfCommonFriendsRetrieved() throws FriendsManagementException {
        String first = "andy@example.com";
        String second = "john@example.com";
        String common = "common@example.com";
        List<String> users = new ArrayList<>();
        users.add(first);
        users.add(second);
        users.add(common);
        friendManagementService.createFriendsConnection(users);
        List<String> commonUsers = new ArrayList<>();
        commonUsers.add(first);
        commonUsers.add(second);
        List<String> friendsList = friendManagementService.retrieveCommonFriendsList(commonUsers);

        assertTrue(friendsList.contains(common));
    }








}