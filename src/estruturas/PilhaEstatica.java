/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package estruturas;

import estruturas.excecoes.StackOverflowException;
import java.util.EmptyStackException;
import java.util.Iterator;

/**
 * Implementação de uma pilha estática genérica.
 * 
 * @param <Tipo> Tipo dos elementos armazenados na pilha.
 * 
 * @author David Buzatto
 */
public class PilhaEstatica<Tipo> implements Iterable<Tipo> {
    
    // itens armazenados na pilha
    private Tipo[] valores;
    
    // topo da pilha
    private int topo;
    
    // tamanho da pilha
    private int tamanho;
    
    // tamanho máximo suportado pela pilha
    private int tamanhoMaximo;
    
    /**
     * Constrói uma pilha vazia que suporta dez itens.
     */
    public PilhaEstatica() {
        this( 10 );
    }
    
    /**
     * Constrói uma pilha vazia de tamanho especificado.
     * 
     * @param maximo Tamanho máximo da pilha.
     */
    public PilhaEstatica( int maximo ) {
        
        tamanhoMaximo = maximo;
        valores = (Tipo[]) new Object[tamanhoMaximo];
        topo = -1;
        
    }
    
    /**
     * Empilha um elemento na pilha.
     * Esta operação é conhecida como "push".
     * 
     * @param valor Elemento a ser empilhado.
     */
    public void empilhar( Tipo valor ) throws StackOverflowException {
        
        if ( topo < tamanhoMaximo - 1 ) {
            topo++;
            valores[topo] = valor;
            tamanho++;
        } else {
            throw new StackOverflowException();
        }
        
    }
    
    /**
     * Desempilha um elemento da pilha.
     * Esta operação é conhecida como "pop".
     * 
     * @return O elemento desempilhado.
     */
    public Tipo desempilhar() throws EmptyStackException {
        
        if ( !estaVazia() ) {
            Tipo valor = valores[topo];
            valores[topo] = null;      // marca como null para coleta de lixo
            topo--;
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
            return valores[topo];
        } else {
            throw new EmptyStackException();
        }
        
    }
    
    /**
     * Esvazia a pilha.
     */
    public void esvaziar() {
        
        while ( !estaVazia() ) {
            desempilhar();
        }
        
    }
    
    /**
     * Verifica se a pilha está vazia.
     * 
     * @return true se a pilha estiver vazia, false caso contrário.
     */
    public boolean estaVazia() {
        return tamanho == 0; // ou topo == -1;
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
            
            // percorrendo o array de valores
            for ( int i = topo; i >= 0; i-- ) {

                if ( i == topo ) {
                    sb.append( valores[i] ).append( " <- topo\n" );
                } else {
                    sb.append( valores[i] ).append( "\n" );
                }

            }


            // ou usando o iterador
            /*int i = topo;
            
            for ( Tipo atual : this ) {

                if ( i == topo ) {
                    sb.append( atual ).append( " <- topo\n" );
                } else {
                    sb.append( atual ).append( "\n" );
                }
                
                i--;

            }*/
        
        } else {
            sb.append( "pilha vazia!\n" );
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
            
            private int atual = topo;
            
            @Override
            public boolean hasNext() {
                return atual != -1;
            }

            @Override
            public Tipo next() {
                return valores[atual--];
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
        
        PilhaEstatica<Integer> pilha = new PilhaEstatica<>(5);
        
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
        //pilha.empilhar( 15 ); // <- estouro da pilha!
        
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
