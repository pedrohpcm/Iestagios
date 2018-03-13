package iEstagios.modelo;

public class Apolice {
    
    private int idApolice;
    private String nomeSeguradora;
    private String cnpjSeguradora;
    private String numero;
    private String nomeApolice;
    private String data;
    private String arquivoPDF;

    public int getIdApolice() {
        return idApolice;
    }

    public void setIdApolice(int idApolice) {
        this.idApolice = idApolice;
    }

    public String getNomeSeguradora() {
        return nomeSeguradora;
    }

    public void setNomeSeguradora(String nomeSeguradora) {
        this.nomeSeguradora = nomeSeguradora;
    }

    public String getCnpjSeguradora() {
        return cnpjSeguradora;
    }

    public void setCnpjSeguradora(String cnpjSeguradora) {
        this.cnpjSeguradora = cnpjSeguradora;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getNomeApolice() {
        return nomeApolice;
    }

    public void setNomeApolice(String nomeApolice) {
        this.nomeApolice = nomeApolice;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getArquivoPDF() {
        return arquivoPDF;
    }

    public void setArquivoPDF(String arquivoPDF) {
        this.arquivoPDF = arquivoPDF;
    }
    
}
