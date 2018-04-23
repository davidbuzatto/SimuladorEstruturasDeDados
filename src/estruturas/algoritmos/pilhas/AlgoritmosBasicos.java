/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estruturas.algoritmos.pilhas;

import estruturas.Pilha;
import java.util.EmptyStackException;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author David
 */
public class AlgoritmosBasicos {
    
    /**
     * Testa a validade de uma expressão balanceada composta exclusivamente de 
     * pares de parênteses.
     * 
     * @param expressao A expressão a ser testada.
     * @return verdadeiro caso a expressão seja válida, falso caso contrário
     */
    public static boolean ehValidaParenteses( String expressao ) {
        
        Pilha<Character> p = new Pilha<>();
        
        for ( char c : expressao.toCharArray() ) {
            
            switch ( c ) {
                
                case '(':
                    p.empilhar( c );
                    break;
                    
                case ')':
                    if ( !p.estaVazia() ) {
                        p.desempilhar();
                    } else {
                        return false;
                    }
                    break;
                    
                default:
                    return false;
                    
            }
            
        }
        
        return p.estaVazia();
        
    }
    
    /**
     * Testa a validade de uma expressão balanceada composta exclusivamente de 
     * pares de parênteses e pares de colchetes.
     * 
     * @param expressao A expressão a ser testada.
     * @return verdadeiro caso a expressão seja válida, falso caso contrário
     */
    public static boolean ehValidaParentesesColchetes( String expressao ) {
        
        Pilha<Character> p = new Pilha<>();
        
        for ( char c : expressao.toCharArray() ) {
            
            switch ( c ) {
                
                case '(':
                case '[':
                    p.empilhar( c );
                    break;
                    
                case ')':
                case ']':
                    if ( !p.estaVazia() ) {
                        char t = p.consultarTopo();
                        if ( ( c == ')' && t == '(' ) ||
                             ( c == ']' && t == '[' ) ) {
                            p.desempilhar();
                        } else {
                            return false;
                        }
                    } else {
                        return false;
                    }
                    break;
                    
                default:
                    return false;
                    
            }
            
        }
        
        return p.estaVazia();
        
    }
    
    /**
     * Testa a validade de uma expressão balanceada composta exclusivamente de 
     * pares de parênteses, pares de colchetes e pares de chaves.
     * 
     * @param expressao A expressão a ser testada.
     * @return verdadeiro caso a expressão seja válida, falso caso contrário
     */
    public static boolean ehValidaParentesesColchetesChaves( String expressao ) {
        
        Pilha<Character> p = new Pilha<>();
        
        for ( char c : expressao.toCharArray() ) {
            
            switch ( c ) {
                
                case '(':
                case '[':
                case '{':
                    p.empilhar( c );
                    break;
                    
                case ')':
                case ']':
                case '}':
                    if ( !p.estaVazia() ) {
                        char t = p.consultarTopo();
                        if ( ( c == ')' && t == '(' ) ||
                             ( c == ']' && t == '[' ) ||
                             ( c == '}' && t == '{' ) ) {
                            p.desempilhar();
                        } else {
                            return false;
                        }
                    } else {
                        return false;
                    }
                    break;
                    
                default:
                    return false;
                    
            }
            
        }
        
        return p.estaVazia();
        
    }
    
    /**
     * Testa a validade de uma expressão balanceada composta por pares de 
     * delimitadores.
     * 
     * @param expressao A expressão a ser testada.
     * @param pares Pares de símbolos que compõe a expressão.
     * @return verdadeiro caso a expressão seja válida, falso caso contrário
     */
    public static boolean ehValida( String expressao, char... pares ) throws IllegalArgumentException {
        
        if ( pares.length % 2 != 0 ) {
            throw new IllegalArgumentException( "forneça uma quantidade par de delimitadores" );
        }
        
        Pilha<Character> p = new Pilha<>();
        
        // usando mapa
        /*Map<Character, Character> delimitadores = new HashMap<>();
        
        for ( int i = 0; i < pares.length; i += 2 ) {
            delimitadores.put( pares[i], pares[i+1] );
        }
        
        for ( char c : expressao.toCharArray() ) {
            
            if ( delimitadores.containsKey( c ) ) {
                p.empilhar( c );
            } else {
                if ( !p.estaVazia() ) {
                    char t = p.consultarTopo();
                    if ( delimitadores.get( t ) == c ) {
                        p.desempilhar();
                    } else {
                        return false;
                    }
                } else {
                    return false;
                }
            }
            
        }*/
        
        
        // sem usar mapa
        for ( char c : expressao.toCharArray() ) {
            
            boolean empilhou = false;
            
            for ( int i = 0; i < pares.length; i += 2 ) {
                if ( c == pares[i] ) {
                    p.empilhar( c );
                    empilhou = true;
                    break;
                }
            }
            
            if ( !empilhou ) {
                
                if ( !p.estaVazia() ) {

                    char t = p.consultarTopo();
                    int pos = -1;

                    for ( int i = 0; i < pares.length; i += 2 ) {
                        if ( t == pares[i] ) {
                            pos = i;
                            break;
                        }
                    }

                    if ( pos != -1 && 
                         pares[pos] == t && 
                         pares[pos+1] == c ) {
                        p.desempilhar();
                    } else {
                        return false;
                    }

                } else {
                    return false;
                }

            }
            
        }
        
        
        return p.estaVazia();
        
    }
    
    
    public static int calcular( String expressao ) throws NumberFormatException {
        
        Pilha<Integer> pilha = new Pilha<>();
        int op1;
        int op2;
        
        try {
            
            for ( String token : expressao.split( " " ) ) {

                switch ( token ) {

                    case "+":
                        op2 = pilha.desempilhar();
                        op1 = pilha.desempilhar();
                        pilha.empilhar( op1 + op2 );
                        break;

                    case "-":
                        op2 = pilha.desempilhar();
                        op1 = pilha.desempilhar();
                        pilha.empilhar( op1 - op2 );
                        break;

                    case "*":
                        op2 = pilha.desempilhar();
                        op1 = pilha.desempilhar();
                        pilha.empilhar( op1 * op2 );
                        break;

                    case "/":
                        op2 = pilha.desempilhar();
                        op1 = pilha.desempilhar();
                        pilha.empilhar( op1 / op2 );
                        break;

                    default:
                        pilha.empilhar( Integer.parseInt( token ) );
                        break;

                }

            }

            return pilha.desempilhar();
        
        } catch ( NumberFormatException | EmptyStackException exc ) {
            throw new NumberFormatException( "expressão mal formada: " + expressao );
        }
        
    }
    
    public static void main( String[] args ) {
        
        String[] testesParenteses = {
            "()",
            "(())",
            "(()())",
            "(()",
            "())"
        };
        
        String[] testesParentesesColchetes = {
            "()[]",
            "(())[[]]",
            "([()]()[])",
            "([())()[])",
            "(()]",
            "[())",
            "(())[",
            "(())]"
        };
        
        String[] testesParentesesColchetesChaves = {
            "()[]{}",
            "(()){}[[]]",
            "({[()]}{}()[])",
            "([())()[])",
            "(()]",
            "[({})]",
            "(())}",
            "{(())"
        };
        
        String[] testesArbitrarios = {
            "ab",
            "cd",
            "aabb",
            "acdb",
            "acbd",
            "abcd",
            "aab",
            "abb",
            "axb"
        };
        
        for ( String teste : testesParenteses ) {
            System.out.printf( "%s: %s\n", teste,
                    ehValidaParenteses( teste ) ? " é válida!" : " não é válida..." );
        }
        System.out.println();
        
        for ( String teste : testesParentesesColchetes ) {
            System.out.printf( "%s: %s\n", teste,
                    ehValidaParentesesColchetes( teste ) ? " é válida!" : " não é válida..." );
        }
        System.out.println();
        
        for ( String teste : testesParentesesColchetesChaves ) {
            System.out.printf( "%s: %s\n", teste,
                    ehValidaParentesesColchetesChaves( teste ) ? " é válida!" : " não é válida..." );
        }
        System.out.println();
        
        for ( String teste : testesArbitrarios ) {
            System.out.printf( "%s: %s\n", teste,
                    ehValida( teste, 'a', 'b', 'c', 'd' ) ? " é válida!" : " não é válida..." );
        }
        System.out.println();
        
        System.out.println( calcular( "5 6 + 5 / 5 10 - +" ) );
        
    }
    
}
