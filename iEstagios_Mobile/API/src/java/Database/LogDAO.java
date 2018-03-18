/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Database;

import Classes.Log;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import org.jboss.logging.Logger;

/**
 *
 * @author joaovictor
 */
public class LogDAO {
    public ArrayList Retrieve() {
        Connection connection = Database.OpenConnection();
        Statement stmt = null;
        ResultSet rs = null;
        ArrayList<Log> array = new ArrayList();
        String sql = "SELECT * FROM Log;";
        try {
            stmt = connection.createStatement();
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                Log log = new Log();
                log.setModificationdate(rs.getDate(2));
                log.setModifiedtable(rs.getString(3));
                log.setModifiedfield(rs.getString(4));
                log.setModifier(rs.getString(5));
                log.setContent(rs.getString(6));
                log.setOperation(rs.getString(7));
                array.add(log);
            }
        }
        catch (SQLException e) {
            Logger.getLogger(LogDAO.class.getName()).log(Logger.Level.FATAL, e.getMessage(), e);
        }
        finally {
            Database.CloseConnection(connection, stmt, null, rs);
        }
        return array;
    }
}
