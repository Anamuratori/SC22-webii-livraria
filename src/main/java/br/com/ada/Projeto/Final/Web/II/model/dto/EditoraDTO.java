package br.com.ada.Projeto.Final.Web.II.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class EditoraDTO {
    private Long id;
    @NotBlank (message = "Campo 'nome' não pode estar vazio.")
    @Size (max = 255, message = "Tamanho acima do permitido. Máximo de 255 caracteres")
    private String nome;
    private String descricao;
}
