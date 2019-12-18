package com.sp.friend.management.service;

import com.sp.friend.management.FriendsManagementException;
import com.sp.friend.management.domain.User;
import com.sp.friend.management.repository.UserRepository;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FriendsManagementServiceImpl implements FriendsManagementService {

    private static final Log logger = LogFactory.getLog(FriendsManagementServiceImpl.class);

    private final UserRepository repository;

    public FriendsManagementServiceImpl(UserRepository repository) {
        this.repository = repository;
    }

    /**
     * For each pair in the users lists, create a new user if the user doesnt exist yet and
     * create a friend connection by adding each other to the list of friends.
     *
     * @param users
     * @throws FriendsManagementException
     */
    @Override
    public void createFriendsConnection(List<String> users) throws FriendsManagementException {
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

    @Override
    public List<String> retrieveFriendsList(String email) throws FriendsManagementException {
        User user = getUser(email);
        return user.getFriends().stream().map(User::getEmail).collect(Collectors.toList());
    }

    private User getUser(String email) throws FriendsManagementException {
        List<User> users = repository.findByEmail(email);
        validateUniqueEmail(users);
        if (users.isEmpty()) {
            return createUser(email);
        }
        else {
            return users.get(0);
        }
    }

    private void validateUniqueEmail(List<User> users) throws FriendsManagementException {
        if (users.size() > 1) {
            throw new FriendsManagementException("more than one user with the same email address!");
        }
    }

    private User createUser(String email) {
        User user = repository.save(User.create(email));
        logger.info("new user created: " + user.toString());
        return user;
    }


}
