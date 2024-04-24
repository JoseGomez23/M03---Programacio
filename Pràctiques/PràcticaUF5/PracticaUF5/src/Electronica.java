public class Electronica extends Producte{

    int diesGarantia;

    public Electronica(float preu, String nom, String codiBarres, int diesGarantia) {
        super(preu, nom, codiBarres);
        this.diesGarantia = diesGarantia;
    }

    public float setPreuDiesGarantia( ) {

        preu = (float) (preu + preu*(diesGarantia/365)*0.1);
        return preu;
    }



    @Override
    public float getPreu() {
        return setPreuDiesGarantia();

    }

    @Override
    public String toString() {
        return  "Nom: " + nom + "\n" +
                "Preu: " + getPreu() + "\n" +
                "Dies de garantia: " + diesGarantia + "\n" +
                "Codi de barres: " + codiBarres;
    }

    @Override
    public int compareTo(Object o) {
        return 0;
    }
}
