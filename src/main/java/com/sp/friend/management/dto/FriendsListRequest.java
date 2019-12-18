package com.sp.friend.management.dto;

public class FriendsListRequest {

    private String email;

    public FriendsListRequest(String email) {
        this.email = email;
    }

    public FriendsListRequest() {
    }

    public String getEmail() {
        return email;
    }
}
