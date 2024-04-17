public class Electronica extends Producte{

    int diesGarantia;

    public Electronica(float preu, String nom, String codiBarres, int diesGarantia) {
        super(preu, nom, codiBarres);
        this.diesGarantia = diesGarantia;
    }

    public float setDiesGarantia(float diesGarantia) {

        diesGarantia = (float)(preu + preu*(diesGarantia/365)*0.1);
        return diesGarantia;
    }



    @Override
    public float getPreu() {
        return setDiesGarantia(diesGarantia);

    }

    @Override
    public String toString() {
        return  "Nom: " + nom + "\n" +
                "Preu: " + getPreu() + "\n" +
                "Dies de garantia: " + diesGarantia + "\n" +
                "Codi de barres: " + codiBarres;
    }
}
