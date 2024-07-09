package br.com.ncs.literalura.repository;

import br.com.ncs.literalura.model.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AuthorRepository extends JpaRepository<Autor, Long> {
    List<Autor> findAll();
    
    Optional<Autor> findById(Long aLong);

    Optional<Autor> findByNome(String nome);

    @Query("SELECT a FROM Autor a WHERE a.anoNascimento <= :ano AND (a.anoFalecimento IS NULL OR a.anoFalecimento >= :ano)")
    List<Autor> findAutoresVivos(@Param("ano") Integer ano);
}

