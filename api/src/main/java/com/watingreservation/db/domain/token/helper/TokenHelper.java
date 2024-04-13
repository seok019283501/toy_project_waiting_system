package com.watingreservation.db.domain.token.helper;

import com.watingreservation.db.common.error.TokenErrorCode;
import com.watingreservation.db.common.exception.ApiException;
import com.watingreservation.db.domain.token.model.TokenDto;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Component
public class TokenHelper implements TokenHelperIfs{

    @Value("${token.secret.key}")
    private String secretKey;
    @Value("${token.access-token.plus-hour}")
    private Long accessTokenPlusHour;
    @Value("${token.refresh-token.plus-hour}")
    private Long refreshTokenPlusHour;

    @Override
    public TokenDto issueAccessToken(Map<String, Object> data) {
        var expiredLocalDateTime = LocalDateTime.now().plusHours(accessTokenPlusHour);
        var expiredAt = Date.from(expiredLocalDateTime.atZone(ZoneId.systemDefault()).toInstant());
        var key = Keys.hmacShaKeyFor(secretKey.getBytes());
        var jwtToken = Jwts.builder()
                .signWith(key, SignatureAlgorithm.HS256)
                .setClaims(data)
                .setExpiration(expiredAt)
                .compact();
        return TokenDto.builder()
                .expriedAt(expiredLocalDateTime)
                .token(jwtToken)
                .build();
    }

    @Override
    public TokenDto issueRefreshToken(Map<String, Object> data) {
        var expiredLocalDateTime = LocalDateTime.now().plusDays(refreshTokenPlusHour);
        var expiredAt = Date.from(expiredLocalDateTime.atZone(ZoneId.systemDefault()).toInstant());
        var key = Keys.hmacShaKeyFor(secretKey.getBytes());
        var jwtToken = Jwts.builder()
                .signWith(key, SignatureAlgorithm.HS256)
                .setClaims(data)
                .setExpiration(expiredAt)
                .compact();
        return TokenDto.builder()
                .expriedAt(expiredLocalDateTime)
                .token(jwtToken)
                .build();
    }

    @Override
    public Map<String, Object> validationTokenWithThrow(String token) {

        var key = Keys.hmacShaKeyFor(secretKey.getBytes());
        var parser = Jwts.parserBuilder()
                .setSigningKey(key)
                .build();

        try {
            var result = parser.parseClaimsJws(token);
            return new HashMap<String,Object>(result.getBody());
        }catch (Exception e){
            if(e instanceof SignatureException){
                //토큰이 유효하지 않을 때
                throw new ApiException(TokenErrorCode.INVALID_TOKEN,e);
            }else if(e instanceof ExpiredJwtException){
                //토큰 만료
                throw new ApiException(TokenErrorCode.EXPIRED_TOKEN,e);
            }else{
                //그 외
                throw new ApiException(TokenErrorCode.TOKEN_EXCEPTUON,e);
            }
        }

    }
}
