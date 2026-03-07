package com.app.monacohub.entity;

import com.app.monacohub.domains.Status;
import com.app.monacohub.domains.TopicoDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "topics")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Topico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;

    private String mensaje;

    private LocalDateTime fecha_creacion;

    private Status status;

    private String autor;

    private String curso;

    public Topico(TopicoDto data){
        this.titulo = data.titulo();
        this.mensaje = data.mensaje();
        this.autor = data.autor();
        this.curso = data.curso();
    }

}
