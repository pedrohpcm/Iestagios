package iEstagios.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import iEstagios.conexao.Conexao;
import iEstagios.modelo.Telefone;

public class TelefoneDAO {
	
    public static int cadastrarTelefone(Telefone telefone){		
        try {
            Connection conexao = Conexao.abrirConexao();
            String cadastrarTelefone = "INSERT INTO telefone(numero) VALUES (?)";
            PreparedStatement statement = conexao.prepareStatement(cadastrarTelefone, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, telefone.getNumero());
            statement.executeUpdate();
            ResultSet rs = statement.getGeneratedKeys();
            rs.next();
            return rs.getInt(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }
    
    public static Telefone pesquisarTelefonePorId(int id){
        try {
            Connection conexao = Conexao.abrirConexao();
            String pesquisarTelefone = "SELECT * FROM Telefone WHERE idTelefone ="+id;
            PreparedStatement statement = conexao.prepareStatement(pesquisarTelefone);          
            ResultSet rs = statement.executeQuery();
            Telefone telefone = new Telefone();
            if(rs.next()){                 
                telefone.setId(rs.getInt(1));
                telefone.setNumero(rs.getString(2));
            }            
            return telefone;
        } catch (SQLException e) {
            e.printStackTrace();
	} 
        return null;
    } 
}
