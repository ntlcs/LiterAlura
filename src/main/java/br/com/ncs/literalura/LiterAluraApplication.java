package br.com.ncs.literalura;

import br.com.ncs.literalura.principal.Principal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"br.com.ncs.literalura"})
public class LiterAluraApplication {

    @Autowired
    private Principal principal;

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(LiterAluraApplication.class, args);
        LiterAluraApplication app = context.getBean(LiterAluraApplication.class);
        app.run();
    }

    public void run() {
        principal.showMenu();
    }

}