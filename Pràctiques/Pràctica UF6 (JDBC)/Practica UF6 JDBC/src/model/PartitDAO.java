package model;
import vista.Vista;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import static model.Model.con;

public class PartitDAO implements DAO<Partit>{


    @Override
    public boolean delete(Partit p) {
        PreparedStatement ps = null;
        try{
            ps = con.prepareStatement(
                    "DELETE FROM partits WHERE partit_id = ?"
            );
            ps.setInt(1, p.getPartit_id());
            ps.executeUpdate();

        } catch (Exception ex) {
            Vista.missatgeError();
        }

        return false;
    }

    @Override
    public boolean update(Partit p) {
        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement(
                    "UPDATE partits" +
                            "SET partit_id = ?, equip_id = ?, data_partit = ?, matx = ?, resultat = ?" +
                        "WHERE partit_id = ?"
            );
            ps.setInt(1, p.getPartit_id());
            ps.setInt(2, p.getEquip_id());
            ps.setDate(3, p.getData_partit());
            ps.setString(4, p.getMatx());
            ps.setInt(5, p.getResultat());
            ps.setInt(6, p.getPartit_id());
            ps.executeUpdate();
        } catch (Exception ex) {
            ex.getStackTrace();
            Vista.missatgeError();
        }
        return false;
    }

    @Override
    public boolean exists(Partit p) {
        return true;
    }

    @Override
    public boolean create(Partit p) {
        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement(
                    "INSERT INTO partits (partit_id, equip_id, data_partit, matx, resultat)" +
                            "VALUES (?, ?, ?, ?, ?)"
            );
            ps.setInt(1, p.getPartit_id());
            ps.setInt(2, p.getEquip_id());
            ps.setDate(3, p.getData_partit());
            ps.setString(4, p.getMatx());
            ps.setInt(5, p.getResultat());
            ps.executeUpdate();


        } catch (Exception e) {
            e.printStackTrace();
            Vista.missatgeError();
        }
        return false;
    }

    @Override
    public boolean read(Partit partit) {
        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement(
                    "SELECT * FROM partits" +
                            "WHERE partit_id = ?"
            );
            ps.setInt(1, partit.getPartit_id());


        } catch (Exception e) {
            Vista.missatgeError();
        }

        return true;
    }
}
