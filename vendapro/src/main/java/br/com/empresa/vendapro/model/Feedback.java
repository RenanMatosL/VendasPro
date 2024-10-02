package br.com.empresa.vendapro.model;

import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table (name= "FEEDBACKS")
public class Feedback {
	
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private long idFeedback;
	
	@ManyToOne // Relacionamento ManyToOne com Cliente (muitos feedbacks podem estar relacionados a um cliente).
	@JoinColumn (name = "ID_CLIENTE", nullable = false)  // Chave estrangeira para ID do Cliente 
	private Cliente cliente;
	
	@ManyToOne 
	@JoinColumn (name = "ID_PRODUTO", nullable = false)
	private Produto produto;
	
	@Column (name = "NOTA", nullable = false)
	private Integer nota; //Nota de 1 a 5

	public long getIdFeedback() {
		return idFeedback;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public Produto getProduto() {
		return produto;
	}

	public Integer getNota() {
		return nota;
	}

	public void setIdFeedback(long idFeedback) {
		this.idFeedback = idFeedback;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public void setNota(Integer nota) {
		this.nota = nota;
	}

	@Override
	public int hashCode() {
		return Objects.hash(idFeedback);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Feedback other = (Feedback) obj;
		return idFeedback == other.idFeedback;
	}
	
	

}
