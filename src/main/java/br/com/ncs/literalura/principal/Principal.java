package br.com.ncs.literalura.principal;

import br.com.ncs.literalura.service.LivroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class Principal {

    private final Scanner scanner = new Scanner(System.in);

    @Autowired
    private LivroService livroService;

    public void showMenu() {
        int option = -1;

        do {
            var menu = """
                    ---- OPÇÕES PARA ESCOLHA ----
                    
                    1 - Buscar livros pelo título.
                    2 - Lista de Livros registrados.
                    3 - Lista de autores registrados.
                    4 - Lista de autores registrados vivos em determinado ano.
                    5 - Lista de livros por idioma.

                    0 - Sair.
                    """;

            System.out.println(menu);
            System.out.print("Escolha uma opção: ");

            if (scanner.hasNextInt()) {
                option = scanner.nextInt();
                switch (option) {
                    case 1 -> {
                        System.out.println("Você escolheu buscar livros pelo título.");
                        livroService.getBookByTitle();
                    }
                    case 2 ->{
                        System.out.println("Você escolheu ver a lista de livros registrados.");
                        livroService.findAllBooks();
                    }
                    case 3 -> {
                        System.out.println("Você escolheu ver a lista de autores registrados.");
                        livroService.findAllAuthors();
                    }
                    case 4 -> {
                        System.out.println("Você escolheu ver a lista de autores registrados vivos em determinado ano.");
                        livroService.livingAuthors();
                    }
                    case 5 -> {
                        System.out.println("Você escolheu ver a lista de livros por idioma.");
                        livroService.booksByLanguage();
                    }
                    case 0 -> System.out.println("Saindo...");
                    default -> System.out.println("Opção inválida. Escolha uma opção válida.");
                }
            } else {
                System.out.println("Entrada inválida. Por favor, insira um número.");
                scanner.next();
            }
        } while (option != 0);
        scanner.close();
    }
}