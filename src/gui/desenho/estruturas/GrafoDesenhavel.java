/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gui.desenho.estruturas;

import estruturas.Grafo;
import estruturas.algoritmos.grafos.BuscaLargura;
import estruturas.algoritmos.grafos.BuscaProfundidade;
import estruturas.algoritmos.grafos.Caminhos;
import estruturas.algoritmos.grafos.ComponentesConexos;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
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
    private Caminhos caminho;
    private ComponentesConexos cc;
    private int caminhoAte;
    
    private static final Color azul = new Color( 0, 162, 232 );
    private static final Color cinza = new Color( 217, 217, 217 );
    private static final Color cinzaClaro = new Color( 242, 242, 242 );
    private static final Color verde = new Color( 152, 199, 35 );
    private static final Color marcadoDentro = new Color( 155, 208, 213 );
    private static final Color marcadoContorno = new Color( 100, 128, 134 );
    private static final Color vermelho = new Color( 192, 0, 0 );
    private static final Color laranja = new Color( 255, 153, 51 );
    private static final Color laranjaClaro = new Color( 255, 192, 0 );
    private static final Color[] coresCc = new Color[30];
    
    public GrafoDesenhavel( GrafoAnotado grafo ) {
        
        this.grafoAnt = grafo;
        
        for ( int i = 1; i <= 30; i++ ) {
            coresCc[i-1] = Color.getHSBColor( i*8 / (float) 255, 184 / (float) 255, 184 / (float) 255 );
        }
        
        for ( int i = 0; i < coresCc.length; i++ ) {
            Color c = coresCc[i];
            int p = (int) ( Math.random() * 30 );
            coresCc[i] = coresCc[p];
            coresCc[p] = c;
        }
        
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
            g2d.setColor( cinza );
            
            for ( Entry<String, ArestaGrafoAnotado> a : grafoAnt.getArestas().entrySet() ) {

                ArestaGrafoAnotado ag = a.getValue();
                g2d.drawLine( ag.origem.xCentro, ag.origem.yCentro,
                        ag.destino.xCentro, ag.destino.yCentro );

            }
            
            g2d.setStroke( new BasicStroke( 6, BasicStroke.CAP_ROUND, BasicStroke.JOIN_MITER ) );
            g2d.setColor( verde );
            
            Grafo g = grafoAnt.getGrafo();
            int[] arestaAte = null;
            
            if ( caminho instanceof BuscaProfundidade ) {
                arestaAte = ((BuscaProfundidade) caminho).getArestaAte();
            } else if ( caminho instanceof BuscaLargura ) {
                arestaAte = ((BuscaLargura) caminho).getArestaAte();
            }
            
            VerticeGrafoAnotado origem = null;
            VerticeGrafoAnotado destino = null;
                    
            for ( int v = 0; v < arestaAte.length; v++ ) {
                
                if ( arestaAte[v] != -1 ) {
                    
                    origem = grafoAnt.getVertices().get( grafoAnt.getTransicaoGrafoParaAnotado().get( arestaAte[v] ) );
                    destino = grafoAnt.getVertices().get( grafoAnt.getTransicaoGrafoParaAnotado().get( v ) );
                    
                    g2d.drawLine( origem.xCentro, origem.yCentro,
                        destino.xCentro, destino.yCentro );
                    
                    // gera a hipotenusa
                    double h = UteisDesenho.gerarHipotenusa(
                            origem.xCentro, origem.yCentro,
                            destino.xCentro, destino.yCentro );

                    // gera o grau relativo entre os estados
                    double gr = UteisDesenho.obtemGrauRelativoJava(
                            origem.xCentro, origem.yCentro,
                            destino.xCentro, destino.yCentro );

                    // calcula o x e y do início da flecha
                    // sendo que h deve ser dubtraido do raio do vértice que no caso
                    // é 20, pois a flecha deve ser desenhada na borda do vértice
                    double x = ( h - 22 ) * Math.cos( Math.toRadians( gr ) );
                    double y = ( h - 22 ) * Math.sin( Math.toRadians( gr ) );

                    // desenha a flecha
                    // cria um novo Graphics a partir do original
                    Graphics2D g2df = ( Graphics2D ) g2d.create();

                    // faz a translação para a coordenada que deve ser a origem
                    g2df.translate( x + origem.xCentro, y + origem.yCentro );

                    // rotaciona
                    g2df.rotate( Math.toRadians( gr ) );

                    // desenha a flecha
                    g2df.drawLine( 0, 0, -10, -7 );
                    g2df.drawLine( 0, 0, -10, 7 );
                    
                    g2df.dispose();
                    
                }
                
            }
            
            if ( caminhoAte != -1 ) {
                
                boolean primeiro = true;
                
                g2d.setColor( azul );
                
                int o = 0;
                int d = 0;
                
                while ( d != -1 ) {
                    
                    if ( primeiro == true ) {
                        o = caminhoAte;
                        d = arestaAte[o];
                        primeiro = false;
                    } else {
                        o = d;
                        d = arestaAte[o];
                    }

                    if ( d != -1 ) {
                        
                        origem = grafoAnt.getVertices().get( grafoAnt.getTransicaoGrafoParaAnotado().get( d ) );
                        destino = grafoAnt.getVertices().get( grafoAnt.getTransicaoGrafoParaAnotado().get( o ) );

                        g2d.drawLine( origem.xCentro, origem.yCentro,
                            destino.xCentro, destino.yCentro );

                        // gera a hipotenusa
                        double h = UteisDesenho.gerarHipotenusa(
                                origem.xCentro, origem.yCentro,
                                destino.xCentro, destino.yCentro );

                        // gera o grau relativo entre os estados
                        double gr = UteisDesenho.obtemGrauRelativoJava(
                                origem.xCentro, origem.yCentro,
                                destino.xCentro, destino.yCentro );

                        // calcula o x e y do início da flecha
                        // sendo que h deve ser dubtraido do raio do vértice que no caso
                        // é 20, pois a flecha deve ser desenhada na borda do vértice
                        double x = ( h - 22 ) * Math.cos( Math.toRadians( gr ) );
                        double y = ( h - 22 ) * Math.sin( Math.toRadians( gr ) );

                        // desenha a flecha
                        // cria um novo Graphics a partir do original
                        Graphics2D g2df = ( Graphics2D ) g2d.create();

                        // faz a translação para a coordenada que deve ser a origem
                        g2df.translate( x + origem.xCentro, y + origem.yCentro );

                        // rotaciona
                        g2df.rotate( Math.toRadians( gr ) );

                        // desenha a flecha
                        g2df.drawLine( 0, 0, -10, -7 );
                        g2df.drawLine( 0, 0, -10, 7 );

                        g2df.dispose();
                        
                        
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
                g2d.setColor( coresCc[vg.id % 30] );
            }
            
            g2d.fillOval( vg.xIni, vg.yIni, vg.tamanho, vg.tamanho );
            
            if ( verticeArestaOrigem != null && verticeArestaOrigem.v == v.getValue().v ) {
                g2d.setStroke( new BasicStroke( 4 ) );
                g2d.setColor( azul );
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

    public void setCaminho( Caminhos caminho ) {
        this.caminho = caminho;
    }

    public void setCaminhoAte( int caminhoAte ) {
        this.caminhoAte = caminhoAte;
    }

    public void setCc( ComponentesConexos cc ) {
        this.cc = cc;
    }
    
}
