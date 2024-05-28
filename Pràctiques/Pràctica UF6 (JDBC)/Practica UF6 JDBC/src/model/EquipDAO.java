package model;
import vista.Vista;
import java.sql.PreparedStatement;
import static model.Model.con;

public class EquipDAO implements DAO<Equip>{

    @Override
    public boolean delete(Equip e) {
        PreparedStatement ps = null;
        try {
            if (ps == null) {
                ps = con.prepareStatement(
                        "SELECT * FROM equips WHERE equip_id =  ?");
                ps.setInt(1, 1);
                ps.executeUpdate();
            }


        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return false;
    }

    @Override
    public boolean update(Equip e) {
        PreparedStatement ps = null;
        try {
            if (ps == null) {
                ps = con.prepareStatement(
                        "UPDATE equips " +
                                "SET ciutat = ?, " +
                                "nom = ?, " +
                                "acronim = ?, " +
                                "divisio = ?, " +
                                "guanyades = ?, " +
                                "perdudes = ? " +
                                "WHERE equip_id = ?;");

                ps.setString(1, e.getCiutat());
                ps.setString(2, e.getNom());
                ps.setString(3, e.getAcronim());
                ps.setString(4, e.getDivisio());
                ps.setInt(5, e.getGuanyades());
                ps.setInt(6, e.getPerdudes());
                ps.setInt(7, e.getEquip_id());
                ps.executeUpdate();
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return false;
    }


    @Override
    public boolean exists(Equip e) {
        return true;
    }

    @Override
    public boolean create(Equip e) {
        PreparedStatement ps = null;
        try {
            if (ps == null) {
                ps = Model.con.prepareStatement(
                        "INSERT INTO equips (equip_id, ciutat, nom, acronim, divisio, guanyades, perdudes)" +
                                "VALUES (?,?)");
                ps.setInt(1, e.getEquip_id());
                ps.setString(2, e.getCiutat());
                ps.setString(3, e.getNom());
                ps.setString(4, e.getAcronim());
                ps.setString(5, e.getDivisio());
                ps.setInt(6, e.getGuanyades());
                ps.setInt(7, e.getPerdudes());
                ps.executeUpdate();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return false;
    }


    @Override
    public boolean read(Equip e) {
        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement(
                    "SELECT * FROM equips" +
                            "WHERE equip_id = ?"
            );
            ps.setInt(1, e.getEquip_id());


        } catch (Exception ex) {
            Vista.missatgeError();

        }

        return true;
    }

    public boolean readByNom(Equip e) {
        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement(
                    "SELECT * FROM equips WHERE nom = ?"
            );
            ps.setString(1, e.getNom());

        } catch (Exception ex) {
            Vista.missatgeError();
        }
        return true;
    }
}
