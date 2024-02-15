package com.wang;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

//@SpringBootTest
class TliasWebManagementApplicationTests {

    /*
    * 生成JWT
    */
    @Test
    public void testGenJwt(){
        Map<String,Object> claims = new HashMap<>();
        claims.put("id",1);
        claims.put("name","tom");

        String jwt = Jwts.builder()
                .signWith(SignatureAlgorithm.HS256,"wangzhiwei")
                .setClaims(claims)
                .setExpiration(new Date(System.currentTimeMillis()))
                .compact();
        System.out.println(jwt);
    }

    /*
    * 解析JWT
    */
    @Test
    public void testParseJwt(){
        Claims claims = Jwts.parser()
                .setSigningKey("wangzhiwei")
                .parseClaimsJws("eyJhbGciOiJIUzI1NiJ9.eyJuYW1lIjoidG9tIiwiaWQiOjEsImV4cCI6MTcwNzExNjM5Mn0.xMnqDQE9r-uhLLwLBDLx03B-21AD6VKHzcwIm_ZVPwQ")
                .getBody();
        System.out.println(claims);
    }

}
