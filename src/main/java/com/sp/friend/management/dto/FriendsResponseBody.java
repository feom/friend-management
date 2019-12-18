package com.sp.friend.management.dto;

import java.util.List;

public class FriendsResponseBody {

    private boolean success;
    private String statusMessage;
    private List<String> friends;
    private int count;

    private FriendsResponseBody(boolean success) {
        this.success = success;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getStatusMessage() {
        return statusMessage;
    }

    public List<String> getFriends() {
        return friends;
    }

    public int getCount() {
        return count;
    }

    public static FriendsResponseBody.FriendResponseBuilder builder(boolean success) {
        return new FriendResponseBuilder(success);
    }

    public static class FriendResponseBuilder {
        private boolean success;
        private String statusMessage;
        private List<String> friends;
        private int count;

        FriendResponseBuilder(boolean success) {
            this.success = success;
        }

        public FriendResponseBuilder withStatusMessage(String statusMessage) {
            this.statusMessage = statusMessage;
            return this;
        }

        public FriendResponseBuilder withFriendsList(List<String> friendsList) {
            this.friends = friendsList;
            this.count = this.friends.size();
            return this;
        }

        public FriendsResponseBody build() {
            FriendsResponseBody friendsResponseBody = new FriendsResponseBody(this.success);
            friendsResponseBody.statusMessage = this.statusMessage;
            friendsResponseBody.friends = this.friends;
            friendsResponseBody.count = this.count;
            return friendsResponseBody;
        }

    }


}
