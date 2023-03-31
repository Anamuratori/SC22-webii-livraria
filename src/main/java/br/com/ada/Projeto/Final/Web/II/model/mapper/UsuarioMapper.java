package br.com.ada.Projeto.Final.Web.II.model.mapper;

import br.com.ada.Projeto.Final.Web.II.model.dto.UsuarioDTO;
import br.com.ada.Projeto.Final.Web.II.model.dto.security.UsuarioComSenhaDTO;
import br.com.ada.Projeto.Final.Web.II.model.entity.UsuarioEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UsuarioMapper {
    @Autowired
    private PasswordEncoder encoder;
    public UsuarioDTO update(UsuarioEntity usuario) {
        UsuarioDTO usuarioDTO = new UsuarioDTO();
        usuarioDTO.setId(usuario.getId());
        usuarioDTO.setNome(usuario.getNome());
        usuarioDTO.setEmail(usuario.getEmail());
        usuarioDTO.setUsername(usuario.getUsername());
        usuarioDTO.setCpf(usuario.getCpf());
        return usuarioDTO;
    }
    public UsuarioEntity update(UsuarioComSenhaDTO usuario) {
        UsuarioEntity usuarioEntity = new UsuarioEntity();

        usuarioEntity.setNome(usuario.getNome());
        usuarioEntity.setEmail(usuario.getEmail());
        usuarioEntity.setUsername(usuario.getUsername());
        usuarioEntity.setCpf(usuario.getCpf());
        usuarioEntity.setPassword(encoder.encode(usuario.getPassword()));
        return usuarioEntity;
    }
}
