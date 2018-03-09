package iEstagios.modelo;

import iEstagios.dao.EnderecoDAO;
import iEstagios.dao.TelefoneDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import iEstagios.conexao.Conexao;
import iEstagios.dao.ContaDAO;
import iEstagios.dao.CurriculoDAO;
import java.util.ArrayList;

public class AlunoDAO {

    public static void cadastrarAluno(Aluno aluno) {
        try {
            Connection conexao = Conexao.abrirConexao();
            String cadastraraAluno = "INSERT INTO aluno(nome,cpf,rg,orgaoexpeditor,datadenascimento,curso,periodo,ano,idendereco,idconta,idtelefone,idcurriculo) VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement statement = conexao.prepareStatement(cadastraraAluno);
            statement.setString(1, aluno.getNome());
            statement.setString(2, aluno.getRg());
            statement.setString(3, aluno.getOrgaoExpeditor());
            statement.setString(4, aluno.getCpf());
            SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
            Date data = formato.parse(aluno.getDataDeNascimento());
            java.sql.Date sqlDate = new java.sql.Date(data.getTime());
            statement.setDate(5, sqlDate);
            statement.setString(6, aluno.getCurso());
            statement.setString(7, aluno.getPeriodo());
            statement.setString(8, aluno.getAno());
            statement.setInt(9, EnderecoDAO.cadastrarEndereco(aluno.getEndereco()));
            statement.setInt(10, TelefoneDAO.cadastrarTelefone(aluno.getTelefone()));
            statement.setInt(11, aluno.getConta().getId());
            statement.setInt(12, CurriculoDAO.cadastrar(aluno.getCurriculo()));
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            System.out.println("Erro ao converter data!");
        }
    }

    public static Aluno pesquisarAluno(int idUsuario) {
        Connection conexao = Conexao.abrirConexao();
        String pesquisarAluno = "SELECT * FROM aluno WHERE idConta = ?";
        try {
            PreparedStatement statement = conexao.prepareStatement(pesquisarAluno);
            statement.setInt(1, idUsuario);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                Aluno aluno = new Aluno();
                aluno.setId(rs.getInt(1));
                aluno.setNome(rs.getString(2));
                aluno.setCpf(rs.getString(3));
                aluno.setRg(rs.getString(4));
                aluno.setOrgaoExpeditor(rs.getString(5));
                Date data = (rs.getDate(6));
                Date dataConvertida = new Date(data.getTime());
                aluno.setDataDeNascimento(new SimpleDateFormat("dd/MM/yyyy").format(dataConvertida));
                aluno.setCurso(rs.getString(6));
                aluno.setPeriodo(rs.getString(7));
                aluno.setAno(rs.getString(8));
                aluno.setEndereco(EnderecoDAO.pesquisarEnderecoPorId(rs.getInt(10)));
                aluno.setConta(ContaDAO.pesquisarPorId(rs.getInt(11)));
                aluno.setTelefone(TelefoneDAO.pesquisarTelefonePorId(rs.getInt(12)));
                aluno.setCurriculo(CurriculoDAO.pesquisar(rs.getInt(13)));
                return aluno;
            }
            return null;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static ArrayList<Aluno> pesquisarPorCPF(String nome) {
        Connection conexao = Conexao.abrirConexao();
        String pesquisarAluno = "SELECT * FROM aluno WHERE Cpf LIKE '" + nome + "%'";
        ArrayList<Aluno> alunos = new ArrayList<>();
        try {
            PreparedStatement statement = conexao.prepareStatement(pesquisarAluno);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Aluno aluno = new Aluno();
                aluno.setId(rs.getInt(1));
                aluno.setNome(rs.getString(2));
                aluno.setCpf(rs.getString(3));
                aluno.setRg(rs.getString(4));
                aluno.setOrgaoExpeditor(rs.getString(5));
                Date data = (rs.getDate(6));
                Date dataConvertida = new Date(data.getTime());
                aluno.setDataDeNascimento(new SimpleDateFormat("dd/MM/yyyy").format(dataConvertida));
                aluno.setCurso(rs.getString(6));
                aluno.setPeriodo(rs.getString(7));
                aluno.setAno(rs.getString(8));
                aluno.setEndereco(EnderecoDAO.pesquisarEnderecoPorId(rs.getInt(10)));
                aluno.setConta(ContaDAO.pesquisarPorId(rs.getInt(11)));
                aluno.setTelefone(TelefoneDAO.pesquisarTelefonePorId(rs.getInt(12)));
                aluno.setCurriculo(CurriculoDAO.pesquisar(rs.getInt(13)));
                alunos.add(aluno);
            }
            return alunos;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Aluno pesquisarPorId(int id) {
        Connection conexao = Conexao.abrirConexao();
        String pesquisarAluno = "SELECT * FROM aluno WHERE id = " + id;
        Aluno aluno = new Aluno();
        try {
            PreparedStatement statement = conexao.prepareStatement(pesquisarAluno);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                aluno.setId(rs.getInt(1));
                aluno.setNome(rs.getString(2));
                aluno.setCpf(rs.getString(3));
                aluno.setRg(rs.getString(4));
                aluno.setOrgaoExpeditor(rs.getString(5));
                Date data = (rs.getDate(6));
                Date dataConvertida = new Date(data.getTime());
                aluno.setDataDeNascimento(new SimpleDateFormat("dd/MM/yyyy").format(dataConvertida));
                aluno.setCurso(rs.getString(7));
                aluno.setPeriodo(rs.getString(8));
                aluno.setAno(rs.getString(11));
                aluno.setEndereco(EnderecoDAO.pesquisarEnderecoPorId(rs.getInt(10)));
                System.out.println("TELEFONE AQUI " + rs.getInt(12));
                aluno.setTelefone(TelefoneDAO.pesquisarTelefonePorId(rs.getInt(12)));
                aluno.setCurriculo(CurriculoDAO.pesquisar(rs.getInt(13)));
            }
            return aluno;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static ArrayList<Aluno> pesquisarParaCurriculo(int idVaga) {
        Connection conexao = Conexao.abrirConexao();
        String pesquisar = "SELECT * FROM processoSeletivo WHERE idVaga = " + idVaga;
        ArrayList<Aluno> alunos = new ArrayList<>();
        try {
            PreparedStatement statement = conexao.prepareStatement(pesquisar);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                alunos.add(pesquisarPorId(rs.getInt(2)));
            }
            return alunos;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void finalizarCadastro(Aluno aluno, Conta usuario) {
        try {
            Connection conexao = Conexao.abrirConexao();
            String cadastraraAluno = "INSERT INTO aluno(nome,cpf,rg,orgaoExpeditor,datadenascimento,curso,periodo,email,senha,ano,idendereco,idconta,idtelefone,idcurriculo) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement statement = conexao.prepareStatement(cadastraraAluno);
            statement.setString(1, aluno.getNome());
            statement.setString(2, aluno.getRg());
            statement.setString(3, aluno.getOrgaoExpeditor());
            statement.setString(4, aluno.getCpf());
            SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
            Date data = formato.parse(aluno.getDataDeNascimento());
            java.sql.Date sqlDate = new java.sql.Date(data.getTime());
            statement.setDate(5, sqlDate);
            statement.setString(6, aluno.getCurso());
            statement.setString(7, aluno.getPeriodo());
            statement.setString(8, usuario.getLogin());
            statement.setString(9, usuario.getSenha());
            statement.setString(10, aluno.getAno());
            statement.setInt(11, EnderecoDAO.cadastrarEndereco(aluno.getEndereco()));
            statement.setInt(12, usuario.getId());
            statement.setInt(13, TelefoneDAO.cadastrarTelefone(aluno.getTelefone()));
            statement.setInt(14, CurriculoDAO.cadastrar(new Curriculo()));
            statement.executeUpdate();
            conexao.commit();
            String atualizarUsuario = "UPDATE usuario SET estaCompleto =  'S' WHERE idusuario =" + usuario.getId();
            statement = conexao.prepareStatement(atualizarUsuario);
            statement.executeUpdate();
            conexao.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            System.out.println("Erro ao converter data!");
        }
    }
}
