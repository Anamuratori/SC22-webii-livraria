package br.com.ada.Projeto.Final.Web.II.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class LivroDTO {
    private Long id;
    @NotBlank (message = "Campo 'nome' não pode estar vazio.")
    private String nome;
    private EditoraDTO editora;
    private CategoriaDTO categoria;
    @NotBlank (message = "Campo 'ISBN' não pode estar vazio.")
    @Size (max=13, message = "Tamanho acima do permitido. Máximo de 13 caracteres.")
    private String isbn;


}
