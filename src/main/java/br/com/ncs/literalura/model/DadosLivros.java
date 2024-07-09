package br.com.ncs.literalura.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DadosLivros(@JsonProperty("id") Long idLivro,
                          @JsonProperty("title") String titulo,
                          @JsonProperty("authors") List<DadosAutor> autor,
                          @JsonProperty("languages") List<String> idiomas,
                          @JsonProperty("download_count") Long numeroDownloads) {
}
