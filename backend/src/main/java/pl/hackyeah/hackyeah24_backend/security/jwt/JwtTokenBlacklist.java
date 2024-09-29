package pl.hackyeah.hackyeah24_backend.security.jwt;

import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class JwtTokenBlacklist {
    private final Set<String> invalidatedTokens = ConcurrentHashMap.newKeySet();

    public void invalidateToken(String token) {
        invalidatedTokens.add(token);
    }

    public boolean isTokenInvalidated(String token) {
        return invalidatedTokens.contains(token);
    }

    public Set<String> getInvalidatedTokens() {
        return invalidatedTokens;
    }
}
