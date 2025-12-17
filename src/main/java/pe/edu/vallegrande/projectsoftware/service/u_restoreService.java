package pe.edu.vallegrande.projectsoftware.service;

import pe.edu.vallegrande.projectsoftware.db.SqlConnection;
import pe.edu.vallegrande.projectsoftware.dto.UserDto;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class u_restoreService {
    public List<UserDto> getAll() {
        List<UserDto> lista = new ArrayList<>();
        Connection cn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        String sql;

        try {
            cn = SqlConnection.getConnection();
            sql = "SELECT * FROM admin WHERE is_active = 'I'";
            pstm = cn.prepareStatement(sql);
            rs = pstm.executeQuery();
            while (rs.next()) {
                UserDto bean = new UserDto();
                bean.setId(rs.getInt("id"));
                bean.setUsername(rs.getString("username"));
                bean.setName(rs.getString("name"));
                bean.setLastname(rs.getString("lastname"));
                bean.setEmail(rs.getString("email"));
                bean.setPassword(rs.getString("password"));
                lista.add(bean);
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
        return lista;
    }

    public void restore(int id) {
        Connection cn = null;
        PreparedStatement pstm = null;
        String sql;

        try {
            cn = SqlConnection.getConnection();
            sql = "UPDATE admin SET is_active = 'A' WHERE id = ?";
            pstm = cn.prepareStatement(sql);
            pstm.setInt(1, id);
            pstm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (pstm != null) pstm.close();
                if (cn != null) cn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}

