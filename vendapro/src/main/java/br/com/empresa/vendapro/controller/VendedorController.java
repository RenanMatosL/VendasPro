package br.com.empresa.vendapro.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.empresa.vendapro.excecoes.RegistroJaExisteException;
import br.com.empresa.vendapro.model.Vendedor;
import br.com.empresa.vendapro.service.VendedorService;

@RestController  
@RequestMapping("/vendedores")  
public class VendedorController {  

    @Autowired  
    private VendedorService vendedorService;  

    @PostMapping("/cadastro")  
    public ResponseEntity<Vendedor> cadastrar(@RequestBody Vendedor vendedor) throws RegistroJaExisteException {  
        Vendedor vendedorCadastrado = vendedorService.cadastrarVendedor(vendedor);  
        return ResponseEntity.ok(vendedorCadastrado);  
    }  

    @PostMapping("/login")  
    public ResponseEntity<String> login(@RequestBody Vendedor vendedor) {  
        Vendedor vendedorEncontrado = vendedorService.buscarPorEmail(vendedor.getEmail());  
        if (vendedorEncontrado != null && vendedorService.validarSenha(vendedorEncontrado, vendedor.getSenha())) {  
            return ResponseEntity.ok("Login bem-sucedido");  
        }  
        return ResponseEntity.status(401).body("Credenciais inválidas");  
    }  
}



