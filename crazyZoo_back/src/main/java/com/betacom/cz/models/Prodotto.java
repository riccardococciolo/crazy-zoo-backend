package com.betacom.cz.models;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "prodotti")
public class Prodotto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(nullable = false)
	private String titolo;

	@Column(nullable = false)
	private Double prezzo;

	@Column(nullable = false)
	private Integer quantita;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_animale")
	private Animale animale;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_marca")
	private Marca marca;

	@OneToMany(mappedBy = "prodotto",
			fetch = FetchType.EAGER,
			cascade = CascadeType.REMOVE)
	private List<Recensione> recensioni;

	@ManyToMany(mappedBy = "prodotti",
			fetch = FetchType.EAGER,
			cascade = {CascadeType.PERSIST,
					CascadeType.MERGE})
	private List<Carrello> carrelli;

	@ManyToMany(fetch = FetchType.EAGER,
			cascade = {CascadeType.PERSIST,
					CascadeType.MERGE})
	@JoinTable(name = "prodotto_ordine", 
	joinColumns = @JoinColumn(name = "id_prodotto"),
	inverseJoinColumns = @JoinColumn(name = "id_ordine"))
	private List<Ordine> ordini;

	@OneToMany(mappedBy = "prodotto",
			fetch = FetchType.EAGER,
			cascade = CascadeType.REMOVE)
	private List<Immagine> immagini;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitolo() {
		return titolo;
	}

	public void setTitolo(String titolo) {
		this.titolo = titolo;
	}

	public Double getPrezzo() {
		return prezzo;
	}

	public void setPrezzo(Double prezzo) {
		this.prezzo = prezzo;
	}

	public Integer getQuantita() {
		return quantita;
	}

	public void setQuantita(Integer quantita) {
		this.quantita = quantita;
	}

	public Animale getAnimale() {
		return animale;
	}

	public void setAnimale(Animale animale) {
		this.animale = animale;
	}

	public Marca getMarca() {
		return marca;
	}

	public void setMarca(Marca marca) {
		this.marca = marca;
	}

	public List<Recensione> getRecensioni() {
		return recensioni;
	}

	public void setRecensioni(List<Recensione> recensioni) {
		this.recensioni = recensioni;
	}

	public List<Carrello> getCarrelli() {
		return carrelli;
	}

	public void setCarrelli(List<Carrello> carrelli) {
		this.carrelli = carrelli;
	}

	public List<Ordine> getOrdini() {
		return ordini;
	}

	public void setOrdini(List<Ordine> ordini) {
		this.ordini = ordini;
	}

	public List<Immagine> getImmagini() {
		return immagini;
	}

	public void setImmagini(List<Immagine> immagini) {
		this.immagini = immagini;
	}
}
