package com.app.monacohub.infra.security;

import com.app.monacohub.infra.security.exception.TokenJwtException;
import com.app.monacohub.usuario.entity.Usuario;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;



@Service
public class TokenService {

    @Value("${jwt.secret}")
    private String secreto;

    public String generarToken(Usuario usuario){
        return createJwt(usuario);
    }
    public String verificarToken(String tokenJwt){
        return verifyJwt(tokenJwt);
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
            throw new TokenJwtException("Error al generar el token JWT");
        }
    }

    private Instant generarExpiracion(){
        return LocalDateTime.now().plusHours(1).toInstant(ZoneOffset.of("-06:00"));
    }

    private String verifyJwt(String tokenJwt){
        DecodedJWT decodedJWT;
        try {
            Algorithm algorithm = Algorithm.HMAC256(secreto);
            JWTVerifier verifier = JWT.require(algorithm)
                    .withIssuer("MonacoHub")
                    .build();

            decodedJWT = verifier.verify(tokenJwt);
            return decodedJWT.getSubject();
        } catch (JWTVerificationException exception){
            throw new TokenJwtException("El token ingresado es invalido o ya caduco");
        }
    }
}
