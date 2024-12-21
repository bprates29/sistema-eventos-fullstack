package br.com.bprates.eventos.Sistema.de.eventos.controller;

import br.com.bprates.eventos.Sistema.de.eventos.dtos.EventoDTO;
import br.com.bprates.eventos.Sistema.de.eventos.dtos.EventoResponseDTO;
import br.com.bprates.eventos.Sistema.de.eventos.service.EventoService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/eventos")
public class EventoController {

    private final EventoService service;

    public EventoController(EventoService service) {
        this.service = service;
    }

    @GetMapping
    public Page<EventoResponseDTO> listar(
            @RequestParam(required = false) String nomeEvento,
            @PageableDefault(size = 10) Pageable paginacao) {
        return service.listar(nomeEvento, paginacao);
    }

    @GetMapping("/{id}")
    public EventoResponseDTO buscarPorId(@PathVariable Long id) {
        return service.buscarPorId(id);
    }

    @PostMapping
    @Transactional
    public ResponseEntity<EventoResponseDTO> cadastra(
            @RequestBody @Valid EventoDTO dto,
            UriComponentsBuilder uriBuilder) {
        EventoResponseDTO eventoResponse = service.cadastrar(dto);
        var uri = uriBuilder.path("/eventos/{id}")
                .buildAndExpand(eventoResponse.id())
                .toUri();
        return ResponseEntity.created(uri).body(eventoResponse);
    }

    @PutMapping("/{id}")
    @Transactional
    public EventoResponseDTO atualizar(
            @PathVariable Long id,
            @RequestBody @Valid EventoDTO dto) {
        return service.atualizar(id, dto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Transactional
    public void deletar(@PathVariable Long id) {
        service.deletar(id);
    }
}

