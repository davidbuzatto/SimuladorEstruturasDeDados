/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package estruturas.algoritmos.grafos.basico;

import estruturas.GrafoBasico;
import estruturas.Pilha;
import estruturas.algoritmos.grafos.Caminhos;

/**
 * Algoritmo de busca em profundidade.
 * 
 * @author David Buzatto
 */
public class BuscaProfundidade extends Caminhos<Integer> {

    private boolean[] marcado;
    private int[] arestaAte;
    private GrafoBasico g;

    public BuscaProfundidade( GrafoBasico g, int f ) {
        
        this.g = g;
        this.fonte = f;
        
        marcado = new boolean[g.v()];
        arestaAte = new int[g.v()];
        
        for ( int i = 0; i < g.v(); i++ ) {
            arestaAte[i] = -1;
        }
        
        dfs( g, f );
        
    }

    private void dfs( GrafoBasico g, int v ) {
        
        marcado[v] = true;
        
        for ( int w : g.adj( v ) ) {
            
            if ( !marcado[w] ) {
                dfs( g, w );
                arestaAte[w] = v;
            }
            
        }
        
    }
    
    @Override
    public Iterable<Integer> caminhoAte( Integer w ) {
        
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
    public boolean existeCaminhoAte( Integer w ) {
        return arestaAte[w] != -1;
    }

    public boolean[] getMarcado() {
        return marcado.clone();
    }

    public int[] getArestaAte() {
        return arestaAte.clone();
    }

    @Override
    public String toString() {
        
        StringBuilder sb = new StringBuilder();
        
        sb.append( "Busca em Profunidade (fonte: vértice " ).append( fonte ).append( ")\n" );
        sb.append( "v\tmarcado[v]\tarestaAte[v]\n" );
        for ( int v = 0; v < g.v(); v++ ) {
            sb.append( String.format( "%d\t%s\t\t%s\n",
                    v, 
                    marcado[v] ? "T" : "F", 
                    arestaAte[v] == -1 ? "-" : arestaAte[v] ) );
        }
        
        return sb.toString();
        
    }
    
}
