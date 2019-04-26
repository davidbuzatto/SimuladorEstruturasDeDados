/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estruturas;

import estruturas.algoritmos.arvores.PercursosArvoreAVLCV;
import estruturas.algoritmos.arvores.TipoPercursoArvores;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * Implementação de uma árvore AVL chave/valor.
 * 
 * Baseado no código de:
 *     WEISS, M. A. Data Structures and Algorithm Analysis in Java. 3. ed. 
 *     Pearson Education: New Jersey, 2012. 614 p.
 * 
 * @author David Buzatto
 */
public class ArvoreAVLCV<TipoChave extends Comparable<? super TipoChave>, TipoValor> 
        implements Iterable<ArvoreAVLCV<TipoChave, TipoValor>.No<TipoChave, TipoValor>> {

    /*
     * Classe interna que define os nós da árvore.
     * É pública para poder acessar a estrutura dos nós externamente
     * no simulador e nos percursos. Deveria ser privada.
     */
    public class No<TipoChave, TipoValor> {
        
        public int altura;
        
        public TipoChave chave;
        public TipoValor valor;
        public No<TipoChave, TipoValor> esquerda;
        public No<TipoChave, TipoValor> direita;
        
        @Override
        public String toString() {
            return chave + " => " + valor;
        }
        
    }
    
    // raiz da árvore
    private No<TipoChave, TipoValor> raiz;
    
    // chave máximo na diferença de alturas de duas subárvores
    private static final int DESBALANCEAMENTO_PERMITIDO = 1;
    
    /**
     * Constrói uma Árvore AVL vazia.
     */
    public ArvoreAVLCV() {
        raiz = null;
    }

    /**
     * Insere um elemento na árvore. Substitui o valor caso a chave exista.
     * 
     * @param chave Elemento a ser inserido.
     */
    public void inserir( TipoChave chave, TipoValor valor ) {
        raiz = inserir( raiz, chave, valor );
    }
    
    /**
     * Método privado para inserção recursiva na subárvore.
     *
     * @param no raiz da subárvore.
     * @param chave elemento que será inserido.
     * @param valor valor associado à chave.
     * @return a nova raiz da subárvore.
     */
    private No<TipoChave, TipoValor> inserir( No<TipoChave, TipoValor> no, TipoChave chave, TipoValor valor ) {
        
        if ( no == null ) {
            
            no = new No<TipoChave, TipoValor>();
            no.chave = chave;
            no.valor = valor;
            no.esquerda = null;
            no.direita = null;
            no.altura = 0;
            
        }

        int comparacao = chave.compareTo( no.chave );

        if ( comparacao < 0 ) {
            no.esquerda = inserir( no.esquerda, chave, valor );
        } else if ( comparacao > 0 ) {
            no.direita = inserir( no.direita, chave, valor );
        } else {
            no.valor = valor;
        }
        
        // balanceia a árvore
        return balancear( no );
        
    }
    
    /**
     * Verifica se uma chave está contida na árvore.
     *
     * @param chave chave a ser pesquisada.
     * @return true caso tenha encontrado, false caso contrário.
     */
    public boolean contemChave( TipoChave chave ) {
        return contemChave( raiz, chave );
    }
    
    /**
     * Método privado para consulta na subárvore.
     *
     * @param no raiz da subárvore.
     * @param chave chave a ser pesquisada.
     * @return true caso tenha encontrado, false caso contrário.
     */
    private boolean contemChave( No<TipoChave, TipoValor> no, TipoChave chave ) {
        
        while ( no != null ) {
            
            int comparacao = chave.compareTo( no.chave );

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
     * @param chave chave a ser removida.
     */
    public void remover( TipoChave chave ) {
        raiz = remover( raiz, chave );
    }

    /**
     * Método privado para remoção recursiva na subárvore.
     *
     * @param no raiz da subárvore.
     * @param chave chave que será removida.
     * @return a nova raiz da subárvore.
     */
    private No<TipoChave, TipoValor> remover( No<TipoChave, TipoValor> no, TipoChave chave ) {
        
        if ( no == null ) {
            return no;   // não encontrado
        }
        
        int comparacao = chave.compareTo( no.chave );

        if ( comparacao < 0 ) {
            no.esquerda = remover( no.esquerda, chave );
        } else if ( comparacao > 0 ) {
            no.direita = remover( no.direita, chave );
        } else if ( no.esquerda != null && no.direita != null ) { // dois filhos
            no.chave = encontrarMinimo( no.direita ).chave;
            no.direita = remover( no.direita, no.chave );
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
    public TipoChave encontrarMinimo() {
        
        if ( estaVazia() ) {
            return null;
        }
        
        return encontrarMinimo( raiz ).chave;
        
    }
    
    /**
     * Método privado para encontrar o menor item de uma subárvore.
     *
     * @param no raiz da subárvore
     * @return o nó que contém o menor item.
     */
    private No<TipoChave, TipoValor> encontrarMinimo( No<TipoChave, TipoValor> no ) {
        
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
    public TipoChave encontrarMaximo() {
        
        if ( estaVazia() ) {
            return null;
        }
        
        return encontrarMaximo( raiz ).chave;
        
    }
    
    /**
     * Método privado para encontrar o maior item de uma subárvore.
     *
     * @param no raiz da subárvore
     * @return o nó que contém o maior item.
     */
    private No<TipoChave, TipoValor> encontrarMaximo( No<TipoChave, TipoValor> no ) {
        
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
    private No<TipoChave, TipoValor> desalocar( No<TipoChave, TipoValor> no ) {

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
    private No<TipoChave, TipoValor> balancear( No<TipoChave, TipoValor> no ) {
        
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

    private int verificarBalanceamento( No<TipoChave, TipoValor> no ) {
        
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
    private int altura( No<TipoChave, TipoValor> no ) {
        return no == null ? -1 : no.altura;
    }

    /**
     * Rotacionada um nó com filho à esquerda. Para as árvores AVL, essa é a 
     * rotação simples do caso 1. Atualiza as alturas e retorna a nova raiz.
     */
    private No<TipoChave, TipoValor> rotacionarComFilhoEsquerdo( No<TipoChave, TipoValor> k2 ) {
        No<TipoChave, TipoValor> k1 = k2.esquerda;
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
    private No<TipoChave, TipoValor> rotacionarComFilhoDireito( No<TipoChave, TipoValor> k1 ) {
        No<TipoChave, TipoValor> k2 = k1.direita;
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
    private No<TipoChave, TipoValor> rotacionarDuploComFilhoEsquerdo( No<TipoChave, TipoValor> k3 ) {
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
    private No<TipoChave, TipoValor> rotacionarDuploComFilhoDireito( No<TipoChave, TipoValor> k1 ) {
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
            
            for ( No<TipoChave, TipoValor> e : PercursosArvoreAVLCV.percorrer( this, TipoPercursoArvores.EM_ORDEM ) ) {
                
                if ( e.equals( raiz ) ) {
                    sb.append( e ).append( " <- raiz\n" );
                } else {
                    sb.append( e ).append( "\n" );
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
    public Iterator<No<TipoChave, TipoValor>> iterator() {
        return PercursosArvoreAVLCV.percorrer( this, TipoPercursoArvores.EM_ORDEM ).iterator();
    }
    
    /**
     * Método para obter o nó da raiz.
     * Não deveria existir, mas é necessário para o simulador.
     * 
     * @return Nó com a raiz da árvore.
     */
    public No<TipoChave, TipoValor> getRaiz() {
        return raiz;
    }
    
    /**
     * Testes da árvore.
     * 
     * @param args
     */
    public static void main( String[] args ) {
        
        ArvoreAVLCV<Integer, String> aavl = new ArvoreAVLCV<>();
        
        aavl.inserir( 6, "seis" );
        System.out.println( aavl );
        aavl.inserir( 8, "oito" );
        System.out.println( aavl );
        aavl.inserir( 7, "sete" );
        System.out.println( aavl );
        aavl.inserir( 4, "quatro" );
        System.out.println( aavl );
        aavl.inserir( 5, "cinco" );
        System.out.println( aavl );
        aavl.inserir( 9, "nove" );
        System.out.println( aavl );
        aavl.inserir( 3, "três" );
        System.out.println( aavl );
        
        // usando o iterador
        System.out.println( "Iterador (Em Ordem):" );
        for ( ArvoreAVLCV<Integer, String>.No<Integer, String> v : aavl ) {
            System.out.println( "Item: " + v );
        }
        System.out.println();
        
        System.out.println( "----- Percursos -----" );
        System.out.print( "Pré-Ordem: " );
        for ( ArvoreAVLCV<Integer, String>.No<Integer, String> e : PercursosArvoreAVLCV.percorrer( aavl, TipoPercursoArvores.PRE_ORDEM ) ) {
            System.out.print( e + " " );
        }
        System.out.println();
        
        System.out.print( "Em Ordem: " );
        for ( ArvoreAVLCV<Integer, String>.No<Integer, String> e : PercursosArvoreAVLCV.percorrer( aavl, TipoPercursoArvores.EM_ORDEM ) ) {
            System.out.print( e + " " );
        }
        System.out.println();
        
        System.out.print( "Pós-Ordem: " );
        for ( ArvoreAVLCV<Integer, String>.No<Integer, String> e : PercursosArvoreAVLCV.percorrer( aavl, TipoPercursoArvores.POS_ORDEM ) ) {
            System.out.print( e + " " );
        }
        System.out.println();
        
        System.out.print( "Em Nível: " );
        for ( ArvoreAVLCV<Integer, String>.No<Integer, String> e : PercursosArvoreAVLCV.percorrer( aavl, TipoPercursoArvores.EM_NIVEL ) ) {
            System.out.print( e + " " );
        }
        System.out.println();
        
        System.out.print( "Pré-Ordem Inverso: " );
        for ( ArvoreAVLCV<Integer, String>.No<Integer, String> e : PercursosArvoreAVLCV.percorrer( aavl, TipoPercursoArvores.PRE_ORDEM_INVERSO ) ) {
            System.out.print( e + " " );
        }
        System.out.println();
        
        System.out.print( "Em Ordem Inverso: " );
        for ( ArvoreAVLCV<Integer, String>.No<Integer, String> e : PercursosArvoreAVLCV.percorrer( aavl, TipoPercursoArvores.EM_ORDEM_INVERSO ) ) {
            System.out.print( e + " " );
        }
        System.out.println();
        
        System.out.print( "Pós-Ordem Inverso: " );
        for ( ArvoreAVLCV<Integer, String>.No<Integer, String> e : PercursosArvoreAVLCV.percorrer( aavl, TipoPercursoArvores.POS_ORDEM_INVERSO ) ) {
            System.out.print( e + " " );
        }
        System.out.println();
        
        System.out.print( "Em Nível Inverso: " );
        for ( ArvoreAVLCV<Integer, String>.No<Integer, String> e : PercursosArvoreAVLCV.percorrer( aavl, TipoPercursoArvores.EM_NIVEL_INVERSO ) ) {
            System.out.print( e + " " );
        }
        System.out.println();
        
        // consultas
        System.out.println( "\n----- Consultas -----" );
        List<ArvoreAVLCV<Integer, String>.No<Integer, String>> elementos = (List<ArvoreAVLCV<Integer, String>.No<Integer, String>>) PercursosArvoreAVLCV.percorrer( aavl, TipoPercursoArvores.EM_ORDEM );
        Collections.shuffle( elementos );
        for ( ArvoreAVLCV<Integer, String>.No<Integer, String> e : elementos ) {
            System.out.printf( "%4d está na árvore? => %s\n", e.chave,
                    aavl.contemChave( e.chave ) ? "SIM" : "NÃO" );
        }
        
        System.out.println( "\n----- Remoção -----" );
        System.out.println( aavl );
        for ( ArvoreAVLCV<Integer, String>.No<Integer, String> e : elementos ) {
            System.out.printf( "Removendo o elemento %4d...\n", e.chave );
            aavl.remover( e.chave );
            System.out.println( aavl );
        }
        
    }

}
