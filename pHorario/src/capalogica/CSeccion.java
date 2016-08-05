/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package capalogica;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author ealonso
 */
public class CSeccion {
    public static CAsignacion A;
    private CCurso curso; 
    private int    horas;
    private List<IRestriccion> restricciones;
    private CHorario horario;
    private List<CProfesor>  profesores;
    private int igual;
    private int fallo;
    private int opos;
    private int costo;
    

    public CSeccion(CCurso curso, int horas) {
        this.curso = curso;
        this.horas = horas;
        this.restricciones = new ArrayList<IRestriccion>();
        this.horario = null;
        this.profesores = new LinkedList<CProfesor>();
        this.fallo = 0;
        this.igual = -1;
        this.opos = -1;
        this.costo = 0;
    }

    public int getFallo() {
        return fallo;
    }

    public void setFallo(int fallo) {
        this.fallo = fallo;
    }

    public int getIgual() {
        return igual;
    }

    public void setIgual(int igual) {
        this.igual = igual;
    }

    public int getOpos() {
        return opos;
    }

    public void setOpos(int opos) {
        this.opos = opos;
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

    public CCurso getCurso() {
        return curso;
    }

    public void setCurso(CCurso curso) {
        this.curso = curso;
    }

    public CHorario getHorario() {
        return horario;
    }

    public void setHorario(CHorario horario) {
        this.horario = horario;
    }

    public int getHoras() {
        return horas;
    }

    public void setHoras(int horas) {
        this.horas = horas;
    }

    public int getNprofesores() {
        return profesores.size();
    }
    
    public CProfesor getProfesor(int i) {
        return profesores.get(i);
    }

    public void addProfesor(CProfesor profesor) {
        this.profesores.add(profesor);
    }

    public CRestriccionDIFsi getRestriccionDIF(int dia, int idx ) {
        CRestriccionDIFsi rd;
        for ( int i = 0; i < getNrestricciones(); i++ ) {
            IRestriccion r = getRestriccion(i);
            if ( r.getClass() == CRestriccionDIFsi.class ) {
                rd = (CRestriccionDIFsi)r;
                if ( rd.getDia() == dia )
                    if ( idx == 0 )
                       return rd;
                    else
                       idx--;
            }
        }
        return null;
    }

    public CRestriccionTipoAula getRestriccionTA() {
        IRestriccion r;
        for ( int i = 0; i < getNrestricciones(); i++ ) {
            r = getRestriccion(i);
            if ( r.getClass() == CRestriccionTipoAula.class ) {
                    return (CRestriccionTipoAula)r;
            }
        }
        return null;
    }
    
    public void remUltimaRestriccion() {
        restricciones.remove(getNrestricciones()-1);
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

        int menor;
        menor = 1000;
        for ( i = 0; i < getNprofesores(); i++ ) {
            costor = getProfesor(i).getCosto();
            if ( costor < menor )
                menor = costor;
        }
        
        CRestriccionTipoAula rta;
        rta = getRestriccionTA();
        if ( rta != null ) { // DEBE SER
            CTipoAula ta = A.getTipoATipo(rta.getTipo());
            if ( ta != null ) { // DEBE SER
                int lib = ta.getLibres();
                if ( lib < menor ) 
                    menor = lib;
            }
        }
        
        if ( menor != 1000 ) {
            if ( menor < costo )
                costo = menor;
        }
        else
            costo += 5*(22-7) + (14-7);

        costo = costo - horas;
        
                
        return costo;
    }

    public String toString() {
        return curso + ", hrs, " + horas + " , " + horario;
    }

}
