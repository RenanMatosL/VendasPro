package br.com.empresa.vendapro.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import br.com.empresa.vendapro.model.Feedback; // Importa RestTemplate para fazer chamadas REST   

@Service
public class FreteService {

	private static final String URL_API_CORREIOS = "http://viacep.com.br/ws/";

	public String calcularFrete(String cepOrigem, String cepDestino, Double peso) {
		RestTemplate restTemplate = new RestTemplate(); // Criação de uma instância do RestTemplate para fazer
														// requisições

		// Montagem da URL da API com os parâmetros necessários para calcular o frete
		String url = URL_API_CORREIOS + "frete?CepOrigem=" + cepOrigem + "&cepDestino=" + cepDestino + "&peso=" + peso;

		// Faz uma requisição GET à API e obtém a resposta como String
		String response = restTemplate.getForObject(url, String.class);

		return response; // Retorna a resposta da API para o calculo de frete

	}

}
