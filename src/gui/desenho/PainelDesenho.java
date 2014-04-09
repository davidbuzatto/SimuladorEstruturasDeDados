/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gui.desenho;

import gui.desenho.estruturas.Desenhavel;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.JPanel;

/**
 * Painel para desenho de objetos desenháveis.
 * 
 * @author David Buzatto
 */
public class PainelDesenho extends JPanel {
    
    private Desenhavel estruturaDesenhavel;

    public PainelDesenho( Desenhavel estruturaDesenhavel ) {
        super();
        this.estruturaDesenhavel = estruturaDesenhavel;
    }
    
    @Override
    protected void paintComponent( Graphics g ) {
        
        super.paintComponent( g );
        
        ( (Graphics2D) g ).setRenderingHint( 
                RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON );
        
        Graphics2D g2d = (Graphics2D) g.create();
        estruturaDesenhavel.desenhar( g2d );
        g2d.dispose();
        
    }
    
}
