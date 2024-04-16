public class Electronica extends Producte{

    int diesGarantia;

    public Electronica(float preu, String nom, String codiBarres, int diesGarantia) {
        super(preu, nom, codiBarres);
        this.diesGarantia = diesGarantia;
    }

    public void setDiesGarantia(int diesGarantia) {

        diesGarantia = (int) (preu + preu*(diesGarantia/365)*0.1);
        this.diesGarantia = diesGarantia;
    }
}
