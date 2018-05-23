/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package uteis;

import gui.desenho.estruturas.VerticeGrafoBasicoAnotado;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;

/**
 * Métodos utilitários para desenhos diversos.
 * 
 * @author David Buzatto
 */
public class UteisDesenho {
    
    public enum PosicaoLabel {
        CIMA,
        BAIXO,
        ESQUERDA, 
        DIREITA
    };
    
    public enum DirecaoSeta {
        CIMA,
        BAIXO,
        ESQUERDA, 
        DIREITA
    };
    
    /**
     * Desenha a caixa do ponteiro.
     * 
     * TODO: usar translação e rotação do contexto gráfico para simplificar
     * o código.
     */
    public static void desenharPonteiro( Graphics2D g2d, int x, int y, 
            int larguraCaixa, int tamanhoSeta, boolean vazio, String texto,
            PosicaoLabel pl, DirecaoSeta ps, Color cor ) {
        
        g2d = (Graphics2D) g2d.create();
        g2d.setColor( cor );
        
        double circulo = 6.5;
        double centroH = x + larguraCaixa / 2;
        double centroV = y + larguraCaixa / 2;
        g2d.setFont( new Font( "Arial", Font.BOLD, 12 ) );
        
        g2d.drawRect( x, y, larguraCaixa, larguraCaixa );
        g2d.fill( new Ellipse2D.Double( centroH - circulo / 2, centroV - circulo / 2, circulo, circulo ) );
        
        FontMetrics fm = g2d.getFontMetrics();
        int larguraTexto = fm.stringWidth( texto );
        
        switch ( pl ) {
            case CIMA:
                g2d.drawString( texto, (float) ( centroH - larguraTexto / 2 ), (float) ( y - 8 ) );
                break;
            case BAIXO:
                g2d.drawString( texto, (float) ( centroH - larguraTexto / 2 ), (float) ( y + larguraCaixa + 16 ) );
                break;
            case ESQUERDA:
                g2d.drawString( texto, (float) ( centroH - larguraCaixa / 2 - larguraTexto - 6 ), (float) ( y + larguraCaixa / 2 + 4 ) );
                break;
            case DIREITA:
                g2d.drawString( texto, (float) ( centroH + larguraCaixa / 2 + 8 ), (float) ( y + larguraCaixa / 2 + 4 ) );
                break;
        }
        
        switch ( ps ) {
            case CIMA:
                g2d.draw( new Line2D.Double( centroH, centroV, centroH, centroV - larguraCaixa / 2 - tamanhoSeta ) );
                if ( vazio ) {
                    g2d.draw( new Line2D.Double( centroH - 10, centroV - larguraCaixa / 2 - tamanhoSeta, centroH + 10, centroV - larguraCaixa / 2 - tamanhoSeta ) );
                    g2d.draw( new Line2D.Double( centroH - 7, centroV - larguraCaixa / 2 - tamanhoSeta - 5, centroH + 7, centroV - larguraCaixa / 2 - tamanhoSeta - 5 ) );
                } else {
                    g2d.draw( new Line2D.Double( centroH - 5, centroV - larguraCaixa / 2 - tamanhoSeta + 5, centroH, centroV - larguraCaixa / 2 - tamanhoSeta ) );
                    g2d.draw( new Line2D.Double( centroH + 5, centroV - larguraCaixa / 2 - tamanhoSeta + 5, centroH, centroV - larguraCaixa / 2 - tamanhoSeta ) );
                }
                break;
            case BAIXO:
                g2d.draw( new Line2D.Double( centroH, centroV, centroH, centroV + larguraCaixa / 2 + tamanhoSeta ) );
                if ( vazio ) {
                    g2d.draw( new Line2D.Double( centroH - 10, centroV + larguraCaixa / 2 + tamanhoSeta, centroH + 10, centroV + larguraCaixa / 2 + tamanhoSeta ) );
                    g2d.draw( new Line2D.Double( centroH - 7, centroV + larguraCaixa / 2 + tamanhoSeta + 5, centroH + 7, centroV + larguraCaixa / 2 + tamanhoSeta + 5 ) );
                } else {
                    g2d.draw( new Line2D.Double( centroH - 5, centroV + larguraCaixa / 2 + tamanhoSeta - 5, centroH, centroV + larguraCaixa / 2 + tamanhoSeta ) );
                    g2d.draw( new Line2D.Double( centroH + 5, centroV + larguraCaixa / 2 + tamanhoSeta - 5, centroH, centroV + larguraCaixa / 2 + tamanhoSeta ) );
                }
                break;
            case ESQUERDA:
                g2d.draw( new Line2D.Double( centroH, centroV, centroH - larguraCaixa / 2 - tamanhoSeta, centroV ) );
                if ( vazio ) {
                    g2d.draw( new Line2D.Double( centroH - larguraCaixa / 2 - tamanhoSeta, centroV - 10, centroH - larguraCaixa / 2 - tamanhoSeta, centroV + 10 ) );
                    g2d.draw( new Line2D.Double( centroH - larguraCaixa / 2 - tamanhoSeta - 5, centroV - 7, centroH - larguraCaixa / 2 - tamanhoSeta - 5, centroV + 7 ) );
                } else {
                    g2d.draw( new Line2D.Double( centroH - larguraCaixa / 2 - tamanhoSeta + 5, centroV - 5, centroH - larguraCaixa / 2 - tamanhoSeta, centroV ) );
                    g2d.draw( new Line2D.Double( centroH - larguraCaixa / 2 - tamanhoSeta + 5, centroV + 5, centroH - larguraCaixa / 2 - tamanhoSeta, centroV ) );
                }
                break;
            case DIREITA:
                g2d.draw( new Line2D.Double( centroH, centroV, centroH + larguraCaixa / 2 + tamanhoSeta, centroV ) );
                if ( vazio ) {
                    g2d.draw( new Line2D.Double( centroH + larguraCaixa / 2 + tamanhoSeta, centroV - 10, centroH + larguraCaixa / 2 + tamanhoSeta, centroV + 10 ) );
                    g2d.draw( new Line2D.Double( centroH + larguraCaixa / 2 + tamanhoSeta + 5, centroV - 7, centroH + larguraCaixa / 2 + tamanhoSeta + 5, centroV + 7 ) );
                } else {
                    g2d.draw( new Line2D.Double( centroH + larguraCaixa / 2 + tamanhoSeta - 5, centroV - 5, centroH + larguraCaixa / 2 + tamanhoSeta, centroV ) );
                    g2d.draw( new Line2D.Double( centroH + larguraCaixa / 2 + tamanhoSeta - 5, centroV + 5, centroH + larguraCaixa / 2 + tamanhoSeta, centroV ) );
                }
                break;
        }
        
        g2d.dispose();
        
    }
    
    /**
     * Faz o desenho para representar o valor "null".
     * 
     * TODO: usar translação e rotação do contexto gráfico para simplificar
     * o código.
     */
    public static void desenharValorNull( Graphics2D g2d, int x, int y, int tamanhoSeta, DirecaoSeta ps, Color cor ) {
        
        g2d = (Graphics2D) g2d.create();
        g2d.setColor( cor );
        
        switch ( ps ) {
            case CIMA:
                g2d.drawLine( x, y, x, y - tamanhoSeta );
                g2d.drawLine( x - 10, y - tamanhoSeta, x + 10, y - tamanhoSeta );
                g2d.drawLine( x - 7, y - tamanhoSeta - 5, x + 7, y - tamanhoSeta - 5 );
                break;
            case BAIXO:
                g2d.drawLine( x, y, x, y + tamanhoSeta );
                g2d.drawLine( x - 10, y + tamanhoSeta, x + 10, y + tamanhoSeta );
                g2d.drawLine( x - 7, y + tamanhoSeta + 5, x + 7, y + tamanhoSeta + 5 );
                break;
            case ESQUERDA:
                g2d.drawLine( x, y, x - tamanhoSeta, y );
                g2d.drawLine( x - tamanhoSeta, y - 10, x - tamanhoSeta, y + 10 );
                g2d.drawLine( x - tamanhoSeta - 5, y - 7, x - tamanhoSeta - 5, y + 7 );
                break;
            case DIREITA:
                g2d.drawLine( x, y, x + tamanhoSeta, y );
                g2d.drawLine( x + tamanhoSeta, y - 10, x + tamanhoSeta, y + 10 );
                g2d.drawLine( x + tamanhoSeta + 5, y - 7, x + tamanhoSeta + 5, y + 7 );
                break;
        }
        
        g2d.dispose();
        
    }
    
    /**
     * Faz o desenho para representar os ponteiros anteriores.
     * 
     * TODO: usar translação e rotação do contexto gráfico para simplificar
     * o código.
     */
    public static void desenharPonteiroAnterior( Graphics2D g2d, int x, int y, int larguraSeta, int alturaSeta, DirecaoSeta ps, Color cor ) {
        
        g2d = (Graphics2D) g2d.create();
        g2d.setColor( cor );
        
        switch ( ps ) {
            case CIMA:
                g2d.drawLine( x, y, x + alturaSeta, y );
                g2d.drawLine( x + alturaSeta, y, x + alturaSeta, y - larguraSeta );
                g2d.drawLine( x + alturaSeta, y - larguraSeta, x, y - larguraSeta );
                if ( alturaSeta >= 0 ) {
                    g2d.drawLine( x, y - larguraSeta, x + 5, y - larguraSeta - 5 );
                    g2d.drawLine( x, y - larguraSeta, x + 5, y - larguraSeta + 5 );
                } else {
                    g2d.drawLine( x, y - larguraSeta, x - 5, y - larguraSeta - 5 );
                    g2d.drawLine( x, y - larguraSeta, x - 5, y - larguraSeta + 5 );
                }
                break;
            case BAIXO:
                g2d.drawLine( x, y, x + alturaSeta, y );
                g2d.drawLine( x + alturaSeta, y, x + alturaSeta, y + larguraSeta );
                g2d.drawLine( x + alturaSeta, y + larguraSeta, x, y + larguraSeta );
                if ( alturaSeta >= 0 ) {
                    g2d.drawLine( x, y + larguraSeta, x + 5, y + larguraSeta - 5 );
                    g2d.drawLine( x, y + larguraSeta, x + 5, y + larguraSeta + 5 );
                } else {
                    g2d.drawLine( x, y + larguraSeta, x - 5, y + larguraSeta - 5 );
                    g2d.drawLine( x, y + larguraSeta, x - 5, y + larguraSeta + 5 );
                }
                break;
            case ESQUERDA:
                g2d.drawLine( x, y, x, y + alturaSeta );
                g2d.drawLine( x, y + alturaSeta, x - larguraSeta, y + alturaSeta );
                g2d.drawLine( x - larguraSeta, y + alturaSeta, x - larguraSeta, y );
                if ( alturaSeta >= 0 ) {
                    g2d.drawLine( x - larguraSeta, y, x - larguraSeta - 5, y + 5 );
                    g2d.drawLine( x - larguraSeta, y, x - larguraSeta + 5, y + 5 );
                } else {
                    g2d.drawLine( x - larguraSeta, y, x - larguraSeta - 5, y - 5 );
                    g2d.drawLine( x - larguraSeta, y, x - larguraSeta + 5, y - 5 );
                }
                break;
            case DIREITA:
                g2d.drawLine( x, y, x, y + alturaSeta );
                g2d.drawLine( x, y + alturaSeta, x + larguraSeta, y + alturaSeta );
                g2d.drawLine( x + larguraSeta, y + alturaSeta, x + larguraSeta, y );
                if ( alturaSeta >= 0 ) {
                    g2d.drawLine( x + larguraSeta, y, x + larguraSeta - 5, y + 5 );
                    g2d.drawLine( x + larguraSeta, y, x + larguraSeta + 5, y + 5 );
                } else {
                    g2d.drawLine( x + larguraSeta, y, x + larguraSeta - 5, y - 5 );
                    g2d.drawLine( x + larguraSeta, y, x + larguraSeta + 5, y - 5 );
                }
                break;
        }
        
        g2d.dispose();
        
    }
    
    public static double gerarHipotenusa(
            double x1, double y1,
            double x2, double y2 ) {

        double x = Math.abs( x1 ) - Math.abs( x2 );
        double y = Math.abs( y1 ) - Math.abs( y2 );

        return Math.sqrt( Math.pow( x, 2 ) + Math.pow( y, 2 ) );

    }
    
    // para sistema carteziano normal
    private static int detectarQuadrante(
            double x1, double y1,
            double x2, double y2 ) {

        if ( ( x2 > x1 && y2 > y1 ) ||
                ( x2 > x1 && y2 == y1 ) ||
                ( x2 == x1 && y2 > y1 ) ||
                ( x2 == x1 && y2 == y1 ) )
            return 1;

        if ( ( x2 < x1 && y2 > y1 ) )
            return 2;

        if ( ( x2 < x1 && y2 < y1 ) ||
                ( x2 == x1 && y2 < y1 ) ||
                ( x2 < x1 && y2 == y1 ) )
            return 3;
        
        return 4;

    }

    private static int gerarIncrementoAngulo(
            double x1, double y1,
            double x2, double y2 ) {

        int q = detectarQuadrante( x1, y1, x2, y2 );

        if ( q == 1 ) {
            return 0;
        } else if ( q == 2 ) {
            return 90;
        } else if ( q == 3 ) {
            return 180;
        } else {
            return 270;
        }

    }
    
    // adaptado
    public static double obtemGrauRelativoJava(
            double x1, double y1,
            double x2, double y2 ) {

        double x = 0;
        double y = 0;

        if ( x2 > x1 )
            x = x2 - x1;
        else
            x = x1 - x2;

        if ( y2 > y1 )
            y = y2 - y1;
        else
            y = y1 - y2;

        int incr = gerarIncrementoAngulo( x1, y1, x2, y2 );
        double ang = Math.toDegrees( Math.atan2( y, x ) );

        if ( incr == 90 || incr == 270 )
            ang = 90 - ang;

        return incr + ang;

    }
    
    public static void desenharFlechaVertice( VerticeGrafoBasicoAnotado origem, VerticeGrafoBasicoAnotado destino, Graphics2D g2d ) {
        
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
 