package com.app.monacohub.infra.security;

import com.app.monacohub.usuario.repository.UsuarioRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class SecurityFilter extends OncePerRequestFilter {

    private final UsuarioRepository repository;

    private final TokenService service;

    public SecurityFilter(
            UsuarioRepository repository,
            TokenService service
    ){
        this.repository = repository;
        this.service = service;
    }
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authentication = request.getHeader("Authorization");
        if(authentication == null || !authentication.startsWith("Bearer ")){
            filterChain.doFilter(request,response);
            return;
        }
        var token = authentication.split(" ")[1];
        var nombreJwt = service.verificarToken(token);
        var user = repository.findByNombre(nombreJwt);
        var authenticationUser = new UsernamePasswordAuthenticationToken(user,null,user.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authenticationUser);
        filterChain.doFilter(request,response);
    }
}
