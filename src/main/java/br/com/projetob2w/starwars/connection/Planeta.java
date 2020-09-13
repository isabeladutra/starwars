package br.com.projetob2w.starwars.connection;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "planetas")

public class Planeta {

	@Id
	private String id;
	private String clima;
	private String nome;
	private String terreno;
	private int quantidadeaparicoes;
	
	
     public int getQuantidadeaparicoes() {
		return quantidadeaparicoes;
	}


	public void setQuantidadeaparicoes(int quantidadeaparicoes) {
		this.quantidadeaparicoes = quantidadeaparicoes;
	}


	public Planeta( String nome, String clima, String terreno) {
    	 
     	this.nome = nome;
     	this.clima = clima;
     	this.terreno = terreno;
	}
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getClima() {
		return clima;
	}
	public void setClima(String clima) {
		this.clima = clima;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getTerreno() {
		return terreno;
	}
	public void setTerreno(String terreno) {
		this.terreno = terreno;
	}
	

	
}