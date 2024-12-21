package br.com.bprates.eventos.Sistema.de.eventos.service;

import br.com.bprates.eventos.Sistema.de.eventos.converters.EventoConverter;
import br.com.bprates.eventos.Sistema.de.eventos.dtos.EventoDTO;
import br.com.bprates.eventos.Sistema.de.eventos.dtos.EventoResponseDTO;
import br.com.bprates.eventos.Sistema.de.eventos.exceptions.NotFoundException;
import br.com.bprates.eventos.Sistema.de.eventos.model.Evento;
import br.com.bprates.eventos.Sistema.de.eventos.repository.EventoRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class EventoService {

    private static final String EVENTO_NOT_FOUND_MESSAGE = "Evento não encontrado!";

    private final EventoRepository repository;
    private final EventoConverter converter;

    public EventoService(EventoRepository repository, EventoConverter converter) {
        this.repository = repository;
        this.converter = converter;
    }

    public Page<EventoResponseDTO> listar(String nomeEvento, Pageable paginacao) {
        Page<Evento> eventos;
        if (nomeEvento == null) {
            eventos = repository.findAll(paginacao);
        } else {
            eventos = repository.findByNome(nomeEvento, paginacao);
        }
        return eventos.map(converter::toEventoResponseDTO);
    }

    public EventoResponseDTO buscarPorId(Long id) {
        Evento evento = repository.findById(id)
                .orElseThrow(() -> new NotFoundException(EVENTO_NOT_FOUND_MESSAGE));
        return converter.toEventoResponseDTO(evento);
    }

    public EventoResponseDTO cadastrar(EventoDTO dto) {
        Evento evento = converter.toEvento(dto);
        Evento savedEvento = repository.save(evento);
        return converter.toEventoResponseDTO(savedEvento);
    }

    public EventoResponseDTO atualizar(Long id, EventoDTO dto) {
        Evento evento = repository.findById(id)
                .orElseThrow(() -> new NotFoundException(EVENTO_NOT_FOUND_MESSAGE));
        evento.setNome(dto.nome());
        evento.setData(dto.data());
        evento.setDataInicioInsc(dto.dataInicioInsc());
        evento.setDataFimInsc(dto.dataFimInsc());
        evento.setDescricao(dto.descricao());
        evento.setStatus(dto.status());
        Evento updatedEvento = repository.save(evento);
        return converter.toEventoResponseDTO(updatedEvento);
    }

    public void deletar(Long id) {
        if (!repository.existsById(id)) {
            throw new NotFoundException(EVENTO_NOT_FOUND_MESSAGE);
        }
        repository.deleteById(id);
    }
}
