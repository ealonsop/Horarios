/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package capalogica;

import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author ealonso
 */
public class CCurso {
    private String nombre;
    private int id;
    private int ciclo;
    private List<CSeccion> secciones;
    private int costo;
    private List<Integer> hijos;
    private int padrec;
    private boolean bloqueado;
    private int opos;

    public CCurso(String nombre, int id, int ciclo) {
        this.nombre = nombre;
        this.id = id;
        this.ciclo = ciclo;
        this.secciones = new LinkedList<CSeccion>();
        this.costo = 0;
        this.hijos = new LinkedList<Integer>();
        this.padrec = -1;
        this.bloqueado = false;
        this.opos = -1;
    }

    public void resetHorario(boolean cero) {
        for (int i = 0; i < getNsecciones(); i++ )
            getSeccion(i).setHorario(null);
        
        if ( cero ) {
           int size, i, j;
           CSeccion to;
        
           size = getNsecciones();
           for ( i = size-1; i > 0; i-- )
               for ( j = 0; j < i; j++ ) {
                   if ( getSeccion(j).getOpos() > getSeccion(j+1).getOpos() ) {
                      to = secciones.get(j);
                      secciones.set(j, secciones.get(j+1));
                      secciones.set(j+1, to);
                   }
               }
        }
    }
    
    public void addSeccion( CSeccion s ) {
        s.setOpos(getNsecciones());
        secciones.add(s);
        CProfesor p;
        int i;
        for ( i = 0; i < s.getNprofesores(); i++ ) {
            p = s.getProfesor(i);
            p.addSeccion(s);
        }
    }

    public void rotate() {
        CSeccion s;
        s = secciones.get(0);
        secciones.remove(0);
        secciones.add(s);
    }
    
    public int getNsecciones() {
        return secciones.size();
    }
    
    public CSeccion getSeccion(int p) {
        return secciones.get(p);
    }

    public List getSecciones() {
        return secciones;
    }
    
    public boolean isBloqueado() {
        return bloqueado;
    }

    public void setBloqueado(boolean bloqueado) {
        this.bloqueado = bloqueado;
    }

    public int getCiclo() {
        return ciclo;
    }

    public int getOpos() {
        return opos;
    }

    public void setOpos(int opos) {
        this.opos = opos;
    }

    public void setCiclo(int ciclo) {
        this.ciclo = ciclo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPadrec() {
        return padrec;
    }

    public void setPadrec(int padrec) {
        this.padrec = padrec;
    }
    
    

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void addHijo(int cicloh) {
        hijos.add(new Integer(cicloh));
    }
    
    public int getHijo(int p) {
        return hijos.get(p).intValue();
    }
    
    public int getNhijos() {
        return hijos.size();
    }
    
    public String toString() {
        return "nombre," +  nombre + ",ciclo,"+ciclo;
    }

    
    public int getCosto() {
        if ( costo > 0 )
            return costo;

        if ( isBloqueado() ) {
            costo = 5000;
            return costo;
        }

        int i;
        int menor, costos;
        costo = 0;
        menor = 1000;
        for ( i = 0; i < getNsecciones(); i++ ) {
            costos = getSeccion(i).getCosto();
            if ( costos < menor )
                menor = costos;
        }
        costo = menor;
        if ( getNhijos() > 0 ) {
            costo = costo/2;
        }
        return costo;
    }

    
    public static class cmpCurso implements ICompara {
        public int compara( Object o1, Object o2 ) {
            CCurso c1, c2;
            c1 = (CCurso)o1;
            c2 = (CCurso)o2;
            return c1.getCosto() - c2.getCosto();
        }
    }

    public static class cmpCursoCiclo implements ICompara {
        public int compara( Object o1, Object o2 ) {
            CCurso c1, c2;
            c1 = (CCurso)o1;
            c2 = (CCurso)o2;
            int r = c1.getCiclo() - c2.getCiclo();
            if ( r != 0 )
                return r;
            int d1, d2;
            d1 = c1.isBloqueado() ? -1 : 0;
            d2 = c2.isBloqueado() ? -1 : 0;
            return d1-d2;
        }
    }
    
    
}
