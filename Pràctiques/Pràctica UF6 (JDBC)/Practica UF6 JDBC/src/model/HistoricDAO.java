package model;

import vista.Vista;

import java.sql.PreparedStatement;

import static model.JugadorDAO.con;

public class HistoricDAO implements DAO<Historic>{
    @Override
    public boolean delete(Historic h) {
        return false;
    }

    @Override
    public boolean update(Historic h) {
        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement(
                    "UPDATE historic" +
                            "SET partit_id = ?, minuts_jugats = ?, punts = ?, tirs_anotats = ?, tirs_tirats = ?, tirs_triples_anotats = ?," +
                            " tirs_triples_tirats = ?, tirs_lliures_anotats = ?, tirs_lliures_tirats = ?, rebots_ofensius = ?," +
                            " rebots_defensius = ?, assistencies = ?, robades = ?, bloqueigs = ?" +
                            "WHERE nom = ? AND cognom = ?"
            );
        } catch (Exception ex) {
            ex.getStackTrace();
            Vista.missatgeError();
        }
        return false;
    }

    @Override
    public boolean exists(Historic h) {
        return false;
    }

    @Override
    public boolean create(Historic h) {
        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement(
                    "INSERT INTO estadistiques_jugadors (jugador_id, partit_id, minuts_jugats, punts, tirs_anotats, tirs_tirats," +
                            " tirs_triples_anotats, tirs_triples_tirats, tirs_lliures_anotats, tirs_lliures_tirats, rebots_ofensius," +
                            " rebots_defensius, assistencies, robades, bloqueigs)" +
                            "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
            ps.setInt(1, h.getJugador_id());
            ps.setInt(2, h.getPartit_id());
            ps.setDouble(3, h.getMinuts_jugats());
            ps.setInt(4, h.getPunts());
            ps.setInt(5, h.getTirs_anotats());
            ps.setInt(6, h.getTirs_tirats());
            ps.setInt(7, h.getTirs_triples_anotats());
            ps.setInt(8, h.getTirs_triples_tirats());
            ps.setInt(9, h.getTirs_lliures_anotats());
            ps.setInt(10, h.getTirs_lliures_tirats());
            ps.setInt(11, h.getRebots_ofensius());
            ps.setInt(12, h.getRebots_defensius());
            ps.setInt(13, h.getAssistencies());
            ps.setInt(14, h.getRobades());
            ps.setInt(15, h.getBloqueigs());
            ps.executeUpdate();
        } catch (Exception ex) {
            ex.printStackTrace();
            Vista.missatgeError();
        }
        return false;
    }

    @Override
    public boolean read(Historic h) {
        return false;
    }
}
