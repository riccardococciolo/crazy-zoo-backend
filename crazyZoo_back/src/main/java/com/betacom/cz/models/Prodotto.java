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
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "prodotti")
public class Prodotto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column
	private String titolo;
	
	@Column
	private Double prezzo;
	
	@Column
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
	
	
}
