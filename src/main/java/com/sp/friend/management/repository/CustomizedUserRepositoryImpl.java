package com.sp.friend.management.repository;

import com.sp.friend.management.domain.User;

public class CustomizedUserRepositoryImpl implements CustomizedUserRepository {

    @Override
    public boolean isFriendsWith(User user, User friend) {
        return user.getFriends().stream().anyMatch(u -> friend.getEmail().equals(u.getEmail()));
    }
}
