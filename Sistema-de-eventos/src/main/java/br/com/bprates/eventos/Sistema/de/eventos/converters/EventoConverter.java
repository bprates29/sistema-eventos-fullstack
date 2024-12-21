package br.com.bprates.eventos.Sistema.de.eventos.converters;

import br.com.bprates.eventos.Sistema.de.eventos.dtos.EventoDTO;
import br.com.bprates.eventos.Sistema.de.eventos.dtos.EventoResponseDTO;
import br.com.bprates.eventos.Sistema.de.eventos.model.Evento;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Component
public class EventoConverter {

    public Evento toEvento(EventoDTO dto) {
        return new Evento(
                null, // ID será gerado automaticamente pelo banco
                dto.nome(),
                dto.data(),
                dto.dataInicioInsc(),
                dto.dataFimInsc(),
                dto.descricao(),
                dto.status(),
                Collections.emptyList() // Lista vazia para inscritos
        );
    }

    public EventoResponseDTO toEventoResponseDTO(Evento evento) {
        return new EventoResponseDTO(
                evento.getId(),
                evento.getNome(),
                evento.getData(),
                evento.getDataInicioInsc(),
                evento.getDataFimInsc(),
                evento.getDescricao(),
                evento.getStatus(),
                evento.getInscritos()
        );
    }
}

