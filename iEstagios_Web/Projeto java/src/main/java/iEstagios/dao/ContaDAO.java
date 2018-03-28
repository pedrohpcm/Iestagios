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
        try {
            Connection conexao = Conexao.abrirConexao();
            String cadastrarUsuario = "INSERT INTO conta(login,senha,tipo,estaCompleto) VALUES (?,?,?,?)";
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

    public static int atualizar(Conta conta) throws SQLException {
        int executeUpdate = 0;
        try {
            Connection conexao = Conexao.abrirConexao();
            String atualizarUsuario = "UPDATE conta SET login = ?, senha = ? WHERE idusuario = ?";
            try (PreparedStatement ps = conexao.prepareStatement(atualizarUsuario)) {
                ps.setString(1, conta.getLogin());
                ps.setString(2, conta.getSenha());
                ps.setInt(3, conta.getId());
                executeUpdate = ps.executeUpdate();
            }
        } catch (SQLException ex) {
            throw ex;
        }
        
        return executeUpdate;
    }
    
    public static Conta pesquisarParaLogin(String login, String senha) throws SQLException {
        Connection conexao = Conexao.abrirConexao();
        String pesquisarAluno = "SELECT * FROM conta WHERE login = ? AND senha = ?";
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
            throw  e;
        }
        return null;
    }
    
    public static Conta pesquisarPorLogin(String login) throws SQLException {
        Connection conexao = Conexao.abrirConexao();
        String pesquisarAluno = "SELECT * FROM conta WHERE login = ? ";
        try {
            PreparedStatement statement = conexao.prepareStatement(pesquisarAluno);
            statement.setString(1, login);
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
            throw e;
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

}
