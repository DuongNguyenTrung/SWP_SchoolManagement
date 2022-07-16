package com.swp.SchoolManagement.config.auth;

import java.time.Duration;
import java.util.Date;
import java.util.HashMap;

import org.apache.tomcat.util.json.JSONParser;
import org.apache.tomcat.util.json.ParseException;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.swp.SchoolManagement.model.CustomUserDetails;

import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class JwtTokenProvider {
    @Value("${SECRET_JWT}")
    private String JWT_SECRET;

    private int JWT_EXPIRATION = 30;

    public String generateToken(CustomUserDetails userDetails) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + Duration.ofMinutes(JWT_EXPIRATION).toMillis());
        JSONObject obj = new JSONObject();
        obj.put("username", userDetails.getAccount().getEmail());
        obj.put("role", userDetails.getAccount().getRole());
        // Tạo chuỗi json web token từ email,role của user.
        return Jwts.builder()
                .setSubject(obj.toJSONString())
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(SignatureAlgorithm.HS512, JWT_SECRET)
                .compact();
    }

    // Lấy thông tin user từ jwt
    public String getUsernameFromJWT(String token) {
        String username ;
        Claims claims = Jwts.parser()
                .setSigningKey(JWT_SECRET)
                .parseClaimsJws(token)
                .getBody();
        JSONParser jsonParser = new JSONParser(claims.getSubject());
        try {
            HashMap map = (HashMap) jsonParser.parse();
            return (String) map.get("username");
        } catch (ParseException e) {
            username = null;
        }
        return username;
    }

    public boolean validateToken(String authToken) {
        try {
            Jwts.parser().setSigningKey(JWT_SECRET).parseClaimsJws(authToken);
            return true;
        } catch (MalformedJwtException ex) {
            log.error("Invalid JWT token");
        } catch (ExpiredJwtException ex) {
            log.error("Expired JWT token");
        } catch (UnsupportedJwtException ex) {
            log.error("Unsupported JWT token");
        } catch (IllegalArgumentException ex) {
            log.error("JWT claims string is empty.");
        }
        return false;
    }

}
