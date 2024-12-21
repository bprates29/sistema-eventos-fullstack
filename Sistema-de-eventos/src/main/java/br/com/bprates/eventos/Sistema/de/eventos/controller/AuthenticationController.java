package br.com.bprates.eventos.Sistema.de.eventos.controller;

import br.com.bprates.eventos.Sistema.de.eventos.dtos.LoginDTO;
import br.com.bprates.eventos.Sistema.de.eventos.dtos.LoginResponseDTO;
import br.com.bprates.eventos.Sistema.de.eventos.dtos.UsuarioDTO;
import br.com.bprates.eventos.Sistema.de.eventos.model.Usuario;
import br.com.bprates.eventos.Sistema.de.eventos.service.TokenService;
import br.com.bprates.eventos.Sistema.de.eventos.service.UsuarioService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("auth")
public class AuthenticationController {

    private final AuthenticationManager authenticationManager;
    private final UsuarioService service;
    private final TokenService tokenService;

    public AuthenticationController(AuthenticationManager authenticationManager, UsuarioService service, TokenService tokenService) {
        this.authenticationManager = authenticationManager;
        this.service = service;
        this.tokenService = tokenService;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginDTO data) {
        UsernamePasswordAuthenticationToken userPassword =
                new UsernamePasswordAuthenticationToken(data.login(), data.password());
        Authentication auth = authenticationManager.authenticate(userPassword);
        String token = tokenService.generateToken((Usuario) auth.getPrincipal());
        return ResponseEntity.ok(new LoginResponseDTO(token));
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody UsuarioDTO data) {
        service.cadastrar(data);
        return ResponseEntity.ok().build();
    }
}

