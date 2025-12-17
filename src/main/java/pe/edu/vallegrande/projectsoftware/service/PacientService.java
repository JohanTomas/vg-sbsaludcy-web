package pe.edu.vallegrande.projectsoftware.service;

import pe.edu.vallegrande.projectsoftware.db.SqlConnection;
import pe.edu.vallegrande.projectsoftware.dto.PacientDto;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PacientService {

    public List<PacientDto> getAll() {
        List<PacientDto> list = new ArrayList<>();
        Connection cn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        String sql;

        try {
            cn = SqlConnection.getConnection();
            sql = "SELECT * FROM pacient WHERE is_active = 'A'";
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

    public int create(PacientDto bean) {
        String sql = "INSERT INTO pacient (name, lastname, gender, document_type, document_dni, day_of_birth, address, email, phone, sickness, alergy, is_active) VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";
        int nuevoPacientId = 0;

        try (Connection cn = SqlConnection.getConnection();
             PreparedStatement pstm = cn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            pstm.setString(1, bean.getName());
            pstm.setString(2, bean.getLastname());
            pstm.setString(3, bean.getGender());
            pstm.setString(4, bean.getDocument_type());
            pstm.setString(5, bean.getDocument_dni());
            pstm.setString(6, bean.getDay_of_birth());
            pstm.setString(7, bean.getAddress());
            pstm.setString(8, bean.getEmail());
            pstm.setString(9, bean.getPhone());
            pstm.setString(10, bean.getSickness());
            pstm.setString(11, bean.getAlergy());
            pstm.setString(12, bean.getIs_active());
            pstm.executeUpdate();

            try (ResultSet rs = pstm.getGeneratedKeys()) {
                if (rs.next()) {
                    nuevoPacientId = rs.getInt(1);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return nuevoPacientId;
    }

    public PacientDto getPacientId(int pacientId) {
        PacientDto pacient = null;
        Connection cn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM pacient WHERE id = ?";

        try {
            cn = SqlConnection.getConnection();
            pstm = cn.prepareStatement(sql);
            pstm.setInt(1, pacientId);
            rs = pstm.executeQuery();

            if (rs.next()) {
                pacient = new PacientDto();
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
        return pacient;
    }

    public boolean updatePacient(PacientDto pacient) {
        Connection cn = null;
        PreparedStatement pstm = null;
        String sql = "UPDATE pacient SET name=?, lastname=?, gender=?, document_type=?, document_dni=?, day_of_birth=?, address=?, email=?, phone=?, sickness=?, alergy=?, is_active=? WHERE id=?";
        boolean isUpdated = false;

        try {
            cn = SqlConnection.getConnection();
            pstm = cn.prepareStatement(sql);
            pstm.setString(1, pacient.getName());
            pstm.setString(2, pacient.getLastname());
            pstm.setString(3, pacient.getGender());
            pstm.setString(4, pacient.getDocument_type());
            pstm.setString(5, pacient.getDocument_dni());
            pstm.setString(6, pacient.getDay_of_birth());
            pstm.setString(7, pacient.getAddress());
            pstm.setString(8, pacient.getEmail());
            pstm.setString(9, pacient.getPhone());
            pstm.setString(10, pacient.getSickness());
            pstm.setString(11, pacient.getAlergy());
            pstm.setString(12, pacient.getIs_active());
            pstm.setInt(13, pacient.getId());
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
        String sql = "UPDATE pacient SET is_active = 'I' WHERE id = ?";

        try (Connection cn = SqlConnection.getConnection();
             PreparedStatement pstm = cn.prepareStatement(sql)) {

            pstm.setInt(1, id);
            pstm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /* Table Layout */
    public List<PacientDto> getTOP() {
        List<PacientDto> list = new ArrayList<>();
        Connection cn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;

        try {
            cn = SqlConnection.getConnection(); // Obtener la conexión (debes definir SqlConnection.getConnection() según tu implementación)
            String sql = "SELECT TOP 3 " +
                    "    name, " +
                    "    lastname, " +
                    "    1 AS is_active " +
                    "FROM pacient " +
                    "WHERE is_active = 'A' " +
                    "ORDER BY id DESC";

            pstm = cn.prepareStatement(sql);
            rs = pstm.executeQuery();

            while (rs.next()) {
                PacientDto pacient = new PacientDto();
                pacient.setName(rs.getString("name"));
                pacient.setLastname(rs.getString("lastname"));
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
}

