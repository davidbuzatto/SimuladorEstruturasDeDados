/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package estruturas.algoritmos.grafos;

import estruturas.Grafo;

/**
 * Algoritmo para contagem de componentes conexos.
 * 
 * @author David Buzatto
 */
public class ComponentesConexos {
    
    private boolean[] marcado;
    private int[] id; // identificadores dos componentes
    private int cont; // quantidade de componentes
    
    public ComponentesConexos( Grafo g ) {
        
        marcado = new boolean[g.v()];
        id = new int[g.v()];
        
        // realiza a contagem usando dfs
        for ( int v = 0; v < g.v(); v++ ) {
            if ( !marcado[v] ) {
                dfs( g, v );
                cont++;
            }
        }
        
    }

    /**
     * Retorna a quantidade de componentes conexos do grafo.
     * 
     * @return Quantidade de componentes conexos.
     */
    public int quantidade() {
        return cont;
    }
    
    /**
     * Obtém o identificador do componente de um vértice
     * @param v vértice que se deseja descobrir o componente.
     * 
     * @return O identificador do componente do vértice desejado.
     */
    public int id( int v ) {
        return id[v];
    }

    /*
     * Busca em profundidade para encontrar os componentes.
     */
    private void dfs( Grafo g, int v ) {
        
        marcado[v] = true;
        
        // todos os vertices descobertos na mesma chamada de dfs têm o mesmo id
        id[v] = cont;
        
        for ( int w : g.adj( v ) ) {
            if ( !marcado[w] ) {
                dfs( g, w );
            }
        }
        
    }
    
}
