package org.example.fullbankingapplication.Configuration;


import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtTokenProvider {


    public Long JWTExpiration;
    public String jwtToken;

    public JwtTokenProvider(@Value("${app.jwt-expiration}") Long JWTExpiration, @Value("${app.jwt-secret}") String jwtToken) {
        this.JWTExpiration = JWTExpiration;
        this.jwtToken = jwtToken;
    }

    public String generateToken(Authentication authentication) {
        String username = authentication.getName();
        Date currentDate = new Date();
        Date expirationDate = new Date(currentDate.getTime() + JWTExpiration);
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(currentDate)
                .setExpiration(expirationDate)
                .signWith(key())
                .compact();
    }


    private Key key() {
        byte[] bytes = Decoders.BASE64.decode(jwtToken);
        return Keys.hmacShaKeyFor(bytes);
    }

    public String getUsername(String token) {
        return Jwts.parser()
                .setSigningKey(key())
                .parseClaimsJws(token)
                .getBody()
                .getSubject(); // ou une autre claim si tu stockes le username ailleurs
    }

               /*Tu donnes ici le token JWT reçu du client (souvent dans l'en-tête HTTP).

                 Cette méthode va :

                Vérifier la signature du token.

                Vérifier que le token n’est pas expiré.

                Lever une exception si le token est invalide.*/


    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(key())
                    .build()
                    .parseClaimsJws(token);
            return true;
        } catch (ExpiredJwtException | UnsupportedJwtException | MalformedJwtException | SignatureException |
                 IllegalArgumentException e) {
            e.printStackTrace();
        }
        return false;
    }
}
