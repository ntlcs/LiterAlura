package br.com.ncs.literalura.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DadosAutor(@JsonProperty("name") String nome,
                         @JsonProperty("birth_year") Integer anoNascimento,
                         @JsonProperty("death_year") Integer anoFalecimento) {

}
