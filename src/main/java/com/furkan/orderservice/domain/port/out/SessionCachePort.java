package com.furkan.orderservice.domain.port.out;

public interface SessionCachePort {
    void cacheAuthenticatedUser(String username);
}
