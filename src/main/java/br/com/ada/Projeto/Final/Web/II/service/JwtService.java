package br.com.ada.Projeto.Final.Web.II.service;

import br.com.ada.Projeto.Final.Web.II.model.dto.UsuarioDTO;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@Service
public class JwtService {
    private SecretKey secretKey = Keys.secretKeyFor(SignatureAlgorithm.HS256);
    public String gerarToken (UsuarioDTO usuario) {
        Long instance = LocalDateTime.now()
                .plus(Duration.ofMinutes(30))
                .atZone(ZoneId.systemDefault())
                .toInstant()
                .toEpochMilli();

        Date dataCriacao = new Date();
        Date dataExpiracao = new Date(instance);

        return Jwts
                .builder()
                .setSubject(usuario.getUsername())
                .setIssuer(usuario.getNome())
                .setIssuedAt(dataCriacao)
                .setExpiration(dataExpiracao)
                .signWith(secretKey)
                .compact();

    }

    public Boolean validarToken (String token) {
        return Jwts.parserBuilder()
                .setSigningKey(secretKey)
                .build().isSigned(token);
    }
    public String getUsername(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(secretKey)
                .build().parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

}
