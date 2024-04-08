package edu.viterbi.staybooking.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;
import java.util.HashMap;
import io.jsonwebtoken.Claims;

@Component
public class JwtUtil {
    @Value("${jwt.secret}") // used to inject the value of the jwt.secret property from the application's configuration into the secret field of this class. This secret key is used for signing and verifying JWTs.
    private String secret;

    // generate a new JWT for a given subject
    public String generateToken(String subject) {
        return Jwts.builder()
                .setClaims(new HashMap<>()) //  initialize the JWT with an empty claims set. Claims are additional data can be included in the JWT
                .setSubject(subject)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24)) // Sets the expiration time of the JWT to 24 hours from the current time
                .signWith(SignatureAlgorithm.HS256, secret) // Signs the JWT using the HS256 signature algorithm and the secret key
                .compact(); // Builds the JWT and serializes it to a compact, URL-safe string
    }
    // helper method that parses the given JWT and extracts the claims. It uses the secret key to verify the signature.
    private Claims extractClaims(String token) {
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
    }
    // retrieve the subject from the JWT
    public String extractUsername(String token) {
        return extractClaims(token).getSubject();
    }

    public Date extractExpiration(String token) {
        return extractClaims(token).getExpiration();
    }

    public Boolean validateToken(String token) {
        return extractExpiration(token).after(new Date());
    }


}

