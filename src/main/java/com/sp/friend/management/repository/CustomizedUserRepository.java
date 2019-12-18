package com.sp.friend.management.repository;

import com.sp.friend.management.domain.User;

public interface CustomizedUserRepository {

    boolean isFriendsWith(User user, User friend);

}
