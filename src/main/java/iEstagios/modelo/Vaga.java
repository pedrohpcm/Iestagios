package iEstagios.modelo;

public class Vaga {
    
    private int idVaga;
    private String nomeDaVaga;
    private double valorDaBolsa;
    private String beneficios;
    private String auxilioTransporte;
    private String planoDeAtividades;
    private String jornadaDiaria;
    private String totalHorasSemanais;
    private Concedente concedente;

    public int getIdVaga() {
        return idVaga;
    }

    public void setIdVaga(int idVaga) {
        this.idVaga = idVaga;
    }

    public String getNomeDaVaga() {
        return nomeDaVaga;
    }

    public void setNomeDaVaga(String nomeDaVaga) {
        this.nomeDaVaga = nomeDaVaga;
    }

    public double getValorDaBolsa() {
        return valorDaBolsa;
    }

    public void setValorDaBolsa(double valorDaBolsa) {
        this.valorDaBolsa = valorDaBolsa;
    }

    public String getBeneficios() {
        return beneficios;
    }

    public void setBeneficios(String beneficios) {
        this.beneficios = beneficios;
    }

    public String getAuxilioTransporte() {
        return auxilioTransporte;
    }

    public void setAuxilioTransporte(String auxilioTransporte) {
        this.auxilioTransporte = auxilioTransporte;
    }

    public String getPlanoDeAtividades() {
        return planoDeAtividades;
    }

    public void setPlanoDeAtividades(String planoDeAtividades) {
        this.planoDeAtividades = planoDeAtividades;
    }

    public String getJornadaDiaria() {
        return jornadaDiaria;
    }

    public void setJornadaDiaria(String jornadaDiaria) {
        this.jornadaDiaria = jornadaDiaria;
    }

    public String getTotalHorasSemanais() {
        return totalHorasSemanais;
    }

    public void setTotalHorasSemanais(String totalHorasSemanais) {
        this.totalHorasSemanais = totalHorasSemanais;
    }

    public Concedente getConcedente() {
        return concedente;
    }

    public void setConcedente(Concedente concedente) {
        this.concedente = concedente;
    }

}
