package br.com.empresa.vendapro.dto;

public class FreteResponseDTO {

    private Double valorFrete;  
    private String prazoEntrega;  

    public FreteResponseDTO(Double valorFrete, String prazoEntrega) {  
        this.valorFrete = valorFrete;  
        this.prazoEntrega = prazoEntrega;  
    }  

    // Getters e Setters  
    public Double getValorFrete() {  
        return valorFrete;  
    }  

    public void setValorFrete(Double valorFrete) {  
        this.valorFrete = valorFrete;  
    }  

    public String getPrazoEntrega() {  
        return prazoEntrega;  
    }  

    public void setPrazoEntrega(String prazoEntrega) {  
        this.prazoEntrega = prazoEntrega;  
    }  
}  
