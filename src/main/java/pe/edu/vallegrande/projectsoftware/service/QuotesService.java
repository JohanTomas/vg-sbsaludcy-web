package pe.edu.vallegrande.projectsoftware.service;

import pe.edu.vallegrande.projectsoftware.db.SqlConnection;
import pe.edu.vallegrande.projectsoftware.dto.QuotesDto;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class QuotesService {

    public List<QuotesDto> getAll() {
        List<QuotesDto> lista = new ArrayList<>();
        Connection cn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        String sql;

        try {
            cn = SqlConnection.getConnection();
            sql = "SELECT r.id, r.title, r.message, r.date_at, r.time_at, " +
                    "r.symtoms," +
                    "p.name AS pacient_name, p.lastname AS pacient_lastname, " +
                    "m.name AS medic_name, m.lastname AS medic_lastname, " +
                    "a.name AS admin_name, a.lastname AS admin_lastname, " +
                    "s.name AS status_name, py.name AS payment_name, " +
                    "r.is_active " +
                    "FROM reservation r " +
                    "LEFT JOIN pacient p ON r.pacient_id = p.id " +
                    "LEFT JOIN medic m ON r.medic_id = m.id " +
                    "LEFT JOIN admin a ON r.admin_id = a.id " +
                    "LEFT JOIN status s ON r.status_id = s.id " +
                    "LEFT JOIN payment py ON r.payment_id = py.id " +
                    "WHERE r.is_active = 'A'";
            pstm = cn.prepareStatement(sql);
            rs = pstm.executeQuery();
            while (rs.next()) {
                QuotesDto bean = new QuotesDto();
                bean.setId(rs.getInt("id"));
                bean.setTitle(rs.getString("title"));
                bean.setMessage(rs.getString("message"));
                bean.setDate_at(rs.getString("date_at"));
                bean.setTime_at(rs.getString("time_at"));
                bean.setSymtoms(rs.getString("symtoms"));
                bean.setPacient_name(rs.getString("pacient_name"));
                bean.setPacient_lastname(rs.getString("pacient_lastname"));
                bean.setMedic_name(rs.getString("medic_name"));
                bean.setMedic_lastname(rs.getString("medic_lastname"));
                bean.setStatus_name(rs.getString("status_name"));
                bean.setPayment_name(rs.getString("payment_name"));
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

    public int create(QuotesDto bean) {
        String sql = "INSERT INTO reservation (title, message, date_at, time_at, pacient_id, symtoms, medic_id, status_id, payment_id, is_active) VALUES (?,?,?,?,?,?,?,?,?,?)";
        int nuevoQuotesId = 0;

        try (Connection cn = SqlConnection.getConnection();
             PreparedStatement pstm = cn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pstm.setString(1, bean.getTitle());
            pstm.setString(2, bean.getMessage());
            pstm.setString(3, bean.getDate_at());
            pstm.setString(4, bean.getTime_at());
            pstm.setString(5, bean.getPacient_id());
            pstm.setString(6, bean.getSymtoms());
            pstm.setString(7, bean.getMedic_id());
            pstm.setString(8, bean.getStatus_id());
            pstm.setString(9, bean.getPayment_id());
            pstm.setString(10, bean.getIs_active());
            pstm.executeUpdate();
            try (ResultSet rs = pstm.getGeneratedKeys()) {
                if (rs.next()) {
                    nuevoQuotesId = rs.getInt(1);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return nuevoQuotesId;
    }

    public QuotesDto getQuotesId(int quotesId) {
        QuotesDto quotes = null;
        Connection cn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM reservation WHERE id = ?";

        try {
            cn = SqlConnection.getConnection();
            pstm = cn.prepareStatement(sql);
            pstm.setInt(1, quotesId);
            rs = pstm.executeQuery();

            if (rs.next()) {
                quotes = new QuotesDto();
                quotes.setId(rs.getInt("id"));
                quotes.setTitle(rs.getString("title"));
                quotes.setMessage(rs.getString("message"));
                quotes.setDate_at(rs.getString("date_at"));
                quotes.setTime_at(rs.getString("time_at"));
                quotes.setSymtoms(rs.getString("symtoms"));
                quotes.setPacient_id(rs.getString("pacient_id"));
                quotes.setMedic_id(rs.getString("medic_id"));
                quotes.setStatus_id(rs.getString("status_id"));
                quotes.setPayment_id(rs.getString("payment_id"));
                /*quotes.setPacient_name(rs.getString("pacient_name"));
                quotes.setPacient_lastname(rs.getString("pacient_lastname"));
                quotes.setMedic_name(rs.getString("medic_name"));
                quotes.setMedic_lastname(rs.getString("medic_lastname"));
                quotes.setStatus_name(rs.getString("status_name"));
                quotes.setPayment_name(rs.getString("payment_name"));*/
                quotes.setIs_active(rs.getString("is_active"));
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
        return quotes;
    }

    public void delete(int id) {
        String sql = "UPDATE reservation SET is_active = 'I' WHERE id = ?";

        try (Connection cn = SqlConnection.getConnection();
             PreparedStatement pstm = cn.prepareStatement(sql)) {

            pstm.setInt(1, id);
            pstm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean updateQuotes(QuotesDto quotes) {
        String sql = "UPDATE reservation SET title=?, message=?, date_at=?, time_at=?, pacient_id=?, symtoms=?, medic_id=?, status_id=?, payment_id=?, is_active=? WHERE id=?";
        boolean isUpdated = false;

        try (Connection cn = SqlConnection.getConnection();
             PreparedStatement pstm = cn.prepareStatement(sql)) {

            pstm.setString(1, quotes.getTitle());
            pstm.setString(2, quotes.getMessage());
            pstm.setString(3, quotes.getDate_at());
            pstm.setString(4, quotes.getTime_at());
            pstm.setString(5, quotes.getPacient_id());
            pstm.setString(6, quotes.getSymtoms());
            pstm.setString(7, quotes.getMedic_id());
            pstm.setString(8, quotes.getStatus_id());
            pstm.setString(9, quotes.getPayment_id());
            pstm.setString(10, quotes.getIs_active());
            pstm.setInt(11, quotes.getId());

            int rowsAffected = pstm.executeUpdate();
            isUpdated = rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return isUpdated;
    }

    /* Table Layout */
    public List<QuotesDto> getTOP() {
        List<QuotesDto> lista = new ArrayList<>();
        Connection cn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        String sql;

        try {
            cn = SqlConnection.getConnection();
            sql = "SELECT TOP 3 " +
                    "p.name AS pacient_name, " +
                    "p.lastname AS pacient_lastname, " +
                    "1 AS is_active " +
                    "FROM reservation r " +
                    "JOIN pacient p ON r.pacient_id = p.id " +
                    "WHERE r.is_active = 'A' " +
                    "ORDER BY r.id DESC";
            pstm = cn.prepareStatement(sql);
            rs = pstm.executeQuery();
            while (rs.next()) {
                QuotesDto bean = new QuotesDto();
                bean.setPacient_name(rs.getString("pacient_name"));
                bean.setPacient_lastname(rs.getString("pacient_lastname"));
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

