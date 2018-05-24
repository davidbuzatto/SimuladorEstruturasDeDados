/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package estruturas.algoritmos.grafos;

import estruturas.Fila;
import estruturas.Grafo;
import estruturas.Pilha;
import java.util.Map;
import java.util.TreeMap;

/**
 * Algoritmo de busca em largura.
 * 
 * @author David Buzatto
 */
public class BuscaLargura<Tipo extends Comparable<? super Tipo>> extends Caminhos<Tipo> {

    private Map<Tipo, Boolean> marcado;
    private Map<Tipo, Tipo> arestaAte;
    private Map<Tipo, Integer> distanciaAte;
    private Grafo<Tipo> g;
    
    public BuscaLargura( Grafo<Tipo> g, Tipo f ) {
        
        this.g = g;
        this.fonte = f;
        
        marcado = new TreeMap<>();
        arestaAte = new TreeMap<>();
        distanciaAte = new TreeMap<>();
        
        for ( Tipo v : g.getVertices() ) {
            marcado.put( v, false );
            arestaAte.put( v, null );
            distanciaAte.put( v, -1 );
        }
        
        bfs( g, f );
        
    }

    private void bfs( Grafo<Tipo> g, Tipo f ) {
        
        Fila<Tipo> fila = new Fila<>();
        fila.enfileirar( f );
        marcado.put( f, true );
        distanciaAte.put( f, 0 );
        
        while ( !fila.estaVazia() ) {
            
            Tipo v = fila.desenfileirar();
            
            for ( Tipo w : g.getAdjacentes( v ) ) {
                
                if ( !marcado.get( w ) ) {
                    fila.enfileirar( w );
                    marcado.put( w, true );
                    arestaAte.put( w, v );
                    distanciaAte.put( w, distanciaAte.get( v ) + 1 );
                }
                
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

    public Map<Tipo, Integer> getDistanciaAte() {
        return distanciaAte;
    }

    @Override
    public String toString() {
        
        StringBuilder sb = new StringBuilder();
        
        sb.append( "Busca em Largura (fonte: vértice " ).append( fonte ).append( ")\n" );
        sb.append( "v\tmarcado[v]\tarestaAte[v]\tdistanciaAte[v]\n" );
        
        for ( Tipo v : g.getVertices() ) {
            sb.append( String.format( "%d\t%s\t\t%s\t\t%s\n", 
                    v, 
                    marcado.get( v ) ? "T" : "F", 
                    arestaAte.get( v ) == null ? "-" : arestaAte.get( v ),  
                    distanciaAte.get( v ) == -1 ? "-" : distanciaAte.get( v ) ) );
        }
        
        return sb.toString();
        
    }
    
}
