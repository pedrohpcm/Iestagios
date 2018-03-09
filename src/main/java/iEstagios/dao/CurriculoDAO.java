package iEstagios.dao;

import iEstagios.conexao.Conexao;
import iEstagios.modelo.Curriculo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Gerardo Neto
 */
public class CurriculoDAO {

    public static int cadastrar(Curriculo curriculo) {
        try {
            Connection conexao = Conexao.abrirConexao();
            String cadastrarUsuario = "INSERT INTO curriculo(formacao,cursos,habilidades,sobre) VALUES (?,?,?,?,?)";
            PreparedStatement statement = conexao.prepareStatement(cadastrarUsuario, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, curriculo.getFormacao());
            statement.setString(2, curriculo.getCursos());
            statement.setString(3, curriculo.getHabilidades());
            statement.setString(4, curriculo.getSobre());
            statement.setInt(5, curriculo.getIdEstudante());
            statement.executeUpdate();
            ResultSet rs = statement.getGeneratedKeys();
            rs.next();
            return rs.getInt(1);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return -1;
    }
    
    public static void atualizar(Curriculo curriculo) {
        try {
            Connection conexao = Conexao.abrirConexao();
            String cadastrarUsuario = "UPDATE curriculo SET formacao = ?, cursos = ?, habilidades = ?, sobre = ? WHERE idCurriculo = ?;";
            PreparedStatement statement = conexao.prepareStatement(cadastrarUsuario);
            statement.setString(1, curriculo.getFormacao());
            statement.setString(2, curriculo.getCursos());
            statement.setString(3, curriculo.getHabilidades());
            statement.setString(4, curriculo.getSobre());
            statement.setInt(5, curriculo.getId());
            statement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    public static Curriculo pesquisar(int id){
        Connection conexao = Conexao.abrirConexao();
        String pesquisar = "SELECT * FROM Curriculo WHERE idCurriculo = ?";        
        try {
            PreparedStatement statement = conexao.prepareStatement(pesquisar);
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                Curriculo curriculo = new Curriculo();
                curriculo.setId(rs.getInt(1));
                curriculo.setFormacao(rs.getString(2));
                curriculo.setCursos(rs.getString(3));
                curriculo.setHabilidades(rs.getString(4));
                curriculo.setSobre(rs.getString(5));
                return curriculo;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
