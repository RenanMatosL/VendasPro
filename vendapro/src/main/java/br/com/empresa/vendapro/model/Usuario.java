package br.com.empresa.vendapro.model;

import java.util.Collection;
import java.util.List;
import java.util.Objects;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Table (name = "USUARIOS")
@Entity (name = "Usuario")
//Para o spring entender que o campo usuario e senha representa de fato esses dados de segurança, tempos que implementar UserDetails
public class Usuario implements UserDetails {
	
	 private static final long serialVersionUID = 1L; // Adicione um ID de serialização 	


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "LOGIN", nullable = false, length = 100, unique = true)
	private String login;

	@Column(name = "SENHA",  length = 250,  nullable = false)
	private String senha; // Senha criptografada

	public Usuario() {
		super();
	}

	public Usuario(Long id, String login, String senha) {
		super();
		this.id = id;
		this.login = login;
		this.senha = senha;
	}

	public Long getId() {
		return id;
	}

	public String getLogin() {
		return login;
	}

	public String getSenha() {
		return senha;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Usuario other = (Usuario) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		//Representa controles de permissões, de perfis de acesso, nao utilizaremos o controle de perfil, mas o spring precisa que 
		//devolvamos uma colecao representando os perfis, vamos simular a colecao somente para compilar o projeto
		return List.of(new SimpleGrantedAuthority("ROLE_USER"));
	}

	@Override
	public String getPassword() {
		// retornamos o atributo que representa a senha:
		return senha;
	}

	@Override
	public String getUsername() {
		// retornamos o atributo que representa o usuario:
		return login;
	}
	
	
	

}