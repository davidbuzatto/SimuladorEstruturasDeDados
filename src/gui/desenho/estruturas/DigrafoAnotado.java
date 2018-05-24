/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gui.desenho.estruturas;

import estruturas.Digrafo;
import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * Uma estrutura de digrafo para ser usado no desenho.
 * 
 * @author David Buzatto
 */
public class DigrafoAnotado implements Serializable {
    
    private static final long serialVersionUID = 2L;
    
    private int verticeAtual;
    private Map<Integer, VerticeGrafoAnotado> vertices;
    private Digrafo<Integer> digrafo;
    
    public DigrafoAnotado() {
        digrafo = new Digrafo<>();
        vertices = new TreeMap<>();
    }
    
    // x e y são o centro do vértice no desenho
    public void adicionarVertice( int x, int y, int tamanho ) {
        
        VerticeGrafoAnotado v = new VerticeGrafoAnotado();
        
        v.v = verticeAtual++;
        
        v.xIni = x - tamanho / 2;
        v.yIni = y - tamanho / 2;
        v.xCentro = x;
        v.yCentro = y;
        v.xFim = v.xIni + tamanho;
        v.yFim = v.yIni + tamanho;
        v.tamanho = tamanho;
        
        vertices.put( v.v, v );
        digrafo.adicionarVertice( v.v );
        
    }
    
    public void adicionarAresta( int v, int w ) {
        digrafo.adicionarAresta( v, w );
    }
    
    public void removerVertice( int v ) {
        digrafo.removerVertice( v );
        
    }
    
    public void removerAresta( int v, int w ) {
        digrafo.removerAresta( v, w );
    }

    public Map<Integer, VerticeGrafoAnotado> getVertices() {
        return vertices;
    }

    public Digrafo<Integer> getDigrafo() {
        return digrafo;
    }
    
    public static void main( String[] args ) {
        
        DigrafoAnotado a = new DigrafoAnotado();
        a.adicionarVertice( 1, 1, 1 );
        a.adicionarVertice( 1, 1, 1 );
        a.adicionarVertice( 1, 1, 1 );
        a.adicionarVertice( 1, 1, 1 );
        a.adicionarVertice( 1, 1, 1 );
        a.adicionarVertice( 1, 1, 1 );
        a.adicionarVertice( 1, 1, 1 );
        a.adicionarVertice( 1, 1, 1 );
        a.adicionarVertice( 1, 1, 1 );
        
        a.adicionarAresta( 0, 1 );
        //a.removerAresta( 1, 0 );
        
        System.out.println( a.verticeAtual );
        System.out.println( a.getDigrafo() );
        
    }
    
    public void limpar() {
        digrafo = new Digrafo<>();
        vertices = new LinkedHashMap<>();
        verticeAtual = 0;
    }
    
}
