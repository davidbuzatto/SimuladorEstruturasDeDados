/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gui.desenho.estruturas;

import estruturas.GrafoBasico;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 * Uma estrutura de grafo para ser usado no desenho.
 * 
 * @author David Buzatto
 */
public class GrafoAnotado implements Serializable {
    
    private int verticeAtual;
    
    // mapa para transição de vértice do grafo anotado para o grafo
    private transient Map<Integer, Integer> transicaoAnotadoParaGrafo;
    
    // mapa para transição de vértice do grafo para o grafo anotado
    private transient Map<Integer, Integer> transicaoGrafoParaAnotado;
    
    private Map<Integer, VerticeGrafoAnotado> vertices;
    private Map<String, ArestaGrafoAnotado> arestas;
    
    private transient GrafoBasico grafo;
    
    public GrafoAnotado() {
        vertices = new LinkedHashMap<>();
        arestas = new LinkedHashMap<>();
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
        
    }
    
    public void adicionarAresta( int v, int w ) {
        
        VerticeGrafoAnotado v1 = vertices.get( v );
        VerticeGrafoAnotado v2 = vertices.get( w );
        
        ArestaGrafoAnotado aVW = new ArestaGrafoAnotado();
        aVW.origem = v1;
        aVW.destino = v2;
        
        arestas.put( "a" + v1.v + "-" + v2.v + "a", aVW );
        
    }
    
    public void removerVertice( int v ) {
        
        VerticeGrafoAnotado ve = vertices.get( v );
        List<String> aRemover = new ArrayList<>();
        
        for ( Entry<String, ArestaGrafoAnotado> e : arestas.entrySet() ) {
            
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
        
        VerticeGrafoAnotado v1 = vertices.get( v );
        VerticeGrafoAnotado v2 = vertices.get( w );
        
        if ( v1 != null && v2 != null ) {
            arestas.remove( "a" + v1.v + "-" + v2.v + "a" );
            arestas.remove( "a" + v2.v + "-" + v1.v + "a" );
        }
        
    }
    
    public GrafoBasico gerarGrafo() {
        
        transicaoAnotadoParaGrafo = new HashMap<>();
        transicaoGrafoParaAnotado = new HashMap<>();
        int i = 0;
        
        for ( Entry<Integer, VerticeGrafoAnotado> v : vertices.entrySet() ) {
            transicaoAnotadoParaGrafo.put( v.getValue().v, i++ );
        }
        
        for ( Entry<Integer, Integer> t : transicaoAnotadoParaGrafo.entrySet() ) {
            transicaoGrafoParaAnotado.put( t.getValue(), t.getKey() );
        }
        
        GrafoBasico g = new GrafoBasico( vertices.size() );
        
        for ( Entry<String, ArestaGrafoAnotado> e : arestas.entrySet() ) {
            
            ArestaGrafoAnotado a = e.getValue();
            int v = transicaoAnotadoParaGrafo.get( a.origem.v );
            int w = transicaoAnotadoParaGrafo.get( a.destino.v );
            
            g.adicionarAresta( v, w );
            
        }
        
        grafo = g;
        return grafo;
        
    }

    public Map<Integer, Integer> getTransicaoAnotadoParaGrafo() {
        return transicaoAnotadoParaGrafo;
    }
    
    public Map<Integer, Integer> getTransicaoGrafoParaAnotado() {
        return transicaoGrafoParaAnotado;
    }

    public Map<Integer, VerticeGrafoAnotado> getVertices() {
        return vertices;
    }

    public Map<String, ArestaGrafoAnotado> getArestas() {
        return arestas;
    }

    public GrafoBasico getGrafo() {
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
        System.out.println( a.gerarGrafo() );
        
    }
    
    public void limpar() {
        vertices = new LinkedHashMap<>();
        arestas = new LinkedHashMap<>();
        verticeAtual = 0;
    }
    
}
