package com.vtortsev.quizapp.sequrity.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.function.Function;

@Service
public class JwtService {
    private static final String SECRET_KEY = "7CDC94C3BDBD1A6889B96FF44CD7A"; // типо что-то умное

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsTFunction) {
        final Claims claims = extractAllClaims(token);
        return claimsTFunction.apply(claims);
    }

    // претензии
    private Claims extractAllClaims(String token) {
        return Jwts
                .parserBuilder()               // создает построитель парсера для JWT.
                .setSigningKey(getSignInkey()) // устанавливает ключ подписи для проверки подписи токена
                .build()                       // парсит переданный токен и проверяет его подпись, используя установленный ключ подписи
                .parseClaimsJws(token)         // извлекает тело токена, которое представляет собой набор утверждений (claims) в формате JSON.
                .getBody();                    // извлекает тело токена, которое представляет собой набор утверждений (claims) в формате JSON.
    }

    private Key getSignInkey() {
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(SECRET_KEY));
    }


}
