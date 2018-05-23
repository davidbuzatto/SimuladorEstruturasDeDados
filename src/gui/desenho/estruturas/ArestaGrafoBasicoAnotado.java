/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gui.desenho.estruturas;

import java.io.Serializable;

/**
 * Classe para arestas de um grafo não direcionado.
 * 
 * @author David Buzatto
 */
public class ArestaGrafoBasicoAnotado implements Serializable {
    
    // origem e destino em grafos não direcionados não fazem sentido
    public VerticeGrafoBasicoAnotado origem;
    public VerticeGrafoBasicoAnotado destino;

    @Override
    public String toString() {
        return origem.v + " - " + destino.v;
    }
    
}
