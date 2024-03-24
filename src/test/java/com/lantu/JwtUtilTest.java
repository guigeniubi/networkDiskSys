//package com.lantu;
//
//import com.lantu.common.utils.JwtUtil;
//import com.lantu.sys.entity.User;
//import io.jsonwebtoken.Claims;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.context.TestComponent;
//
//@SpringBootTest
//public class JwtUtilTest {
//    @Autowired
//    private JwtUtil jwtUtil;
//    @Test
//    public void testCreateJwt(){
//        User user =new User();
//        user.setUsername("zhangsan");
//        user.setPhone("123568151");
//        String token=jwtUtil.createToken(user);
//        System.out.println(token);
//    }
//    @Test
//    public void testParseJwt(){
//        String token="eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIyZWNlYzllMC1mZDNmLTRlNGQtOWFkMC04NjM3NWQxNTM2YWIiLCJzdWIiOiJ7XCJwaG9uZVwiOlwiMTIzNTY4MTUxXCIsXCJ1c2VybmFtZVwiOlwiemhhbmdzYW5cIn0iLCJpc3MiOiJzeXN0ZW0iLCJpYXQiOjE2OTQ1OTE0NzcsImV4cCI6MTY5NDU5MzI3N30.R3eXe3hj1RI4DiATiMU6Vj9WiuIf1sg9qg0ZouaFy3k";
//        Claims claims=jwtUtil.parseToken(token);
//        System.out.println(claims );
//    }
//    @Test
//    public void testParseJwt2(){
//        String token="eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIyZWNlYzllMC1mZDNmLTRlNGQtOWFkMC04NjM3NWQxNTM2YWIiLCJzdWIiOiJ7XCJwaG9uZVwiOlwiMTIzNTY4MTUxXCIsXCJ1c2VybmFtZVwiOlwiemhhbmdzYW5cIn0iLCJpc3MiOiJzeXN0ZW0iLCJpYXQiOjE2OTQ1OTE0NzcsImV4cCI6MTY5NDU5MzI3N30.R3eXe3hj1RI4DiATiMU6Vj9WiuIf1sg9qg0ZouaFy3k";
//        User user=jwtUtil.parseToken(token,User.class);
//        System.out.println(user );
//    }
//}
