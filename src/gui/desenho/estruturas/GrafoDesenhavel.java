/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gui.desenho.estruturas;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.util.Map.Entry;
import javax.swing.JPanel;

/**
 * Grafo desenhável.
 * 
 * @author David Buzatto
 */
public class GrafoDesenhavel implements Desenhavel {
    
    private GrafoAnotado grafoAnt;
    private JPanel painel;
    
    public GrafoDesenhavel( GrafoAnotado grafo ) {
        this.grafoAnt = grafo;
    }
    
    public void setPainel( JPanel painel ) {
        this.painel = painel;
    }

    @Override
    public void desenhar( Graphics2D g2d ) {
        
        int margemDireita = painel.getWidth() - 20;
        int centroV = painel.getHeight()/ 2;
        
        int larguraPadraoCaixa = 50;
        int larguraUsadaCaixa = 0;
        int larguraItemString = 0;
        int inicioCaixa = 0;
        int inicioCaixaAnterior = 0;
        int resto = 10;
        int altura = 30;
        int inicioPrimeiraCaixa = 0;
        int larguraPrimeiraCaixa = 0;
        
        g2d.setStroke( new BasicStroke( 2 ) );
        g2d.setFont( new Font( "Arial", Font.BOLD, 16 ) );
        
        FontMetrics fm = g2d.getFontMetrics();
        
        g2d.setColor( Color.WHITE );
        g2d.fillRect( 0, 0, painel.getWidth(), painel.getHeight() );
        g2d.setColor( Color.BLACK );
        g2d.drawRect( 0, 0, painel.getWidth() - 2, painel.getHeight() - 2 );
        
        for ( Entry<String, ArestaGrafo> a : grafoAnt.getArestas().entrySet() ) {
            
            ArestaGrafo ag = a.getValue();
            
            g2d.setColor( Color.BLACK );
            g2d.drawLine( ag.origem.xCentro, ag.origem.yCentro,
                    ag.destino.xCentro, ag.destino.yCentro );
            
        }
        
        for ( Entry<Integer, VerticeGrafo> v : grafoAnt.getVertices().entrySet() ) {
            
            VerticeGrafo vg = v.getValue();
            
            g2d.setColor( Color.WHITE );
            g2d.fillOval( vg.xIni, vg.yIni, vg.tamanho, vg.tamanho );
            
            g2d.setColor( Color.BLACK );
            g2d.drawOval( vg.xIni, vg.yIni, vg.tamanho, vg.tamanho );
            
            int largItem = fm.stringWidth( String.valueOf( vg.v ) );
            g2d.drawString( String.valueOf( vg.v ), vg.xCentro - largItem / 2, vg.yCentro + 6 );
            
        }
        
    }
    
}
