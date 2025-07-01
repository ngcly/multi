package com.cn.config;

import cn.hutool.core.date.DateField;
import cn.hutool.core.date.DateUtil;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.extra.spring.SpringUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.jwt.JWT;
import cn.hutool.jwt.JWTUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.Map;

/**
 * JWT工具类
 * @author LinChen
 */
@Component
public class JwtTokenHandler {
    @Value("${jwt.secret}")
    private String secret;
    @Value("${jwt.expiration}")
    private Integer expiration;

    private static final String BEARER = "Bearer ";

    /**
     * 生成 user token
     * @param user 用户信息
     * @return jwt字符串
     */
    public <T extends UserDetails> String generateToken(T user) {
        final Date now = new Date();
        ObjectMapper mapper = SpringUtil.getBean(ObjectMapper.class);
        Map<String, ?> map = mapper.convertValue(user, Map.class);
        //创建jwt
        return JWT.create()
                .setSubject(user.getUsername())
                .addPayloads(map)
                .setIssuedAt(now)
                .setExpiresAt(DateUtil.offsetSecond(now,expiration))
                .setKey(SecureUtil.md5(secret).getBytes())
                .sign();
    }

    public String generateToken(String str) {
        return generateToken(str,expiration);
    }

    public String generateToken(String str, Integer expTime) {
        return generateToken(str,expTime,DateField.SECOND);
    }

    public String generateToken(String str, Integer expTime, DateField dateField) {
        final Date now =new Date();
        return JWT.create()
                .setSubject(str)
                .setIssuedAt(now)
                .setExpiresAt(DateUtil.offset(now,dateField,expTime))
                .setKey(SecureUtil.md5(secret).getBytes())
                .sign();
    }

    /**
     * 刷新token
     * @param token token字符串
     * @return jwt字符串
     */
    public String refreshToken(String token) {
        final Date now = new Date();
        return JWTUtil.parseToken(token)
                .setIssuedAt(now)
                .setExpiresAt(DateUtil.offsetSecond(now,expiration))
                .sign();
    }

    /**
     * 验证jwt
     * @param jwt jwt对象
     * @return boolean
     */
    public boolean verify(JWT jwt){
        return jwt.setKey(SecureUtil.md5(secret).getBytes()).validate(0L);
    }

    /**
     * 从Http请求里获取token
     * @param request 请求
     * @return String
     */
    public static String getToken(HttpServletRequest request){
        String jwtToken = request.getHeader(HttpHeaders.AUTHORIZATION);
        if(StringUtils.hasLength(jwtToken) && jwtToken.startsWith(BEARER)){
            return jwtToken.substring(BEARER.length());
        }
        return null;
    }

    public UserDetails getUserDetailsFromToken(JWT jwt) {
        JSONObject jsonObject = jwt.getPayload().getClaimsJson();
        String username =jsonObject.get("username").toString();
        String[] authorities = jsonObject.getJSONArray("authorities").stream().map(Object::toString).toArray(String[]::new);
        Boolean enabled = jsonObject.get("enabled", Boolean.class);
        Boolean accountNonLocked = jsonObject.get("accountNonLocked", Boolean.class);
        Boolean accountNonExpired = jsonObject.get("accountNonExpired", Boolean.class);
        Boolean credentialsNonExpired = jsonObject.get("credentialsNonExpired", Boolean.class);

        return User.builder()
                .username(username)
                .password("") // 不需要密码
                .disabled(!enabled)
                .accountLocked(!accountNonLocked)
                .accountExpired(!accountNonExpired)
                .credentialsExpired(!credentialsNonExpired)
                .authorities(authorities)
                .build();
    }

}
