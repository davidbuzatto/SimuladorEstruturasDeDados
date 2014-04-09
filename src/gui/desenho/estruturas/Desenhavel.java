/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gui.desenho.estruturas;

import java.awt.Graphics2D;

/**
 * Interface para objetos desenháveis.
 * 
 * @author David Buzatto
 */
public interface Desenhavel {
    
    public abstract void desenhar( Graphics2D g2d );
    
}
