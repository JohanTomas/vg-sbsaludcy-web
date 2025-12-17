package pe.edu.vallegrande.projectsoftware.service;

import pe.edu.vallegrande.projectsoftware.db.SqlConnection;
import pe.edu.vallegrande.projectsoftware.dto.MedicDto;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MedicService {
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
                    "WHERE m.is_active = 'A'";
            pstm = cn.prepareStatement(sql);
            rs = pstm.executeQuery();
            while (rs.next()) {
                MedicDto bean = new MedicDto();
                bean.setId(rs.getInt("id"));
                bean.setName(rs.getString("name"));
                bean.setLastname(rs.getString("lastname"));
                bean.setGender(rs.getString("gender"));
                bean.setDocument_type(rs.getString("document_type"));
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

    public int create(MedicDto bean) {
        String sql = "INSERT INTO medic (name, lastname, gender, document_type, document_dni, day_of_birth, address, email, phone, is_active, category_id) VALUES (?,?,?,?,?,?,?,?,?,?,?)";
        int nuevoMedicId = 0;

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
            pstm.setString(10, bean.getIs_active());
            pstm.setString(11, bean.getCategory_id());
            pstm.executeUpdate();
            try (ResultSet rs = pstm.getGeneratedKeys()) {
                if (rs.next()) {
                    nuevoMedicId = rs.getInt(1);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return nuevoMedicId;
    }

    public MedicDto getMedicId(int medicId) {
        MedicDto medic = null;
        Connection cn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        String sql = "SELECT m.*, c.name AS category_name " +
                "FROM medic m " +
                "INNER JOIN category c ON m.category_id = c.id " +
                "WHERE m.id = ?";

        try {
            cn = SqlConnection.getConnection();
            pstm = cn.prepareStatement(sql);
            pstm.setInt(1, medicId);
            rs = pstm.executeQuery();

            if(rs.next()) {
                medic = new MedicDto();
                medic.setId(rs.getInt("id"));
                medic.setName(rs.getString("name"));
                medic.setLastname(rs.getString("lastname"));
                medic.setGender(rs.getString("gender"));
                medic.setDocument_type(rs.getString("document_type"));
                medic.setDocument_dni(rs.getString("document_dni"));
                medic.setDay_of_birth(rs.getString("day_of_birth"));
                medic.setAddress(rs.getString("address"));
                medic.setEmail(rs.getString("email"));
                medic.setPhone(rs.getString("phone"));
                medic.setCategory_id(rs.getString("category_id"));
                medic.setCategory_name(rs.getString("category_name"));
                medic.setIs_active(rs.getString("is_active"));
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
        return medic;
    }

    public boolean updateMedic(MedicDto medic) {
        Connection cn = null;
        PreparedStatement pstm = null;
        String sql = "UPDATE medic SET name=?, lastname=?, gender=?, document_type=?, document_dni=?, day_of_birth=?, address=?, email=?, phone=?, is_active=?, category_id=? WHERE id=?";
        boolean isUpdated = false;

        try {
            cn = SqlConnection.getConnection();
            pstm = cn.prepareStatement(sql);
            pstm.setString(1, medic.getName());
            pstm.setString(2, medic.getLastname());
            pstm.setString(3, medic.getGender());
            pstm.setString(4, medic.getDocument_type());
            pstm.setString(5, medic.getDocument_dni());
            pstm.setString(6, medic.getDay_of_birth());
            pstm.setString(7, medic.getAddress());
            pstm.setString(8, medic.getEmail());
            pstm.setString(9, medic.getPhone());
            pstm.setString(10, medic.getIs_active());
            pstm.setString(11, medic.getCategory_id());
            pstm.setInt(12, medic.getId());
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
        String sql = "UPDATE medic SET is_active = 'I' WHERE id = ?";

        try (Connection cn = SqlConnection.getConnection();
             PreparedStatement pstm = cn.prepareStatement(sql)) {

            pstm.setInt(1, id);
            pstm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /* Table Layout */
    public List<MedicDto> getTOP() {
        List<MedicDto> lista = new ArrayList<>();
        Connection cn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;

        try {
            cn = SqlConnection.getConnection();
            String sql = "SELECT TOP 3 " +
                    "    name, " +
                    "    lastname, " +
                    "    1 AS is_active " +
                    "FROM medic " +
                    "WHERE is_active = 'A' " +
                    "ORDER BY id DESC";

            pstm = cn.prepareStatement(sql);
            rs = pstm.executeQuery();

            while (rs.next()) {
                MedicDto bean = new MedicDto();
                bean.setName(rs.getString("name"));
                bean.setLastname(rs.getString("lastname"));
                bean.setIs_active(rs.getString("is_active"));
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
}

