package com.betacom.cz.models;

import java.util.List;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="carrelli")
public class Carrello {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@OneToOne
	@JoinColumn(name="id_utente",
	referencedColumnName = "id")
	private Utente utente;

	@OneToMany(mappedBy = "carrello")
	private List<Ordine> ordini;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "prodotto_carrello", 
	joinColumns = @JoinColumn(name = "id_carrello"),
	inverseJoinColumns = @JoinColumn(name = "id_prodotto"))
	private List<Prodotto> prodotti;

	//Getters&Setters
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Utente getUtente() {
		return utente;
	}

	public void setUtente(Utente utente) {
		this.utente = utente;
	}
	public List<Ordine> getOrdini() {
		return ordini;
	}

	public void setOrdini(List<Ordine> ordini) {
		this.ordini = ordini;
	}

	public List<Prodotto> getProdotti() {
		return prodotti;
	}

	public void setProdotti(List<Prodotto> prodotti) {
		this.prodotti = prodotti;
	}
	
}
