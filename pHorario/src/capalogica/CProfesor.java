/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package capalogica;

import java.util.ArrayList;

/**
 *
 * @author ealonso
 */
public class CProfesor {
    private String nombre;
    private int id;
    private ArrayList<IRestriccion> restricciones;
    private int costo;
    private ArrayList<CSeccion> secciones;

    public CProfesor(String nombre, int id) {
        this.nombre = nombre;
        this.id = id;
        this.restricciones = new ArrayList<IRestriccion>();
        this.secciones = new ArrayList<CSeccion>();
        this.costo = 0;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public void addRestriccion( IRestriccion r ) {
        restricciones.add(r);
    }

    public int getNrestricciones() {
        return restricciones.size();
    }

    public IRestriccion getRestriccion(int p) {
        return restricciones.get(p);
    }

    public void addSeccion( CSeccion s ) {
        secciones.add(s);
    }

    public int getNsecciones() {
        return secciones.size();
    }
    
    public CSeccion getSeccion(int p) {
        return secciones.get(p);
    }
    
    public int getCosto() {
        if ( costo > 0 )
            return costo;
        int i;
        int costop;
        int coston;
        int costor;
        costop = coston = 0;
        for ( i = 0; i < getNrestricciones(); i++ ) {
            costor = getRestriccion(i).getCosto();
            if ( costor > 0 )
                costop += costor;
            else
                coston += costor;
        }
        if ( costop > 0 )
            costo = costop;
        else
            costo = 5*(22-7) + (14-7) + coston;
        return costo;
    }
    
    public String toString() {
        return "profesor,"+nombre+", id,"+id;
    }
             
    
}
