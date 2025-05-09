package top.luoyuanxiang.api;

import io.jsonwebtoken.Claims;
import top.luoyuanxiang.api.utils.JwtUtils;

/**
 * @author luoyuanxiang
 */
public class JwtTest {

    public static void main(String[] args) {
        String jwt = JwtUtils.JwtBuilder
                .builder()
                .secretKey("luoyuanxiang")
                .subject("luoyuanxiang")
                .ttlMillis(259200000L)
                .build();
        System.out.println(jwt);
        Claims luoyuanxiang = JwtUtils.parseJWT("luoyuanxiang", jwt);
        System.out.println(luoyuanxiang);
    }
}
