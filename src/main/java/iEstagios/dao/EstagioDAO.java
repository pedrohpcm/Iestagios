package iEstagios.dao;

import iEstagios.conexao.Conexao;
import iEstagios.modelo.AlunoDAO;
import iEstagios.modelo.Estagio;
import iEstagios.modelo.Telefone;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class EstagioDAO {

    public static int cadastrarEstagio(Estagio estagio) {
        try {
            DateFormat fmt = new SimpleDateFormat("dd-MM-yyyy");
            Connection conexao = Conexao.abrirConexao();
            String cadastrarEndereco = "INSERT INTO Estagio(dataInicio,dataFim,orientador,emailOrientador,supervisor,emailSupervisor,idVaga,idEstudante,idConcedente,idSeguro,dataCriacao,status)"
                    + " VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement statement = conexao.prepareStatement(cadastrarEndereco, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, estagio.getDataInicio());
            statement.setString(2, estagio.getDataFim());
            statement.setString(3, estagio.getOrientador());
            statement.setString(4, estagio.getEmailOrientador());
            statement.setString(5, estagio.getSupervisor());
            statement.setString(6, estagio.getEmailSupervisor());
            statement.setInt(7, estagio.getVaga().getIdVaga());
            statement.setInt(8, estagio.getAluno().getId());
            statement.setInt(9, estagio.getConcedente().getId());
            statement.setInt(10, 0);
            statement.setString(11, fmt.format(new Date()).toString());
            statement.setString(12, "Em andamento");
            statement.executeUpdate();
            ResultSet rs = statement.getGeneratedKeys();
            rs.next();
            return rs.getInt(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    public static List<Estagio> listarEstagiosDaConcedente(int idConcedente) {
        try {
            Connection conexao = Conexao.abrirConexao();
            String pesquisarTelefone = "SELECT * FROM Estagio WHERE idConcedente =" + idConcedente;
            PreparedStatement statement = conexao.prepareStatement(pesquisarTelefone);
            ResultSet rs = statement.executeQuery();
            List<Estagio> estagios = new ArrayList<>();
            while (rs.next()) {
                Estagio estagio = new Estagio();
                estagio.setId(rs.getInt(1));
                estagio.setDataInicio(rs.getString(2));
                estagio.setDataFim(rs.getString(3));
                estagio.setOrientador(rs.getString(4));
                estagio.setEmailOrientador(rs.getString(5));
                estagio.setSupervisor(rs.getString(6));
                estagio.setEmailSupervisor(rs.getString(7));
                estagio.setVaga(VagaDAO.buscarVagaPorId(rs.getInt(8)));
                estagio.setAluno(AlunoDAO.pesquisarPorId(rs.getInt(9)));
                estagios.add(estagio);
            }
            return estagios;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public static List<Estagio> listarEstagiosDoEstudante(int idEstudante) {
        try {
            Connection conexao = Conexao.abrirConexao();
            String pesquisarTelefone = "SELECT * FROM Estagio WHERE idEstudante =" + idEstudante;
            PreparedStatement statement = conexao.prepareStatement(pesquisarTelefone);
            ResultSet rs = statement.executeQuery();
            List<Estagio> estagios = new ArrayList<>();
            while (rs.next()) {
                Estagio estagio = new Estagio();
                estagio.setId(rs.getInt(1));
                estagio.setDataInicio(rs.getString(2));
                estagio.setDataFim(rs.getString(3));
                estagio.setOrientador(rs.getString(4));
                estagio.setEmailOrientador(rs.getString(5));
                estagio.setSupervisor(rs.getString(6));
                estagio.setEmailSupervisor(rs.getString(7));
                estagio.setVaga(VagaDAO.buscarVagaPorId(rs.getInt(8)));
                estagio.setAluno(AlunoDAO.pesquisarPorId(rs.getInt(9)));
                estagios.add(estagio);
            }
            return estagios;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
