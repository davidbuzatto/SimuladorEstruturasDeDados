/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gui.desenho.estruturas;

import estruturas.HeapMinimo;
import estruturas.algoritmos.arvores.TipoPercursoArvores;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe utilizada no simulador de heap mínimo.
 * 
 * Esta classe processa um heap mínimo, gerando os dados
 * necessários ao simulador em cada nó. Esta classe gera um clone do heap mínimo.
 * 
 * @author David Buzatto
 */
public class HeapMinimoAnotado<Tipo extends Comparable<? super Tipo>> {
        
    /*
     * Classe interna que representa os nós do heap mínimo anotado.
     * É pública para poder acessar os dados externamente no simulador.
     */
    public class NoAnotado<Tipo> {

        // valor
        public Tipo valor;

        // ponteiros
        public NoAnotado<Tipo> esquerda;
        public NoAnotado<Tipo> direita;
        public NoAnotado<Tipo> pai;

        // atributos dos nós
        public boolean folha;
        public int nivel;
        public int rank;
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
            return "NoAnotado{" + "valor=" + valor + ", folha=" + folha + ", nivel=" + nivel + ", rank=" + rank + ", ehRaiz=" + ehRaiz + ", pai=" + (pai != null ? pai.valor : pai) + '}';
        }

    }

    // raiz do heap mínimo anotado
    private NoAnotado<Tipo> raiz;

    // contadores usados no processamento
    private int contadorRank = 0;
    private int contadorNivel = -1;

    /**
     * Cria um heap mínimo anotado com base em um heap mínimo passado.
     * @param hm Heap mínimo que será usada como base.
     */
    public HeapMinimoAnotado( HeapMinimo<Tipo> hm ) {
        copiar( hm );
        processar( raiz, null );
    }

    /**
     * Copia os dados do heap mínimo, criando uma outra árvore
     * com os mesmos dados. É o primeiro passo da construção.
     * 
     * @param hm Heap mínimo original.
     */
    private void copiar( HeapMinimo<Tipo> hm ) {

        List<NoAnotado<Tipo>> nos = new ArrayList<>();
        nos.add( null );
        
        boolean primeiro = true;
        
        for ( Tipo item : hm ) {
            
            NoAnotado<Tipo> novoNo = new NoAnotado<>();
            novoNo.valor = item;
            
            if ( primeiro ) {
                raiz = novoNo;
                primeiro = false;
            }
            
            nos.add( novoNo );
            
        }
        
        int n = nos.size() - 1;
        
        for ( int i = 1; i < nos.size(); i++ ) {
            
            NoAnotado<Tipo> no = nos.get( i );
            
            no.esquerda = i * 2 <= n ? nos.get( i * 2 ) : null;
            no.direita = i * 2 + 1 <= n ? nos.get( i * 2 + 1 ) : null;
            
        }

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
