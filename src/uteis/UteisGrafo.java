/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uteis;

import gui.desenho.estruturas.GrafoAnotado;

/**
 *
 * @author David
 */
public class UteisGrafo {
    
    public static void criarGrafoBase( GrafoAnotado grafoAnt, int tamanhoVertice ) {
        
        grafoAnt.adicionarVertice( 100, 100, tamanhoVertice );
        grafoAnt.adicionarVertice( 150, 200, tamanhoVertice );
        grafoAnt.adicionarVertice( 250, 200, tamanhoVertice );
        grafoAnt.adicionarVertice( 200, 300, tamanhoVertice );
        grafoAnt.adicionarVertice( 300, 300, tamanhoVertice );
        grafoAnt.adicionarVertice( 100, 400, tamanhoVertice );
        grafoAnt.adicionarVertice( 350, 200, tamanhoVertice );
        grafoAnt.adicionarVertice( 450, 150, tamanhoVertice );
        grafoAnt.adicionarVertice( 550, 150, tamanhoVertice );
        grafoAnt.adicionarVertice( 450, 250, tamanhoVertice );
        grafoAnt.adicionarVertice( 550, 250, tamanhoVertice );
        grafoAnt.adicionarVertice( 450, 350, tamanhoVertice );
        grafoAnt.adicionarVertice( 550, 350, tamanhoVertice );
        
        grafoAnt.adicionarAresta( 0, 5 );
        grafoAnt.adicionarAresta( 4, 3 );
        grafoAnt.adicionarAresta( 0, 1 );
        grafoAnt.adicionarAresta( 9, 12 );
        grafoAnt.adicionarAresta( 6, 4 );
        grafoAnt.adicionarAresta( 5, 4 );
        grafoAnt.adicionarAresta( 0, 2 );
        grafoAnt.adicionarAresta( 11, 12 );
        grafoAnt.adicionarAresta( 9, 10 );
        grafoAnt.adicionarAresta( 0, 6 );
        grafoAnt.adicionarAresta( 7, 8 );
        grafoAnt.adicionarAresta( 9, 11 );
        grafoAnt.adicionarAresta( 5, 3 );
        
    }
    
    public static void criarGrafoExemplo1( GrafoAnotado grafoAnt, int tamanhoVertice ) {
        
        grafoAnt.adicionarVertice( 100, 100, tamanhoVertice );
        grafoAnt.adicionarVertice( 175, 175, tamanhoVertice );
        grafoAnt.adicionarVertice( 250, 100, tamanhoVertice );
        grafoAnt.adicionarVertice( 250, 250, tamanhoVertice );
        grafoAnt.adicionarVertice( 100, 250, tamanhoVertice );
        
        grafoAnt.adicionarAresta( 3, 4 );
        grafoAnt.adicionarAresta( 1, 4 );
        grafoAnt.adicionarAresta( 2, 3 );
        grafoAnt.adicionarAresta( 0, 1 );
        grafoAnt.adicionarAresta( 0, 2 );
        grafoAnt.adicionarAresta( 0, 4 );
        
    }

    public static void criarGrafoExemplo2( GrafoAnotado grafoAnt, int tamanhoVertice ) {
        
        grafoAnt.adicionarVertice( 175, 75, tamanhoVertice );
        grafoAnt.adicionarVertice( 79, 144, tamanhoVertice );
        grafoAnt.adicionarVertice( 116, 255, tamanhoVertice );
        grafoAnt.adicionarVertice( 233, 255, tamanhoVertice );
        grafoAnt.adicionarVertice( 270, 144, tamanhoVertice );

        grafoAnt.adicionarAresta( 2, 3 );
        grafoAnt.adicionarAresta( 2, 1 );
        grafoAnt.adicionarAresta( 3, 1 );
        grafoAnt.adicionarAresta( 4, 3 );
        grafoAnt.adicionarAresta( 4, 2 );
        grafoAnt.adicionarAresta( 4, 1 );
        grafoAnt.adicionarAresta( 0, 4 );
        grafoAnt.adicionarAresta( 0, 2 );
        grafoAnt.adicionarAresta( 0, 3 );
        grafoAnt.adicionarAresta( 0, 1 );
        
    }
    
    public static void criarGrafoExemplo3( GrafoAnotado grafoAnt, int tamanhoVertice ) {
        
        grafoAnt.adicionarVertice( 175, 100, tamanhoVertice );
        grafoAnt.adicionarVertice( 100, 200, tamanhoVertice );
        grafoAnt.adicionarVertice( 175, 300, tamanhoVertice );
        grafoAnt.adicionarVertice( 475, 300, tamanhoVertice );
        grafoAnt.adicionarVertice( 550, 200, tamanhoVertice );
        grafoAnt.adicionarVertice( 475, 100, tamanhoVertice );
        grafoAnt.adicionarVertice( 275, 150, tamanhoVertice );
        grafoAnt.adicionarVertice( 375, 150, tamanhoVertice );
        grafoAnt.adicionarVertice( 275, 250, tamanhoVertice );
        grafoAnt.adicionarVertice( 375, 250, tamanhoVertice );
        
        grafoAnt.adicionarAresta( 9, 7 );
        grafoAnt.adicionarAresta( 9, 8 );
        grafoAnt.adicionarAresta( 9, 6 );
        grafoAnt.adicionarAresta( 8, 7 );
        grafoAnt.adicionarAresta( 8, 6 );
        grafoAnt.adicionarAresta( 7, 6 );
        grafoAnt.adicionarAresta( 2, 6 );
        grafoAnt.adicionarAresta( 2, 1 );
        grafoAnt.adicionarAresta( 2, 3 );
        grafoAnt.adicionarAresta( 3, 7 );
        grafoAnt.adicionarAresta( 3, 4 );
        grafoAnt.adicionarAresta( 4, 5 );
        grafoAnt.adicionarAresta( 5, 9 );
        grafoAnt.adicionarAresta( 5, 0 );
        grafoAnt.adicionarAresta( 0, 8 );
        grafoAnt.adicionarAresta( 0, 1 );
        
    }
    
}
