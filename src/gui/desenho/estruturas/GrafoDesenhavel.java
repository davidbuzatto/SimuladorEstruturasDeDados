/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gui.desenho.estruturas;

import estruturas.Grafo;
import estruturas.algoritmos.grafos.Caminhos;
import estruturas.algoritmos.grafos.BuscaLargura;
import estruturas.algoritmos.grafos.BuscaProfundidade;
import estruturas.algoritmos.grafos.ComponentesConexos;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.util.Map;
import java.util.Map.Entry;
import javax.swing.JPanel;
import uteis.UteisDesenho;

/**
 * Grafo desenhável.
 * 
 * @author David Buzatto
 */
public class GrafoDesenhavel implements Desenhavel {
    
    private GrafoAnotado grafoAnt;
    private JPanel painel;
    private VerticeGrafoAnotado verticeArestaOrigem;
    private Caminhos<Integer> caminho;
    private ComponentesConexos<Integer> cc;
    private int caminhoAte;
    
    public GrafoDesenhavel( GrafoAnotado grafo ) {
        this.grafoAnt = grafo;
    }
    
    public void setPainel( JPanel painel ) {
        this.painel = painel;
    }

    @Override
    public void desenhar( Graphics2D g2d ) {
        
        g2d.setStroke( new BasicStroke( 2 ) );
        g2d.setFont( new Font( "Arial", Font.BOLD, 16 ) );
        
        FontMetrics fm = g2d.getFontMetrics();
        
        g2d.setColor( Color.WHITE );
        g2d.fillRect( 0, 0, painel.getWidth(), painel.getHeight() );
        g2d.setColor( Color.BLACK );
        g2d.drawRect( 0, 0, painel.getWidth() - 2, painel.getHeight() - 2 );
        
        // arestas
        if ( caminho == null ) {
            
            g2d.setColor( Color.BLACK );
            
            for ( Entry<String, ArestaGrafoAnotado> a : grafoAnt.getArestas().entrySet() ) {

                ArestaGrafoAnotado ag = a.getValue();
                g2d.drawLine( ag.origem.xCentro, ag.origem.yCentro,
                        ag.destino.xCentro, ag.destino.yCentro );

            }
            
        } else {
            
            g2d.setStroke( new BasicStroke( 4 ) );
            g2d.setColor( UteisDesenho.CINZA );
            
            for ( Entry<String, ArestaGrafoAnotado> a : grafoAnt.getArestas().entrySet() ) {

                ArestaGrafoAnotado ag = a.getValue();
                g2d.drawLine( ag.origem.xCentro, ag.origem.yCentro,
                        ag.destino.xCentro, ag.destino.yCentro );

            }
            
            g2d.setStroke( new BasicStroke( 6, BasicStroke.CAP_ROUND, BasicStroke.JOIN_MITER ) );
            g2d.setColor( UteisDesenho.VERDE );
            
            Grafo<Integer> g = grafoAnt.getGrafo();
            Map<Integer, Integer> arestaAte = null;
            
            if ( caminho instanceof BuscaProfundidade ) {
                arestaAte = ((BuscaProfundidade<Integer>) caminho).getArestaAte();
            } else if ( caminho instanceof BuscaLargura ) {
                arestaAte = ((BuscaLargura<Integer>) caminho).getArestaAte();
            }
            
            VerticeGrafoAnotado origem = null;
            VerticeGrafoAnotado destino = null;
                    
            for ( Map.Entry<Integer, Integer> e : arestaAte.entrySet() ) {
                
                if ( e.getKey() != -1 && e.getValue() != null ) {
                    
                    origem = grafoAnt.getVertices().get( grafoAnt.getTransicaoGrafoParaAnotado().get( e.getValue() ) );
                    destino = grafoAnt.getVertices().get( grafoAnt.getTransicaoGrafoParaAnotado().get( e.getKey() ) );
                    
                    g2d.drawLine( origem.xCentro, origem.yCentro,
                        destino.xCentro, destino.yCentro );
                    
                    UteisDesenho.desenharFlechaVertice( origem, destino, g2d );
                    
                }
                
            }
            
            if ( caminhoAte != -1 ) {
                
                boolean primeiro = true;
                
                g2d.setColor( UteisDesenho.AZUL );
                
                int o = 0;
                int d = 0;
                
                while ( d != -1 ) {
                    
                    if ( primeiro == true ) {
                        o = caminhoAte;
                        d = arestaAte.get(o);
                        primeiro = false;
                    } else {
                        o = d;
                        d = arestaAte.get(o);
                    }

                    if ( d != -1 ) {
                        
                        origem = grafoAnt.getVertices().get( grafoAnt.getTransicaoGrafoParaAnotado().get( d ) );
                        destino = grafoAnt.getVertices().get( grafoAnt.getTransicaoGrafoParaAnotado().get( o ) );

                        g2d.drawLine( origem.xCentro, origem.yCentro,
                            destino.xCentro, destino.yCentro );

                        UteisDesenho.desenharFlechaVertice( origem, destino, g2d );                        
                        
                    }
                }
                
            }
            
        }
        
        if ( cc == null ) {
            for ( Entry<Integer, VerticeGrafoAnotado> v : grafoAnt.getVertices().entrySet() ) {
                VerticeGrafoAnotado vg = v.getValue();
                vg.id = -1;
            }
        } else {
            for ( Entry<Integer, VerticeGrafoAnotado> v : grafoAnt.getVertices().entrySet() ) {
                VerticeGrafoAnotado vg = v.getValue();
                vg.id = cc.id( grafoAnt.getTransicaoAnotadoParaGrafo().get( vg.v ) );
            }
        }
        
        // vértices
        for ( Entry<Integer, VerticeGrafoAnotado> v : grafoAnt.getVertices().entrySet() ) {
            
            VerticeGrafoAnotado vg = v.getValue();
            
            g2d.setStroke( new BasicStroke( 2 ) );
            
            if ( vg.id == -1 ) {
                g2d.setColor( Color.WHITE );
            } else {
                g2d.setColor( UteisDesenho.CORES_CC[vg.id % 30] );
            }
            
            g2d.fillOval( vg.xIni, vg.yIni, vg.tamanho, vg.tamanho );
            
            if ( verticeArestaOrigem != null && verticeArestaOrigem.v == v.getValue().v ) {
                g2d.setStroke( new BasicStroke( 4 ) );
                g2d.setColor( UteisDesenho.AZUL );
                g2d.drawOval( vg.xIni, vg.yIni, vg.tamanho, vg.tamanho );
            } else {
                g2d.setColor( Color.BLACK );
                g2d.drawOval( vg.xIni, vg.yIni, vg.tamanho, vg.tamanho );
            }
            
            g2d.setColor( Color.BLACK );
            int largItem = fm.stringWidth( String.valueOf( vg.v ) );
            g2d.drawString( String.valueOf( vg.v ), vg.xCentro - largItem / 2, vg.yCentro + 6 );
            
        }
        
    }

    public void setVerticeArestaOrigem( VerticeGrafoAnotado verticeArestaOrigem ) {
        this.verticeArestaOrigem = verticeArestaOrigem;
    }

    public void setCaminho( Caminhos<Integer> caminho ) {
        this.caminho = caminho;
    }

    public void setCaminhoAte( int caminhoAte ) {
        this.caminhoAte = caminhoAte;
    }

    public void setCc( ComponentesConexos<Integer> cc ) {
        this.cc = cc;
    }
    
}
