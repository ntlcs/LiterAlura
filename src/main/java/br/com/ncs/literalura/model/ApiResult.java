package br.com.ncs.literalura.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ApiResult {
    @JsonAlias("results")
    List<DadosLivros> resultadoLivro;

    public List<DadosLivros> getResultadoLivro() {
        return resultadoLivro;
    }

    public void setResultadoLivro(List<DadosLivros> resultadoLivro) {
        this.resultadoLivro = resultadoLivro;
    }

}