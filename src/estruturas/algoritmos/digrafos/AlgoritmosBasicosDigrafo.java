/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package estruturas.algoritmos.digrafos;

import estruturas.Digrafo;

/**
 * Classe com alguns algoritmos básicos para digrafos.
 *
 * @author David Buzatto
 */
public class AlgoritmosBasicosDigrafo {

    /**
     * Calcula o grau de saída de um vértice de um digrafo.
     * 
     * @param dg Digrafo
     * @param v Vértice
     * @return Grau de saída vértice do grafo.
     */
    public static <Tipo extends Comparable<? super Tipo>> int grauSaida( Digrafo<Tipo> dg, Tipo v ) {
        
        int grauEntrada = 0;
        
        for ( Tipo w : dg.getAdjacentes( v ) ) {
            grauEntrada++;
        }
        
        return grauEntrada;
        
    }
    
    /**
     * Calcula o grau de saída de um vértice de um digrafo.
     * 
     * @param dg Digrafo
     * @param v Vértice
     * @return Grau de saída vértice do grafo.
     */
    public static <Tipo extends Comparable<? super Tipo>> int grauEntrada( Digrafo<Tipo> dg, Tipo v ) {
        
        int grauSaida = 0;
        
        for ( Tipo w : dg.getVertices() ) {
            
            // se não é o nó de origem
            if ( !w.equals( v ) ) {
                
                for ( Tipo k : dg.getAdjacentes( w ) ) {
                    if ( k.equals( v ) ) {
                        grauSaida++;
                    }
                }
                
            }
            
        }
        
        return grauSaida;
        
    }

    /**
     * Calcula o maior grau de saída do digrafo.
     * 
     * @param dg Digrafo
     * @return O maior grau de saída do grafo.
     */
    public static <Tipo extends Comparable<? super Tipo>> int grauMaximoSaida( Digrafo<Tipo> dg ) {
        
        int max = 0;
        int grauSaida;
        
        for ( Tipo v : dg.getVertices() ) {
            grauSaida = grauSaida( dg, v );
            if ( grauSaida > max ) {
                max = grauSaida;
            }
        }
        
        return max;
        
    }
    
    /**
     * Calcula o maior grau de entrada do digrafo.
     * 
     * @param dg Digrafo
     * @return O maior grau de entrada do grafo.
     */
    public static <Tipo extends Comparable<? super Tipo>> int grauMaximoEntrada( Digrafo<Tipo> dg ) {
        
        int max = 0;
        int grauEntrada;
        
        for ( Tipo v : dg.getVertices() ) {
            grauEntrada = grauEntrada( dg, v );
            if ( grauEntrada > max ) {
                max = grauEntrada;
            }
        }
        
        return max;
        
    }

    /**
     * Calcula o grauSaida médio do grafo.
     * 
     * @param dg Digrafo
     * @return Grau médio do grafo.
     */
    public static double grauMedio( Digrafo dg ) {
        return dg.getQuantidadeArestas() / (double) dg.getQuantidadeVertices();
    }

    /**
     * Calcula a quantidade de laços dentro de um grafo.
     * 
     * @param dg Digrafo
     * @return Quantidade de laços.
     */
    public static <Tipo extends Comparable<? super Tipo>> int quantidadeLacos( Digrafo<Tipo> dg ) {
        
        int cont = 0;
        
        for ( Tipo v : dg.getVertices() ) {
            for ( Tipo w : dg.getAdjacentes( v ) ) {
                if ( v.equals( w ) ) {
                    cont++;
                }
            }
        }
        
        return cont;
        
    }

}
