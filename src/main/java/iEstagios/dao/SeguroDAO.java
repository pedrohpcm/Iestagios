package iEstagios.dao;

import iEstagios.conexao.Conexao;
import iEstagios.modelo.Seguro;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SeguroDAO {
    
    int i = 0;
    public int cadastrarSeguro(Seguro s) throws SQLException{
        Connection conn = Conexao.abrirConexao();
        PreparedStatement pstmt = null;
        String sql = "INSERT INTO seguro (inicio,fim,taxa,nomeDoSegurado,cpf,dataDeNascimento,estadoCivil) values(?,?,?,?,?,?,?)";
        try {
        pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, s.getDataInicio());           
        pstmt.setString(2, s.getDataFim());           
        pstmt.setFloat(3, s.getTaxa());           
        pstmt.setString(4, s.getNomeDoSegurado());           
        pstmt.setString(5, s.getCPF());           
        pstmt.setString(6, s.getDataDeNascimento());           
        pstmt.setString(7, s.getEstadoCivil());                   
        i = pstmt.executeUpdate();
        conn.setAutoCommit(true);
        } catch (SQLException ex) {
        Logger.getLogger(SeguroDAO.class.getName()).log(Level.SEVERE, null, ex);
        conn.rollback();
        } finally{
        Conexao.closeConexao(conn, null, null, pstmt);
        }
        return i;
    } 
    
}
