package iEstagios.bean;

import iEstagios.dao.ContaDAO;
import iEstagios.email.Email;
import iEstagios.modelo.Conta;
import java.io.IOException;
import java.io.Serializable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author Gerardo Neto
 */
@ManagedBean
@RequestScoped

public class mBeanConta implements Serializable {

    private static final long serialVersionUID = 1L;
    private Conta conta;

    @PostConstruct
    public void init() {
        conta = new Conta();
    }

    public void cadastrar() {
        if (emailValido()) {
            if (conta.getTipo().equals("Concedente")) {
                cadastrarConcedente();
            } else if (conta.getTipo().equals("Estudante")) {
                cadastrarEstudante();
            } else {
                cadastrarInstituicao();
            }
        } else {
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage("Email inválido!", ""));
        }
    }

    public void cadastrarEstudante() {
        conta.setTipo("Estudante");
        ContaDAO.cadastrar(conta);
        Email.enviarSenha(conta);
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("index.xhtml");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void cadastrarInstituicao() {
        conta.setTipo("Instituicao");
        ContaDAO.cadastrar(conta);
        Email.enviarSenha(conta);
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("index.xhtml");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void cadastrarConcedente() {
        conta.setTipo("Concedente");
        ContaDAO.cadastrar(conta);
        Email.enviarSenha(conta);
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("index.xhtml");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void recuperarSenha() {
        if (emailValido()) {
            System.out.println("Email enviado com sucesso!");
        } else {
            System.out.println("Email inv�lido!");
        }

    }

    public boolean emailValido() {
        boolean isEmailIdValid = false;
        if (conta.getLogin() != null && conta.getLogin().length() > 0) {
            String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
            Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
            Matcher matcher = pattern.matcher(conta.getLogin());
            if (matcher.matches()) {
                isEmailIdValid = true;
            }
        }
        return isEmailIdValid;
    }

    public Conta getConta() {
        return conta;
    }

    public void setConta(Conta conta) {
        this.conta = conta;
    }

}
