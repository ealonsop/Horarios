/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package capalogica;

/**
 *
 * @author ealonso
 */
public class CRestriccionDIFsi implements IRestriccion {
    private int dia, horai, horaf;

    public CRestriccionDIFsi(int dia, int hi, int hf) {
        this.dia = dia;
        this.horai = hi;
        this.horaf = hf;
    }


    public int getDia() {
        return dia;
    }

    public void setDia(int dia) {
        this.dia = dia;
    }

    public int getHf() {
        return horaf;
    }

    public void setHf(int hf) {
        this.horaf = hf;
    }

    public int getHi() {
        return horai;
    }

    public void setHi(int hi) {
        this.horai = hi;
    }

    public int getHoras() {
        return horaf-horai;
    }

    public int getCosto() {
        return getHoras();
    }


    public boolean cumple(CHorario h) {
        CBloque B = h.getBloque();
        if ( B != null ) {
            int bdia = B.getDia();
            if ( dia == bdia  ) {
                int bhi = B.getHi();
                int bhf = B.getHf();
                int bnh = B.getHoras();
                int snh = h.getSeccion().getHoras();
                if ( bnh < snh )
                    return false;
                int mayor;
                mayor = bhi > horai ? bhi : horai;
                if ( mayor+snh <= bhf &&
                     mayor+snh <= horaf )
                    return true;
                else
                    return false;
            }
            else
                return false;
        }
        else
            return true;
    }

    public String toString() {
        return "Si,"+dia+" ,hi, "+horai+" ,hf, "+horaf;
    }
    
}
