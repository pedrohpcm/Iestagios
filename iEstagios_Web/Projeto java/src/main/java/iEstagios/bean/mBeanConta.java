package iEstagios.bean;

import iEstagios.dao.ContaDAO;
import iEstagios.email.Email;
import iEstagios.modelo.Conta;
import java.io.IOException;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
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

    public Conta getConta() {
        return conta;
    }

    public void setConta(Conta conta) {
        this.conta = conta;
    }
    
    public void cadastrar() {
        if (emailValido()) {
            conta.setTipo(conta.getTipo());
            conta.setSenha(gerarSenha());
            ContaDAO.cadastrar(conta);
            Email.enviarSenha(conta);
        } else {
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage("Email inválido!", ""));
        }
    }

    public void recuperarSenha() {
        FacesContext context = FacesContext.getCurrentInstance();
        
        if (emailValido()) {            
            try {
                String email = conta.getLogin();
                Conta usuario = ContaDAO.pesquisarPorLogin(email);
                
                if(usuario != null){

                    String novaSenha = gerarSenha();
                    usuario.setSenha(novaSenha);
                    int atualizado = ContaDAO.atualizar(usuario);

                    if (atualizado == 1){
                        Email.enviarSenha(conta);

                        try {
                            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Email enviado com sucesso!", ""));
                            context.getExternalContext().getFlash().setKeepMessages(true);
                            context.getExternalContext().redirect("login.xhtml");
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                    } 
                } else {
                    context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Usuário não cadastrado", ""));
                }
                
            } catch (SQLException ex) {
                Logger.getLogger(mBeanConta.class.getName()).log(Level.SEVERE, null, ex);
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Algo aconteceu de inesperado, por gentilza entre em contato como administrador", ""));
            }
        } else {            
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Email inválido!", ""));
        }
    }

    private boolean emailValido() {
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
    
    private String gerarSenha() {
        String[] carct = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};
        String senha = "";

        for (int x = 0; x < 8; x++) {
            int j = (int) (Math.random() * carct.length);
            senha += carct[j];
        }
        
        return senha;
    }
}
