/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gui.desenho.estruturas;

import estruturas.Lista;
import gui.AreaItemLista;
import uteis.UteisDesenho;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JPanel;

/**
 * Lista desenhável.
 * 
 * @author David Buzatto
 */
public class ListaDesenhavel implements Desenhavel {
    
    private Lista<String> lista;
    private JPanel painel;
    private List<AreaItemLista> areasItens;
    
    public ListaDesenhavel( Lista<String> lista ) {
        this.lista = lista;
        areasItens = new ArrayList<>();
    }
    
    public void setPainel( JPanel painel ) {
        this.painel = painel;
    }

    public List<AreaItemLista> getAreasItens() {
        return areasItens;
    }

    @Override
    public void desenhar( Graphics2D g2d ) {
        
        areasItens.clear();
        
        int margemDireita = painel.getWidth() - 40;
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
        String indice;
        int contIndice;
        Color verde = new Color( 34, 177, 76 );
        Color azul = new Color( 0, 116, 166 );
        
        g2d.setStroke( new BasicStroke( 2 ) );
        g2d.setFont( new Font( "Arial", Font.BOLD, 12 ) );
        
        FontMetrics fm = g2d.getFontMetrics();
        
        g2d.setColor( Color.WHITE );
        g2d.fillRect( 0, 0, painel.getWidth(), painel.getHeight() );
        g2d.setColor( Color.BLACK );
        g2d.drawRect( 0, 0, painel.getWidth() - 2, painel.getHeight() - 2 );
        
        if ( lista.estaVazia() ) {
            
            g2d.drawLine( margemDireita, centroV - altura / 2, margemDireita, centroV + altura / 2 );
            g2d.drawLine( margemDireita, centroV - altura / 2, margemDireita - resto, centroV - altura / 2 );
            g2d.drawLine( margemDireita, centroV + altura / 2, margemDireita - resto, centroV + altura / 2 );
            
            UteisDesenho.desenharPonteiro( g2d, margemDireita - 35, centroV - 80, 20, 20, true, "início", 
                    UteisDesenho.PosicaoLabel.CIMA,
                    UteisDesenho.DirecaoSeta.BAIXO, Color.BLACK );
            
            UteisDesenho.desenharPonteiro( g2d, margemDireita - 35, centroV + 60, 20, 20, true, "fim", 
                    UteisDesenho.PosicaoLabel.BAIXO,
                    UteisDesenho.DirecaoSeta.CIMA, Color.BLACK );
            
        } else {
            
            boolean primeiro = true;
            int cont = 1;
            contIndice = lista.getTamanho() - 1;
            
            for ( String valor : lista ) {
                
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
                
                if ( cont++ != lista.getTamanho() ) {
                    UteisDesenho.desenharPonteiroAnterior( g2d, inicioCaixa + 5, centroV + altura / 2, 15, 10, UteisDesenho.DirecaoSeta.ESQUERDA, Color.RED );
                    UteisDesenho.desenharPonteiroAnterior( g2d, inicioCaixa - 5, centroV - altura / 2, 15, -10, UteisDesenho.DirecaoSeta.DIREITA, verde );
                }
                
                g2d.drawLine( inicioCaixa, centroV - altura / 2, inicioCaixa, centroV + altura / 2 );
                g2d.drawString( valor, inicioCaixa + ( larguraUsadaCaixa / 2 - larguraItemString / 2 ), centroV + 5 );
                
                // retângulos dos itens
                areasItens.add( new AreaItemLista( 
                        new Rectangle( inicioCaixa, centroV - altura / 2, larguraUsadaCaixa, altura ), 
                        lista.getTamanho() - cont + 1 ) );
                
                g2d.setColor( azul );
                indice = "[" + contIndice-- + "]";
                g2d.drawString( indice, inicioCaixa + ( larguraUsadaCaixa / 2 - fm.stringWidth( indice ) / 2 ), centroV + altura / 2 + 15 );
                g2d.setColor( Color.BLACK );
                
                inicioCaixaAnterior = inicioCaixa;
                
            }
            
            g2d.drawLine( margemDireita, centroV - altura / 2, margemDireita, centroV + altura / 2 );
            g2d.drawLine( margemDireita, centroV - altura / 2, inicioCaixa - resto, centroV - altura / 2 );
            g2d.drawLine( margemDireita, centroV + altura / 2, inicioCaixa - resto, centroV + altura / 2 );
            
            UteisDesenho.desenharValorNull( g2d, inicioCaixa, centroV, 20, UteisDesenho.DirecaoSeta.ESQUERDA, Color.RED );
            UteisDesenho.desenharValorNull( g2d, margemDireita, centroV, 20, UteisDesenho.DirecaoSeta.DIREITA, verde );
            
            UteisDesenho.desenharPonteiro( g2d, inicioPrimeiraCaixa + larguraPrimeiraCaixa / 2 - 10, centroV - 80, 20, 40, false, "início", 
                    UteisDesenho.PosicaoLabel.CIMA,
                    UteisDesenho.DirecaoSeta.BAIXO, Color.BLACK );
            
            if ( lista.getTamanho() == 1 ) {
                
                UteisDesenho.desenharPonteiro( g2d, inicioPrimeiraCaixa + larguraPrimeiraCaixa / 2 - 10, centroV + 80, 20, 40, false, "fim", 
                        UteisDesenho.PosicaoLabel.BAIXO,
                        UteisDesenho.DirecaoSeta.CIMA, Color.BLACK );
                
            } else {
                
                UteisDesenho.desenharPonteiro( g2d, inicioCaixa + larguraUsadaCaixa / 2 - 10, centroV + 80, 20, 40, false, "fim", 
                    UteisDesenho.PosicaoLabel.BAIXO,
                    UteisDesenho.DirecaoSeta.CIMA, Color.BLACK );
                
            }
            
        }
        
    }
    
}
