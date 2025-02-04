package com.betacom.cz.request;

public class TipologiaRequest {
	private Integer id;
	private String nome;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public TipologiaRequest(Integer id, String nome) {
		super();
		this.id = id;
		this.nome = nome;
	}
	public TipologiaRequest() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "TipologiaRequest [id=" + id + ", nome=" + nome + "]";
	}
	
	
}
