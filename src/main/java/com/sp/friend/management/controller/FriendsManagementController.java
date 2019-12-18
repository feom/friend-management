package com.sp.friend.management.controller;

import com.sp.friend.management.FriendsManagementException;
import com.sp.friend.management.dto.FriendsConnectionRequest;
import com.sp.friend.management.dto.FriendsListRequest;
import com.sp.friend.management.dto.FriendsResponseBody;
import com.sp.friend.management.service.FriendsManagementService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class FriendsManagementController {

    private static final Log logger = LogFactory.getLog(FriendsManagementController.class);

    private final FriendsManagementService friendsManagementService;

    public FriendsManagementController(FriendsManagementService friendsManagementService) {
        this.friendsManagementService = friendsManagementService;
    }

    // TODO for all services dto input validations

    @PostMapping("/friends")
    public ResponseEntity<FriendsResponseBody> createFriendsConnection(@RequestBody FriendsConnectionRequest friendsConnectionRequest) {
        List<String> friends = friendsConnectionRequest.getFriends();

        try {
            friendsManagementService.createFriendsConnection(friends);
            return ResponseEntity.ok().body(FriendsResponseBody.builder(true).build());
        } catch (FriendsManagementException e) {
            e.printStackTrace();
            FriendsResponseBody friendsResponseBody = FriendsResponseBody.builder(false).withStatusMessage(e.getMessage()).build();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(friendsResponseBody);
        }
    }

    @PostMapping("/friends/list")
    public ResponseEntity<FriendsResponseBody> retrieveFriendsList(@RequestBody FriendsListRequest friendsListRequest) {
        logger.info("friends list ");
        try {
            List<String> friendsList = friendsManagementService.retrieveFriendsList(friendsListRequest.getEmail());
            FriendsResponseBody responseBody = FriendsResponseBody.builder(true).withFriendsList(friendsList).build();
            return ResponseEntity.ok().body(responseBody);
        } catch (FriendsManagementException e) {
            e.printStackTrace();
            FriendsResponseBody friendsResponseBody = FriendsResponseBody.builder(false).withStatusMessage(e.getMessage()).build();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(friendsResponseBody);
        }
    }

    @ExceptionHandler({RuntimeException.class})
    public final ResponseEntity<FriendsResponseBody> handleExceptions(RuntimeException ex) {
        logger.error("exception handler invoked: " + ex.toString());
        FriendsResponseBody friendsResponseBody = FriendsResponseBody.builder(false).withStatusMessage(ex.toString()).build();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(friendsResponseBody);
    }

}
