package br.com.ada.Projeto.Final.Web.II.model.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table (name = "livro")
public class LivroEntity {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;
    @Column (name = "nome", nullable = false)
    private String nome;
    @ManyToOne
    @JoinColumn (name = "editora", nullable = false)
    private EditoraEntity editora;
    @ManyToOne
    @JoinColumn (name = "categoria", nullable = false)
    private CategoriaEntity categoria;
    @Column (name = "isbn", nullable = false, unique = true)
    private String isbn;
}
