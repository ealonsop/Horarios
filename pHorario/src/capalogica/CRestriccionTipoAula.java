/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package capalogica;

/**
 *
 * @author ealonso
 */
public class CRestriccionTipoAula implements IRestriccion {
    private int tipo;

    public CRestriccionTipoAula(int tipo) {
        this.tipo = tipo;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public int getCosto() {
        return 0;
    }

    
    public boolean cumple(CHorario h) {
        CBloque B = h.getBloque();
        if ( B != null ) {
            CAula A = B.getAula();
            if ( A.getTipo() == tipo )
                return true;
            else
                return false;
        }
        else
            return true;
    }

    public String toString() {
        return "tipo aula" + tipo;
    }
    
}
