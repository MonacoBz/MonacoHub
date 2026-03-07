package com.app.monacohub.repository;

import com.app.monacohub.entity.Topico;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TopicRepository extends JpaRepository<Topico,Integer> {

    boolean existsByTituloAndMensaje(String titulo, String mensaje);
}
