package br.com.projetob2w.starwars;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.projetob2w.starwars.connection.*;


@SpringBootApplication
@RestController
public class StarwarsApplication {

	@Autowired
	private ArquivoService arquivoService;
	
	public void setArquivoService(ArquivoService arquivoService) {
		this.arquivoService = arquivoService;
	}


	Map<String, Integer> mapaNomes = new HashMap<String, Integer>();

	public void setMapaNomes(Map<String, Integer> mapaNomes) {
		this.mapaNomes = mapaNomes;
	}
	
	private static final Logger log = LoggerFactory.getLogger(StarwarsApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(StarwarsApplication.class, args);
	}

	@GetMapping("/planetas")
	public List<Planeta> planetas() {
		List<Planeta> planet = arquivoService.findAll();
		for (int i = 0; i < planet.size(); i++) {
			String nome = planet.get(i).getNome();

			if ( mapaNomes.get(nome) != null) {

				planet.get(i).setQuantidadeaparicoes(mapaNomes.get(planet.get(i).getNome()));
			} else {
				planet.get(i).setQuantidadeaparicoes(0);
			}

		}

		return planet;
	}

	@GetMapping("/buscarporid")
	public Planeta buscarPorId(@RequestParam(value = "id") String id) {
		return arquivoService.findById(id);
	}
	
	@RequestMapping(value ="/adicionarplaneta", method = RequestMethod.POST)
	public String adicionarPlaneta(@RequestParam(value = "nome") String nome,
			@RequestParam(value = "clima") String clima, @RequestParam(value = "terreno") String terreno) {
		arquivoService.salvar(nome, clima, terreno);
		return "Sucesso!";
	}
	
	@GetMapping("/buscarpornome")
	public String buscarPorNome(@RequestParam(value = "nome") String nome) throws JsonProcessingException {
		Planeta planet = arquivoService.buscarPorNome(nome);
		ObjectMapper objectMapper = new ObjectMapper();
		if (planet != null) {
			planet.setQuantidadeaparicoes(mapaNomes.get(nome) != null ? mapaNomes.get(nome) : 0);
			return objectMapper.writeValueAsString(planet);
		}else {
			return objectMapper.writeValueAsString("Planeta inexistente");
		}
			
	
		

	}

	@RequestMapping(value ="/removerplaneta", method = RequestMethod.DELETE)
	public String removerPlaneta(@RequestParam(value = "id") String id) {
		if(arquivoService.findById(id) != null){
			arquivoService.delete(id);
			return "Removido com Sucesso!";
		}else {
			return "id inexistente!";
		}
		
		
	}

	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		return builder.build();
	}

	@Bean
	public CommandLineRunner run(RestTemplate restTemplate) throws Exception {
		return args -> {
			NextRes planet = restTemplate.getForObject("https://swapi.dev/api/planets/", NextRes.class);
			HttpHeaders headers = new HttpHeaders();
			headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
			HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);

			ResponseEntity<NextRes> result = restTemplate.exchange("https://swapi.dev/api/planets/",
					HttpMethod.GET, entity, NextRes.class);

			while (result.getBody().next != null) {

				List<Result> listaderesult = planet.getResults();

				for (int i = 0; i < listaderesult.size(); i++) {
					Result obj = listaderesult.get(i);
					String nome = obj.getName();
					List<String> filmes = obj.getFilms();
					int quantidadeaparicoes = filmes.size();

					mapaNomes.put(nome, quantidadeaparicoes);

				}

				result = restTemplate.exchange(result.getBody().next.replaceAll("http", "https"), HttpMethod.GET,
						entity, NextRes.class);

				log.info("Resultado da Chamada REST: " + planet.toString());
			}
			;
		};
	}
}
