package model;

import java.sql.PreparedStatement;
import java.util.ArrayList;

import static model.JugadorDAO.con;

public class estadistiques_jugadorsDAO implements DAO<estadistiques_jugadors> {


    @Override
    public boolean delete(estadistiques_jugadors e) {
        return false;
    }

    @Override
    public boolean update(estadistiques_jugadors e) {
        return false;
    }


    @Override
    public boolean exists(estadistiques_jugadors e) {
        return false;
    }

    @Override
    public boolean create(estadistiques_jugadors e) {
        PreparedStatement ps = null;
        try {
            if (ps == null) {
                //ToDo: Cambiar el insert de jugadors a estadistiques_jugadors
                ps = con.prepareStatement(
                        "INSERT INTO jugadors (jugador_id, nom, cognom, Data_naixement, pes, alcada, dorsal, posicio, equipId)" +
                                "VALUES (?,?,?,?,?,?,?,?,?)");
                ps.setInt(1, e.getJugador_id());
                //ToDo: Poner los datos necesarios
                ps.executeUpdate();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean read(estadistiques_jugadors estadistiquesJugadors) {
        return false;
    }
}
