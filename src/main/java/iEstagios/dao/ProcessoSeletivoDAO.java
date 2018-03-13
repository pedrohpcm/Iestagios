package iEstagios.dao;

import iEstagios.conexao.Conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author Gerardo Neto
 */
public class ProcessoSeletivoDAO {
    public static void cadastrarProcesso(int idVaga, int idAluno){		
        try {
            Connection conexao = Conexao.abrirConexao();
            String cadastrarProcesso = "INSERT INTO processoseletivo(idVaga, idAluno) VALUES (?,?)";
            PreparedStatement statement = conexao.prepareStatement(cadastrarProcesso);
            statement.setInt(1, idVaga);
            statement.setInt(2, idAluno);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
