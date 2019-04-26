/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estruturas;

import estruturas.algoritmos.arvores.PercursosArvoreVermelhoPretoCV;
import estruturas.algoritmos.arvores.TipoPercursoArvores;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Implementação de uma árvore vermelho e preto para chave/valor.
 * 
 * Baseado no código de:
 *     SEDGEWICK, R.; WAYNE, K. Algorithms. 4. ed. 
 *     Pearson Education: New Jersey, 2011. 955 p.
 * 
 * @author David Buzatto
 */
public class ArvoreVermelhoPretoCV<TipoChave extends Comparable<? super TipoChave>, TipoValor> 
        implements Iterable<ArvoreVermelhoPretoCV<TipoChave, TipoValor>.No<TipoChave, TipoValor>> {

    /*
     * Classe interna que define os nós da árvore.
     * É pública para poder acessar a estrutura dos nós externamente
     * no simulador e nos percursos. Deveria ser privada.
     */
    public class No<TipoChave, TipoValor> {
        
        public TipoChave chave;
        public TipoValor valor;
        public No<TipoChave, TipoValor> esquerda;
        public No<TipoChave, TipoValor> direita;
        public CorNo cor;
        public int n;

        @Override
        public String toString() {
            return chave + " -> " + valor + " (" + ( cor == CorNo.VERMELHO ? "V" : "P" ) + ")";
        }
        
    }
    
    /*
     * Enumeração para especificação da cor do nó.
     */
    public enum CorNo {
        VERMELHO,
        PRETO
    }

    // raiz da árvore
    private No<TipoChave, TipoValor> raiz;

    
    
    /*
     * ***********************************************************************
     * Métodos utilitários para os nós. Não estão inseridos na classe nó
     * pois verificam também se o nó passado por parâmetro é null para retornar
     * ***********************************************************************
     */
    
    /**
     * Verifica se um nó é vermelho.
     * 
     * @param no O nó a ser verificado
     * @return true caso o nó seja vermelho, false caso seja preto ou null.
     */
    private boolean isVermelho( No<TipoChave, TipoValor> no ) {
        if ( no == null ) {
            return false;
        }
        return no.cor == CorNo.VERMELHO;
    }

    /**
     * Retorna o tamanho do nó.
     * 
     * @param no O nó a ser verificado.
     * @return O número do nó na sub-árvore enraizada nó ou 0 caso seja null.
     */
    // number of node in subtree rooted at x; 0 if x is null
    private int tamanho( No<TipoChave, TipoValor> no ) {
        if ( no == null ) {
            return 0;
        }
        return no.n;
    }

    
    
    /**
     * ***********************************************************************
     * Métodos para obtenção do tamanho.
     * ***********************************************************************
     */
    
    /**
     * 
     * @return return o número do par chave-valor ness árvore.
     */
    // 
    public int tamanho() {
        return tamanho( raiz );
    }

    /**
     * Verifica se a árvore está vazia.
     * 
     * @return true caso esteja vazia, false caso contrário.
     */
    public boolean estaVazia() {
        return raiz == null;
    }

    
    
    /**
     * ***********************************************************************
     * Métodos de busca padrão.
     * ***********************************************************************
     */
    
    /**
     * Obtém o valor associado a uma chave.
     * 
     * @param chave chave a ser consultada
     * @return o valor associado à chave ou null caso não haja a chave.
     */
    public TipoValor obter( TipoChave chave ) {
        return obter( raiz, chave );
    }

    /**
     * Obtém o valor associado a uma chave em uma subárvore enraizada em um nó.
     * 
     * @param no nó de origem da consulta
     * @param chave chave a ser consultada
     * @return o valor associado à chave ou null caso não haja a chave.
     */
    private TipoValor obter( No<TipoChave, TipoValor> no, TipoChave chave ) {
        
        while ( no != null ) {
            
            int comparacao = chave.compareTo( no.chave );
            
            if ( comparacao < 0 ) {
                no = no.esquerda;
            } else if ( comparacao > 0 ) {
                no = no.direita;
            } else {
                return no.valor;
            }
            
        }
        
        return null;
        
    }

    /**
     * Verifica se existe um valor associado a uma chave.
     * 
     * @param chave chave a ser consultada
     * @return true caso haja um valor associado à chave, false caso contrário.
     */
    public boolean contem( TipoChave chave ) {
        return obter( chave ) != null;
    }

    /**
     * Verifica se existe um valor associado a uma chave em uma subárvore enraizada em um nó.
     * 
     * @param no nó de origem da consulta
     * @param chave chave a ser consultada
     * @return true caso haja um valor associado à chave, false caso contrário.
     */
    private boolean contem( No<TipoChave, TipoValor> no, TipoChave chave ) {
        return obter( no, chave ) != null;
    }

    
    
    /**
     * ***********************************************************************
     * Inserção vermelho-preto
     * ***********************************************************************
     */
    
    /**
     * Insere um valor em uma chave. Caso a chave já exista, sobreescreve o valor.
     * 
     * @param chave chave para inserção
     * @param valor valor para inserção
     */
    public void inserir( TipoChave chave, TipoValor valor ) {
        
        raiz = inserir( raiz, chave, valor );
        raiz.cor = CorNo.PRETO;
        
        //assert verificar();
        
    }

    /**
     * Insere um valor em uma chave em um subárvore enraizada em um nó.
     * Caso a chave já exista, sobreescreve o valor.
     * 
     * @param no nó de origem da inserção
     * @param chave chave para inserção
     * @param valor valor para inserção
     */
    private No<TipoChave, TipoValor> inserir( No<TipoChave, TipoValor> no, TipoChave chave, TipoValor valor ) {
        
        if ( no == null ) {
            
            No<TipoChave, TipoValor> novoNo = new No<>();
            novoNo.chave = chave;
            novoNo.valor = valor;
            novoNo.cor = CorNo.VERMELHO;
            novoNo.n = 1;
            
            return novoNo;
            
        }

        int comparacao = chave.compareTo( no.chave );
        
        if ( comparacao < 0 ) {
            no.esquerda = inserir( no.esquerda, chave, valor );
        } else if ( comparacao > 0 ) {
            no.direita = inserir( no.direita, chave, valor );
        } else {
            no.valor = valor;
        }

        // consertando os links inclinados à direita
        if ( isVermelho( no.direita ) && !isVermelho( no.esquerda ) ) {
            no = rotacionarParaEsquerda( no );
        }
        if ( isVermelho( no.esquerda ) && isVermelho( no.esquerda.esquerda ) ) {
            no = rotacionarParaDireita( no );
        }
        if ( isVermelho( no.esquerda ) && isVermelho( no.direita ) ) {
            trocarCor( no );
        }
        
        no.n = tamanho( no.esquerda ) + tamanho( no.direita ) + 1;

        return no;
        
    }

    /**
     * ***********************************************************************
     * Remoção vermelho-preto
     * ***********************************************************************
     */
    
    /**
     * Remove o nó com a menor chave.
     */
    public void removerMinimo() {
        
        if ( estaVazia() ) {
            throw new NoSuchElementException( "Não há nó mínimo - BST underflow" );
        }

        // se ambos os filhos da raiz forem pretos, configura a raiz como vermelho
        if ( !isVermelho( raiz.esquerda ) && !isVermelho( raiz.direita ) ) {
            raiz.cor = CorNo.VERMELHO;
        }

        raiz = removerMinimo( raiz );
        if ( !estaVazia() ) {
            raiz.cor = CorNo.PRETO;
        }
        
        //assert verificar();
        
    }

    /**
     * Remove o nó com a menor chave da subárvore enraizada em um nó.
     */
    private No<TipoChave, TipoValor> removerMinimo( No<TipoChave, TipoValor> no ) {
        
        if ( no.esquerda == null ) {
            return null;
        }

        if ( !isVermelho( no.esquerda ) && !isVermelho( no.esquerda.esquerda ) ) {
            no = moverVermelhoEsquerda( no );
        }

        no.esquerda = removerMinimo( no.esquerda );
        
        return balancear( no );
        
    }

    /**
     * Remove o nó com a maior chave.
     */
    public void removerMaximo() {
        
        if ( estaVazia() ) {
            throw new NoSuchElementException( "Não há nó máximo - BST underflow" );
        }

        // se ambos os filhos da raiz forem pretos, configura a raiz como vermelho
        if ( !isVermelho( raiz.esquerda ) && !isVermelho( raiz.direita ) ) {
            raiz.cor = CorNo.VERMELHO;
        }

        raiz = removerMaximo( raiz );
        if ( !estaVazia() ) {
            raiz.cor = CorNo.PRETO;
        }
        
        //assert verificar();
        
    }

    /**
     * Remove o nó com a maior chave da subárvore enraizada em um nó.
     */
    private No<TipoChave, TipoValor> removerMaximo( No<TipoChave, TipoValor> no ) {
        
        if ( isVermelho( no.esquerda ) ) {
            no = rotacionarParaDireita( no );
        }

        if ( no.direita == null ) {
            return null;
        }

        if ( !isVermelho( no.direita ) && !isVermelho( no.direita.esquerda ) ) {
            no = moverVermelhoDireita( no );
        }

        no.direita = removerMaximo( no.direita );

        return balancear( no );
        
    }

    /**
     * Remove um par chave-valor de acordo com a chave passada. Caso a chave não
     * exista, não faz nada.
     * 
     * @param chave chave a ser consultada.
     */
    public void remover( TipoChave chave ) {
        
        if ( !contem( chave ) ) {
            return;
        }

        // se ambos os filhos da raiz forem pretos, configura a raiz como vermelho
        if ( !isVermelho( raiz.esquerda ) && !isVermelho( raiz.direita ) ) {
            raiz.cor = CorNo.VERMELHO;
        }

        raiz = remover( raiz, chave );
        if ( !estaVazia() ) {
            raiz.cor = CorNo.PRETO;
        }
        
        //assert verificar();
        
    }

    /**
     * Remove um par chave-valor de acordo com a chave passada de uma subárvore
     * enraizada em um nó. Caso a chave não exista, não faz nada.
     * 
     * @param chave chave a ser consultada.
     */
    private No<TipoChave, TipoValor> remover( No<TipoChave, TipoValor> no, TipoChave chave ) {
        
        //assert contem( h, chave );

        if ( chave.compareTo( no.chave ) < 0 ) {
            
            if ( !isVermelho( no.esquerda ) && !isVermelho( no.esquerda.esquerda ) ) {
                no = moverVermelhoEsquerda( no );
            }
            
            no.esquerda = remover( no.esquerda, chave );
            
        } else {
            
            if ( isVermelho( no.esquerda ) ) {
                no = rotacionarParaDireita( no );
            }
            
            if ( chave.compareTo( no.chave ) == 0 && ( no.direita == null ) ) {
                return null;
            }
            
            if ( !isVermelho( no.direita ) && !isVermelho( no.direita.esquerda ) ) {
                no = moverVermelhoDireita( no );
            }
            
            if ( chave.compareTo( no.chave ) == 0 ) {
                no.valor = obter( no.direita, minimo( no.direita ).chave );
                no.chave = minimo( no.direita ).chave;
                no.direita = removerMinimo( no.direita );
            } else {
                no.direita = remover( no.direita, chave );
            }
            
        }
        
        return balancear( no );
        
    }

    
    
    /**
     * ***********************************************************************
     * Métodos utilitários da árvore vermelho-preto
     * ***********************************************************************
     */
    
    /**
     * Faz com que o link inclinado à esquerda se incline para a direita.
     * 
     * @param no nó de origem
     * @return a nova raiz da subárvore.
     */
    private No<TipoChave, TipoValor> rotacionarParaDireita( No<TipoChave, TipoValor> no ) {
        
        //assert ( no != null ) && isVermelho( no.esquerda );
        
        No<TipoChave, TipoValor> novaRaiz = no.esquerda;
        no.esquerda = novaRaiz.direita;
        
        novaRaiz.direita = no;
        novaRaiz.cor = novaRaiz.direita.cor;
        novaRaiz.direita.cor = CorNo.VERMELHO;
        novaRaiz.n = no.n;
        
        no.n = tamanho( no.esquerda ) + tamanho( no.direita ) + 1;
        
        return novaRaiz;
        
    }

    /**
     * Faz com que o link inclinado à direita se incline para a esquerda.
     * 
     * @param no nó de origem
     * @return a nova raiz da subárvore.
     */
    private No<TipoChave, TipoValor> rotacionarParaEsquerda( No<TipoChave, TipoValor> no ) {
        
        //assert ( h != null ) && isVermelho( h.direita );
        
        No<TipoChave, TipoValor> novaRaiz = no.direita;
        no.direita = novaRaiz.esquerda;
        
        novaRaiz.esquerda = no;
        novaRaiz.cor = novaRaiz.esquerda.cor;
        novaRaiz.esquerda.cor = CorNo.VERMELHO;
        novaRaiz.n = no.n;
        
        no.n = tamanho( no.esquerda ) + tamanho( no.direita ) + 1;
        
        return novaRaiz;
        
    }

    /**
     * Inverte a cor de um nó e de seus filhos.
     * 
     * @param no nó a ser alterado.
     */
    private void trocarCor( No<TipoChave, TipoValor> no ) {
        
        // condição de existência: no precisa ter cor oposta a seus filhos
        //assert ( no != null ) && ( no.esquerda != null ) && ( no.direita != null );
        //assert ( !isVermelho( no ) && isVermelho( no.esquerda ) && isVermelho( no.direita ) )
        //        || ( isVermelho( no ) && !isVermelho( no.esquerda ) && !isVermelho( no.direita ) );
        
        no.cor = no.cor == CorNo.VERMELHO ? CorNo.PRETO : CorNo.VERMELHO;
        no.esquerda.cor = no.esquerda.cor == CorNo.VERMELHO ? CorNo.PRETO : CorNo.VERMELHO;
        no.direita.cor = no.direita.cor == CorNo.VERMELHO ? CorNo.PRETO : CorNo.VERMELHO;
        
    }

    /**
     * Assumindo que o nó é vermelho e ambos os seus filhos são pretos, faz
     * com que no.esquerda ou um de seus filhos seja vermelho.
     * 
     * @param no nó a ser movido.
     * @return o nó modificado.
     */
    private No<TipoChave, TipoValor> moverVermelhoEsquerda( No<TipoChave, TipoValor> no ) {
        
        //assert ( no != null );
        //assert isVermelho( no ) && !isVermelho( no.esquerda ) && !isVermelho( no.esquerda.esquerda );

        trocarCor( no );
        
        if ( isVermelho( no.direita.esquerda ) ) {
            no.direita = rotacionarParaDireita( no.direita );
            no = rotacionarParaEsquerda( no );
            //trocarCor( no );
        }
        
        return no;
        
    }

    /**
     * Assumindo que o nó é vermelho e ambos os seus filhos são pretos, faz
     * com que no.direita ou um de seus filhos seja vermelho.
     * 
     * @param no nó a ser movido.
     * @return o nó modificado.
     */
    private No<TipoChave, TipoValor> moverVermelhoDireita( No<TipoChave, TipoValor> no ) {
        
        //assert ( h != null );
        //assert isVermelho( h ) && !isVermelho( h.direita ) && !isVermelho( h.direita.esquerda );
        
        trocarCor( no );
        
        if ( isVermelho( no.esquerda.esquerda ) ) {
            no = rotacionarParaDireita( no );
            //trocarCor( no );
        }
        
        return no;
        
    }

    /**
     * Recupera a condição de existência (invariante) para a árvore vermelho-preto.
     * 
     * @param no nó de origem.
     * @return o nó modificado.
     */
    private No<TipoChave, TipoValor> balancear( No<TipoChave, TipoValor> no ) {
        
        //assert ( no != null );

        if ( isVermelho( no.direita ) ) {
            no = rotacionarParaEsquerda( no );
        }
        
        if ( isVermelho( no.esquerda ) && isVermelho( no.esquerda.esquerda ) ) {
            no = rotacionarParaDireita( no );
        }
        
        if ( isVermelho( no.esquerda ) && isVermelho( no.direita ) ) {
            trocarCor( no );
        }

        no.n = tamanho( no.esquerda ) + tamanho( no.direita ) + 1;
        
        return no;
        
    }

    
    
    /**
     * ***********************************************************************
     * Métodos utilitários
     * ***********************************************************************
     */
    
    /**
     * Altura da árvore.
     * @return Retorna a altura da árvore, 0 se vazia.
     */
    public int altura() {
        return altura( raiz );
    }

    private int altura( No<TipoChave, TipoValor> x ) {
        
        if ( x == null ) {
            return 0;
        }
        
        return 1 + Math.max( altura( x.esquerda ), altura( x.direita ) );
        
    }

    /**
     * ***********************************************************************
     * Métodos para a tabela de símbolos ordenada
     * ***********************************************************************
     */
    
    /**
     * Retorna a menor chave.
     * 
     * @return a menor chave, null caso a chave não exista.
     */
    public TipoChave minimo() {
        
        if ( estaVazia() ) {
            return null;
        }
        
        return minimo( raiz ).chave;
        
    }

    /**
     * Retorna a menor chave em uma subárvore enraizada em um nó.
     * 
     * @param no nó de origem.
     * @return a menor chave, null caso a chave não exista.
     */
    private No<TipoChave, TipoValor> minimo( No<TipoChave, TipoValor> no ) {
        
        //assert x != null;
        
        if ( no.esquerda == null ) {
            return no;
        } else {
            return minimo( no.esquerda );
        }
        
    }

    /**
     * Retorna a maior chave.
     * 
     * @return a maior chave, null caso a chave não exista.
     */
    public TipoChave maximo() {
        
        if ( estaVazia() ) {
            return null;
        }
        
        return maximo( raiz ).chave;
        
    }

    /**
     * Retorna a maior chave em uma subárvore enraizada em um nó.
     * 
     * @param no nó de origem.
     * @return a maior chave, null caso a chave não exista.
     */
    private No<TipoChave, TipoValor> maximo( No<TipoChave, TipoValor> no ) {
        
        //assert no != null;
        
        if ( no.direita == null ) {
            return no;
        } else {
            return maximo( no.direita );
        }
        
    }

    /**
     * Retorna a maior chave menor ou igual a uma chave.
     * 
     * @param chave chave a ser consultada
     * @return a maior chave menor ou igual a uma chave, null caso a chave não exista.
     */
    public TipoChave chao( TipoChave chave ) {
        
        No<TipoChave, TipoValor> x = chao( raiz, chave );
        
        if ( x == null ) {
            return null;
        } else {
            return x.chave;
        }
        
    }

    /**
     * Retorna a maior chave menor ou igual a uma chave em uma subárvore enraizada em um nó.
     * 
     * @param no nó de origem
     * @param chave chave a ser consultada
     * @return a maior chave menor ou igual a uma chave, null caso a chave não exista.
     */
    private No<TipoChave, TipoValor> chao( No<TipoChave, TipoValor> no, TipoChave chave ) {
        
        if ( no == null ) {
            return null;
        }
        
        int comparacao = chave.compareTo( no.chave );
        
        if ( comparacao == 0 ) {
            return no;
        }
        if ( comparacao < 0 ) {
            return chao( no.esquerda, chave );
        }
        
        No<TipoChave, TipoValor> t = chao( no.direita, chave );
        if ( t != null ) {
            return t;
        } else {
            return no;
        }
        
    }

    /**
     * Retorna a menor chave maior ou igual a uma chave.
     * 
     * @param chave chave a ser consultada
     * @return a menor chave maior ou igual a uma chave, null caso a chave não exista.
     */
    public TipoChave teto( TipoChave chave ) {
        
        No<TipoChave, TipoValor> x = teto( raiz, chave );
        
        if ( x == null ) {
            return null;
        } else {
            return x.chave;
        }
        
    }

    /**
     * Retorna a menor chave maior ou igual a uma chave em uma subárvore enraizada em um nó.
     * 
     * @param no nó de origem
     * @param chave chave a ser consultada
     * @return a menor chave maior ou igual a uma chave, null caso a chave não exista.
     */
    private No<TipoChave, TipoValor> teto( No<TipoChave, TipoValor> x, TipoChave chave ) {
        
        if ( x == null ) {
            return null;
        }
        
        int comparacao = chave.compareTo( x.chave );
        
        if ( comparacao == 0 ) {
            return x;
        }
        if ( comparacao > 0 ) {
            return teto( x.direita, chave );
        }
        
        No<TipoChave, TipoValor> t = teto( x.esquerda, chave );
        if ( t != null ) {
            return t;
        } else {
            return x;
        }
        
    }

    /**
     * Retorna a chave que tem o determinado ranque.
     * 
     * @param k ranque a ser verificado
     * @return a chave com o ranque passado, null caso o ranque não exista
     */
    public TipoChave selecionar( int k ) {
        
        if ( k < 0 || k >= tamanho() ) {
            return null;
        }
        
        No<TipoChave, TipoValor> x = selecionar( raiz, k );
        return x.chave;
        
    }

    /**
     * Retorna a chave que tem o determinado ranque em uma subárvore enraizada em um nó
     * 
     * @param no nó de origem
     * @param k ranque a ser verificado
     * @return a chave com o ranque passado, null caso o ranque não exista
     */
    private No<TipoChave, TipoValor> selecionar( No<TipoChave, TipoValor> no, int k ) {
        
        //assert no != null;
        //assert k >= 0 && k < tamanho( no );
        
        int t = tamanho( no.esquerda );
        
        if ( t > k ) {
            return selecionar( no.esquerda, k );
        } else if ( t < k ) {
            return selecionar( no.direita, k - t - 1 );
        } else {
            return no;
        }
        
    }

    /**
     * Obtém o ranque de uma chave.
     * 
     * @param chave chave a ser consultada
     * @return o ranque da chave, 0 caso não exista
     */
    public int ranque( TipoChave chave ) {
        return ranque( raiz, chave );
    }

    /**
     * Obtém o ranque de uma chave em uma subárvore enraizada em um nó.
     * 
     * @param no nó de origem
     * @param chave chave a ser consultada
     * @return o ranque da chave, 0 caso não exista
     */
    private int ranque( No<TipoChave, TipoValor> no, TipoChave chave ) {
        
        if ( no == null ) {
            return 0;
        }
        
        int comparacao = chave.compareTo( no.chave );
        
        if ( comparacao < 0 ) {
            return ranque( no.esquerda, chave );
        } else if ( comparacao > 0 ) {
            return 1 + tamanho( no.esquerda ) + ranque( no.direita, chave );
        } else {
            return tamanho( no.esquerda );
        }
        
    }

    
    
    /**
     * *********************************************************************
     * Contagem e busca em intervalos.
     * *********************************************************************
     */
    
    /**
     * Obtém todas as chaves na forma de um iterável.
     * 
     * @return todas as chaves.
     */
    public Iterable<TipoChave> chaves() {
        return chaves( minimo(), maximo() );
    }

    /**
     * Obtém todas as chaves de um intervalo na forma de um iterável.
     * 
     * @param menor chave de ínicio
     * @param maior chave de fim
     * @return todas as chaves no intervalo.
     */
    public Iterable<TipoChave> chaves( TipoChave menor, TipoChave maior ) {
        
        Fila<TipoChave> fila = new Fila<TipoChave>();
        
        if ( estaVazia() || menor.compareTo( maior ) > 0 ) {
            return fila;
        }
        
        chaves( raiz, fila, menor, maior );
        
        return fila;
        
    }

    /**
     * Obtém todas as chaves de um intervalo na forma de um iterável em uma subárvore
     * enraizada em um nó.
     * 
     * @param no nó de origem
     * @param menor chave de ínicio
     * @param maior chave de fim
     * @return todas as chaves no intervalo.
     */
    private void chaves( No<TipoChave, TipoValor> no, Fila<TipoChave> fila, TipoChave menor, TipoChave maior ) {
        
        if ( no == null ) {
            return;
        }
        
        int comparacaoMenor = menor.compareTo( no.chave );
        int comparacaoMaior = maior.compareTo( no.chave );
        
        if ( comparacaoMenor < 0 ) {
            chaves( no.esquerda, fila, menor, maior );
        }
        if ( comparacaoMenor <= 0 && comparacaoMaior >= 0 ) {
            fila.enfileirar( no.chave );
        }
        if ( comparacaoMaior > 0 ) {
            chaves( no.direita, fila, menor, maior );
        }
        
    }

    /**
     * Obtém a quantidade de chaves entre duas chaves.
     * 
     * @param menor chave de início
     * @param maior chave de fim
     * @return a quantidade de chaves entre duas chaves
     */
    public int tamanho( TipoChave menor, TipoChave maior ) {
        
        if ( menor.compareTo( maior ) > 0 ) {
            return 0;
        }
        
        if ( contem( maior ) ) {
            return ranque( maior ) - ranque( menor ) + 1;
        } else {
            return ranque( maior ) - ranque( menor );
        }
        
    }

    /**
     * Verifica a integridade da árvore vermelho-preto
     * 
     * @return true caso a árvore este íntegra, false caso contrário.
     */
    private boolean verificar() {
        
        if ( !isABB() ) {
            System.out.println( "Não possui ordem simétrica" );
        }
        
        if ( !isTamanhoConsistente() ) {
            System.out.println( "Contagens das subárvores não está consistente" );
        }
        
        if ( !isRanqueConsistente() ) {
            System.out.println( "Ranques não consistentes" );
        }
        
        if ( !is23() ) {
            System.out.println( "Não é uma árvore 2-3" );
        }
        
        if ( !isBalanceada() ) {
            System.out.println( "Não está balanceada" );
        }
        
        return isABB() && 
               isTamanhoConsistente() && 
               isRanqueConsistente() && 
               is23() && 
               isBalanceada();
        
    }

    /**
     * Verifica se é uma árvore binária de busca (menores à esquerda, maiores à direita)
     * 
     * @return true caso seja uma ABB, false caso contrário.
     */
    private boolean isABB() {
        return isABB( raiz, null, null );
    }

    /**
     * Verifica se a subárvore enraizada em um nó tem as chaves em ordem entre duas 
     * chaves.
     * Credito: Bob Dondero's
     * 
     * @param no no de origem
     * @param min chave mínima
     * @param max chave máxima
     * @return true caso seja uma ABB entre as chaves, false caso contrário.
     */
    private boolean isABB( No<TipoChave, TipoValor> no, TipoChave min, TipoChave max ) {
        
        if ( no == null ) {
            return true;
        }
        
        if ( min != null && no.chave.compareTo( min ) <= 0 ) {
            return false;
        }
        
        if ( max != null && no.chave.compareTo( max ) >= 0 ) {
            return false;
        }
        
        return isABB( no.esquerda, min, no.chave ) && isABB( no.direita, no.chave, max );
        
    }

    /**
     * Verifica se as contagens estão certas.
     * 
     * @return true caso sim, false caso contrário
     */
    private boolean isTamanhoConsistente() {
        return isTamanhoConsistente( raiz );
    }

    /**
     * Verifica se as contagens estão certas em uma subárvore enraizada em um nó.
     * 
     * @return true caso sim, false caso contrário
     */
    private boolean isTamanhoConsistente( No<TipoChave, TipoValor> no ) {
        
        if ( no == null ) {
            return true;
        }
        
        if ( no.n != tamanho( no.esquerda ) + tamanho( no.direita ) + 1 ) {
            return false;
        }
        
        return isTamanhoConsistente( no.esquerda ) && isTamanhoConsistente( no.direita );
        
    }

    /**
     * Verifica se os ranques são consistentes.
     * 
     * @return true caso sim, false caso contrário
     */
    private boolean isRanqueConsistente() {
        
        for ( int i = 0; i < tamanho(); i++ ) {
            if ( i != ranque( selecionar( i ) ) ) {
                return false;
            }
        }
        
        for ( TipoChave chave : chaves() ) {
            if ( chave.compareTo( selecionar( ranque( chave ) ) ) != 0 ) {
                return false;
            }
        }
        
        return true;
        
    }

    /**
     * Verifica se esta árvore não tem links vermelhos à direita e se tem no máximo
     * um link vermelho seguido à esquerda um uma linha de qualquer caminho.
     * 
     * @return true caso sim, false caso contrário
     */
    private boolean is23() {
        return is23( raiz );
    }

    /**
     * Verifica se uma subárvore enraizada em um nó não tem links vermelhos à 
     * direita e se tem no máximo um link vermelho seguido à esquerda um uma 
     * linha de qualquer caminho.
     * 
     * @return true caso sim, false caso contrário
     */
    private boolean is23( No<TipoChave, TipoValor> no ) {
        
        if ( no == null ) {
            return true;
        }
        
        if ( isVermelho( no.direita ) ) {
            return false;
        }
        
        if ( no != raiz && isVermelho( no ) && isVermelho( no.esquerda ) ) {
            return false;
        }
        
        return is23( no.esquerda ) && is23( no.direita );
        
    }

    /**
     * Verifica se todos os caminhos da raiz às folhas tem a mesma quantidade
     * de links pretos.
     * 
     * @return true caso sim, false caso contrário
     */
    private boolean isBalanceada() {
        
        // quantidade de links pretos em um caminho da raiz até uma folha.
        
        int black = 0;
        No<TipoChave, TipoValor> atual = raiz;
        
        while ( atual != null ) {
            
            if ( !isVermelho( atual ) ) {
                black++;
            }
            
            atual = atual.esquerda;
            
        }
        
        return isBalanceada( raiz, black );
        
    }

    /**
     * Verifica se todos os caminhos a partir de um nó até uma folha folhas tem
     * a mesma quantidade de links pretos.
     * 
     * @return true caso sim, false caso contrário
     */
    private boolean isBalanceada( No<TipoChave, TipoValor> no, int black ) {
        
        if ( no == null ) {
            return black == 0;
        }
        
        if ( !isVermelho( no ) ) {
            black--;
        }
        
        return isBalanceada( no.esquerda, black ) && isBalanceada( no.direita, black );
        
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
    private No<TipoChave, TipoValor> desalocar( No<TipoChave, TipoValor> no ) {

        if ( no != null ) {
            no.esquerda = desalocar( no.esquerda );
            no.direita = desalocar( no.direita );
        }

        return null;

    }
    
    /**
     * Cria uma representação em String da árvore.
     * Esta representação apresenta os elementos na ordem do percurso em ordem.
     */
    @Override
    public String toString() {
        
        StringBuilder sb = new StringBuilder();
        
        if ( !estaVazia() ) {
            
            for ( No<TipoChave, TipoValor> no : PercursosArvoreVermelhoPretoCV.percorrer( this, TipoPercursoArvores.EM_ORDEM ) ) {
                
                if ( no == raiz ) {
                    sb.append( no ).append( " <- raiz\n" );
                } else {
                    sb.append( no ).append( "\n" );
                }
                
            }
            
        } else {
            sb.append( "árvore vazia!\n" );
        }
        
        return sb.toString();
        
    }
    
    /**
     * Cria um iterador para a árvore, permitindo iterar por todos os elementos
     * da mesma, além de poder usar em um for each (for melhorado).
     * Este iterador percorre a árvore usando o percurso em ordem e os itens são do
     * tipo do nó da árvore.
     */
    @Override
    public Iterator<No<TipoChave, TipoValor>> iterator() {
        return PercursosArvoreVermelhoPretoCV.percorrer( this, TipoPercursoArvores.EM_ORDEM ).iterator();
    }
    
    /**
     * Método para obter o nó da raiz.
     * Não deveria existir, mas é necessário para o simulador.
     * 
     * @return Nó com a raiz da árvore.
     */
    public No<TipoChave, TipoValor> getRaiz() {
        return raiz;
    }
    
    /**
     * Testes da árvore.
     * 
     * @param args
     */
    public static void main( String[] args ) {
        
        ArvoreVermelhoPretoCV<Integer, String> avp = new ArvoreVermelhoPretoCV<>();
        
        avp.inserir( 6, "seis" );
        System.out.println( avp );
        avp.inserir( 8, "oito" );
        System.out.println( avp );
        avp.inserir( 7, "sete" );
        System.out.println( avp );
        avp.inserir( 4, "quatro" );
        System.out.println( avp );
        avp.inserir( 5, "cinco" );
        System.out.println( avp );
        avp.inserir( 9, "nove" );
        System.out.println( avp );
        avp.inserir( 3, "três" );
        System.out.println( avp );
        
        for ( Integer chave : avp.chaves() ) {
            System.out.printf( "%d: %s\n", chave, avp.obter( chave ) );
        }
        
    }
    
}
