package pe.edu.vallegrande.projectsoftware.service;

import pe.edu.vallegrande.projectsoftware.db.SqlConnection;
import pe.edu.vallegrande.projectsoftware.dto.CategoryDto;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class c_restoreService {
    public List<CategoryDto> getAll() {
        List<CategoryDto> lista = new ArrayList<>();
        Connection cn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        String sql;

        try {
            cn = SqlConnection.getConnection();
            sql = "SELECT * FROM category WHERE is_active = 'I'";
            pstm = cn.prepareStatement(sql);
            rs = pstm.executeQuery();
            while (rs.next()) {
                CategoryDto category = new CategoryDto();
                category.setId(rs.getInt("id"));
                category.setName(rs.getString("name"));
                category.setIs_active(rs.getString("is_active"));
                lista.add(category);
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

    public void delete(int id) {
        String sql = "UPDATE category SET is_active = 'A' WHERE id = ?";

        try (Connection cn = SqlConnection.getConnection();
             PreparedStatement pstm = cn.prepareStatement(sql)) {

            pstm.setInt(1, id);
            pstm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

