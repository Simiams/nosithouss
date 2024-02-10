package fr.arosaje.nosithouss.services;

import fr.arosaje.nosithouss.models.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.util.Base64;
import java.util.Date;
import java.util.Map;
import java.util.function.Function;

@AllArgsConstructor
@Service
public class JwtService {

    private final String ENCRIPTION_KEY = "4735c48302328b72972935d4ed9c3b777ccf98bf094a40926e6134a104f9b7a3"; //todo properties file / .env most safe...
    private UserService userService;

    public Map<String, String> generate(String username) {
        User user = (User) userService.loadUserByUsername(username);
        return this.generateJwt(user);
    }

    public String extractUsername(String token) {
        return this.getClaim(token, Claims::getSubject);
    }

    public boolean isTokenExpired(String token) {
        Date expirationDate = getClaim(token, Claims::getExpiration);
        return expirationDate.before(new Date());
    }

    private <T> T getClaim(String token, Function<Claims, T> function) {
        Claims claims = getAllClaims(token);
        return function.apply(claims);
    }

    private Claims getAllClaims(String token) {
        return Jwts.parser()
                .setSigningKey(getKey())
                .parseClaimsJws(token)
                .getBody();
    }

    private Map<String, String> generateJwt(User user) {
        final long currentTime = System.currentTimeMillis();
        final long expirationTime = currentTime + 30 * 60 * 1000;

        final String bearer = Jwts.builder()
                .setIssuedAt(new Date(currentTime))
                .setExpiration(new Date(expirationTime))
                .setClaims(Map.of("roles", user.getRoles(), Claims.EXPIRATION, new Date(expirationTime), Claims.SUBJECT, user.getUsername()))
                .signWith(SignatureAlgorithm.HS256, getKey())
                .compact();

        return Map.of("bearer", bearer);
    }

    private Key getKey() {
        return new SecretKeySpec(Base64.getDecoder().decode(ENCRIPTION_KEY),
                          SignatureAlgorithm.HS256.getJcaName());
    }
}