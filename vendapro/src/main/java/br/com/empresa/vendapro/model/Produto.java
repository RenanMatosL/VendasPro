package br.com.empresa.vendapro.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "PRODUTO", schema = "dbo")
public class Produto implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_PRODUTO")
	private Long idProduto;

	@Column(name = "NOME", length = 250, nullable = false)
	private String nome;

	@Column(name = "PRECO", precision = 9, scale = 2, nullable = false)
	private BigDecimal preco;

	@Column(name = "CATEGORIA", nullable = false, length = 50)
	private String categoria;

	@Column(name = "COR", length = 30)
	private String cor;

	@Column(name = "TAMANHO", length = 20)
	private String tamanho;

	@Column(name = "PESO")
	private double peso;

	@Column(name = "ALTURA", nullable = false)
	private Double altura; // em cm

	@Column(name = "LARGURA", nullable = false)
	private Double largura; // em cm

	@Column(name = "PROFUNDIDADE", nullable = false)
	private Double profundidade; // em cm

	@Column(name = "ESTOQUE", nullable = false)
	private Integer estoque; // Quantidade em estoque

	@Column(name = "CEP_ORIGEM", nullable = false)
	private String cepOrigem; // CEP de origem do produto

	public Long getIdProduto() {
		return idProduto;
	}

	public String getNome() {
		return nome;
	}

	public BigDecimal getPreco() {
		return preco;
	}

	public String getCategoria() {
		return categoria;
	}

	public String getCor() {
		return cor;
	}

	public String getTamanho() {
		return tamanho;
	}

	public double getPeso() {
		return peso;
	}

	public Double getAltura() {
		return altura;
	}

	public Double getLargura() {
		return largura;
	}

	public Double getProfundidade() {
		return profundidade;
	}

	public Integer getEstoque() {
		return estoque;
	}

	public String getCepOrigem() {
		return cepOrigem;
	}

	public void setIdProduto(Long idProduto) {
		this.idProduto = idProduto;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setPreco(BigDecimal preco) {
		this.preco = preco;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public void setCor(String cor) {
		this.cor = cor;
	}

	public void setTamanho(String tamanho) {
		this.tamanho = tamanho;
	}

	public void setPeso(double peso) {
		this.peso = peso;
	}

	public void setAltura(Double altura) {
		this.altura = altura;
	}

	public void setLargura(Double largura) {
		this.largura = largura;
	}

	public void setProfundidade(Double profundidade) {
		this.profundidade = profundidade;
	}

	public void setEstoque(Integer estoque) {
		this.estoque = estoque;
	}

	public void setCepOrigem(String cepOrigem) {
		this.cepOrigem = cepOrigem;
	}

	@Override
	public int hashCode() {
		return Objects.hash(idProduto);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Produto other = (Produto) obj;
		return Objects.equals(idProduto, other.idProduto);
	}

	

}
