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
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;

/**
 * Painel para desenho de objetos desenháveis.
 * 
 * @author David Buzatto
 */
public class PainelDesenho extends JPanel {
    
    private Desenhavel estruturaDesenhavel;
    private double zoom;

    public PainelDesenho( Desenhavel estruturaDesenhavel ) {
        super();
        this.estruturaDesenhavel = estruturaDesenhavel;
        zoom = 1;
    }
    
    @Override
    protected void paintComponent( Graphics g ) {
        
        super.paintComponent( g );
        
        BufferedImage bim = new BufferedImage( getWidth(), getHeight(),
                BufferedImage.TYPE_INT_ARGB );
        
        Graphics2D g2d = bim.createGraphics();
        
        g2d.setRenderingHint( RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON );
        
        AffineTransform af = AffineTransform.getTranslateInstance( 0, 0 );
        af.scale( zoom, zoom );
        g2d.setTransform( af );
        
        estruturaDesenhavel.desenhar( g2d );
        g2d.dispose();
        
        g.drawImage( bim, 0, 0, null );
        
    }

    public void setEstruturaDesenhavel( Desenhavel estruturaDesenhavel ) {
        this.estruturaDesenhavel = estruturaDesenhavel;
    }

    public void setZoom( double zoom ) {
        this.zoom = zoom;
    }

    public double getZoom() {
        return zoom;
    }
    
}
