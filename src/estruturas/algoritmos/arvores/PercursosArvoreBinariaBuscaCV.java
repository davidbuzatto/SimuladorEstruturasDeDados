/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package estruturas.algoritmos.arvores;

import estruturas.ArvoreBinariaBusca;
import estruturas.ArvoreBinariaBuscaCV;
import estruturas.ArvoreBinariaBuscaCV.No;
import estruturas.Fila;
import estruturas.Pilha;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementação dos percursos das árvores binárias de busca chave->valor.
 * 
 * @author David Buzatto
 */
public class PercursosArvoreBinariaBuscaCV {
    
    /**
     * Retorna uma lista contendo os ítens da árvore na ordem do percurso
     * especificado.
     * 
     * @param <Tipo> Tipo de dados da árvore.
     * @param abb Árvore que será percorrida.
     * @param tipo Tipo do percurso a ser executado.
     * @return Lista de elementos na ordem do percurso executado.
     */
    public static 
        <TipoChave extends Comparable<? super TipoChave>, TipoValor> 
        Iterable<ArvoreBinariaBuscaCV<TipoChave, TipoValor>.No<TipoChave, TipoValor>> 
        percorrer( ArvoreBinariaBuscaCV<TipoChave, TipoValor> abb, TipoPercursoArvores tipo ) {
        
        List<ArvoreBinariaBuscaCV<TipoChave, TipoValor>.No<TipoChave, TipoValor>> elementos = new ArrayList<>();
        
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
    private static 
        <TipoChave extends Comparable<? super TipoChave>, TipoValor> 
        void preOrdem( 
                ArvoreBinariaBuscaCV<TipoChave, TipoValor>.No<TipoChave, TipoValor> no, 
                List<ArvoreBinariaBuscaCV<TipoChave, TipoValor>.No<TipoChave, TipoValor>> elementos ) {
        
        if ( no != null ) {
            elementos.add( no );
            preOrdem( no.esquerda, elementos );
            preOrdem( no.direita, elementos );
        }
        
    }
    
    private static 
        <TipoChave extends Comparable<? super TipoChave>, TipoValor> 
        void emOrdem( 
                ArvoreBinariaBuscaCV<TipoChave, TipoValor>.No<TipoChave, TipoValor> no, 
                List<ArvoreBinariaBuscaCV<TipoChave, TipoValor>.No<TipoChave, TipoValor>> elementos ) {
        
        if ( no != null ) {
            emOrdem( no.esquerda, elementos );
            elementos.add( no );
            emOrdem( no.direita, elementos );
        }
        
    }
    
    private static 
        <TipoChave extends Comparable<? super TipoChave>, TipoValor> 
        void posOrdem( 
                ArvoreBinariaBuscaCV<TipoChave, TipoValor>.No<TipoChave, TipoValor> no, 
                List<ArvoreBinariaBuscaCV<TipoChave, TipoValor>.No<TipoChave, TipoValor>> elementos ) {
        
        if ( no != null ) {
            posOrdem( no.esquerda, elementos );
            posOrdem( no.direita, elementos );
            elementos.add( no );
        }
        
    }
    
    private static 
        <TipoChave extends Comparable<? super TipoChave>, TipoValor> 
        void emNivel( 
                ArvoreBinariaBuscaCV<TipoChave, TipoValor>.No<TipoChave, TipoValor> no, 
                List<ArvoreBinariaBuscaCV<TipoChave, TipoValor>.No<TipoChave, TipoValor>> elementos ) {
        
        if ( no != null ) {
            
            Fila<ArvoreBinariaBuscaCV<TipoChave, TipoValor>.No<TipoChave, TipoValor>> fila = new Fila<>();
            fila.enfileirar( no );

            while ( !fila.estaVazia() ) {

                ArvoreBinariaBuscaCV<TipoChave, TipoValor>.No<TipoChave, TipoValor> atual = fila.desenfileirar();
                elementos.add( atual );

                if ( atual.esquerda != null ) {
                    fila.enfileirar( atual.esquerda );
                }

                if ( atual.direita != null ) {
                    fila.enfileirar( atual.direita );
                }

            }
            
        }
        
    }
    
    private static 
        <TipoChave extends Comparable<? super TipoChave>, TipoValor>
        void preOrdemInverso( 
                ArvoreBinariaBuscaCV<TipoChave, TipoValor>.No<TipoChave, TipoValor> no, 
                List<ArvoreBinariaBuscaCV<TipoChave, TipoValor>.No<TipoChave, TipoValor>> elementos ) {
        
        if ( no != null ) {
            elementos.add( no );
            preOrdemInverso( no.direita, elementos );
            preOrdemInverso( no.esquerda, elementos );
        }
        
    }
    
    private static 
        <TipoChave extends Comparable<? super TipoChave>, TipoValor>
        void emOrdemInverso( 
                ArvoreBinariaBuscaCV<TipoChave, TipoValor>.No<TipoChave, TipoValor> no, 
                List<ArvoreBinariaBuscaCV<TipoChave, TipoValor>.No<TipoChave, TipoValor>> elementos ) {
        
        if ( no != null ) {
            emOrdemInverso( no.direita, elementos );
            elementos.add( no );
            emOrdemInverso( no.esquerda, elementos );
        }
        
    }
    
    private static 
        <TipoChave extends Comparable<? super TipoChave>, TipoValor>
        void posOrdemInverso( 
                ArvoreBinariaBuscaCV<TipoChave, TipoValor>.No<TipoChave, TipoValor> no, 
                List<ArvoreBinariaBuscaCV<TipoChave, TipoValor>.No<TipoChave, TipoValor>> elementos ) {
        
        if ( no != null ) {
            posOrdemInverso( no.direita, elementos );
            posOrdemInverso( no.esquerda, elementos );
            elementos.add( no );
        }
        
    }
    
    private static 
        <TipoChave extends Comparable<? super TipoChave>, TipoValor>
        void emNivelInverso( 
                ArvoreBinariaBuscaCV<TipoChave, TipoValor>.No<TipoChave, TipoValor> no, 
                List<ArvoreBinariaBuscaCV<TipoChave, TipoValor>.No<TipoChave, TipoValor>> elementos ) {
        
        if ( no != null ) {
            
            Fila<ArvoreBinariaBuscaCV<TipoChave, TipoValor>.No<TipoChave, TipoValor>> fila = new Fila<>();
            Pilha<ArvoreBinariaBuscaCV<TipoChave, TipoValor>.No<TipoChave, TipoValor>> pilha = new Pilha<>();
            fila.enfileirar( no );

            while ( !fila.estaVazia() ) {

                ArvoreBinariaBuscaCV<TipoChave, TipoValor>.No<TipoChave, TipoValor> atual = fila.desenfileirar();
                pilha.empilhar( atual );

                if ( atual.esquerda != null ) {
                    fila.enfileirar( atual.esquerda );
                }

                if ( atual.direita != null ) {
                    fila.enfileirar( atual.direita );
                }

            }

            while ( !pilha.estaVazia() ) {
                elementos.add( pilha.desempilhar() );
            }
        
        }
        
    }
    
}
