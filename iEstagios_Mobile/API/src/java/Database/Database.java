/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author joaovictor
 */
public class Database {
    public static Connection connection = null;
    
    public static Connection OpenConnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/iestagios", "João Victor", "victor.administrador");
        }
        catch (ClassNotFoundException e) {
            System.out.println("Não foi possível encontrar o Driver!");
        }
        catch (SQLException e) {
            System.out.println("Não foi possível abrir Conexão!");
        }
        return connection;
    }
 
    /**
     *
     * @param connection
     * @param stmt
     * @param pstmt
     * @param rs
     */
    public static void CloseConnection(Connection connection, Statement stmt, PreparedStatement pstmt, ResultSet rs) {
        if (connection != null) {
            try {
                connection.close();
            }
            catch (SQLException e) {
                System.out.println("Não foi possível fechar Conexão!");
            }
        }
        if (stmt != null) {
            try {
                stmt.close();
            }
            catch (SQLException e) {
                System.out.println("Não foi possível fechar Declaração!");
            }
        }
        if (pstmt != null) {
            try {
                pstmt.close();
            }
            catch (SQLException e) {
                System.out.println("Não foi possível fechar Declaração Preparada!");
            }
        }
        if (rs != null) {
            try {
                rs.close();
            }
            catch (SQLException e) {
                System.out.println("Não foi possível fechar Conjunto Resultado!");
            }
        }
    }
}
