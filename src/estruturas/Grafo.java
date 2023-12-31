/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package estruturas;

import estruturas.algoritmos.grafos.AlgoritmosBasicosGrafo;
import estruturas.algoritmos.grafos.BuscaLargura;
import estruturas.algoritmos.grafos.BuscaProfundidade;
import estruturas.algoritmos.grafos.ComponentesConexos;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

/**
 * Implementação de um grafo utilizando lista de adjacências implementada como
 * mapa.
 * 
 * Permite mais de uma aresta v-w/w-v e remove todas as ocorrências.
 * 
 * Implementa Serializable para poder ser salvo.
 * 
 * @author David Buzatto
 */
public class Grafo<Tipo extends Comparable<? super Tipo>> implements Serializable {
    
    private static final long serialVersionUID = 2L;
    
    private int quantidadeArestas;
    private Map<Tipo, List<Tipo>> adj;
    
    /** 
     * Constrói um grafo vazio.
     */
    public Grafo() {
        
        // cria o mapa de listas de adjacências
        adj = new TreeMap<>();
        
    }
    
    /**
     * Adiciona um vértice no grafo.
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
     * Remove um vértice do grafo e todas as arestas em que ele está.
     * 
     * @param v Valor do vértice.
     */
    public void removerVertice( Tipo v ) {
        
        // se o vértice existe
        if ( adj.containsKey( v ) ) {
            
            // para cada aresta do vértice
            for ( Tipo w : getAdjacentes( v ) ) {
                
                // remove a ligação à ele
                List<Tipo> vs = getAdjacentes( w );

                while ( vs.contains( v ) ) {
                    vs.remove( v );
                    quantidadeArestas--;
                }
                
            }
            
            quantidadeArestas -= getAdjacentes( v ).size();
            adj.remove( v );
            
        }
        
    }
    
    /**
     * Adicionar uma aresta v-w / w-v.
     * Permite arestas duplicadas!
     * 
     * @param v Uma extremidade da aresta.
     * @param w Outra extremidade da aresta.
     */
    public void adicionarAresta( Tipo v, Tipo w ) {
        
        // como é um grafo, a aresta é de ida e volta, ou seja,
        // v-w e w-v. em laços, haverá dois laços iguais para cada vértice
        // com laço
        adicionarVertice( v );
        adicionarVertice( w );
        
        getAdjacentes( v ).add( 0, w );
        getAdjacentes( w ).add( 0, v );
        /*getAdjacentes( v ).add( w );
        getAdjacentes( w ).add( v );*/
        
        quantidadeArestas++;
        
    }
    
    /**
     * Remove uma aresta v-w / w-v.
     * Remove duplicatas caso exista!
     * 
     * @param v Uma extremidade da aresta.
     * @param w Outra extremidade da aresta.
     */
    public void removerAresta( Tipo v, Tipo w ) {
        
        int quantidadeRemovida = 0;
        
        if ( adj.containsKey( v ) ) {
            List<Tipo> ws = getAdjacentes( v );
            while ( ws.contains( w ) ) {
                ws.remove( w );
                quantidadeRemovida++;
            }
        }
        
        if ( adj.containsKey( w ) ) {
            List<Tipo> vs = getAdjacentes( w );
            while ( vs.contains( v ) ) {
                vs.remove( v );
                quantidadeRemovida++;
            }
        }
        
        quantidadeArestas -= quantidadeRemovida/2;
        
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
        
        Grafo<Integer> g = new Grafo<>();        
        g.adicionarAresta( 0, 5 );
        g.adicionarAresta( 4, 3 );
        g.adicionarAresta( 0, 1 );
        g.adicionarAresta( 9, 12 );
        g.adicionarAresta( 6, 4 );
        g.adicionarAresta( 5, 4 );
        g.adicionarAresta( 0, 2 );
        g.adicionarAresta( 11, 12 );
        g.adicionarAresta( 9, 10 );
        g.adicionarAresta( 0, 6 );
        g.adicionarAresta( 7, 8 );
        g.adicionarAresta( 9, 11 );
        g.adicionarAresta( 5, 3 );
        
        System.out.println( g );
        
        /*g.adicionarVertice( 30 );
        g.adicionarAresta( 30, 5 );
        g.removerVertice( 5 );
        g.removerVertice( 30 );
        System.out.println( g );*/
        
        // utilizando os algoritmos
        System.out.println( "Grau do vértice 0: " + AlgoritmosBasicosGrafo.grau( g, 0 ) );
        System.out.println( "Grau máximo: " + AlgoritmosBasicosGrafo.grauMaximo( g ) );
        System.out.println( "Grau médio: " + AlgoritmosBasicosGrafo.grauMedio( g ) );
        System.out.println( "Quantidade de laços: " + AlgoritmosBasicosGrafo.quantidadeLacos( g ) );
        
        BuscaProfundidade<Integer> dfs = new BuscaProfundidade<>( g, 0 );
        System.out.println( dfs );
        
        for ( int w = 0; w < g.getQuantidadeVertices(); w++ ) {
            System.out.printf( "Caminho de 0 a %d: ", w );
            for ( int a : dfs.caminhoAte( w ) ) {
                System.out.print( a + " " );
            }
            System.out.println();
        }
        System.out.println();
        
        
        BuscaLargura<Integer> bfs = new BuscaLargura<>( g, 0 );
        System.out.println( bfs );
        
        for ( int w = 0; w < g.getQuantidadeVertices(); w++ ) {
            System.out.printf( "Caminho de 0 a %d: ", w );
            for ( int a : bfs.caminhoAte( w ) ) {
                System.out.print( a + " " );
            }
            System.out.println();
        }
        System.out.println();
        
        ComponentesConexos<Integer> cc = new ComponentesConexos<>( g );
        System.out.println( "Quantidade de componentes conexos: " + cc.getQuantidade() );
        
    }
    
}
