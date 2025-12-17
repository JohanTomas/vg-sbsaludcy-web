package pe.edu.vallegrande.projectsoftware.service;

import pe.edu.vallegrande.projectsoftware.db.SqlConnection;
import pe.edu.vallegrande.projectsoftware.dto.PrescriptionDto;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PrescriptionService {

    public List<PrescriptionDto> getAll(){
        List<PrescriptionDto> lista = new ArrayList<>();
        Connection cn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        String sql;

        try{
            cn = SqlConnection.getConnection();
            sql = "SELECT r.id," +
                    "c.title,AS reservation_title" +
                    "p.name,AS pacient_name" +
                    "p.lastname,AS pacient_lastname" +
                    "m.name AS medic_name" +
                    "m.lastname,AS medic_lastname" +
                    "r.sickness," +
                    "r.medicaments," +
                    "r.price," +
                    "r.created_at" +
                    "FROM prescription r" +
                    "JOIN reservation c ON r.reservation_id = c.id" +
                    "JOIN medic m ON c.medic_id = m.id" +
                    "JOIN pacient p ON c.pacient_id = p.id"+
                    "WHERE r.is_active = 'A'";
            pstm = cn.prepareStatement(sql);
            rs = pstm.executeQuery();
            while (rs.next()){
                PrescriptionDto bean = new PrescriptionDto();
                bean.setId(rs.getInt("id"));
                bean.setTitle(rs.getString("reservation_title"));
                bean.setPacient_name(rs.getString("pacient__name"));
                bean.setPacient_lastname(rs.getString("pacient_lastname"));
                bean.setMedic_name(rs.getString("medic_name"));
                bean.setMedic_lastname(rs.getString("medic_lastname"));
                bean.setSickness(rs.getString("sickness"));
                bean.setMedicaments(rs.getString("medicaments"));
                bean.setPrice(rs.getString("price"));
                bean.setCreated_at(rs.getString("created_at"));
                lista.add(bean);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            try {
                if (rs != null) rs.close();
                if (pstm != null) pstm.close();
                if (cn != null) cn.close();
            }catch (SQLException e){
                e.printStackTrace();
            }
        }
        return lista;
    }

    public int create(PrescriptionDto bean){
        String sql = "INSERT INTO prescription (reservation_id, sickness, medicaments, price, is_active) VALUES (?, ?, ?, ?, ?)";
        int nuevoPrescriptionId = 0;
        try (Connection cn = SqlConnection.getConnection();
        PreparedStatement pstm = cn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){
            pstm.setString(1, bean.getReservation_id());
            pstm.setString(2, bean.getSickness());
            pstm.setString(3, bean.getMedicaments());
            pstm.setString(4, bean.getPrice());
            pstm.setString(5, bean.getIs_active());
            pstm.executeUpdate();
            try (ResultSet rs = pstm.getGeneratedKeys()){
                if (rs.next()){
                    nuevoPrescriptionId = rs.getInt(1);
                }
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return nuevoPrescriptionId;
    }

    public PrescriptionDto getPrescriptionId(int prescriptionId){
        PrescriptionDto prescription = null;
        Connection cn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM prescription WHERE id = ?";

        try{
            cn = SqlConnection.getConnection();
            pstm = cn.prepareStatement(sql);
            pstm.setInt(1, prescriptionId);

            if (rs.next()){
                prescription = new PrescriptionDto();
                prescription.setId(rs.getInt("id"));
                prescription.setReservation_id(rs.getString("reservation_id"));
                prescription.setSickness(rs.getString("sickness"));
                prescription.setMedicaments(rs.getString("medicaments"));
                prescription.setPrice(rs.getString("price"));
                prescription.setIs_active(rs.getString("is_active"));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            try {
                if (pstm != null) pstm.close();
                if (cn != null) cn.close();
            }catch (SQLException e){
                e.printStackTrace();
            }
        }
        return prescription;
    }

    public void delete(int id){
        String sql = "UPDATE prescription SET is_active = 'I' WHERE id = ?";

        try (Connection cn = SqlConnection.getConnection();
            PreparedStatement pstm = cn.prepareStatement(sql)){

            pstm.setInt(1, id);
            pstm.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public boolean updatePrescription(PrescriptionDto prescription){
        String sql = "UPDATE prescription SET reservation_id = ?, sickness = ?, medicaments = ?, price = ?, is_active = ? WHERE id = ?";
        boolean isUpdated = false;

        try (Connection cn = SqlConnection.getConnection();
            PreparedStatement pstm = cn.prepareStatement(sql)){
            pstm.setString(1, prescription.getReservation_id());
            pstm.setString(2, prescription.getSickness());
            pstm.setString(2,prescription.getMedicaments());
            pstm.setString(3, prescription.getPrice());
            pstm.setString(4, prescription.getIs_active());
            pstm.setInt(6, prescription.getId());

            int rowsAffected = pstm.executeUpdate();
            isUpdated = rowsAffected > 0;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return isUpdated;
    }

    public List<PrescriptionDto> getTOP(){
        List<PrescriptionDto> lista = new ArrayList<>();
        Connection cn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        String sql;

        try{
            cn = SqlConnection.getConnection();
            sql = "SELECT TOP 3" +
                    "c.title AS reservation_title," +
                    "1 AS is_active" +
                    "FROM  prescription r" +
                    "JOIN reservation c ON r.reservation_id = c.id" +
                    "WHERE r.is_active = 'A'" +
                    "ORDER BY r.id DESC";
            pstm = cn.prepareStatement(sql);
            rs = pstm.executeQuery();
            while (rs.next()){
                PrescriptionDto bean = new PrescriptionDto();
                bean.setTitle(rs.getString("reservation_title"));
                bean.setIs_active(rs.getString("is_active"));
                lista.add(bean);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            try {
                if (rs != null) rs.close();
                if (pstm != null) pstm.close();
                if (cn != null) cn.close();
            }catch (SQLException e){
                e.printStackTrace();
            }
        }
        return lista;
    }

}
