package com.betacom.cz.models;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
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

	@OneToOne(mappedBy = "carrello",
			cascade = CascadeType.REMOVE)
	private Ordine ordine;

	@ManyToMany(fetch = FetchType.EAGER,
			cascade = {CascadeType.PERSIST,
					CascadeType.MERGE})
	@JoinTable(name = "prodotto_carrello", 
	joinColumns = @JoinColumn(name = "id_carrello"),
	inverseJoinColumns = @JoinColumn(name = "id_prodotto"))
	private List<Prodotto> prodotti;


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

	public Ordine getOrdine() {
		return ordine;
	}

	public void setOrdine(Ordine ordine) {
		this.ordine = ordine;
	}

	public List<Prodotto> getProdotti() {
		return prodotti;
	}

	public void setProdotti(List<Prodotto> prodotti) {
		this.prodotti = prodotti;
	}
}
