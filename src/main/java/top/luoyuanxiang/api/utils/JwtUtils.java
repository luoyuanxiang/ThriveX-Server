package top.luoyuanxiang.api.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class JwtUtils {

    private static final int MIN_SECRET_KEY_LENGTH = 32;

    private static final String KEY_PADDING_CHAR = "0";

    /**
     * 验证并解析 JWT Token
     *
     * @param secretKey JWT 秘钥，必须保密存储
     * @param token     要解析的 JWT 字符串
     * @return 解析后的 Claims 对象，包含用户信息和元数据
     * @throws RuntimeException 如果解析失败或签名无效
     */
    public static Claims parseJWT(String secretKey, String token) {
        // 参数验证
        if (secretKey == null || secretKey.trim().isEmpty()) {
            throw new IllegalArgumentException("Secret key cannot be null or empty");
        }
        if (token == null || token.trim().isEmpty()) {
            throw new IllegalArgumentException("Token cannot be null or empty");
        }
        try {
            // 解析JWT
            return Jwts.parser()
                    .verifyWith(getSecretKey(secretKey))
                    .build()
                    .parseSignedClaims(token)
                    .getPayload();
        } catch (Exception e) {
            throw new IllegalArgumentException("Invalid token: " + e.getMessage(), e);
        }
    }

    /**
     * 获取密钥
     *
     * @param secretKey 密钥
     * @return {@link SecretKey }
     */
    private static SecretKey getSecretKey(String secretKey) {
        // 验证输入
        if (secretKey == null || secretKey.trim().isEmpty()) {
            throw new IllegalArgumentException("Secret key cannot be null or empty");
        }

        // 确保密钥长度符合要求
        if (secretKey.length() < MIN_SECRET_KEY_LENGTH) {
            secretKey = String.format("%-" + MIN_SECRET_KEY_LENGTH + "s", secretKey).replace(' ', KEY_PADDING_CHAR.charAt(0));
        }
        return Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));
    }

    public static class JwtBuilder {

        private String secretKey;
        private long ttlMillis;
        private final Map<String, Object> claims = new HashMap<>();
        private Date issuedAt;
        private String subject;

        public JwtBuilder secretKey(String secretKey) {
            this.secretKey = secretKey;
            return this;
        }

        /**
         * 过期时间
         *
         * @param ttlMillis TTL 米利斯
         * @return {@link JwtBuilder }
         */
        public JwtBuilder ttlMillis(long ttlMillis) {
            this.ttlMillis = System.currentTimeMillis() + ttlMillis;
            return this;
        }

        /**
         * 添加自定义内容
         *
         * @param name  名字
         * @param value 价值
         * @return {@link JwtBuilder }
         */
        public JwtBuilder addClaims(String name, Object value) {
            this.claims.put(name, value);
            return this;
        }

        /**
         * 发行于
         *
         * @param issuedAt 发行于
         * @return {@link JwtBuilder }
         */
        public JwtBuilder issuedAt(Date issuedAt) {
            this.issuedAt = issuedAt;
            return this;
        }

        /**
         * 主题
         *
         * @param subject 主题
         * @return {@link JwtBuilder }
         */
        public JwtBuilder subject(String subject) {
            this.subject = subject;
            return this;
        }

        /**
         * 构建器
         *
         * @return {@link JwtBuilder }
         */
        public static JwtBuilder builder() {
            return new JwtBuilder();
        }

        /**
         * 构建
         *
         * @return {@link String }
         */
        public String build() {
            // 生成过期时间
            Date expiration = new Date(ttlMillis);
            io.jsonwebtoken.JwtBuilder builder = Jwts.builder();
            if (Objects.nonNull(issuedAt)) {
                builder.issuedAt(issuedAt);
            }
            if (Objects.nonNull(subject)) {
                builder.subject(subject);
            }
            if (Objects.isNull(issuedAt)) {
                builder.issuedAt(new Date());
            }
            builder.claims(claims);
            builder.expiration(expiration);
            builder.signWith(getSecretKey(secretKey));
            // 构建JWT
            return builder.compact();
        }
    }
}
