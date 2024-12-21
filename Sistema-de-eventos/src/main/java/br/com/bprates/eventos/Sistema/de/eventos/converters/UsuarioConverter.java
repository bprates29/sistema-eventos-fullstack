package br.com.bprates.eventos.Sistema.de.eventos.converters;

import br.com.bprates.eventos.Sistema.de.eventos.dtos.UsuarioDTO;
import br.com.bprates.eventos.Sistema.de.eventos.dtos.UsuarioResponseDTO;
import br.com.bprates.eventos.Sistema.de.eventos.model.UserRole;
import br.com.bprates.eventos.Sistema.de.eventos.model.Usuario;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UsuarioConverter {

    public UsuarioResponseDTO toUsuarioResponseDTO(Usuario usuario) {
        return new UsuarioResponseDTO(
                usuario.getId(),
                usuario.getNome(),
                usuario.getEmail()
        );
    }

    public Usuario toUsuario(UsuarioDTO dto) {
        return new Usuario(
                null, // ID será gerado automaticamente pelo banco
                dto.nome(),
                new BCryptPasswordEncoder().encode(dto.senha()), // Codificação da senha
                dto.email(),
                UserRole.USER,
                null // Lista de inscrições será inicializada vazia
        );
    }
}

