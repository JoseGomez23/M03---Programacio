package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;



public class JugadorDAO implements DAO<Jugador> {

    public static Connection con;

    static {
        try {
            con = DriverManager.getConnection("jdbc:mysql://192.168.56.103:3306/nba", "perepi", "pastanaga");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public boolean delete(Jugador j) {
        PreparedStatement ps = null;
        try {
            if (ps == null) {
                ps = con.prepareStatement("DELETE FROM jugadors" +
                                                    "WHERE jugador_id = ?");
                ps.setInt(1, j.getJugador_id());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean update(Jugador j) {
        PreparedStatement ps = null;
        try {
            if (ps == null) {
                ps = con.prepareStatement(
                        "UPDATE jugadors " +
                            "SET nom = ?" +
                            "WHERE jugador_id = ?");
                ps.setString(1,j.getNom() );
                ps.setInt(2, j.getJugador_id());
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
    public boolean create(Jugador j) {
        PreparedStatement ps = null;
        try {
            if (ps == null) {
                ps = con.prepareStatement(
                        "INSERT INTO jugadors (jugador_id, nom, cognom, Data_naixement, pes, alcada, dorsal, posicio, equipId)" +
                                "VALUES (?,?,?,?,?,?,?,?,?)");
                ps.setInt(1, j.getJugador_id());
                ps.setString(2, j.getNom());
                ps.setString(3, j.getCognom());
                ps.setDate(4, j.getData_naixement());
                ps.setDouble(5, j.getPes());
                ps.setDouble(6, j.getAlcada());
                ps.setString(7, j.getDorsal());
                ps.setString(8, j.getPosicio());
                ps.setInt(9, j.getEquip_id());
                ps.executeUpdate();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

}
