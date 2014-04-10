/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package estruturas;

import estruturas.algoritmos.arvores.PercursosArvoreBinariaBusca;
import estruturas.algoritmos.arvores.TipoPercursoArvores;
import gui.desenho.estruturas.ArvoreBinariaBuscaAnotada;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * Implementação de uma árvore binária de busca não balanceada.
 * 
 * Algumas modificações de acesso foram feitas na classe, permitindo que alguns
 * detalhes internos da classe sejam acessíveis externamente. Essa mudança
 * teve como objetivo permitir que os dados da árvore sejam processados
 * pela classe ArvoreBuscaBinariaAnotada e pela classe com os algoritmos de 
 * percursos.
 * 
 * @author David Buzatto
 */
public class ArvoreBinariaBusca<Tipo extends Comparable> implements Iterable<Tipo> {
    
    /*
     * Classe interna que define os nós da árvore.
     * É pública para poder acessar a estrutura dos nós externamente
     * no simulador e nos percursos. Deveria ser privada.
     */
    public class No<Tipo> {
        public Tipo valor;
        public No<Tipo> esquerda;
        public No<Tipo> direita;
    }
    
    // raiz da árvore
    private No<Tipo> raiz;
    
    /**
     * Constrói uma árvore binária de busca vazia.
     */
    public ArvoreBinariaBusca() {
        raiz = null;
    }
    
    /**
     * Insere um elemento na árvore.
     * 
     * @param valor Elemento a ser inserido.
     */
    public void inserir( Tipo valor ) {
        
            /*
             * Algoritmo iterativo.
             */
            /*NoAnotado<Tipo> novoNo = new NoAnotado<>();
            novoNo.valor = valor;
            novoNo.esquerda = null;
            novoNo.direita = null;

            if ( estaVazia() ) {
               raiz = novoNo;
            } else {

               boolean achou = false;
               NoAnotado<Tipo> temp = raiz;
               int comparacao = 0;

               while ( !achou ) {
                   
                   comparacao = valor.compareTo( temp.valor );
                   
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

                   } else { // igual, não insere
                       break;
                   }

               }

            }*/

            /*
             * Algoritmo recursivo.
             */
            raiz = inserir( raiz, valor );
        
    }
    
    /*
     * Método privado para a inserção recursiva.
     */
    private No<Tipo> inserir( No<Tipo> no, Tipo valor ) {

        if ( no == null ) {

            no = new No<>();
            no.valor = valor;
            no.esquerda = null;
            no.direita = null;

        } else if ( valor.compareTo( no.valor ) < 0 ) {
            no.esquerda = inserir( no.esquerda, valor );
        } else if ( valor.compareTo( no.valor ) > 0 ) {
            no.direita = inserir( no.direita, valor );
        }

        return no;

    }
    
    /**
     * Verifica se um valor está contido na árvore.
     * 
     * @param valor Valor a ser pesquisado.
     * @return true caso tenha encontrado, false caso contrário.
     */
    public boolean contem( Tipo valor ) {
        
        /*
         * Algoritmo iterativo.
         */
        /*boolean achou = false;
        
        if ( !estaVazia() ) {

            NoAnotado<Tipo> temp = raiz;
            int comparacao = 0;

            while ( !achou ) {
                
                comparacao = valor.compareTo( temp.valor );
                
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
        return contem( raiz, valor );
        
    }

    /*
     * Método privado para a consulta recursiva.
     */
    private boolean contem( No<Tipo> no, Tipo valor ) {

        boolean achou = false;
        int comparacao = 0;
        
        if ( no != null ) {
            
            comparacao = valor.compareTo( no.valor );
            
            if ( comparacao == 0 ) {
                achou = true;
            } else if ( comparacao < 0 ) {
                achou = contem( no.esquerda, valor );
            } else { // comparacao > 0
                achou = contem( no.direita, valor );
            }
            
        }

        return achou;

    }
    
    /**
     * Remove um elemento da árvore.
     * 
     * @param valor Valor a ser removido.
     */
    public void remover( Tipo valor ) {

        /*
         * Algoritmo iterativo.
         * Não estudaremos! É um pouco mais complicado que os outros.
         */

        /*
         * Algoritmo recursivo.
         */
        raiz = remover( raiz, valor );

    }
    
    /*
     * Método privado para a remoção recursiva (Hibbard Deletion).
     */
    private No<Tipo> remover( No<Tipo> no, Tipo valor ) {
        
        if ( no != null ) {
            
            No<Tipo> temp;
            int comparacao = valor.compareTo( no.valor );

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
                    No<Tipo> menor = temp;

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
                no.esquerda = remover( no.esquerda, valor );
            } else { // comparacao > 0
                no.direita = remover( no.direita, valor );
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
    
    /**
     * Cria uma representação em String da árvore.
     * Esta representação apresenta os elementos na ordem do percurso em ordem.
     */
    @Override
    public String toString() {
        
        StringBuilder sb = new StringBuilder();
        
        if ( !estaVazia() ) {
            
            for ( Tipo valor : PercursosArvoreBinariaBusca.percorrer( this, TipoPercursoArvores.EM_ORDEM ) ) {
                
                if ( valor.equals( raiz.valor ) ) {
                    sb.append( valor ).append( " <- raiz\n" );
                } else {
                    sb.append( valor ).append( "\n" );
                }
                
            }
            
        } else {
            sb.append( "deque vazia!\n" );
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
        return PercursosArvoreBinariaBusca.percorrer( this, TipoPercursoArvores.EM_ORDEM ).iterator();
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
        
        ArvoreBinariaBusca<Integer> abb = new ArvoreBinariaBusca<>();
        
        abb.inserir( 6 );
        System.out.println( abb );
        abb.inserir( 8 );
        System.out.println( abb );
        abb.inserir( 7 );
        System.out.println( abb );
        abb.inserir( 4 );
        System.out.println( abb );
        abb.inserir( 5 );
        System.out.println( abb );
        abb.inserir( 9 );
        System.out.println( abb );
        abb.inserir( 3 );
        System.out.println( abb );
        
        // usando o iterador
        System.out.println( "Iterador (Em Ordem):" );
        for ( Integer v : abb ) {
            System.out.println( "Item: " + v );
        }
        System.out.println();
        
        System.out.println( "----- Percursos -----" );
        System.out.print( "Pré-Ordem: " );
        for ( Integer e : PercursosArvoreBinariaBusca.percorrer( abb, TipoPercursoArvores.PRE_ORDEM ) ) {
            System.out.print( e + " " );
        }
        System.out.println();
        
        System.out.print( "Em Ordem: " );
        for ( Integer e : PercursosArvoreBinariaBusca.percorrer( abb, TipoPercursoArvores.EM_ORDEM ) ) {
            System.out.print( e + " " );
        }
        System.out.println();
        
        System.out.print( "Pós-Ordem: " );
        for ( Integer e : PercursosArvoreBinariaBusca.percorrer( abb, TipoPercursoArvores.POS_ORDEM ) ) {
            System.out.print( e + " " );
        }
        System.out.println();
        
        System.out.print( "Em Nível: " );
        for ( Integer e : PercursosArvoreBinariaBusca.percorrer( abb, TipoPercursoArvores.EM_NIVEL ) ) {
            System.out.print( e + " " );
        }
        System.out.println();
        
        System.out.print( "Pré-Ordem Inverso: " );
        for ( Integer e : PercursosArvoreBinariaBusca.percorrer( abb, TipoPercursoArvores.PRE_ORDEM_INVERSO ) ) {
            System.out.print( e + " " );
        }
        System.out.println();
        
        System.out.print( "Em Ordem Inverso: " );
        for ( Integer e : PercursosArvoreBinariaBusca.percorrer( abb, TipoPercursoArvores.EM_ORDEM_INVERSO ) ) {
            System.out.print( e + " " );
        }
        System.out.println();
        
        System.out.print( "Pós-Ordem Inverso: " );
        for ( Integer e : PercursosArvoreBinariaBusca.percorrer( abb, TipoPercursoArvores.POS_ORDEM_INVERSO ) ) {
            System.out.print( e + " " );
        }
        System.out.println();
        
        System.out.print( "Em Nível Inverso: " );
        for ( Integer e : PercursosArvoreBinariaBusca.percorrer( abb, TipoPercursoArvores.EM_NIVEL_INVERSO ) ) {
            System.out.print( e + " " );
        }
        System.out.println();
        
        // consultas
        System.out.println( "\n----- Consultas -----" );
        List<Integer> elementos = (List<Integer>) PercursosArvoreBinariaBusca.percorrer( abb, TipoPercursoArvores.EM_ORDEM );
        elementos.add( 15 );
        elementos.add( 19 );
        elementos.add( -4 );
        Collections.shuffle( elementos );
        for ( Integer e : elementos ) {
            System.out.printf( "%4d está na lista? => %s\n", e,
                    abb.contem( e ) ? "SIM" : "NÃO" );
        }
        
        System.out.println( "\n----- Remoção -----" );
        System.out.println( abb );
        for ( Integer e : elementos ) {
            System.out.printf( "Removendo o elemento %4d...\n", e );
            abb.remover( e );
            System.out.println( abb );
        }
        
        // utilizando a árvore binária de busca anotada para testar os dados
        abb.inserir( 8 );
        abb.inserir( 4 );
        abb.inserir( 2 );
        abb.inserir( 1 );
        abb.inserir( 3 );
        abb.inserir( 6 );
        abb.inserir( 5 );
        abb.inserir( 7 );
        abb.inserir( 12 );
        abb.inserir( 10 );
        abb.inserir( 9 );
        abb.inserir( 11 );
        abb.inserir( 14 );
        abb.inserir( 13 );
        abb.inserir( 15 );
        
        ArvoreBinariaBuscaAnotada<Integer> abbAnt = new ArvoreBinariaBuscaAnotada<>( abb );
        System.out.println( abbAnt );
        for ( ArvoreBinariaBuscaAnotada<Integer>.NoAnotado<Integer> n : abbAnt.percorrer( TipoPercursoArvores.EM_ORDEM ) ) {
            System.out.println( n );
        }
        
    }
    
}
