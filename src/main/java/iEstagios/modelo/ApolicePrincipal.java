
package iEstagios.modelo;

public class ApolicePrincipal {
    
    private int idapolice;
    private String nomeseguradora;
    private String cnpjseguradora;
    private int numero;
    private String nomeapolice;
    private String data;
    private String arquivopdf;

    public int getIdapolice() {
        return idapolice;
    }

    public void setIdapolice(int idapolice) {
        this.idapolice = idapolice;
    }

    public String getNomeseguradora() {
        return nomeseguradora;
    }

    public void setNomeseguradora(String nomeseguradora) {
        this.nomeseguradora = nomeseguradora;
    }

    public String getCnpjseguradora() {
        return cnpjseguradora;
    }

    public void setCnpjseguradora(String cnpjseguradora) {
        this.cnpjseguradora = cnpjseguradora;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getNomeapolice() {
        return nomeapolice;
    }

    public void setNomeapolice(String nomeapolice) {
        this.nomeapolice = nomeapolice;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getArquivopdf() {
        return arquivopdf;
    }

    public void setArquivopdf(String arquivopdf) {
        this.arquivopdf = arquivopdf;
    }

    
}