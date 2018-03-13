
package iEstagios.dao;

import iEstagios.conexao.Conexao;
import iEstagios.modelo.ApolicePrincipal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ApolicePrincipalDAO {
       
    int i = 0;
    public int cadastrarApolice(ApolicePrincipal a) throws SQLException{
        Connection conn = Conexao.abrirConexao();
        PreparedStatement pstmt = null;
        String sql = "INSERT INTO apoliceprincipal (nomeseguradora,cnpjseguradora,numero,nomeapolice,data,arquivopdf) values(?,?,?,?,?,?)";
        try {
        pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, a.getNomeseguradora());
        pstmt.setString(2, a.getCnpjseguradora());
        pstmt.setInt(3, a.getNumero());
        pstmt.setString(4, a.getNomeapolice());
        pstmt.setString(5, a.getData());
        pstmt.setString(6, a.getArquivopdf());  
        i = pstmt.executeUpdate();
        conn.setAutoCommit(true);        
        } catch (SQLException ex) {
        Logger.getLogger(ApolicePrincipalDAO.class.getName()).log(Level.SEVERE, null, ex);
        conn.rollback();       
        } finally {
        Conexao.closeConexao(conn, null, null, pstmt);
        }
        return i;
    } 
    
    
}
