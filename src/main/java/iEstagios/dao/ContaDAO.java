package iEstagios.dao;

import iEstagios.conexao.Conexao;
import iEstagios.modelo.Conta;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author pc
 */
public class ContaDAO {

    public static void cadastrar(Conta conta) {
        conta.setSenha(gerarSenha());
        try {
            Connection conexao = Conexao.abrirConexao();
            String cadastrarUsuario = "INSERT INTO conta(email,senha,tipo,estaCompleto) VALUES (?,?,?,?)";
            PreparedStatement statement = conexao.prepareStatement(cadastrarUsuario);
            statement.setString(1, conta.getLogin());
            statement.setString(2, conta.getSenha());
            statement.setString(3, conta.getTipo());
            statement.setString(4, "N");
            statement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public static Conta pesquisarParaLogin(String login, String senha) {
        Connection conexao = Conexao.abrirConexao();
        String pesquisarAluno = "SELECT * FROM conta WHERE email = ? AND senha = ?";
        try {
            PreparedStatement statement = conexao.prepareStatement(pesquisarAluno);
            statement.setString(1, login);
            statement.setString(2, senha);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                Conta usuario = new Conta();
                usuario.setId(rs.getInt(1));
                usuario.setLogin(rs.getString(2));
                usuario.setSenha(rs.getString(3));
                usuario.setTipo(rs.getString(4));
                usuario.setEstaCompleto(rs.getString(5));
                return usuario;
            }
        } catch (SQLException e) {
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Erro ao pesquisar no banco!", "classe: UsuarioDAO"));
        }
        return null;
    }
    
    public static Conta pesquisarPorId(int id) {
        Connection conexao = Conexao.abrirConexao();
        String pesquisarAluno = "SELECT * FROM conta WHERE id = ?";
        try {
            PreparedStatement statement = conexao.prepareStatement(pesquisarAluno);
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                Conta usuario = new Conta();
                usuario.setId(rs.getInt(1));
                usuario.setLogin(rs.getString(2));
                usuario.setSenha(rs.getString(3));
                usuario.setTipo(rs.getString(4));
                usuario.setEstaCompleto(rs.getString(5));
                return usuario;
            }
        } catch (SQLException e) {
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Erro ao pesquisar no banco!", "classe: UsuarioDAO"));
        }
        return null;
    }

    public static String gerarSenha() {
        String[] carct = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};
        String senha = "";

        for (int x = 0; x < 6; x++) {
            int j = (int) (Math.random() * carct.length);
            senha += carct[j];
        }
        return senha;
    }
}
