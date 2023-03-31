package br.com.ada.Projeto.Final.Web.II.model.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Data
@Entity
@Table (name = "usuario")
public class UsuarioEntity implements UserDetails {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String email;
    @Column (unique = true)
    private String username;
    private String cpf;
    private String password;
    @ManyToOne
    @JoinColumn
    private PerfilEntity perfil;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if(perfil!=null) {
            return List.of(perfil);
        } else {
            return null;
        }
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
