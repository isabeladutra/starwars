package br.com.projetob2w.starwars.connection;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import static org.springframework.data.mongodb.core.query.Criteria.where;
 
@Service
public class ArquivoService {
 
    @Autowired
    private ArquivoRepository arquivoRepository;
    
    public  Planeta buscarPorNome (String nome) {
    	Planeta planeta = new Planeta(nome, null, null);
    	return arquivoRepository.findOne(Example.of(planeta)).orElse(null);
    	
    }
 
    public void salvar( String nome, String clima, String terreno) {
    	
        arquivoRepository.save(new Planeta(nome, clima, terreno));
    }
 
    public List<Planeta> findAll() {
       return arquivoRepository.findAll();
    }
 
    public long count() {
        return arquivoRepository.count();
    }
 
    public Planeta findById(String id) {
        return arquivoRepository.findById(id).orElse(null);
    }
 
    public void delete(String id) {
        arquivoRepository.deleteById(id);
    }
    
    


 
}