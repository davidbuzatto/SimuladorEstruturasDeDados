/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gui;

import java.awt.Rectangle;

/**
 * Uma área de um item da lista desenhável.
 * 
 * @author David Buzatto
 */
public class AreaItemLista {
    
    private Rectangle area;
    private int indice;

    public AreaItemLista( Rectangle area, int indice ) {
        this.area = area;
        this.indice = indice;
    }

    public Rectangle getArea() {
        return area;
    }

    public int getIndice() {
        return indice;
    }
    
}
