public class Alimentacio extends Producte{

    double dataCad;


    public Alimentacio(float preu, String nom, String codiBarres, double dataCad) {
        super(preu, nom, codiBarres);
        this.dataCad = dataCad;
    }
}
