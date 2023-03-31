package br.com.ada.Projeto.Final.Web.II.service;

import br.com.ada.Projeto.Final.Web.II.model.dto.UsuarioDTO;
import br.com.ada.Projeto.Final.Web.II.model.dto.security.TokenDTO;
import br.com.ada.Projeto.Final.Web.II.model.dto.security.UsuarioComSenhaDTO;
import br.com.ada.Projeto.Final.Web.II.model.dto.security.UsuarioLoginDTO;
import br.com.ada.Projeto.Final.Web.II.model.entity.UsuarioEntity;
import br.com.ada.Projeto.Final.Web.II.model.mapper.UsuarioMapper;
import br.com.ada.Projeto.Final.Web.II.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {
    @Autowired
    private UsuarioMapper mapper;
    @Autowired
    private UsuarioRepository repository;
    @Autowired
    private AuthenticationManager authManager;
    @Autowired
    private JwtService jwtService;

    public UsuarioDTO criar (UsuarioComSenhaDTO usuarioDTO) {
        UsuarioEntity usuario = mapper.update(usuarioDTO);
        usuario = repository.save(usuario);
        return mapper.update(usuario);
    }

    public TokenDTO entrar (UsuarioLoginDTO usuario) {
        UsernamePasswordAuthenticationToken authentication =
                UsernamePasswordAuthenticationToken.authenticated(
                        usuario.getUsername(), usuario.getPassword(), null);
        Authentication auth = authManager.authenticate(authentication);
        if (auth.isAuthenticated()) {
            UsuarioEntity usuarioEntity = (UsuarioEntity) auth.getPrincipal();
            String token = jwtService.gerarToken(mapper.update(usuarioEntity));
            System.out.println("Login realizado com sucesso.");
            return new TokenDTO("Bearer", token);
        } else {
            throw new AuthenticationCredentialsNotFoundException("Usuário inexistente ou senha inválida.");
        }
    }
}
