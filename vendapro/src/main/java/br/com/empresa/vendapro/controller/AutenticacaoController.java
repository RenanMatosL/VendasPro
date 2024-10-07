package br.com.empresa.vendapro.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.empresa.vendapro.dto.DadosAutenticacao;
import br.com.empresa.vendapro.model.Usuario;
import jakarta.validation.Valid;
import security.DadosTokenJWT;
import security.TokenService;

@RestController
@RequestMapping("/login")
public class AutenticacaoController {
	
	//Precisamos consultar o banco de dados e disparar o processo de autenticacao, que é o loadUserByUserName, porém 
	// nao chamamos a classe deste metodo, (AutenticationService) diretamente, chamamos outra classe que por baixo dos panos chama
	//esta classe, entao injetamos abaixo:
	@Autowired 
	private AuthenticationManager manager;
	
	//injetando a nossa classe Token e nao do spring
	@Autowired
    private TokenService tokenService;
	
	@PostMapping
	//Ao criar este metodo de efetuar login, recebemos o dto com os dados de login e senha vindo do app ou postman, usamos as classes
	//do Spring security para disparar o processo de auteticação, criando o dto do spring security, 
	//(UsernamePasswordAuthenticationToken), passamos o login e senha vindo do dto e usamos a classe injetada acima: 
	// (AuthenticationManager) para disparar o processo de autenticação, chamando a service, que chama a dao, que faz a consulta no
	//banco, verifica se a senha e usuario existe, se nao devolve 403, se sim, codigo 200 ok como resposta.
	//A principioda erro de compilacao pois nao existe a classe e criamos em seguida
	public ResponseEntity efetuarLogin(@RequestBody @Valid DadosAutenticacao dados) {
		
		//chamando o metodo authentication devolve o objeto que representa o usurario autenticado no sistema, guardaremos na variavel
	

		//Esse token é o login e a senha, ele ja esta representado no DTO acima, porem esse nao é o parametro que o Spring espera,
		//Ele espera uma outra classe dele e nao do nosso projeto, temos que criar a classe dele que representa o usuario e a senha,
		//E passamos no construtor o nosso DTO que é o dados.getLogin e dados.getSenha.
		//Temos que fazer essa conversao pois o spring espera o dto dele. agora, la embaixo temos que inserir o retorno: 	
		var authenticationToken = new UsernamePasswordAuthenticationToken(dados.getLogin(), dados.getSenha()); 	
		
		//Da erro de compilacao pois nao existe a variavel token, criamos ela acima
		//este metodo, esta variável authentication representa um usuario, que passamos ele abaixo como retorno
		var authentication = manager.authenticate(authenticationToken);
			 
		var tokenJWT = tokenService.gerarToken((Usuario) authentication.getPrincipal());
		
		
		//Retornoamos o ok, cod 200 para informar qdo a requisicao for processada com sucesso
		//.getPrincipal é para conseguirmos pegar o usuario logado
		return ResponseEntity.ok(new DadosTokenJWT (tokenJWT));
		
	}

}
