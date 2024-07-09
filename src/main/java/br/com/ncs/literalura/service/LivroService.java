package br.com.ncs.literalura.service;

import br.com.ncs.literalura.model.ApiResult;
import br.com.ncs.literalura.model.Autor;
import br.com.ncs.literalura.model.Livro;
import br.com.ncs.literalura.repository.AuthorRepository;
import br.com.ncs.literalura.repository.BookRepository;
import br.com.ncs.literalura.utils.IdiomaSiglaConverter;
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

    private final IdiomaSiglaConverter idiomaSiglaConverter = new IdiomaSiglaConverter();

    @Autowired
    private WebClient webClient;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorRepository authorRepository;

    public LivroService() {
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

    @Transactional(readOnly = true)
    public void findAllBooks(){
        List<Livro> livros = bookRepository.findAllBooks();

        livros.forEach(System.out::println);
    }

    @Transactional(readOnly = true)
    public void findAllAuthors(){
        List<Autor> autores = authorRepository.findAll();

        autores.forEach(System.out::println);

    }

    @Transactional(readOnly = true)
    public void livingAuthors(){

        System.out.println("Digite o ano que deseja buscar: ");

        Integer ano = scanner.nextInt();
        List<Autor> autores = authorRepository.findAutoresVivos(ano);

        autores.forEach(System.out::println);
    }

    @Transactional(readOnly = true)
    public void booksByLanguage() {
        String idioma;

        do {
            System.out.println("Digite o idioma dos livros que deseja buscar: ");
            idioma = scanner.nextLine().toLowerCase();

            if (idioma.isEmpty()) {
                System.out.println("O idioma não pode ser vazio. Por favor, insira um idioma válido.");
            } else {
                idioma = IdiomaSiglaConverter.getSigla(idioma);
            }
        } while (idioma.isEmpty());

        List<Livro> livros = bookRepository.findByIdioma(idioma);

        if(livros.isEmpty()) {
            System.out.println("Livros não encontrados!");
        } else {
            livros.forEach(System.out::println);
        }

    }

}