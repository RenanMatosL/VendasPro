package br.com.empresa.vendapro.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.empresa.vendapro.dao.UsuarioDao;

@Service
//Devemos implementar a classe UserDetailsService
public class AutenticacaoService implements UserDetailsService{
	
	@Autowired
	private UsuarioDao usuarioDao; 

	@Override
	//Devemos implementar o unico metodo desta implementação, o loadUserByUsername, esse e o metodo que o spring vai chamar automaticamente
	//no projeto sempre que for feito login no projeto, pois ela implementa a interface UserDetailsService e ele chama este metodo abaixo,
	//passando o username digitado no formulario de login
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		//precisamos de devolver algo, ou seja acessar o nosso banco de dados, então o injetamos acima o Dao.
		//E abaixo precisamos retornar o metodo que representa o nosso atributo como login e passamos para ele como parametro o username que 
		//esta na assinatura do metodo acima:
		//O metodo nao compila pq nao existe, clicamos no metodo e o criamos na classe Dao
		return usuarioDao.findUsuarioByLogin(username);
	}
	
	

}
