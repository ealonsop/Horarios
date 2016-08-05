/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package capalogica;

/**
 *
 * @author ealonso
 */
public class CRestriccionAulaDia implements IRestriccion {
    private int dia, horai, horaf;

    public CRestriccionAulaDia(int dia, int hi, int hf) {
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
            if ( dia != bdia  )
                return true;
            int bhi = B.getHi();
            int bhf = B.getHf();
            int horas = getHoras();
            if ( bhf <= horai || bhi >= horaf ) {
                return true;
            }
            else
               if ( (bhi+horas <= horai && bhi+horas <= bhf ) ||
                    (bhf-horas >= horaf && bhf-horas >= bhi ) ) {
                    return true;
               }
                else
                    return false;
        }
        else
            return true;
    }
    
    public String toString() {
        return "No,"+dia+" ,hi, "+horai+" ,hf, "+horaf;
    }    
    
}
