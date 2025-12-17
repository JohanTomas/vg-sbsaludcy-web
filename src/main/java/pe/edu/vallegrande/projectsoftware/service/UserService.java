package pe.edu.vallegrande.projectsoftware.service;

import pe.edu.vallegrande.projectsoftware.db.SqlConnection;
import pe.edu.vallegrande.projectsoftware.dto.UserDto;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserService {
    /* MOSTRAR REGISTRO - Principal */
    public List<UserDto> getAll() {
        List<UserDto> lista = new ArrayList<>();
        Connection cn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        String sql;

        try {
            cn = SqlConnection.getConnection();
            sql = "SELECT * FROM admin WHERE is_active = 'A'";
            pstm = cn.prepareStatement(sql);
            rs = pstm.executeQuery();
            while (rs.next()) {
                UserDto bean = new UserDto();
                bean.setId(rs.getInt("id"));
                bean.setUsername(rs.getString("username"));
                bean.setName(rs.getString("name"));
                bean.setLastname(rs.getString("lastname"));
                bean.setEmail(rs.getString("email"));
                bean.setPassword(rs.getString("password"));
                bean.setIs_active(rs.getString("is_active"));
                bean.setIs_admin(rs.getString("is_admin"));
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

    /* CREAR REGISTRO */
    public int create(UserDto bean) {
        Connection cn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        String sql;
        int nuevoUserId = 0;

        try {
            cn = SqlConnection.getConnection();
            sql = "INSERT INTO admin (name, lastname, username, email, password, is_active, is_admin) VALUES (?,?,?,?,?,?,?)";
            pstm = cn.prepareStatement(sql);
            pstm.setString(1, bean.getName());
            pstm.setString(2, bean.getLastname());
            pstm.setString(3, bean.getUsername());
            pstm.setString(4, bean.getEmail());
            pstm.setString(5, bean.getPassword());
            pstm.setString(6, bean.getIs_active());
            pstm.setString(7, bean.getIs_admin());
            pstm.executeUpdate();

            rs = pstm.getGeneratedKeys();
            if (rs.next()) {
                nuevoUserId = rs.getInt(1);
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
        return nuevoUserId;
    }

    /* VISUALIZACIÓN DEL REGISTRO */
    public UserDto getUserId(int userId) {
        UserDto user = null;
        Connection cn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM admin WHERE id = ?";

        try {
            cn = SqlConnection.getConnection();
            pstm = cn.prepareStatement(sql);
            pstm.setInt(1, userId);
            rs = pstm.executeQuery();

            if (rs.next()) {
                user = new UserDto();
                user.setId(rs.getInt("id"));
                user.setUsername(rs.getString("username"));
                user.setName(rs.getString("name"));
                user.setLastname(rs.getString("lastname"));
                user.setEmail(rs.getString("email"));
                user.setPassword(rs.getString("password"));
                user.setIs_active(rs.getString("is_active"));
                user.setIs_admin(rs.getString("is_admin"));
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
        return user;
    }

    /* ACTUALIZAR REGISTRO USUARIO */
    public void updateUser(UserDto user) {
        Connection cn = null;
        PreparedStatement pstm = null;
        String sql = "UPDATE admin SET name=?, lastname=?, username=?, email=?, password=?, is_active=?, is_admin=? WHERE id=?";

        try {
            cn = SqlConnection.getConnection();
            pstm = cn.prepareStatement(sql);
            pstm.setString(1, user.getName());
            pstm.setString(2, user.getLastname());
            pstm.setString(3, user.getUsername());
            pstm.setString(4, user.getEmail());
            pstm.setString(5, user.getPassword());
            pstm.setString(6, user.getIs_active());
            pstm.setString(7, user.getIs_admin());
            pstm.setInt(8, user.getId());
            pstm.executeUpdate();
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
    }

    /* ELIMINADO LOGICO */
    public void delete(int id) {
        Connection cn = null;
        PreparedStatement pstm = null;
        String sql;

        try {
            cn = SqlConnection.getConnection();
            sql = "UPDATE admin SET is_active = 'I' WHERE id = ?";
            pstm = cn.prepareStatement(sql);
            pstm.setInt(1, id);
            pstm.executeUpdate();
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
    }
}
