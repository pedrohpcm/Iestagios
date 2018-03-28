package iEstagios.bean;

import iEstagios.dao.ConcedenteDAO;
import iEstagios.dao.ContaDAO;
import iEstagios.dao.VagaDAO;
import iEstagios.email.Email;
import iEstagios.modelo.Aluno;
import iEstagios.modelo.AlunoDAO;
import iEstagios.modelo.Concedente;
import iEstagios.modelo.Curriculo;
import iEstagios.modelo.Endereco;
import iEstagios.modelo.Telefone;
import iEstagios.modelo.Conta;
import iEstagios.modelo.Vaga;
import java.io.IOException;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

/**
 *
 * @author Gerardo
 */
@ManagedBean
@ViewScoped
public class mBeanLogin implements Serializable {

    private Curriculo curriculo;
    private Curriculo curriculoPcadastro;
    private Conta conta;
    private Concedente concedente;
    private Vaga vagaCurriculos;
    private Vaga vaga;
    private List<Vaga> vagas;
    private Aluno estudante;
    private List<Aluno> estudantes;
    private String pagina;
    private String mensagem;

    @PostConstruct
    public void init() {
        conta = new Conta();
    }

    public void logar() {
        Conta u;
        FacesContext context = FacesContext.getCurrentInstance();
        try {
            u = ContaDAO.pesquisarParaLogin(conta.getLogin(), conta.getSenha());
            if (u != null) {
                conta = u;                
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Bem vindo ao iEstagios!", ""));
                
                if (conta.getTipo().equals("Estudante")) {
                    pagina = "/iEstagios/Estudante/indexEstudante.xhtml";
                    vagas = VagaDAO.buscarVagas();
                } else {
                    if (conta.getTipo().equals("Concedente")) {
                        pagina = "/iEstagios/Concedente/indexConcedente.xhtml";
                    } else {
                        pagina = "indexInstituicao.xhtml";
                    }
                }
                
                ExternalContext ec = context.getExternalContext();
                Map attrMap = ec.getSessionMap();
                attrMap.put("Conta", conta);
    
                context.getExternalContext().redirect(pagina);

            } else {
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Login ou senha inválidos!", ""));
            }
        } catch (SQLException | IOException ex) {
            try {
                FacesContext.getCurrentInstance().getExternalContext().redirect("erro404.xhtml");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }        
    }

    public void redirectInicio() {
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect(pagina);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void redirectContratar() {
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("contratarEstagiario.xhtml");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void redirectGerenciarVagas() {
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("indexConcedente.xhtml");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void redirectConfig() {
        if (conta.getEstaCompleto().equals("N")) {
            try {
                FacesContext.getCurrentInstance().getExternalContext().redirect("cadastrarEstudante.xhtml");
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        } else {
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage("Seu perfil já está completo!", ""));
        }
    }

    public void redirectVaga() {
        if (conta.getEstaCompleto().equals("S")) {
            vaga = new Vaga();
            vaga.setConcedente(concedente);
            VagaDAO.cadastrarVaga(vaga);
            try {
                FacesContext.getCurrentInstance().getExternalContext().redirect("cadastrarVaga.xhtml");
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        } else {
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage("Sua conta ainda não está completa!", ""));
        }
    }

    public void entrevista() {
        System.out.println(estudante);
        System.out.println(concedente);
        System.out.println(vaga);
        System.out.println(mensagem);
        Email.enviarEntrevista(estudante, concedente, vaga, mensagem);
    }

    public void sair() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ExternalContext ec = facesContext.getExternalContext();
        ec.invalidateSession();
        ec.getSessionMap().clear();
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("/iEstagios/login.xhtml");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
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

    public Conta getUsuario() {
        return conta;
    }

    public void setUsuario(Conta usuario) {
        this.conta = usuario;
    }

    public Curriculo getCurriculoPcadastro() {
        return curriculoPcadastro;
    }

    public void setCurriculoPcadastro(Curriculo curriculoPcadastro) {
        this.curriculoPcadastro = curriculoPcadastro;
    }

    public Vaga getVagaCurriculos() {

        return vagaCurriculos;
    }

    public void setVagaCurriculos(Vaga vagaCurriculos) {
        this.vagaCurriculos = vagaCurriculos;
    }

    public Aluno getEstudante() {
        return estudante;
    }

    public void setEstudante(Aluno estudante) {
        this.estudante = estudante;
    }

    public Curriculo getCurriculo() {
        return curriculo;
    }

    public void setCurriculo(Curriculo curriculo) {
        this.curriculo = curriculo;
    }

    public List<Aluno> getEstudantes() {
        estudantes = AlunoDAO.pesquisarParaCurriculo(vaga.getIdVaga());
        return estudantes;
    }

    public void setEstudantes(List<Aluno> estudantes) {
        this.estudantes = estudantes;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

}
