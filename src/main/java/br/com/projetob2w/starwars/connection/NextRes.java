package br.com.projetob2w.starwars.connection;

import java.util.List;

public class NextRes {
	
	public String next;
	private List<Result> results;
	
	public String getNext() {
		return next;
	}
	public void setNext(String next) {
		this.next = next;
	}
	public List<Result> getResults() {
		return results;
	}
	public void setResults(List<Result> results) {
		this.results = results;
	}

	

}
