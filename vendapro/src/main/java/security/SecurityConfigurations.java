package security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfigurations {

	@Autowired
	private SecurityFilter securityFilter;

	// Anotacao para leitura do metodo, expondo o retorno do metodo
	@Bean
	// Configurando o processo de autenticação e autorizacao;
	// precisamos receber nos parenteses deste metodo outra clase do spring
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

		// O retorno o proprio http pelo spring oferece o parametro p gente com o metodo
		// para desabilitar a proteção contra ataques
		// CSRF (cross-site request forgery) pois usaremos autenticacao via token que ja
		// e uma proteção contra esse ataque
		return http.csrf(csrf -> csrf.disable())
				// metodo para mostrar como sera o gerenciamento da sessao (statement)
				.sessionManagement(sm -> sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
				// Criar o objeto securityFilterChain
				.build();

	}

	// Esse método ensina ao spring a injetar objetos, entao temos que anotar com
	// @bean, para exportar a classe para o spring,
	// fazendo com que ele consiga carrega-la e realizar sua injeção de dependencias
	// em outras classes
	@Bean
	// Como o spring nao consegue injatar automaticamente o AuthenticationManager,
	// temos que ensina-lo, criando o metodo abaixo
	public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {

		// Retornamos a classe que sabe criar o objeto authenticationManager, este
		// metodo precisa lancar uma exception
		return configuration.getAuthenticationManager();

	}

	// Como nao configuramos o spring a usar o armazenamento de senhas usando o hash
	// de senhas bcrypt, temos que ensinar isso
	// para o sprin
	@Bean
	// Criamos o metodo abaixo que devolve o algoritmo que representa o hashde senha
	// "PasswordEncoder"
	public PasswordEncoder passwordEncoder() {
		// Ensinamos ao spring que é para usar o algoritmo abaixo para hash de senha
		return new BCryptPasswordEncoder();
	}

}
