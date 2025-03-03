package com.betacom.cz.models;

import java.util.List;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "ordini")
public class Ordine {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_carrello")
	private Carrello carrello;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_utente")
	private Utente utente;

	@ManyToMany(mappedBy = "ordini",
			fetch = FetchType.EAGER)
	private List<Prodotto> prodotti;
	
	@Column(nullable = false)
	private Double totale;
	
	//Getters&Setters
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Carrello getCarrello() {
		return carrello;
	}

	public void setCarrello(Carrello carrello) {
		this.carrello = carrello;
	}

	public Utente getUtente() {
		return utente;
	}

	public void setUtente(Utente utente) {
		this.utente = utente;
	}

	public List<Prodotto> getProdotti() {
		return prodotti;
	}

	public void setProdotti(List<Prodotto> prodotti) {
		this.prodotti = prodotti;
	}
	
	public Double getTotale() {
		return totale;
	}

	public void setTotale(Double totale) {
		this.totale = totale;
	}
	
	//addProdotto
	public void addProdtto(Prodotto prodotto) {
	    prodotti.add(prodotto);
	    prodotto.getOrdini().add(this);
	}
	
}
