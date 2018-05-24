/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gui.desenho.estruturas;

import estruturas.Digrafo;
import estruturas.algoritmos.grafos.Caminhos;
import estruturas.algoritmos.digrafos.BuscaLargura;
import estruturas.algoritmos.digrafos.BuscaProfundidade;
import estruturas.algoritmos.digrafos.ComponentesFortes;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.util.Map;
import javax.swing.JPanel;
import uteis.UteisDesenho;

/**
 * Grafo desenhável.
 * 
 * @author David Buzatto
 */
public class DigrafoDesenhavel implements Desenhavel {
    
    private DigrafoAnotado digrafoAnt;
    private JPanel painel;
    private VerticeGrafoAnotado verticeArestaOrigem;
    private Caminhos<Integer> caminho;
    private ComponentesFortes<Integer> cf;
    private int caminhoAte;
    
    public DigrafoDesenhavel( DigrafoAnotado digrafoAnt ) {
        this.digrafoAnt = digrafoAnt;
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
        
        Digrafo<Integer> digrafo = digrafoAnt.getDigrafo();
        VerticeGrafoAnotado origem;
        VerticeGrafoAnotado destino;
        
        // arestas
        if ( caminho == null ) {
            
            g2d.setColor( Color.BLACK );
            
            for ( Integer v : digrafo.getVertices() ) {
                for ( Integer w : digrafo.getAdjacentes( v ) ) {
                    origem = digrafoAnt.getVertices().get( v );
                    destino = digrafoAnt.getVertices().get( w );
                    g2d.drawLine( origem.xCentro, origem.yCentro,
                            destino.xCentro, destino.yCentro );
                    UteisDesenho.desenharFlechaVertice( origem, destino, g2d );
                }
            }
            
        } else {
            
            g2d.setStroke( new BasicStroke( 4 ) );
            g2d.setColor( UteisDesenho.CINZA );
            
            for ( Integer v : digrafo.getVertices() ) {
                for ( Integer w : digrafo.getAdjacentes( v ) ) {
                    origem = digrafoAnt.getVertices().get( v );
                    destino = digrafoAnt.getVertices().get( w );
                    g2d.drawLine( origem.xCentro, origem.yCentro,
                            destino.xCentro, destino.yCentro );
                    UteisDesenho.desenharFlechaVertice( origem, destino, g2d );
                }
            }
            
            g2d.setStroke( new BasicStroke( 6, BasicStroke.CAP_ROUND, BasicStroke.JOIN_MITER ) );
            g2d.setColor( UteisDesenho.VERDE );
            
            Map<Integer, Integer> arestaAte = null;
            
            if ( caminho instanceof BuscaProfundidade ) {
                arestaAte = ((BuscaProfundidade<Integer>) caminho).getArestaAte();
            } else if ( caminho instanceof BuscaLargura ) {
                arestaAte = ((BuscaLargura<Integer>) caminho).getArestaAte();
            }
            
            for ( Map.Entry<Integer, Integer> e : arestaAte.entrySet() ) {
                
                if ( e.getValue() != null ) {
                
                    origem = digrafoAnt.getVertices().get( e.getValue() );
                    destino = digrafoAnt.getVertices().get( e.getKey() );

                    g2d.drawLine( origem.xCentro, origem.yCentro,
                        destino.xCentro, destino.yCentro );

                    UteisDesenho.desenharFlechaVertice( origem, destino, g2d );
                
                }
                
            }
            
            if ( caminhoAte != -1 ) {
                
                boolean primeiro = true;
                
                g2d.setColor( UteisDesenho.AZUL );
                
                Integer o = 0;
                Integer d = 0;
                
                while ( d != null ) {
                    
                    if ( primeiro == true ) {
                        o = caminhoAte;
                        d = arestaAte.get(o);
                        primeiro = false;
                    } else {
                        o = d;
                        d = arestaAte.get(o);
                    }

                    if ( d != null ) {
                        
                        origem = digrafoAnt.getVertices().get( d );
                        destino = digrafoAnt.getVertices().get( o );

                        g2d.drawLine( origem.xCentro, origem.yCentro,
                            destino.xCentro, destino.yCentro );

                        UteisDesenho.desenharFlechaVertice( origem, destino, g2d );                    
                        
                    }
                }
                
            }
            
        }
        
        if ( cf == null ) {
            for ( Integer v : digrafo.getVertices() ) {
                VerticeGrafoAnotado vg = digrafoAnt.getVertices().get( v );
                vg.id = -1;
            }
        } else {
            for ( Integer v : digrafo.getVertices() ) {
                VerticeGrafoAnotado vg = digrafoAnt.getVertices().get( v );
                vg.id = cf.id( vg.v );
            }
        }
        
        // vértices
        for ( Integer v : digrafo.getVertices() ) {
            
            VerticeGrafoAnotado vg = digrafoAnt.getVertices().get( v );
            
            g2d.setStroke( new BasicStroke( 2 ) );
            
            if ( vg.id == -1 ) {
                g2d.setColor( Color.WHITE );
            } else {
                g2d.setColor( UteisDesenho.CORES_CC[vg.id % 30] );
            }
            
            g2d.fillOval( vg.xIni, vg.yIni, vg.tamanho, vg.tamanho );
            
            if ( verticeArestaOrigem != null && verticeArestaOrigem.v == vg.v ) {
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

    public void setCf( ComponentesFortes<Integer> cf ) {
        this.cf = cf;
    }
    
}
