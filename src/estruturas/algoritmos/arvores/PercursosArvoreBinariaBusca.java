/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package estruturas.algoritmos.arvores;

import estruturas.ArvoreBinariaBusca;
import estruturas.Fila;
import estruturas.Pilha;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementação dos percursos das árvores binárias de busca.
 * 
 * @author David Buzatto
 */
public class PercursosArvoreBinariaBusca {
    
    /**
     * Retorna uma lista contendo os ítens da árvore na ordem do percurso
     * especificado.
     * 
     * @param <Tipo> Tipo de dados da árvore.
     * @param abb Árvore que será percorrida.
     * @param tipo Tipo do percurso a ser executado.
     * @return Lista de elementos na ordem do percurso executado.
     */
    public static <Tipo extends Comparable> Iterable<Tipo> percorrer( ArvoreBinariaBusca<Tipo> abb, TipoPercursoArvores tipo ) {
        
        List<Tipo> elementos = new ArrayList<>();
        
        switch ( tipo ) {
            case PRE_ORDEM:
                preOrdem( abb.getRaiz(), elementos );
                break;
            case EM_ORDEM:
                emOrdem( abb.getRaiz(), elementos );
                break;
            case POS_ORDEM:
                posOrdem( abb.getRaiz(), elementos );
                break;
            case EM_NIVEL:
                emNivel( abb.getRaiz(), elementos );
                break;
            case PRE_ORDEM_INVERSO:
                preOrdemInverso( abb.getRaiz(), elementos );
                break;
            case EM_ORDEM_INVERSO:
                emOrdemInverso( abb.getRaiz(), elementos );
                break;
            case POS_ORDEM_INVERSO:
                posOrdemInverso( abb.getRaiz(), elementos );
                break;
            case EM_NIVEL_INVERSO:
                emNivelInverso( abb.getRaiz(), elementos );
                break;
        }
        
        
        return elementos;
        
    }
    
    /*
     * Métodos privados para os percursos.
     */
    private static <Tipo extends Comparable> void preOrdem( ArvoreBinariaBusca<Tipo>.No<Tipo> no, List<Tipo> elementos ) {
        
        if ( no != null ) {
            elementos.add( no.valor );
            preOrdem( no.esquerda, elementos );
            preOrdem( no.direita, elementos );
        }
        
    }
    
    private static <Tipo extends Comparable> void emOrdem( ArvoreBinariaBusca<Tipo>.No<Tipo> no, List<Tipo> elementos ) {
        
        if ( no != null ) {
            emOrdem( no.esquerda, elementos );
            elementos.add( no.valor );
            emOrdem( no.direita, elementos );
        }
        
    }
    
    private static <Tipo extends Comparable> void posOrdem( ArvoreBinariaBusca<Tipo>.No<Tipo> no, List<Tipo> elementos ) {
        
        if ( no != null ) {
            posOrdem( no.esquerda, elementos );
            posOrdem( no.direita, elementos );
            elementos.add( no.valor );
        }
        
    }
    
    private static <Tipo extends Comparable> void emNivel( ArvoreBinariaBusca<Tipo>.No<Tipo> no, List<Tipo> elementos ) {
        
        if ( no != null ) {
            
            Fila<ArvoreBinariaBusca<Tipo>.No<Tipo>> fila = new Fila<>();
            fila.enfileirar( no );

            while ( !fila.estaVazia() ) {

                ArvoreBinariaBusca<Tipo>.No<Tipo> atual = fila.desenfileirar();
                elementos.add( atual.valor );

                if ( atual.esquerda != null ) {
                    fila.enfileirar( atual.esquerda );
                }

                if ( atual.direita != null ) {
                    fila.enfileirar( atual.direita );
                }

            }
            
        }
        
    }
    
    private static <Tipo extends Comparable> void preOrdemInverso( ArvoreBinariaBusca<Tipo>.No<Tipo> no, List<Tipo> elementos ) {
        
        if ( no != null ) {
            elementos.add( no.valor );
            preOrdemInverso( no.direita, elementos );
            preOrdemInverso( no.esquerda, elementos );
        }
        
    }
    
    private static <Tipo extends Comparable> void emOrdemInverso( ArvoreBinariaBusca<Tipo>.No<Tipo> no, List<Tipo> elementos ) {
        
        if ( no != null ) {
            emOrdemInverso( no.direita, elementos );
            elementos.add( no.valor );
            emOrdemInverso( no.esquerda, elementos );
        }
        
    }
    
    private static <Tipo extends Comparable> void posOrdemInverso( ArvoreBinariaBusca<Tipo>.No<Tipo> no, List<Tipo> elementos ) {
        
        if ( no != null ) {
            posOrdemInverso( no.direita, elementos );
            posOrdemInverso( no.esquerda, elementos );
            elementos.add( no.valor );
        }
        
    }
    
    private static <Tipo extends Comparable> void emNivelInverso( ArvoreBinariaBusca<Tipo>.No<Tipo> no, List<Tipo> elementos ) {
        
        if ( no != null ) {
            
            Fila<ArvoreBinariaBusca<Tipo>.No<Tipo>> fila = new Fila<>();
            Pilha<ArvoreBinariaBusca<Tipo>.No<Tipo>> pilha = new Pilha<>();
            fila.enfileirar( no );

            while ( !fila.estaVazia() ) {

                ArvoreBinariaBusca<Tipo>.No<Tipo> atual = fila.desenfileirar();
                pilha.empilhar( atual );

                if ( atual.esquerda != null ) {
                    fila.enfileirar( atual.esquerda );
                }

                if ( atual.direita != null ) {
                    fila.enfileirar( atual.direita );
                }

            }

            while ( !pilha.estaVazia() ) {
                elementos.add( pilha.desempilhar().valor );
            }
        
        }
        
    }
    
}
