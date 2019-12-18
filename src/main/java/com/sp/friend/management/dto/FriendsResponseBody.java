package com.sp.friend.management.dto;

public class FriendsResponseBody {

    private boolean success;
    private String statusMessage;

    private FriendsResponseBody(boolean success) {
        this.success = success;
    }

    public boolean isSuccess() {
        return success;
    }

    public static FriendsResponseBody.FriendResponseBuilder builder(boolean success) {
        return new FriendResponseBuilder(success);
    }

    public static class FriendResponseBuilder {
        private boolean success;
        private String statusMessage;

        FriendResponseBuilder(boolean success) {
            this.success = success;
        }

        public FriendResponseBuilder withStatusMessage(String statusMessage) {
            this.statusMessage = statusMessage;
            return this;
        }

        public FriendsResponseBody build() {
            FriendsResponseBody friendsResponseBody = new FriendsResponseBody(this.success);
            friendsResponseBody.statusMessage = this.statusMessage;
            return friendsResponseBody;
        }

    }


}
