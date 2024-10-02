package br.com.empresa.vendapro.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Subcategoria {
	
	

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idSubcategoria;

	public Subcategoria() {
	}

	@Column(name = "NOME_SUB_CATEGORIA", length = 250, nullable = false)
	private String nome;

	@ManyToOne
	@JoinColumn(name = "ID_CATEGORIA")
	private Categoria categoria;
	
	// construtor para que na classe DataInitializer a lista de subcategorias possa compilar corretamente.
	public Subcategoria(String nome, Categoria categoria) {
		this.nome = nome;
		this.categoria = categoria;
	}

	public Long getIdSubcategoria() {
		return idSubcategoria;
	}

	public String getNomeSubcategoria() {
		return nome;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setIdSubcategoria(Long idSubcategoria) {
		this.idSubcategoria = idSubcategoria;
	}

	public void setNomeSubcategoria(String nomeSubcategoria) {
		this.nome = nomeSubcategoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

}
