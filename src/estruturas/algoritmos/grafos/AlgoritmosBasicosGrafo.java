/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package estruturas.algoritmos.grafos;

import estruturas.Grafo;

/**
 * Classe com alguns algoritmos básicos para grafos.
 *
 * @author David Buzatto
 */
public class AlgoritmosBasicosGrafo {

    /**
     * Calcula o grau de um vértice de um grafo.
     * 
     * @param g Grafo
     * @param v Vértice
     * @return Grau do vértice do grafo.
     */
    public static <Tipo extends Comparable<? super Tipo>> int grau( Grafo<Tipo> g, Tipo v ) {
        
        int grau = 0;
        
        for ( Tipo w : g.getAdjacentes( v ) ) {
            grau++;
        }
        
        return grau;
        
    }

    /**
     * Calcula o maior grau do grafo.
     * 
     * @param g Grafo
     * @return O maior grau do grafo.
     */
    public static <Tipo extends Comparable<? super Tipo>> int grauMaximo( Grafo<Tipo> g ) {
        
        int max = 0;
        int grau;
        
        for ( Tipo v : g.getVertices() ) {
            grau = grau( g, v );
            if ( grau > max ) {
                max = grau;
            }
        }
        
        return max;
        
    }

    /**
     * Calcula o grau médio do grafo.
     * 
     * @param g Grafo
     * @return Grau médio do grafo.
     */
    public static double grauMedio( Grafo g ) {
        return 2.0 * g.getQuantidadeArestas() / (double) g.getQuantidadeVertices();
    }

    /**
     * Calcula a quantidade de laços dentro de um grafo.
     * 
     * @param g Grafo
     * @return Quantidade de laços.
     */
    public static <Tipo extends Comparable<? super Tipo>> int quantidadeLacos( Grafo<Tipo> g ) {
        
        int cont = 0;
        
        for ( Tipo v : g.getVertices() ) {
            for ( Tipo w : g.getAdjacentes( v ) ) {
                if ( v.equals( w ) ) {
                    cont++;
                }
            }
        }
        
        // para laços, 2 iguais (implementação)
        return cont / 2;
        
    }

}
