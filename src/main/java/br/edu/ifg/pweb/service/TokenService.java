package br.edu.ifg.pweb.service;

import br.edu.ifg.pweb.entity.User;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {
    public String gerarToken(User user) {
        return JWT.create()
                .withIssuer("Produtos")
                .withSubject(user.getUsername())
                .withClaim("id", user.getId())
                .withClaim("name", user.getName())
                .withClaim("role", user.getRole())
                .withExpiresAt(LocalDateTime.now()
                        .plusMinutes(30)
                        .toInstant(ZoneOffset.of("-03:00"))
                ).sign(Algorithm.HMAC256("secreta"));
    }


    public String getSubject(String token) {
        return JWT.require(Algorithm.HMAC256("secreta"))
                .withIssuer("Produtos")
                .build().verify(token.substring(7)).getSubject();

    }
}
