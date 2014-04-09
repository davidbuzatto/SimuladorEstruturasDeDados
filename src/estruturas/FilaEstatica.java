/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package estruturas;

import estruturas.excecoes.EmptyQueueException;
import estruturas.excecoes.QueueOverflowException;
import java.util.Iterator;

/**
 * Implementação de uma fila estática genérica.
 * 
 * @param <Tipo> Tipo dos elementos armazenados na fila.
 * 
 * @author David Buzatto
 */
public class FilaEstatica<Tipo> implements Iterable<Tipo> {
    
    // itens armazenados na fila
    private Tipo[] valores;
    
    // cabeça da fila (início)
    private int cabeca;
    
    // tamanho da fila
    private int tamanho;
    
    // tamanho máximo suportado pela fila
    private int tamanhoMaximo;
    
    /**
     * Constrói uma fila vazia que suporta dez itens.
     */
    public FilaEstatica() {
        this( 10 );
    }
    
    /**
     * Constrói uma fila vazia de tamanho especificado.
     * 
     * @param maximo Tamanho máximo da fila.
     */
    public FilaEstatica( int maximo ) {
        
        tamanhoMaximo = maximo;
        valores = (Tipo[]) new Object[tamanhoMaximo];
        cabeca = -1;
        
    }
    
    /**
     * Enfileira um elemento na fila.
     * Esta operação é conhecida como "insert" ou "add" ou "offer" ou "enqueue".
     * 
     * @param valor Elemento a ser enfileirado.
     */
    public void enfileirar( Tipo valor ) throws QueueOverflowException {
        
        if ( cabeca < tamanhoMaximo - 1 ) {
            
            // realoca os valores (faz a fila andar)
            for ( int i = cabeca; i >= 0; i-- ) {
                valores[i+1] = valores[i];
            }
            
            valores[0] = valor;
            cabeca++;
            tamanho++;
            
        } else {
            throw new QueueOverflowException();
        }
        
    }
    
    /**
     * Desenfileira um elemento da fila.
     * Esta operação é conhecida como "remove" ou "delete" ou "poll" ou "dequeue".
     * 
     * @return O elemento desenfileirado.
     */
    public Tipo desenfileirar() throws EmptyQueueException {
        
        if ( !estaVazia() ) {
            Tipo valor = valores[cabeca];
            valores[cabeca] = null;      // marca como null para coleta de lixo
            cabeca--;
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
            return valores[cabeca];
        } else {
            throw new EmptyQueueException();
        }
        
    }
    
    /**
     * Esvazia a fila.
     */
    public void esvaziar() {
        
        for ( int i = 0; i <= cabeca; i++ ) {
            valores[i] = null;      // marca como null para coleta de lixo
        }
        
        tamanho = 0;
        cabeca = -1;
        
    }
    
    /**
     * Verifica se a fila está vazia.
     * 
     * @return true se a fila estiver vazia, false caso contrário.
     */
    public boolean estaVazia() {
        return cabeca == -1;
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
            
            // percorrendo o array de valores
            for ( int i = cabeca; i >= 0; i-- ) {
                
                if ( tamanho == 1 ) {
                    sb.append( valores[i] ).append( " <- cabeça/cauda\n" );
                } else if ( i == cabeca ) {
                    sb.append( valores[i] ).append( " <- cabeça\n" );
                } else if ( i == 0 ) {
                    sb.append( valores[i] ).append( " <- cauda\n" );
                } else {
                    sb.append( valores[i] ).append( "\n" );
                }

            }

            
            // ou usando o iterador
            /*int i = tamanho - 1;
            
            for ( Tipo atual : this ) {

                if ( tamanho == 1 ) {
                    sb.append( atual ).append( " <- cabeça/cauda\n" );
                } else if ( i == cabeca ) {
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
            
            private int atual = cabeca;
            
            @Override
            public boolean hasNext() {
                return atual >= 0;
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
     * Testes da fila.
     * 
     * @param args
     */
    public static void main( String[] args ) {
        
        FilaEstatica<Integer> fila = new FilaEstatica<>(5);
        
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
        //fila.enfileirar( 15 ); // <- estouro da fila!
        
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
