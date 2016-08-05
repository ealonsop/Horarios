/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package capalogica;

/**
 *
 * @author ealonso
 */
public class CHorario {
    private  CSeccion seccion;
    private  CBloque bloque;

    public CHorario(CSeccion seccion) {
        this.seccion = seccion;
        this.bloque = null;
    }

    public boolean hayCruceHoras(CHorario vh) {
        CBloque b1 = bloque;
        CBloque b2 = vh.getBloque();
        if ( b1.getDia() != b2.getDia() )
            return false;
        int b1hi = b1.getHi();
        int b1hf = b1.getHf();
        int b2hi = b2.getHi();
        int b2hf = b2.getHf();

        if ( b1hi == b2hi )
            return true;
        if ( b1hi < b2hi && b1hf > b2hi )
            return true;
        if ( b2hi < b1hi && b2hf > b1hi )
            return true;
        return false;

    }

    public boolean noAlmuerza(CHorario vh) {
        CBloque b1 = bloque;
        CBloque b2 = vh.getBloque();
        if ( b1.getDia() != b2.getDia() )
            return false;
        int b1hi = b1.getHi();
        int b1hf = b1.getHf();
        int b2hi = b2.getHi();
        int b2hf = b2.getHf();

        if ( (b1hf == 13 && b2hi == 13) ||
             (b2hf == 13 && b1hi == 13) )
            return true;

//        if ( (b1hf == 14 && b2hi == 14) ||
//             (b2hf == 14 && b1hi == 14) )
//            return true;
        
        return false;

    }
    
    
    public CBloque getBloque() {
        return bloque;
    }

    public void setBloque(CBloque bloque) {
        this.bloque = bloque;
    }

    public CSeccion getSeccion() {
        return seccion;
    }

    public void setSeccion(CSeccion seccion) {
        this.seccion = seccion;
    }

    public String toString() {
        String res;
        res = "";
        for (int i = 0; i < seccion.getNprofesores(); i++ )
          res = res +  "," + seccion.getProfesor(i);
        return bloque + res;
    }
    
    
}
