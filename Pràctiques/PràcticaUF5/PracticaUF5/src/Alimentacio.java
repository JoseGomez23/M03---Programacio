import java.util.Date;

public class Alimentacio extends Producte{

    int preuDataCad;
    Date dataCad;

    public Alimentacio(float preu, String nom, String codiBarres, int preuDataCad, Date dataCad) {
        super(preu, nom, codiBarres);
        this.preuDataCad = preuDataCad;
        this.dataCad = dataCad;
    }

    public void setDataCad() {


        Date dataActual = new Date(System.currentTimeMillis());
        preuDataCad = (int) (preu - preu*(1/(dataCad.getTime()- dataActual.getTime()  +1)) + (preu * 0.1));
        System.out.println(preuDataCad);


    }
}
