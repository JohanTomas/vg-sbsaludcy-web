package pe.edu.vallegrande.projectsoftware.service;

import pe.edu.vallegrande.projectsoftware.db.SqlConnection;
import pe.edu.vallegrande.projectsoftware.dto.CategoryDto;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoryService {
    public List<CategoryDto> getAll() {
        List<CategoryDto> lista = new ArrayList<>();
        Connection cn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        String sql;

        try {
            cn = SqlConnection.getConnection();
            sql = "SELECT * FROM category WHERE is_active = 'A'";
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

    public int create(CategoryDto bean) {
        String sql = "INSERT INTO category (name, is_active) VALUES (?, ?)";
        int nuevoCategoryId = 0;

        try (Connection cn = SqlConnection.getConnection();
             PreparedStatement pstm = cn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            pstm.setString(1, bean.getName());
            pstm.setString(2, bean.getIs_active());
            pstm.executeUpdate();

            try (ResultSet rs = pstm.getGeneratedKeys()) {
                if (rs.next()) {
                    nuevoCategoryId = rs.getInt(1);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return nuevoCategoryId;
    }

    public CategoryDto getCategoryId(int categoryId) {
        CategoryDto category = null;
        Connection cn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM category WHERE id = ?";

        try {
            cn = SqlConnection.getConnection();
            pstm = cn.prepareStatement(sql);
            pstm.setInt(1, categoryId);
            rs = pstm.executeQuery();

            if (rs.next()) {
                category = new CategoryDto();
                category.setId(rs.getInt("id"));
                category.setName(rs.getString("name"));
                category.setIs_active(rs.getString("is_active"));
            }
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
        return category;
    }

    public boolean updateCategory(CategoryDto category) {
        Connection cn = null;
        PreparedStatement pstm = null;
        String sql = "UPDATE category SET name = ?, is_active = ? WHERE id = ?";
        boolean isUpdated = false;

        try {
            cn = SqlConnection.getConnection();
            pstm = cn.prepareStatement(sql);
            pstm.setString(1, category.getName());
            pstm.setString(2, category.getIs_active());
            pstm.setInt(3, category.getId());
            int rowsAffected = pstm.executeUpdate();
            isUpdated = rowsAffected > 0;
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
        return isUpdated;
    }

    public void delete(int id) {
        String sql = "UPDATE category SET is_active = 'I' WHERE id = ?";

        try (Connection cn = SqlConnection.getConnection();
             PreparedStatement pstm = cn.prepareStatement(sql)) {

            pstm.setInt(1, id);
            pstm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

