package br.com.ada.Projeto.Final.Web.II.controller;

import br.com.ada.Projeto.Final.Web.II.model.dto.CategoriaDTO;
import br.com.ada.Projeto.Final.Web.II.model.dto.MensagemDTO;
import br.com.ada.Projeto.Final.Web.II.service.CategoriaService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/categorias")
public class CategoriaController {
    @Autowired
    private CategoriaService categoriaService;

    @GetMapping()
    public ResponseEntity<Object> listar() {
        try {
            return ResponseEntity.ok(categoriaService.listar());
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
            return ResponseEntity.ok(categoriaService.buscarPorId(id));
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
    public ResponseEntity<Object> criar(@RequestBody @Valid CategoriaDTO categoriaDTO){

        try {
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(categoriaService.criar(categoriaDTO));
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(new MensagemDTO(e.getMessage()));
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> editar(
            @RequestBody @Valid CategoriaDTO categoriaDTO,
            @PathVariable("id") Long id) {
        try {
            return ResponseEntity.ok(categoriaService.editar(categoriaDTO, id));
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
            categoriaService.deletar(id);
            return ResponseEntity.ok("Categoria com id " + id + " removida!");
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

}
