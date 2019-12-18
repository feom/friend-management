package com.sp.friend.management.domain;


import org.neo4j.ogm.annotation.*;

import java.util.HashSet;
import java.util.Set;

@NodeEntity
public class User {

    @Id @GeneratedValue
    private Long id;

    private String email;

    @Relationship(type = "FRIENDS_WITH")
    private Set<User> friends;

    private User(String email) {
        this.email = email;
        this.friends = new HashSet<>();
    }

    public void addFriend(User user) {
        friends.add(user);
    }

    public Set<User> getFriends() {
        return friends;
    }

    public String getEmail() {
        return email;
    }

    public static User create(String email) {
        return new User(email);
    }

}
