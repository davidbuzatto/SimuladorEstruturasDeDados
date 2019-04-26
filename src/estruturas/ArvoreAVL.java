/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estruturas;

import estruturas.algoritmos.arvores.PercursosArvoreAVL;
import estruturas.algoritmos.arvores.TipoPercursoArvores;
import gui.desenho.estruturas.ArvoreAVLAnotada;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * Implementação de uma árvore AVL.
 * 
 * Baseado no código de:
 *     WEISS, M. A. Data Structures and Algorithm Analysis in Java. 3. ed. 
 *     Pearson Education: New Jersey, 2012. 614 p.
 * 
 * @author David Buzatto
 */
public class ArvoreAVL<Tipo extends Comparable<? super Tipo>> implements Iterable<Tipo> {

    /*
     * Classe interna que define os nós da árvore.
     * É pública para poder acessar a estrutura dos nós externamente
     * no simulador e nos percursos. Deveria ser privada.
     */
    public class No<Tipo> {
        
        public int altura;
        
        public Tipo valor;
        public No<Tipo> esquerda;
        public No<Tipo> direita;
        
    }
    
    // raiz da árvore
    private No<Tipo> raiz;
    
    // valor máximo na diferença de alturas de duas subárvores
    private static final int DESBALANCEAMENTO_PERMITIDO = 1;
    
    /**
     * Constrói uma Árvore AVL vazia.
     */
    public ArvoreAVL() {
        raiz = null;
    }

    /**
     * Insere um elemento na árvore. Elementos duplicados são ignorados.
     * 
     * @param valor Elemento a ser inserido.
     */
    public void inserir( Tipo valor ) {
        raiz = inserir( raiz, valor );
    }
    
    /**
     * Método privado para inserção recursiva na subárvore.
     *
     * @param no raiz da subárvore.
     * @param valor elemento que será inserido.
     * @return a nova raiz da subárvore.
     */
    private No<Tipo> inserir( No<Tipo> no, Tipo valor ) {
        
        if ( no == null ) {
            
            no = new No<Tipo>();
            no.valor = valor;
            no.esquerda = null;
            no.direita = null;
            no.altura = 0;
            
        }

        int comparacao = valor.compareTo( no.valor );

        if ( comparacao < 0 ) {
            no.esquerda = inserir( no.esquerda, valor );
        } else if ( comparacao > 0 ) {
            no.direita = inserir( no.direita, valor );
        }

        // não faz nada para duplicatas
        
        // balanceia a árvore
        return balancear( no );
        
    }
    
    /**
     * Verifica se um valor está contido na árvore.
     *
     * @param valor Valor a ser pesquisado.
     * @return true caso tenha encontrado, false caso contrário.
     */
    public boolean contem( Tipo valor ) {
        return contem( raiz, valor );
    }
    
    /**
     * Método privado para consulta na subárvore.
     *
     * @param no raiz da subárvore.
     * @param valor elemento que será consultado.
     * @return true caso tenha encontrado, false caso contrário.
     */
    private boolean contem( No<Tipo> no, Tipo valor ) {
        
        while ( no != null ) {
            
            int comparacao = valor.compareTo( no.valor );

            if ( comparacao == 0 ) {
                return true;
            } else if ( comparacao < 0 ) {
                no = no.esquerda;
            } else { // comparacao > 0
                no = no.direita;
            }
        }

        return false;
        
    }
    
    /**
     * Remove um elemento da árvore.
     * 
     * @param valor Valor a ser removido.
     */
    public void remover( Tipo valor ) {
        raiz = remover( raiz, valor );
    }

    /**
     * Método privado para remoção recursiva na subárvore.
     *
     * @param no raiz da subárvore.
     * @param valor elemento que será removido.
     * @return a nova raiz da subárvore.
     */
    private No<Tipo> remover( No<Tipo> no, Tipo valor ) {
        
        if ( no == null ) {
            return no;   // não encontrado
        }
        
        int comparacao = valor.compareTo( no.valor );

        if ( comparacao < 0 ) {
            no.esquerda = remover( no.esquerda, valor );
        } else if ( comparacao > 0 ) {
            no.direita = remover( no.direita, valor );
        } else if ( no.esquerda != null && no.direita != null ) { // dois filhos
            no.valor = encontrarMinimo( no.direita ).valor;
            no.direita = remover( no.direita, no.valor );
        } else {
            no = ( no.esquerda != null ) ? no.esquerda : no.direita;
        }
        
        return balancear( no );
        
    }

    /**
     * Encontra o menor item da árvore.
     *
     * @return o menor item ou null caso a árvore esteja vazia.
     */
    public Tipo encontrarMinimo() {
        
        if ( estaVazia() ) {
            return null;
        }
        
        return encontrarMinimo( raiz ).valor;
        
    }
    
    /**
     * Método privado para encontrar o menor item de uma subárvore.
     *
     * @param no raiz da subárvore
     * @return o nó que contém o menor item.
     */
    private No<Tipo> encontrarMinimo( No<Tipo> no ) {
        
        if ( no == null ) {
            return no;
        }

        while ( no.esquerda != null ) {
            no = no.esquerda;
        }
        
        return no;
        
    }

    /**
     * Encontra o maior item da árvore.
     *
     * @return o maior item ou null caso a árvore esteja vazia.
     */
    public Tipo encontrarMaximo() {
        
        if ( estaVazia() ) {
            return null;
        }
        
        return encontrarMaximo( raiz ).valor;
        
    }
    
    /**
     * Método privado para encontrar o maior item de uma subárvore.
     *
     * @param no raiz da subárvore
     * @return o nó que contém o maior item.
     */
    private No<Tipo> encontrarMaximo( No<Tipo> no ) {
        
        if ( no == null ) {
            return no;
        }

        while ( no.direita != null ) {
            no = no.direita;
        }
        
        return no;
        
    }

    /**
     * Esvazia a árvore.
     */
    public void esvaziar() {
        raiz = desalocar( raiz );
    }

    /*
     * Método privado para remoção de todos os itens de forma recursiva.
     */
    private No<Tipo> desalocar( No<Tipo> no ) {

        if ( no != null ) {
            no.esquerda = desalocar( no.esquerda );
            no.direita = desalocar( no.direita );
        }

        return null;

    }
    
    /**
     * Verifica se a árvore está vazia.
     * 
     * @return true se a árvore estiver vazia, false caso contrário.
     */
    public boolean estaVazia() {
        return raiz == null;
    }
    
    // Assume que no está balanceado ou está sendo balanceado
    private No<Tipo> balancear( No<Tipo> no ) {
        
        if ( no == null ) {
            return no;
        }

        if ( altura( no.esquerda ) - altura( no.direita ) > DESBALANCEAMENTO_PERMITIDO ) {
            if ( altura( no.esquerda.esquerda ) >= altura( no.esquerda.direita ) ) {
                no = rotacionarComFilhoEsquerdo( no );
            } else {
                no = rotacionarDuploComFilhoEsquerdo( no );
            }
        } else if ( altura( no.direita ) - altura( no.esquerda ) > DESBALANCEAMENTO_PERMITIDO ) {
            if ( altura( no.direita.direita ) >= altura( no.direita.esquerda ) ) {
                no = rotacionarComFilhoDireito( no );
            } else {
                no = rotacionarDuploComFilhoDireito( no );
            }
        }

        no.altura = Math.max( altura( no.esquerda ), altura( no.direita ) ) + 1;
        
        return no;
        
    }

    public void verificarBalanceamento() {
        verificarBalanceamento( raiz );
    }

    private int verificarBalanceamento( No<Tipo> no ) {
        
        if ( no == null ) {
            return -1;
        }

        if ( no != null ) {
            
            int alturaEsquerda = verificarBalanceamento( no.esquerda );
            int alturaDireita = verificarBalanceamento( no.direita );
            
            if ( Math.abs( altura( no.esquerda ) - altura( no.direita ) ) > 1 || 
                    altura( no.esquerda ) != alturaEsquerda || 
                    altura( no.direita ) != alturaDireita ) {
                System.out.println( "Desbalanceamento encontrado!" );
            }
            
        }

        return altura( no );
        
    }

    /**
     * Retorna a altura de um no ou -1 caso no seja nulo.
     */
    private int altura( No<Tipo> no ) {
        return no == null ? -1 : no.altura;
    }

    /**
     * Rotacionada um nó com filho à esquerda. Para as árvores AVL, essa é a 
     * rotação simples do caso 1. Atualiza as alturas e retorna a nova raiz.
     */
    private No<Tipo> rotacionarComFilhoEsquerdo( No<Tipo> k2 ) {
        No<Tipo> k1 = k2.esquerda;
        k2.esquerda = k1.direita;
        k1.direita = k2;
        k2.altura = Math.max( altura( k2.esquerda ), altura( k2.direita ) ) + 1;
        k1.altura = Math.max( altura( k1.esquerda ), k2.altura ) + 1;
        return k1;
    }

    /**
     * Rotacionada um nó com filho à direita. Para as árvores AVL, essa é a 
     * rotação simples do caso 4. Atualiza as alturas e retorna a nova raiz.
     */
    private No<Tipo> rotacionarComFilhoDireito( No<Tipo> k1 ) {
        No<Tipo> k2 = k1.direita;
        k1.direita = k2.esquerda;
        k2.esquerda = k1;
        k1.altura = Math.max( altura( k1.esquerda ), altura( k1.direita ) ) + 1;
        k2.altura = Math.max( altura( k2.direita ), k1.altura ) + 1;
        return k2;
    }

    /**
     * Realiza uma rotação dupla:
     *     1 - filho da esquerda com seu filho da direita;
     *     2 - nó k3 com seu novo filho à esquerda.
     * 
     * Para as árvores AVL, essa é a rotação dupla do caso 2.
     * Atualiza as alturas e retorna a nova raiz.
     */
    private No<Tipo> rotacionarDuploComFilhoEsquerdo( No<Tipo> k3 ) {
        k3.esquerda = rotacionarComFilhoDireito( k3.esquerda );
        return rotacionarComFilhoEsquerdo( k3 );
    }

    /**
     * Realiza uma rotação dupla:
     *     1 - filho da direita com seu filho da esquerda;
     *     2 - nó k1 com seu novo filho à direita.
     * 
     * Para as árvores AVL, essa é a rotação dupla do caso 3.
     * Atualiza as alturas e retorna a nova raiz.
     */
    private No<Tipo> rotacionarDuploComFilhoDireito( No<Tipo> k1 ) {
        k1.direita = rotacionarComFilhoEsquerdo( k1.direita );
        return rotacionarComFilhoDireito( k1 );
    }

    
    /**
     * Cria uma representação em String da árvore.
     * Esta representação apresenta os elementos na ordem do percurso em ordem.
     */
    @Override
    public String toString() {
        
        StringBuilder sb = new StringBuilder();
        
        if ( !estaVazia() ) {
            
            for ( Tipo valor : PercursosArvoreAVL.percorrer( this, TipoPercursoArvores.EM_ORDEM ) ) {
                
                if ( valor.equals( raiz.valor ) ) {
                    sb.append( valor ).append( " <- raiz\n" );
                } else {
                    sb.append( valor ).append( "\n" );
                }
                
            }
            
        } else {
            sb.append( "árvore vazia!\n" );
        }
        
        return sb.toString();
        
    }
    
    /**
     * Cria um iterador para a árvore, permitindo iterar por todos os elementos
     * da mesma, além de poder usar em um for each (for melhorado).
     * Este iterador percorre a árvore usando o percurso em ordem.
     */
    @Override
    public Iterator<Tipo> iterator() {
        return PercursosArvoreAVL.percorrer( this, TipoPercursoArvores.EM_ORDEM ).iterator();
    }
    
    /**
     * Método para obter o nó da raiz.
     * Não deveria existir, mas é necessário para o simulador.
     * 
     * @return Nó com a raiz da árvore.
     */
    public No<Tipo> getRaiz() {
        return raiz;
    }
    
    /**
     * Testes da árvore.
     * 
     * @param args
     */
    public static void main( String[] args ) {
        
        ArvoreAVL<Integer> aavl = new ArvoreAVL<>();
        
        aavl.inserir( 6 );
        System.out.println( aavl );
        aavl.inserir( 8 );
        System.out.println( aavl );
        aavl.inserir( 7 );
        System.out.println( aavl );
        aavl.inserir( 4 );
        System.out.println( aavl );
        aavl.inserir( 5 );
        System.out.println( aavl );
        aavl.inserir( 9 );
        System.out.println( aavl );
        aavl.inserir( 3 );
        System.out.println( aavl );
        
        // usando o iterador
        System.out.println( "Iterador (Em Ordem):" );
        for ( Integer v : aavl ) {
            System.out.println( "Item: " + v );
        }
        System.out.println();
        
        System.out.println( "----- Percursos -----" );
        System.out.print( "Pré-Ordem: " );
        for ( Integer e : PercursosArvoreAVL.percorrer( aavl, TipoPercursoArvores.PRE_ORDEM ) ) {
            System.out.print( e + " " );
        }
        System.out.println();
        
        System.out.print( "Em Ordem: " );
        for ( Integer e : PercursosArvoreAVL.percorrer( aavl, TipoPercursoArvores.EM_ORDEM ) ) {
            System.out.print( e + " " );
        }
        System.out.println();
        
        System.out.print( "Pós-Ordem: " );
        for ( Integer e : PercursosArvoreAVL.percorrer( aavl, TipoPercursoArvores.POS_ORDEM ) ) {
            System.out.print( e + " " );
        }
        System.out.println();
        
        System.out.print( "Em Nível: " );
        for ( Integer e : PercursosArvoreAVL.percorrer( aavl, TipoPercursoArvores.EM_NIVEL ) ) {
            System.out.print( e + " " );
        }
        System.out.println();
        
        System.out.print( "Pré-Ordem Inverso: " );
        for ( Integer e : PercursosArvoreAVL.percorrer( aavl, TipoPercursoArvores.PRE_ORDEM_INVERSO ) ) {
            System.out.print( e + " " );
        }
        System.out.println();
        
        System.out.print( "Em Ordem Inverso: " );
        for ( Integer e : PercursosArvoreAVL.percorrer( aavl, TipoPercursoArvores.EM_ORDEM_INVERSO ) ) {
            System.out.print( e + " " );
        }
        System.out.println();
        
        System.out.print( "Pós-Ordem Inverso: " );
        for ( Integer e : PercursosArvoreAVL.percorrer( aavl, TipoPercursoArvores.POS_ORDEM_INVERSO ) ) {
            System.out.print( e + " " );
        }
        System.out.println();
        
        System.out.print( "Em Nível Inverso: " );
        for ( Integer e : PercursosArvoreAVL.percorrer( aavl, TipoPercursoArvores.EM_NIVEL_INVERSO ) ) {
            System.out.print( e + " " );
        }
        System.out.println();
        
        // consultas
        System.out.println( "\n----- Consultas -----" );
        List<Integer> elementos = (List<Integer>) PercursosArvoreAVL.percorrer( aavl, TipoPercursoArvores.EM_ORDEM );
        elementos.add( 15 );
        elementos.add( 19 );
        elementos.add( -4 );
        Collections.shuffle( elementos );
        for ( Integer e : elementos ) {
            System.out.printf( "%4d está na lista? => %s\n", e,
                    aavl.contem( e ) ? "SIM" : "NÃO" );
        }
        
        System.out.println( "\n----- Remoção -----" );
        System.out.println( aavl );
        for ( Integer e : elementos ) {
            System.out.printf( "Removendo o elemento %4d...\n", e );
            aavl.remover( e );
            System.out.println( aavl );
        }
        
        // utilizando a árvore binária de busca anotada para testar os dados
        aavl.inserir( 8 );
        aavl.inserir( 4 );
        aavl.inserir( 2 );
        aavl.inserir( 1 );
        aavl.inserir( 3 );
        aavl.inserir( 6 );
        aavl.inserir( 5 );
        aavl.inserir( 7 );
        aavl.inserir( 12 );
        aavl.inserir( 10 );
        aavl.inserir( 9 );
        aavl.inserir( 11 );
        aavl.inserir( 14 );
        aavl.inserir( 13 );
        aavl.inserir( 15 );
        
    }

}
