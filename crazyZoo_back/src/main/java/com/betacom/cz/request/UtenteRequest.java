package com.betacom.cz.request;

public class UtenteRequest {
	
	private Integer id;
	private String nome;
	private String cognome;
	private String username;
	private String password;
	private String email;
	private String cellulare;	
	private String ruolo;
	private String via;
	private String civico;
	private String cap;
	private String citta;
	
	
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
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
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
	
	public String getVia() {
		return via;
	}

	public void setVia(String via) {
		this.via = via;
	}

	public String getCivico() {
		return civico;
	}

	public void setCivico(String civico) {
		this.civico = civico;
	}

	public String getCap() {
		return cap;
	}

	public void setCap(String cap) {
		this.cap = cap;
	}

	public String getCitta() {
		return citta;
	}

	public void setCitta(String citta) {
		this.citta = citta;
	}
	
	public String getRuolo() {
		return ruolo;
	}

	public void setRuolo(String ruolo) {
		this.ruolo = ruolo;
	}

	//Costruttori
	public UtenteRequest(Integer id, String nome, String cognome, String username, String password, String email,
			String cellulare, String ruolo, String via, String civico, String cap, String citta) {
		super();
		this.id = id;
		this.nome = nome;
		this.cognome = cognome;
		this.username = username;
		this.password = password;
		this.email = email;
		this.cellulare = cellulare;
		this.ruolo = ruolo;
		this.via = via;
		this.civico = civico;
		this.cap = cap;
		this.citta = citta;
	}

	public UtenteRequest(Integer id, String nome, String cognome, String username, String password, String email,
			String cellulare, String ruolo) {
		super();
		this.id = id;
		this.nome = nome;
		this.cognome = cognome;
		this.username = username;
		this.password = password;
		this.email = email;
		this.cellulare = cellulare;
		this.ruolo = ruolo;
	}
	
	public UtenteRequest() {
		super();
	}
	
}
