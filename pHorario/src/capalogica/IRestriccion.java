/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package capalogica;

/**
 *
 * @author ealonso
 */
public interface IRestriccion {
    public boolean cumple(CHorario h);
    public int getCosto();
}
