package br.com.projetob2w.starwars.teste;

import br.com.projetob2w.starwars.connection.*;
import br.com.projetob2w.starwars.*;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import br.com.projetob2w.starwars.StarwarsApplication;
import br.com.projetob2w.starwars.connection.ArquivoRepository;
import br.com.projetob2w.starwars.connection.Planeta;
import junit.framework.*;
import org.mockito.Matchers;
import static org.mockito.Mockito.*;
import org.powermock.api.mockito.PowerMockito;

public class StarwarsApllicationTest extends TestCase {
	
	
	
	private ArquivoService arquivoService = mock(ArquivoService.class);
	
	
	
	@Test
	public void testlistarPlanetas() {
		StarwarsApplication starwars = new StarwarsApplication();
		
		List<Planeta> retorno = 
		when(arquivoService.findAll()).thenReturn(retorno);
		
		Assert.assertNotNull(retorno);
		
	}
	
	@Test
	public boolean testListaDePlanetasVazia() {
		StarwarsApplication starwars = new StarwarsApplication();
		List<Planeta> planeta = starwars.planetas();

		if (planeta.isEmpty()) {
			return true;
		} else {
			return false;
		}
	}

	// Testa se está passando um id
	@Test
	public boolean testaBuscaporIdNotNull(String id) {
		StarwarsApplication starwars = new StarwarsApplication();
		if (id == null) {
			return false;
		} else {
			starwars.buscarPorId(id);
			return true;
		}
	}

	// testa se está adicionando planeta
	@Test
	public boolean testaAdicionarPlaneta(String nome, String clima, String terreno) {
		StarwarsApplication starwars = new StarwarsApplication();
		String retorno = starwars.adicionarPlaneta(nome, clima, terreno);

		if (retorno == "Sucesso!") {
			return true;
		} else {
			return false;

		}
	}

	// testar criar um planeta com campos null

	// testa se a busca por nome está funcionando
    @Test
	public boolean testaBuscaPorNome(String nome) {
		StarwarsApplication starwars = new StarwarsApplication();
			Planeta retorno = starwars.buscarPorNome(nome);
			if (retorno != null) {
				return true;
			} else {
				return false;
			}

		}
	
    @Test
	public boolean testaRemoverPlaneta(String id) {
		StarwarsApplication starwars = new StarwarsApplication();
		String retorno = starwars.removerPlaneta(id);
		if (retorno == "Removido com Sucesso!") {
			return true;
		}else {
			return false;
		}
	}
	
	}


