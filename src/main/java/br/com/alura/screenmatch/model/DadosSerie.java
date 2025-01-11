package br.com.alura.screenmatch.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

// ignora tudo que for diferente do listado no record
@JsonIgnoreProperties(ignoreUnknown = true)

public record DadosSerie(
     @JsonAlias("Title") String titulo,
     @JsonAlias("totalSeasons")  Integer totalTemp,
     @JsonAlias("imdbRating")   String avaliacao
                         ) {





}
