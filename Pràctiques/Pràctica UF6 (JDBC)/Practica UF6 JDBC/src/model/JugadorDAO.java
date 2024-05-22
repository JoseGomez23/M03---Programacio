package model;

import vista.Vista;

import java.sql.*;
import java.util.ArrayList;


public class JugadorDAO implements DAO<Jugador> {

    public static Connection con;

    static {
        try {
            con = DriverManager.getConnection("jdbc:mysql://192.168.56.103:3306/nba", "perepi", "pastanaga");
        } catch (SQLException e) {
            throw new RuntimeException(e);

        }
    }

    public JugadorDAO(Connection con) {
        JugadorDAO.con = con;
    }


    @Override
    public boolean delete(Jugador j) {
        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement("DELETE FROM jugadors" +
                    "WHERE jugador_id = ?");
            ps.setInt(1, j.getJugador_id());
            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
            Vista.missatgeError();
        }
        return false;
    }

    @Override
    public boolean update(Jugador j) {
        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement(
                    "UPDATE jugadors " +
                        "SET nom = ?, cognom = ?, Data_naixement = ?, pes = ?, alcada = ?, dorsal = ?, posicio = ?, equipId = ?" +
                        "WHERE jugador_id = ?");
            ps.setString(1,j.getNom() );
            ps.setString(2, j.getNom());
            ps.setDate(3, j.getData_naixement());
            ps.setDouble(4, j.getPes());
            ps.setDouble(5, j.getAlcada());
            ps.setInt(6, j.getDorsal());
            ps.setString(7, j.getPosicio());
            ps.setInt(8, j.getEquip_id());
            ps.setInt(2, j.getJugador_id());
            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
            Vista.missatgeError();
        }
        return false;
    }

    @Override
    public boolean exists(Jugador jugador) {
        return true;
    }

    @Override
    public boolean read(Jugador j) {
        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement(
                    "SELECT * FROM jugadors WHERE jugador_id = ?");
            ps.setInt(1, j.getJugador_id());
            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
            Vista.missatgeError();
        }

        return false;
    }


    @Override
    public boolean create(Jugador j) {
        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement(
                    "INSERT INTO jugadors (jugador_id, nom, cognom, Data_naixement, pes, alcada, dorsal, posicio, equipId)" +
                            "VALUES (?,?,?,?,?,?,?,?,?)");
            ps.setInt(1, j.getJugador_id());
            ps.setString(2, j.getNom());
            ps.setString(3, j.getCognom());
            ps.setDate(4, j.getData_naixement());
            ps.setDouble(5, j.getPes());
            ps.setDouble(6, j.getAlcada());
            ps.setInt(7, j.getDorsal());
            ps.setString(8, j.getPosicio());
            ps.setInt(9, j.getEquip_id());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            Vista.missatgeError();
        }

        return false;
    }

}
