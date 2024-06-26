package model;

public class Historic {

    int jugador_id, partit_id, punts, tirs_anotats, tirs_tirats, tirs_triples_anotats, tirs_triples_tirats;
    int tirs_lliures_anotats, tirs_lliures_tirats, rebots_ofensius, rebots_defensius, assistencies, robades, bloqueigs;
    double minuts_jugats;


    public Historic(int jugador_id, int partit_id, int punts, int tirs_anotats, int tirs_tirats, int tirs_triples_anotats, int tirs_triples_tirats, int tirs_lliures_anotats, int tirs_lliures_tirats, int rebots_ofensius, int rebots_defensius, int assistencies, int robades, int bloqueigs, double minuts_jugats) {
        this.jugador_id = jugador_id;
        this.partit_id = partit_id;
        this.punts = punts;
        this.tirs_anotats = tirs_anotats;
        this.tirs_tirats = tirs_tirats;
        this.tirs_triples_anotats = tirs_triples_anotats;
        this.tirs_triples_tirats = tirs_triples_tirats;
        this.tirs_lliures_anotats = tirs_lliures_anotats;
        this.tirs_lliures_tirats = tirs_lliures_tirats;
        this.rebots_ofensius = rebots_ofensius;
        this.rebots_defensius = rebots_defensius;
        this.assistencies = assistencies;
        this.robades = robades;
        this.bloqueigs = bloqueigs;
        this.minuts_jugats = minuts_jugats;
    }

    public int getJugador_id() {
        return jugador_id;
    }

    public void setJugador_id(int jugador_id) {
        this.jugador_id = jugador_id;
    }

    public int getPartit_id() {
        return partit_id;
    }

    public void setPartit_id(int partit_id) {
        this.partit_id = partit_id;
    }

    public int getPunts() {
        return punts;
    }

    public void setPunts(int punts) {
        this.punts = punts;
    }

    public int getTirs_anotats() {
        return tirs_anotats;
    }

    public void setTirs_anotats(int tirs_anotats) {
        this.tirs_anotats = tirs_anotats;
    }

    public int getTirs_tirats() {
        return tirs_tirats;
    }

    public void setTirs_tirats(int tirs_tirats) {
        this.tirs_tirats = tirs_tirats;
    }

    public int getTirs_triples_anotats() {
        return tirs_triples_anotats;
    }

    public void setTirs_triples_anotats(int tirs_triples_anotats) {
        this.tirs_triples_anotats = tirs_triples_anotats;
    }

    public int getTirs_triples_tirats() {
        return tirs_triples_tirats;
    }

    public void setTirs_triples_tirats(int tirs_triples_tirats) {
        this.tirs_triples_tirats = tirs_triples_tirats;
    }

    public int getTirs_lliures_anotats() {
        return tirs_lliures_anotats;
    }

    public void setTirs_lliures_anotats(int tirs_lliures_anotats) {
        this.tirs_lliures_anotats = tirs_lliures_anotats;
    }

    public int getTirs_lliures_tirats() {
        return tirs_lliures_tirats;
    }

    public void setTirs_lliures_tirats(int tirs_lliures_tirats) {
        this.tirs_lliures_tirats = tirs_lliures_tirats;
    }

    public int getRebots_ofensius() {
        return rebots_ofensius;
    }

    public void setRebots_ofensius(int rebots_ofensius) {
        this.rebots_ofensius = rebots_ofensius;
    }

    public int getRebots_defensius() {
        return rebots_defensius;
    }

    public void setRebots_defensius(int rebots_defensius) {
        this.rebots_defensius = rebots_defensius;
    }

    public int getAssistencies() {
        return assistencies;
    }

    public void setAssistencies(int assistencies) {
        this.assistencies = assistencies;
    }

    public int getRobades() {
        return robades;
    }

    public void setRobades(int robades) {
        this.robades = robades;
    }

    public int getBloqueigs() {
        return bloqueigs;
    }

    public void setBloqueigs(int bloqueigs) {
        this.bloqueigs = bloqueigs;
    }

    public double getMinuts_jugats() {
        return minuts_jugats;
    }

    public void setMinuts_jugats(double minuts_jugats) {
        this.minuts_jugats = minuts_jugats;
    }
}


