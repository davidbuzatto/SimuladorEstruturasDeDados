/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gui;

/**
 * Classe principal da aplicação.
 * 
 * @author David Buzatto
 */
public class Principal {
    
    public static void main( String[] args ) {
        
        try {
            for ( javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels() ) {
                if ( "Nimbus".equals( info.getName() ) ) {
                    javax.swing.UIManager.setLookAndFeel( info.getClassName() );
                    break;
                }
            }
        } catch ( ClassNotFoundException ex ) {
            java.util.logging.Logger.getLogger( JanelaPrincipal.class.getName() ).log( java.util.logging.Level.SEVERE, null, ex );
        } catch ( InstantiationException ex ) {
            java.util.logging.Logger.getLogger( JanelaPrincipal.class.getName() ).log( java.util.logging.Level.SEVERE, null, ex );
        } catch ( IllegalAccessException ex ) {
            java.util.logging.Logger.getLogger( JanelaPrincipal.class.getName() ).log( java.util.logging.Level.SEVERE, null, ex );
        } catch ( javax.swing.UnsupportedLookAndFeelException ex ) {
            java.util.logging.Logger.getLogger( JanelaPrincipal.class.getName() ).log( java.util.logging.Level.SEVERE, null, ex );
        }
        
        java.awt.EventQueue.invokeLater( new Runnable() {
            public void run() {
                new JanelaPrincipal().setVisible( true );
            }
        });
    }
    
}
