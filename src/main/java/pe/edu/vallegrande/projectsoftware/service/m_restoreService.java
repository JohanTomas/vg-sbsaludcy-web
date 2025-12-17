package pe.edu.vallegrande.projectsoftware.service;

import pe.edu.vallegrande.projectsoftware.db.SqlConnection;
import pe.edu.vallegrande.projectsoftware.dto.MedicDto;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class m_restoreService {
    public List<MedicDto> getAll() {
        List<MedicDto> lista = new ArrayList<>();
        Connection cn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        String sql;

        try {
            cn = SqlConnection.getConnection();
            sql = "SELECT m.*, c.name AS category_name " +
                    "FROM medic m " +
                    "INNER JOIN category c ON m.category_id = c.id " +
                    "WHERE m.is_active = 'I'";
            pstm = cn.prepareStatement(sql);
            rs = pstm.executeQuery();
            while (rs.next()) {
                MedicDto bean = new MedicDto();
                bean.setId(rs.getInt("id"));
                bean.setName(rs.getString("name"));
                bean.setLastname(rs.getString("lastname"));
                bean.setDocument_dni(rs.getString("document_dni"));
                bean.setDay_of_birth(rs.getString("day_of_birth"));
                bean.setAddress(rs.getString("address"));
                bean.setEmail(rs.getString("email"));
                bean.setPhone(rs.getString("phone"));
                bean.setCategory_id(rs.getString("category_id"));
                bean.setCategory_name(rs.getString("category_name"));
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

    public void delete(int id) {
        String sql = "UPDATE medic SET is_active = 'A' WHERE id = ?";

        try (Connection cn = SqlConnection.getConnection();
             PreparedStatement pstm = cn.prepareStatement(sql)) {

            pstm.setInt(1, id);
            pstm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

