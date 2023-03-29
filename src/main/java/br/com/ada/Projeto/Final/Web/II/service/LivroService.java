package br.com.ada.Projeto.Final.Web.II.service;

import br.com.ada.Projeto.Final.Web.II.model.dto.LivroDTO;
import br.com.ada.Projeto.Final.Web.II.model.entity.CategoriaEntity;
import br.com.ada.Projeto.Final.Web.II.model.entity.EditoraEntity;
import br.com.ada.Projeto.Final.Web.II.model.entity.LivroEntity;
import br.com.ada.Projeto.Final.Web.II.model.mapper.LivroMapper;
import br.com.ada.Projeto.Final.Web.II.repository.LivroRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class LivroService {
    @Autowired
    private LivroRepository repository;
    @Autowired
    private LivroMapper mapper;

    public List<LivroDTO> listar () {
        List<LivroEntity> livros = repository.findAll();
        return mapper.updateListDTO(livros);
    }

    public LivroDTO buscarPorId (Long id) {
        Optional<LivroEntity> livroEntityOp = repository.findById(id);
        if (livroEntityOp.isPresent()) {
            LivroEntity livroEntity = livroEntityOp.get();
            return mapper.update(livroEntity);
        }
        throw new EntityNotFoundException("Categoria não encontrada.");
    }

    public LivroDTO criar (LivroDTO livroDTO) {
        LivroEntity livro = mapper.update(livroDTO);
        livro = repository.save(livro);
        return mapper.update(livro);
    }

    public LivroDTO editar (LivroDTO livroDTO, Long id) {
        if (repository.existsById(id)) {
            LivroEntity livroEntity = mapper.update(livroDTO);
            livroEntity.setId(id);
            repository.save(livroEntity);
            return mapper.update(livroEntity);
        }
        throw new EntityNotFoundException("Categoria não encontrada.");
    }

    public void deletar (Long id) {
        Optional<LivroEntity> livroEntityOp = repository.findById(id);
        if (livroEntityOp.isPresent()) {
            LivroEntity livroEntity = livroEntityOp.get();
            repository.delete(livroEntity);
        }
        throw new EntityNotFoundException("Categoria não encontrada.");
    }
    public Object listarPorCategoria(Long idCategoria) {
        CategoriaEntity categoria = new CategoriaEntity();
        categoria.setId(idCategoria);
        List<LivroEntity> listaLivroEntity =  repository.findByCategoria(categoria);
        return mapper.updateListDTO(listaLivroEntity);
    }

    public Object listarPorEditora(Long idEditora) {
        EditoraEntity editora = new EditoraEntity();
        editora.setId(idEditora);
        List<LivroEntity> listaLivroEntity =  repository.findByEditora(editora);
        return mapper.updateListDTO(listaLivroEntity);
    }
    public Object filtrarPorNomeOrIsbn(String nome, String isbn) {
        List<LivroEntity> livros = repository.findByNomeOrIsbn(nome, isbn);
        return mapper.updateListDTO(livros);
    }
    
}
