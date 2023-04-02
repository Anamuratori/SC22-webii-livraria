package br.com.ada.Projeto.Final.Web.II.model.dto;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
public class UsuarioDTO {
    private Long id;
    private String nome;
    private String email;
    private String username;
    private String cpf;
}
