/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package estruturas;

import estruturas.excecoes.EmptyListException;
import java.util.Iterator;

/**
 * Implementação de uma lista dinâmica genérica, usando encadeamento duplo.
 * 
 * @param <Tipo> Tipo dos elementos armazenados na lista.
 * 
 * @author David Buzatto
 */
public class Lista<Tipo> implements Iterable<Tipo> {
    
    /*
     * Classe privada que define os nós da lista.
     */
    private class No<Tipo> {
        Tipo valor;
        No<Tipo> anterior;
        No<Tipo> proximo;
    }
    
    // ponteiros para o início e fim da lista
    private No<Tipo> inicio; // extremidade direita, posição n-1
    private No<Tipo> fim;  // extremidade esquerda, posicao 0
    
    // tamanho da lista
    private int tamanho;
    
    /**
     * Constrói uma lista vazia.
     */
    public Lista() {
        fim = null;
        inicio = null;
        tamanho = 0;
    }
    
    /**
     * Insere um elemento no início da lista.
     * 
     * @param valor Elemento a ser inserido no início.
     */
    public void inserirInicio( Tipo valor ) {
        
        No<Tipo> novoNo = new No<>();
        novoNo.valor = valor;
        novoNo.anterior = null;
        novoNo.proximo = null;
        
        if ( estaVazia() ) {
            inicio = novoNo;
            fim = novoNo;
        } else {
            inicio.proximo = novoNo;
            novoNo.anterior = inicio;
            inicio = novoNo;
        }
        
        tamanho++;
        
    }
    
    /**
     * Insere um elemento no final da lista.
     * 
     * @param valor Elemento a ser inserido no final.
     */
    public void inserirFim( Tipo valor ) {
        
        No<Tipo> novoNo = new No<>();
        novoNo.valor = valor;
        novoNo.anterior = null;
        novoNo.proximo = null;
        
        if ( estaVazia() ) {
            inicio = novoNo;
            fim = novoNo;
        } else {
            fim.anterior = novoNo;
            novoNo.proximo = fim;
            fim = novoNo;
        }
        
        tamanho++;
        
    }
    
    /**
     * Insere um elemento na lista na posição especificada.
     * 
     * @param valor Elemento a ser inserido na lista.
     * @param posicao Posição que o elemento será inserido.
     */
    public void inserir( Tipo valor, int posicao ) {
        
        if ( posicao <= 0 ) {
            inserirFim( valor );
        } else if ( posicao >= tamanho ) {
            inserirInicio( valor );
        } else {
            
            No<Tipo> novoNo = new No<>();
            novoNo.valor = valor;
            
            // posiciona onde será mexido (vai deslocar a lista para a direita)
            No<Tipo> temp = fim;
            for ( int i = 0; i < posicao; i++ ) {
                temp = temp.proximo;
            }
            
            novoNo.proximo = temp;
            novoNo.anterior = temp.anterior;
            
            temp.anterior.proximo = novoNo;
            temp.anterior = novoNo;

            tamanho++;
            
        }
        
    }
    
    /**
     * Altera o elemento que está no início da lista.
     * 
     * @param valor Valor a ser colocado no lugar do elemento do início.
     */
    public void alterarInicio( Tipo valor ) throws EmptyListException {
        
        if ( !estaVazia() ) {
            inicio.valor = valor;
        } else {
            throw new EmptyListException();
        }
        
    }
    
    /**
     * Altera o elemento que está no fim da lista.
     * 
     * @param valor Valor a ser colocado no lugar do elemento do fim.
     */
    public void alterarFim( Tipo valor ) throws EmptyListException {
        
        if ( !estaVazia() ) {
            fim.valor = valor;
        } else {
            throw new EmptyListException();
        }
        
    }
    
    /**
     * Altera o elemento que está na posicao especificada.
     * 
     * @param valor Valor a ser colocado no lugar do elemento que está na posição especificada.
     * @param posicao Posição do elemento a ser alterado.
     */
    public void alterar( Tipo valor, int posicao ) throws EmptyListException {
        
        if ( !estaVazia() ) {
            
            if ( posicao <= 0 ) {
                alterarFim( valor );
            } else if ( posicao >= tamanho - 1 ) {
                alterarInicio( valor );
            } else {
                
                No<Tipo> temp = fim;
                
                for ( int i = 0; i < posicao; i++ ) {
                    temp = temp.proximo;
                }

                temp.valor = valor;
                
            }
            
        } else {
            throw new EmptyListException();
        }
        
    }
    
    /**
     * Remove um elemento do início da lista.
     * 
     * @return O elemento removido do início.
     */
    public Tipo removerInicio() throws EmptyListException {
        
        if ( !estaVazia() ) {
            
            Tipo valor = inicio.valor;
            
            // cabeça e cauda apontam para o mesmo objeto
            if ( inicio == fim ) {
                inicio = null;
                fim = null;
            } else {
                No<Tipo> temp = inicio;
                inicio = inicio.anterior;
                inicio.proximo = null;
                temp.anterior = null;
            }
            
            tamanho--;
            return valor;
            
        } else {
            throw new EmptyListException();
        }
        
    }
    
    /**
     * Remove um elemento do fim da lista.
     * 
     * @return O elemento removido do fim.
     */
    public Tipo removerFim() throws EmptyListException {
        
        if ( !estaVazia() ) {
            
            Tipo valor = fim.valor;
            
            // cabeça e cauda apontam para o mesmo objeto
            if ( inicio == fim ) {
                inicio = null;
                fim = null;
            } else {
                No<Tipo> temp = fim;
                fim = fim.proximo;
                fim.anterior = null;
                temp.proximo = null;
            }
            
            tamanho--;
            return valor;
            
        } else {
            throw new EmptyListException();
        }
        
    }
    
    /**
     * Remove um elemento na lista na posição especificada.
     * 
     * @param posicao Posição que o elemento será removido.
     * @return O elemento removido.
     */
    public Tipo remover( int posicao ) {
        
        if ( posicao <= 0 ) {
            return removerFim();
        } else if ( posicao >= tamanho - 1 ) {
            return removerInicio();
        } else {
            
            // posiciona onde será removido
            No<Tipo> temp = fim;
            for ( int i = 0; i < posicao; i++ ) {
                temp = temp.proximo;
            }
            
            Tipo valor = temp.valor;
            
            temp.anterior.proximo = temp.proximo;
            temp.proximo.anterior = temp.anterior;
            
            temp.proximo = null;
            temp.anterior = null;

            tamanho--;
            return valor;
            
        }
        
    }
    
    /**
     * Retorna o elemento que está no início da lista, sem removê-lo.
     * 
     * @return O elemento no início da lista.
     */
    public Tipo consultarInicio() throws EmptyListException {
        
        if ( !estaVazia() ) {
            return inicio.valor;
        } else {
            throw new EmptyListException();
        }
        
    }
    
    /**
     * Retorna o elemento que está no fim da lista, sem removê-lo.
     * 
     * @return O elemento no fim da lista.
     */
    public Tipo consultarFim() throws EmptyListException {
        
        if ( !estaVazia() ) {
            return fim.valor;
        } else {
            throw new EmptyListException();
        }
        
    }
    
    /**
     * Retorna o elemento que está na posicao especificada.
     * 
     * @param posicao Posição do elemento a ser retornado.
     * @return O elemento da posição.
     */
    public Tipo consultar( int posicao ) throws EmptyListException {
        
        if ( !estaVazia() ) {
            
            if ( posicao <= 0 ) {
                return consultarFim();
            } else if ( posicao >= tamanho - 1 ) {
                return consultarInicio();
            } else {
                
                No<Tipo> temp = fim;
                
                for ( int i = 0; i < posicao; i++ ) {
                    temp = temp.proximo;
                }

                return temp.valor;
                
            }
            
        } else {
            throw new EmptyListException();
        }
        
    }
    
    /**
     * Esvazia a lista.
     */
    public void esvaziar() {
        
        try {
            while ( true ) {
                removerInicio();
            }
        } catch ( EmptyListException exc ) {
        }
        
    }
    
    /**
     * Verifica se a lista está vazia.
     * 
     * @return true se a lista estiver vazia, false caso contrário.
     */
    public boolean estaVazia() {
        return inicio == null;
    }
    
    /**
     * Obtém o tamanho da lista (quantidade de elementos).
     * 
     * @return O tamanho da lista.
     */
    public int getTamanho() {
        return tamanho;
    }
    
    /**
     * Cria uma representação em String da lista.
     * Esta representação apresenta os elementos no sentido cabeça -> cauda.
     */
    @Override
    public String toString() {
        
        StringBuilder sb = new StringBuilder();
        
        if ( !estaVazia() ) {
            
            int i = tamanho - 1;
            
            // percorrendo a estrutura encadeada
            No<Tipo> atual = inicio;

            while ( atual != null ) {
                
                if ( tamanho == 1 ) {
                    sb.append( String.format( "[%d] - ", i ) )
                            .append( atual.valor ).append( " <- início/fim\n" );
                } else if ( atual == inicio ) {
                    sb.append( String.format( "[%d] - ", i ) )
                            .append( atual.valor ).append( " <- início\n" );
                } else if ( atual == fim ) {
                    sb.append( String.format( "[%d] - ", i ) )
                            .append( atual.valor ).append( " <- fim\n" );
                } else {
                    sb.append( String.format( "[%d] - ", i ) )
                            .append( atual.valor ).append( "\n" );
                }
                
                i--;
                atual = atual.anterior;

            }

            
            // ou usando o iterador
            /*for ( Tipo atual : this ) {

                if ( tamanho == 1 ) {
                    sb.append( String.format( "[%d] - ", i ) )
                            .append( atual ).append( " <- início/fim\n" );
                } else if ( i == tamanho - 1 ) {
                    sb.append( String.format( "[%d] - ", i ) )
                            .append( atual ).append( " <- início\n" );
                } else if ( i == 0 ) {
                    sb.append( String.format( "[%d] - ", i ) )
                            .append( atual ).append( " <- fim\n" );
                } else {
                    sb.append( String.format( "[%d] - ", i ) )
                            .append( atual ).append( "\n" );
                }
                
                i--;

            }*/
            
        } else {
            sb.append( "lista vazia!\n" );
        }
        
        return sb.toString();
        
    }
    
    /**
     * Cria um iterador para a lista, permitindo iterar por todos os elementos
     * da mesma, além de poder usar em um for each (for melhorado).
     * Este iterador percorre a lista no sentido cabeça -> cauda.
     */
    @Override
    public Iterator<Tipo> iterator() {
        
        return new Iterator<Tipo>() {
            
            private No<Tipo> atual = inicio;
            
            @Override
            public boolean hasNext() {
                return atual != null;
            }

            @Override
            public Tipo next() {
                Tipo item = atual.valor;
                atual = atual.anterior;
                return item;
            }
            
            @Override
            public void remove() {
                throw new UnsupportedOperationException( "Não suportado." );
            }
            
        };
        
    }
    
    /**
     * Testes da lista.
     * 
     * @param args
     */
    public static void main( String[] args ) {
        
        Lista<Integer> lista = new Lista<>();
        
        lista.inserirInicio( 10 );
        System.out.println( lista );
        lista.inserirFim( 5 );
        System.out.println( lista );
        lista.inserir( 7, -1 );
        System.out.println( lista );
        lista.inserir( -9, 0 );
        System.out.println( lista );
        lista.inserir( 3, 1 );
        System.out.println( lista );
        lista.inserir( 8, 0 );
        System.out.println( lista );
        lista.inserir( 32, 10 );
        System.out.println( lista );
        lista.inserir( 21, 3 );
        System.out.println( lista );
        
        // usando o iterador
        for ( int v : lista ) {
            System.out.println( "Item: " + v );
        }
        System.out.println();
        
        System.out.println( "Removeu do Início: " + lista.removerInicio() );
        System.out.println( lista );
        System.out.println( "Removeu do Fim: " + lista.removerFim() );
        System.out.println( lista );
        System.out.println( "Removeu da Posição -1: " + lista.remover( -1 ) );
        System.out.println( lista );
        System.out.println( "Removeu da Posição 0: " + lista.remover( 0 ) );
        System.out.println( lista );
        System.out.println( "Removeu da Posição 3: " + lista.remover( 3 ) );
        System.out.println( lista );
        System.out.println( "Removeu da Posição 1: " + lista.remover( 1 ) );
        System.out.println( lista );
        System.out.println( "Removeu do Fim: " + lista.removerFim() );
        System.out.println( lista );
        System.out.println( "Removeu do Inicio: " + lista.removerInicio() );
        System.out.println( lista );
        //System.out.println( "Removeu do Fim: " + lista.removerFim() ); // <- lista vazia!
        //System.out.println( "Removeu do Inicio: " + lista.removerInicio() ); // <- lista vazia!
        
    }
    
}
