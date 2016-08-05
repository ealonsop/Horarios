/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package capalogica;

/**
 *
 * @author ealonso
 */
public class CRestriccionProfDIFno implements IRestriccion {
    private int dia, horai, horaf;

    public CRestriccionProfDIFno(int dia, int hi, int hf) {
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
        return - getHoras();
    }
    
    public boolean cumple(CHorario h) {
        CBloque B = h.getBloque();
        if ( B != null ) {
            int bdia = B.getDia();
            if ( dia != bdia  )
                return true;
            int bhi = B.getHi();
            int bhf = B.getHf();
            if ( bhf <= horai || bhi >= horaf )
                return true;
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
