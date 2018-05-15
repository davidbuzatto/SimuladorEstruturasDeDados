/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gui;

import estruturas.ArvoreAVL;
import estruturas.algoritmos.arvores.TipoPercursoArvores;
import gui.desenho.PainelDesenho;
import gui.desenho.estruturas.ArvoreAVLAnotada;
import gui.desenho.estruturas.ArvoreAVLDesenhavel;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

/**
 * Janela para a simulação de árvores AVL.
 * 
 * @author David Buzatto
 */
public class IFArvoreAVL extends javax.swing.JInternalFrame {

    private ArvoreAVL<Integer> aavl;
    private ArvoreAVLDesenhavel aavlD;
    private ArvoreAVLAnotada<Integer> aavlAnt;
    private ArvoreAVLAnotada<Integer>.NoAnotado<Integer> noRemocao;
    private DefaultListModel dlm;
    private PainelDesenho painelD;
    
    /**
     * Creates new form IFArvoreAVL
     */
    public IFArvoreAVL() {
        
        aavl = new ArvoreAVL<>();
        aavlD = new ArvoreAVLDesenhavel( aavl );
        aavlAnt = new ArvoreAVLAnotada<>( aavl );
        
        initComponents();
        setVisible( true );
        
        dlm = new DefaultListModel();
        listResPercurso.setModel( dlm );
        
        painelD = (PainelDesenho) painelDesenho;
        
        aavlD.setPainel( painelD );
        
        fieldRaiz.setText( "vazio" );
        painelD.repaint();
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings( "unchecked" )
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnGroup = new javax.swing.ButtonGroup();
        menuPopUp = new javax.swing.JPopupMenu();
        itemMenuRemover = new javax.swing.JMenuItem();
        painelOperacoes = new javax.swing.JPanel();
        labelValor = new javax.swing.JLabel();
        fieldValor = new javax.swing.JTextField();
        btnInserir = new javax.swing.JButton();
        btnInserirN1 = new javax.swing.JButton();
        btnInserirAleatorio = new javax.swing.JButton();
        btnEsvaziar = new javax.swing.JButton();
        checkExibirAtributos = new javax.swing.JCheckBox();
        labelZoom = new javax.swing.JLabel();
        sliderZoom = new javax.swing.JSlider();
        painelPercursos = new javax.swing.JPanel();
        radioPre = new javax.swing.JRadioButton();
        radioEm = new javax.swing.JRadioButton();
        radioPos = new javax.swing.JRadioButton();
        radioNivel = new javax.swing.JRadioButton();
        checkInverter = new javax.swing.JCheckBox();
        spResPercurso = new javax.swing.JScrollPane();
        listResPercurso = new javax.swing.JList();
        btnAvancar = new javax.swing.JButton();
        btnRetroceder = new javax.swing.JButton();
        btnExecutar = new javax.swing.JButton();
        painelDados = new javax.swing.JPanel();
        labelRaiz = new javax.swing.JLabel();
        labelAltura = new javax.swing.JLabel();
        labelGrau = new javax.swing.JLabel();
        fieldRaiz = new javax.swing.JTextField();
        fieldAltura = new javax.swing.JTextField();
        fieldGrau = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        painelDesenho = new PainelDesenho( aavlD );

        itemMenuRemover.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/imagens/delete.png"))); // NOI18N
        itemMenuRemover.setText("remover");
        itemMenuRemover.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemMenuRemoverActionPerformed(evt);
            }
        });
        menuPopUp.add(itemMenuRemover);

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Árvore AVL");
        setFrameIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/imagens/avlP.png"))); // NOI18N

        painelOperacoes.setBorder(javax.swing.BorderFactory.createTitledBorder("Operações"));

        labelValor.setText("Valor:");

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

        btnInserirN1.setText("[ini...fim]");
        btnInserirN1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInserirN1ActionPerformed(evt);
            }
        });

        btnInserirAleatorio.setText("aleatório");
        btnInserirAleatorio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInserirAleatorioActionPerformed(evt);
            }
        });

        btnEsvaziar.setText("Esvaziar");
        btnEsvaziar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEsvaziarActionPerformed(evt);
            }
        });

        checkExibirAtributos.setText("ExibirAtributos dos Nós");
        checkExibirAtributos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkExibirAtributosActionPerformed(evt);
            }
        });

        labelZoom.setText("Zoom:");

        sliderZoom.setValue(0);
        sliderZoom.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                sliderZoomStateChanged(evt);
            }
        });

        painelPercursos.setBorder(javax.swing.BorderFactory.createTitledBorder("Percursos"));

        btnGroup.add(radioPre);
        radioPre.setSelected(true);
        radioPre.setText("Pré-Odem");

        btnGroup.add(radioEm);
        radioEm.setText("Em Ordem");

        btnGroup.add(radioPos);
        radioPos.setText("Pós-Ordem");

        btnGroup.add(radioNivel);
        radioNivel.setText("Em Nível");

        checkInverter.setText("Inverter");

        listResPercurso.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        listResPercurso.setEnabled(false);
        spResPercurso.setViewportView(listResPercurso);

        btnAvancar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/imagens/arrow_right.png"))); // NOI18N
        btnAvancar.setEnabled(false);
        btnAvancar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAvancarActionPerformed(evt);
            }
        });

        btnRetroceder.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/imagens/arrow_left.png"))); // NOI18N
        btnRetroceder.setEnabled(false);
        btnRetroceder.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRetrocederActionPerformed(evt);
            }
        });

        btnExecutar.setText("Executar");
        btnExecutar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExecutarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout painelPercursosLayout = new javax.swing.GroupLayout(painelPercursos);
        painelPercursos.setLayout(painelPercursosLayout);
        painelPercursosLayout.setHorizontalGroup(
            painelPercursosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelPercursosLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(painelPercursosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(painelPercursosLayout.createSequentialGroup()
                        .addGroup(painelPercursosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(radioPre)
                            .addComponent(radioEm)
                            .addComponent(radioPos)
                            .addComponent(radioNivel)
                            .addComponent(checkInverter))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(spResPercurso, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, painelPercursosLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnExecutar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnRetroceder)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnAvancar)))
                .addContainerGap())
        );
        painelPercursosLayout.setVerticalGroup(
            painelPercursosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelPercursosLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(radioPre)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(radioEm)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(radioPos)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(radioNivel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(checkInverter)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(painelPercursosLayout.createSequentialGroup()
                .addComponent(spResPercurso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(painelPercursosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAvancar)
                    .addComponent(btnRetroceder)
                    .addComponent(btnExecutar))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        painelDados.setBorder(javax.swing.BorderFactory.createTitledBorder("Dados da Estrutura"));

        labelRaiz.setText("Raiz:");

        labelAltura.setText("Altura:");

        labelGrau.setText("Grau:");

        fieldRaiz.setEditable(false);

        fieldAltura.setEditable(false);

        fieldGrau.setEditable(false);

        javax.swing.GroupLayout painelDadosLayout = new javax.swing.GroupLayout(painelDados);
        painelDados.setLayout(painelDadosLayout);
        painelDadosLayout.setHorizontalGroup(
            painelDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelDadosLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(painelDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(labelGrau)
                    .addComponent(labelAltura)
                    .addComponent(labelRaiz))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(painelDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(fieldRaiz)
                    .addComponent(fieldAltura)
                    .addComponent(fieldGrau))
                .addContainerGap())
        );
        painelDadosLayout.setVerticalGroup(
            painelDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelDadosLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(painelDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelRaiz)
                    .addComponent(fieldRaiz, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(painelDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelAltura)
                    .addComponent(fieldAltura, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(painelDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelGrau)
                    .addComponent(fieldGrau, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout painelOperacoesLayout = new javax.swing.GroupLayout(painelOperacoes);
        painelOperacoes.setLayout(painelOperacoesLayout);
        painelOperacoesLayout.setHorizontalGroup(
            painelOperacoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelOperacoesLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(painelOperacoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(painelDados, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(painelPercursos, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, painelOperacoesLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(painelOperacoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnInserir, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btnEsvaziar, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, painelOperacoesLayout.createSequentialGroup()
                                .addComponent(btnInserirN1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnInserirAleatorio))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, painelOperacoesLayout.createSequentialGroup()
                                .addComponent(labelValor)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(fieldValor, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, painelOperacoesLayout.createSequentialGroup()
                                .addComponent(labelZoom)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(sliderZoom, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(checkExibirAtributos, javax.swing.GroupLayout.Alignment.TRAILING))))
                .addContainerGap())
        );
        painelOperacoesLayout.setVerticalGroup(
            painelOperacoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelOperacoesLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(painelOperacoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelValor)
                    .addComponent(fieldValor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnInserir)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(painelOperacoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnInserirAleatorio)
                    .addComponent(btnInserirN1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnEsvaziar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(checkExibirAtributos)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(painelOperacoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(sliderZoom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelZoom))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(painelPercursos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(painelDados, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        painelDesenho.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                painelDesenhoMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout painelDesenhoLayout = new javax.swing.GroupLayout(painelDesenho);
        painelDesenho.setLayout(painelDesenhoLayout);
        painelDesenhoLayout.setHorizontalGroup(
            painelDesenhoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 475, Short.MAX_VALUE)
        );
        painelDesenhoLayout.setVerticalGroup(
            painelDesenhoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 570, Short.MAX_VALUE)
        );

        jScrollPane1.setViewportView(painelDesenho);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jScrollPane1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(painelOperacoes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(painelOperacoes, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jScrollPane1)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnInserirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInserirActionPerformed
        inserir();
    }//GEN-LAST:event_btnInserirActionPerformed

    private void btnEsvaziarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEsvaziarActionPerformed
        
        resetarPercurso();
        
        aavl.esvaziar();
        aavlAnt = new ArvoreAVLAnotada<>( aavl );
        aavlD.setAbbAnt( aavlAnt );
        
        fieldValor.setText( "" );
        
        fieldRaiz.setText( "vazio" );
        fieldAltura.setText( "" );
        fieldGrau.setText( "" );
        
        sliderZoom.setValue( 0 );
        //painelD.repaint();
        
    }//GEN-LAST:event_btnEsvaziarActionPerformed

    private void fieldValorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fieldValorActionPerformed
        inserir();
    }//GEN-LAST:event_fieldValorActionPerformed

    private void checkExibirAtributosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkExibirAtributosActionPerformed
        aavlD.setMostrarAtributosNos( checkExibirAtributos.isSelected() );
        painelD.repaint();
    }//GEN-LAST:event_checkExibirAtributosActionPerformed

    private void btnInserirAleatorioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInserirAleatorioActionPerformed
        
        boolean pedirInicio = true;
        boolean pedirFim = true;
        int valorInicio = 0;
        int valorFim = 0;

        while ( pedirInicio ) {

            try {

                String valor = JOptionPane.showInputDialog( this, "Valor do Início do Intervalo:" );

                if ( valor == null ) {
                    pedirInicio = false;
                } else {
                    valorInicio = Integer.parseInt( valor );
                    pedirInicio = false;
                }

            } catch ( NumberFormatException exc ) {
                JOptionPane.showMessageDialog( this, "Entre com um número inteiro!", "ERRO", JOptionPane.ERROR_MESSAGE );
            }

        }
        
        while ( pedirFim ) {

            try {

                String valor = JOptionPane.showInputDialog( this, "Valor do Fim do Intervalo:" );

                if ( valor == null ) {
                    pedirFim = false;
                } else {

                    valorFim = Integer.parseInt( valor );

                    resetarPercurso();
                    
                    List<Integer> lista = new ArrayList<>();
                        
                    if ( valorInicio < valorFim ) {
                        for ( int i = valorInicio; i <= valorFim; i++ ) {
                            lista.add( i );
                        }
                    } else {
                        for ( int i = valorInicio; i >= valorFim; i-- ) {
                            lista.add( i );
                        }
                    }

                    Collections.shuffle( lista );

                    for ( int i : lista ) {
                        aavl.inserir( i );
                    }
                    
                    aavlAnt = new ArvoreAVLAnotada<>( this.aavl );
                    aavlD.setAbbAnt( aavlAnt );
                    painelD.repaint();

                    fieldRaiz.setText( aavlAnt.getRaiz().valor.toString() );
                    fieldAltura.setText( String.valueOf( aavlAnt.getAltura() ) );
                    fieldGrau.setText( String.valueOf( aavlAnt.getGrau() ) );

                    pedirFim = false;

                }

            } catch ( NumberFormatException exc ) {
                JOptionPane.showMessageDialog( this, "Entre com um número inteiro!", "ERRO", JOptionPane.ERROR_MESSAGE );
            }

        }
        
    }//GEN-LAST:event_btnInserirAleatorioActionPerformed

    private void sliderZoomStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_sliderZoomStateChanged
        //abbD.setDiametroNos( sliderZoom.getValue() );
        painelD.setZoom( 1 + 0.02 * sliderZoom.getValue() );
        painelD.repaint();
    }//GEN-LAST:event_sliderZoomStateChanged

    private void painelDesenhoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_painelDesenhoMouseClicked
        
        if ( SwingUtilities.isRightMouseButton( evt ) ) {

            for ( ArvoreAVLAnotada<Integer>.NoAnotado<Integer> no : aavlAnt.percorrer( TipoPercursoArvores.EM_ORDEM ) ) {
                
                Point p = evt.getPoint();
                
                int cat1 = p.x - no.xCentro;
                int cat2 = p.y - no.yCentro;
                
                if ( cat1 * cat1 + cat2 * cat2 <= aavlD.getDiametroNos() * aavlD.getDiametroNos() ) {
                    
                    noRemocao = no;
                    itemMenuRemover.setText( String.format( "Remover elemento \"%d\"", no.valor ) );
                    menuPopUp.show( painelD, p.x, p.y );
                    
                    break;
                }
                
            }
            
        }
        
        painelD.repaint();
        
    }//GEN-LAST:event_painelDesenhoMouseClicked

    private void itemMenuRemoverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemMenuRemoverActionPerformed

        aavl.remover( noRemocao.valor );
        aavlAnt = new ArvoreAVLAnotada<>( this.aavl );
        aavlD.setAbbAnt( aavlAnt );
        
        fieldValor.setText( "" );

        fieldRaiz.setText( aavlAnt.getRaiz().valor.toString() );
        fieldAltura.setText( String.valueOf( aavlAnt.getAltura() ) );
        fieldGrau.setText( String.valueOf( aavlAnt.getGrau() ) );
        
        fieldValor.requestFocus();
        
        painelD.repaint();
            
        JOptionPane.showMessageDialog( painelD,
            String.format( "O elemento \"%d\" foi removido.",
                noRemocao.valor ), "Removido", JOptionPane.INFORMATION_MESSAGE );
        
        noRemocao = null;
        
    }//GEN-LAST:event_itemMenuRemoverActionPerformed

    private void btnExecutarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExecutarActionPerformed
        
        aavlD.reiniciar();
        dlm.clear();
        
        if ( !checkInverter.isSelected() ) {
            if ( radioPre.isSelected() ) {
                aavlD.setListaPercurso( 
                        (List<ArvoreAVLAnotada<Integer>.NoAnotado<Integer>>)
                         aavlAnt.percorrer( TipoPercursoArvores.PRE_ORDEM ) );
            } else if ( radioEm.isSelected() ) {
                aavlD.setListaPercurso( 
                        (List<ArvoreAVLAnotada<Integer>.NoAnotado<Integer>>)
                         aavlAnt.percorrer( TipoPercursoArvores.EM_ORDEM ) );
            } else if ( radioPos.isSelected() ) {
                aavlD.setListaPercurso( 
                        (List<ArvoreAVLAnotada<Integer>.NoAnotado<Integer>>)
                         aavlAnt.percorrer( TipoPercursoArvores.POS_ORDEM ) );
            } else {
                aavlD.setListaPercurso( 
                        (List<ArvoreAVLAnotada<Integer>.NoAnotado<Integer>>)
                         aavlAnt.percorrer( TipoPercursoArvores.EM_NIVEL ) );
            }
        } else {
            if ( radioPre.isSelected() ) {
                aavlD.setListaPercurso( 
                        (List<ArvoreAVLAnotada<Integer>.NoAnotado<Integer>>)
                         aavlAnt.percorrer( TipoPercursoArvores.PRE_ORDEM_INVERSO ) );
            } else if ( radioEm.isSelected() ) {
                aavlD.setListaPercurso( 
                        (List<ArvoreAVLAnotada<Integer>.NoAnotado<Integer>>)
                         aavlAnt.percorrer( TipoPercursoArvores.EM_ORDEM_INVERSO ) );
            } else if ( radioPos.isSelected() ) {
                aavlD.setListaPercurso( 
                        (List<ArvoreAVLAnotada<Integer>.NoAnotado<Integer>>)
                         aavlAnt.percorrer( TipoPercursoArvores.POS_ORDEM_INVERSO ) );
            } else {
                aavlD.setListaPercurso( 
                        (List<ArvoreAVLAnotada<Integer>.NoAnotado<Integer>>)
                         aavlAnt.percorrer( TipoPercursoArvores.EM_NIVEL_INVERSO ) );
            }
        }
        
        boolean temElementos = false;
        
        for ( ArvoreAVLAnotada<Integer>.NoAnotado<Integer> no : aavlD.getListaPercurso() ) {
            dlm.addElement( no.valor );
            temElementos = true;
        }
        
        if ( temElementos ) {
            btnRetroceder.setEnabled( true );
            btnAvancar.setEnabled( true );
        }
        
        painelD.repaint();
        
    }//GEN-LAST:event_btnExecutarActionPerformed

    private void btnRetrocederActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRetrocederActionPerformed
        aavlD.anterior();
        listResPercurso.setSelectedIndex(aavlD.getAtual() );
        painelD.repaint();
    }//GEN-LAST:event_btnRetrocederActionPerformed

    private void btnAvancarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAvancarActionPerformed
        aavlD.proximo();
        listResPercurso.setSelectedIndex(aavlD.getAtual() );
        painelD.repaint();
    }//GEN-LAST:event_btnAvancarActionPerformed

    private void btnInserirN1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInserirN1ActionPerformed

        boolean pedirInicio = true;
        boolean pedirFim = true;
        int valorInicio = 0;
        int valorFim = 0;

        while ( pedirInicio ) {

            try {

                String valor = JOptionPane.showInputDialog( this, "Valor do Início do Intervalo:" );

                if ( valor == null ) {
                    pedirInicio = false;
                } else {
                    valorInicio = Integer.parseInt( valor );
                    pedirInicio = false;
                }

            } catch ( NumberFormatException exc ) {
                JOptionPane.showMessageDialog( this, "Entre com um número inteiro!", "ERRO", JOptionPane.ERROR_MESSAGE );
            }

        }
        
        while ( pedirFim ) {

            try {

                String valor = JOptionPane.showInputDialog( this, "Valor do Fim do Intervalo:" );

                if ( valor == null ) {
                    pedirFim = false;
                } else {

                    valorFim = Integer.parseInt( valor );

                    resetarPercurso();
                    
                    if ( valorInicio < valorFim ) {
                        for ( int i = valorInicio; i <= valorFim; i++ ) {
                            aavl.inserir( i );
                        }
                    } else {
                        for ( int i = valorInicio; i >= valorFim; i-- ) {
                            aavl.inserir( i );
                        }
                    }
                    
                    aavlAnt = new ArvoreAVLAnotada<>( this.aavl );
                    aavlD.setAbbAnt( aavlAnt );
                    painelD.repaint();

                    fieldRaiz.setText( aavlAnt.getRaiz().valor.toString() );
                    fieldAltura.setText( String.valueOf( aavlAnt.getAltura() ) );
                    fieldGrau.setText( String.valueOf( aavlAnt.getGrau() ) );

                    pedirFim = false;

                }

            } catch ( NumberFormatException exc ) {
                JOptionPane.showMessageDialog( this, "Entre com um número inteiro!", "ERRO", JOptionPane.ERROR_MESSAGE );
            }

        }

    }//GEN-LAST:event_btnInserirN1ActionPerformed

    private void inserir() {
        
        try {
            
            resetarPercurso();
            
            aavl.inserir( Integer.valueOf( fieldValor.getText() ) );
            aavlAnt = new ArvoreAVLAnotada<>( this.aavl );
            aavlD.setAbbAnt( aavlAnt );

            fieldValor.setText( "" );

            fieldRaiz.setText( aavlAnt.getRaiz().valor.toString() );
            fieldAltura.setText( String.valueOf( aavlAnt.getAltura() ) );
            fieldGrau.setText( String.valueOf( aavlAnt.getGrau() ) );
            
        } catch ( NumberFormatException exc ) {
            
            JOptionPane.showMessageDialog( this, "Entre com um número inteiro!", "ERRO", JOptionPane.ERROR_MESSAGE );
            fieldValor.setSelectionStart( 0 );
            fieldValor.setSelectionEnd( fieldValor.getText().length() );
            
        }
        
        fieldValor.requestFocus();
        
        painelD.repaint();
        
    }

    private void resetarPercurso() {
        
        dlm.clear();
        aavlD.getListaPercurso().clear();
        aavlD.reiniciar();
        painelD.repaint();
        
        btnRetroceder.setEnabled( false );
        btnAvancar.setEnabled( false );
        
    }
           
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAvancar;
    private javax.swing.JButton btnEsvaziar;
    private javax.swing.JButton btnExecutar;
    private javax.swing.ButtonGroup btnGroup;
    private javax.swing.JButton btnInserir;
    private javax.swing.JButton btnInserirAleatorio;
    private javax.swing.JButton btnInserirN1;
    private javax.swing.JButton btnRetroceder;
    private javax.swing.JCheckBox checkExibirAtributos;
    private javax.swing.JCheckBox checkInverter;
    private javax.swing.JTextField fieldAltura;
    private javax.swing.JTextField fieldGrau;
    private javax.swing.JTextField fieldRaiz;
    private javax.swing.JTextField fieldValor;
    private javax.swing.JMenuItem itemMenuRemover;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel labelAltura;
    private javax.swing.JLabel labelGrau;
    private javax.swing.JLabel labelRaiz;
    private javax.swing.JLabel labelValor;
    private javax.swing.JLabel labelZoom;
    private javax.swing.JList listResPercurso;
    private javax.swing.JPopupMenu menuPopUp;
    private javax.swing.JPanel painelDados;
    private javax.swing.JPanel painelDesenho;
    private javax.swing.JPanel painelOperacoes;
    private javax.swing.JPanel painelPercursos;
    private javax.swing.JRadioButton radioEm;
    private javax.swing.JRadioButton radioNivel;
    private javax.swing.JRadioButton radioPos;
    private javax.swing.JRadioButton radioPre;
    private javax.swing.JSlider sliderZoom;
    private javax.swing.JScrollPane spResPercurso;
    // End of variables declaration//GEN-END:variables
}
