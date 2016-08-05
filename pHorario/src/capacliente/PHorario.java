/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package capacliente;

import capalogica.*;


/**
 *
 * @author ealonso
 */
public class PHorario {
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)  {
        // TODO code application logic here

        long tiempo;
        long min, seg, mili;
        boolean res;
        int i, usado;
        CAula aula;
        
        new consola(); 
        
        CAsignacion A;

        A = new CAsignacion();
        
        A.leerDatos();

        tiempo = System.currentTimeMillis();

        res = A.generarHorarios();
        
        tiempo = System.currentTimeMillis() - tiempo;
        min = tiempo / (1000*60);
        seg = tiempo / 1000;
        mili = tiempo % 1000;
        consola.outln();
        consola.outln(min+ " mins " + seg + " segs "  + mili + " milisegs");

        if ( res ) {
            A.guardarHorarios();
            consola.outln();
            for ( i = 0; i < A.getNaulas(); i++ ) {
                aula = A.getAula(i);
                usado = aula.getDisponibles() - aula.getLibres();
                consola.outln(aula + ", Usado, " + 
                        (float)((int)(((float)usado/aula.getDisponibles()*100)*10))/10 + 
                        " %"
                        );
            }
        }

        consola.stopscroll();

        
    }
}
