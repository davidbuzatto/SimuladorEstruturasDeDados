/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gui;

import gui.desenho.estruturas.ArestaGrafoAnotado;
import java.util.List;
import javax.swing.DefaultListModel;

/**
 * Diálogo para remoção de arestas do grafo.
 * 
 * @author David Buzatto
 */
public class DialogRemoverArestaGrafo extends javax.swing.JDialog {

    private DefaultListModel<ArestaGrafoAnotado> modelo;
    private List<ArestaGrafoAnotado> arestas;
    private List<ArestaGrafoAnotado> arestasRemovidas;
    
    /**
     * Creates new form DialogRemoverArestaGrafo
     */
    public DialogRemoverArestaGrafo( java.awt.Frame parent, boolean modal, List<ArestaGrafoAnotado> arestas, List<ArestaGrafoAnotado> arestasRemovidas ) {
        
        super( parent, modal );
        initComponents();
        
        this.arestas = arestas;
        this.arestasRemovidas = arestasRemovidas;
        
        modelo = new DefaultListModel<>();
        for ( ArestaGrafoAnotado a : arestas ) {
            modelo.addElement( a );
        }
        
        listaArestas.setModel( modelo );
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings( "unchecked" )
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        painelArestas = new javax.swing.JPanel();
        sp = new javax.swing.JScrollPane();
        listaArestas = new javax.swing.JList<>();
        btnRemover = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        btnOK = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Remover Aresta");

        painelArestas.setBorder(javax.swing.BorderFactory.createTitledBorder("Arestas"));

        sp.setViewportView(listaArestas);

        btnRemover.setText("Remover");
        btnRemover.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRemoverActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout painelArestasLayout = new javax.swing.GroupLayout(painelArestas);
        painelArestas.setLayout(painelArestasLayout);
        painelArestasLayout.setHorizontalGroup(
            painelArestasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelArestasLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(sp, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnRemover)
                .addContainerGap())
        );
        painelArestasLayout.setVerticalGroup(
            painelArestasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelArestasLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(painelArestasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnRemover)
                    .addComponent(sp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        btnOK.setText("OK");
        btnOK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOKActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnOK)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnCancelar))
                    .addComponent(painelArestas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(painelArestas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCancelar)
                    .addComponent(btnOK))
                .addContainerGap(50, Short.MAX_VALUE))
        );

        setSize(new java.awt.Dimension(262, 303));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        dispose();
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnOKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOKActionPerformed
        
        arestas.clear();
        
        for ( int i = 0; i < modelo.size(); i++ ) {
            arestas.add( modelo.getElementAt( i ) );
        }
        
        dispose();
        
    }//GEN-LAST:event_btnOKActionPerformed

    private void btnRemoverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRemoverActionPerformed
        
        for ( Object o : listaArestas.getSelectedValuesList() ) {
            modelo.removeElement( o );
            arestasRemovidas.add((ArestaGrafoAnotado) o );
        }
        
    }//GEN-LAST:event_btnRemoverActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnOK;
    private javax.swing.JButton btnRemover;
    private javax.swing.JList<ArestaGrafoAnotado> listaArestas;
    private javax.swing.JPanel painelArestas;
    private javax.swing.JScrollPane sp;
    // End of variables declaration//GEN-END:variables
}
