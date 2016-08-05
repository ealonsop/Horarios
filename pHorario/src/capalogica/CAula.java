/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package capalogica;

/**
 *
 * @author ealonso
 */
public class CAula {
    private int id;
    private int cap; //no usado
    private int tipo;
    private String desc;
    private int disponibles;
    private int libres;

    public CAula(int id, int cap, int tipo, String desc) {
        this.id = id;
        this.cap = cap;
        this.tipo = tipo;
        this.desc = desc;
        this.disponibles = this.libres = 0;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }


    public int getCap() {
        return cap;
    }

    public void setCap(int cap) {
        this.cap = cap;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public int getDisponibles() {
        return disponibles;
    }

    public void setDisponibles(int disponibles) {
        this.disponibles = disponibles;
    }

    public int getLibres() {
        return libres;
    }

    public void setLibre(int libres) {
        this.libres = libres;
    }
 
    public int incDisponibles( int hrs ) {
        return disponibles += hrs;
    }
    
    public int incLibres(int hrs ) {
        return libres += hrs;
    }
    
    public int getUsadas() {
        return getDisponibles() - getLibres();
    }
    
    public String toString() {
       return "aula id, " + id + " ,tipo, " + tipo +" ,desc," +desc;
    }
}
