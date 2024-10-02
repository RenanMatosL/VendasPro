package br.com.empresa.vendapro.model;

import java.util.List;
import java.util.Objects;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Categoria {
	
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	@Column(name = "ID_CATEGORIA")
	private Long idCategoria;
	
	@Column(name = "NOME_CATEGORIA", length = 250, nullable = false)
	private String nomeCategoria;
	
	@OneToMany (mappedBy = "categoria", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List <Subcategoria> subcategorias;

	public Long getIdCategoria() {
		return idCategoria;
	}

	public String getNomeCategoria() {
		return nomeCategoria;
	}

	public List<Subcategoria> getSubcategorias() {
		return subcategorias;
	}

	public void setIdCategoria(Long idCategoria) {
		this.idCategoria = idCategoria;
	}

	public void setNomeCategoria(String nomeCategoria) {
		this.nomeCategoria = nomeCategoria;
	}

	public void setSubcategorias(List<Subcategoria> subcategorias) {
		this.subcategorias = subcategorias;
	}

	@Override
	public int hashCode() {
		return Objects.hash(idCategoria);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Categoria other = (Categoria) obj;
		return Objects.equals(idCategoria, other.idCategoria);
	}
	
	
	
	
	

}
