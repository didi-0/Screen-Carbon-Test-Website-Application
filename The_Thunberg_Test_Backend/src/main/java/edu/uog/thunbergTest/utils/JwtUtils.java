package edu.uog.thunbergTest.utils;

import io.jsonwebtoken.*;

import java.util.Date;

/**
 * @Description:
 * @Author: Xin Zhou(2756254z)
 * @Date: 2023/08/27/22:19
 */
public class JwtUtils {

    private static final String SECRET = "&/2yf5!6@$$";

    public static String createToken(String username) {
        return Jwts.builder()
                .setHeaderParam("type","JWT")
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 24 * 60 * 60 * 7))
                .signWith(SignatureAlgorithm.HS512, SECRET)
                .compact();
    }

    public static Claims parseToken(String token) {
        return Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token).getBody();
    }

}
