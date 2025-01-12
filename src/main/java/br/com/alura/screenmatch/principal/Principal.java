package br.com.alura.screenmatch.principal;

import br.com.alura.screenmatch.model.DadosEpisodios;
import br.com.alura.screenmatch.model.DadosSerie;
import br.com.alura.screenmatch.model.DadosTemporada;
import br.com.alura.screenmatch.model.Episodio;
import br.com.alura.screenmatch.service.ConsumoApi;
import br.com.alura.screenmatch.service.ConverteDados;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Principal {
    private ConsumoApi consumoApi = new ConsumoApi();
    private Scanner leitor = new Scanner(System.in);
    private ConverteDados conversor = new ConverteDados();

// FINAL = NUNCA SERA MODIFICAO
    private final  String ENDERECO = "https://www.omdbapi.com/?t=";

    private final String API_KEY = "&apikey=86d67965";

    public void exibeMenu(){
        System.out.println("*******************************************************");
        System.out.println("Digite o nome da serie para consulta");
        var nomeSerie = leitor.nextLine();
        var json = consumoApi.obterDados(ENDERECO + nomeSerie.replace(" ", "+") + API_KEY);
        DadosSerie dadosSerie = conversor.obterDados(json, DadosSerie.class);
        System.out.println(dadosSerie);



        List<DadosTemporada> temporadas = new ArrayList<>();

		for (int i = 1; i<=dadosSerie.totalTemp(); i++){

			json = consumoApi.obterDados(ENDERECO +nomeSerie.replace(" ", "+")
                    +"&season="+i+ API_KEY);
			DadosTemporada dadosTemporada = conversor.obterDados(json, DadosTemporada.class);
			temporadas.add(dadosTemporada);
		}
		temporadas.forEach(System.out::println);

//        for (int i=0; i< dadosSerie.totalTemp(); i++){
//            List<DadosEpisodios> episodiosTemporadas = temporadas.get(i).episodios();
//            for (int j = 0; j <episodiosTemporadas.size(); j++){
//                System.out.println(episodiosTemporadas.get(j).titulo());
//            }
//        }

        temporadas.forEach(t -> t.episodios().forEach(e -> System.out.println(e.titulo())));

        List<DadosEpisodios> dadosEpisodios = temporadas.stream()
                .flatMap(t-> t.episodios().stream())
                .collect(Collectors.toList());

        System.out.println("\n top 5");
        dadosEpisodios.stream().filter(e -> !e.avaiacao().equalsIgnoreCase("N/A"))
                .sorted(Comparator.comparing(DadosEpisodios::avaiacao).reversed())
                .limit(5)
                .forEach(System.out::println);

        List<Episodio> episodios = temporadas.stream()
                .flatMap(t-> t.episodios().stream()
                        .map(d -> new Episodio(t.numeroTemp(), d))
                ).collect(Collectors.toList());

        episodios.forEach(System.out::println);

        System.out.println("A partir de que ano voce pretende ver os episodios? ");
        var ano = leitor.nextInt();
        leitor.nextLine();

        LocalDate databusca = LocalDate.of(ano, 1 , 1);

        DateTimeFormatter dtFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        episodios.stream()
                .filter( e -> e.getDataLancamento() != null && e.getDataLancamento().isAfter(databusca))
                .forEach(e -> System.out.println(
                        "temporada= " + e.getTemporada() +
                        " Episodio= "+ e.getTitulo() +
                        " Data Lançamento= " + e.getDataLancamento().format(dtFormat)));
        ;


    }
}
