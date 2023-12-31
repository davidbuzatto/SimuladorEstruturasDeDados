/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gui;

import estruturas.TabelaSimbolosES;
import gui.desenho.PainelDesenho;
import gui.desenho.estruturas.TabelaSimbolosDesenhavel;
import javax.swing.DefaultComboBoxModel;

/**
 * Janela para a simulação de tabelas de símbolos.
 * 
 * @author David Buzatto
 */
public class IFTabelaSimbolos extends javax.swing.JInternalFrame {

    private TabelaSimbolosES<String, String> tabelaSimbolos;
    private TabelaSimbolosDesenhavel tabelaSimbolosD;
    private DefaultComboBoxModel<String> dcbm;
    
    /**
     * Creates new form IFLista
     */
    public IFTabelaSimbolos() {
        
        tabelaSimbolos = new TabelaSimbolosES<>();
        tabelaSimbolosD = new TabelaSimbolosDesenhavel( tabelaSimbolos );
        
        initComponents();
        setVisible( true );
        
        dcbm = new DefaultComboBoxModel<>();
        comboItensTabela.setModel( dcbm );
        
        tabelaSimbolosD.setPainel( painelDesenho );
        
        fieldTamanho.setText( String.valueOf( tabelaSimbolos.getTamanho() ) );
        painelDesenho.repaint();
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings( "unchecked" )
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        painelDesenho = new PainelDesenho( tabelaSimbolosD );
        painelOperacoes = new javax.swing.JPanel();
        labelChave = new javax.swing.JLabel();
        labelValor = new javax.swing.JLabel();
        labelRemover = new javax.swing.JLabel();
        fieldChave = new javax.swing.JTextField();
        fieldValor = new javax.swing.JTextField();
        comboItensTabela = new javax.swing.JComboBox<>();
        btnInserir = new javax.swing.JButton();
        btnRemover = new javax.swing.JButton();
        btnEsvaziar = new javax.swing.JButton();
        painelDados = new javax.swing.JPanel();
        labelTamanho = new javax.swing.JLabel();
        fieldTamanho = new javax.swing.JTextField();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Tabela de Símbolos");
        setFrameIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/imagens/tabelaSimbolosP.png"))); // NOI18N

        javax.swing.GroupLayout painelDesenhoLayout = new javax.swing.GroupLayout(painelDesenho);
        painelDesenho.setLayout(painelDesenhoLayout);
        painelDesenhoLayout.setHorizontalGroup(
            painelDesenhoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 379, Short.MAX_VALUE)
        );
        painelDesenhoLayout.setVerticalGroup(
            painelDesenhoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        painelOperacoes.setBorder(javax.swing.BorderFactory.createTitledBorder("Operações"));

        labelChave.setText("Chave:");

        labelValor.setText("Valor:");

        labelRemover.setText("Remover:");

        fieldChave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fieldChaveActionPerformed(evt);
            }
        });

        fieldValor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fieldValorActionPerformed(evt);
            }
        });

        btnInserir.setText("Inserir");
        btnInserir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInserirActionPerformed(evt);
            }
        });

        btnRemover.setText("Remover");
        btnRemover.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRemoverActionPerformed(evt);
            }
        });

        btnEsvaziar.setText("Esvaziar");
        btnEsvaziar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEsvaziarActionPerformed(evt);
            }
        });

        painelDados.setBorder(javax.swing.BorderFactory.createTitledBorder("Dados da Estrutura"));

        labelTamanho.setText("Tamanho:");

        fieldTamanho.setEditable(false);

        javax.swing.GroupLayout painelDadosLayout = new javax.swing.GroupLayout(painelDados);
        painelDados.setLayout(painelDadosLayout);
        painelDadosLayout.setHorizontalGroup(
            painelDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelDadosLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelTamanho)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(fieldTamanho)
                .addContainerGap())
        );
        painelDadosLayout.setVerticalGroup(
            painelDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelDadosLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(painelDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelTamanho)
                    .addComponent(fieldTamanho, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(163, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout painelOperacoesLayout = new javax.swing.GroupLayout(painelOperacoes);
        painelOperacoes.setLayout(painelOperacoesLayout);
        painelOperacoesLayout.setHorizontalGroup(
            painelOperacoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelOperacoesLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(painelOperacoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(painelDados, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(painelOperacoesLayout.createSequentialGroup()
                        .addGap(9, 9, 9)
                        .addGroup(painelOperacoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(painelOperacoesLayout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(btnEsvaziar))
                            .addGroup(painelOperacoesLayout.createSequentialGroup()
                                .addGroup(painelOperacoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(labelChave)
                                    .addComponent(labelValor)
                                    .addComponent(labelRemover))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(painelOperacoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(fieldChave)
                                    .addComponent(fieldValor)
                                    .addComponent(comboItensTabela, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, painelOperacoesLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(painelOperacoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnInserir, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btnRemover, javax.swing.GroupLayout.Alignment.TRAILING))))
                .addContainerGap())
        );
        painelOperacoesLayout.setVerticalGroup(
            painelOperacoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelOperacoesLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(painelOperacoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelChave)
                    .addComponent(fieldChave, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(painelOperacoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(fieldValor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelValor))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnInserir)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(painelOperacoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelRemover)
                    .addComponent(comboItensTabela, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnRemover)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnEsvaziar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(painelDados, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(painelDesenho, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(painelOperacoes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(painelOperacoes, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(painelDesenho, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnEsvaziarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEsvaziarActionPerformed
        tabelaSimbolos.esvaziar();
        fieldChave.setText( "" );
        fieldValor.setText( "" );
        fieldTamanho.setText( String.valueOf( tabelaSimbolos.getTamanho() ) );
        atualizarCombo();
        painelDesenho.repaint();        
    }//GEN-LAST:event_btnEsvaziarActionPerformed

    private void btnInserirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInserirActionPerformed
        inserir();
    }//GEN-LAST:event_btnInserirActionPerformed

    private void btnRemoverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRemoverActionPerformed
        
        Object chave = comboItensTabela.getModel().getSelectedItem();
        
        if ( chave != null ) {
            
            tabelaSimbolos.remover( chave.toString() );
            fieldTamanho.setText( String.valueOf( tabelaSimbolos.getTamanho() ) );
            atualizarCombo();
            painelDesenho.repaint();
        }
        
    }//GEN-LAST:event_btnRemoverActionPerformed

    private void fieldValorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fieldValorActionPerformed
        inserir();
    }//GEN-LAST:event_fieldValorActionPerformed

    private void fieldChaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fieldChaveActionPerformed
        fieldValor.requestFocus();
    }//GEN-LAST:event_fieldChaveActionPerformed
    
    private void inserir() {
        
        fieldChave.requestFocus();
        
        tabelaSimbolos.inserir( fieldChave.getText(), fieldValor.getText() );
        fieldChave.setText( "" );
        fieldValor.setText( "" );
        fieldTamanho.setText( String.valueOf( tabelaSimbolos.getTamanho() ) );
        atualizarCombo();
        
        painelDesenho.repaint();
        
    }
    
    private void atualizarCombo() {
        
        dcbm.removeAllElements();
        
        for ( String s : tabelaSimbolos.chaves() ) {
            dcbm.addElement( s );
        }
        
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnEsvaziar;
    private javax.swing.JButton btnInserir;
    private javax.swing.JButton btnRemover;
    private javax.swing.JComboBox<String> comboItensTabela;
    private javax.swing.JTextField fieldChave;
    private javax.swing.JTextField fieldTamanho;
    private javax.swing.JTextField fieldValor;
    private javax.swing.JLabel labelChave;
    private javax.swing.JLabel labelRemover;
    private javax.swing.JLabel labelTamanho;
    private javax.swing.JLabel labelValor;
    private javax.swing.JPanel painelDados;
    private javax.swing.JPanel painelDesenho;
    private javax.swing.JPanel painelOperacoes;
    // End of variables declaration//GEN-END:variables
}
