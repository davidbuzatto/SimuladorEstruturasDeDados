/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package estruturas;

import estruturas.algoritmos.arvores.PercursosArvoreBinariaBuscaCV;
import estruturas.algoritmos.arvores.TipoPercursoArvores;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * Implementação de uma árvore binária de busca não balanceada.
 * Esta árvore opera com chaves associadas a valores.
 * 
 * Algumas modificações de acesso foram feitas na classe, permitindo que alguns
 * detalhes internos da classe sejam acessíveis externamente. Essa mudança
 * teve como objetivo permitir que os dados da árvore sejam processados
 * pela classe ArvoreBuscaBinariaAnotada e pela classe com os algoritmos de 
 * percursos.
 * 
 * @param <TipoChave> Tipo das chaves armazenadas na árvore.
 * @param <TipoChave> Tipo dos valores associados às chaves.
 * 
 * @author David Buzatto
 */
public class ArvoreBinariaBuscaCV<TipoChave extends Comparable<? super TipoChave>, TipoValor> 
        implements Iterable<ArvoreBinariaBuscaCV<TipoChave, TipoValor>.No<TipoChave, TipoValor>> {
    
    /*
     * Classe interna que define os nós da árvore.
     * É pública para poder acessar a estrutura dos nós externamente
     * no simulador e nos percursos. Deveria ser privada.
     */
    public class No<TipoChave, TipoValor> {
        public TipoChave chave;
        public TipoValor valor;
        public No<TipoChave, TipoValor> esquerda;
        public No<TipoChave, TipoValor> direita;

        @Override
        public String toString() {
            return chave + " -> " + valor;
        }
    }
    
    // raiz da árvore
    private No<TipoChave, TipoValor> raiz;
    
    /**
     * Constrói uma árvore binária de busca vazia.
     */
    public ArvoreBinariaBuscaCV() {
        raiz = null;
    }
    
    /**
     * Insere uma chave associada a um valor.
     * 
     * @param chave Elemento a ser inserida.
     * @param valor Valor a ser associado à chave.
     */
    public void inserir( TipoChave chave, TipoValor valor ) {
        
            /*
             * Algoritmo iterativo.
             */
            /*No<TipoChave, TipoValor> novoNo = new No<>();
            novoNo.chave = chave;
            novoNo.valor = valor;
            novoNo.esquerda = null;
            novoNo.direita = null;

            if ( estaVazia() ) {
               raiz = novoNo;
            } else {

               boolean achou = false;
               No<TipoChave, TipoValor> temp = raiz;
               int comparacao = 0;

               while ( !achou ) {
                   
                   comparacao = chave.compareTo( temp.chave );
                   
                   if ( comparacao < 0 ) {

                       if ( temp.esquerda == null ) {
                           temp.esquerda = novoNo;
                           achou = true;
                       } else {
                           temp = temp.esquerda;
                       }

                   } else if ( comparacao > 0 ) {

                       if ( temp.direita == null ) {
                           temp.direita = novoNo;
                           achou = true;
                       } else {
                           temp = temp.direita;
                       }

                   } else { // igual, substitui o valor
                       temp.valor = valor;
                       achou = true;
                   }

               }

            }*/

            /*
             * Algoritmo recursivo.
             */
            raiz = inserir( raiz, chave, valor );
        
    }
    
    /*
     * Método privado para a inserção recursiva.
     */
    private No<TipoChave, TipoValor> inserir( No<TipoChave, TipoValor> no, TipoChave chave, TipoValor valor ) {
        
        if ( no == null ) {

            no = new No<>();
            no.chave = chave;
            no.valor = valor;
            no.esquerda = null;
            no.direita = null;

        } else {
            
            int comparacao = chave.compareTo( no.chave );
            
            if ( comparacao < 0 ) {
                no.esquerda = inserir( no.esquerda, chave, valor );
            } else if ( comparacao > 0 ) {
                no.direita = inserir( no.direita, chave, valor );
            } else { // substitui o valor
                no.valor = valor;
            }
            
        }

        return no;

    }
    
    /**
     * Obtém o valor associado a uma chave.
     * 
     * @param chave chave a ser consultada
     * @return o valor associado à chave ou null caso não haja a chave.
     */
    public TipoValor obter( TipoChave chave ) {
        return obter( raiz, chave );
    }

    /**
     * Obtém o valor associado a uma chave em uma subárvore enraizada em um nó.
     * 
     * @param no nó de origem da consulta
     * @param chave chave a ser consultada
     * @return o valor associado à chave ou null caso não haja a chave.
     */
    private TipoValor obter( No<TipoChave, TipoValor> no, TipoChave chave ) {
        
        while ( no != null ) {
            
            int comparacao = chave.compareTo( no.chave );
            
            if ( comparacao < 0 ) {
                no = no.esquerda;
            } else if ( comparacao > 0 ) {
                no = no.direita;
            } else {
                return no.valor;
            }
            
        }
        
        return null;
        
    }
    
    
    /**
     * Verifica se uma chave está contida na árvore.
     * 
     * @param chave Chave a ser pesquisada.
     * @return true caso tenha encontrado, false caso contrário.
     */
    public boolean contem( TipoChave chave ) {
        
        /*
         * Algoritmo iterativo.
         */
        /*boolean achou = false;
        
        if ( !estaVazia() ) {

            No<TipoChave, TipoValor> temp = raiz;
            int comparacao = 0;

            while ( !achou ) {
                
                comparacao = chave.compareTo( temp.chave );
                
                if ( comparacao == 0 ) {
                    achou = true;
                    break;
                } else if ( comparacao < 0 ) {

                    if ( temp.esquerda == null ) {
                        achou = false;
                        break;
                    } else {
                        temp = temp.esquerda;
                    }

                } else { // comparacao > 0

                    if ( temp.direita == null ) {
                        achou = false;
                        break;
                    } else {
                        temp = temp.direita;
                    }

                }

            }

        }
        
        return achou;*/
        
        /*
         * Algoritmo recursivo.
         */
        return contem( raiz, chave );
        
    }

    /*
     * Método privado para a consulta recursiva.
     */
    private boolean contem( No<TipoChave, TipoValor> no, TipoChave chave ) {

        boolean achou = false;
        int comparacao = 0;
        
        if ( no != null ) {
            
            comparacao = chave.compareTo( no.chave );
            
            if ( comparacao == 0 ) {
                achou = true;
            } else if ( comparacao < 0 ) {
                achou = contem( no.esquerda, chave );
            } else { // comparacao > 0
                achou = contem( no.direita, chave );
            }
            
        }

        return achou;

    }
    
    /**
     * Remove uma chave de árvore, caso exista (Hibbard Deletion).
     * 
     * @param chave Chave a ser removida.
     */
    public void remover( TipoChave chave ) {

        /*
         * Algoritmo iterativo.
         */
        /*if ( !estaVazia() ) {

            No<TipoChave, TipoValor> atual = raiz;
            No<TipoChave, TipoValor> anterior = null;
            char caminho = '\0';
            int comparacao = 0;

            while ( true ) {
                
                comparacao = chave.compareTo( atual.chave );
                
                if ( comparacao == 0 ) {
                    
                    // o nó não tem filhos
                    if ( atual.esquerda == atual.direita ) {

                        // está na raiz
                        if ( anterior == null ) {
                            raiz = null;
                        } else {
                            if ( caminho == 'e' ) {
                                anterior.esquerda = null;
                            } else if ( caminho == 'd' ) {
                                anterior.direita = null;
                            }
                        }

                    // o nó a ser removido não tem filho à esquerda, só à direita
                    // a primeira condição garante que se os dois nós não são o mesmo,
                    // um deles pode ser null.
                    } else if ( atual.esquerda == null ) {
                        
                        // está na raiz
                        if ( anterior == null ) {
                            raiz = atual.direita;
                        } else {
                            if ( caminho == 'e' ) {
                                anterior.esquerda = atual.direita;
                            } else if ( caminho == 'd' ) {
                                anterior.direita = atual.direita;
                            }
                        }
                        
                        atual.direita = null;

                    // o nó a ser removido não tem filho à direita, só à esquerda
                    // a primeira condição garante que se os dois nós não são o mesmo,
                    // um deles pode ser null.
                    } else if ( atual.direita == null ) {

                        // está na raiz
                        if ( anterior == null ) {
                            raiz = atual.esquerda;
                        } else {
                            if ( caminho == 'e' ) {
                                anterior.esquerda = atual.esquerda;
                            } else if ( caminho == 'd' ) {
                                anterior.direita = atual.esquerda;
                            }
                        }
                        
                        atual.esquerda = null;

                    // o nó a ser removido tem filhos em ambos os lados
                    } else {

                        // busca pelo menor nó, onde a subárvore esquerda
                        // será inserida
                        No<TipoChave, TipoValor> menor = atual.direita;

                        while ( menor.esquerda != null ) {
                            menor = menor.esquerda;
                        }

                        // reaponta a subárvore esquerda do nó removido
                        // no menor item encontrado
                        menor.esquerda = atual.esquerda;
                        
                        // está na raiz
                        if ( anterior == null ) {
                            raiz = atual.direita;
                        } else {
                            if ( caminho == 'e' ) {
                                anterior.esquerda = atual.direita;
                            } else if ( caminho == 'd' ) {
                                anterior.direita = atual.direita;
                            }
                        }
                        
                        atual.esquerda = null;
                        atual.direita = null;

                    }
                    
                    break;
                    
                } else if ( comparacao < 0 ) {

                    if ( atual.esquerda == null ) {
                        break;
                    } else {
                        anterior = atual;
                        caminho = 'e';
                        atual = atual.esquerda;
                    }

                } else { // comparacao > 0

                    if ( atual.direita == null ) {
                        break;
                    } else {
                        anterior = atual;
                        caminho = 'd';
                        atual = atual.direita;
                    }

                }

            }

        }*/

        /*
         * Algoritmo recursivo.
         */
        raiz = remover( raiz, chave );

    }
    
    /*
     * Método privado para a remoção recursiva (Hibbard Deletion).
     */
    private No<TipoChave, TipoValor> remover( No<TipoChave, TipoValor> no, TipoChave chave) {
        
        if ( no != null ) {
            
            No<TipoChave, TipoValor> temp;
            int comparacao = chave.compareTo( no.chave );

            if ( comparacao == 0 ) {

                // o nó não tem filhos
                if ( no.esquerda == no.direita ) {

                    return null;

                // o nó a ser removido não tem filho à esquerda, só à direita
                // a primeira condição garante que se os dois nós não são o mesmo,
                // um deles pode ser null.
                } else if ( no.esquerda == null ) {

                    temp = no.direita;
                    no.direita = null;
                    return temp;

                // o nó a ser removido não tem filho à direita, só à esquerda
                // a primeira condição garante que se os dois nós não são o mesmo,
                // um deles pode ser null.
                } else if ( no.direita == null ) {

                    temp = no.esquerda;
                    no.esquerda = null;
                    return temp;

                // o nó a ser removido tem filhos em ambos os lados
                } else {

                    // busca pelo menor nó, onde a subárvore esquerda
                    // será inserida
                    temp = no.direita;
                    No<TipoChave, TipoValor> menor = temp;

                    while ( menor.esquerda != null ) {
                        menor = menor.esquerda;
                    }

                    // reaponta a subárvore esquerda do nó removido
                    // no menor item encontrado
                    menor.esquerda = no.esquerda;

                    no.esquerda = null;
                    no.direita = null;

                    return temp;

                }

            } else if ( comparacao < 0 ) {
                no.esquerda = remover( no.esquerda, chave );
            } else { // comparacao > 0
                no.direita = remover( no.direita, chave );
            }
            
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
    
    /**
     * Cria uma representação em String da árvore.
     * Esta representação apresenta os elementos na ordem do percurso em ordem.
     */
    @Override
    public String toString() {
        
        StringBuilder sb = new StringBuilder();
        
        if ( !estaVazia() ) {
            
            for ( No<TipoChave, TipoValor> valor : PercursosArvoreBinariaBuscaCV.percorrer( this, TipoPercursoArvores.EM_ORDEM ) ) {
                
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
    public Iterator<No<TipoChave, TipoValor>> iterator() {
        return PercursosArvoreBinariaBuscaCV.percorrer( this, TipoPercursoArvores.EM_ORDEM ).iterator();
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
        
        ArvoreBinariaBuscaCV<Integer, String> abb = new ArvoreBinariaBuscaCV<>();
        
        abb.inserir( 6, "seis" );
        System.out.println( abb );
        abb.inserir( 8, "oito" );
        System.out.println( abb );
        abb.inserir( 7, "sete" );
        System.out.println( abb );
        abb.inserir( 4, "quatro" );
        System.out.println( abb );
        abb.inserir( 5, "cinco" );
        System.out.println( abb );
        abb.inserir( 9, "nove" );
        System.out.println( abb );
        abb.inserir( 3, "três" );
        System.out.println( abb );
        
        // usando o iterador
        System.out.println( "Iterador (Em Ordem):" );
        for ( ArvoreBinariaBuscaCV<Integer, String>.No<Integer, String> v : abb ) {
            System.out.println( "Item: " + v );
        }
        System.out.println();
        
        System.out.println( "----- Percursos -----" );
        System.out.print( "Pré-Ordem: " );
        for ( ArvoreBinariaBuscaCV<Integer, String>.No<Integer, String> e : PercursosArvoreBinariaBuscaCV.percorrer( abb, TipoPercursoArvores.PRE_ORDEM ) ) {
            System.out.print( e + " " );
        }
        System.out.println();
        
        System.out.print( "Em Ordem: " );
        for ( ArvoreBinariaBuscaCV<Integer, String>.No<Integer, String> e : PercursosArvoreBinariaBuscaCV.percorrer( abb, TipoPercursoArvores.EM_ORDEM ) ) {
            System.out.print( e + " " );
        }
        System.out.println();
        
        System.out.print( "Pós-Ordem: " );
        for ( ArvoreBinariaBuscaCV<Integer, String>.No<Integer, String> e : PercursosArvoreBinariaBuscaCV.percorrer( abb, TipoPercursoArvores.POS_ORDEM ) ) {
            System.out.print( e + " " );
        }
        System.out.println();
        
        System.out.print( "Em Nível: " );
        for ( ArvoreBinariaBuscaCV<Integer, String>.No<Integer, String> e : PercursosArvoreBinariaBuscaCV.percorrer( abb, TipoPercursoArvores.EM_NIVEL ) ) {
            System.out.print( e + " " );
        }
        System.out.println();
        
        System.out.print( "Pré-Ordem Inverso: " );
        for ( ArvoreBinariaBuscaCV<Integer, String>.No<Integer, String> e : PercursosArvoreBinariaBuscaCV.percorrer( abb, TipoPercursoArvores.PRE_ORDEM_INVERSO ) ) {
            System.out.print( e + " " );
        }
        System.out.println();
        
        System.out.print( "Em Ordem Inverso: " );
        for ( ArvoreBinariaBuscaCV<Integer, String>.No<Integer, String> e : PercursosArvoreBinariaBuscaCV.percorrer( abb, TipoPercursoArvores.EM_ORDEM_INVERSO ) ) {
            System.out.print( e + " " );
        }
        System.out.println();
        
        System.out.print( "Pós-Ordem Inverso: " );
        for ( ArvoreBinariaBuscaCV<Integer, String>.No<Integer, String> e : PercursosArvoreBinariaBuscaCV.percorrer( abb, TipoPercursoArvores.POS_ORDEM_INVERSO ) ) {
            System.out.print( e + " " );
        }
        System.out.println();
        
        System.out.print( "Em Nível Inverso: " );
        for ( ArvoreBinariaBuscaCV<Integer, String>.No<Integer, String> e : PercursosArvoreBinariaBuscaCV.percorrer( abb, TipoPercursoArvores.EM_NIVEL_INVERSO ) ) {
            System.out.print( e + " " );
        }
        System.out.println();
        
        // consultas
        System.out.println( "\n----- Consultas -----" );
        List<ArvoreBinariaBuscaCV<Integer, String>.No<Integer, String>> elementos = (List<ArvoreBinariaBuscaCV<Integer, String>.No<Integer, String>>) PercursosArvoreBinariaBuscaCV.percorrer( abb, TipoPercursoArvores.EM_ORDEM );
        /*elementos.add( 15 );
        elementos.add( 19 );
        elementos.add( -4 );*/
        Collections.shuffle( elementos );
        for ( ArvoreBinariaBuscaCV<Integer, String>.No<Integer, String> e : elementos ) {
            System.out.printf( "%4d está na árvore? => %s\n", e.chave,
                    abb.contem( e.chave ) ? "SIM" : "NÃO" );
        }
        
        System.out.println( "\n----- Remoção -----" );
        System.out.println( abb );
        for ( ArvoreBinariaBuscaCV<Integer, String>.No<Integer, String> e : elementos ) {
            System.out.printf( "Removendo a chave %4d...\n", e.chave );
            abb.remover( e.chave );
            System.out.println( abb );
        }
        
    }
    
}
