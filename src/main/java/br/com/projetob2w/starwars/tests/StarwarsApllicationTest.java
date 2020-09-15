package br.com.projetob2w.starwars.tests;

import java.util.List;

import br.com.projetob2w.starwars.StarwarsApplication;
import br.com.projetob2w.starwars.connection.Planeta;
import junit.framework.*;

public class StarwarsApllicationTest extends TestCase {

	// Testa se a lista de planetas está vazia
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

	public boolean testaBuscaPorNome(String nome) {
		StarwarsApplication starwars = new StarwarsApplication();
			Planeta retorno = starwars.buscarPorNome(nome);
			if (retorno != null) {
				return true;
			} else {
				return false;
			}

		}
	}


