package br.com.empresa.vendapro.service;

import br.com.empresa.vendapro.dao.VendedorDao;
import br.com.empresa.vendapro.excecoes.RegistroJaExisteException;
import br.com.empresa.vendapro.model.Vendedor;
import org.springframework.beans.factory.annotation.Autowired;  
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;  
import org.springframework.stereotype.Service;  

@Service  
public class VendedorService {  

    @Autowired  
    private VendedorDao vendedorDao;  

    public Vendedor cadastrarVendedor(Vendedor vendedor) throws RegistroJaExisteException {  
        // Criptografa a senha antes de salvar  
        vendedor.setSenha(vendedor.getSenha());  
        vendedorDao.salvarVendedor(vendedor);  
        return vendedor;  
    }  

    public boolean validarSenha(Vendedor vendedor, String senha) {  
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();  
        return encoder.matches(senha, vendedor.getSenha());  
    }  

    public Vendedor buscarPorEmail(String email) {  
        return vendedorDao.buscarPorEmail(email);  
    }  
}  