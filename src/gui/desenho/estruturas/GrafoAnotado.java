/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gui.desenho.estruturas;

import estruturas.Grafo;
import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * Uma estrutura de grafo para ser usado no desenho.
 * 
 * @author David Buzatto
 */
public class GrafoAnotado implements Serializable {
    
    private static final long serialVersionUID = 2L;
    
    private int verticeAtual;
    private Map<Integer, VerticeGrafoAnotado> vertices;
    private Grafo<Integer> grafo;
    
    public GrafoAnotado() {
        grafo = new Grafo<>();
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
        grafo.adicionarVertice( v.v );
        
    }
    
    public void adicionarAresta( int v, int w ) {
        grafo.adicionarAresta( v, w );
    }
    
    public void removerVertice( int v ) {
        grafo.removerVertice( v );
        
    }
    
    public void removerAresta( int v, int w ) {
        grafo.removerAresta( v, w );
    }

    public Map<Integer, VerticeGrafoAnotado> getVertices() {
        return vertices;
    }

    public Grafo<Integer> getGrafo() {
        return grafo;
    }
    
    public static void main( String[] args ) {
        
        GrafoAnotado a = new GrafoAnotado();
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
        System.out.println( a.getGrafo() );
        
    }
    
    public void limpar() {
        grafo = new Grafo<>();
        vertices = new LinkedHashMap<>();
        verticeAtual = 0;
    }
    
}
