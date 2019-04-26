/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package estruturas.algoritmos.arvores;

import estruturas.ArvoreVermelhoPretoPre;
import estruturas.ArvoreVermelhoPretoPre;
import estruturas.Fila;
import estruturas.Pilha;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementação dos percursos das árvores vermelho-preto.
 * 
 * @author David Buzatto
 */
public class PercursosArvoreVermelhoPreto {
    
    /**
     * Retorna uma lista contendo os ítens da árvore na ordem do percurso
     * especificado.
     * 
     * @param <Tipo> Tipo do valor.
     * @param avp Árvore que será percorrida.
     * @param tipo Tipo do percurso a ser executado.
     * @return Lista de elementos com os nós na ordem do percurso executado.
     */
    public static <Tipo extends Comparable<? super Tipo>> Iterable<ArvoreVermelhoPretoPre<Tipo>.No<Tipo>> percorrer( 
            ArvoreVermelhoPretoPre<Tipo> avp, TipoPercursoArvores tipo ) {
        
        List<ArvoreVermelhoPretoPre<Tipo>.No<Tipo>> elementos = new ArrayList<>();
        
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
    private static <Tipo extends Comparable<? super Tipo>> void preOrdem( 
            ArvoreVermelhoPretoPre<Tipo>.No<Tipo> no, 
            List<ArvoreVermelhoPretoPre<Tipo>.No<Tipo>> elementos ) {
        
        if ( no != null ) {
            elementos.add( no );
            preOrdem( no.esquerda, elementos );
            preOrdem( no.direita, elementos );
        }
        
    }
    
    private static <Tipo extends Comparable<? super Tipo>> void emOrdem( 
            ArvoreVermelhoPretoPre<Tipo>.No<Tipo> no, 
            List<ArvoreVermelhoPretoPre<Tipo>.No<Tipo>> elementos ) {
        
        if ( no != null ) {
            emOrdem( no.esquerda, elementos );
            elementos.add( no );
            emOrdem( no.direita, elementos );
        }
        
    }
    
    private static <Tipo extends Comparable<? super Tipo>> void posOrdem( 
            ArvoreVermelhoPretoPre<Tipo>.No<Tipo> no, 
            List<ArvoreVermelhoPretoPre<Tipo>.No<Tipo>> elementos ) {
        
        if ( no != null ) {
            posOrdem( no.esquerda, elementos );
            posOrdem( no.direita, elementos );
            elementos.add( no );
        }
        
    }
    
    private static <Tipo extends Comparable<? super Tipo>> void emNivel( 
            ArvoreVermelhoPretoPre<Tipo>.No<Tipo> no, 
            List<ArvoreVermelhoPretoPre<Tipo>.No<Tipo>> elementos ) {
        
        if ( no != null ) {
            
            Fila<ArvoreVermelhoPretoPre<Tipo>.No<Tipo>> fila = new Fila<>();
            fila.enfileirar( no );

            while ( !fila.estaVazia() ) {

                ArvoreVermelhoPretoPre<Tipo>.No<Tipo> atual = fila.desenfileirar();
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
    
    private static <Tipo extends Comparable<? super Tipo>> void preOrdemInverso( 
            ArvoreVermelhoPretoPre<Tipo>.No<Tipo> no, 
            List<ArvoreVermelhoPretoPre<Tipo>.No<Tipo>> elementos ) {
        
        if ( no != null ) {
            elementos.add( no );
            preOrdemInverso( no.direita, elementos );
            preOrdemInverso( no.esquerda, elementos );
        }
        
    }
    
    private static <Tipo extends Comparable<? super Tipo>> void emOrdemInverso( 
            ArvoreVermelhoPretoPre<Tipo>.No<Tipo> no, 
            List<ArvoreVermelhoPretoPre<Tipo>.No<Tipo>> elementos ) {
        
        if ( no != null ) {
            emOrdemInverso( no.direita, elementos );
            elementos.add( no );
            emOrdemInverso( no.esquerda, elementos );
        }
        
    }
    
    private static <Tipo extends Comparable<? super Tipo>> void posOrdemInverso( 
            ArvoreVermelhoPretoPre<Tipo>.No<Tipo> no, 
            List<ArvoreVermelhoPretoPre<Tipo>.No<Tipo>> elementos ) {
        
        if ( no != null ) {
            posOrdemInverso( no.direita, elementos );
            posOrdemInverso( no.esquerda, elementos );
            elementos.add( no );
        }
        
    }
    
    private static <Tipo extends Comparable<? super Tipo>> void emNivelInverso( 
            ArvoreVermelhoPretoPre<Tipo>.No<Tipo> no, 
            List<ArvoreVermelhoPretoPre<Tipo>.No<Tipo>> elementos ) {
        
        if ( no != null ) {
            
            Fila<ArvoreVermelhoPretoPre<Tipo>.No<Tipo>> fila = new Fila<>();
            Pilha<ArvoreVermelhoPretoPre<Tipo>.No<Tipo>> pilha = new Pilha<>();
            fila.enfileirar( no );

            while ( !fila.estaVazia() ) {

                ArvoreVermelhoPretoPre<Tipo>.No<Tipo> atual = fila.desenfileirar();
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
