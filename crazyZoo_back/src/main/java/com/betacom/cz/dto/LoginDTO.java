package com.betacom.cz.dto;

public class LoginDTO {
	
    private String token;
    private String role;
    
	private Integer id;
	private String nome;
	private String cognome;
	private String username;
	private String email;
	private String cellulare;
	private Integer carrelloID;
    
    //Getters&Setters
	public String getToken() {
		return token;
	}
	
	public void setToken(String token) {
		this.token = token;
	}
	
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getRole() {
		return role;
	}
	
	public void setRole(String role) {
		this.role = role;
	}

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

	public Integer getCarrelloID() {
		return carrelloID;
	}

	public void setCarrelloID(Integer carrelloID) {
		this.carrelloID = carrelloID;
	}

	public LoginDTO(String token, String role, Integer id, String nome, String cognome, String username, String email,
			String cellulare, Integer carrelloID) {
		super();
		this.token = token;
		this.role = role;
		this.id = id;
		this.nome = nome;
		this.cognome = cognome;
		this.username = username;
		this.email = email;
		this.cellulare = cellulare;
		this.carrelloID = carrelloID;
	}     

	
	
}
