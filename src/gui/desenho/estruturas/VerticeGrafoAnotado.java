/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gui.desenho.estruturas;

import java.io.Serializable;

/**
 * Classe para vértices de grafos.
 * 
 * @author David Buzatto
 */
public class VerticeGrafoAnotado implements Serializable {
    
    private static final long serialVersionUID = 2L;
    
    public int v;
        
    // posicionamento
    public int xIni;
    public int xFim;
    public int xCentro;
    public int yIni;
    public int yFim;
    public int yCentro;
    public int tamanho;
    
    // identificador do grupo
    public int id = -1;
    
}
