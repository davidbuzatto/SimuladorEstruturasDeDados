/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package estruturas;

import estruturas.algoritmos.digrafos.AlgoritmosBasicosDigrafo;
import estruturas.algoritmos.digrafos.BuscaLargura;
import estruturas.algoritmos.digrafos.BuscaProfundidade;
import estruturas.algoritmos.digrafos.ComponentesFortes;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

/**
 * Implementação de um digrafo utilizando lista de adjacências implementada como
 * mapa.
 * 
 * Permite mais de uma aresta v-w/w-v e remove todas as ocorrências.
 * 
 * Implementa Serializable para poder ser salvo.
 * 
 * @author David Buzatto
 */
public class Digrafo<Tipo extends Comparable<? super Tipo>> implements Serializable {
    
    private static final long serialVersionUID = 2L;
    
    private int quantidadeArestas;
    private Map<Tipo, List<Tipo>> adj;
    
    /**
     * Constrói um digrafo vazio.
     */
    public Digrafo() {
        
        // cria o mapa de listas de adjacências
        adj = new TreeMap<>();
        
    }
    
    /**
     * Adiciona um vértice no digrafo.
     * 
     * @param v Valor do vértice.
     */
    public void adicionarVertice( Tipo v ) {
        
        // se o vértice não existe
        if ( !adj.containsKey( v ) ) {
            
            // associa o vértice com uma lista vazia
            adj.put( v, new ArrayList<>() );
            
        }
        
    }
    
    /**
     * Remove um vértice do digrafo e todas as arestas em que ele está.
     * 
     * @param v Valor do vértice.
     */
    public void removerVertice( Tipo v ) {
        
        // se o vértice existe
        if ( adj.containsKey( v ) ) {
            
            // remoção das arestas de entrada
            for ( Tipo w : getVertices() ) {
                
                // se não é o vértice de origem
                if ( !w.equals( v ) ) {
                    
                    // remove a ligação à ele
                    List<Tipo> vs = getAdjacentes( w );

                    while ( vs.contains( v ) ) {
                        vs.remove( v );
                        quantidadeArestas--;
                    }
                    
                }
                
                
            }
            
            // remoção das arestas de saída
            quantidadeArestas -= getAdjacentes( v ).size();
            adj.remove( v );
            
        }
        
    }
    
    /**
     * Adicionar uma aresta v-w.
     * Permite arestas duplicadas!
     * 
     * @param v Uma extremidade da aresta.
     * @param w Outra extremidade da aresta.
     */
    public void adicionarAresta( Tipo v, Tipo w ) {
        
        // como é um digrafo, a aresta é de ida, ou seja, v-w
        adicionarVertice( v );
        adicionarVertice( w );
        
        getAdjacentes( v ).add( 0, w );
        
        quantidadeArestas++;
        
    }
    
    /**
     * Remove uma aresta v-w.
     * Remove duplicatas caso exista!
     * 
     * @param v Uma extremidade da aresta.
     * @param w Outra extremidade da aresta.
     */
    public void removerAresta( Tipo v, Tipo w ) {
        
        if ( adj.containsKey( v ) ) {
            List<Tipo> ws = getAdjacentes( v );
            while ( ws.contains( w ) ) {
                ws.remove( w );
                quantidadeArestas--;
            }
        }
        
    }
    
    /**
     * Retorna o conjunto de vértices adjacentes a v.
     * 
     * @param v Vértice que se deseja obter os adjacentes.
     * @return Vértices adjacentes a v ou null caso v não esteja no grafo.
     */
    public List<Tipo> getAdjacentes( Tipo v ) {
        return adj.get( v );
    }
    
    /**
     * Retorna os vértices do grafo.
     * 
     * @return Vértices do grafo.
     */
    public Set<Tipo> getVertices() {
        return adj.keySet();
    }
    
    public int getQuantidadeVertices() {
        return adj.size();
    }
    
    public int getQuantidadeArestas() {
        return quantidadeArestas;
    }

    @Override
    public String toString() {
        
        StringBuilder sb = new StringBuilder();
        
        sb.append( "Vértices: " ).append( getQuantidadeVertices() ).append( "\n" );
        sb.append( "Arestas: " ).append( getQuantidadeArestas() ).append( "\n" );
        
        for ( Map.Entry<Tipo, List<Tipo>> e : adj.entrySet() ) {
            
            Tipo v = e.getKey();
            boolean primeiro = true;
            
            sb.append( "v: " ).append( v ).append( " -> { " );
            
            for ( Tipo w : e.getValue() ) {
                
                if ( primeiro ) {
                    primeiro = false;
                } else {
                    sb.append( ", " );
                }
                
                sb.append( w );
                
            }
            
            sb.append( " }\n" );
            
        }
        
        return sb.toString();
        
    }
    
    public static void main( String[] args ) {
        
        Digrafo<Integer> dg = new Digrafo<>();        
        dg.adicionarAresta( 0, 5 );
        dg.adicionarAresta( 4, 3 );
        dg.adicionarAresta( 0, 1 );
        dg.adicionarAresta( 9, 12 );
        dg.adicionarAresta( 6, 4 );
        dg.adicionarAresta( 5, 4 );
        dg.adicionarAresta( 0, 2 );
        dg.adicionarAresta( 11, 12 );
        dg.adicionarAresta( 9, 10 );
        dg.adicionarAresta( 0, 6 );
        dg.adicionarAresta( 7, 8 );
        dg.adicionarAresta( 9, 11 );
        dg.adicionarAresta( 5, 3 );
        //dg.adicionarAresta( 5, 5 );
        
        System.out.println( dg );
        
        /*dg.adicionarVertice( 30 );
        dg.adicionarAresta( 30, 5 );
        dg.adicionarAresta( 5, 30 );
        dg.adicionarAresta( 1, 30 );
        dg.adicionarAresta( 9, 30 );
        dg.removerAresta( 5, 30 );
        dg.removerVertice( 5 );
        dg.removerVertice( 30 );
        System.out.println( dg );*/
        
        // utilizando os algoritmos
        System.out.println( "Grau de saída vértice 0: " + AlgoritmosBasicosDigrafo.grauSaida( dg, 0 ) );
        System.out.println( "Grau de entrada vértice 0: " + AlgoritmosBasicosDigrafo.grauEntrada( dg, 0 ) );
        System.out.println( "Grau de saída do vértice 3: " + AlgoritmosBasicosDigrafo.grauSaida( dg, 3 ) );
        System.out.println( "Grau de entrada do vértice 3: " + AlgoritmosBasicosDigrafo.grauEntrada( dg, 3 ) );
        System.out.println( "Grau máximo de saída: " + AlgoritmosBasicosDigrafo.grauMaximoSaida( dg ) );
        System.out.println( "Grau máximo de entrada: " + AlgoritmosBasicosDigrafo.grauMaximoEntrada( dg ) );
        System.out.println( "Grau médio: " + AlgoritmosBasicosDigrafo.grauMedio( dg ) );
        System.out.println( "Quantidade de laços: " + AlgoritmosBasicosDigrafo.quantidadeLacos( dg ) );
        
        BuscaProfundidade<Integer> dfs = new BuscaProfundidade<>( dg, 0 );
        System.out.println( dfs );
        
        for ( int w = 0; w < dg.getQuantidadeVertices(); w++ ) {
            System.out.printf( "Caminho de 0 a %d: ", w );
            for ( int a : dfs.caminhoAte( w ) ) {
                System.out.print( a + " " );
            }
            System.out.println();
        }
        System.out.println();
        
        
        BuscaLargura<Integer> bfs = new BuscaLargura<>( dg, 0 );
        System.out.println( bfs );
        
        for ( int w = 0; w < dg.getQuantidadeVertices(); w++ ) {
            System.out.printf( "Caminho de 0 a %d: ", w );
            for ( int a : bfs.caminhoAte( w ) ) {
                System.out.print( a + " " );
            }
            System.out.println();
        }
        System.out.println();
        
        ComponentesFortes<Integer> cc = new ComponentesFortes<>( dg );
        System.out.println( "Quantidade de componentes conexos: " + cc.getQuantidade() );
        
    }
    
}
