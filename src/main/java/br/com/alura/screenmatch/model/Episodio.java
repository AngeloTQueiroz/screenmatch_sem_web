package br.com.alura.screenmatch.model;



import java.time.DateTimeException;
import java.time.LocalDate;

public class Episodio {

    public Episodio(Integer numeroTemp, DadosEpisodios dadosEpisodios) {

        this.temporada = numeroTemp;
        this.titulo = dadosEpisodios.titulo();
        this.numeroEp = dadosEpisodios.numeroEp();

        try {
            this.avaiacao = Double.valueOf(dadosEpisodios.avaiacao());
        }catch (NumberFormatException ex){
            this.avaiacao = 0.0;
        }

        try {
            this.dataLancamento = LocalDate.parse(dadosEpisodios.dataLancamento());
        }catch (DateTimeException ex) {
        this.dataLancamento = null;
        }

    }

    public Integer getTemporada() {
        return temporada;
    }

    public void setTemporada(Integer temporada) {
        this.temporada = temporada;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Integer getNumeroEp() {
        return numeroEp;
    }

    public void setNumeroEp(Integer numeroEp) {
        this.numeroEp = numeroEp;
    }

    public double getAvaiacao() {
        return avaiacao;
    }

    public void setAvaiacao(double avaiacao) {
        this.avaiacao = avaiacao;
    }

    public LocalDate getDataLancamento() {
        return dataLancamento;
    }

    public void setDataLancamento(LocalDate dataLancamento) {
        this.dataLancamento = dataLancamento;
    }

    private Integer temporada;
     private String titulo;
     private Integer numeroEp;
    private double avaiacao;
     private LocalDate dataLancamento;


    @Override
    public String toString() {
        return "temporada=" + temporada +
                ", titulo='" + titulo + '\'' +
                ", numeroEp=" + numeroEp +
                ", avaiacao=" + avaiacao +
                ", dataLancamento=" + dataLancamento ;
    }
}


