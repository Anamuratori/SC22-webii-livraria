package br.com.ada.Projeto.Final.Web.II.model.dto.security;

import br.com.ada.Projeto.Final.Web.II.model.dto.UsuarioDTO;
import lombok.Data;

@Data
public class UsuarioComSenhaDTO extends UsuarioDTO {
    private String password;
}
