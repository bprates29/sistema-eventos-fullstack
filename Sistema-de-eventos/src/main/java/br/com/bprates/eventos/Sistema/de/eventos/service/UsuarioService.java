package br.com.bprates.eventos.Sistema.de.eventos.service;

import br.com.bprates.eventos.Sistema.de.eventos.converters.UsuarioConverter;
import br.com.bprates.eventos.Sistema.de.eventos.dtos.UsuarioDTO;
import br.com.bprates.eventos.Sistema.de.eventos.dtos.UsuarioResponseDTO;
import br.com.bprates.eventos.Sistema.de.eventos.exceptions.NotFoundException;
import br.com.bprates.eventos.Sistema.de.eventos.model.Usuario;
import br.com.bprates.eventos.Sistema.de.eventos.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UsuarioService {

    private static final String USUARIO_NOT_FOUND_MESSAGE = "Usuário não encontrado!";

    private final UsuarioRepository repository;
    private final UsuarioConverter converter;

    public UsuarioService(UsuarioRepository repository, UsuarioConverter converter) {
        this.repository = repository;
        this.converter = converter;
    }

    public List<UsuarioResponseDTO> listar() {
        return repository.findAll()
                .stream()
                .map(converter::toUsuarioResponseDTO)
                .collect(Collectors.toList());
    }

    public UsuarioResponseDTO buscarPorId(Long id) {
        Usuario usuario = repository.findById(id)
                .orElseThrow(() -> new NotFoundException(USUARIO_NOT_FOUND_MESSAGE));
        return converter.toUsuarioResponseDTO(usuario);
    }

    public UsuarioResponseDTO cadastrar(UsuarioDTO dto) {
        Usuario usuario = converter.toUsuario(dto);
        Usuario savedUsuario = repository.save(usuario);
        return converter.toUsuarioResponseDTO(savedUsuario);
    }

    public UsuarioResponseDTO atualizar(Long id, UsuarioDTO dto) {
        Usuario usuario = repository.findById(id)
                .orElseThrow(() -> new NotFoundException(USUARIO_NOT_FOUND_MESSAGE));

        usuario.setNome(dto.nome());

        Usuario updatedUsuario = repository.save(usuario);
        return converter.toUsuarioResponseDTO(updatedUsuario);
    }

    public void deletar(Long id) {
        if (!repository.existsById(id)) {
            throw new NotFoundException(USUARIO_NOT_FOUND_MESSAGE);
        }
        repository.deleteById(id);
    }
}
