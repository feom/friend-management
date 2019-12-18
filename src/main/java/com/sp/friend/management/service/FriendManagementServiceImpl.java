package com.sp.friend.management.service;

import com.sp.friend.management.FriendManagementException;
import com.sp.friend.management.domain.User;
import com.sp.friend.management.repository.UserRepository;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FriendManagementServiceImpl implements FriendsManagementService {

    private static final Log logger = LogFactory.getLog(FriendManagementServiceImpl.class);

    private final UserRepository repository;

    public FriendManagementServiceImpl(UserRepository repository) {
        this.repository = repository;
    }

    /**
     * For each pair in the users lists, create a new user if the user doesnt exist yet and
     * create a friend connection by adding each other to the list of friends.
     *
     * @param users
     * @throws FriendManagementException
     */
    @Override
    public void createFriendsConnection(List<String> users) throws FriendManagementException {
        for (String email : users) {
            User outerUser = getUser(email);

            for (String emailInner : users) {
                User innerUser = getUser(emailInner);
                if (innerUser.getEmail().equals(outerUser.getEmail())) {
                    continue; // cannot be friends with oneself
                }
                outerUser.addFriend(innerUser);
                innerUser.addFriend(outerUser);
                repository.save(innerUser);
            }
            repository.save(outerUser);
        }
    }

    private User getUser(String email) throws FriendManagementException {
        List<User> users = repository.findByEmail(email);
        validateUniqueEmail(users);
        if (users.isEmpty()) {
            return createUser(email);
        }
        else {
            return users.get(0);
        }
    }

    private void validateUniqueEmail(List<User> users) throws FriendManagementException {
        if (users.size() > 1) {
            throw new FriendManagementException("more than one user with the same email address!");
        }
    }

    private User createUser(String email) {
        User user = repository.save(User.create(email));
        logger.info("new user created: " + user.toString());
        return user;
    }


}
