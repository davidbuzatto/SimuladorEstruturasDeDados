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
    public static 
        <TipoChave extends Comparable<? super TipoChave>, TipoValor> 
        Iterable<ArvoreAVLCV<TipoChave, TipoValor>.No<TipoChave, TipoValor>> 
        percorrer( ArvoreAVLCV<TipoChave, TipoValor> aavl, TipoPercursoArvores tipo ) {
        
        List<ArvoreAVLCV<TipoChave, TipoValor>.No<TipoChave, TipoValor>> elementos = new ArrayList<>();
        
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
    private static 
        <TipoChave extends Comparable<? super TipoChave>, TipoValor> 
        void preOrdem( 
                ArvoreAVLCV<TipoChave, TipoValor>.No<TipoChave, TipoValor> no, 
                List<ArvoreAVLCV<TipoChave, TipoValor>.No<TipoChave, TipoValor>> elementos ) {
        
        if ( no != null ) {
            elementos.add( no );
            preOrdem( no.esquerda, elementos );
            preOrdem( no.direita, elementos );
        }
        
    }
    
    private static 
        <TipoChave extends Comparable<? super TipoChave>, TipoValor> 
        void emOrdem( 
                ArvoreAVLCV<TipoChave, TipoValor>.No<TipoChave, TipoValor> no, 
                List<ArvoreAVLCV<TipoChave, TipoValor>.No<TipoChave, TipoValor>> elementos ) {
        
        if ( no != null ) {
            emOrdem( no.esquerda, elementos );
            elementos.add( no );
            emOrdem( no.direita, elementos );
        }
        
    }
    
    private static 
        <TipoChave extends Comparable<? super TipoChave>, TipoValor>  
        void posOrdem( 
                ArvoreAVLCV<TipoChave, TipoValor>.No<TipoChave, TipoValor> no, 
                List<ArvoreAVLCV<TipoChave, TipoValor>.No<TipoChave, TipoValor>> elementos ) {
        
        if ( no != null ) {
            posOrdem( no.esquerda, elementos );
            posOrdem( no.direita, elementos );
            elementos.add( no );
        }
        
    }
    
    private static 
        <TipoChave extends Comparable<? super TipoChave>, TipoValor>  
        void emNivel( 
                ArvoreAVLCV<TipoChave, TipoValor>.No<TipoChave, TipoValor> no, 
                List<ArvoreAVLCV<TipoChave, TipoValor>.No<TipoChave, TipoValor>> elementos ) {
        
        if ( no != null ) {
            
            Fila<ArvoreAVLCV<TipoChave, TipoValor>.No<TipoChave, TipoValor>> fila = new Fila<>();
            fila.enfileirar( no );

            while ( !fila.estaVazia() ) {

                ArvoreAVLCV<TipoChave, TipoValor>.No<TipoChave, TipoValor> atual = fila.desenfileirar();
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
                ArvoreAVLCV<TipoChave, TipoValor>.No<TipoChave, TipoValor> no, 
                List<ArvoreAVLCV<TipoChave, TipoValor>.No<TipoChave, TipoValor>> elementos ) {
        
        if ( no != null ) {
            elementos.add( no );
            preOrdemInverso( no.direita, elementos );
            preOrdemInverso( no.esquerda, elementos );
        }
        
    }
    
    private static 
        <TipoChave extends Comparable<? super TipoChave>, TipoValor>  
        void emOrdemInverso( 
                ArvoreAVLCV<TipoChave, TipoValor>.No<TipoChave, TipoValor> no, 
                List<ArvoreAVLCV<TipoChave, TipoValor>.No<TipoChave, TipoValor>> elementos ) {
        
        if ( no != null ) {
            emOrdemInverso( no.direita, elementos );
            elementos.add( no );
            emOrdemInverso( no.esquerda, elementos );
        }
        
    }
    
    private static 
        <TipoChave extends Comparable<? super TipoChave>, TipoValor> 
        void posOrdemInverso( 
                ArvoreAVLCV<TipoChave, TipoValor>.No<TipoChave, TipoValor> no, 
                List<ArvoreAVLCV<TipoChave, TipoValor>.No<TipoChave, TipoValor>> elementos ) {
        
        if ( no != null ) {
            posOrdemInverso( no.direita, elementos );
            posOrdemInverso( no.esquerda, elementos );
            elementos.add( no );
        }
        
    }
    
    private static 
        <TipoChave extends Comparable<? super TipoChave>, TipoValor> 
        void emNivelInverso(  
                ArvoreAVLCV<TipoChave, TipoValor>.No<TipoChave, TipoValor> no, 
                List<ArvoreAVLCV<TipoChave, TipoValor>.No<TipoChave, TipoValor>> elementos ) {
        
        if ( no != null ) {
            
            Fila<ArvoreAVLCV<TipoChave, TipoValor>.No<TipoChave, TipoValor>> fila = new Fila<>();
            Pilha<ArvoreAVLCV<TipoChave, TipoValor>.No<TipoChave, TipoValor>> pilha = new Pilha<>();
            fila.enfileirar( no );

            while ( !fila.estaVazia() ) {

                ArvoreAVLCV<TipoChave, TipoValor>.No<TipoChave, TipoValor> atual = fila.desenfileirar();
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
