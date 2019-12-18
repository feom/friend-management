package com.sp.friend.management.service;

import com.sp.friend.management.FriendsManagementException;

import java.util.List;

public interface FriendsManagementService {
    void createFriendsConnection(List<String> users) throws FriendsManagementException;
    List<String> retrieveFriendsList(String email) throws FriendsManagementException;
}
