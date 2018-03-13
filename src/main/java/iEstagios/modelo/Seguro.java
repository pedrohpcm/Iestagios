
package iEstagios.modelo;


public class Seguro {
    private int idSeguro;
    private String dataInicio;
    private String dataFim;
    private float taxa;
    private String nomeDoSegurado;
    private String CPF;
    private String dataDeNascimento;
    private String estadoCivil;

     public int getIdSeguro() {
        return idSeguro;
    }
     
    public String getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(String dataInicio) {
        this.dataInicio = dataInicio;
    }

    public String getDataFim() {
        return dataFim;
    }

    public void setDataFim(String dataFim) {
        this.dataFim = dataFim;
    }

    public float getTaxa() {
        return taxa;
    }

    public void setTaxa(float taxa) {
        this.taxa = taxa;
    }

    public String getNomeDoSegurado() {
        return nomeDoSegurado;
    }

    public void setNomeDoSegurado(String nomeDoSegurado) {
        this.nomeDoSegurado = nomeDoSegurado;
    }

    public String getCPF() {
        return CPF;
    }

    public void setCPF(String CPF) {
        this.CPF = CPF;
    }

    public String getDataDeNascimento() {
        return dataDeNascimento;
    }

    public void setDataDeNascimento(String dataDeNascimento) {
        this.dataDeNascimento = dataDeNascimento;
    }

    public String getEstadoCivil() {
        return estadoCivil;
    }

    public void setEstadoCivil(String estadoCivil) {
        this.estadoCivil = estadoCivil;
    }

    @Override
    public String toString() {
        return "Seguro{" + "dataInicio=" + dataInicio + ", dataFim=" + dataFim + ", taxa=" + taxa + ", nomeDoSegurado=" + nomeDoSegurado + ", CPF=" + CPF + ", dataDeNascimento=" + dataDeNascimento + ", estadoCivil=" + estadoCivil + '}';
    }

    
    
    
}
