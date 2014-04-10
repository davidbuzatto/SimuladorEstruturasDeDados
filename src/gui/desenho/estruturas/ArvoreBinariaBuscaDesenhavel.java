/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gui.desenho.estruturas;

import estruturas.ArvoreBinariaBusca;
import estruturas.algoritmos.arvores.TipoPercursoArvores;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JPanel;
import uteis.UteisDesenho;

/**
 * Tabela de símbolos desenhável.
 * 
 * @author David Buzatto
 */
public class ArvoreBinariaBuscaDesenhavel implements Desenhavel {
    
    private ArvoreBinariaBusca<Integer> abb;
    private JPanel painel;
    private ArvoreBinariaBuscaAnotada<Integer> abbAnt;
    private boolean mostrarAtributosNos;
    private int diametroNos;
    
    private int atual;
    private int maximo;
    
    // lista de itens do percurso executado
    private List<ArvoreBinariaBuscaAnotada<Integer>.NoAnotado<Integer>> listaPercurso;
    
    public ArvoreBinariaBuscaDesenhavel( ArvoreBinariaBusca<Integer> abb ) {
        this.abb = abb;
        diametroNos = 30;
        listaPercurso = new ArrayList<>();
        atual = -1;
        maximo = -1;
    }
    
    public void setPainel( JPanel painel ) {
        this.painel = painel;
    }

    @Override
    public void desenhar( Graphics2D g2d ) {
        
        int centroH = painel.getWidth()/ 2;
        int centroV = painel.getHeight()/ 2;
        int margemSuperior = 40;
        
        //int diametroNos = 40;
        int espV = diametroNos + diametroNos / 3;
        int espH = diametroNos + diametroNos / 3;
        
        Color azul = new Color( 154, 177, 209 );
        
        g2d.setStroke( new BasicStroke( 2 ) );
        g2d.setFont( new Font( "Arial", Font.BOLD, 12 * diametroNos / 30 ) );
        
        FontMetrics fm = g2d.getFontMetrics();
        
        g2d.setColor( Color.WHITE );
        g2d.fillRect( 0, 0, painel.getWidth(), painel.getHeight() );
        g2d.setColor( Color.BLACK );
        g2d.drawRect( 0, 0, painel.getWidth() - 2, painel.getHeight() - 2 );
        
        if ( abbAnt == null || abbAnt.estaVazia() ) {
            
            UteisDesenho.desenharPonteiro( g2d, centroH - 10, margemSuperior - 25, 20, 20, true, "raiz", 
                    UteisDesenho.PosicaoLabel.DIREITA,
                    UteisDesenho.DirecaoSeta.BAIXO, Color.BLACK );
            
        } else {
            
            int rankRaiz = abbAnt.getRaiz().rank + 1;
            
            for ( ArvoreBinariaBuscaAnotada<Integer>.NoAnotado<Integer> no : abbAnt.percorrer( TipoPercursoArvores.EM_ORDEM ) ) {
                
                no.xIni = centroH + ( ( ( no.rank + 1 ) - rankRaiz ) * espH - diametroNos / 2 ) * diametroNos / 30;
                no.yIni = margemSuperior + ( no.nivel + 1 ) * espV - diametroNos / 2;
                no.xFim = no.xIni + diametroNos;
                no.yFim = no.yIni + diametroNos;
                no.xCentro = no.xFim - diametroNos / 2;
                no.yCentro = no.yFim - diametroNos / 2;
                
            }
            
            for ( ArvoreBinariaBuscaAnotada<Integer>.NoAnotado<Integer> no : abbAnt.percorrer( TipoPercursoArvores.EM_ORDEM ) ) {
                
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
            
            for ( ArvoreBinariaBuscaAnotada<Integer>.NoAnotado<Integer> no : abbAnt.percorrer( TipoPercursoArvores.EM_ORDEM ) ) {
                
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
                    g2d.drawString( String.format( "n: %d", no.nivel ), no.xCentro + diametroNos / 2 + 10, no.yCentro );
                    g2d.drawString( String.format( "g: %d", no.grau ), no.xCentro + diametroNos / 2 + 10, no.yCentro + 10 );
                }
                
            }
            
            UteisDesenho.desenharPonteiro( g2d, abbAnt.getRaiz().xCentro - 10, abbAnt.getRaiz().yCentro - 65, 20, 20, false, "raiz", 
                    UteisDesenho.PosicaoLabel.DIREITA,
                    UteisDesenho.DirecaoSeta.BAIXO, Color.BLACK );
            
        }
        
    }

    public void setAbbAnt( ArvoreBinariaBuscaAnotada<Integer> abbAnt ) {
        this.abbAnt = abbAnt;
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

    public void setListaPercurso( List<ArvoreBinariaBuscaAnotada<Integer>.NoAnotado<Integer>> listaPercurso ) {
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

    public List<ArvoreBinariaBuscaAnotada<Integer>.NoAnotado<Integer>> getListaPercurso() {
        return listaPercurso;
    }
    
}
