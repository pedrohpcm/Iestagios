package iEstagios.dao;

import iEstagios.conexao.Conexao;
import iEstagios.modelo.Concedente;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConcedenteDAO {

    public static int cadastrarConcedente(Concedente concedente) {
        String cadastrarConcedente = "INSERT INTO concedentes(razaoSocial,nome,cnpj,ramoDeAtividade,tipoConcedente,representante,cargo,idConta,idEndereco,idTelefone) "
                + "VALUES(?,?,?,?,?,?,?,?,?,?)";
        Connection conexao = Conexao.abrirConexao();
        try {
            PreparedStatement statement = conexao.prepareStatement(cadastrarConcedente, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, concedente.getRazaoSocial());
            statement.setString(2, concedente.getNome());
            statement.setString(3, concedente.getCnpj());
            statement.setString(4, concedente.getRamoDeAtividade());
            statement.setString(5, concedente.getTipoConcedente());
            statement.setString(6, concedente.getRepresentante());
            statement.setString(8, concedente.getCargo());
            statement.setInt(9, concedente.getConta().getId());
            statement.setInt(10, TelefoneDAO.cadastrarTelefone(concedente.getTelefone()));
            statement.setInt(11, EnderecoDAO.cadastrarEndereco(concedente.getEndereco()));
            statement.executeUpdate();
            ResultSet rs = statement.getGeneratedKeys();
            rs.next();
            return rs.getInt(1);
        } catch (SQLException ex) {
            Logger.getLogger(ConcedenteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    public static ArrayList<Concedente> pesquisarPorCNPJ(String cnpj) {
        try {
            Connection conexao = Conexao.abrirConexao();
            String pesquisarConcedente = "SELECT * FROM concedentes WHERE cnpj LIKE '" + cnpj + "%'";
            PreparedStatement statement = conexao.prepareStatement(pesquisarConcedente);
            ResultSet rs = statement.executeQuery();
            Concedente concedente;
            ArrayList<Concedente> concedentes = new ArrayList<>();
            while (rs.next()) {
                concedente = new Concedente();
                concedente.setId(rs.getInt(1));
                concedente.setRazaoSocial(rs.getString(2));
                concedente.setNome(rs.getString(3));
                concedente.setCnpj(rs.getString(4));
                concedente.setRamoDeAtividade(rs.getString(5));
                concedente.setTipoConcedente(rs.getString(6));
                concedente.setRepresentante(rs.getString(7));
                concedente.setCargo(rs.getString(8));
                concedente.setEndereco(EnderecoDAO.pesquisarEnderecoPorId(rs.getInt(10)));
                concedente.setTelefone(TelefoneDAO.pesquisarTelefonePorId(rs.getInt(11)));
                concedentes.add(concedente);
            }
            return concedentes;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static ArrayList<Concedente> pesquisarInstituicao(String cnpj) {
        try {
            Connection conexao = Conexao.abrirConexao();
            String pesquisarConcedente = "SELECT * FROM concedentes WHERE cnpj LIKE '" + cnpj + "%' AND tipoConcedente = 'Instituicao'";
            PreparedStatement statement = conexao.prepareStatement(pesquisarConcedente);
            ResultSet rs = statement.executeQuery();
            Concedente concedente;
            ArrayList<Concedente> concedentes = new ArrayList<>();
            while (rs.next()) {
                concedente = new Concedente();
                concedente.setId(rs.getInt(1));
                concedente.setRazaoSocial(rs.getString(2));
                concedente.setNome(rs.getString(3));
                concedente.setCnpj(rs.getString(4));
                concedente.setRamoDeAtividade(rs.getString(5));
                concedente.setTipoConcedente(rs.getString(6));
                concedente.setRepresentante(rs.getString(7));
                concedente.setCargo(rs.getString(8));
                concedente.setEndereco(EnderecoDAO.pesquisarEnderecoPorId(rs.getInt(10)));
                concedente.setTelefone(TelefoneDAO.pesquisarTelefonePorId(rs.getInt(11)));
                concedentes.add(concedente);
            }
            return concedentes;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Concedente pesquisarPorId(int id) {
        try {
            Connection conexao = Conexao.abrirConexao();
            Concedente concedente = null;
            String sql = "SELECT * FROM concedentes WHERE id = " + id;

            PreparedStatement statement = conexao.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                concedente = new Concedente();
                concedente.setId(rs.getInt(1));
                concedente.setRazaoSocial(rs.getString(2));
                concedente.setNome(rs.getString(3));
                concedente.setCnpj(rs.getString(4));
                concedente.setRamoDeAtividade(rs.getString(5));
                concedente.setTipoConcedente(rs.getString(6));
                concedente.setRepresentante(rs.getString(7));
                concedente.setCargo(rs.getString(8));
                concedente.setEndereco(EnderecoDAO.pesquisarEnderecoPorId(rs.getInt(10)));
                concedente.setTelefone(TelefoneDAO.pesquisarTelefonePorId(rs.getInt(11)));
            }
            return concedente;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Concedente pesquisarPorIdConta(int idConta) {
        try {
            Connection conexao = Conexao.abrirConexao();
            Concedente concedente = null;
            String sql = "SELECT * FROM concedentes WHERE idConta = ?";
            PreparedStatement statement = conexao.prepareStatement(sql);
            statement.setInt(1, idConta);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                concedente = new Concedente();
                concedente.setId(rs.getInt(1));
                concedente.setRazaoSocial(rs.getString(2));
                concedente.setNome(rs.getString(3));
                concedente.setCnpj(rs.getString(4));
                concedente.setRamoDeAtividade(rs.getString(5));
                concedente.setTipoConcedente(rs.getString(6));
                concedente.setRepresentante(rs.getString(7));
                concedente.setCargo(rs.getString(8));
                concedente.setEndereco(EnderecoDAO.pesquisarEnderecoPorId(rs.getInt(10)));
                concedente.setTelefone(TelefoneDAO.pesquisarTelefonePorId(rs.getInt(11)));
            }
            return concedente;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
