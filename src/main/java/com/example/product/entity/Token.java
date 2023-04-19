package com.example.product.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@RedisHash(value = "jwt", timeToLive = 1000) //time expire in second
public class Token implements Serializable {
    @Id
    private String jwt;
    private boolean is_expired;
    private boolean is_provoked;

}
