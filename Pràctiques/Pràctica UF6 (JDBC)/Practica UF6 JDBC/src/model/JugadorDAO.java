package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;



public class JugadorDAO implements DAO<Jugador> {

    static Connection con;

    static {
        try {
            con = DriverManager.getConnection("jdbc:mysql://192.168.56.103:3306/nba", "perepi", "pastanaga");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public boolean delete(Jugador jugador) {
        PreparedStatement ps = null;
        try {
            if (ps == null) {
                ps = con.prepareStatement("DELETE FROM jugadors" +
                                                    "WHERE jugador_id = ?");
                ps.setInt(1,1);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean update(Jugador jugador) {
        PreparedStatement ps = null;
        try {
            if (ps == null) {
                ps = con.prepareStatement(
                        "UPDATE jugadors " +
                            "SET nom = ?" +
                            "WHERE jugador_id = ?");
                ps.setString(1,"Caramba" );
                ps.setInt(2, 1);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean read(Jugador jugador) {
        PreparedStatement ps = null;
        try {
            if (ps == null) {
                ps = con.prepareStatement(
                        "SELECT * FROM jugadors WHERE equip_id =  ?");
                ps.setInt(1, 1);
            }


        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public boolean exists(Jugador jugador) {
        return false;
    }

    @Override
    public boolean create(Jugador jugador) {
        PreparedStatement ps = null;
        try {
            if (ps == null) {
                ps = con.prepareStatement(
                        "INSERT INTO jugadors (jugador_id, nom, cognom)" +
                                "VALUES (?,?,?)");
                ps.setInt(1, 1);
                ps.setString(2, "Hulio");
                ps.setString(3, "Sacnchez");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

}
