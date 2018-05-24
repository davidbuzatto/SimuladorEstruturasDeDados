/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package estruturas.algoritmos.grafos;

import estruturas.Grafo;
import estruturas.Pilha;
import java.util.HashMap;
import java.util.Map;

/**
 * Algoritmo de busca em profundidade.
 * 
 * @author David Buzatto
 */
public class BuscaProfundidade<Tipo extends Comparable<? super Tipo>> extends Caminhos<Tipo> {

    private Map<Tipo, Boolean> marcado;
    private Map<Tipo, Tipo> arestaAte;
    private Grafo<Tipo> g;

    public BuscaProfundidade( Grafo<Tipo> g, Tipo f ) {
        
        this.g = g;
        this.fonte = f;
        
        marcado = new HashMap<>();
        arestaAte = new HashMap<>();
        
        for ( Tipo v : g.getVertices() ) {
            marcado.put( v, false );
            arestaAte.put( v, null );
        }
        
        dfs( g, f );
        
    }

    private void dfs( Grafo<Tipo> g, Tipo v ) {
        
        marcado.put( v, true );
        
        for ( Tipo w : g.getAdjacentes( v ) ) {
            
            if ( !marcado.get( w ) ) {
                dfs( g, w );
                arestaAte.put( w, v );
            }
            
        }
        
    }
    
    @Override
    public Iterable<Tipo> caminhoAte( Tipo w ) {
        
        Pilha<Tipo> p = new Pilha<>();
        
        if ( arestaAte.get( w ) != null ) {    
            
            Tipo atual = w;
            p.empilhar( atual );

            while ( atual != null ) {
                atual = arestaAte.get( atual );
                if ( atual != null ) {
                    p.empilhar( atual );
                }
            }
            
        }
        
        return p;
        
    }
    
    @Override
    public boolean existeCaminhoAte( Tipo w ) {
        return arestaAte.get( w ) != null;
    }

    public Map<Tipo, Boolean> getMarcado() {
        return marcado;
    }

    public Map<Tipo, Tipo> getArestaAte() {
        return arestaAte;
    }

    @Override
    public String toString() {
        
        StringBuilder sb = new StringBuilder();
        
        sb.append( "Busca em Profunidade (fonte: vértice " ).append( fonte ).append( ")\n" );
        sb.append( "v\tmarcado[v]\tarestaAte[v]\n" );
        
        for ( Tipo v : g.getVertices() ) {
            sb.append( String.format( "%d\t%s\t\t%s\n",
                    v, 
                    marcado.get( v ) ? "T" : "F", 
                    arestaAte.get( v ) == null ? "-" : arestaAte.get( v ) ) );
        }
        
        return sb.toString();
        
    }
    
}
