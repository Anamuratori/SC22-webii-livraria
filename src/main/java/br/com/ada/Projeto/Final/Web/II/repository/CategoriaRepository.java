package br.com.ada.Projeto.Final.Web.II.repository;

import br.com.ada.Projeto.Final.Web.II.model.entity.CategoriaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoriaRepository extends JpaRepository<CategoriaEntity, Long> {
}
