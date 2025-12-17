package pe.edu.vallegrande.projectsoftware.service;

import pe.edu.vallegrande.projectsoftware.db.SqlConnection;
import pe.edu.vallegrande.projectsoftware.dto.QuotesDto;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class q_restoreService {

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
                    "WHERE r.is_active = 'I'";
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
}
