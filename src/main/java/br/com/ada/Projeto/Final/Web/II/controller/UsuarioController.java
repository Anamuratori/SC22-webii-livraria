package br.com.ada.Projeto.Final.Web.II.controller;

import br.com.ada.Projeto.Final.Web.II.model.dto.MensagemDTO;
import br.com.ada.Projeto.Final.Web.II.model.dto.UsuarioDTO;
import br.com.ada.Projeto.Final.Web.II.model.dto.security.UsuarioComSenhaDTO;
import br.com.ada.Projeto.Final.Web.II.model.dto.security.UsuarioLoginDTO;
import br.com.ada.Projeto.Final.Web.II.service.UsuarioService;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/usuarios")
@Slf4j
public class UsuarioController {
    @Autowired
    private UsuarioService service;
    @PostMapping
    public ResponseEntity<Object> criar (@RequestBody UsuarioComSenhaDTO usuario) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(service.criar(usuario));
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(new MensagemDTO(e.getMessage()));
        }
    }
    @PostMapping("/auth")
    public ResponseEntity<Object> entrar (@RequestBody UsuarioLoginDTO usuario) {
        try {
            return ResponseEntity.ok(service.entrar(usuario));
        } catch (EntityNotFoundException e) {
            log.error(e.getMessage());
            return ResponseEntity
                    .status(HttpStatus.NO_CONTENT)
                    .body(new MensagemDTO(e.getMessage()));

        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(new MensagemDTO(e.getMessage()));
        }
    }
}
