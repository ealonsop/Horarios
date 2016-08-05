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
public class CCiclo {
    private int ciclo;
    private ArrayList<CCurso> cursos;
    private boolean reseted;
    private int nulos;

    public int getNulos() {
        return nulos;
    }

    public void setNulos(int nulos) {
        this.nulos = nulos;
    }

    public int incNulos() {
        return ++this.nulos;
    }
    
    public CCiclo( int ciclo ) {
        this.ciclo = ciclo;
        this.cursos = new ArrayList<CCurso>();
        this.reseted = false;
        this.nulos = 0;
    }

    public boolean isReseted() {
        return reseted;
    }

    public void setReseted(boolean reseted) {
        this.reseted = reseted;
    }
    
    

    public int getCiclo() {
        return ciclo;
    }

    public void setCiclo(int ciclo) {
        this.ciclo = ciclo;
    }
    
    public void addCurso( CCurso c ) {
        cursos.add(c);
    }

    public int getNcursos() {
        return cursos.size();
    }

    public CCurso getCurso(int p) {
        return cursos.get(p);
    }
    
    
}
