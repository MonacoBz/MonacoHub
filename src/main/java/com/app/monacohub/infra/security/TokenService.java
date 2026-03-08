package com.app.monacohub.infra.security;

import com.app.monacohub.usuario.entity.Usuario;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.temporal.TemporalAccessor;


@Service
public class TokenService {

    @Value("${jwt.secret}")
    private String secreto;

    public String generarToken(Usuario usuario){
        return createJwt(usuario);
    }

    private String createJwt(Usuario usuario){
        try {
            Algorithm algorithm = Algorithm.HMAC256(secreto);
            String token = JWT.create()
                    .withIssuer("MonacoHub")
                    .withSubject(usuario.getNombre())
                    .withExpiresAt(generarExpiracion())
                    .sign(algorithm);
            return token;
        } catch (JWTCreationException exception){
            throw new RuntimeException("Error");
        }
    }

    private Instant generarExpiracion(){
        return LocalDateTime.now().plusHours(1).toInstant(ZoneOffset.of("-06:00"));
    }
}
