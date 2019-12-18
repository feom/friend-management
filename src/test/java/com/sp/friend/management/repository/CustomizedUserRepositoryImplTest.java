package com.sp.friend.management.repository;

import com.sp.friend.management.domain.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.neo4j.DataNeo4jTest;

import static org.junit.jupiter.api.Assertions.*;

@DataNeo4jTest
class CustomizedUserRepositoryImplTest {

    @Autowired
    UserRepository userRepository;

    @Test
    void test_when_twoUsersAreFriends_thenReturnTrue() {
        String andyMail = "andyMail@gmailcom";
        String thomasMail = "thomas@gmail";
        User andy = User.create(andyMail);
        User thomas = User.create(thomasMail);
        andy.addFriend(thomas);
        thomas.addFriend(andy);
        userRepository.save(andy);
        userRepository.save(thomas);

        assertTrue(userRepository.isFriendsWith(andy, thomas));
        assertTrue(userRepository.isFriendsWith(thomas, andy));
    }

    @Test
    void test_when_twoUsersAreNotFriends_thenReturnFalse() {
        String andyMail = "andyMail@gmailcom";
        String thomasMail = "thomas@gmail";
        User andy = User.create(andyMail);
        User thomas = User.create(thomasMail);
        userRepository.save(andy);
        userRepository.save(thomas);

        assertFalse(userRepository.isFriendsWith(andy, thomas));
    }

}
