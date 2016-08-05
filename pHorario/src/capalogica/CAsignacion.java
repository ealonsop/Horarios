/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package capalogica;

import capadatos.*;
import capacliente.*;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author eduardo
 */
public class CAsignacion {

    private ArrayList<CEscuela> escuelas;
    private ArrayList<CAula> aulas;
    private ArrayList<CBloque> obloques;
    private ArrayList<CBloque> bloques;
    private ArrayList<CProfesor> profesores;
    private ArrayList<CCurso> cursos;
    private ArrayList<CCiclo> ciclos;
    private ArrayList<CTipoAula> tiposa;

    public static boolean debug, restart;
        int maxIter, maxNIter;

    static int cc = 0;

    //probabilidades
    public static double _p[];
    
    public CAsignacion() {
        obloques = new ArrayList<CBloque>();
        bloques = new ArrayList<CBloque>();
        aulas = new ArrayList<CAula>();
        profesores = new ArrayList<CProfesor>();
        cursos = new ArrayList<CCurso>();
        ciclos = new ArrayList<CCiclo>();
        escuelas = new ArrayList<CEscuela>();
        tiposa = new ArrayList<CTipoAula>();
        debug = false;
        //debug = true;
        maxIter = 1000;
        maxNIter = 500;

        _p = new double[9];

        prob0();
        
//        _p[0] = 0.1;      //OK poner al padre en null cdo el hijo no puede tomar su horario
//        _p[1] = 0.01;   // eliminar horario de cursos de un ciclo que tenga cursos sin horario
//        _p[2] = 0.1;    //OK reordena los cursos sin horarios
//        _p[3] = 0.1;     // mover al inicio de ciclo un curso sin horario
//        _p[4] = 0.01;     //OK rotar las secciones
//        _p[5] = 1;        // mover
//        _p[6] = 0.000001;  //OK costos iguales
//        _p[7] = 0.0000;  //poner nulo a cursos de un ciclo sin nulos
//        _p[8] = 0.0000;  //poner nulo a cursos de un profesor con h. null

       //  ORIGINAL
//        _p[0] = 0.2;      // poner al padre en null cdo el hijo no puede tomar su horario
//        _p[1] = 0.001;   // eliminar horario de cursos de un ciclo que tenga cursos sin horario
//        _p[2] = 0.1;    // reordena los cursos sin horarios
//        _p[3] = 0.5;     // mover al inicio de ciclo un curso sin horario
//        _p[4] = 0.5;     // rotar las secciones
//        _p[5] = 1;        // mover
//        _p[6] = 0.00001;  // costos iguales
//        _p[7] = 0.0000;  //poner nulo a cursos de un ciclo sin nulos
//        _p[8] = 0.0000;  //poner nulo a cursos de un profesor con h. null
        
        //CIVIL
//        _p[0] = 0.001; //0.2;      // poner al padre en null cdo el hijo no puede tomar su horario
//        _p[1] = 0.001; //0.001   // eliminar horario de cursos de un ciclo que tenga cursos sin horario
//        _p[2] = 0.2;  //0.1    // reordena los cursos sin horarios
//        _p[3] = 0.01; // 0.5;     // mover al inicio de ciclo un curso sin horario
//        _p[4] = 0.01;//0.001; // 0.5;     // rotar las secciones
//        _p[5] = 1;        // mover
//        _p[6] = 0.00001;  // costos iguales
//        _p[7] = 0.0000;  //poner nulo a cursos de un ciclo sin nulos
//        _p[8] = 0.0000;  //poner nulo a cursos de un profesor con h. null
        
        
        //SISTEMAS
//        _p[0] = 0.001; //0.2;      // poner al padre en null cdo el hijo no puede tomar su horario
//        _p[1] = 0.001; //0.001   // eliminar horario de cursos de un ciclo que tenga cursos sin horario
//        _p[2] = 1;  //0.1    // reordena los cursos sin horarios
//        _p[3] = 0.8; // 0.5;     // mover al inicio de ciclo un curso sin horario
//        _p[4] = 0.0;//0.001; // 0.5;     // rotar las secciones
//        _p[5] = 1;        // mover
//        _p[6] = 0.00001;  // costos iguales        
//        _p[7] = 0.0000;  //poner nulo a cursos de un ciclo sin nulos
//        _p[8] = 0.0000;  //poner nulo a cursos de un profesor con h. null

        restart = true;
        
    }

    public static void prob9() {
        _p[0] = 0.2;      //OK poner al padre en null cdo el hijo no puede tomar su horario
        _p[1] = 0.001;   // eliminar horario de cursos de un ciclo que tenga cursos sin horario
        _p[2] = 0.01;    //OK reordena los cursos sin horarios
        _p[3] = 0.01;    // mover al inicio de ciclo un curso sin horario
        _p[4] = 0.00;     //OK rotar las secciones
        _p[5] = 1;        // mover
        _p[6] = 0.0001;  //OK costos iguales
        _p[7] = 0.0001;  //poner nulo a cursos de un ciclo sin nulos
        _p[8] = 0.0001;  //poner nulo a cursos de un profesor con h. null
    }
    
    public static void prob0() {
       //  ORIGINAL
        _p[0] = 0.2;      // poner al padre en null cdo el hijo no puede tomar su horario
        _p[1] = 0.001;   // eliminar horario de cursos de un ciclo que tenga cursos sin horario
        _p[2] = 0.1;    // reordena los cursos sin horarios
        _p[3] = 0.5;     // mover al inicio de ciclo un curso sin horario
        _p[4] = 0.5;     // rotar las secciones
        _p[5] = 1;        // mover
        _p[6] = 0.00001;  // costos iguales
        _p[7] = 0.01;  //poner nulo a cursos de un ciclo sin nulos
        _p[8] = 0.01;  //poner nulo a cursos de un profesor con h. null
    }

    public static void prob1() {
        //SISTEMAS
        _p[0] = 0.001; //0.2;      // poner al padre en null cdo el hijo no puede tomar su horario
        _p[1] = 0.001; //0.001   // eliminar horario de cursos de un ciclo que tenga cursos sin horario
        _p[2] = 1;  //0.1    // reordena los cursos sin horarios
        _p[3] = 0.8; // 0.5;     // mover al inicio de ciclo un curso sin horario
        _p[4] = 0.0;//0.001; // 0.5;     // rotar las secciones
        _p[5] = 1;        // mover
        _p[6] = 0.00001;  // costos iguales        
        _p[7] = 0.00001;  //poner nulo a cursos de un ciclo sin nulos
        _p[8] = 0.0001;  //poner nulo a cursos de un profesor con h. null
    }
    
    public static void prob2() {
        //CIVIL
        _p[0] = 0.001; //0.2;      // poner al padre en null cdo el hijo no puede tomar su horario
        _p[1] = 0.001; //0.001   // eliminar horario de cursos de un ciclo que tenga cursos sin horario
        _p[2] = 0.2;  //0.1    // reordena los cursos sin horarios
        _p[3] = 0.01; // 0.5;     // mover al inicio de ciclo un curso sin horario
        _p[4] = 0.01;//0.001; // 0.5;     // rotar las secciones
        _p[5] = 1;        // mover
        _p[6] = 0.00001;  // costos iguales
        _p[7] = 0.00001;  //poner nulo a cursos de un ciclo sin nulos
        _p[8] = 0.0001;  //poner nulo a cursos de un profesor con h. null
    }
    

    public void leerDatos() {
        try {
            datosExcel.leerdatos(this);
        }
        catch ( Exception e) {}
        int i, j, ip, ik;
        CCurso c, cp;
        CSeccion s;

        sortCursos( new CCurso.cmpCursoCiclo() );
        
        //mover los cursos bloqueados al incio de cada ciclo
//        for ( i = 0; i < getNcursos(); i++ ) {
//            c = getCurso(i);
//            if ( c.isBloqueado() ) {
//               ik = -1;
//               j = i;
//               while ( j >= 0 ) {
//                 if ( getCurso(j).getCiclo() == c.getCiclo() )
//                    ik = j;
//                 j--;
//               }
//               if ( ik < 0 ) ik = 0;
//               cursos.remove(i);
//               cursos.add(ik, c);
//            }
//        }

        //mover los padres para posiciones anteriores a sus hijos
        for ( i = 0; i < getNcursos(); i++ ) {
            c = getCurso(i);
            if ( c.getPadrec() >= 0 ) {
                c.setBloqueado(true); //no es necesario..ya lo está
                ip = getCursoIdPos(c.getPadrec());
                cp = getCurso(ip);
                cp.addHijo(c.getId());
//                if ( ip > i ) {
//                    cursos.remove(ip);
//                    cursos.add(i, cp);
//                    i++;
//                }
            }
        }
        
        //mover los padres(iguales) para posiciones anteriores a sus hijos
//        for ( i = 0; i < getNcursos(); i++ ) {
//            c = getCurso(i);
//            for ( j = 0; j < c.getNsecciones(); j++ ) {
//                s = c.getSeccion(j);
//                if ( s.getIgual() >= 0 ) {
//                    c.setBloqueado(true); //no es necesario..ya lo está
//                    ip = getCursoIdPos(s.getIgual());
//                    if ( ip > i ) {
//                        cp = getCurso(ip);
//                        cursos.remove(ip);
//                        cursos.add(i, cp);
//                        i++;
//                    }
//                }
//            }
//        }
        
    }

    public void guardarHorarios() {
        try {
            horarioCsv.guardarCsv(this);
        }
        catch ( Exception e) {}
    }
    
    public void sortList( List<Object> L, int O[] ) {
        int i;
        int j;
        int tr, size;
        Object to;
        
        size = O.length;
        for ( i = size-1; i > 0; i-- )
            for ( j = 0; j < i; j++ ) {
                if ( O[j] > O[j+1] ) {
                    tr = O[j];
                    O[j] = O[j+1];
                    O[j+1] = tr;
                    to = L.get(j);
                    L.set(j, L.get(j+1));
                    L.set(j+1, to);
                }
            }
    }
    
    public void resetHorario(boolean cero) {
        int i;
        for ( i = 0; i < getNcursos(); i++ ) {
            getCurso(i).resetHorario(cero);
        }
        for ( i = 0; i < getNciclos(); i++ ) {
            getCiclo(i).setReseted(false);
        }
        
        bloques.clear();
        for ( i = 0; i < obloques.size(); i++ )
            bloques.add( obloques.get(i).duplicar());
    }
    
    public boolean cumple( CSeccion s, CHorario h ) {
        int i;
        IRestriccion r;
        boolean res1;
        int c1, c2;
        c1 = 0;
        c2 = 0;
        res1 = false;
        for ( i = 0; i < s.getNrestricciones(); i++ ) {
            r = s.getRestriccion(i);
            if ( ! r.cumple(h) ) {
                if ( r.getClass() != CRestriccionDIFsi.class )
                   return false;
                else {
                   c1++;
                   continue;
                }
            }
            else
                if ( r.getClass() == CRestriccionDIFsi.class ) {
                    res1 = true;
                    c1++;
                }
                else
                    c2++;
        }

        if ( c1 == 0 )
            res1 = c2 > 0;

        return res1;
    }

    public boolean cumpleRestriccionCiclo( CSeccion vs ) {
        int i, j;
        CCurso c;
        CSeccion s;
        CCiclo cic;

        cic = getCicloId( vs.getCurso().getCiclo() );
        
        CHorario h;
        h = vs.getHorario();

        for ( i = 0; i < cic.getNcursos(); i++ ) {
            c = cic.getCurso(i);
            for ( j = 0; j < c.getNsecciones(); j++ ) {
                s = c.getSeccion(j);
                if ( s.getHorario() == null )
                    continue;
                if ( s != vs ) {
                    if ( h.hayCruceHoras(s.getHorario()) )
                        return false;
                }
            }
        }
        return true;
    }

    public boolean cumpleRestriccionProfesor( CSeccion vs ) {
        CProfesor p;
        int i;
        for ( i = 0; i < vs.getNprofesores(); i++ ) {
            if ( !cumpleRestriccionProfesor(vs,vs.getProfesor(i)) )
                return false;
        }
        return true;
    }
    
    public boolean cumpleRestriccionProfesor( CSeccion vs, CProfesor p ) {
        int i, j;
        CCurso c;
        CSeccion s;
        IRestriccion r;
        CHorario h;

        if ( p == null )
            return true;

        h = vs.getHorario();

        boolean res1;
        res1 = true;
        if ( p.getNrestricciones() > 0 ) {
            int c1, c2;
            c1 = 0;
            c2 = 0;
            res1 = false;
            for ( i = 0; i < p.getNrestricciones(); i++ ) {
                r = p.getRestriccion(i);
                if ( ! r.cumple(h) ) {
                    if ( r.getClass() != CRestriccionDIFsi.class )
                       return false;
                    else {
                       c1++;
                       continue;
                    }
                }
                else
                    if ( r.getClass() == CRestriccionDIFsi.class ) {
                        res1 = true;
                        c1++;
                    }
                    else
                        c2++;
            }

            if ( c1 == 0 )
                res1 = c2 > 0;
        }

        if ( !res1 )
            return false;
        
        for ( j = 0; j < p.getNsecciones(); j++ ) {
            s = p.getSeccion(j);
            if ( s.getHorario() == null )
                continue;
            if ( s != vs ) {
                if ( h.hayCruceHoras(s.getHorario()) )
                    return false;
                if ( h.noAlmuerza(s.getHorario()) )
                    return false;
            }
        }
        return true;
    }
    
    public void addAula( CAula a ) {
        aulas.add(a);
    }
    
    public CAula getAula(int p) {
        return aulas.get(p);
    }
    
    public int getNaulas() {
        return aulas.size();
    }

    public void addBloque( CBloque b ) {
        bloques.add(b);
        obloques.add(b.duplicar());
    }

    public void addCurso( CCurso c ) {
        c.setOpos(getNcursos());
        cursos.add(c);
        CCiclo cic;
        int ciclo;
        ciclo = c.getCiclo();
        cic = getCicloId(ciclo);
        if ( cic == null ) {
            cic = new CCiclo(ciclo);
            addCiclo(cic);
        }
        cic.addCurso(c);
    }

    public int getNcursos() {
        return cursos.size();
    }

    public CCurso getCurso(int p) {
        return cursos.get(p);
    }
    
    public List getCursos() {
        return cursos;
    }
    
    public int getNbloques() {
        return bloques.size();
    }


    public CBloque getBloque(int p) {
        return bloques.get(p);
    }
    
    public void addProfesor( CProfesor p ) {
        profesores.add(p);
    }
    
    public int getNprofesores() {
        return profesores.size();
    }
    
    public CProfesor getProfesor(int p) {
        return profesores.get(p);
    }
    
    public CProfesor getProfesorId(int id) {
        int i;
        CProfesor p;
        for ( i = 0; i < getNprofesores(); i++ ) {
            p = getProfesor(i);
            if ( p.getId() == id )
                return p;
        }
        return null;
    }

    public void addTipoA( CTipoAula ta ) {
        tiposa.add(ta);
    }
    
    public int getNtiposA() {
        return tiposa.size();
    }
    
    public CTipoAula getTipoA(int p) {
        return tiposa.get(p);
    }
    
    public CTipoAula getTipoATipo(int tipo) {
        int i;
        CTipoAula p;
        for ( i = 0; i < getNtiposA(); i++ ) {
            p = getTipoA(i);
            if ( p.getTipo() == tipo )
                return p;
        }
        return null;
    }
    
    public void addCiclo(CCiclo c) {
        ciclos.add(c);
    }
    
    public CCiclo getCiclo(int p) {
        return ciclos.get(p);
    }
    
    public int getNciclos() {
        return ciclos.size();
    }
    
    public CCiclo getCicloId(int ciclo) {
        int i;
        CCiclo c;
        for ( i = 0; i < getNciclos(); i++ ) {
            c = getCiclo(i);
            if ( c.getCiclo() == ciclo )
                return c;
        }
        return null;
    }
    
    public void addEscuela( CEscuela E ) {
        escuelas.add(E);
    }
    
    public CEscuela getEscuela(int p) {
        return escuelas.get(p);
    }
    
    public int getNescuelas() {
        return escuelas.size();
    }
    
    public CEscuela getEscuelaId(int id) {
        int i;
        CEscuela c;
        for ( i = 0; i < getNescuelas(); i++ ) {
            c = getEscuela(i);
            if ( c.getId() == id )
                return c;
        }
        return null;
    }

    public CCurso getCursoId(int id) {
        int i;
        CCurso c;
        for ( i = 0; i < getNcursos(); i++ ) {
            c = getCurso(i);
            if ( c.getId() == id )
                return c;
        }
        return null;
    }

    public int getCursoIdPos(int id) {
        int i;
        CCurso c;
        for ( i = 0; i < getNcursos(); i++ ) {
            c = getCurso(i);
            if ( c.getId() == id )
                return i;
        }
        return -1;
    }
    
    public void sortCursos( ICompara cmp ) {
        int i;
        int j;
        int r;
        CCurso tmp;
        
        for ( i = getNcursos()-1; i > 0; i-- )
            for ( j = 0; j < i; j++ ) {
                r = cmp.compara( getCurso(j), getCurso(j+1) );
                if ( r > 0 ) {
                    tmp = cursos.get(j);
                    cursos.set(j, cursos.get(j+1));
                    cursos.set(j+1, tmp);
                }
            }
    }
    
    public void asignarHorarios() {
        int i, j, k, r;
        int igual;
        CCurso c, ci;
        CSeccion s, si;
        CBloque b, bi;
        CHorario h, hi;
        CBloque nb[];
        IRestriccion rd[];
        IRestriccion rh;
        boolean continuar;
        int offset;
        int horas;
        int fallo;
        CBloque b1, b2, b3;
        int d1,d3;
        CRestriccionDIFsi rDIFsecc;

        int costomenor, posmenor, costo, offsetmenor, ridxmenor, ridx;
        
        rd = new IRestriccion[10];
        for ( i = 0; i < 10; i++ )
            rd[i] = new  CRestriccionAulaDia(0,0,0);
        
        rh  = new CRestriccionDIFsi(0,0,0);

        nb = new CBloque[3];
        b1 = nb[0] = new CBloque(0,0,0,null);
        b2 = nb[1] = new CBloque(0,0,0,null);
        b3 = nb[2] = new CBloque(0,0,0,null);

        for ( i = 0; i < getNciclos(); i++ ) 
            getCiclo(i).setNulos(0);
        
        for ( i = 0; i < getNcursos(); i++ ) {
            c = getCurso(i);
            if ( c.getPadrec() != -1 ) {
                for ( j = 0; j < c.getNsecciones(); j++ ) {
                    s = c.getSeccion(j);
                    h = s.getHorario();
                    if ( h != null )
                        if ( !cumpleRestriccionCiclo(s) ) {
                            s.setHorario(null);
                            if ( Math.random() <=  _p[0] ) //0.3 // (0.4)
                                getCursoId(c.getPadrec()).getSeccion(j).setHorario(null);
                            s.setFallo(2);
                            break;
                        }
                    else
                        s.setFallo(0);
                }
                continue;
            }
            for ( j = 0; j < c.getNsecciones(); j++ ) {
                s = c.getSeccion(j);
                h = new CHorario(s);
                s.setHorario(h);
                if ( s.getIgual() < 0 ) {
                    for ( r = 0; r < j; r++)
                        s.addRestriccion(rd[r]);
                }
                else {
                    igual = s.getIgual();
                    ci = getCursoId(igual);
                    si = ci.getSeccion(j);//para evitar el null
                    for ( k = 0; k < ci.getNsecciones(); k++ ) {
                        si = ci.getSeccion(k);
                        if ( si.getOpos() == j )
                            break;
                    }
                    hi = si.getHorario();
                    if ( hi != null ) {
                        bi = hi.getBloque();
                        ((CRestriccionDIFsi)rh).setDia(bi.getDia());
                        ((CRestriccionDIFsi)rh).setHi(bi.getHi());
                        ((CRestriccionDIFsi)rh).setHf(bi.getHf());
                        s.addRestriccion(rh);
                    }
                    else {
                        s.setHorario(null);
                        continue;
                    }
                }
                continuar = true;
                costomenor = 100;
                posmenor = -1;
                offsetmenor = -1;
                ridxmenor = 0;
                fallo = 3;
                for ( k = 0; costomenor > 0 && k < getNbloques(); k++ ) {
                    b = getBloque(k);
                    h.setBloque(b);
                    if ( cumple(s,h) ) {
                        offset = 0;
                        do {
                            ridx = 0;
                            continuar = false;
                            rDIFsecc = h.getSeccion().getRestriccionDIF(b.getDia(), ridx);
                            if ( rDIFsecc == null ) //no debe ocurrir por ya se verifico el cumple!
                                ;
                            
                            do {
                                //nb[]: b1, b2, b3
                                b.divide(h, nb, offset, rDIFsecc);
                                if ( b2.getDia() != -1 ) {
                                    continuar = true;
                                    h.setBloque(b2);
                                    if ( cumple(s,h) ) {
                                       if ( fallo > 0 ) fallo = 2;
                                       if ( cumpleRestriccionCiclo(s) ) {
                                          if ( fallo > 0 ) fallo = 1;
                                          if ( cumpleRestriccionProfesor(s) ) {
                                                fallo = 0;
                                                costo = 0;
                                                d1 = b1.getDia();
                                                d3 = b3.getDia();
                                                if ( d1 == -1 && d3 == -1 ) // exacto
                                                    costo = 0;

                                                if ( d1 != -1 ) {
                                                    costo += 2;
                                                    horas = b1.getHoras();
                                                    if ( horas < 2 )
                                                        costo += 30;
                                                }
                                                if ( d3 != -1 ) {
                                                    costo += 1;
                                                    horas = b3.getHoras();
                                                    if ( horas < 2 )
                                                        costo += 20;
                                                }
                                                if ( costo <= costomenor ) {
                                                    if ( costo < costomenor  || Math.random() <= _p[6] ) {
                                                        costomenor = costo;
                                                        posmenor = k;
                                                        offsetmenor = offset;
                                                        ridxmenor = ridx;
                                                    }
                                                }
                                          }
                                          else
                                              ;
                                       }
                                       else
                                           ;
                                    }
                                    else
                                        ;
                                }
                                ridx = ridx + 1;
                                rDIFsecc = h.getSeccion().getRestriccionDIF(b.getDia(), ridx);

                            }
                            while ( rDIFsecc != null);
                            offset++;
                        }
                        while ( costomenor > 0 && continuar );
                    }
                    else
                        h.setBloque(null);
                }
                if ( posmenor >= 0 ) {
                    k = posmenor;
                    offset = offsetmenor;
                    b = getBloque(k);
                    h.setBloque(b);
                    //nb[]: b1, b2, b3
                    rDIFsecc = h.getSeccion().getRestriccionDIF(b.getDia(), ridxmenor);
                    b.divide(h, nb, offset, rDIFsecc);

                    h.setBloque(b2.duplicar());
                    
                    bloques.remove(k);
                    if ( b3.getDia() != -1 )
                         bloques.add(k, b3.duplicar());
                    if ( b1.getDia() != -1 )
                         bloques.add(k, b1.duplicar());
                    
                    ((CRestriccionAulaDia)rd[j]).setDia(b2.getDia());

                    if ( c.getNsecciones() <= 5 ) {
                        ((CRestriccionAulaDia)rd[j]).setHi(7);
                        ((CRestriccionAulaDia)rd[j]).setHf(22);
                    }
                    else
                        if ( b2.getHf() <= 13 ) {
                           ((CRestriccionAulaDia)rd[j]).setHi(7);
                           ((CRestriccionAulaDia)rd[j]).setHf(13);
                        }
                        else {
                           ((CRestriccionAulaDia)rd[j]).setHi(13);
                           ((CRestriccionAulaDia)rd[j]).setHf(22);
                        }  
                }
                else
                    s.setHorario(null);
                s.setFallo(fallo);
                
                if ( s.getIgual() < 0 ) {
                    for ( r = 0; r < j; r++)
                        s.remUltimaRestriccion();   
                }
                else {
                    s.remUltimaRestriccion();
                    if ( s.getHorario() == null ) {
                        //consola.outln("igual buscado pero no asignado");
                        /*igual = s.getIgual();
                        ci = getCursoId(igual);
                        si = ci.getSeccion(j);
                        si.setHorario(null);*/
                    }
                }
                    
            }

            for ( r = 0; r < c.getNhijos(); r++ ) {
               CCurso ch = getCursoId(c.getHijo(r));
               if ( c.getNsecciones() == ch.getNsecciones() )
                    for ( j = 0; j < c.getNsecciones(); j++ ) {
                        si = c.getSeccion(j);
                        ch.getSeccion(si.getOpos()).setHorario(
                                si.getHorario()
                                 );
                    }
            }
            
        }
    }
    
    public void resetHorarioCiclo( int idciclo ) {
        CCiclo cic;
        cic = getCicloId(idciclo);
        
        cic.incNulos();//no necesariamente va a contarlos todos los null
        
        if ( cic.isReseted() ) return;
        
        cic.setReseted(true);
        for ( int i = 0; i < cic.getNcursos(); i++ ) {
            if ( Math.random() <= _p[1] )
                cic.getCurso(i).resetHorario(false);
        } 
    }
    
    public void resetHorarioProfesor( CProfesor p ) {
        
        for ( int i = 0; i < p.getNsecciones(); i++ ) {
            if ( Math.random() <= _p[8] )
                p.getSeccion(i).setHorario(null);
        } 
    }
    
        static boolean  xx = true;

    public boolean generarHorarios() {
        int nulos[];
        int nnulos, nnulost, i, j, k, ik, iteracion;
        CCurso c, c2;
        CSeccion s;
        String msj;
        int nseccs, niter;
        CCiclo cic;
        
        nulos = new int[1000];
        String fallos[] = { "sin","profesor","ciclo","ambiente" };

        for ( i = 0; i < getNcursos(); i++ ) {
            c = getCurso(i);
            System.out.println(i + " "  + c.getNombre()  + " " + c.getCiclo() 
                    + (c.isBloqueado() ?" * " : " ") );
        }
        
        niter = 0;
        do {

        nnulos = nnulost = 0;
        iteracion = 0;
        do {
            if ( restart ) {
               resetHorario( true );
               //sortCursos( new CCurso.cmpCurso() );
               iteracion = 0;
               niter = 0;
               restart = false;
            }
            iteracion++;
            consola.outln();
            if ( !debug )
               consola.clear();
            consola.out("Iteracion: " +(niter*maxIter+iteracion) + " " + nnulost  + " " + nnulos);
            asignarHorarios();
            nnulost = nnulos = 0;
            nseccs = 0;
            
            for ( i = 0; i < getNcursos(); i++ ) {
                c = getCurso(i);
                for ( j = 0; j < c.getNsecciones(); j++ ) {
                    s = c.getSeccion(j);
                    if ( s.getHorario() == null || s.getHorario().getBloque() == null ) {
                        nnulos++;
                        resetHorarioCiclo(c.getCiclo());
                        if ( s.getFallo() == 1 ) {
                           resetHorarioProfesor(s.getProfesor(0));
                        }
                    }
                    
                }
            }

            if ( nnulos > 0 )
                for ( i = 0; i < getNcursos(); i++ ) {
                    c = getCurso(i);
                    if ( this.getCicloId(c.getCiclo()).getNulos() == 0 )
                        if ( Math.random() <= _p[7] ) {
                           resetHorarioCiclo(c.getCiclo());
//                           System.out.println("hijolete " + c.getCiclo());
                        }
                }
            
            nnulos = 0;
            for ( i = 0; i < getNcursos(); i++ ) {
                c = getCurso(i);
                for ( j = 0; j < c.getNsecciones(); j++ ) {
                    nseccs++;
                    s = c.getSeccion(j);
                    if ( s.getHorario() == null || s.getHorario().getBloque() == null ) {
                        msj = s.toString();
                        if  (debug)
                            consola.outln(msj + " ["+fallos[s.getFallo()]+"]");
                        for ( k = 0; k < nnulos; k++ )
                            if ( nulos[k] == i )
                                break;
                        if ( k == nnulos ) {
                           nnulost++;
                           if ( !c.isBloqueado() ) {
                              nulos[nnulos] = i;
                              nnulos++;
                           }
                        }
                    }
                }
            }
            if ( nnulost > 0 ) {
//                if ( nnulost <= 9 ) {
//                    prob9();
//                    xx = false;
//                }
//                else
//                    if ( nnulost > 15 )
//                        if ( xx )
//                           prob0();
//                        else
//                            prob1();
                    
                
                if  (debug)
                    consola.outln("***** " + nnulost + " " + nnulos);
                int nn;
                nn = nnulos;
                if ( Math.random() <= _p[2] ) // 0.1
                   reordena(nulos,nnulos) ;
               for ( k = 0; k < nn; k++ ) {
                    c = getCurso(nulos[k]);
                    ik =-1;
                    if ( !c.isBloqueado() ) { //no debe ser, ya esta filtrado
                       if ( Math.random() <= _p[3] ) { //0.8
                            i = nulos[k]-1;
                            ik = -1;
                            while ( i >= 0 ) {
                                c2 = getCurso(i);
                                if ( c2.getCiclo() == c.getCiclo() )
                                    if ( !c2.isBloqueado() )
                                       ik = i;
                                    else
                                       break;
                                i--;
                            }
                        }
                        else {
                            if ( c.getNhijos() == 0 && Math.random() <= 0.0000 ) {
                                ik = -1;
                                System.out.println("waooo");
                            }
                            else {
                            ik = -2;
                            if ( Math.random() <= _p[4] ) { // 0.5
                                c.rotate();
                            }
                            }
                        }
                        if ( ik == -1 ) ik = 0;
                        if ( Math.random() <= _p[5] && ik >= 0 ) { //0.95
                            cursos.remove(nulos[k]);
                            cursos.add(ik, c);
                        }
                    }
                  
                }
                
                if ( iteracion < maxIter || niter < maxNIter )
                    resetHorario( false ); //Math.random() <= 0.005 );
            }
        }
        while ( nnulost > 0 && iteracion < maxIter );
        niter++;
        }
        while ( nnulost > 0 && niter < maxNIter );

        if ( nnulost == 0 ) {
            consola.outln();
            consola.outln("Horarios generados completamente");
            consola.settitle("Ok");
            calcularTotalLibre();
        }
        consola.outln(getNcursos() + " cursos, " +  nseccs + " secciones");
        consola.outln();
        return nnulost == 0;
        
    }

    public void calcularTotalLibre() {
        int i;
        CBloque bloque;
        for ( i= 0; i < getNbloques(); i++ ) {
            bloque = getBloque(i);
            bloque.getAula().incLibres( bloque.getHoras() );
        }
    }
    
    public void reordena( int d[], int n) {
        int j, tmp;

        while( n > 0 ){
            j= (int)Math.floor( Math.random() * n );
            n--;
            tmp=d[n];
            d[n]=d[j];
            d[j]=tmp;
        }
    }
    
}
