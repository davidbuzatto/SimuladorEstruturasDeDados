/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package estruturas;

import estruturas.excecoes.EmptyDequeException;
import java.util.Iterator;

/**
 * Implementação de uma deque dinâmica genérica, usando encadeamento duplo.
 * 
 * Obs: Implementação com a marcação da cabeça para a direita e da cauda para a
 * esquerda.
 * 
 * @param <Tipo> Tipo dos elementos armazenados na deque.
 * 
 * @author David Buzatto
 */
public class Deque<Tipo> implements Iterable<Tipo> {
    
    /*
     * Classe privada que define os nós da deque.
     */
    private class No<Tipo> {
        Tipo valor;
        No<Tipo> anterior;
        No<Tipo> proximo;
    }
    
    // cabeça a cauda da deque
    private No<Tipo> cabeca;
    private No<Tipo> cauda;
    
    // tamanho da deque
    private int tamanho;
    
    /**
     * Constrói uma deque vazia.
     */
    public Deque() {
        cauda = null;
        cabeca = null;
        tamanho = 0;
    }
    
    /**
     * Insere um elemento no início da deque.
     * 
     * @param valor Elemento a ser inserido no início.
     */
    public void inserirInicio( Tipo valor ) {
        
        No<Tipo> novoNo = new No<>();
        novoNo.valor = valor;
        novoNo.anterior = null;
        novoNo.proximo = null;
        
        if ( estaVazia() ) {
            cabeca = novoNo;
            cauda = novoNo;
        } else {
            cabeca.proximo = novoNo;
            novoNo.anterior = cabeca;
            cabeca = novoNo;
        }
        
        tamanho++;
        
    }
    
    /**
     * Insere um elemento no final da deque.
     * 
     * @param valor Elemento a ser inserido no final.
     */
    public void inserirFim( Tipo valor ) {
        
        No<Tipo> novoNo = new No<>();
        novoNo.valor = valor;
        novoNo.anterior = null;
        novoNo.proximo = null;
        
        if ( estaVazia() ) {
            cabeca = novoNo;
            cauda = novoNo;
        } else {
            cauda.anterior = novoNo;
            novoNo.proximo = cauda;
            cauda = novoNo;
        }
        
        tamanho++;
        
    }
    
    /**
     * Remove um elemento do início da deque.
     * 
     * @return O elemento removido do início.
     */
    public Tipo removerInicio() throws EmptyDequeException {
        
        if ( !estaVazia() ) {
            
            Tipo valor = cabeca.valor;
            
            // cabeça e cauda apontam para o mesmo objeto
            if ( cabeca == cauda ) {
                cabeca = null;
                cauda = null;
            } else {
                No<Tipo> temp = cabeca;
                cabeca = cabeca.anterior;
                cabeca.proximo = null;
                temp.anterior = null;
            }
            
            tamanho--;
            return valor;
            
        } else {
            throw new EmptyDequeException();
        }
        
    }
    
    /**
     * Remove um elemento do fim da deque.
     * 
     * @return O elemento removido do fim.
     */
    public Tipo removerFim() throws EmptyDequeException {
        
        if ( !estaVazia() ) {
            
            Tipo valor = cauda.valor;
            
            // cabeça e cauda apontam para o mesmo objeto
            if ( cabeca == cauda ) {
                cabeca = null;
                cauda = null;
            } else {
                No<Tipo> temp = cauda;
                cauda = cauda.proximo;
                cauda.anterior = null;
                temp.proximo = null;
            }
            
            tamanho--;
            return valor;
            
        } else {
            throw new EmptyDequeException();
        }
        
    }
    
    /**
     * Retorna o elemento que está no início da deque, sem removê-lo.
     * 
     * @return O elemento no início da deque.
     */
    public Tipo consultarInicio() throws EmptyDequeException {
        
        if ( !estaVazia() ) {
            return cabeca.valor;
        } else {
            throw new EmptyDequeException();
        }
        
    }
    
    /**
     * Retorna o elemento que está no fim da deque, sem removê-lo.
     * 
     * @return O elemento no fim da deque.
     */
    public Tipo consultarFim() throws EmptyDequeException {
        
        if ( !estaVazia() ) {
            return cauda.valor;
        } else {
            throw new EmptyDequeException();
        }
        
    }
    
    /**
     * Esvazia a deque.
     */
    public void esvaziar() {
        
        while ( !estaVazia() ) {
            removerInicio();
        }
        
        // ou
        /*try {
            while ( true ) {
                removerInicio();
            }
        } catch ( EmptyDequeException exc ) {
        }*/
        
    }
    
    /**
     * Verifica se a deque está vazia.
     * 
     * @return true se a deque estiver vazia, false caso contrário.
     */
    public boolean estaVazia() {
        return tamanho == 0; // ou cabeca == null;
    }
    
    /**
     * Obtém o tamanho da deque (quantidade de elementos).
     * 
     * @return O tamanho da deque.
     */
    public int getTamanho() {
        return tamanho;
    }
    
    /**
     * Cria uma representação em String da deque.
     * Esta representação apresenta os elementos no sentido cabeça -> cauda.
     */
    @Override
    public String toString() {
        
        StringBuilder sb = new StringBuilder();
        
        if ( !estaVazia() ) {
            
            // percorrendo a estrutura encadeada
            No<Tipo> atual = cabeca;

            while ( atual != null ) {

                if ( tamanho == 1 ) {
                    sb.append( atual.valor ).append( " <- cabeça/cauda\n" );
                } else if ( atual == cabeca ) {
                    sb.append( atual.valor ).append( " <- cabeça\n" );
                } else if ( atual == cauda ) {
                    sb.append( atual.valor ).append( " <- cauda\n" );
                } else {
                    sb.append( atual.valor ).append( "\n" );
                }

                atual = atual.anterior;

            }

            
            // ou usando o iterador
            /*int i = tamanho - 1;
            
            for ( Tipo atual : this ) {

                if ( tamanho == 1 ) {
                    sb.append( atual ).append( " <- cabeça/cauda\n" );
                } else if ( i == tamanho - 1 ) {
                    sb.append( atual ).append( " <- cabeça\n" );
                } else if ( i == 0 ) {
                    sb.append( atual ).append( " <- cauda\n" );
                } else {
                    sb.append( atual ).append( "\n" );
                }
                
                i--;

            }*/
            
        } else {
            sb.append( "deque vazia!\n" );
        }
        
        return sb.toString();
        
    }
    
    /**
     * Cria um iterador para a deque, permitindo iterar por todos os elementos
     * da mesma, além de poder usar em um for each (for melhorado).
     * Este iterador percorre a deque no sentido cabeça -> cauda.
     */
    @Override
    public Iterator<Tipo> iterator() {
        
        return new Iterator<Tipo>() {
            
            private No<Tipo> atual = cabeca;
            
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
     * Testes da deque.
     * 
     * @param args
     */
    public static void main( String[] args ) {
        
        Deque<Integer> deque = new Deque<>();
        
        deque.inserirInicio( 10 );
        System.out.println( deque );
        deque.inserirFim( 5 );
        System.out.println( deque );
        deque.inserirInicio( -2 );
        System.out.println( deque );
        deque.inserirFim( 3 );
        System.out.println( deque );
        deque.inserirInicio( 7 );
        System.out.println( deque );
        
        // usando o iterador
        for ( int v : deque ) {
            System.out.println( "Item: " + v );
        }
        System.out.println();
        
        System.out.println( "Removeu do Início: " + deque.removerInicio() );
        System.out.println( deque );
        System.out.println( "Removeu do Fim: " + deque.removerFim() );
        System.out.println( deque );
        System.out.println( "Removeu do Início: " + deque.removerInicio() );
        System.out.println( deque );
        System.out.println( "Removeu do Fim: " + deque.removerFim() );
        System.out.println( deque );
        System.out.println( "Removeu do Início: " + deque.removerInicio() );
        System.out.println( deque );
        //System.out.println( "Removeu do Fim: " + deque.removerFim() ); // <- deque vazia!
        //System.out.println( "Removeu do Inicio: " + deque.removerInicio() ); // <- deque vazia!
        
    }
    
}
