/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package estruturas;

import estruturas.excecoes.DequeOverflowException;
import estruturas.excecoes.EmptyDequeException;
import java.util.Iterator;

/**
 * Implementação de uma deque estática genérica.
 * 
 * @param <Tipo> Tipo dos elementos armazenados na deque.
 * 
 * @author David Buzatto
 */
public class DequeEstatica<Tipo> implements Iterable<Tipo> {
    
    // itens armazenados na deque
    private Tipo[] valores;
    
    // cabeça da deque (início)
    private int cabeca;
    
    // tamanho da deque
    private int tamanho;
    
    // tamanho máximo suportado pela deque
    private int tamanhoMaximo;
    
    /**
     * Constrói uma deque vazia que suporta dez itens.
     */
    public DequeEstatica() {
        this( 10 );
    }
    
    /**
     * Constrói uma deque vazia de tamanho especificado.
     * 
     * @param maximo Tamanho máximo da deque.
     */
    public DequeEstatica( int maximo ) {
        
        tamanhoMaximo = maximo;
        valores = (Tipo[]) new Object[tamanhoMaximo];
        cabeca = -1;
        
    }
    
    /**
     * Insere um elemento no início da deque.
     * 
     * @param valor Elemento a ser inserido no início.
     */
    public void inserirInicio( Tipo valor ) throws DequeOverflowException {
        
        if ( cabeca < tamanhoMaximo - 1 ) {
            cabeca++;
            valores[cabeca] = valor;
            tamanho++;
        } else {
            throw new DequeOverflowException();
        }
        
    }
    
    /**
     * Insere um elemento no final da deque.
     * 
     * @param valor Elemento a ser inserido no final.
     */
    public void inserirFim( Tipo valor ) throws DequeOverflowException {
        
        if ( cabeca < tamanhoMaximo - 1 ) {
            
            // realoca os valores (faz a deque andar para frente)
            for ( int i = cabeca; i >= 0; i-- ) {
                valores[i+1] = valores[i];
            }
            
            valores[0] = valor;
            cabeca++;
            tamanho++;
            
        } else {
            throw new DequeOverflowException();
        }
        
    }
    
    /**
     * Remove um elemento do início da deque.
     * 
     * @return O elemento removido do início.
     */
    public Tipo removerInicio() throws EmptyDequeException {
        
        if ( !estaVazia() ) {
            Tipo valor = valores[cabeca];
            valores[cabeca] = null;      // marca como null para coleta de lixo
            cabeca--;
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
            
            Tipo valor = valores[0];
            
            // realoca os valores (faz a deque andar para trás)
            for ( int i = 1; i <= cabeca; i++ ) {
                valores[i-1] = valores[i];
            }
            
            valores[cabeca] = null;      // marca como null para coleta de lixo
            cabeca--;
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
            return valores[cabeca];
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
            return valores[0];
        } else {
            throw new EmptyDequeException();
        }
        
    }
    
    /**
     * Esvazia a deque.
     */
    public void esvaziar() {
        
        for ( int i = 0; i <= cabeca; i++ ) {
            valores[i] = null;      // marca como null para coleta de lixo
        }
        
        tamanho = 0;
        cabeca = -1;
        
    }
    
    /**
     * Verifica se a deque está vazia.
     * 
     * @return true se a deque estiver vazia, false caso contrário.
     */
    public boolean estaVazia() {
        return cabeca == -1;
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
            /*int i = cabeca;
            
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
     * Testes da deque.
     * 
     * @param args
     */
    public static void main( String[] args ) {
        
        DequeEstatica<Integer> deque = new DequeEstatica<>(5);
        
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
        //deque.inserirFim( 15 ); // <- estouro da deque!
        //deque.inserirInicio( 15 ); // <- estouro da deque!
        
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
