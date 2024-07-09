package br.com.ncs.literalura.service;

import br.com.ncs.literalura.model.ApiResult;
import br.com.ncs.literalura.model.Autor;
import br.com.ncs.literalura.model.Livro;
import br.com.ncs.literalura.repository.AuthorRepository;
import br.com.ncs.literalura.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;


@Service
public class LivroService {

    private final Scanner scanner = new Scanner(System.in);

    @Autowired
    private WebClient webClient;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorRepository authorRepository;

    @Transactional(readOnly = true)
    public void findAll(){
            List<Object[]> books = bookRepository.findAllBooks();

            System.out.println("aqui eu mostro o tamanho do retorno do repository: " + books.size());

            for (Object[] book : books) {
                System.out.println("ID: " + book[0]);
                System.out.println("ID Livro: " + book[1]);
                System.out.println("Título: " + book[2]);
                System.out.println("Número de Downloads: " + book[3]);
                System.out.println("ID do Autor: " + book[4]);
                System.out.println("-----------------------------");

            }

    }

    @Transactional
    public void getBookByTitle() {

        System.out.println("Digite o nome do livro que deseja buscar: ");

        var nomeLivro = scanner.nextLine().toLowerCase();

        Optional<Livro> livroOptional = bookRepository.findByTituloIgnoreCase(nomeLivro);

        var livroEncontrado = livroOptional.orElseGet(()-> {
            try{
                ApiResult dadosLivros = webClient.get().uri(uriBuilder ->
                        uriBuilder.path("books/").queryParam("search", nomeLivro.replace(" ", "%20")).build()
                ).retrieve().bodyToMono(ApiResult.class).block();

                if (dadosLivros != null){

                    Livro livro = new Livro(dadosLivros.getResultadoLivro().get(0));

                    Optional<Autor> authorOptional = authorRepository.findByNome(livro.getAutor().getNome());

                    authorOptional.ifPresent(livro::setAutor);

                    return bookRepository.save(livro);
                }
                return null;
            } catch (Exception exception){
                return null;
            }

        } );

        if(livroEncontrado != null){
            System.out.println(livroEncontrado);
        } else {
            System.out.println("Livro não encontrado!" + "\n");
        }
    }
}