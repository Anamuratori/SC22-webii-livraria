package br.com.ada.Projeto.Final.Web.II.controller;

import br.com.ada.Projeto.Final.Web.II.model.dto.LivroDTO;
import br.com.ada.Projeto.Final.Web.II.model.dto.MensagemDTO;
import br.com.ada.Projeto.Final.Web.II.service.LivroService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/livros")
public class LivroController {
    @Autowired
    private LivroService livroService;
    @GetMapping()
    @Secured({"ADMIN"})
    public ResponseEntity<Object> listar() {
        try {
            return ResponseEntity.ok(livroService.listar());
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(new MensagemDTO(e.getMessage()));
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> buscarPorId(@PathVariable("id") Long id){

        try {
            return ResponseEntity.ok(livroService.buscarPorId(id));
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
    @PostMapping
    public ResponseEntity<Object> criar(@RequestBody @Valid LivroDTO livroDTO){

        try {
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(livroService.criar(livroDTO));
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(new MensagemDTO(e.getMessage()));
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> editar(
            @RequestBody @Valid LivroDTO livroDTO,
            @PathVariable("id") Long id) {
        try {
            return ResponseEntity.ok(livroService.editar(livroDTO, id));
        } catch (EntityNotFoundException e) {
            log.error(e.getMessage());
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(new MensagemDTO(e.getMessage()));
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(new MensagemDTO(e.getMessage()));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deletar(
            @PathVariable("id") Long id) {
        try {
            livroService.deletar(id);
            return ResponseEntity.ok("Livro com id " + id + " removido!");
        } catch (EntityNotFoundException e) {
            log.error(e.getMessage());
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(new MensagemDTO(e.getMessage()));
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(new MensagemDTO(e.getMessage()));
        }
    }
    @GetMapping("/categoria/{idCategoria}")
    public ResponseEntity<Object> listarPorCategoria(@PathVariable Long idCategoria) {
        try {
            return ResponseEntity.ok(livroService.listarPorCategoria(idCategoria));
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(new MensagemDTO(e.getMessage()));
        }
    }
    @GetMapping("/editora/{idEditora}")
    public ResponseEntity<Object> listarPorEditora(@PathVariable Long idEditora) {
        try {
            return ResponseEntity.ok(livroService.listarPorEditora(idEditora));
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(new MensagemDTO(e.getMessage()));
        }
    }
    @GetMapping("/listarNomeIsbn")
    public ResponseEntity<Object> filtrarPorNomeOrIsbn(@RequestParam(name="nome", defaultValue = " ") String nome,
                                                       @RequestParam(name="isbn", defaultValue = " ") String isbn) {
        try {
            return ResponseEntity.ok(livroService.filtrarPorNomeOrIsbn(nome, isbn));
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(new MensagemDTO(e.getMessage()));
        }
    }
}
