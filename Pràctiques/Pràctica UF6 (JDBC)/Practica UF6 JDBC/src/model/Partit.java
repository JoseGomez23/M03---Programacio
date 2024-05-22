package model;
import java.sql.Date;

public class Partit {

    int partit_id, equip_id;
    Date data_partit;
    String matx;
    char resultat;


    public Partit(int partit_id, int equip_id, Date data_partit, String matx, char resultat) {
        this.partit_id = partit_id;
        this.equip_id = equip_id;
        this.data_partit = data_partit;
        this.matx = matx;
        this.resultat = resultat;
    }

    public int getPartit_id() {
        return partit_id;
    }

    public int getEquip_id() {
        return equip_id;
    }

    public java.sql.Date getData_partit() {
        return data_partit;
    }

    public String getMatx() {
        return matx;
    }

    public char getResultat() {
        return resultat;
    }

    public void setPartit_id(int partit_id) {
        this.partit_id = partit_id;
    }

    public void setEquip_id(int equip_id) {
        this.equip_id = equip_id;
    }

    public void setData_partit(Date data_partit) {
        this.data_partit = data_partit;
    }

    public void setMatx(String matx) {
        this.matx = matx;
    }

    public void setResultat(char resultat) {
        this.resultat = resultat;
    }
}
