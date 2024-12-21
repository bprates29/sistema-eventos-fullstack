package br.com.bprates.eventos.Sistema.de.eventos.controller;

import br.com.bprates.eventos.Sistema.de.eventos.dtos.UsuarioDTO;
import br.com.bprates.eventos.Sistema.de.eventos.dtos.UsuarioResponseDTO;
import br.com.bprates.eventos.Sistema.de.eventos.service.UsuarioService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    private final UsuarioService service;

    public UsuarioController(UsuarioService service) {
        this.service = service;
    }

    @GetMapping
    public List<UsuarioResponseDTO> listar() {
        return service.listar();
    }

    @GetMapping("/{id}")
    public UsuarioResponseDTO buscarPorId(@PathVariable Long id) {
        return service.buscarPorId(id);
    }

    @PostMapping
    @Transactional
    public ResponseEntity<UsuarioResponseDTO> cadastrar(
            @RequestBody @Valid UsuarioDTO dto,
            UriComponentsBuilder uriBuilder) {
        UsuarioResponseDTO userResponse = service.cadastrar(dto);
        var uri = uriBuilder.path("/usuarios/{id}")
                .buildAndExpand(userResponse.id())
                .toUri();
        return ResponseEntity.created(uri).body(userResponse);
    }

    @PutMapping("/{id}")
    @Transactional
    public UsuarioResponseDTO atualizar(
            @PathVariable Long id,
            @RequestBody @Valid UsuarioDTO dto) {
        return service.atualizar(id, dto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Transactional
    public void deletar(@PathVariable Long id) {
        service.deletar(id);
    }
}

