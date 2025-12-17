package pe.edu.vallegrande.projectsoftware.service;

import pe.edu.vallegrande.projectsoftware.db.SqlConnection;
import pe.edu.vallegrande.projectsoftware.dto.PacientDto;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class p_restoreService {
    public List<PacientDto> getAll() {
        List<PacientDto> list = new ArrayList<>();
        Connection cn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        String sql;

        try {
            cn = SqlConnection.getConnection();
            sql = "SELECT * FROM pacient WHERE is_active = 'I'";
            pstm = cn.prepareStatement(sql);
            rs = pstm.executeQuery();
            while (rs.next()) {
                PacientDto pacient = new PacientDto();
                pacient.setId(rs.getInt("id"));
                pacient.setName(rs.getString("name"));
                pacient.setLastname(rs.getString("lastname"));
                pacient.setGender(rs.getString("gender"));
                pacient.setDocument_type(rs.getString("document_type"));
                pacient.setDocument_dni(rs.getString("document_dni"));
                pacient.setDay_of_birth(rs.getString("day_of_birth"));
                pacient.setAddress(rs.getString("address"));
                pacient.setEmail(rs.getString("email"));
                pacient.setPhone(rs.getString("phone"));
                pacient.setSickness(rs.getString("sickness"));
                pacient.setAlergy(rs.getString("alergy"));
                pacient.setIs_active(rs.getString("is_active"));
                list.add(pacient);
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
        return list;
    }

    public void restore(int id) {
        String sql = "UPDATE pacient SET is_active = 'A' WHERE id = ?";
        try (Connection cn = SqlConnection.getConnection();
             PreparedStatement pstm = cn.prepareStatement(sql)) {

            pstm.setInt(1, id);
            pstm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
