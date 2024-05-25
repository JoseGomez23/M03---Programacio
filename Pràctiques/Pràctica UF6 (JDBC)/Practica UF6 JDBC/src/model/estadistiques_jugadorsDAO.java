package model;

import vista.Vista;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import static model.JugadorDAO.con;

public class estadistiques_jugadorsDAO implements DAO<estadistiques_jugadors> {


    @Override
    public boolean delete(estadistiques_jugadors e) {
        PreparedStatement ps = null;
        try{
            ps = con.prepareStatement(
                    "DELETE FROM estadistiques_jugadors WHERE jugador_id = ?"
            );
            ps.setInt(1, e.getJugador_id());

        } catch (Exception ex) {
            Vista.missatgeError();

        }


        return false;
    }

    @Override
    public boolean update(estadistiques_jugadors e) {
        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement(
                    "UPDATE estadistiques_jugadors" +
                            "SET partit_id = ?, minuts_jugats = ?, punts = ?, tirs_anotats = ?, tirs_tirats = ?, tirs_triples_anotats = ?," +
                            " tris_triples_tirats = ?, tirs_lliures_anotats = ?, tirs_lliures_tirats = ?, rebots_ofensius = ?," +
                            " rebots_defensius = ?, assistencies = ?, robades = ?, bloqueigs = ?" +
                            "WHERE jugador_id = ?"
            );
            ps.setInt(1, e.getPartit_id());
            ps.setDouble(2, e.getMinuts_jugats());
            ps.setInt(3, e.getPunts());
            ps.setInt(4, e.getTirs_anotats());
            ps.setInt(5, e.getTirs_tirats());
            ps.setInt(6, e.getTirs_triples_anotats());
            ps.setInt(7, e.getTirs_triples_tirats());
            ps.setInt(8, e.getTirs_lliures_anotats());
            ps.setInt(9, e.getTirs_lliures_tirats());
            ps.setInt(10, e.getRebots_ofensius());
            ps.setInt(11, e.getRebots_defensius());
            ps.setInt(12, e.getAsistencies());
            ps.setInt(13, e.getRobades());
            ps.setInt(14, e.getBloqueigs());
            ps.setInt(15, e.getJugador_id());
            ps.executeUpdate();
        } catch (Exception ex) {
            ex.getStackTrace();
            Vista.missatgeError();
        }
        return false;
    }


    @Override
    public boolean exists(estadistiques_jugadors e) {
        return true;
    }

    @Override
    public boolean create(estadistiques_jugadors e) {
        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement(
                    "INSERT INTO estadistiques_jugadors (jugador_id, partit_id, minuts_jugats, punts, tirs_anotats, tirs_tirats," +
                            " tirs_triples_anotats, tirs_triples_tirats, tirs_lliures_anotats, tirs_lliures_tirats, rebots_ofensius," +
                            " rebots_defensius, assistencies, robades, bloqueigs)" +
                            "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
            ps.setInt(1, e.getJugador_id());
            ps.setInt(2, e.getPartit_id());
            ps.setDouble(3, e.getMinuts_jugats());
            ps.setInt(4, e.getPunts());
            ps.setInt(5, e.getTirs_anotats());
            ps.setInt(6, e.getTirs_tirats());
            ps.setInt(7, e.getTirs_triples_anotats());
            ps.setInt(8, e.getTirs_triples_tirats());
            ps.setInt(9, e.getTirs_lliures_anotats());
            ps.setInt(10, e.getTirs_lliures_tirats());
            ps.setInt(11, e.getRebots_ofensius());
            ps.setInt(12, e.getRebots_defensius());
            ps.setInt(13, e.getAsistencies());
            ps.setInt(14, e.getRobades());
            ps.setInt(15, e.getBloqueigs());
            ps.executeUpdate();
        } catch (Exception ex) {
            ex.printStackTrace();
            Vista.missatgeError();
        }
        return false;
    }

    @Override
    public boolean read(estadistiques_jugadors e) {
        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement(
                    "SELECT * FROM estadistiques_jugadors WHERE jugador_id =  ?");
            ps.setInt(1, e.getJugador_id());


        } catch (Exception ex) {
            ex.printStackTrace();
            Vista.missatgeError();
        }

        return false;
    }

    public boolean readEstadistiquesAvg(estadistiques_jugadors e) {
        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement(
                    "SELECT * FROM estadistiques_jugadors WHERE jugador_id =  ?");
            ps.setInt(1, e.getJugador_id());


        } catch (Exception ex) {
            ex.printStackTrace();
            Vista.missatgeError();
        }

        return false;
    }
}
