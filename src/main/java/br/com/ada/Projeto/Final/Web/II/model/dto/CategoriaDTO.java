package br.com.ada.Projeto.Final.Web.II.model.dto;

import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
@Data
public class CategoriaDTO {
    private Long id;
    @NotBlank (message = "Campo 'nome' não pode estar vazio.")
    @Size (max=100, message = "Tamanho acima do permitido. Máximo de 100 caracteres.")
    private String nome;
}
