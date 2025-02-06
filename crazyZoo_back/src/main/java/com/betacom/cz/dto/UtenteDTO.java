package com.betacom.cz.dto;

public class UtenteDTO {
	
	private Integer id;
	private String nome;
	private String cognome;
	private String username;
	private String email;
	private String cellulare;
	private String ruolo;
	
	//Getters&Setters
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
	
	public String getCognome() {
		return cognome;
	}
	
	public void setCognome(String cognome) {
		this.cognome = cognome;
	}
	
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getCellulare() {
		return cellulare;
	}
	
	public void setCellulare(String cellulare) {
		this.cellulare = cellulare;
	}
	public String getRuolo() {
		return ruolo;
	}

	public void setRuolo(String ruolo) {
		this.ruolo = ruolo;
	}
	
	public UtenteDTO(Integer id, String nome, String cognome, String username, String email, String cellulare,
			String ruolo) {
		super();
		this.id = id;
		this.nome = nome;
		this.cognome = cognome;
		this.username = username;
		this.email = email;
		this.cellulare = cellulare;
		this.ruolo = ruolo;
	}

	public UtenteDTO() {
		super();
	}
	
	//toString
	@Override
	public String toString() {
		return "UtenteDTO [id=" + id + ", nome=" + nome + ", cognome=" + cognome + ", username=" + username + ", email="
				+ email + ", cellulare=" + cellulare + "]";
	}
	
}
