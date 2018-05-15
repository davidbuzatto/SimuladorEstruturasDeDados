/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package estruturas.algoritmos.grafos;

import estruturas.GrafoBasico;
import estruturas.Grafo;
import java.util.HashMap;
import java.util.Map;

/**
 * Algoritmo para contagem de componentes conexos.
 * 
 * @author David Buzatto
 */
public class ComponentesConexos<Tipo extends Comparable<? super Tipo>> {
    
    private Map<Tipo, Boolean> marcado;
    private Map<Tipo, Integer> id;
    private int cont; // getQuantidade de componentes
    
    public ComponentesConexos( Grafo<Tipo> g ) {
        
        marcado = new HashMap<>();
        id = new HashMap<>();
        
        for ( Tipo v : g.getVertices() ) {
            marcado.put( v, false );
            id.put( v, 0 );
        }
        
        // realiza a contagem usando dfs
        for ( Tipo v : g.getVertices() ) {
            if ( !marcado.get( v ) ) {
                dfs( g, v );
                cont++;
            }
        }
        
    }

    /**
     * Verifica se dois vértices estão conectados.
     * 
     * @param v Um dos vértices
     * @param w Outro vértice
     * @return Se os dois vértices estão conectados.
     */
    public boolean conectado( Tipo v, Tipo w ) {
        return id.get( v ) == id.get( w );
    }
    
    /**
     * Retorna a getQuantidade de componentes conexos do grafo.
     * 
     * @return Quantidade de componentes conexos.
     */
    public int getQuantidade() {
        return cont;
    }
    
    /**
     * Obtém o identificador do componente de um vértice
     * 
     * @param v vértice que se deseja descobrir o componente.
     * @return O identificador do componente do vértice desejado.
     */
    public int id( Tipo v ) {
        return id.get( v );
    }

    /*
     * Busca em profundidade para encontrar os componentes.
     */
    private void dfs( Grafo<Tipo> g, Tipo v ) {
        
        marcado.put( v , true );
        
        // todos os vertices descobertos na mesma chamada de dfs têm o mesmo id
        id.put( v , cont );
        
        for ( Tipo w : g.getAdjacentes( v ) ) {
            
            if ( !marcado.get( w ) ) {
                dfs( g, w );
            }
            
        }
        
    }
    
}
