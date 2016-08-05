/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package capalogica;

/**
 *
 * @author ealonso
 */
public class CBloque {
    private  int dia;
    private  int hi, hf;
    private  CAula aula;

    public CBloque(int dia, int hi, int hf, CAula aula) {
        this.dia = dia;
        this.hi = hi;
        this.hf = hf;
        this.aula = aula;
    }


    public CBloque duplicar() {
        return new CBloque(dia,hi,hf,aula);
    }
    
    public void set(int dia, int hi, int hf, CAula aula) {
        this.dia = dia;
        this.hi = hi;
        this.hf = hf;
        this.aula = aula;
    }
    
    public int getHoras() {
        return hf-hi;
    }

    public CAula getAula() {
        return aula;
    }

    public void setAula(CAula aula) {
        this.aula = aula;
    }

    public int getDia() {
        return dia;
    }

    public void setDia(int dia) {
        this.dia = dia;
    }

    public int getHf() {
        return hf;
    }

    public void setHf(int hf) {
        this.hf = hf;
    }

    public int getHi() {
        return hi;
    }

    public void setHi(int hi) {
        this.hi = hi;
    }

    public void divide( CHorario h, CBloque nb [], int offset, CRestriccionDIFsi r ) {
        CBloque b1, b2, b3;

        b1 = nb[0];
        b2 = nb[1];
        b3 = nb[2];
        
        b2.setDia(-1);
        
        int bhi = getHi();
        int bhf = getHf();
        int bnh = getHoras();
        int snh = h.getSeccion().getHoras();
        if ( bnh < snh )
                return;
        int horai = r.getHi() + offset;
        int horaf = r.getHf();
        int mayor;
        mayor = bhi >= horai ? bhi : horai;
        if ( mayor+snh <= bhf &&
             mayor+snh <= horaf ) {
            if ( mayor > bhi ) {
                b1.set(dia,bhi,mayor,aula);
            }
            else
                b1.setDia(-1);
            
            b2.set(dia,mayor,mayor+snh,aula);

            if ( mayor+snh < bhf ) {
                b3.set(dia,mayor+snh,bhf,aula);
            }
            else
                b3.setDia(-1);
        }
    }

    public String toString() {
        return "bloque dia," + dia + " ,hi, " + hi + " ,hf, " + hf + " , " + aula;
    }

    
    
}
