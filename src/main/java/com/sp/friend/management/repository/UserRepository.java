package com.sp.friend.management.repository;


import com.sp.friend.management.domain.User;
import org.springframework.data.neo4j.repository.Neo4jRepository;

import java.util.List;

public interface UserRepository extends Neo4jRepository<User, Long>, CustomizedUserRepository {

    List<User> findByEmail(String email);

}
