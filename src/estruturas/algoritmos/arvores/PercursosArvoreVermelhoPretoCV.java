/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package estruturas.algoritmos.arvores;

import estruturas.ArvoreVermelhoPretoCV;
import estruturas.Fila;
import estruturas.Pilha;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementação dos percursos das árvores vermelho-preto.
 * 
 * @author David Buzatto
 */
public class PercursosArvoreVermelhoPretoCV {
    
    /**
     * Retorna uma lista contendo os ítens da árvore na ordem do percurso
     * especificado.
     * 
     * @param <TipoChave> Tipo da chave da árvore.
     * @param <TipoValor> Tipo do valor associado a uma chave da árvore.
     * @param avp Árvore que será percorrida.
     * @param tipo Tipo do percurso a ser executado.
     * @return Lista de elementos com os nós na ordem do percurso executado.
     */
    public static <TipoChave extends Comparable<? super TipoChave>, TipoValor> Iterable<ArvoreVermelhoPretoCV<TipoChave, TipoValor>.No<TipoChave, TipoValor>> percorrer( 
            ArvoreVermelhoPretoCV<TipoChave, TipoValor> avp, TipoPercursoArvores tipo ) {
        
        List<ArvoreVermelhoPretoCV<TipoChave, TipoValor>.No<TipoChave, TipoValor>> elementos = new ArrayList<>();
        
        switch ( tipo ) {
            case PRE_ORDEM:
                preOrdem( avp.getRaiz(), elementos );
                break;
            case EM_ORDEM:
                emOrdem( avp.getRaiz(), elementos );
                break;
            case POS_ORDEM:
                posOrdem( avp.getRaiz(), elementos );
                break;
            case EM_NIVEL:
                emNivel( avp.getRaiz(), elementos );
                break;
            case PRE_ORDEM_INVERSO:
                preOrdemInverso( avp.getRaiz(), elementos );
                break;
            case EM_ORDEM_INVERSO:
                emOrdemInverso( avp.getRaiz(), elementos );
                break;
            case POS_ORDEM_INVERSO:
                posOrdemInverso( avp.getRaiz(), elementos );
                break;
            case EM_NIVEL_INVERSO:
                emNivelInverso( avp.getRaiz(), elementos );
                break;
        }
        
        return elementos;
        
    }
    
    /*
     * Métodos privados para os percursos.
     */
    private static <TipoChave extends Comparable<? super TipoChave>, TipoValor> void preOrdem( 
            ArvoreVermelhoPretoCV<TipoChave, TipoValor>.No<TipoChave, TipoValor> no, 
            List<ArvoreVermelhoPretoCV<TipoChave, TipoValor>.No<TipoChave, TipoValor>> elementos ) {
        
        if ( no != null ) {
            elementos.add( no );
            preOrdem( no.esquerda, elementos );
            preOrdem( no.direita, elementos );
        }
        
    }
    
    private static <TipoChave extends Comparable<? super TipoChave>, TipoValor> void emOrdem( 
            ArvoreVermelhoPretoCV<TipoChave, TipoValor>.No<TipoChave, TipoValor> no, 
            List<ArvoreVermelhoPretoCV<TipoChave, TipoValor>.No<TipoChave, TipoValor>> elementos ) {
        
        if ( no != null ) {
            emOrdem( no.esquerda, elementos );
            elementos.add( no );
            emOrdem( no.direita, elementos );
        }
        
    }
    
    private static <TipoChave extends Comparable<? super TipoChave>, TipoValor> void posOrdem( 
            ArvoreVermelhoPretoCV<TipoChave, TipoValor>.No<TipoChave, TipoValor> no, 
            List<ArvoreVermelhoPretoCV<TipoChave, TipoValor>.No<TipoChave, TipoValor>> elementos ) {
        
        if ( no != null ) {
            posOrdem( no.esquerda, elementos );
            posOrdem( no.direita, elementos );
            elementos.add( no );
        }
        
    }
    
    private static <TipoChave extends Comparable<? super TipoChave>, TipoValor> void emNivel( 
            ArvoreVermelhoPretoCV<TipoChave, TipoValor>.No<TipoChave, TipoValor> no, 
            List<ArvoreVermelhoPretoCV<TipoChave, TipoValor>.No<TipoChave, TipoValor>> elementos ) {
        
        if ( no != null ) {
            
            Fila<ArvoreVermelhoPretoCV<TipoChave, TipoValor>.No<TipoChave, TipoValor>> fila = new Fila<>();
            fila.enfileirar( no );

            while ( !fila.estaVazia() ) {

                ArvoreVermelhoPretoCV<TipoChave, TipoValor>.No<TipoChave, TipoValor> atual = fila.desenfileirar();
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
    
    private static <TipoChave extends Comparable<? super TipoChave>, TipoValor> void preOrdemInverso( 
            ArvoreVermelhoPretoCV<TipoChave, TipoValor>.No<TipoChave, TipoValor> no, 
            List<ArvoreVermelhoPretoCV<TipoChave, TipoValor>.No<TipoChave, TipoValor>> elementos ) {
        
        if ( no != null ) {
            elementos.add( no );
            preOrdemInverso( no.direita, elementos );
            preOrdemInverso( no.esquerda, elementos );
        }
        
    }
    
    private static <TipoChave extends Comparable<? super TipoChave>, TipoValor> void emOrdemInverso( 
            ArvoreVermelhoPretoCV<TipoChave, TipoValor>.No<TipoChave, TipoValor> no, 
            List<ArvoreVermelhoPretoCV<TipoChave, TipoValor>.No<TipoChave, TipoValor>> elementos ) {
        
        if ( no != null ) {
            emOrdemInverso( no.direita, elementos );
            elementos.add( no );
            emOrdemInverso( no.esquerda, elementos );
        }
        
    }
    
    private static <TipoChave extends Comparable<? super TipoChave>, TipoValor> void posOrdemInverso( 
            ArvoreVermelhoPretoCV<TipoChave, TipoValor>.No<TipoChave, TipoValor> no, 
            List<ArvoreVermelhoPretoCV<TipoChave, TipoValor>.No<TipoChave, TipoValor>> elementos ) {
        
        if ( no != null ) {
            posOrdemInverso( no.direita, elementos );
            posOrdemInverso( no.esquerda, elementos );
            elementos.add( no );
        }
        
    }
    
    private static <TipoChave extends Comparable<? super TipoChave>, TipoValor> void emNivelInverso( 
            ArvoreVermelhoPretoCV<TipoChave, TipoValor>.No<TipoChave, TipoValor> no, 
            List<ArvoreVermelhoPretoCV<TipoChave, TipoValor>.No<TipoChave, TipoValor>> elementos ) {
        
        if ( no != null ) {
            
            Fila<ArvoreVermelhoPretoCV<TipoChave, TipoValor>.No<TipoChave, TipoValor>> fila = new Fila<>();
            Pilha<ArvoreVermelhoPretoCV<TipoChave, TipoValor>.No<TipoChave, TipoValor>> pilha = new Pilha<>();
            fila.enfileirar( no );

            while ( !fila.estaVazia() ) {

                ArvoreVermelhoPretoCV<TipoChave, TipoValor>.No<TipoChave, TipoValor> atual = fila.desenfileirar();
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
