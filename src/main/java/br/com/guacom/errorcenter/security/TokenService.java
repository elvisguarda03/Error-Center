package br.com.guacom.errorcenter.security;

import br.com.guacom.errorcenter.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class TokenService {
    @Value("${error.center.expiration}")
    private String expiration;

    @Value("${error.center.secret}")
    private String secret;

    public String generateToken(Authentication authenticate) {
        User user = (User) authenticate.getPrincipal();

        Date today = new Date();
        Date expirationDate = new Date(today.   getTime() + Long.parseLong(expiration));

        return Jwts.builder()
                .setIssuer("API Error Center")
                .setSubject(user.getId().toString())
                .setIssuedAt(today)
                .setExpiration(expirationDate)
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }

    public boolean isValid(String token) {
        try {
            Jwts.parser().setSigningKey(this.secret)
                    .parseClaimsJws(token);
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    public Long getIdUser(String token) {
        Claims claims = Jwts.parser().setSigningKey(this.secret)
                .parseClaimsJws(token)
                .getBody();
        return Long.parseLong(claims.getSubject());
    }
}
