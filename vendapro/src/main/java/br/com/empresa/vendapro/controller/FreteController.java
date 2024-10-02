package br.com.empresa.vendapro.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.empresa.vendapro.service.FreteService;

@RestController
@RequestMapping ("/api/frete")// // Mapeia a URL base para todos os endpoints deste controlador  
public class FreteController {
	
	@Autowired 
	private FreteService freteService; // Referência ao serviço que calcula o frete 
	
    // Método para calcular o frete a partir dos parâmetros recebidos na requisição GET  
	@GetMapping // Define que este método será chamado em requisições GET  
	public String calcularFrete (@RequestParam String cepOrigem, @RequestParam String cepDestino, @RequestParam Double peso ) {
		
		return freteService.calcularFrete(cepOrigem, cepDestino, peso);
		
		
	}

}
