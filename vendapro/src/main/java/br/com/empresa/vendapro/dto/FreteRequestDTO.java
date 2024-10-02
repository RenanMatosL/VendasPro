package br.com.empresa.vendapro.dto;

public class FreteRequestDTO {

    private String cepOrigem;  
    private String cepDestino;  
    private Double peso;  
    
    

    public String getCepOrigem() {  
        return cepOrigem;  
    }  

    public void setCepOrigem(String cepOrigem) {  
        this.cepOrigem = cepOrigem;  
    }  

    public String getCepDestino() {  
        return cepDestino;  
    }  

    public void setCepDestino(String cepDestino) {  
        this.cepDestino = cepDestino;  
    }  

    public Double getPeso() {  
        return peso;  
    }  

    public void setPeso(Double peso) {  
        this.peso = peso;  
    }  
} 