package iEstagios.conexao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Conexao {

    private static final String USUARIO = "root";
    private static final String SENHA = "010816@neto";
    private static final String URL = "jdbc:mysql://localhost:3306/iestagios_db?useSSL=false";
    private static final String DRIVER = "com.mysql.jdbc.Driver";
    private static Connection conexao = null;

    public static Connection abrirConexao() {
        try {
            Class.forName(DRIVER);
            if (conexao == null) {
                conexao = DriverManager.getConnection(URL, USUARIO, SENHA);
            }
            return conexao;
        } catch (SQLException e) {
            System.out.println("Erro ao conectar no banco!");
        } catch (ClassNotFoundException e) {
            System.out.println("Diver nao encontrado!");
        }
        return null;
    }

    public static void closeConexao(java.sql.Connection conn, Object object, Object object0, PreparedStatement pstmt) {
        if (pstmt != null) {
            try {
                pstmt.close();
            } catch (SQLException ex) {
            }
            System.out.println("Erro ao fechar PreparedStatement");
        }

        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException ex) {
                System.out.println("Erro ao fechar Conexao");
            }
        }
    }

}
