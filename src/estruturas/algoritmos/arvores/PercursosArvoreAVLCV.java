/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package estruturas.algoritmos.arvores;

import estruturas.ArvoreAVLCV;
import estruturas.Fila;
import estruturas.Pilha;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementação dos percursos das árvores AVLs.
 * 
 * @author David Buzatto
 */
public class PercursosArvoreAVLCV {
    
    /**
     * Retorna uma lista contendo os ítens da árvore na ordem do percurso
     * especificado.
     * 
     * @param <Tipo> Tipo de dados da árvore.
     * @param aavl Árvore que será percorrida.
     * @param tipo Tipo do percurso a ser executado.
     * @return Lista de elementos na ordem do percurso executado.
     */
    public static <Tipo extends Comparable<? super Tipo>> Iterable<Tipo> percorrer( ArvoreAVLCV<Tipo> aavl, TipoPercursoArvores tipo ) {
        
        List<Tipo> elementos = new ArrayList<>();
        
        switch ( tipo ) {
            case PRE_ORDEM:
                preOrdem( aavl.getRaiz(), elementos );
                break;
            case EM_ORDEM:
                emOrdem( aavl.getRaiz(), elementos );
                break;
            case POS_ORDEM:
                posOrdem( aavl.getRaiz(), elementos );
                break;
            case EM_NIVEL:
                emNivel( aavl.getRaiz(), elementos );
                break;
            case PRE_ORDEM_INVERSO:
                preOrdemInverso( aavl.getRaiz(), elementos );
                break;
            case EM_ORDEM_INVERSO:
                emOrdemInverso( aavl.getRaiz(), elementos );
                break;
            case POS_ORDEM_INVERSO:
                posOrdemInverso( aavl.getRaiz(), elementos );
                break;
            case EM_NIVEL_INVERSO:
                emNivelInverso( aavl.getRaiz(), elementos );
                break;
        }
        
        
        return elementos;
        
    }
    
    /*
     * Métodos privados para os percursos.
     */
    private static <Tipo extends Comparable<? super Tipo>> void preOrdem( ArvoreAVLCV<Tipo>.No<Tipo> no, List<Tipo> elementos ) {
        
        if ( no != null ) {
            elementos.add( no.valor );
            preOrdem( no.esquerda, elementos );
            preOrdem( no.direita, elementos );
        }
        
    }
    
    private static <Tipo extends Comparable<? super Tipo>> void emOrdem( ArvoreAVLCV<Tipo>.No<Tipo> no, List<Tipo> elementos ) {
        
        if ( no != null ) {
            emOrdem( no.esquerda, elementos );
            elementos.add( no.valor );
            emOrdem( no.direita, elementos );
        }
        
    }
    
    private static <Tipo extends Comparable<? super Tipo>> void posOrdem( ArvoreAVLCV<Tipo>.No<Tipo> no, List<Tipo> elementos ) {
        
        if ( no != null ) {
            posOrdem( no.esquerda, elementos );
            posOrdem( no.direita, elementos );
            elementos.add( no.valor );
        }
        
    }
    
    private static <Tipo extends Comparable<? super Tipo>> void emNivel( ArvoreAVLCV<Tipo>.No<Tipo> no, List<Tipo> elementos ) {
        
        if ( no != null ) {
            
            Fila<ArvoreAVLCV<Tipo>.No<Tipo>> fila = new Fila<>();
            fila.enfileirar( no );

            while ( !fila.estaVazia() ) {

                ArvoreAVLCV<Tipo>.No<Tipo> atual = fila.desenfileirar();
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
    
    private static <Tipo extends Comparable<? super Tipo>> void preOrdemInverso( ArvoreAVLCV<Tipo>.No<Tipo> no, List<Tipo> elementos ) {
        
        if ( no != null ) {
            elementos.add( no.valor );
            preOrdemInverso( no.direita, elementos );
            preOrdemInverso( no.esquerda, elementos );
        }
        
    }
    
    private static <Tipo extends Comparable<? super Tipo>> void emOrdemInverso( ArvoreAVLCV<Tipo>.No<Tipo> no, List<Tipo> elementos ) {
        
        if ( no != null ) {
            emOrdemInverso( no.direita, elementos );
            elementos.add( no.valor );
            emOrdemInverso( no.esquerda, elementos );
        }
        
    }
    
    private static <Tipo extends Comparable<? super Tipo>> void posOrdemInverso( ArvoreAVLCV<Tipo>.No<Tipo> no, List<Tipo> elementos ) {
        
        if ( no != null ) {
            posOrdemInverso( no.direita, elementos );
            posOrdemInverso( no.esquerda, elementos );
            elementos.add( no.valor );
        }
        
    }
    
    private static <Tipo extends Comparable<? super Tipo>> void emNivelInverso( ArvoreAVLCV<Tipo>.No<Tipo> no, List<Tipo> elementos ) {
        
        if ( no != null ) {
            
            Fila<ArvoreAVLCV<Tipo>.No<Tipo>> fila = new Fila<>();
            Pilha<ArvoreAVLCV<Tipo>.No<Tipo>> pilha = new Pilha<>();
            fila.enfileirar( no );

            while ( !fila.estaVazia() ) {

                ArvoreAVLCV<Tipo>.No<Tipo> atual = fila.desenfileirar();
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
