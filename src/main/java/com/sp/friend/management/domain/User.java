package com.sp.friend.management.domain;


import org.neo4j.ogm.annotation.*;

import java.util.HashSet;
import java.util.Objects;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id.equals(user.id) &&
                email.equals(user.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, email);
    }
}
