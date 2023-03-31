package br.com.ada.Projeto.Final.Web.II.service;

import br.com.ada.Projeto.Final.Web.II.model.entity.UsuarioEntity;
import br.com.ada.Projeto.Final.Web.II.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class UsuarioLoginService implements UserDetailsService {
    @Autowired
    private UsuarioRepository repository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UsuarioEntity> usuarioOp = repository.findByUsername(username);
        if(usuarioOp.isPresent()) {
            return usuarioOp.get();
        } else {
            throw new UsernameNotFoundException("Usuário não encontrado.");
        }
    }
}
