package br.com.ada.Projeto.Final.Web.II.service;

import br.com.ada.Projeto.Final.Web.II.model.dto.CategoriaDTO;
import br.com.ada.Projeto.Final.Web.II.model.entity.CategoriaEntity;
import br.com.ada.Projeto.Final.Web.II.model.mapper.CategoriaMapper;
import br.com.ada.Projeto.Final.Web.II.repository.CategoriaRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoriaService {
    @Autowired
    private CategoriaRepository repository;
    @Autowired
    private CategoriaMapper mapper;

    public List<CategoriaDTO> listar () {
        List<CategoriaEntity> categorias = repository.findAll();
        return mapper.updateListDTO(categorias);
    }

    public CategoriaDTO buscarPorId (Long id) {
        Optional<CategoriaEntity> categoriaEntityOp = repository.findById(id);
        if (categoriaEntityOp.isPresent()) {
            CategoriaEntity categoriaEntity = categoriaEntityOp.get();
            return mapper.update(categoriaEntity);
        }
        throw new EntityNotFoundException("Categoria não encontrada.");
    }

    public CategoriaDTO criar (CategoriaDTO categoriaDTO) {
        CategoriaEntity categoria = mapper.update(categoriaDTO);
        categoria = repository.save(categoria);
        return mapper.update(categoria);
    }

    public CategoriaDTO editar (CategoriaDTO categoriaDTO, Long id) {
        if (repository.existsById(id)) {
            CategoriaEntity categoriaEntity = mapper.update(categoriaDTO);
            categoriaEntity.setId(id);
            repository.save(categoriaEntity);
            return mapper.update(categoriaEntity);
        }
        throw new EntityNotFoundException("Categoria não encontrada.");
    }

    public void deletar (Long id) {
        Optional<CategoriaEntity> categoriaEntityOp = repository.findById(id);
        if (categoriaEntityOp.isPresent()) {
            CategoriaEntity categoriaEntity = categoriaEntityOp.get();
            repository.delete(categoriaEntity);
        }
        throw new EntityNotFoundException("Categoria não encontrada.");
    }

}
