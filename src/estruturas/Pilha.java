/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package estruturas;

import java.util.EmptyStackException;
import java.util.Iterator;

/**
 * Implementação de uma pilha dinâmica genérica, utilizando encadeamento simples.
 * 
 * @param <Tipo> Tipo dos elementos armazenados na pilha.
 * 
 * @author David Buzatto
 */
public class Pilha<Tipo> implements Iterable<Tipo> {
    
    /*
     * Classe privada que define os nós da pilha.
     */
    private class No<Tipo> {
        Tipo valor;
        No<Tipo> anterior;
    }
    
    // topo da pilha
    private No<Tipo> topo;
    
    // tamanho da pilha
    private int tamanho;
    
    /**
     * Constrói uma pilha vazia.
     */
    public Pilha() {
        topo = null;   // redundante, apenas para mostrar o que acontece.
        tamanho = 0;   // redundante também
    }
    
    /**
     * Empilha um elemento na pilha.
     * Esta operação é conhecida como "push".
     * 
     * @param valor Elemento a ser empilhado.
     */
    public void empilhar( Tipo valor ) {
        
        No<Tipo> novoNo = new No<>();
        
        novoNo.valor = valor;
        novoNo.anterior = topo;
        topo = novoNo;
        
        tamanho++;
        
    }
    
    /**
     * Desempilha um elemento da pilha.
     * Esta operação é conhecida como "pop".
     * 
     * @return O elemento desempilhado.
     */
    public Tipo desempilhar() throws EmptyStackException {
        
        if ( !estaVazia() ) {
            Tipo valor = topo.valor;
            topo = topo.anterior;
            tamanho--;
            return valor;
        } else {
            throw new EmptyStackException();
        }
        
    }
    
    /**
     * Retorna o elemento que está no topo da pilha, sem desempilhá-lo.
     * Esta operação é conhecida como "top".
     * 
     * @return O elemento no topo da pilha.
     */
    public Tipo consultarTopo() throws EmptyStackException {
        
        if ( !estaVazia() ) {
            return topo.valor;
        } else {
            throw new EmptyStackException();
        }
        
    }
    
    /**
     * Esvazia a pilha.
     */
    public void esvaziar() {
        
        try {
            while ( true ) {
                desempilhar();
            }
        } catch ( EmptyStackException exc ) {
        }
        
    }
    
    /**
     * Verifica se a pilha está vazia.
     * 
     * @return true se a pilha estiver vazia, false caso contrário.
     */
    public boolean estaVazia() {
        return topo == null;
    }
    
    /**
     * Obtém o tamanho da pilha (quantidade de elementos).
     * 
     * @return O tamanho da pilha.
     */
    public int getTamanho() {
        return tamanho;
    }
    
    /**
     * Cria uma representação em String da pilha.
     * Esta representação apresenta os elementos no sentido topo -> base.
     */
    @Override
    public String toString() {
        
        StringBuilder sb = new StringBuilder();
        
        if ( !estaVazia() ) {

            // percorrendo a estrutura encadeada
            No<Tipo> atual = topo;

            while ( atual != null ) {

                if ( atual == topo ) {
                    sb.append( atual.valor ).append( " <- topo\n" );
                } else {
                    sb.append( atual.valor ).append( "\n" );
                }

                atual = atual.anterior;

            }


            // ou usando o iterador
            /*int i = tamanho - 1;
            
            for ( Tipo atual : this ) {

                if ( i == tamanho - 1 ) {
                    sb.append( atual ).append( " <- topo\n" );
                } else {
                    sb.append( atual ).append( "\n" );
                }

                i--;

            }*/
        
        } else {
            System.out.println( "pilha vazia!\n" );
        }
        
        return sb.toString();
        
    }
    
    /**
     * Cria um iterador para a pilha, permitindo iterar por todos os elementos
     * da mesma, além de poder usar em um for each (for melhorado).
     * Este iterador percorre a fila no sentido topo -> base.
     */
    @Override
    public Iterator<Tipo> iterator() {
        
        return new Iterator<Tipo>() {
            
            private No<Tipo> atual = topo;
            
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
     * Testes da pilha.
     * 
     * @param args
     */
    public static void main( String[] args ) {
        
        Pilha<Integer> pilha = new Pilha<>();
        
        pilha.empilhar( 10 );
        System.out.println( pilha );
        pilha.empilhar( 5 );
        System.out.println( pilha );
        pilha.empilhar( -2 );
        System.out.println( pilha );
        pilha.empilhar( 3 );
        System.out.println( pilha );
        pilha.empilhar( 7 );
        System.out.println( pilha );
        
        // usando o iterador
        for ( int v : pilha ) {
            System.out.println( "Item: " + v );
        }
        System.out.println();
        
        System.out.println( "Desempilhou: " + pilha.desempilhar() );
        System.out.println( pilha );
        System.out.println( "Desempilhou: " + pilha.desempilhar() );
        System.out.println( pilha );
        System.out.println( "Desempilhou: " + pilha.desempilhar() );
        System.out.println( pilha );
        System.out.println( "Desempilhou: " + pilha.desempilhar() );
        System.out.println( pilha );
        System.out.println( "Desempilhou: " + pilha.desempilhar() );
        System.out.println( pilha );
        //System.out.println( "Desempilhou: " + pilha.desempilhar() ); // <- pilha vazia!
        
    }
    
}
