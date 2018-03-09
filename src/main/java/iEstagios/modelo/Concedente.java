package iEstagios.modelo;

import java.io.Serializable;

public class Concedente implements Serializable{
    
    private int id;
    private String razaoSocial;
    private String nome;
    private String cnpj;
    private String ramoDeAtividade;
    private String tipoConcedente;
    private String representante;
    private String cargo;
    private Conta conta;
    private Endereco endereco;
    private Telefone telefone;

    public Conta getConta() {
        return conta;
    }

    public void setConta(Conta conta) {
        this.conta = conta;
    }
        
    public String getRepresentante() {
        return representante;
    }
    
    public void setRepresentante(String representante) {
        this.representante = representante;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public int getId() {
        return id;
    }

    public void setId(int idConcedente) {
        this.id = idConcedente;
    }

    public String getRazaoSocial() {
        return razaoSocial;
    }
    
    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public Telefone getTelefone() {
        return telefone;
    }

    public void setTelefone(Telefone telefone) {
        this.telefone = telefone;
    }

    public void setRazaoSocial(String razaoSocial) {
        this.razaoSocial = razaoSocial;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getRamoDeAtividade() {
        return ramoDeAtividade;
    }

    public void setRamoDeAtividade(String ramoDeAtividade) {
        this.ramoDeAtividade = ramoDeAtividade;
    }

    public String getTipoConcedente() {
        return tipoConcedente;
    }

    public void setTipoConcedente(String tipoConcedente) {
        this.tipoConcedente = tipoConcedente;
    }

}

