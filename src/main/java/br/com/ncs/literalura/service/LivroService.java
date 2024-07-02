package br.com.ncs.literalura.service;

import br.com.ncs.literalura.api.ApiClient;
import br.com.ncs.literalura.converter.JsonConverter;
import br.com.ncs.literalura.model.ApiResult;
import br.com.ncs.literalura.model.Livro;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Scanner;


@Service
public class LivroService {

    private final Scanner scanner = new Scanner(System.in);

    private final ApiClient client = new ApiClient();

    private final JsonConverter converter = new JsonConverter();

    @Transactional
    public void getBookByTitle() {

        System.out.println("Digite o nome do livro que deseja buscar: ");

        var nomeLivro = scanner.nextLine().toLowerCase();

        var json = client.getData("https://gutendex.com/books/?search=" + nomeLivro.replace(" ", "%20"));

        ApiResult dados = converter.getData(json, ApiResult.class);
        System.out.println(dados);

        if (dados != null && dados.getResultadoLivro() != null && !dados.getResultadoLivro().isEmpty()) {
            Livro livro = new Livro(dados.getResultadoLivro().get(0));
            System.out.println(livro);
        } else {
            System.out.println("Nenhum resultado encontrado");
        }

    }

}