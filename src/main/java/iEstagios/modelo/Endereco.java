package iEstagios.modelo;

import java.io.Serializable;

public class Endereco implements Serializable{
	
    private int id;
    private String rua;
    private String bairro;
    private int numero;
    private String cep;
    private String cidade;
    private String estado;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
        
	
    public String getRua() {
    	return rua;
    }
    public void setRua(String rua) {
	this.rua = rua;
    }
    public String getBairro() {
	return bairro;
    }
    public void setBairro(String bairro) {
	this.bairro = bairro;
    }
    public int getNumero() {
    	return numero;
    }
    public void setNumero(int numero) {
	this.numero = numero;
    }
    public String getCep() {
    	return cep;
    }
    public void setCep(String cep) {
	this.cep = cep;
    }
    public String getCidade() {
	return cidade;
    }
    public void setCidade(String cidade) {
    	this.cidade = cidade;
    }
    public String getEstado() {
	return estado;
    }
    public void setEstado(String estado) {
	this.estado = estado;
    }

    @Override
    public String toString() {
    	return "Endereco [rua=" + rua + ", bairro=" + bairro + ", numero=" + numero + ", cep=" + cep + ", cidade="
		+ cidade + ", estado=" + estado + "]";
    }
	
}
