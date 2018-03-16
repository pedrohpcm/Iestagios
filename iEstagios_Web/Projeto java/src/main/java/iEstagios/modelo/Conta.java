package iEstagios.modelo;

import java.io.Serializable;
import java.util.Objects;

public class Conta implements Serializable{

    private int id;
    private String email;
    private String senha;
    private String tipo;
    private String estaCompleto;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return email;
    }

    public void setLogin(String login) {
        this.email = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getEstaCompleto() {
        return estaCompleto;
    }

    public void setEstaCompleto(String estaCompleto) {
        this.estaCompleto = estaCompleto;
    }

    @Override
    public String toString() {
        return "Conta{" + "id=" + id + ", email=" + email + ", senha=" + senha + ", tipo=" + tipo + ", estaCompleto=" + estaCompleto + '}';
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 37 * hash + this.id;
        hash = 37 * hash + Objects.hashCode(this.email);
        hash = 37 * hash + Objects.hashCode(this.senha);
        hash = 37 * hash + Objects.hashCode(this.tipo);
        hash = 37 * hash + Objects.hashCode(this.estaCompleto);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Conta other = (Conta) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.email, other.email)) {
            return false;
        }
        if (!Objects.equals(this.senha, other.senha)) {
            return false;
        }
        if (!Objects.equals(this.estaCompleto, other.estaCompleto)) {
            return false;
        }
        return true;
    }
    
}
