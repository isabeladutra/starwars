package br.com.projetob2w.starwars.teste;

import br.com.projetob2w.starwars.connection.*;
import br.com.projetob2w.starwars.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.junit.internal.runners.statements.Fail;

import static org.junit.Assert.*;
import br.com.projetob2w.starwars.StarwarsApplication;
import br.com.projetob2w.starwars.connection.ArquivoRepository;
import br.com.projetob2w.starwars.connection.Planeta;
import junit.framework.*;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;
import org.springframework.test.context.event.annotation.BeforeTestMethod;

import com.fasterxml.jackson.core.JsonProcessingException;

public class StarwarsApllicationTest {


	private ArquivoService arquivoService = Mockito.mock(ArquivoService.class);

	@Before
	public void initMocks() {
		MockitoAnnotations.initMocks(this);
	}



	@Test
	public void testlistarPlanetas() {
		StarwarsApplication starwars = new StarwarsApplication();

		Planeta planet = new Planeta("Terra", "diverso", "diverso");

		List<Planeta> retorno = new ArrayList<Planeta>();

		retorno.add(planet);

		when(arquivoService.findAll()).thenReturn(retorno);
		starwars.setArquivoService(arquivoService);
		starwars.planetas();
		

		Assert.assertNotNull(retorno);

	}
	
	
	@Test
	public void testListarComQuantidadeDeAparicoes() {
		StarwarsApplication starwars = new StarwarsApplication();

		Planeta planet = new Planeta("Terra", "diverso", "diverso");
		planet.setQuantidadeaparicoes(1);
		
		
		Map<String, Integer> mapa = new HashMap<String, Integer>();
		mapa.put("Terra", 1);
		mapa.put("Tatooine", 5);
		mapa.put("Kamino", 1);

		List<Planeta> retorno = new ArrayList<Planeta>();

		retorno.add(planet);

		when(arquivoService.findAll()).thenReturn(retorno);
		
		
		starwars.setArquivoService(arquivoService);
		starwars.setMapaNomes(mapa);
		
		List<Planeta> lista_retorno = starwars.planetas();
		
			Assert.assertTrue(lista_retorno.contains(planet) == true);
	        
	}
	
	
	@Test 
	public void testBuscaPorId() {
		StarwarsApplication starwars = new StarwarsApplication();
		String id = "1234";
		Planeta planet = new Planeta("Terra", "diverso", "diverso");
		planet.setId(id);
		planet.setQuantidadeaparicoes(1);
		
		Map<String, Integer> mapa = new HashMap<String, Integer>();
		mapa.put("Terra", 1);
		mapa.put("Tatooine", 5);
		mapa.put("Kamino", 1);
		
        when(arquivoService.findById(id)).thenReturn(planet);
		starwars.setMapaNomes(mapa);
		starwars.setArquivoService(arquivoService);
		Planeta planeta_retornado = starwars.buscarPorId(id);
		Assert.assertEquals(planet, planeta_retornado);
		
	}
	
	@Test
	public void testAdicionarPlaneta() {
		StarwarsApplication starwars = new StarwarsApplication();

		String nome = "Terra";
		String clima = "diverso";
		String terreno = "diverso";
		 String retornoadd;
		 
	
		starwars.setArquivoService(arquivoService);
	    retornoadd = starwars.adicionarPlaneta(nome, clima, terreno);
	    	Assert.assertEquals("Sucesso!", retornoadd);
	    
	     //testar o retorno com string
		
		
	}
	
	@Test
	public void testBuscaPorNome() throws JsonProcessingException {
		StarwarsApplication starwars = new StarwarsApplication();
		String nome = "Terra";
		Planeta planet = new Planeta("Terra", "diverso", "diverso");
		Map<String, Integer> mapa = new HashMap<String, Integer>();
		mapa.put("Terra", 1);
		mapa.put("Tatooine", 5);
		mapa.put("Kamino", 1);
		
		
		when(arquivoService.buscarPorNome(nome)).thenReturn(planet);
		starwars.setMapaNomes(mapa);
		starwars.setArquivoService(arquivoService);
		String string_retornada = starwars.buscarPorNome(nome);
		
		if(string_retornada == "Planeta inexistente") {
		    Assert.fail();
		}else {
			Assert.assertTrue(string_retornada.contains(nome));
		}
			
		
	}
	
	
	
	
	@Test
	public void testRemoverPlaneta () {
		StarwarsApplication starwars = new StarwarsApplication();
		String id = "1234";
		Planeta planet = new Planeta("Terra", "diverso", "diverso");
		planet.setId(id);
		
	
		starwars.setArquivoService(arquivoService);
		String retorno ;
		when(arquivoService.findById(id)).thenReturn(planet);
		retorno = starwars.removerPlaneta(id);
		
		Assert.assertEquals("Removido com Sucesso!", retorno);
		
		
		
	}
	
	
	@Test
	public void testRemoverPlanetaRetornandoIdNulo () {
		StarwarsApplication starwars = new StarwarsApplication();
		String id = "1234";
		
	
		starwars.setArquivoService(arquivoService);
		String retorno ;
		when(arquivoService.findById(id)).thenReturn(null);
		retorno = starwars.removerPlaneta(id);
		
		Assert.assertEquals("id inexistente!", retorno);
		
		
		
	}

}
