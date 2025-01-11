package br.com.alura.screenmatch;

import br.com.alura.screenmatch.model.DadosSerie;
import br.com.alura.screenmatch.service.ConsumoApi;
import br.com.alura.screenmatch.service.ConverteDados;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ScreenmatchApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(ScreenmatchApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception
	{
		// instancia a classe que consome a API
		var consumoApi = new ConsumoApi();

		// obtenho os dados da serie e jogo numa variavel
		var json = consumoApi.obterDados("https://www.omdbapi.com/?t=Family+Guy&apikey=86d67965");
		// print da variavel com o json cru
		System.out.println(json);
		// instancio o conversor
		// função dele é desserializar o Json e converte no formato da classe
		ConverteDados conversor = new ConverteDados();
		DadosSerie dadosSerie = conversor.obterDados(json, DadosSerie.class);
		// print dos dados ja convertidos
		System.out.println(dadosSerie);
	}
}
