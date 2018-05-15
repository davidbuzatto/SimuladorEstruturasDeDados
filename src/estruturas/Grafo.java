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
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Implementação de um grafo utilizando lista de adjacências.
 * Permite mais de uma aresta v-w/w-v e remove todas as ocorrências.
 * 
 * @author David Buzatto
 */
public class Grafo<Tipo extends Comparable> {
    
    private int quantidadeArestas;
    private Map<Tipo, List<Tipo>> adj;
    
    /**
     * Constrói um grafo vazio.
     */
    public Grafo() {
        
        // cria o mapa de listas de adjacências
        adj = new LinkedHashMap<>();
        
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
        if ( !adj.containsKey( v ) ) {
            adj.put( v, new ArrayList<>() );
        }
        
        if ( !adj.containsKey( w ) ) {
            adj.put( w, new ArrayList<>() );
        }
        
        adj.get( v ).add( 0, w );
        adj.get( w ).add( 0, v );
        
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
            List<Tipo> ws = adj.get( v );
            while ( ws.contains( w ) ) {
                ws.remove( w );
                quantidadeRemovida++;
            }
        }
        
        if ( adj.containsKey( w ) ) {
            List<Tipo> vs = adj.get( w );
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
     * @param v Vértique que se deseja obter os adjacentes.
     * @return Vértices adjacentes a v ou null caso v não esteja no grafo.
     */
    public Iterable<Tipo> getAdjacentes( Tipo v ) {
        return adj.get( v );
    }
    
    /**
     * Retorna os vértices do grafo.
     * 
     * @return Vértices do grafo.
     */
    public Iterable<Tipo> getVertices() {
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
