package security;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;

import br.com.empresa.vendapro.model.Usuario;

@Service
public class TokenService {
	
	public String gerarToken (Usuario usuario) {
		
		try {
			
			Algorithm algoritmo = Algorithm.HMAC256("12345678");
			return JWT.create()
					.withIssuer("API vendapro")
					.withSubject(usuario.getLogin())
					.withExpiresAt(dataExpiracao())
					.sign(algoritmo);
			} catch (JWTCreationException exception) {
				throw new RuntimeException("erro ao gerar token jwt", exception);
			}
	}

	private Instant dataExpiracao() {
		return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
	}

}
