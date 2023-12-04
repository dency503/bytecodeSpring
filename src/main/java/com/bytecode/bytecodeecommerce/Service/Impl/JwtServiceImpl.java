package com.bytecode.bytecodeecommerce.Service.Impl;

import com.bytecode.bytecodeecommerce.Service.JwtService;
import com.bytecode.bytecodeecommerce.models.Usuario;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtServiceImpl implements JwtService {
    @Value("${token.signing.key}")
    private String jwtSigningKey;
    private static final long TOKEN_EXPIRATION_TIME_MS = 1000 * 60 * 24;
    @Override
    public String extractUserName(String token) {


        try {
            Claims claims = extractAllClaims(token);

            // Extract email from the claims
            String email = claims.get("email", String.class);

            if (email != null) {
                return email.trim();
            }
        } catch (SignatureException e) {
            // Handle the exception or log it, depending on your requirements

        }

        return null; // Return null if extraction fails
    }

    @Override
    public String generateToken(UserDetails userDetails) {
        Usuario user = (Usuario) userDetails;

        return generateToken(new HashMap<>(), user);
    }

    @Override
    public boolean isTokenValid(String token, UserDetails userDetails) {
        Usuario user = (Usuario) userDetails;
        final String userName = extractUserName(token);
        return (userName.equals(user.getEmail())) && !isTokenExpired(token);
    }

    private <T> T extractClaim(String token, Function<Claims, T> claimsResolvers) {
        final Claims claims = extractAllClaims(token);
        return claimsResolvers.apply(claims);
    }

    private String generateToken(Map<String, Object> extraClaims, Usuario userDetails) {
        return Jwts.builder().claims(extraClaims).claim("email", userDetails.getEmail())
                .claim("role", userDetails.getRole()).issuedAt(new Date(System.currentTimeMillis())).expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 24))
                .signWith(getSigningKey(), SignatureAlgorithm.HS256).compact();
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parser().setSigningKey(getSigningKey()).build().parseSignedClaims(token).getPayload();
    }
    @Override
    public String extractEmail(String token) {
        try {
            Claims claims = extractAllClaims(token);

            // Extract email from the claims
            String email = claims.get("email", String.class);

            if (email != null) {
                return email.trim();
            }
        } catch (SignatureException e) {
            // Handle the exception or log it, depending on your requirements

        }

        return null; // Return null if extraction fails
    }
    private Key getSigningKey() {
        byte[] keyBytes = Decoders.BASE64.decode(jwtSigningKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
