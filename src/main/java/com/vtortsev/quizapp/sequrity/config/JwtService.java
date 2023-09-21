package com.vtortsev.quizapp.sequrity.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtService {
    private static final String SECRET_KEY = "7CDC94C3BDBD1A6889B96FF44CD7A"; // типо что-то умное

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    // Этот метод обобщенный и принимает тип T
    // claimsTFunction: функция, которая принимает объект Claims (утверждения из токена) и возвращает значение типа T
    // extractAllClaims(token): извлекает все утверждения из токена
    // claimsTFunction.apply(claims): применяет переданную функцию claimsTFunction к объекту Claims (утверждениям) для извлечения значения
    public <T> T extractClaim(String token, Function<Claims, T> claimsTFunction) {
        final Claims claims = extractAllClaims(token);
        return claimsTFunction.apply(claims);
    }
    public String generateToken(UserDetails userDetails) {
        return generateToken(new HashMap<>(), userDetails);
    }
    public boolean isTokenValid(String token, UserDetails userDetails) {
        final String userName = extractUsername(token);
        return (userName.equals(userDetails.getUsername())) && !isTokenExpired(token);
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public String generateToken(
            Map<String, Object> extraClaims,
            UserDetails userDetails
    ) {
        return Jwts
                .builder()
                .setClaims(extraClaims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()*1000*60*24))
                .signWith(getSignInkey(), SignatureAlgorithm.HS256)
                .compact();
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
