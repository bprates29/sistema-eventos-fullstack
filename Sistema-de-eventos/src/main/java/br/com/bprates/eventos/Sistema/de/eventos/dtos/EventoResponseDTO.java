package br.com.bprates.eventos.Sistema.de.eventos.dtos;

import br.com.bprates.eventos.Sistema.de.eventos.model.Inscricao;
import br.com.bprates.eventos.Sistema.de.eventos.model.StatusEvento;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public record EventoResponseDTO(
        Long id,
        String nome,
        LocalDate data,
        LocalDateTime dataInicioInsc,
        LocalDateTime dataFimInsc,
        String descricao,
        StatusEvento status,
        List<Inscricao> inscritos
) {}

