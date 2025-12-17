package pe.edu.vallegrande.projectsoftware.service;

import java.sql.*;
import pe.edu.vallegrande.projectsoftware.db.SqlConnection;

public class LoginService {

    public boolean authenticate(String username, String password) {

        Connection cn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;

        String sql = "SELECT COUNT(*) FROM admin WHERE username = ? AND password = ? AND is_active = 'A'";

        try {
            cn = SqlConnection.getConnection();

            pstm = cn.prepareStatement(sql);
            pstm.setString(1, username);
            pstm.setString(2, password);

            rs = pstm.executeQuery();

            if (rs.next()) {
                int count = rs.getInt(1);
                return count > 0;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (pstm != null) pstm.close();
                if (cn != null) cn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
    }
}
