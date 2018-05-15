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
 * Obs: implementação com a marcação da cabeça para a direita e da
 * cauda para a esquerda. Utiliza também o operador de módulo para o mapeamento
 * dos endereços, sendo que as mudanças de posições dos índices deve ser feita
 * utilizando os métodos incrementarCabeca/Cauda ou decrementarCabeca/Cauda. 
 * Dependendo do lado da cabeça/cauda é necessário mudar as operações de 
 * incremento e decremento. Essa implementação está ao contrário da 
 * FilaEstaticaModulo.
 * 
 * A cabeça anda para a direita na inserção no ínicio e para a esquerda na 
 * remoção do início. A cauda anda para a esquera na inserção no fim e para a 
 * direita na remoção do fim.
 * 
 * @param <Tipo> Tipo dos elementos armazenados na deque.
 * 
 * @author David Buzatto
 */
public class DequeEstaticaModulo<Tipo> implements Iterable<Tipo> {
    
    // itens armazenados na deque
    private Tipo[] valores;
    
    // cabeça da fila (início) - em inglês, head
    private int cabeca;
    
    // cabeça da fila (fim) - em inglês, tail
    private int cauda;
    
    // tamanho da deque
    private int tamanho;
    
    // tamanho máximo suportado pela deque
    private int tamanhoMaximo;
    
    /**
     * Constrói uma deque vazia que suporta dez itens.
     */
    public DequeEstaticaModulo() {
        this( 10 );
    }
    
    /**
     * Constrói uma deque vazia de tamanho especificado.
     * 
     * @param maximo Tamanho máximo da deque.
     */
    @SuppressWarnings( "unchecked" )
    public DequeEstaticaModulo( int maximo ) {
        
        tamanhoMaximo = maximo;
        valores = (Tipo[]) new Object[tamanhoMaximo];
        cabeca = Integer.MIN_VALUE;
        cauda = Integer.MIN_VALUE;
        
    }
    
    /**
     * Insere um elemento no início (cabeça) da deque.
     * 
     * @param valor Elemento a ser inserido no início.
     */
    public void inserirInicio( Tipo valor ) throws DequeOverflowException {
        
        if ( tamanho < tamanhoMaximo ) {
            
            if ( estaVazia() ) {
                cabeca = 0;
                cauda = 0;
            } else {
                incrementarCabeca();
            }
            
            valores[cabeca] = valor;
            tamanho++;
            
        } else {
            throw new DequeOverflowException();
        }
        
    }
    
    /**
     * Insere um elemento no final (cauda) da deque.
     * 
     * @param valor Elemento a ser inserido no final.
     */
    public void inserirFim( Tipo valor ) throws DequeOverflowException {
        
        if ( tamanho < tamanhoMaximo ) {
            
            if ( estaVazia() ) {
                cabeca = 0;
                cauda = 0;
            } else {
                decrementarCauda();
            }
            
            valores[cauda] = valor;
            tamanho++;
            
        } else {
            throw new DequeOverflowException();
        }
        
    }
    
    /**
     * Remove um elemento do início (cabeça) da deque.
     * 
     * @return O elemento removido do início.
     */
    public Tipo removerInicio() throws EmptyDequeException {
        
        if ( !estaVazia() ) {
            
            Tipo valor = valores[cabeca];
            valores[cabeca] = null;      // marca como null para coleta de lixo
            
            decrementarCabeca();
            tamanho--;
            
            if ( estaVazia() ) {
                cabeca = Integer.MIN_VALUE;
                cauda = Integer.MIN_VALUE;
            }
            
            return valor;
            
        } else {
            throw new EmptyDequeException();
        }
        
    }
    
    /**
     * Remove um elemento do fim (cauda) da deque.
     * 
     * @return O elemento removido do fim.
     */
    public Tipo removerFim() throws EmptyDequeException {
        
        if ( !estaVazia() ) {
            
            Tipo valor = valores[cauda];
            valores[cauda] = null;      // marca como null para coleta de lixo
            
            incrementarCauda();
            tamanho--;
            
            if ( estaVazia() ) {
                cabeca = Integer.MIN_VALUE;
                cauda = Integer.MIN_VALUE;
            }
            
            return valor;
            
        } else {
            throw new EmptyDequeException();
        }
        
    }
    
    /**
     * Retorna o elemento que está no início (cabeça) da deque, sem removê-lo.
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
     * Retorna o elemento que está no fim (cauda) da deque, sem removê-lo.
     * 
     * @return O elemento no fim da deque.
     */
    public Tipo consultarFim() throws EmptyDequeException {
        
        if ( !estaVazia() ) {
            return valores[cauda];
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
        
    }
    
    /**
     * Verifica se a deque está vazia.
     * 
     * @return true se a deque estiver vazia, false caso contrário.
     */
    public boolean estaVazia() {
        return tamanho == 0;
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
            for ( int i = 0; i < tamanho; i++ ) {
                
                int mapeamento = cabeca - i;
                
                if ( mapeamento < 0 ) {
                    if ( -mapeamento > tamanhoMaximo ) {
                        mapeamento = tamanhoMaximo + 
                                ( mapeamento % tamanhoMaximo ) - 
                                ( mapeamento % tamanhoMaximo == 0 ? tamanhoMaximo : 0 );
                    } else {
                        mapeamento = tamanhoMaximo + mapeamento;
                    }
                } else {
                    mapeamento = mapeamento % tamanhoMaximo;
                }
                
                if ( tamanho == 1 ) {
                    sb.append( valores[mapeamento] ).append( " <- cabeça/cauda\n" );
                } else if ( mapeamento == cabeca ) {
                    sb.append( valores[mapeamento] ).append( " <- cabeça\n" );
                } else if ( mapeamento == cauda ) {
                    sb.append( valores[mapeamento] ).append( " <- cauda\n" );
                } else {
                    sb.append( valores[mapeamento] ).append( "\n" );
                }

            }

            
            // ou usando o iterador
            /*int i = 0;
            
            for ( Tipo atual : this ) {

                int mapeamento = cabeca - i;
                
                if ( mapeamento < 0 ) {
                    if ( -mapeamento > tamanhoMaximo ) {
                        mapeamento = tamanhoMaximo + 
                                ( mapeamento % tamanhoMaximo ) - 
                                ( mapeamento % tamanhoMaximo == 0 ? tamanhoMaximo : 0 );
                    } else {
                        mapeamento = tamanhoMaximo + mapeamento;
                    }
                } else {
                    mapeamento = mapeamento % tamanhoMaximo;
                }
                
                if ( tamanho == 1 ) {
                    sb.append( atual ).append( " <- cabeça/cauda\n" );
                } else if ( mapeamento == cabeca ) {
                    sb.append( atual ).append( " <- cabeça\n" );
                } else if ( mapeamento == cauda ) {
                    sb.append( atual ).append( " <- cauda\n" );
                } else {
                    sb.append( atual ).append( "\n" );
                }
                
                i++;
                
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
            
            private int atual = 0;
            
            @Override
            public boolean hasNext() {
                return atual < tamanho;
            }

            @Override
            public Tipo next() {
                
                int mapeamento = cabeca - atual;
                
                if ( mapeamento < 0 ) {
                    if ( -mapeamento > tamanhoMaximo ) {
                        mapeamento = tamanhoMaximo + 
                                ( mapeamento % tamanhoMaximo ) - 
                                ( mapeamento % tamanhoMaximo == 0 ? tamanhoMaximo : 0 );
                    } else {
                        mapeamento = tamanhoMaximo + mapeamento;
                    }
                } else {
                    mapeamento = mapeamento % tamanhoMaximo;
                }
                
                atual++;
                return valores[mapeamento];
                
            }
            
            @Override
            public void remove() {
                throw new UnsupportedOperationException( "Não suportado." );
            }
            
        };
        
    }
    
    private void incrementarCabeca() {
        cabeca++;
        cabeca %= tamanhoMaximo;
    }
    
    private void decrementarCabeca() {
        cabeca--;
        cabeca = cabeca < 0 ? tamanhoMaximo - 1 : cabeca % tamanhoMaximo;
    }
    
    private void incrementarCauda() {
        cauda++;
        cauda %= tamanhoMaximo;
    }
    
    private void decrementarCauda() {
        cauda--;
        cauda = cauda < 0 ? tamanhoMaximo - 1 : cauda % tamanhoMaximo;
    }
    
    /**
     * Testes da deque.
     * 
     * @param args
     */
    public static void main( String[] args ) {
        
        DequeEstaticaModulo<Integer> deque = new DequeEstaticaModulo<>(5);
        
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
