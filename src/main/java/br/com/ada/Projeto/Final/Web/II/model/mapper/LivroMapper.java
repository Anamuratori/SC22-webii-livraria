package br.com.ada.Projeto.Final.Web.II.model.mapper;

import br.com.ada.Projeto.Final.Web.II.model.dto.LivroDTO;
import br.com.ada.Projeto.Final.Web.II.model.entity.LivroEntity;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class LivroMapper {
    private EditoraMapper editoraMapper = new EditoraMapper();
    private CategoriaMapper categoriaMapper = new CategoriaMapper();
    public LivroDTO update(LivroEntity livro) {
        LivroDTO livroDTO = new LivroDTO();
        livroDTO.setId(livro.getId());
        livroDTO.setNome(livro.getNome());
        livroDTO.setEditora(editoraMapper.update(livro.getEditora()));
        livroDTO.setCategoria(categoriaMapper.update(livro.getCategoria()));
        livroDTO.setIsbn(livro.getIsbn());
        return livroDTO;
    }
    public LivroEntity update(LivroDTO livro) {
        LivroEntity livroEntity = new LivroEntity();
        livroEntity.setId(livro.getId());
        livroEntity.setNome(livro.getNome());
        livroEntity.setEditora(editoraMapper.update(livro.getEditora()));
        livroEntity.setCategoria(categoriaMapper.update(livro.getCategoria()));
        livroEntity.setIsbn(livro.getIsbn());
        return livroEntity;
    }
    public List<LivroEntity> updateListEntity (List<LivroDTO> listaDTO) {
        return listaDTO.stream()
                .map(livroDTO -> this.update(livroDTO)).toList();
    }
    public List<LivroDTO> updateListDTO (List<LivroEntity> listaEntity) {
        return listaEntity.stream()
                .map(livroEntity -> this.update(livroEntity)).toList();
    }
}
