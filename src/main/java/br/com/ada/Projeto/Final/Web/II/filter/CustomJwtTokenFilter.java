package br.com.ada.Projeto.Final.Web.II.filter;

import br.com.ada.Projeto.Final.Web.II.model.entity.UsuarioEntity;
import br.com.ada.Projeto.Final.Web.II.service.JwtService;
import br.com.ada.Projeto.Final.Web.II.service.UsuarioLoginService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
@Component
public class CustomJwtTokenFilter extends OncePerRequestFilter {
    @Autowired
    private JwtService jwtService;

    @Autowired
    private UsuarioLoginService loginService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String token = request.getHeader("authorization");

        if(token!=null && !token.isBlank() && token.contains("Bearer ")) {
            token = token.trim().replace("Bearer ","");
            if(jwtService.validarToken(token)) {
                String username = jwtService.getUsername(token);
                UsuarioEntity usuario = (UsuarioEntity) loginService.loadUserByUsername(username);
                UsernamePasswordAuthenticationToken authentication =
                        UsernamePasswordAuthenticationToken.authenticated(usuario.getUsername(), null, usuario.getAuthorities());

                if(SecurityContextHolder.getContext().getAuthentication()==null) {
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            }
        }

        filterChain.doFilter(request, response);
    }
}
