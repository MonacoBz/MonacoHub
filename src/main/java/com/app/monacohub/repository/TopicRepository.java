package com.app.monacohub.repository;

import com.app.monacohub.entity.Topico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface TopicRepository extends JpaRepository<Topico,Integer> {

    boolean existsByTituloAndMensaje(String titulo, String mensaje);

    @Query(value = """
    SELECT * FROM topics
    ORDER BY fecha_creacion ASC 
    LIMIT 10
   """, nativeQuery = true)
    List<Topico> getFirstTenByFecha();
}
