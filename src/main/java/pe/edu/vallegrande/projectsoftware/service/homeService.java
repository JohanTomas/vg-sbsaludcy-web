package pe.edu.vallegrande.projectsoftware.service;

import pe.edu.vallegrande.projectsoftware.db.SqlConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class homeService {

    public int getTotalReservaciones() {
        return getTotal("SELECT COUNT(*) AS Total FROM reservation WHERE is_active = 'A'");
    }

    public int getTotalPacientes() {
        return getTotal("SELECT COUNT(*) AS Total FROM pacient WHERE is_active = 'A'");
    }

    public int getTotalMedicos() {
        return getTotal("SELECT COUNT(*) AS Total FROM medic WHERE is_active = 'A'");
    }

    public int getTotalAdmins() {
        return getTotal("SELECT COUNT(*) AS Total FROM admin WHERE is_active = 'A'");
    }

    private int getTotal(String query) {
        int total = 0;
        try (Connection conn = SqlConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            if (rs.next()) {
                total = rs.getInt("Total");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return total;
    }
}
