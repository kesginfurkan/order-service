package com.furkan.orderservice.infrastructure.adapter.out.redis;

import com.furkan.orderservice.domain.port.out.SessionCachePort;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.time.Duration;

@Component
@RequiredArgsConstructor
public class RedisSessionCacheAdapter implements SessionCachePort {

    private static final Duration SESSION_TTL = Duration.ofMinutes(5);
    private static final String SESSION_KEY_PREFIX = "session:user:";

    private final StringRedisTemplate stringRedisTemplate;

    @Override
    public void cacheAuthenticatedUser(String username) {
        String key = SESSION_KEY_PREFIX + username;

        stringRedisTemplate.opsForValue().set(
          key,
          username,
          SESSION_TTL
        );
    }
}
