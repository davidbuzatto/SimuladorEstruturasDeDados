/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gui.desenho.estruturas;

import estruturas.Grafo;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 * Uma estrutura de grafo para ser usado no desenho.
 * 
 * @author David Buzatto
 */
public class GrafoAnotado {
    
    private int verticeAtual;
    
    // mapa de transição entre esta representação de grafo e a representação
    // da estrutura de dados
    private Map<Integer, Integer> transicao;
    
    private Map<Integer, VerticeGrafo> vertices;
    private Map<String, ArestaGrafo> arestas;
    
    public GrafoAnotado() {
        vertices = new HashMap<>();
        arestas = new HashMap<>();
    }
    
    // x e y são o centro do vértice no desenho
    public void adicionarVertice( int x, int y, int tamanho ) {
        
        VerticeGrafo v = new VerticeGrafo();
        
        v.v = verticeAtual++;
        
        v.xIni = x - tamanho / 2;
        v.yIni = y - tamanho / 2;
        v.xCentro = x;
        v.yCentro = y;
        v.xFim = v.xIni + tamanho;
        v.yFim = v.yIni + tamanho;
        v.tamanho = tamanho;
        
        vertices.put( v.v, v );
        
    }
    
    public void adicionarAresta( int v, int w ) {
        
        VerticeGrafo v1 = vertices.get( v );
        VerticeGrafo v2 = vertices.get( w );
        
        ArestaGrafo aVW = new ArestaGrafo();
        aVW.origem = v1;
        aVW.destino = v2;
        
        arestas.put( "a" + v1.v + "-" + v2.v + "a", aVW );
        
    }
    
    public void removerVertice( int v ) {
        
        VerticeGrafo ve = vertices.get( v );
        List<String> aRemover = new ArrayList<>();
        
        for ( Entry<String, ArestaGrafo> e : arestas.entrySet() ) {
            
            if ( e.getKey().contains( "a" + v + "-" ) ) {
                aRemover.add( e.getKey() );
            }
            
            if ( e.getKey().contains( "-" + v + "a" ) ) {
                aRemover.add( e.getKey() );
            }
            
        }
        
        for ( String r : aRemover ) {
            arestas.remove( r );
        }
        
        vertices.remove( ve.v );
        
    }
    
    public void removerAresta( int v, int w ) {
        
        VerticeGrafo v1 = vertices.get( v );
        VerticeGrafo v2 = vertices.get( w );
        
        if ( v1 != null && v2 != null ) {
            arestas.remove( "a" + v1.v + "-" + v2.v + "a" );
            arestas.remove( "a" + v2.v + "-" + v1.v + "a" );
        }
        
    }
    
    public Grafo gerarGrafo() {
        
        transicao = new HashMap<>();
        int i = 0;
        
        for ( Entry<Integer, VerticeGrafo> v : vertices.entrySet() ) {
            transicao.put( v.getValue().v, i++ );
        }
        
        Grafo g = new Grafo( vertices.size() );
        
        for ( Entry<String, ArestaGrafo> e : arestas.entrySet() ) {
            
            ArestaGrafo a = e.getValue();
            int v = transicao.get( a.origem.v );
            int w = transicao.get( a.destino.v );
            
            g.adicionarAresta( v, w );
            
        }
        
        return g;
        
    }

    public Map<Integer, Integer> getTransicao() {
        return transicao;
    }

    public Map<Integer, VerticeGrafo> getVertices() {
        return vertices;
    }

    public Map<String, ArestaGrafo> getArestas() {
        return arestas;
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
        System.out.println( a.gerarGrafo() );
        
    }
    
}
