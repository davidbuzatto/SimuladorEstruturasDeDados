/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package estruturas;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * Implementação de uma tabela de símbolos usando árvore binária de busca balanceada chave/valor.
 * 
 * @author David Buzatto
 */
public class TabelaSimbolosABBB<TipoChave extends Comparable<? super TipoChave>, TipoValor> implements 
        Iterable<TabelaSimbolosABBB<TipoChave, TipoValor>.Entrada<TipoChave, TipoValor>> {
    
    public class Entrada<TipoChave, TipoValor> {
        
        public TipoChave chave;
        public TipoValor valor;

        @Override
        public String toString() {
            return chave + " -> " + valor;
        }
        
    }
    
    private ArvoreVermelhoPretoCV<TipoChave, TipoValor> abbb;
    
    /**
     * Constrói uma tabela de símbolos vazia.
     */
    public TabelaSimbolosABBB() {
        abbb = new ArvoreVermelhoPretoCV<>();
    }
    
    /**
     * Insere um novo valor na tabela de símbolos.
     * 
     * @param chave chave usada para armazenar o valor.
     * @param valor valor a ser armazenado.
     */
    public void inserir( TipoChave chave, TipoValor valor ) {
        abbb.inserir( chave, valor );
    }
    
    /**
     * Obtém o valor contido na tabela de símbolos que está associado à chave
     * passada.
     * 
     * @param chave chave a ser utilizada na pesquisa.
     * @return valor encontrado na posição. Retorna null caso não encontre.
     */
    public TipoValor obter( TipoChave chave ) {
        return abbb.obter( chave );
    }
    
    /**
     * Remove o elemento associado à chave, inclusive a chave da tabela de 
     * símbolos.
     * 
     * @param chave chave a ser utilizada na pesquisa.
     */
    public void remover( TipoChave chave ) {
        abbb.remover( chave );
    }
    
    /**
     * Verifica se a tabela de símbolos contém um elemento indexado com a chave
     * especificada.
     * 
     * @param chave chave a ser utilizada na busca.
     * @return true caso a tabela contenha o elemento especificado, false caso
     * contrário.
     */
    public boolean contem( TipoChave chave ) {
        return abbb.contem( chave );
    }
    
    /**
     * Esvazia a a tabela de símbolos.
     */
    public void esvaziar() {
        abbb.esvaziar();
    }
    
    /**
     * Verifica se a tabela de símbolos está vazia.
     * 
     * @return true se a tabela de símbolos estiver vazia, false caso contrário.
     */
    public boolean estaVazia() {
        return abbb.estaVazia();
    }
    
    /**
     * Obtém o tamanho da tabela de símbolos (quantidade de elementos).
     * 
     * @return O tamanho da tabela de símbolos.
     */
    public int getTamanho() {
        return chaves().size();
    }
    
    /**
     * Obtém uma coleção iterável que contém as chaves da tabela de símbolos.
     * 
     * @return Coleção com as chaves da tabela de símbolos.
     */
    public List<TipoChave> chaves() {
        
        List<TipoChave> chaves = new ArrayList<>();
        
        for ( ArvoreVermelhoPretoCV<TipoChave, TipoValor>.No<TipoChave, TipoValor> e : abbb ) {
            chaves.add( e.chave );
        }
        
        return chaves;
        
    }
    
    /**
     * Cria uma representação em String da tabela de símbolos.
     * Esta representação apresenta os elementos no sentido primeiro -> último.
     */
    @Override
    public String toString() {
        
        StringBuilder sb = new StringBuilder();
        
        if ( !estaVazia() ) {
            
            for ( TipoChave c : chaves() ) {
                
                TipoValor v = obter( c );
                sb.append( String.format( "[%s] - ", c ) )
                            .append( v ).append( "\n" );
                
            }
            
        } else {
            sb.append( "tabela de símbolos vazia!\n" );
        }
        
        return sb.toString();
        
    }
    
    /**
     * Cria um iterador para a tabela de símbolos, permitindo iterar por todos
     * os elementos da mesma, além de poder usar em um for each (for melhorado).
     * Este iterador percorre a tabela de símbolos no sentido primeiro -> último.
     */
    @Override
    public Iterator<Entrada<TipoChave, TipoValor>> iterator() {
        
        List<Entrada<TipoChave, TipoValor>> elementos = new ArrayList<>();
        
        for ( TipoChave c : chaves() ) {
            Entrada<TipoChave, TipoValor> e = new Entrada<>();
            e.chave = c;
            e.valor = abbb.obter( c );
            elementos.add( e );
        }
        
        return elementos.iterator();
        
    }
    
    public static void main( String[] args ) {
        
        TabelaSimbolosABB<String, String> tabelaSimbolos = new TabelaSimbolosABB<>();
        
        tabelaSimbolos.inserir( "A", "letra a" );
        System.out.println( tabelaSimbolos );
        tabelaSimbolos.inserir( "X", "letra x" );
        System.out.println( tabelaSimbolos );
        tabelaSimbolos.inserir( "C", "letra c" );
        System.out.println( tabelaSimbolos );
        tabelaSimbolos.inserir( "D", "letra d" );
        System.out.println( tabelaSimbolos );
        tabelaSimbolos.inserir( "A", "letra a denovo" );
        System.out.println( tabelaSimbolos );
        tabelaSimbolos.inserir( "Y", "letra y" );
        System.out.println( tabelaSimbolos );
        
        // chaves
        for ( String c : tabelaSimbolos.chaves() ) {
            System.out.println( "chave: " + c );
        }
        System.out.println();
        
        // valores usando o iterador
        for ( TabelaSimbolosABB<String, String>.Entrada<String, String> v : tabelaSimbolos ) {
            System.out.println( "Item: " + v );
        }
        System.out.println();
        
        // removendo de forma aleatória
        List<String> chaves = (List<String>) tabelaSimbolos.chaves();
        Collections.shuffle( chaves );
        for ( String c : chaves ) {
            System.out.printf( "Removeu o elemento [%s].\n", c );
            tabelaSimbolos.remover( c );
            System.out.println( tabelaSimbolos );
        }
        
    }
    
}