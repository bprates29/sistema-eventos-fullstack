package br.com.bprates.eventos.Sistema.de.eventos.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public record Inscricao(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        Long id,

        @ManyToOne
        Usuario usuario,

        @ManyToOne
        Evento evento,

        LocalDateTime data
) {
    public Inscricao {
        // Valor padrão para o campo data
        if (data == null) {
            data = LocalDateTime.now();
        }
    }
}
