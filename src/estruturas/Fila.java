/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package estruturas;

import estruturas.excecoes.EmptyQueueException;
import java.util.Iterator;

/**
 * Implementação de uma fila dinâmica genérica, usando encadeamento simples.
 * 
 * @param <Tipo> Tipo dos elementos armazenados na fila.
 * 
 * @author David Buzatto
 */
public class Fila<Tipo> implements Iterable<Tipo> {
    
    /*
     * Classe privada que define os nós da fila.
     */
    private class No<Tipo> {
        Tipo valor;
        No<Tipo> anterior;
    }
    
    // cabeça a cauda da fila
    private No<Tipo> cabeca;
    private No<Tipo> cauda;
    
    // tamanho da fila
    private int tamanho;
    
    /**
     * Constrói uma fila vazia.
     */
    public Fila() {
        cauda = null;
        cabeca = null;
        tamanho = 0;
    }
    
    /**
     * Enfileira um elemento na fila.
     * Esta operação é conhecida como "insert" ou "add" ou "offer" ou "enqueue".
     * 
     * @param valor Elemento a ser enfileirado.
     */
    public void enfileirar( Tipo valor ) {
        
        No<Tipo> novoNo = new No<>();
        novoNo.valor = valor;
        novoNo.anterior = null;
        
        if ( estaVazia() ) {
            cabeca = novoNo;
            cauda = novoNo;
        } else {
            cauda.anterior = novoNo;
            cauda = novoNo;
        }
        
        tamanho++;
        
    }
    
    /**
     * Desenfileira um elemento da fila.
     * Esta operação é conhecida como "remove" ou "delete" ou "poll" ou "dequeue".
     * 
     * @return O elemento desenfileirado.
     */
    public Tipo desenfileirar() throws EmptyQueueException {
        
        if ( !estaVazia() ) {
            
            Tipo valor = cabeca.valor;
            
            // cabeça e cauda apontam para o mesmo objeto
            if ( cabeca == cauda ) {
                cabeca = null;
                cauda = null;
            } else {
                No<Tipo> temp = cabeca;
                cabeca = cabeca.anterior;
                temp.anterior = null;
            }
            
            tamanho--;
            return valor;
            
        } else {
            throw new EmptyQueueException();
        }
        
    }
    
    /**
     * Retorna o elemento que está na cabeca fila, sem desenfileirá-lo.
     * Esta operação é conhecida como "peek" ou "element".
     * 
     * @return O elemento na cabeça da fila.
     */
    public Tipo consultarCabeca() throws EmptyQueueException {
        
        if ( !estaVazia() ) {
            return cabeca.valor;
        } else {
            throw new EmptyQueueException();
        }
        
    }
    
    /**
     * Esvazia a fila.
     */
    public void esvaziar() {
        
        try {
            while ( true ) {
                desenfileirar();
            }
        } catch ( EmptyQueueException exc ) {
        }
        
    }
    
    /**
     * Verifica se a fila está vazia.
     * 
     * @return true se a fila estiver vazia, false caso contrário.
     */
    public boolean estaVazia() {
        return cabeca == null;
    }
    
    /**
     * Obtém o tamanho da fila (quantidade de elementos).
     * 
     * @return O tamanho da fila.
     */
    public int getTamanho() {
        return tamanho;
    }
    
    /**
     * Cria uma representação em String da fila.
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
            sb.append( "fila vazia!\n" );
        }
        
        return sb.toString();
        
    }
    
    /**
     * Cria um iterador para a fila, permitindo iterar por todos os elementos
     * da mesma, além de poder usar em um for each (for melhorado).
     * Este iterador percorre a fila no sentido cabeça -> cauda.
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
     * Testes da fila.
     * 
     * @param args
     */
    public static void main( String[] args ) {
        
        Fila<Integer> fila = new Fila<>();
        
        fila.enfileirar( 10 );
        System.out.println( fila );
        fila.enfileirar( 5 );
        System.out.println( fila );
        fila.enfileirar( -2 );
        System.out.println( fila );
        fila.enfileirar( 3 );
        System.out.println( fila );
        fila.enfileirar( 7 );
        System.out.println( fila );
        
        // usando o iterador
        for ( int v : fila ) {
            System.out.println( "Item: " + v );
        }
        System.out.println();
        
        System.out.println( "Desenfileirou: " + fila.desenfileirar() );
        System.out.println( fila );
        System.out.println( "Desenfileirou: " + fila.desenfileirar() );
        System.out.println( fila );
        System.out.println( "Desenfileirou: " + fila.desenfileirar() );
        System.out.println( fila );
        System.out.println( "Desenfileirou: " + fila.desenfileirar() );
        System.out.println( fila );
        System.out.println( "Desenfileirou: " + fila.desenfileirar() );
        System.out.println( fila );
        //System.out.println( "Desenfileirou: " + fila.desenfileirar() ); // <- fila vazia!
        
    }
    
}
