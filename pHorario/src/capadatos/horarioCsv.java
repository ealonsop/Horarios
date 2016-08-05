/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package capadatos;

import capalogica.*;

import java.io.FileNotFoundException;
import java.io.PrintStream;

/**
 *
 * @author ealonso
 */
public class horarioCsv {

    public static void guardarCsv(CAsignacion A) throws FileNotFoundException {
        PrintStream csv = new PrintStream("horario.csv");

        CCurso c;
        CSeccion s;
        String msj;
        int i, j;

        for ( i = 0; i < A.getNcursos(); i++ ) {
            c = A.getCurso(i);
            for ( j = 0; j < c.getNsecciones(); j++ ) {
                s = c.getSeccion(j);
                msj = s.toString();
                if ( s.getHorario() == null || s.getHorario().getBloque() == null ) {
//                    System.out.println(msj);
                }
                csv.println(msj);
            }
        }
        csv.close();
    }
    
}
