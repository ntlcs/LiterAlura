package br.com.ncs.literalura.repository;

import br.com.ncs.literalura.model.Autor;
import br.com.ncs.literalura.model.Livro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Livro, Long>{

    Optional<Livro> findByTituloIgnoreCase(String title);

    @Query("SELECT l FROM Livro l WHERE lower(l.titulo) = lower(:titulo)")
    Livro findByTituloIgnoreCaseCustom(@Param("titulo") String titulo);

    Boolean existsByTitulo(String titulo);
    Livro findByTituloContainsIgnoreCase(String titulo);
    String existsByAutor(Autor nomeauto);

    @Query("SELECT l FROM Livro l WHERE :idioma MEMBER OF l.idiomas")
    List<Livro> livrosPorIdioma(String idioma);

    @Query(value = "SELECT id, idlivro, titulo, numerodownloads, autor_id FROM livros", nativeQuery = true)
    List<Object[]> findAllBooks();


}
