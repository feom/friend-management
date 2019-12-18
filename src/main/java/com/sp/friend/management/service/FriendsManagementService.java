package com.sp.friend.management.service;

import com.sp.friend.management.FriendManagementException;

import java.util.List;

public interface FriendsManagementService {
    void createFriendsConnection(List<String> users) throws FriendManagementException;
}
