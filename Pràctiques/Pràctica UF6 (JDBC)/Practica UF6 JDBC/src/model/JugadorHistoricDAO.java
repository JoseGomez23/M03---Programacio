package model;

import vista.Vista;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class JugadorHistoricDAO implements DAO<JugadorHistoric>{

    public static Connection con;
    @Override
    public boolean delete(JugadorHistoric jh) {
        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement("DELETE FROM jugadorsHistoric" +
                    "WHERE jugador_id = ?");
            ps.setInt(1, jh.getJugador_id());
            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
            Vista.missatgeError();
        }
        return false;
    }

    @Override
    public boolean update(JugadorHistoric jh) {
        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement(
                    "UPDATE jugadorsHistoric " +
                            "SET nom = ?, cognom = ?, data_naixement = ?, pes = ?, alcada = ?, dorsal = ?, posicio = ?, equip_id = ?" +
                            " WHERE jugador_id = ?;");
            ps.setString(1,jh.getNom() );
            ps.setString(2, jh.getCognom());
            ps.setDate(3, jh.getData_naixement());
            ps.setDouble(4, jh.getPes());
            ps.setDouble(5, jh.getAlcada());
            ps.setInt(6, jh.getDorsal());
            ps.setString(7, jh.getPosicio());
            ps.setInt(8, jh.getEquip_id());
            ps.setInt(9, jh.getJugador_id());
            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
            Vista.missatgeError();
        }
        return false;
    }

    @Override
    public boolean exists(JugadorHistoric jh) {
        return false;
    }

    @Override
    public boolean create(JugadorHistoric jh) {
        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement(
                    "SELECT * FROM jugadorsHistoric WHERE jugador_id = ?");
            ps.setInt(1, jh.getJugador_id());
            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
            Vista.missatgeError();
        }

        return false;
    }

    @Override
    public boolean read(JugadorHistoric jh) {
        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement(
                    "INSERT INTO jugadors (jugador_id, nom, cognom, Data_naixement, pes, alcada, dorsal, posicio, equipId)" +
                            "VALUES (?,?,?,?,?,?,?,?,?)");
            ps.setInt(1, jh.getJugador_id());
            ps.setString(2, jh.getNom());
            ps.setString(3, jh.getCognom());
            ps.setDate(4, jh.getData_naixement());
            ps.setDouble(5, jh.getPes());
            ps.setDouble(6, jh.getAlcada());
            ps.setInt(7, jh.getDorsal());
            ps.setString(8, jh.getPosicio());
            ps.setInt(9, jh.getEquip_id());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            Vista.missatgeError();
        }

        return false;
    }
}
