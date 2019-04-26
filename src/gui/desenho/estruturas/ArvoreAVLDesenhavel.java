/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gui.desenho.estruturas;

import estruturas.ArvoreAVLCV;
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
 * Árvore AVL desenhável.
 * 
 * @author David Buzatto
 */
public class ArvoreAVLDesenhavel<Tipo extends Comparable<? super Tipo>> implements Desenhavel {
    
    private ArvoreAVLCV<Tipo> aavl;
    private PainelDesenho painel;
    private ArvoreAVLAnotada<Tipo> aavlAnt;
    private boolean mostrarAtributosNos;
    private int diametroNos;
    
    private int atual;
    private int maximo;
    
    private int menorX;
    private int maiorX;
    private int menorY;
    private int maiorY;
    
    // lista de itens do percurso executado
    private List<ArvoreAVLAnotada<Integer>.NoAnotado<Integer>> listaPercurso;
    
    public ArvoreAVLDesenhavel( ArvoreAVLCV<Tipo> abb ) {
        this.aavl = abb;
        diametroNos = 30;
        listaPercurso = new ArrayList<>();
        atual = -1;
        maximo = -1;
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
        
        if ( aavlAnt != null && aavlAnt.getRaiz() != null ) {
            
            menorX = Integer.MAX_VALUE;
            maiorX = Integer.MIN_VALUE;
            menorY = Integer.MAX_VALUE;
            maiorY = Integer.MIN_VALUE;
        
            int rankRaiz = aavlAnt.getRaiz().rank + 1;
            int espV = diametroNos + diametroNos / 3;
            int espH = diametroNos + diametroNos / 3;
            int margemSuperior = 40;

            for ( ArvoreAVLAnotada<Tipo>.NoAnotado<Tipo> no : aavlAnt.percorrer( TipoPercursoArvores.EM_ORDEM ) ) {

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
                
                for ( ArvoreAVLAnotada<Tipo>.NoAnotado<Tipo> no : aavlAnt.percorrer( TipoPercursoArvores.EM_ORDEM ) ) {

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
        
        Color azul = new Color( 154, 177, 209 );
        
        g2d.setStroke( new BasicStroke( 2 ) );
        g2d.setFont( new Font( "Arial", Font.BOLD, 12 * diametroNos / 30 ) );
        
        FontMetrics fm = g2d.getFontMetrics();
        
        g2d.setColor( Color.WHITE );
        g2d.fillRect( 0, 0, painel.getWidth(), painel.getHeight() );
        g2d.setColor( Color.BLACK );
        g2d.drawRect( 0, 0, painel.getWidth() - 2, painel.getHeight() - 2 );
        
        if ( aavlAnt == null || aavlAnt.estaVazia() ) {
            
            UteisDesenho.desenharPonteiro( g2d, 35, margemSuperior - 25, 20, 20, true, "raiz", 
                    UteisDesenho.PosicaoLabel.DIREITA,
                    UteisDesenho.DirecaoSeta.BAIXO, Color.BLACK );
            
        } else {
            
            for ( ArvoreAVLAnotada<Tipo>.NoAnotado<Tipo> no : aavlAnt.percorrer( TipoPercursoArvores.EM_ORDEM ) ) {
                
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
            
            for ( ArvoreAVLAnotada<Tipo>.NoAnotado<Tipo> no : aavlAnt.percorrer( TipoPercursoArvores.EM_ORDEM ) ) {
                
                g2d.setColor( Color.WHITE );
                
                for ( int i = 0; i <= atual; i++ ) {
                    if ( no.valor.equals( listaPercurso.get(i).valor ) ) {
                        g2d.setColor( azul );
                        break;
                    }
                }
                
                g2d.fillOval( no.xIni, no.yIni, diametroNos, diametroNos );
                g2d.setColor( Color.BLACK );
                g2d.drawOval( no.xIni, no.yIni, diametroNos, diametroNos );
                
                int largura = fm.stringWidth( no.valor.toString() );
                
                g2d.drawString( no.valor.toString(), no.xCentro - largura / 2, no.yCentro + 5 );
                
                if ( mostrarAtributosNos ) {
                    g2d.drawString( String.format( "a: %d", no.altura ), no.xCentro + diametroNos / 2 + 10, no.yCentro );
                    g2d.drawString( String.format( "n: %d", no.nivel ), no.xCentro + diametroNos / 2 + 10, no.yCentro + 10 );
                    g2d.drawString( String.format( "g: %d", no.grau ), no.xCentro + diametroNos / 2 + 10, no.yCentro + 20 );
                }
                
            }
            
            UteisDesenho.desenharPonteiro(g2d, aavlAnt.getRaiz().xCentro - 10, aavlAnt.getRaiz().yCentro - 65, 20, 20, false, "raiz", 
                    UteisDesenho.PosicaoLabel.DIREITA,
                    UteisDesenho.DirecaoSeta.BAIXO, Color.BLACK );
            
        }
        
    }

    public void setAbbAnt( ArvoreAVLAnotada<Tipo> aavlAnt ) {
        this.aavlAnt = aavlAnt;
    }

    public void setMostrarAtributosNos( boolean mostrarAtributos ) {
        this.mostrarAtributosNos = mostrarAtributos;
    }

    public void setDiametroNos( int diametroNos ) {
        this.diametroNos = diametroNos;
    }

    public int getDiametroNos() {
        return diametroNos;
    }

    public void setListaPercurso( List<ArvoreAVLAnotada<Integer>.NoAnotado<Integer>> listaPercurso ) {
        this.listaPercurso = listaPercurso;
        maximo = listaPercurso.size();
    }
    
    public void proximo() {
        if ( atual < maximo - 1 ) {
            atual++;
        }
    }
    
    public void reiniciar() {
        atual = -1;
    }
    
    public void anterior() {
        if ( atual >= 0 ) {
            atual--;
        }
    }

    public int getAtual() {
        return atual;
    }

    public List<ArvoreAVLAnotada<Integer>.NoAnotado<Integer>> getListaPercurso() {
        return listaPercurso;
    }
    
}
