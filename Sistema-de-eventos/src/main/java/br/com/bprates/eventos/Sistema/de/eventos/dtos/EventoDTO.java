package br.com.bprates.eventos.Sistema.de.eventos.dtos;

import br.com.bprates.eventos.Sistema.de.eventos.model.StatusEvento;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record EventoDTO(
        @NotBlank(message = "Evento sempre deve ter um nome")
        String nome,

        @NotNull(message = "Evento sempre deve ter uma data")
        LocalDate data,

        @NotNull(message = "Evento sempre deve ter uma data de início das inscrições")
        LocalDateTime dataInicioInsc,

        @NotNull(message = "Evento sempre deve ter uma data de fim das inscrições")
        LocalDateTime dataFimInsc,

        String descricao,

        StatusEvento status
) {}
