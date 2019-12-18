package com.sp.friend.management.dto;

import java.util.List;

public class FriendsConnectionRequest {

    private List<String> friends;

    public FriendsConnectionRequest(List<String> friends) {
        this.friends = friends;
    }

    public FriendsConnectionRequest() {
    }

    public List<String> getFriends() {
        return friends;
    }

}
