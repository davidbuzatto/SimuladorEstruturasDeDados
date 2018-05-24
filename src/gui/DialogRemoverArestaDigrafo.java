/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gui;

import estruturas.Digrafo;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultListModel;

/**
 * Diálogo para remoção de arestas do digrafo.
 * 
 * @author David Buzatto
 */
public class DialogRemoverArestaDigrafo extends javax.swing.JDialog {

    private DefaultListModel<String> modelo;
    private List<String> arestasRemovidas;
    private Digrafo<Integer> digrafo;
    
    /**
     * Creates new form DialogRemoverArestaGrafo
     */
    public DialogRemoverArestaDigrafo( java.awt.Frame parent, boolean modal, Digrafo<Integer> digrafo ) {
        
        super( parent, modal );
        initComponents();
        
        this.digrafo = digrafo;
        this.arestasRemovidas = new ArrayList<>();
        
        modelo = new DefaultListModel<>();
        
        for ( Integer v : digrafo.getVertices() ) {
            for ( Integer w : digrafo.getAdjacentes( v ) ) {
                modelo.addElement( v + "-" + w );
            }
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
        btnCancelar = new javax.swing.JButton();
        btnOK = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Remover Aresta");

        painelArestas.setBorder(javax.swing.BorderFactory.createTitledBorder("Arestas"));

        sp.setViewportView(listaArestas);

        javax.swing.GroupLayout painelArestasLayout = new javax.swing.GroupLayout(painelArestas);
        painelArestas.setLayout(painelArestasLayout);
        painelArestasLayout.setHorizontalGroup(
            painelArestasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelArestasLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(sp, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                .addContainerGap())
        );
        painelArestasLayout.setVerticalGroup(
            painelArestasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelArestasLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(sp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
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
        
        for ( String aRemover : listaArestas.getSelectedValuesList() ) {
            
            modelo.removeElement( aRemover );
            arestasRemovidas.add( aRemover );
            
        }
        
        dispose();
    }//GEN-LAST:event_btnOKActionPerformed

    public List<String> getArestasRemovidas() {
        return arestasRemovidas;
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnOK;
    private javax.swing.JList<String> listaArestas;
    private javax.swing.JPanel painelArestas;
    private javax.swing.JScrollPane sp;
    // End of variables declaration//GEN-END:variables
}
