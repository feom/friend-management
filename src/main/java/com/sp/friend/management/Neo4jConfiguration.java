package com.sp.friend.management;


import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;

@Configuration
@EnableNeo4jRepositories
@EntityScan(basePackages = "com.sp.friend.management.domain")
public class Neo4jConfiguration {
}
