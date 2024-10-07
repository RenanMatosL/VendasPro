package br.com.empresa.vendapro.dao;


import java.util.List;

import org.springframework.security.core.userdetails.UserDetails;

import br.com.empresa.vendapro.enuns.StatusUsuarioAtivo;
import br.com.empresa.vendapro.excecoes.RegistroJaExisteException;
import br.com.empresa.vendapro.excecoes.RegistroNaoEncontradoException;
import br.com.empresa.vendapro.model.Usuario;


import java.util.List;

public interface UsuarioDao{
	public List<Usuario> carregarTodosUsuarios();
	public Usuario consultarUsuarioPorId(Long idUsuario);
	public Usuario salvarUsuario(Usuario usuario) throws RegistroJaExisteException;
	public void alterarStatusUsuario(Long idUsuario, StatusUsuarioAtivo statusUsuarioAtivo) throws RegistroNaoEncontradoException;
	//Metodo implantado do UserDetails para localizar o usuario pelo id
	public UserDetails findUsuarioByLogin(String login);
}