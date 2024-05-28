package model;

public class Equip {

    int equip_id, guanyades, perdudes;
    String ciutat, nom, divisio, acronim;

    public Equip(int equip_id, int guanyades, int perdudes, String ciutat, String nom, String divisio, String acronim) {
        this.equip_id = equip_id;
        this.guanyades = guanyades;
        this.perdudes = perdudes;
        this.ciutat = ciutat;
        this.nom = nom;
        this.divisio = divisio;
        this.acronim = acronim;
    }

    public int getEquip_id() {
        return equip_id;
    }

    public int getGuanyades() {
        return guanyades;
    }

    public int getPerdudes() {
        return perdudes;
    }

    public String getCiutat() {
        return ciutat;
    }

    public String getNom() {
        return nom;
    }

    public String getDivisio() {
        return divisio;
    }

    public String getAcronim() {
        return acronim;
    }

    public void setEquip_id(int equip_id) {
        this.equip_id = equip_id;
    }

    public void setGuanyades(int guanyades) {
        this.guanyades = guanyades;
    }

    public void setPerdudes(int perdudes) {
        this.perdudes = perdudes;
    }

    public void setCiutat(String ciutat) {
        this.ciutat = ciutat;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setDivisio(String divisio) {
        this.divisio = divisio;
    }

    public void setAcronim(String acronim) {
        this.acronim = acronim;
    }
}
