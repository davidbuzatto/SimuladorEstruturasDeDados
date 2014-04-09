/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gui.desenho.estruturas;

import estruturas.Fila;
import uteis.UteisDesenho;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import javax.swing.JPanel;

/**
 * Fila desenhável.
 * 
 * @author David Buzatto
 */
public class FilaDesenhavel implements Desenhavel {
    
    private Fila<String> fila;
    private JPanel painel;
    
    public FilaDesenhavel( Fila<String> fila ) {
        this.fila = fila;
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
        g2d.setFont( new Font( "Arial", Font.BOLD, 12 ) );
        
        FontMetrics fm = g2d.getFontMetrics();
        
        g2d.setColor( Color.WHITE );
        g2d.fillRect( 0, 0, painel.getWidth(), painel.getHeight() );
        g2d.setColor( Color.BLACK );
        g2d.drawRect( 0, 0, painel.getWidth() - 2, painel.getHeight() - 2 );
        
        if ( fila.estaVazia() ) {
            
            g2d.drawLine( margemDireita, centroV - altura / 2, margemDireita, centroV + altura / 2 );
            g2d.drawLine( margemDireita, centroV - altura / 2, margemDireita - resto, centroV - altura / 2 );
            g2d.drawLine( margemDireita, centroV + altura / 2, margemDireita - resto, centroV + altura / 2 );
            
            UteisDesenho.desenharPonteiro( g2d, margemDireita - 35, centroV - 80, 20, 20, true, "cabeça", 
                    UteisDesenho.PosicaoLabel.CIMA,
                    UteisDesenho.DirecaoSeta.BAIXO, Color.BLACK );
            
            UteisDesenho.desenharPonteiro( g2d, margemDireita - 35, centroV + 60, 20, 20, true, "cauda", 
                    UteisDesenho.PosicaoLabel.BAIXO,
                    UteisDesenho.DirecaoSeta.CIMA, Color.BLACK );
            
        } else {
            
            boolean primeiro = true;
            int cont = 1;
            
            for ( String valor : fila ) {
                
                larguraItemString = fm.stringWidth( valor );
                
                if ( larguraItemString > larguraPadraoCaixa - 10 ) {
                    larguraUsadaCaixa = larguraItemString + 10;
                } else {
                    larguraUsadaCaixa = larguraPadraoCaixa;
                }
                
                if ( primeiro ) {
                    inicioCaixa = margemDireita - larguraUsadaCaixa;
                    inicioPrimeiraCaixa = inicioCaixa;
                    larguraPrimeiraCaixa = larguraUsadaCaixa;
                    primeiro = false;
                } else {
                    inicioCaixa = inicioCaixaAnterior - larguraUsadaCaixa;
                }
                
                if ( cont++ != fila.getTamanho() ) {
                    UteisDesenho.desenharPonteiroAnterior( g2d, inicioCaixa + 5, centroV + altura / 2, 15, 10, UteisDesenho.DirecaoSeta.ESQUERDA, Color.RED );
                }
                
                g2d.drawLine( inicioCaixa, centroV - altura / 2, inicioCaixa, centroV + altura / 2 );
                g2d.drawString( valor, inicioCaixa + ( larguraUsadaCaixa / 2 - larguraItemString / 2 ), centroV + 5 );
                
                inicioCaixaAnterior = inicioCaixa;
                
            }
            
            g2d.drawLine( margemDireita, centroV - altura / 2, margemDireita, centroV + altura / 2 );
            g2d.drawLine( margemDireita, centroV - altura / 2, inicioCaixa - resto, centroV - altura / 2 );
            g2d.drawLine( margemDireita, centroV + altura / 2, inicioCaixa - resto, centroV + altura / 2 );
            
            UteisDesenho.desenharValorNull( g2d, inicioCaixa, centroV, 20, UteisDesenho.DirecaoSeta.ESQUERDA, Color.RED );
            
            UteisDesenho.desenharPonteiro( g2d, inicioPrimeiraCaixa + larguraPrimeiraCaixa / 2 - 10, centroV - 80, 20, 40, false, "cabeça", 
                    UteisDesenho.PosicaoLabel.CIMA,
                    UteisDesenho.DirecaoSeta.BAIXO, Color.BLACK );
            
            if ( fila.getTamanho() == 1 ) {
                
                UteisDesenho.desenharPonteiro( g2d, inicioPrimeiraCaixa + larguraPrimeiraCaixa / 2 - 10, centroV + 60, 20, 40, false, "cauda", 
                        UteisDesenho.PosicaoLabel.BAIXO,
                        UteisDesenho.DirecaoSeta.CIMA, Color.BLACK );
                
            } else {
                
                UteisDesenho.desenharPonteiro( g2d, inicioCaixa + larguraUsadaCaixa / 2 - 10, centroV + 60, 20, 40, false, "cauda", 
                    UteisDesenho.PosicaoLabel.BAIXO,
                    UteisDesenho.DirecaoSeta.CIMA, Color.BLACK );
                
            }
            
        }
        
    }
    
}
