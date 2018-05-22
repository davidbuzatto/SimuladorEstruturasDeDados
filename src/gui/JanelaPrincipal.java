/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gui;

import java.beans.PropertyVetoException;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;

/**
 * Janela principal da aplicação.
 * 
 * @author David Buzatto
 */
public class JanelaPrincipal extends javax.swing.JFrame {

    private final Map<String, JInternalFrame> iFrames = new HashMap<>();
    
    /**
     * Creates new form JanelaPrincipal
     */
    public JanelaPrincipal() {
        
        initComponents();
        
        btnHeapMinimo.setVisible( false );
        btnHeapMaximo.setVisible( false );
        btnTabelaHash.setVisible( false );
        btnGrafoPonderado.setVisible( false );
        
        setExtendedState( MAXIMIZED_BOTH );
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings( "unchecked" )
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        barraFerramentas = new javax.swing.JToolBar();
        btnPilha = new javax.swing.JButton();
        btnFila = new javax.swing.JButton();
        btnDeque = new javax.swing.JButton();
        btnLista = new javax.swing.JButton();
        btnTabelaSimbolos = new javax.swing.JButton();
        btnABB = new javax.swing.JButton();
        btnArvoreAVL = new javax.swing.JButton();
        btnArvoreVP = new javax.swing.JButton();
        btnTabelaHash = new javax.swing.JButton();
        btnHeapMinimo = new javax.swing.JButton();
        btnHeapMaximo = new javax.swing.JButton();
        btnGrafo = new javax.swing.JButton();
        btnGrafoPonderado = new javax.swing.JButton();
        btnSobre = new javax.swing.JButton();
        desktopPane = new javax.swing.JDesktopPane();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Simulador - Estruturas de Dados");

        barraFerramentas.setFloatable(false);
        barraFerramentas.setRollover(true);

        btnPilha.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/imagens/pilha.png"))); // NOI18N
        btnPilha.setText("Pilha");
        btnPilha.setFocusable(false);
        btnPilha.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnPilha.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnPilha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPilhaActionPerformed(evt);
            }
        });
        barraFerramentas.add(btnPilha);

        btnFila.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/imagens/fila.png"))); // NOI18N
        btnFila.setText("Fila");
        btnFila.setFocusable(false);
        btnFila.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnFila.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnFila.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFilaActionPerformed(evt);
            }
        });
        barraFerramentas.add(btnFila);

        btnDeque.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/imagens/deque.png"))); // NOI18N
        btnDeque.setText("Deque");
        btnDeque.setFocusable(false);
        btnDeque.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnDeque.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnDeque.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDequeActionPerformed(evt);
            }
        });
        barraFerramentas.add(btnDeque);

        btnLista.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/imagens/lista.png"))); // NOI18N
        btnLista.setText("Lista");
        btnLista.setFocusable(false);
        btnLista.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnLista.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnLista.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnListaActionPerformed(evt);
            }
        });
        barraFerramentas.add(btnLista);

        btnTabelaSimbolos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/imagens/tabelaSimbolos.png"))); // NOI18N
        btnTabelaSimbolos.setText("Tabela de Símbolos");
        btnTabelaSimbolos.setFocusable(false);
        btnTabelaSimbolos.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnTabelaSimbolos.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnTabelaSimbolos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTabelaSimbolosActionPerformed(evt);
            }
        });
        barraFerramentas.add(btnTabelaSimbolos);

        btnABB.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/imagens/abb.png"))); // NOI18N
        btnABB.setText("Árvore Binária de Busca");
        btnABB.setFocusable(false);
        btnABB.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnABB.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnABB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnABBActionPerformed(evt);
            }
        });
        barraFerramentas.add(btnABB);

        btnArvoreAVL.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/imagens/avl.png"))); // NOI18N
        btnArvoreAVL.setText("Árvore AVL");
        btnArvoreAVL.setFocusable(false);
        btnArvoreAVL.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnArvoreAVL.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnArvoreAVL.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnArvoreAVLActionPerformed(evt);
            }
        });
        barraFerramentas.add(btnArvoreAVL);

        btnArvoreVP.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/imagens/avp.png"))); // NOI18N
        btnArvoreVP.setText("Árvore Vermelho-Preto");
        btnArvoreVP.setFocusable(false);
        btnArvoreVP.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnArvoreVP.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnArvoreVP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnArvoreVPActionPerformed(evt);
            }
        });
        barraFerramentas.add(btnArvoreVP);

        btnTabelaHash.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/imagens/tabelaHash.png"))); // NOI18N
        btnTabelaHash.setText("Tabela Hash");
        btnTabelaHash.setFocusable(false);
        btnTabelaHash.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnTabelaHash.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnTabelaHash.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTabelaHashActionPerformed(evt);
            }
        });
        barraFerramentas.add(btnTabelaHash);

        btnHeapMinimo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/imagens/heapMinimo.png"))); // NOI18N
        btnHeapMinimo.setText("Heap Mínimo");
        btnHeapMinimo.setFocusable(false);
        btnHeapMinimo.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnHeapMinimo.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnHeapMinimo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHeapMinimoActionPerformed(evt);
            }
        });
        barraFerramentas.add(btnHeapMinimo);

        btnHeapMaximo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/imagens/heapMaximo.png"))); // NOI18N
        btnHeapMaximo.setText("Heap Máximo");
        btnHeapMaximo.setFocusable(false);
        btnHeapMaximo.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnHeapMaximo.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnHeapMaximo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHeapMaximoActionPerformed(evt);
            }
        });
        barraFerramentas.add(btnHeapMaximo);

        btnGrafo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/imagens/grafo.png"))); // NOI18N
        btnGrafo.setText("Grafo");
        btnGrafo.setFocusable(false);
        btnGrafo.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnGrafo.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnGrafo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGrafoActionPerformed(evt);
            }
        });
        barraFerramentas.add(btnGrafo);

        btnGrafoPonderado.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/imagens/grafoPonderado.png"))); // NOI18N
        btnGrafoPonderado.setText("Grafo Ponderado");
        btnGrafoPonderado.setFocusable(false);
        btnGrafoPonderado.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnGrafoPonderado.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnGrafoPonderado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGrafoPonderadoActionPerformed(evt);
            }
        });
        barraFerramentas.add(btnGrafoPonderado);

        btnSobre.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/imagens/sobre.png"))); // NOI18N
        btnSobre.setText("Sobre");
        btnSobre.setFocusable(false);
        btnSobre.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnSobre.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnSobre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSobreActionPerformed(evt);
            }
        });
        barraFerramentas.add(btnSobre);

        javax.swing.GroupLayout desktopPaneLayout = new javax.swing.GroupLayout(desktopPane);
        desktopPane.setLayout(desktopPaneLayout);
        desktopPaneLayout.setHorizontalGroup(
            desktopPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        desktopPaneLayout.setVerticalGroup(
            desktopPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 307, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(barraFerramentas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(desktopPane)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(barraFerramentas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(desktopPane))
        );

        setSize(new java.awt.Dimension(1070, 414));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnPilhaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPilhaActionPerformed
        
        JInternalFrame iFrame = iFrames.get( "ifPilha" );
        
        if ( iFrame == null ) {
            
            iFrame = new IFPilha();
            desktopPane.add( iFrame );
            iFrames.put( "ifPilha", iFrame );
            
            iFrame.addInternalFrameListener( new InternalFrameAdapter() {
                @Override
                public void internalFrameClosing( InternalFrameEvent e ) {
                    iFrames.remove( "ifPilha" );
                }
            });
            
        }
        
        try {
            iFrame.setSelected( true );
        } catch ( PropertyVetoException ex ) {
        }
        
    }//GEN-LAST:event_btnPilhaActionPerformed

    private void btnFilaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFilaActionPerformed
        
        JInternalFrame iFrame = iFrames.get( "ifFila" );
        
        if ( iFrame == null ) {
            
            iFrame = new IFFila();
            desktopPane.add( iFrame );
            iFrames.put( "ifFila", iFrame );
            
            iFrame.addInternalFrameListener( new InternalFrameAdapter() {
                @Override
                public void internalFrameClosing( InternalFrameEvent e ) {
                    iFrames.remove( "ifFila" );
                }
            });
            
        }
        
        try {
            iFrame.setSelected( true );
        } catch ( PropertyVetoException ex ) {
        }
        
    }//GEN-LAST:event_btnFilaActionPerformed

    private void btnDequeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDequeActionPerformed
        
        JInternalFrame iFrame = iFrames.get( "ifDeque" );
        
        if ( iFrame == null ) {
            
            iFrame = new IFDeque();
            desktopPane.add( iFrame );
            iFrames.put( "ifDeque", iFrame );
            
            iFrame.addInternalFrameListener( new InternalFrameAdapter() {
                @Override
                public void internalFrameClosing( InternalFrameEvent e ) {
                    iFrames.remove( "ifDeque" );
                }
            });
            
        }
        
        try {
            iFrame.setSelected( true );
        } catch ( PropertyVetoException ex ) {
        }
        
    }//GEN-LAST:event_btnDequeActionPerformed

    private void btnListaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnListaActionPerformed
        
        JInternalFrame iFrame = iFrames.get( "ifLista" );
        
        if ( iFrame == null ) {
            
            iFrame = new IFLista();
            desktopPane.add( iFrame );
            iFrames.put( "ifLista", iFrame );
            
            iFrame.addInternalFrameListener( new InternalFrameAdapter() {
                @Override
                public void internalFrameClosing( InternalFrameEvent e ) {
                    iFrames.remove( "ifLista" );
                }
            });
            
        }
        
        try {
            iFrame.setSelected( true );
        } catch ( PropertyVetoException ex ) {
        }
        
    }//GEN-LAST:event_btnListaActionPerformed

    private void btnABBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnABBActionPerformed
        
        JInternalFrame iFrame = iFrames.get( "ifABB" );
        
        if ( iFrame == null ) {
            
            iFrame = new IFArvoreBinariaBusca();
            desktopPane.add( iFrame );
            iFrames.put( "ifABB", iFrame );
            
            iFrame.addInternalFrameListener( new InternalFrameAdapter() {
                @Override
                public void internalFrameClosing( InternalFrameEvent e ) {
                    iFrames.remove( "ifABB" );
                }
            });
            
        }
        
        try {
            iFrame.setSelected( true );
        } catch ( PropertyVetoException ex ) {
        }
        
    }//GEN-LAST:event_btnABBActionPerformed

    private void btnSobreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSobreActionPerformed
        
        JOptionPane.showMessageDialog( this, 
                "Simulador de Estruturas de Dados\n\n"
                        + "Desenvolvido por: Prof. David Buzatto\n"
                        + "E-mail: davidbuzatto@ifsp.edu.br\n"
                        + "Versão atual: 30/03/2014", 
                "Sobre...", JOptionPane.INFORMATION_MESSAGE );
        
    }//GEN-LAST:event_btnSobreActionPerformed

    private void btnTabelaSimbolosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTabelaSimbolosActionPerformed
        
        JInternalFrame iFrame = iFrames.get( "ifTabelaSimbolos" );
        
        if ( iFrame == null ) {
            
            iFrame = new IFTabelaSimbolos();
            desktopPane.add( iFrame );
            iFrames.put( "ifTabelaSimbolos", iFrame );
            
            iFrame.addInternalFrameListener( new InternalFrameAdapter() {
                @Override
                public void internalFrameClosing( InternalFrameEvent e ) {
                    iFrames.remove( "ifTabelaSimbolos" );
                }
            });
            
        }
        
        try {
            iFrame.setSelected( true );
        } catch ( PropertyVetoException ex ) {
        }
        
    }//GEN-LAST:event_btnTabelaSimbolosActionPerformed

    private void btnArvoreVPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnArvoreVPActionPerformed
        
        JInternalFrame iFrame = iFrames.get( "ifAVP" );
        
        if ( iFrame == null ) {
            
            iFrame = new IFArvoreVermelhoPreto();
            desktopPane.add( iFrame );
            iFrames.put( "ifAVP", iFrame );
            
            iFrame.addInternalFrameListener( new InternalFrameAdapter() {
                @Override
                public void internalFrameClosing( InternalFrameEvent e ) {
                    iFrames.remove( "ifAVP" );
                }
            });
            
        }
        
        try {
            iFrame.setSelected( true );
        } catch ( PropertyVetoException ex ) {
        }
        
    }//GEN-LAST:event_btnArvoreVPActionPerformed

    private void btnTabelaHashActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTabelaHashActionPerformed
        JOptionPane.showMessageDialog( this, "Não implementado!", 
                "ERRO", JOptionPane.ERROR_MESSAGE );
    }//GEN-LAST:event_btnTabelaHashActionPerformed

    private void btnHeapMinimoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHeapMinimoActionPerformed
        JOptionPane.showMessageDialog( this, "Não implementado!", 
                "ERRO", JOptionPane.ERROR_MESSAGE );
    }//GEN-LAST:event_btnHeapMinimoActionPerformed

    private void btnHeapMaximoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHeapMaximoActionPerformed
        JOptionPane.showMessageDialog( this, "Não implementado!", 
                "ERRO", JOptionPane.ERROR_MESSAGE );
    }//GEN-LAST:event_btnHeapMaximoActionPerformed

    private void btnGrafoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGrafoActionPerformed
        
        JInternalFrame iFrame = iFrames.get( "ifGrafo" );
        
        if ( iFrame == null ) {
            
            iFrame = new IFGrafo();
            desktopPane.add( iFrame );
            iFrames.put( "ifGrafo", iFrame );
            
            iFrame.addInternalFrameListener( new InternalFrameAdapter() {
                @Override
                public void internalFrameClosing( InternalFrameEvent e ) {
                    iFrames.remove( "ifGrafo" );
                }
            });
            
        }
        
        try {
            iFrame.setSelected( true );
        } catch ( PropertyVetoException ex ) {
        }
        
    }//GEN-LAST:event_btnGrafoActionPerformed

    private void btnGrafoPonderadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGrafoPonderadoActionPerformed
        JOptionPane.showMessageDialog( this, "Não implementado!", 
                "ERRO", JOptionPane.ERROR_MESSAGE );
    }//GEN-LAST:event_btnGrafoPonderadoActionPerformed

    private void btnArvoreAVLActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnArvoreAVLActionPerformed
        
        JInternalFrame iFrame = iFrames.get( "ifAVL" );
        
        if ( iFrame == null ) {
            
            iFrame = new IFArvoreAVL();
            desktopPane.add( iFrame );
            iFrames.put( "ifAVL", iFrame );
            
            iFrame.addInternalFrameListener( new InternalFrameAdapter() {
                @Override
                public void internalFrameClosing( InternalFrameEvent e ) {
                    iFrames.remove( "ifAVL" );
                }
            });
            
        }
        
        try {
            iFrame.setSelected( true );
        } catch ( PropertyVetoException ex ) {
        }
        
    }//GEN-LAST:event_btnArvoreAVLActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main( String args[] ) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
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
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater( new Runnable() {
            public void run() {
                new JanelaPrincipal().setVisible( true );
            }
        } );
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JToolBar barraFerramentas;
    private javax.swing.JButton btnABB;
    private javax.swing.JButton btnArvoreAVL;
    private javax.swing.JButton btnArvoreVP;
    private javax.swing.JButton btnDeque;
    private javax.swing.JButton btnFila;
    private javax.swing.JButton btnGrafo;
    private javax.swing.JButton btnGrafoPonderado;
    private javax.swing.JButton btnHeapMaximo;
    private javax.swing.JButton btnHeapMinimo;
    private javax.swing.JButton btnLista;
    private javax.swing.JButton btnPilha;
    private javax.swing.JButton btnSobre;
    private javax.swing.JButton btnTabelaHash;
    private javax.swing.JButton btnTabelaSimbolos;
    private javax.swing.JDesktopPane desktopPane;
    // End of variables declaration//GEN-END:variables
}
