/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package capalogica;

/**
 *
 * @author ealonso
 */
public class CTipoAula {
    private int tipo;
    private int disponibles;
    private int necesarias;
    
    public CTipoAula( int tipo ) {
        this.tipo = tipo;
        disponibles = necesarias = 0;
    }

    public int getDisponibles() {
        return disponibles;
    }

    public void setDisponibles(int horasDisponibles) {
        this.disponibles = horasDisponibles;
    }

    public int getNecesarias() {
        return necesarias;
    }

    public void setNecesarias(int horasNecesarias) {
        this.necesarias = horasNecesarias;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }
    
    public int incDisponibles( int hrs ) {
        disponibles += hrs;
        return disponibles;
    }
    
    public int incNecesarias( int hrs ) {
        necesarias += hrs;
        return necesarias;
    }
    
    public int getLibres() {
        return getDisponibles() - getNecesarias();
    }
    
    public String toString() {
        return "tipo, " + tipo + ", disp, " + getDisponibles() + 
                                 ", nec, " + getNecesarias() + 
                                 ", lib, " + getLibres();
    }
            
}
