/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package capadatos;

import capalogica.*;

import java.io.*;
import jxl.*;
import jxl.read.biff.BiffException;

/**
 *
 * @author ealonso
 */
public class datosExcel {
    
    
    public static void leerdatos( CAsignacion A ) throws IOException {
        File inputWorkbook = new File("datos.xls");
        Workbook w;
        Sheet sheet;
        try {
          w = Workbook.getWorkbook(inputWorkbook);
          sheet = w.getSheet("Escuelas");
          leerEscuelas( sheet, A );
          sheet = w.getSheet("Aulas");
          leerAulas( sheet, A );
          sheet = w.getSheet("Profesores");
          leerProfesores( sheet, A );
          sheet = w.getSheet("Secciones");
          leerSecciones( sheet, A );
        }
        catch (BiffException e) {
           //e.printStackTrace();
        }        
          
    }

    public static int intCell(Sheet sh, int c, int r) {
        return Integer.parseInt(stringCell(sh,c,r));
    }
    
    public static int hexCell(Sheet sh, int c, int r) {
        return Integer.parseInt(stringCell(sh,c,r));
//        return Integer.parseInt(stringCell(sh,c,r),16);
    }    
    
    public static String stringCell(Sheet sh, int c, int r) {
        String val;
        val = sh.getCell(c, r).getContents();
        return val;
    }

    public static void leerEscuelas( Sheet sh, CAsignacion A ) {
        int i;
        int id;
        String nombre;
        CEscuela escuela;

//        System.out.println("ESCUELAS");
//        System.out.println("Nro de filas: "+sh.getRows());
//        System.out.println("Nro de columnas: "+sh.getColumns());
        for ( i = 1; i < sh.getRows() && sh.getCell(0, i).getType() != CellType.EMPTY; i++ ) {
            id = intCell(sh, 0, i);
            nombre = stringCell(sh,1,i);
            escuela = new CEscuela(id,nombre);
            A.addEscuela(escuela);
        }
    }
    
    
    public static void leerAulas( Sheet sh, CAsignacion A ) {
        int i, j;
        int nro;
        int tipo;
        int cap;
        String desc;
        int dia, hi, hf;
        CAula aula;
        CBloque bloque;
        CTipoAula ta;

//        System.out.println("AULAS");
//        System.out.println("Nro de filas: "+sh.getRows());
//        System.out.println("Nro de columnas: "+sh.getColumns());
        for ( i = 1; i < sh.getRows() && sh.getCell(0, i).getType() != CellType.EMPTY; i++ ) {
            nro = intCell(sh, 0, i);
            tipo = intCell(sh, 1, i);
            cap = intCell(sh, 2, i);
            desc = stringCell(sh,3,i);
            aula = new CAula(nro,cap,tipo,desc);
            A.addAula(aula);
            ta = A.getTipoATipo(tipo);
            if ( ta == null ) {
                ta = new CTipoAula(tipo);
                A.addTipoA(ta);
            }
            hi = -1;
            for ( j = 4; j < sh.getColumns() &&
                    sh.getCell(j, i).getType() != CellType.EMPTY; j+= 3 ) {
                dia = intCell(sh, j, i);
                if ( dia < 0 ) //solo disponibilidad tipo SI
                    dia = -dia;
                hi = intCell(sh, j+1, i);
                hf = intCell(sh, j+2, i);
                bloque = new CBloque(dia,hi,hf,aula);
                A.addBloque(bloque);
                ta.incDisponibles(bloque.getHoras());
                aula.incDisponibles(bloque.getHoras());
            }
            if ( hi == -1 ) {
                for ( dia = 1; dia <= 5; dia++ ) {
                    bloque = new CBloque(dia, 7, 13, aula);
                    A.addBloque(bloque);
                    ta.incDisponibles(bloque.getHoras());
                    aula.incDisponibles(bloque.getHoras());
                    bloque = new CBloque(dia, 13, 19, aula);
                    A.addBloque(bloque);
                    ta.incDisponibles(bloque.getHoras());
                    aula.incDisponibles(bloque.getHoras());
                }                
            }
        }
    }
        

    public static void leerProfesores( Sheet sh, CAsignacion A ) {
        int i, j;
        int nro;
        String nombre;
        int dia, hi, hf;
        CProfesor profesor;
        IRestriccion r;

//        System.out.println("PROFESORES");
//        System.out.println("Nro de filas: "+sh.getRows());
//        System.out.println("Nro de columnas: "+sh.getColumns());
        for ( i = 1; i < sh.getRows() && sh.getCell(0, i).getType() != CellType.EMPTY; i++ ) {
            nro = intCell(sh, 0, i);
            nombre = stringCell(sh,1,i);
            profesor = new CProfesor(nombre,nro);
            hi = -1;
            for ( j = 2; j < sh.getColumns() &&
                    sh.getCell(j, i).getType() != CellType.EMPTY; j+= 3 ) {
                dia = intCell(sh, j, i);
                hi = intCell(sh, j+1, i);
                hf = intCell(sh, j+2, i);
                if ( dia > 0 )
                    r = new CRestriccionDIFsi(dia,hi,hf);
                else
                    r = new CRestriccionProfDIFno(-dia,hi,hf);
                profesor.addRestriccion(r);
            }
            A.addProfesor(profesor);
        }
    }

    public static void leerSecciones( Sheet sh, CAsignacion A ) {
        int i, j;
        int nro, idcurso, ciclo, idprof;
        String nombre;
        int tipoaula, hi, hf, hrs, dia;
        int idescuela;
        int padre, igual;
        CCurso curso;
        CSeccion seccion;
        CProfesor p;
        IRestriccion r;
        CTipoAula ta;

//        System.out.println("SECCIONES");
//        System.out.println("Nro de filas: "+sh.getRows());
//        System.out.println("Nro de columnas: "+sh.getColumns());
        for ( i = 1; i < sh.getRows() && sh.getCell(0, i).getType() != CellType.EMPTY;  ) {
            ciclo = intCell(sh, 3, i);
            idescuela = ciclo / 100000;
            if ( A.getEscuelaId(idescuela) == null ) {
                i++;
                continue;
            }
            nro = intCell(sh, 0, i);
            nombre = stringCell(sh,1,i);
            idcurso = hexCell(sh, 2, i);
//            System.out.println(A.getNcursos()+ ": " + nombre + ": " + idcurso);
            curso = new CCurso(nombre,idcurso,ciclo);
            while ( i < sh.getRows() &&
                    sh.getCell(0, i).getType() != CellType.EMPTY &&
                    hexCell(sh,2,i) == idcurso ) {
                nro = intCell(sh, 0, i);//no importa
                hrs = intCell(sh, 5, i);
                seccion = new CSeccion(curso,hrs);
                seccion.A = A;
                if ( curso.getPadrec() < 0 ) {
                    if ( sh.getCell(4, i).getType() != CellType.EMPTY  ) {
                        padre = hexCell(sh, 4, i);
                        curso.setPadrec(padre);
                        curso.setBloqueado(true);
                    }
                    else {
                        tipoaula = intCell(sh, 6, i);
                        ta = A.getTipoATipo(tipoaula);
                        if ( ta == null ) { //NO DEBE PASAR!!!
                            ta = new CTipoAula(tipoaula);
                            A.addTipoA(ta);
                        }                        
                        ta.incNecesarias(hrs);
                        
                        r = new CRestriccionTipoAula(tipoaula);
                        seccion.addRestriccion(r);
                        

                        for ( j = 8; j <= 12; j++ )
                          if ( sh.getCell(j, i).getType() != CellType.EMPTY) {
                             idprof = intCell(sh,j,i);
                             if ( idprof > 0 ) {
                                 p = A.getProfesorId(idprof);
                                 seccion.addProfesor(p);
                             }
                          }

                        if ( sh.getCell(7, i).getType() != CellType.EMPTY) {
                            igual = intCell(sh,7,i);
                            seccion.setIgual(igual);
                            curso.setBloqueado(true);
                        }
                        else {
                            for ( j = 13; j < sh.getColumns() &&
                                    sh.getCell(j, i).getType() != CellType.EMPTY; j+= 3 ) {
                                dia = intCell(sh, j, i);
                                hi = intCell(sh, j+1, i);
                                hf = intCell(sh, j+2, i);
                                if ( dia > 0 )
                                    r = new CRestriccionDIFsi(dia,hi,hf);
                                else //no permitido!!!, se asume solo DIFsi
                                    r = new CRestriccionDIFsi(-dia,hi,hf);
//                                    r = new CRestriccionProfDIFno(-dia,hi,hf);
                                seccion.addRestriccion(r);
                            }
                        }
                    }
                }
                curso.addSeccion(seccion);
                i++;
            }
            A.addCurso(curso);
        }
    }
    
}
