package br.com.projetob2w.starwars.connection;

import org.springframework.data.mongodb.repository.MongoRepository;

import br.com.projetob2w.starwars.connection.Planeta;

public interface ArquivoRepository extends MongoRepository<Planeta, String> {
	
	Planeta findByNome(String nome);
	

}