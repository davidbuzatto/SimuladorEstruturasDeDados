/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package estruturas.algoritmos.grafos;

import estruturas.Fila;
import estruturas.Grafo;
import estruturas.Pilha;

/**
 * Algoritmode busca em largura.
 * 
 * @author David Buzatto
 */
public class BuscaLargura extends Caminhos {

    private boolean[] marcado;
    private int[] arestaAte;
    private int[] distanciaAte;
    private Grafo g;
    
    public BuscaLargura( Grafo g, int f ) {
        
        this.g = g;
        this.fonte = f;
        
        marcado = new boolean[g.v()];
        arestaAte = new int[g.v()];
        distanciaAte = new int[g.v()];
        
        for ( int i = 0; i < g.v(); i++ ) {
            arestaAte[i] = -1;
            distanciaAte[i] = -1;
        }
        
        bfs( g, f );
        
    }

    private void bfs( Grafo g, int f ) {
        
        Fila<Integer> fila = new Fila<>();
        fila.enfileirar( f );
        marcado[f] = true;
        distanciaAte[f] = 0;
        
        while ( !fila.estaVazia() ) {
            
            int v = fila.desenfileirar();
            
            for ( int w : g.adj( v ) ) {
                
                if ( !marcado[w] ) {
                    fila.enfileirar( w );
                    marcado[w] = true;
                    arestaAte[w] = v;
                    distanciaAte[w] = distanciaAte[v] + 1;
                }
                
            }
            
        }
        
    }

    @Override
    public Iterable<Integer> caminhoAte( int w ) {
        
        Pilha<Integer> p = new Pilha<>();
        
        if ( arestaAte[w] != -1 ) {    
            
            int atual = w;
            p.empilhar( atual );

            while ( atual != -1 ) {
                atual = arestaAte[atual];
                if ( atual != -1 ) {
                    p.empilhar( atual );
                }
            }
            
        }
        
        return p;
        
    }
    
    @Override
    public boolean existeCaminhoAte( int w ) {
        return arestaAte[w] != -1;
    }
    
    public boolean[] getMarcado() {
        return marcado.clone();
    }

    public int[] getArestaAte() {
        return arestaAte.clone();
    }

    public int[] getDistanciaAte() {
        return distanciaAte.clone();
    }

    @Override
    public String toString() {
        
        StringBuilder sb = new StringBuilder();
        
        sb.append( "Busca em Largura (fonte: vértice " ).append( fonte ).append( ")\n" );
        sb.append( "v\tmarcado[v]\tarestaAte[v]\tdistanciaAte[v]\n" );
        for ( int v = 0; v < g.v(); v++ ) {
            sb.append( String.format( "%d\t%s\t\t%s\t\t%s\n", 
                    v, 
                    marcado[v] ? "T" : "F", 
                    arestaAte[v] == -1 ? "-" : arestaAte[v],  
                    distanciaAte[v] == -1 ? "-" : distanciaAte[v] ) );
        }
        
        return sb.toString();
        
    }
    
}
