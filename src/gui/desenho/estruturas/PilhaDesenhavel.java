/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gui.desenho.estruturas;

import estruturas.Pilha;
import uteis.UteisDesenho;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.swing.JPanel;

/**
 * Pilha desenhável.
 * 
 * @author David Buzatto
 */
public class PilhaDesenhavel implements Desenhavel {
    
    private Pilha<String> pilha;
    private JPanel painel;
    
    public PilhaDesenhavel( Pilha<String> pilha ) {
        this.pilha = pilha;
    }
    
    public void setPainel( JPanel painel ) {
        this.painel = painel;
    }

    @Override
    public void desenhar( Graphics2D g2d ) {
        
        int margemAbaixo = painel.getHeight() - 20;
        int centroH = painel.getWidth() / 2;
        
        int largura = 50;
        int resto = 10;
        int posicao = margemAbaixo;
        int passoVertical = 30;
        int larguraItem = 0;
        int maiorLargura = largura - 10;
        
        g2d.setStroke( new BasicStroke( 2 ) );
        g2d.setFont( new Font( "Arial", Font.BOLD, 12 ) );
        
        FontMetrics fm = g2d.getFontMetrics();
        
        g2d.setColor( Color.WHITE );
        g2d.fillRect( 0, 0, painel.getWidth(), painel.getHeight() );
        g2d.setColor( Color.BLACK );
        g2d.drawRect( 0, 0, painel.getWidth() - 2, painel.getHeight() - 2 );
        
        if ( pilha.estaVazia() ) {
            
            g2d.drawLine( centroH - largura / 2, margemAbaixo - passoVertical / 2, centroH + largura / 2, margemAbaixo - passoVertical / 2 );
            g2d.drawLine( centroH - largura / 2, margemAbaixo - passoVertical / 2, centroH - largura / 2, margemAbaixo - passoVertical / 2 - resto );
            g2d.drawLine( centroH + largura / 2, margemAbaixo - passoVertical / 2, centroH + largura / 2, margemAbaixo - passoVertical / 2 - resto );
            
            UteisDesenho.desenharPonteiro( g2d, centroH + largura / 2 + 45, margemAbaixo - 40, 20, 20, true, "topo", 
                    UteisDesenho.PosicaoLabel.BAIXO,
                    UteisDesenho.DirecaoSeta.DIREITA, Color.BLACK );
            
        } else {
            
            List<String> lis = new ArrayList<>();
            
            for ( String valor : pilha ) {
                lis.add( valor );
            }
            
            Collections.reverse( lis );
            
            for ( String valor : lis ) {
                larguraItem = fm.stringWidth( valor );
                if ( maiorLargura < larguraItem ) {
                    maiorLargura = larguraItem;
                }
                g2d.drawString( valor, centroH - larguraItem / 2, posicao - 10 - passoVertical / 2 );
                posicao -= passoVertical;
            }
            
            posicao = margemAbaixo;
            
            for ( int i = 0; i < lis.size() + 1; i++ ) {
                g2d.drawLine( centroH - maiorLargura / 2 - 5, posicao - passoVertical / 2, centroH + maiorLargura / 2 + 5, posicao - passoVertical / 2 );
                if ( i > 1 ) {
                    UteisDesenho.desenharPonteiroAnterior( g2d, centroH + maiorLargura / 2 + 5, posicao + passoVertical / 2 - 5, 15, 10, UteisDesenho.DirecaoSeta.BAIXO, Color.RED );
                }
                posicao -= passoVertical;
            }
            
            UteisDesenho.desenharValorNull( g2d, centroH - maiorLargura / 2 - 5, margemAbaixo - passoVertical, 20, UteisDesenho.DirecaoSeta.ESQUERDA, Color.RED );
            
            g2d.drawLine( centroH - maiorLargura / 2 - 5, margemAbaixo - passoVertical / 2, centroH - maiorLargura / 2 - 5, posicao + passoVertical - resto - passoVertical / 2 );
            g2d.drawLine( centroH + maiorLargura / 2 + 5, margemAbaixo - passoVertical / 2, centroH + maiorLargura / 2 + 5, posicao + passoVertical - resto - passoVertical / 2 );
            
            UteisDesenho.desenharPonteiro( g2d, centroH + maiorLargura / 2 + 50, posicao + passoVertical - 10, 20, 40, false, "topo", 
                    UteisDesenho.PosicaoLabel.BAIXO,
                    UteisDesenho.DirecaoSeta.ESQUERDA, Color.BLACK );
            
        }
        
    }
    
}
