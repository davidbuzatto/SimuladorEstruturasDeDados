/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gui.desenho.estruturas;

import estruturas.HeapMaximo;
import estruturas.algoritmos.arvores.TipoPercursoArvores;
import gui.desenho.PainelDesenho;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JViewport;
import uteis.UteisDesenho;

/**
 * Heap máximo desenhável.
 * 
 * @author David Buzatto
 */
public class HeapMaximoDesenhavel<Tipo extends Comparable<? super Tipo>> implements Desenhavel {
    
    private HeapMaximo<Tipo> hm;
    private PainelDesenho painel;
    private HeapMaximoAnotado<Tipo> hmAnt;
    private int diametroNos;
    
    private int menorX;
    private int maiorX;
    private int menorY;
    private int maiorY;
    
    // lista de itens do percurso executado
    private List<HeapMaximoAnotado<Tipo>.NoAnotado<Tipo>> listaPercurso;
    
    public HeapMaximoDesenhavel( HeapMaximo<Tipo> hm ) {
        this.hm = hm;
        diametroNos = 30;
        listaPercurso = new ArrayList<>();
    }
    
    public void setPainel( PainelDesenho painel ) {
        this.painel = painel;
    }

    public void mudarTamanhoPainel( int larg, int alt ) {
        
        int largP = painel.getParent().getWidth();
        int altP = painel.getParent().getHeight();
        
        if ( larg < largP ) {
            larg = largP;
        }
        
        if ( alt < altP ) {
            alt = altP;
        }
        
        Dimension dim = new Dimension( larg, alt );
        painel.setMinimumSize( dim );
        painel.setMaximumSize( dim );
        painel.setPreferredSize( dim );
        
        ( ( JViewport ) painel.getParent() ).updateUI();
        
    }
    
    private void calcularNovoTamanoPainelEPosicaoNos() {
        
        if ( hmAnt != null && hmAnt.getRaiz() != null ) {
            
            menorX = Integer.MAX_VALUE;
            maiorX = Integer.MIN_VALUE;
            menorY = Integer.MAX_VALUE;
            maiorY = Integer.MIN_VALUE;
        
            int rankRaiz = hmAnt.getRaiz().rank + 1;
            int espV = diametroNos + diametroNos / 3;
            int espH = diametroNos + diametroNos / 3;
            int margemSuperior = 40;

            for ( HeapMaximoAnotado<Tipo>.NoAnotado<Tipo> no : hmAnt.percorrer( TipoPercursoArvores.EM_ORDEM ) ) {

                no.xIni = ( ( ( no.rank + 1 ) - rankRaiz ) * espH - diametroNos / 2 ) * diametroNos / 30;
                no.yIni = margemSuperior + ( no.nivel + 1 ) * espV - diametroNos / 2;
                no.xFim = no.xIni + diametroNos;
                no.yFim = no.yIni + diametroNos;
                no.xCentro = no.xFim - diametroNos / 2;
                no.yCentro = no.yFim - diametroNos / 2;

                if ( menorX > no.xIni ) {
                    menorX = no.xIni;
                }
                if ( maiorX < no.xFim ) {
                    maiorX = no.xFim;
                }

                if ( menorY > no.yIni ) {
                    menorY = no.yIni;
                }
                if ( maiorY < no.yFim ) {
                    maiorY = no.yFim;
                }

            }
            
            maiorY += 30;
            menorX -= 30;
            
            if ( menorX < 0 ) {
                
                maiorX += -menorX;
                maiorX += 30;
                
                for ( HeapMaximoAnotado<Tipo>.NoAnotado<Tipo> no : hmAnt.percorrer( TipoPercursoArvores.EM_ORDEM ) ) {

                    no.xIni += -menorX;
                    no.xFim += -menorX;
                    no.xCentro += -menorX;

                }
                
                menorX = 0;
            
            }
            
            mudarTamanhoPainel( (int) ( maiorX * painel.getZoom() ), (int) ( maiorY * painel.getZoom() ) );
            
        } else {
            
            mudarTamanhoPainel( 0, 0 );
            
        }
        
    }
    
    @Override
    public void desenhar( Graphics2D g2d ) {
        
        calcularNovoTamanoPainelEPosicaoNos();
        
        int margemSuperior = 40;
        
        g2d.setStroke( new BasicStroke( 2 ) );
        g2d.setFont( new Font( "Arial", Font.BOLD, 12 * diametroNos / 30 ) );
        
        FontMetrics fm = g2d.getFontMetrics();
        
        g2d.setColor( Color.WHITE );
        g2d.fillRect( 0, 0, painel.getWidth(), painel.getHeight() );
        g2d.setColor( Color.BLACK );
        g2d.drawRect( 0, 0, painel.getWidth() - 2, painel.getHeight() - 2 );
        
        if ( hmAnt == null || hmAnt.estaVazia() ) {
            
            UteisDesenho.desenharPonteiro( g2d, 35, margemSuperior - 25, 20, 20, true, "raiz", 
                    UteisDesenho.PosicaoLabel.DIREITA,
                    UteisDesenho.DirecaoSeta.BAIXO, Color.BLACK );
            
        } else {
            
            for ( HeapMaximoAnotado<Tipo>.NoAnotado<Tipo> no : hmAnt.percorrer( TipoPercursoArvores.EM_ORDEM ) ) {
                
                if ( no.pai != null ) {
                    g2d.drawLine( no.xCentro, no.yCentro, no.pai.xCentro, no.pai.yCentro );
                }
                
                if ( no.esquerda == null ) {
                    g2d.drawLine( no.xCentro, no.yCentro, no.xCentro - diametroNos + 10, no.yCentro + diametroNos - 10 );
                    g2d.drawLine( no.xCentro - diametroNos + 1, no.yCentro + diametroNos - 10, no.xCentro - diametroNos + 21, no.yCentro + diametroNos - 10 );
                    g2d.drawLine( no.xCentro - diametroNos + 3, no.yCentro + diametroNos - 10 + 5, no.xCentro - diametroNos + 19, no.yCentro + diametroNos - 10 + 5 );
                }
                
                if ( no.direita == null ) {
                    g2d.drawLine( no.xCentro, no.yCentro, no.xCentro + diametroNos - 10, no.yCentro + diametroNos - 10 );
                    g2d.drawLine( no.xCentro + diametroNos - 1, no.yCentro + diametroNos - 10, no.xCentro + diametroNos - 21, no.yCentro + diametroNos - 10 );
                    g2d.drawLine( no.xCentro + diametroNos - 3, no.yCentro + diametroNos - 10 + 5, no.xCentro + diametroNos - 19, no.yCentro + diametroNos - 10 + 5 );
                }
                
            }
            
            for ( HeapMaximoAnotado<Tipo>.NoAnotado<Tipo> no : hmAnt.percorrer( TipoPercursoArvores.EM_ORDEM ) ) {
                
                g2d.setColor( Color.WHITE );
                
                g2d.fillOval( no.xIni, no.yIni, diametroNos, diametroNos );
                g2d.setColor( Color.BLACK );
                g2d.drawOval( no.xIni, no.yIni, diametroNos, diametroNos );
                
                int largura = fm.stringWidth( no.valor.toString() );
                
                g2d.drawString( no.valor.toString(), no.xCentro - largura / 2, no.yCentro + 5 );
                
            }
            
            UteisDesenho.desenharPonteiro(g2d, hmAnt.getRaiz().xCentro - 10, hmAnt.getRaiz().yCentro - 65, 20, 20, false, "raiz", 
                    UteisDesenho.PosicaoLabel.DIREITA,
                    UteisDesenho.DirecaoSeta.BAIXO, Color.BLACK );
            
        }
        
    }

    public void setAbbAnt( HeapMaximoAnotado<Tipo> hmAnt ) {
        this.hmAnt = hmAnt;
    }

    public void setDiametroNos( int diametroNos ) {
        this.diametroNos = diametroNos;
    }

    public int getDiametroNos() {
        return diametroNos;
    }
    
}
