package br.com.projetob2w.starwars.teste;

import br.com.projetob2w.starwars.connection.*;
import br.com.projetob2w.starwars.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
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

}
