package iEstagios.dao;

import iEstagios.conexao.Conexao;
import iEstagios.modelo.Vaga;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class VagaDAO {

    public static int cadastrarVaga(Vaga v) {
        Connection conn = Conexao.abrirConexao();
        PreparedStatement pstmt = null;
        String sql = "INSERT INTO vaga (nomeDaVaga,valorDaBolsa,beneficios,auxilioTransporte,planoDeAtividades,jornadaDiaria,totalHorasSemanais,idConcedente) values(?,?,?,?,?,?,?,?)";
        try {
            pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, v.getNomeDaVaga());
            pstmt.setDouble(2, v.getValorDaBolsa());
            pstmt.setString(3, v.getBeneficios());
            pstmt.setString(4, v.getAuxilioTransporte());
            pstmt.setString(5, v.getPlanoDeAtividades());
            pstmt.setString(6, v.getJornadaDiaria());
            pstmt.setString(7, v.getTotalHorasSemanais());
            pstmt.setInt(8, v.getConcedente().getId());
            pstmt.executeUpdate();
            ResultSet rs = pstmt.getGeneratedKeys();
            rs.next();
            return rs.getInt(1);
        } catch (SQLException ex) {
            Logger.getLogger(VagaDAO.class.getName()).log(Level.SEVERE, null, ex);
            try {
                conn.rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(VagaDAO.class.getName()).log(Level.SEVERE, null, ex1);
            }
            return 0;
        }
    }

    public static ArrayList<Vaga> buscarVagas() {
        Connection conn = Conexao.abrirConexao();
        PreparedStatement pstmt = null;
        String buscarVagas = "SELECT * FROM vaga";
        try {
            pstmt = conn.prepareStatement(buscarVagas);
            ResultSet rs = pstmt.executeQuery();
            ArrayList<Vaga> vagas = new ArrayList<>();
            while (rs.next()) {
                Vaga v = new Vaga();
                v.setIdVaga(rs.getInt(1));
                v.setNomeDaVaga(rs.getString(2));
                v.setValorDaBolsa(rs.getDouble(3));
                v.setBeneficios(rs.getString(4));
                v.setAuxilioTransporte(rs.getString(5));
                v.setPlanoDeAtividades(rs.getString(6));
                v.setJornadaDiaria(rs.getString(7));
                v.setTotalHorasSemanais(rs.getString(8));
                v.setConcedente(ConcedenteDAO.pesquisarPorId(rs.getInt(9)));
                vagas.add(v);
            }
            return vagas;
        } catch (SQLException ex) {
            Logger.getLogger(VagaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static ArrayList<Vaga> buscarPorId(int id) {
        Connection conn = Conexao.abrirConexao();
        PreparedStatement pstmt = null;
        String buscarVagas = "SELECT * FROM vaga WHERE idConcedente = " + id;
        try {
            pstmt = conn.prepareStatement(buscarVagas);
            ResultSet rs = pstmt.executeQuery();
            ArrayList<Vaga> vagas = new ArrayList<>();
            while (rs.next()) {
                Vaga v = new Vaga();
                v.setIdVaga(rs.getInt(1));
                v.setNomeDaVaga(rs.getString(2));
                v.setValorDaBolsa(rs.getDouble(3));
                v.setBeneficios(rs.getString(4));
                v.setAuxilioTransporte(rs.getString(5));
                v.setPlanoDeAtividades(rs.getString(6));
                v.setJornadaDiaria(rs.getString(7));
                v.setTotalHorasSemanais(rs.getString(8));
                v.setConcedente(ConcedenteDAO.pesquisarPorId(rs.getInt(9)));
                vagas.add(v);
            }
            return vagas;
        } catch (SQLException ex) {
            Logger.getLogger(VagaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static Vaga buscarVagaPorId(int id) {
        Connection conn = Conexao.abrirConexao();
        PreparedStatement pstmt = null;
        String buscarVagas = "SELECT * FROM vaga WHERE idVaga = " + id;
        try {
            pstmt = conn.prepareStatement(buscarVagas);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                Vaga vaga = new Vaga();
                vaga.setIdVaga(rs.getInt(1));
                vaga.setNomeDaVaga(rs.getString(2));
                vaga.setValorDaBolsa(rs.getDouble(3));
                vaga.setBeneficios(rs.getString(4));
                vaga.setAuxilioTransporte(rs.getString(5));
                vaga.setPlanoDeAtividades(rs.getString(6));
                vaga.setJornadaDiaria(rs.getString(7));
                vaga.setTotalHorasSemanais(rs.getString(8));
                vaga.setConcedente(ConcedenteDAO.pesquisarPorId(rs.getInt(9)));
                return vaga;
            }
        } catch (SQLException ex) {
            Logger.getLogger(VagaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
