package com.app.monacohub.usuario.controller;

import com.app.monacohub.infra.security.DatosJwtToken;
import com.app.monacohub.infra.security.TokenService;
import com.app.monacohub.usuario.domains.UsuarioDtoData;
import com.app.monacohub.usuario.entity.Usuario;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class UsuarioController {

    private final AuthenticationManager manager;

    private final TokenService service;

    public UsuarioController(
            AuthenticationManager manager,
            TokenService service
    ){
        this.manager = manager;
        this.service = service;
    }

    @PostMapping()
    public ResponseEntity<DatosJwtToken> login(@RequestBody @Validated UsuarioDtoData data){
        var authRequest = new UsernamePasswordAuthenticationToken(data.nombre(),data.contrasenia());
        var auth = manager.authenticate(authRequest);
        var usuario = (Usuario)auth.getPrincipal();
        String token = service.generarToken(usuario);
        return ResponseEntity.ok(new DatosJwtToken(token));
    }
}
