package iEstagios.bean;

import iEstagios.dao.VagaDAO;
import iEstagios.modelo.Conta;
import iEstagios.modelo.Vaga;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean
@ViewScoped
public class mBeanVaga  implements Serializable{
    
    private Vaga vaga;
    private List<Vaga> vagas;
    private Conta usuario;
    
    @PostConstruct
    public void init(){
        vagas = VagaDAO.buscarVagas();
        vaga = new Vaga();
    }

    public Conta getUsuario() {
        return usuario;
    }

    public void setUsuario(Conta usuario) {
        this.usuario = usuario;
    }
    
    public Vaga getVaga() {
        return vaga;
    }

    public void setVaga(Vaga vaga) {
        this.vaga = vaga;
    }

    public List<Vaga> getVagas() {
        return vagas;
    }

    public void setVagas(List<Vaga> vagas) {
        this.vagas = vagas;
    }

    public void cadastrarVaga(){
        VagaDAO.cadastrarVaga(vaga);
    }
}
