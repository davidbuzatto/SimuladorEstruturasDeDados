/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package estruturas.algoritmos.grafos.basico;

import estruturas.algoritmos.grafos.*;

/**
 * Classe abstrata de caminhos.
 * 
 * @author David Buzatto
 */
public abstract class Caminhos {
    
    // fonte do caminho
    protected int fonte;
    
    /**
     * Caminho da fonte até w.
     * 
     * @param w Alvo do caminho.
     * @return Uma coleção iterável com os elementos do caminho da fonte até w.
     */
    public abstract Iterable<Integer> caminhoAte( int w );
    
    /**
     * Retorna se existe ou não caminho da fonte até w.
     * 
     * @param w Alvo do caminho.
     * @return True se existir caminho, false caso contrário.
     */
    public abstract boolean existeCaminhoAte( int w );

    public int getFonte() {
        return fonte;
    }

    public void setFonte( int fonte ) {
        this.fonte = fonte;
    }
    
}
