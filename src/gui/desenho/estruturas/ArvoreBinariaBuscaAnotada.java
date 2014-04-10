/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gui.desenho.estruturas;

import estruturas.ArvoreBinariaBusca;
import estruturas.Fila;
import estruturas.algoritmos.arvores.TipoPercursoArvores;
import estruturas.Pilha;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe utilizada no simulador de árvores binárias de busca.
 * 
 * Esta classe processa uma árvore binária de busca, gerando os dados
 * necessários ao simulador em cada nó. Esta classe gera um clone da árvore
 * original.
 * 
 * @author David Buzatto
 */
public class ArvoreBinariaBuscaAnotada<Tipo extends Comparable> {
        
    /*
     * Classe interna que representa os nós da árvore anotada.
     * É pública para poder acessar os dados externamente no simulador.
     */
    public class NoAnotado<Tipo> {

        // valor
        public Tipo valor;

        // ponteiros
        public NoAnotado<Tipo> esquerda;
        public NoAnotado<Tipo> direita;
        public NoAnotado<Tipo> pai;

        // atributos dos nóis
        public boolean folha;
        public int nivel;
        public int rank;
        public int grau;
        public boolean ehRaiz;
        
        // posicionamento
        public int xIni;
        public int xFim;
        public int xCentro;
        public int yIni;
        public int yFim;
        public int yCentro;

        @Override
        public String toString() {
            return "NoAnotado{" + "valor=" + valor + ", folha=" + folha + ", nivel=" + nivel + ", rank=" + rank + ", grau=" + grau + ", ehRaiz=" + ehRaiz + ", pai=" + (pai != null ? pai.valor : pai) + '}';
        }

    }

    // raiz da árvore anotada
    private NoAnotado<Tipo> raiz;
    
    // altura e grau da árvore anotada
    private int altura;
    private int grau;

    // contadores usados no processamento
    private int contadorRank = 0;
    private int contadorNivel = -1;

    /**
     * Cria uma árvore anotada com base em uma árvore binária de busca passada.
     * @param abb Árvore binária de busca que será usada como base.
     */
    public ArvoreBinariaBuscaAnotada( ArvoreBinariaBusca<Tipo> abb ) {
        copiar( abb, abb.getRaiz() );
        processar( raiz, null );
    }

    /**
     * Copia os dados da árvore binária de busca, criando uma outra árvore
     * com os mesmos dados. É o primeiro passo da construção.
     * 
     * @param abb Árvore binária de busca original.
     * @param no Nó que se quer iniciar a cópia (nó da árvore original).
     * @return Um nó anotado para a construção recursiva.
     */
    private NoAnotado<Tipo> copiar( ArvoreBinariaBusca<Tipo> abb, ArvoreBinariaBusca<Tipo>.No<Tipo> no ) {

        NoAnotado<Tipo> novoNo = null;

        if ( no != null ) {

            novoNo = new NoAnotado<>();
            novoNo.valor = no.valor;

            if ( no == abb.getRaiz() ) {
                raiz = novoNo;
            }

            novoNo.esquerda = copiar( abb, no.esquerda );
            novoNo.direita = copiar( abb, no.direita );

        }

        return novoNo;

    }

    /**
     * Processa a árvore construída, gerando as propriedades de cada nó
     * bem como as propriedades da árvore.
     * 
     * @param no Nó inicial onde será feito o processamento (normalente a raiz
     * da árvore original).
     */
    private void processar( NoAnotado<Tipo> no, NoAnotado<Tipo> pai ) {

        contadorNivel++;

        if ( no != null ) {

            processar( no.esquerda, no );

            no.pai = pai;
            no.nivel = contadorNivel;
            no.rank = contadorRank++;

            if ( no.esquerda == no.direita ) {
                no.folha = true;
            }

            if ( no.esquerda != null ) {
                no.grau++;
            }

            if ( no.direita != null ) {
                no.grau++;
            }

            if ( altura < contadorNivel ) {
                altura = contadorNivel;
            }

            if ( grau < no.grau ) {
                grau = no.grau;
            }

            if ( no == raiz ) {
                no.ehRaiz = true;
            }

            processar( no.direita, no );

        }

        contadorNivel--;

    }

    /**
     * Retorna uma lista contendo os nós anotados da árvore na ordem do percurso
     * especificado.
     * 
     * @param tipo Tipo do percurso a ser executado.
     * @return Lista de nós anotados na ordem do percurso executado.
     */
    public Iterable<NoAnotado<Tipo>> percorrer( TipoPercursoArvores tipo ) {

        List<NoAnotado<Tipo>> elementos = new ArrayList<>();
        
        switch ( tipo ) {
            case PRE_ORDEM:
                preOrdem( raiz, elementos );
                break;
            case EM_ORDEM:
                emOrdem( raiz, elementos );
                break;
            case POS_ORDEM:
                posOrdem( raiz, elementos );
                break;
            case EM_NIVEL:
                emNivel( raiz, elementos );
                break;
            case PRE_ORDEM_INVERSO:
                preOrdemInverso( raiz, elementos );
                break;
            case EM_ORDEM_INVERSO:
                emOrdemInverso( raiz, elementos );
                break;
            case POS_ORDEM_INVERSO:
                posOrdemInverso( raiz, elementos );
                break;
            case EM_NIVEL_INVERSO:
                emNivelInverso( raiz, elementos );
                break;
        }


        return elementos;

    }

    /*
     * Métodos privados para os percursos.
     */
    private void preOrdem( NoAnotado<Tipo> no, List<NoAnotado<Tipo>> elementos ) {

        if ( no != null ) {
            elementos.add( no );
            preOrdem( no.esquerda, elementos );
            preOrdem( no.direita, elementos );
        }

    }

    private void emOrdem( NoAnotado<Tipo> no, List<NoAnotado<Tipo>> elementos ) {

        if ( no != null ) {
            emOrdem( no.esquerda, elementos );
            elementos.add( no );
            emOrdem( no.direita, elementos );
        }

    }

    private void posOrdem( NoAnotado<Tipo> no, List<NoAnotado<Tipo>> elementos ) {

        if ( no != null ) {
            posOrdem( no.esquerda, elementos );
            posOrdem( no.direita, elementos );
            elementos.add( no );
        }

    }

    private void emNivel( NoAnotado<Tipo> no, List<NoAnotado<Tipo>> elementos ) {

        if ( no != null ) {

            Fila<NoAnotado<Tipo>> fila = new Fila<>();
            fila.enfileirar( no );

            while ( !fila.estaVazia() ) {

                NoAnotado<Tipo> atual = fila.desenfileirar();
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

    private void preOrdemInverso( NoAnotado<Tipo> no, List<NoAnotado<Tipo>> elementos ) {

        if ( no != null ) {
            elementos.add( no );
            preOrdemInverso( no.direita, elementos );
            preOrdemInverso( no.esquerda, elementos );
        }

    }

    private void emOrdemInverso( NoAnotado<Tipo> no, List<NoAnotado<Tipo>> elementos ) {

        if ( no != null ) {
            emOrdemInverso( no.direita, elementos );
            elementos.add( no );
            emOrdemInverso( no.esquerda, elementos );
        }

    }

    private void posOrdemInverso( NoAnotado<Tipo> no, List<NoAnotado<Tipo>> elementos ) {

        if ( no != null ) {
            posOrdemInverso( no.direita, elementos );
            posOrdemInverso( no.esquerda, elementos );
            elementos.add( no );
        }

    }

    private void emNivelInverso( NoAnotado<Tipo> no, List<NoAnotado<Tipo>> elementos ) {

        if ( no != null ) {

            Fila<NoAnotado<Tipo>> fila = new Fila<>();
            Pilha<NoAnotado<Tipo>> pilha = new Pilha<>();
            fila.enfileirar( no );

            while ( !fila.estaVazia() ) {

                NoAnotado<Tipo> atual = fila.desenfileirar();
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

    public int getAltura() {
        return altura;
    }

    public int getGrau() {
        return grau;
    }

    public NoAnotado<Tipo> getRaiz() {
        return raiz;
    }

    public boolean estaVazia() {
        return raiz == null;
    }

    /**
     * Cria a representação em string da árvore.
     * @return Representação em string desta árvore.
     */
    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();
        sb.append( "Altura: " ).append( altura ).append( "\n" );
        sb.append( "Grau: " ).append( grau ).append( "\n\n" );

        for ( NoAnotado<Tipo> no : percorrer( TipoPercursoArvores.PRE_ORDEM ) ) {
            for ( int i = 0; i < no.nivel; i++ ) {
                if ( i == no.nivel - 1 ) {
                    sb.append( "|--" );
                } else {
                    sb.append( "|  " );
                }
            }
            if ( no.ehRaiz ) {
                sb.append( no.valor ).append( " <- raiz\n" );
            } else {
                sb.append( no.valor ).append( "\n" );
            }
        }

        return sb.toString();

    }

}
