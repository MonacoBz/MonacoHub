package com.app.monacohub.usuario.repository;

import com.app.monacohub.usuario.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface UsuarioRepository extends JpaRepository<Usuario,Long> {

    UserDetails findByNombre(String nombre);
}
