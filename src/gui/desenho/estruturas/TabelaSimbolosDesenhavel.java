/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gui.desenho.estruturas;

import estruturas.TabelaSimbolosES;
import uteis.UteisDesenho;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import javax.swing.JPanel;

/**
 * Tabela de símbolos desenhável.
 * 
 * @author David Buzatto
 */
public class TabelaSimbolosDesenhavel implements Desenhavel {
    
    private TabelaSimbolosES<String, String> tabelaSimbolos;
    private JPanel painel;
    
    public TabelaSimbolosDesenhavel( TabelaSimbolosES<String, String> tabelaSimbolos ) {
        this.tabelaSimbolos = tabelaSimbolos;
    }
    
    public void setPainel( JPanel painel ) {
        this.painel = painel;
    }

    @Override
    public void desenhar( Graphics2D g2d ) {
        
        int centroH = painel.getWidth()/ 2;
        int centroV = painel.getHeight()/ 2;
        
        int alturaPadrao = 30;
        int alturaTotal = alturaPadrao * tabelaSimbolos.getTamanho();
        
        int larguraPadraoChave = 50;
        int larguraPadraoValor = 50;
        int larguraTotal = larguraPadraoChave + larguraPadraoValor;
        int larguraChaveAtual = 0;
        int larguraValorAtual = 0;
        int margemItem = 5;
        
        int larguraItemChave = 0;
        int larguraItemValor = 0;
        
        int inicioX = 0;
        int inicioY = 0;
        int yAtual = 0;
        
        int resto = 10;
        
        Color azul = new Color( 0, 116, 166 );
        
        g2d.setStroke( new BasicStroke( 2 ) );
        g2d.setFont( new Font( "Arial", Font.BOLD, 12 ) );
        
        FontMetrics fm = g2d.getFontMetrics();
        
        g2d.setColor( Color.WHITE );
        g2d.fillRect( 0, 0, painel.getWidth(), painel.getHeight() );
        g2d.setColor( Color.BLACK );
        g2d.drawRect( 0, 0, painel.getWidth() - 2, painel.getHeight() - 2 );
        
        if ( tabelaSimbolos.estaVazia() ) {
            
            inicioX = centroH - larguraTotal / 2;
            
            g2d.setColor( azul );
            g2d.drawString( "chave", inicioX + margemItem + larguraPadraoChave / 2 - fm.stringWidth( "chave" ) / 2, centroV - 5 );
            g2d.setColor( Color.BLACK );
            g2d.drawString( "valor", inicioX + larguraPadraoChave + 3 * margemItem + larguraPadraoValor / 2 - fm.stringWidth( "valor" ) / 2, centroV - 5 );
            
            g2d.drawLine( inicioX + larguraTotal / 2 + 2 * margemItem, centroV, inicioX + larguraTotal + 4 * margemItem, centroV );
            g2d.drawLine( inicioX + larguraTotal / 2 + 2 * margemItem, centroV, inicioX + larguraTotal / 2 + 2 * margemItem, centroV + resto );
            g2d.drawLine( inicioX + larguraTotal + 4 * margemItem, centroV, inicioX + larguraTotal + 4 * margemItem, centroV + resto );
            
            UteisDesenho.desenharPonteiro( g2d, inicioX + 4 * margemItem + larguraTotal + 30, centroV + 5, 20, 25, true, "primeiro", 
                    UteisDesenho.PosicaoLabel.BAIXO,
                    UteisDesenho.DirecaoSeta.DIREITA, Color.BLACK );
            
        } else {
            
            for ( String chave : tabelaSimbolos.chaves() ) {
                
                String valor = tabelaSimbolos.obter( chave );
                chave = "[" + chave + "]";
                
                larguraChaveAtual = fm.stringWidth( chave );
                larguraValorAtual = fm.stringWidth( valor );
                
                if ( larguraChaveAtual > larguraPadraoChave ) {
                    larguraPadraoChave = larguraChaveAtual;
                }
                
                if ( larguraValorAtual > larguraPadraoValor ) {
                    larguraPadraoValor = larguraValorAtual;
                }
                
            }
            
            larguraTotal = larguraPadraoChave + larguraPadraoValor + margemItem * 4;
            
            inicioX = centroH - larguraTotal / 2;
            inicioY = centroV - alturaTotal / 2;
            yAtual = inicioY;
            
            int i = 0;
            
            for ( String chave : tabelaSimbolos.chaves() ) {
                
                String valor = tabelaSimbolos.obter( chave );
                chave = "[" + chave + "]";
                
                larguraItemChave = fm.stringWidth( chave );
                larguraItemValor = fm.stringWidth( valor );
                
                g2d.drawLine( inicioX + larguraPadraoChave + 2 * margemItem, yAtual, inicioX + larguraTotal, yAtual );
                
                g2d.setColor( azul );
                g2d.drawString( chave, inicioX + larguraPadraoChave / 2 - larguraItemChave / 2 + margemItem, yAtual + alturaPadrao / 2 + 5 );
                g2d.setColor( Color.BLACK );
                g2d.drawString( valor, inicioX + larguraPadraoChave + 3 * margemItem +  larguraPadraoValor / 2 - larguraItemValor / 2, yAtual + alturaPadrao / 2 + 5 );
                
                if ( i < tabelaSimbolos.getTamanho() - 1 ) {
                    UteisDesenho.desenharPonteiroAnterior( g2d, inicioX + larguraTotal, yAtual + alturaPadrao - 5, 15, 10, UteisDesenho.DirecaoSeta.BAIXO, Color.RED );
                }
                i++;
                
                yAtual += alturaPadrao;
                
            }
            
            g2d.setColor( azul );
            g2d.drawString( "chave", inicioX + margemItem + larguraPadraoChave / 2 - fm.stringWidth( "chave" ) / 2, inicioY - 5 );
            g2d.setColor( Color.BLACK );
            g2d.drawString( "valor", inicioX + larguraPadraoChave + 3 * margemItem + larguraPadraoValor / 2 - fm.stringWidth( "valor" ) / 2, inicioY - 5 );
            
            UteisDesenho.desenharValorNull( g2d, inicioX + larguraPadraoChave + 3 * margemItem + larguraPadraoValor / 2, yAtual, 20, UteisDesenho.DirecaoSeta.BAIXO, Color.RED );
            
            g2d.drawLine( inicioX + larguraPadraoChave + 2 * margemItem, inicioY, inicioX + larguraPadraoChave + 2 * margemItem, yAtual );
            g2d.drawLine( inicioX + larguraTotal, inicioY, inicioX + larguraTotal, yAtual );
            g2d.drawLine( inicioX + larguraPadraoChave + 2 * margemItem, yAtual, inicioX + larguraTotal, yAtual );
            
            UteisDesenho.desenharPonteiro( g2d, inicioX + larguraTotal + 45, inicioY + 5, 20, 40, false, "primeiro", 
                    UteisDesenho.PosicaoLabel.BAIXO,
                    UteisDesenho.DirecaoSeta.ESQUERDA, Color.BLACK );
            
        }
        
        
    }
    
}
