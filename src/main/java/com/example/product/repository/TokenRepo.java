package com.example.product.repository;

import com.example.product.entity.Token;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
public class TokenRepo {
    final Logger logger = LoggerFactory.getLogger(TokenRepo.class);
    private HashOperations hashOperations;
    private final String key = "User";

    public TokenRepo(RedisTemplate redisTemplate) {
        this.hashOperations = redisTemplate.opsForHash();
    }

    public void create(Token jwt) {
        hashOperations.put(key, jwt.getJwt(), jwt);
        logger.info(String.format("User with ID %s saved", jwt.getJwt()));
    }

    public Token get(String userId) {
        return (Token) hashOperations.get(key, userId);
    }

    public List<Token> getAll(){
        Map<String, Token> map=  hashOperations.entries(key);
        return new ArrayList<>(map.values());
    }

    public void update(Token user) {
        hashOperations.put(key, user.getJwt(), user);
        logger.info(String.format("User with ID %s updated", user.getJwt()));
    }

    public void delete(String userId) {
        hashOperations.delete(key, userId);
        logger.info(String.format("User with ID %s deleted", userId));
    }

    public boolean isExist(String token){
        return hashOperations.hasKey(key,token);
    }
}
