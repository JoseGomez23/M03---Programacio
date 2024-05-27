package model;

import java.util.Date;

public class JugadorHistoric {

    int jugador_id, equip_id, dorsal;
    String nom, cognom, posicio;
    double pes, alcada; //Alçcada;
    Date data_naixement;

    public JugadorHistoric(int jugador_id, int equip_id, int dorsal, String nom, String cognom, String posicio, double pes, double alcada, Date data_naixement) {
        this.jugador_id = jugador_id;
        this.equip_id = equip_id;
        this.dorsal = dorsal;
        this.nom = nom;
        this.cognom = cognom;
        this.posicio = posicio;
        this.pes = pes;
        this.alcada = alcada;
        this.data_naixement = data_naixement;
    }

    public int getJugador_id() {
        return jugador_id;
    }

    public void setJugador_id(int jugador_id) {
        this.jugador_id = jugador_id;
    }

    public int getEquip_id() {
        return equip_id;
    }

    public void setEquip_id(int equip_id) {
        this.equip_id = equip_id;
    }

    public int getDorsal() {
        return dorsal;
    }

    public void setDorsal(int dorsal) {
        this.dorsal = dorsal;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getCognom() {
        return cognom;
    }

    public void setCognom(String cognom) {
        this.cognom = cognom;
    }

    public String getPosicio() {
        return posicio;
    }

    public void setPosicio(String posicio) {
        this.posicio = posicio;
    }

    public double getPes() {
        return pes;
    }

    public void setPes(double pes) {
        this.pes = pes;
    }

    public double getAlcada() {
        return alcada;
    }

    public void setAlcada(double alcada) {
        this.alcada = alcada;
    }

    public java.sql.Date getData_naixement() {
        return (java.sql.Date) data_naixement;
    }

    public void setData_naixement(Date data_naixement) {
        this.data_naixement = data_naixement;
    }
}
