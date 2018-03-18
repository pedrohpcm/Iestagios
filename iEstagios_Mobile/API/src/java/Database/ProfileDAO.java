/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Database;

import Classes.Profile;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import org.jboss.logging.Logger;

/**
 *
 * @author joaovictor
 */
public class ProfileDAO {
    public void Create(Profile profile) {
        Connection connection = Database.OpenConnection();
        PreparedStatement pstmt = null;
        String sql = "INSERT INTO UserProfile(Email, UserPassword) VALUES(?, ?);";
        
        try {
            pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, profile.getEmail());
            pstmt.setString(2, profile.getPassword());
            int linhasModificadas = pstmt.executeUpdate();
            System.out.println("Affected Rows: " + linhasModificadas);
        }
        catch (SQLException e) {
            Logger.getLogger(ProfileDAO.class.getName()).log(Logger.Level.FATAL, e.getMessage(), e);
        }
        finally {
            Database.CloseConnection(connection, null, pstmt, null);
        }
    }
    
    public ArrayList Retrieve() {
        Connection connection = Database.OpenConnection();
        Statement stmt = null;
        ResultSet rs = null;
        ArrayList<Profile> array = new ArrayList();
        String sql = "SELECT * FROM UserProfile;";
        try {
            stmt = connection.createStatement();
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                Profile profile = new Profile();
                profile.setFirstname(rs.getString(2));
                profile.setLastname(rs.getString(3));
                profile.setEmail(rs.getString(4));
                profile.setPassword(rs.getString(5));
                profile.setAge(rs.getInt(6));
                array.add(profile);
            }
        }
        catch (SQLException e) {
            Logger.getLogger(ProfileDAO.class.getName()).log(Logger.Level.FATAL, e.getMessage(), e);
        }
        finally {
            Database.CloseConnection(connection, stmt, null, rs);
        }
        return array;
    }
    
    public void Update(Profile profile) {
        Connection connection = Database.OpenConnection();
        PreparedStatement pstmt = null;
        String sql = "UPDATE UserProfile SET Firstname = ?, Lastname = ?, Email = ?, Age = ? WHERE UserPassword = ?;";
        
        try {
            pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, profile.getFirstname());
            pstmt.setString(2, profile.getLastname());
            pstmt.setString(3, profile.getEmail());
            pstmt.setInt(4, profile.getAge());
            pstmt.setString(5, profile.getPassword());
            int linhasModificadas = pstmt.executeUpdate();
            System.out.println("Affected Rows: " + linhasModificadas);
        }
        catch (SQLException e) {
            Logger.getLogger(ProfileDAO.class.getName()).log(Logger.Level.FATAL, e.getMessage(), e);
        }
        finally {
            Database.CloseConnection(connection, null, pstmt, null);
        }
    }
    
    public void Delete(Profile profile) {
        Connection connection = Database.OpenConnection();
        PreparedStatement pstmt = null;
        String sql = "DELETE FROM UserProfile WHERE UserPassword = ?;";
        
        try {
            pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, profile.getPassword());
            int linhasModificadas = pstmt.executeUpdate();
            System.out.println("Affected Rows: " + linhasModificadas);
        }
        catch (SQLException e) {
            Logger.getLogger(ProfileDAO.class.getName()).log(Logger.Level.FATAL, e.getMessage(), e);
        }
        finally {
            Database.CloseConnection(connection, null, pstmt, null);
        }
    }
}
