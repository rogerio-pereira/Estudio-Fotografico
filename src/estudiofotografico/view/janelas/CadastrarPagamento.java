/*
 * EstudioFotografico 1.0 - Gerenciador de Estudio Fotografico
 * Copyright (C) 2014 Rogério Eduardo Pereira
 * 
 * This file is part of EstudioFotografico 1.0.
 * 
 * This program is free software: you can redistribute it and/or modify it under the terms of the GNU 
 * General Public License as published by the Free Software Foundation, either version 3 of the 
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; 
 * without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. 
 * See the GNU General Public License for more details.
 */

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package estudiofotografico.view.janelas;

import java.awt.Component;
import java.awt.Container;
import java.awt.Toolkit;
import java.awt.event.ContainerEvent;
import java.awt.event.ContainerListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 *
 * @author farofa
 */
public class CadastrarPagamento extends javax.swing.JFrame implements KeyListener, ContainerListener{

    /**
     * Creates new form cadastroMarcas
     */
    public CadastrarPagamento() {
        initComponents();
        addKeyAndContainerListenerRecursively(this);
        this.setLocationRelativeTo(null);
    }
    
    //BOTAO ESC E ENTER
    //LISTENERS DO FRAME
    //KEYLISTENER RECURSIVO (COLOCA EM TODOS OS COMPONENTES)
    private void addKeyAndContainerListenerRecursively(Component c) 
    {  
        try 
        {  
            c.addKeyListener(this);  
            if (c instanceof Container) 
            {  
                Container cont = (Container) c;  
                cont.addContainerListener(this);  
                Component[] children = cont.getComponents();  
                for (int i = 0; i < children.length; i++) 
                {  
                    addKeyAndContainerListenerRecursively(children[i]);  
                }  
            }  
        } catch (Exception e) {  
            //Anuncie Aqui  
        }  
    }  
    
    //EVENTOS KEYLISTENER
    public void keyTyped(KeyEvent e) {}  
    public void keyReleased(KeyEvent e) {}  
    public void componentAdded(ContainerEvent e) {}  
    public void componentRemoved(ContainerEvent e) {}  
    public void keyPressed(KeyEvent e) 
    {
        //TECLA ENTER
        if(e.getKeyCode() == KeyEvent.VK_ENTER)
        {
            
        }
        
        //TECLA ESC
        if(e.getKeyCode() == KeyEvent.VK_ESCAPE)
        {
            dispose();
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents()
    {

        grupoPessoa = new javax.swing.ButtonGroup();
        painelDados = new javax.swing.JPanel();
        labelDataVencimento = new javax.swing.JLabel();
        labelValor = new javax.swing.JLabel();
        textoValor = new javax.swing.JTextField();
        labelObs = new javax.swing.JLabel();
        painelObs = new javax.swing.JScrollPane();
        textoObs = new javax.swing.JTextArea();
        calendarioPagamento = new com.toedter.calendar.JDateChooser();
        painelParcelamento = new javax.swing.JPanel();
        checkParcelar = new javax.swing.JCheckBox();
        textoParcelas = new javax.swing.JTextField();
        labelParcelas = new javax.swing.JLabel();
        painelPagamentoRealizado = new javax.swing.JPanel();
        checkPago = new javax.swing.JCheckBox();
        labelDataPagamento = new javax.swing.JLabel();
        labelFormaPagamento = new javax.swing.JLabel();
        comboFormaPagamento = new javax.swing.JComboBox();
        labelOutros = new javax.swing.JLabel();
        textoOutros = new javax.swing.JTextField();
        botaoGravar = new javax.swing.JButton();
        botaoCancelar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Pagamento");
        setBounds(new java.awt.Rectangle(0, 0, 0, 0));
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/estudiofotografico/view/img/camera-photo-5.png")));

        painelDados.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(95, 133, 169), null), "Dados", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.TOP));

        labelDataVencimento.setText("Data Vencimento");

        labelValor.setText("Valor à Pagar");

        labelObs.setText("Observações");

        textoObs.setColumns(20);
        textoObs.setRows(5);
        painelObs.setViewportView(textoObs);

        calendarioPagamento.setName("calendarioPagamento"); // NOI18N

        javax.swing.GroupLayout painelDadosLayout = new javax.swing.GroupLayout(painelDados);
        painelDados.setLayout(painelDadosLayout);
        painelDadosLayout.setHorizontalGroup(
            painelDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelDadosLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(painelDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(painelObs, javax.swing.GroupLayout.DEFAULT_SIZE, 227, Short.MAX_VALUE)
                    .addGroup(painelDadosLayout.createSequentialGroup()
                        .addGroup(painelDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labelDataVencimento)
                            .addComponent(labelValor))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(painelDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(textoValor, javax.swing.GroupLayout.DEFAULT_SIZE, 142, Short.MAX_VALUE)
                            .addComponent(calendarioPagamento, javax.swing.GroupLayout.DEFAULT_SIZE, 142, Short.MAX_VALUE)))
                    .addComponent(labelObs))
                .addContainerGap())
        );
        painelDadosLayout.setVerticalGroup(
            painelDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelDadosLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(painelDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(labelDataVencimento)
                    .addComponent(calendarioPagamento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(13, 13, 13)
                .addGroup(painelDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelValor)
                    .addComponent(textoValor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(labelObs)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(painelObs, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        painelParcelamento.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(95, 133, 169), null), "Parcelamento", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.TOP));

        checkParcelar.setText("Parcelar");
        checkParcelar.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                checkParcelarActionPerformed(evt);
            }
        });

        textoParcelas.setEnabled(false);

        labelParcelas.setText("Parcelas");
        labelParcelas.setEnabled(false);

        javax.swing.GroupLayout painelParcelamentoLayout = new javax.swing.GroupLayout(painelParcelamento);
        painelParcelamento.setLayout(painelParcelamentoLayout);
        painelParcelamentoLayout.setHorizontalGroup(
            painelParcelamentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelParcelamentoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(checkParcelar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(textoParcelas, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(labelParcelas)
                .addContainerGap(88, Short.MAX_VALUE))
        );
        painelParcelamentoLayout.setVerticalGroup(
            painelParcelamentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelParcelamentoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(painelParcelamentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(checkParcelar)
                    .addComponent(textoParcelas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelParcelas))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        painelPagamentoRealizado.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(95, 133, 169), null), "Pagamento Realizado", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.TOP));

        checkPago.setText("Pago");
        checkPago.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                checkPagoActionPerformed(evt);
            }
        });

        labelDataPagamento.setText("Data Pagamento");
        labelDataPagamento.setEnabled(false);

        labelFormaPagamento.setText("Forma Pagamento");
        labelFormaPagamento.setEnabled(false);

        comboFormaPagamento.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Dinheiro", "Cartão de Crédito", "Cartão de Débito", "Cheque", "Cheque de Terceiros", "Deposito Bancário", "Transferencia Bancaria", "Outros" }));
        comboFormaPagamento.setEnabled(false);
        comboFormaPagamento.addItemListener(new java.awt.event.ItemListener()
        {
            public void itemStateChanged(java.awt.event.ItemEvent evt)
            {
                comboFormaPagamentoItemStateChanged(evt);
            }
        });

        labelOutros.setText("Outros");
        labelOutros.setEnabled(false);

        textoOutros.setEnabled(false);

        javax.swing.GroupLayout painelPagamentoRealizadoLayout = new javax.swing.GroupLayout(painelPagamentoRealizado);
        painelPagamentoRealizado.setLayout(painelPagamentoRealizadoLayout);
        painelPagamentoRealizadoLayout.setHorizontalGroup(
            painelPagamentoRealizadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelPagamentoRealizadoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(painelPagamentoRealizadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(painelPagamentoRealizadoLayout.createSequentialGroup()
                        .addGroup(painelPagamentoRealizadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labelDataPagamento)
                            .addComponent(checkPago)
                            .addComponent(labelFormaPagamento))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(comboFormaPagamento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 1, Short.MAX_VALUE))
                    .addGroup(painelPagamentoRealizadoLayout.createSequentialGroup()
                        .addComponent(labelOutros)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(textoOutros, javax.swing.GroupLayout.DEFAULT_SIZE, 190, Short.MAX_VALUE)))
                .addContainerGap())
        );
        painelPagamentoRealizadoLayout.setVerticalGroup(
            painelPagamentoRealizadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelPagamentoRealizadoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(checkPago)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(labelDataPagamento)
                .addGap(18, 18, 18)
                .addGroup(painelPagamentoRealizadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelFormaPagamento)
                    .addComponent(comboFormaPagamento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(painelPagamentoRealizadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelOutros)
                    .addComponent(textoOutros, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(16, Short.MAX_VALUE))
        );

        botaoGravar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/estudiofotografico/view/img/accept.png"))); // NOI18N
        botaoGravar.setText("Gravar");

        botaoCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/estudiofotografico/view/img/cancel.png"))); // NOI18N
        botaoCancelar.setText("Cancelar");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(painelPagamentoRealizado, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(painelParcelamento, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(painelDados, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(botaoGravar)
                        .addGap(18, 18, 18)
                        .addComponent(botaoCancelar)
                        .addGap(39, 39, 39))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(painelDados, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(painelParcelamento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(painelPagamentoRealizado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(botaoGravar)
                    .addComponent(botaoCancelar))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void checkParcelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkParcelarActionPerformed
        //HABILITA CAMPO NUMERO DE PARCELAS SE TIVER MARCADO
        if(checkParcelar.isSelected())
        {
            textoParcelas.setEnabled(true);
            labelParcelas.setEnabled(true);
        }
        else
        {
            textoParcelas.setEnabled(false);
            labelParcelas.setEnabled(false);
        }
    }//GEN-LAST:event_checkParcelarActionPerformed

    private void checkPagoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkPagoActionPerformed
        //HABILITA CAMPOS PAGO
        if(checkPago.isSelected())
        {
            labelDataPagamento.setEnabled(true);
            calendarioPagamento.setEnabled(true);
            labelFormaPagamento.setEnabled(true);
            comboFormaPagamento.setEnabled(true);
        }
        else
        {
            labelDataPagamento.setEnabled(false);
            calendarioPagamento.setEnabled(false);
            labelFormaPagamento.setEnabled(false);
            comboFormaPagamento.setEnabled(false);
        }
    }//GEN-LAST:event_checkPagoActionPerformed

    private void comboFormaPagamentoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_comboFormaPagamentoItemStateChanged
        // TODO add your handling code here:
        if(comboFormaPagamento.getSelectedIndex() == 7)
        {
            labelOutros.setEnabled(true);
            textoOutros.setEnabled(true);
        }
    }//GEN-LAST:event_comboFormaPagamentoItemStateChanged

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /*
         * Set the Nimbus look and feel
         */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /*
         * If Nimbus (introduced in Java SE 6) is not available, stay with the
         * default look and feel. For details see
         * http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(CadastrarPagamento.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CadastrarPagamento.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CadastrarPagamento.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CadastrarPagamento.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /*
         * Create and display the form
         */
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new CadastrarPagamento().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botaoCancelar;
    private javax.swing.JButton botaoGravar;
    private com.toedter.calendar.JDateChooser calendarioPagamento;
    private javax.swing.JCheckBox checkPago;
    private javax.swing.JCheckBox checkParcelar;
    private javax.swing.JComboBox comboFormaPagamento;
    private javax.swing.ButtonGroup grupoPessoa;
    private javax.swing.JLabel labelDataPagamento;
    private javax.swing.JLabel labelDataVencimento;
    private javax.swing.JLabel labelFormaPagamento;
    private javax.swing.JLabel labelObs;
    private javax.swing.JLabel labelOutros;
    private javax.swing.JLabel labelParcelas;
    private javax.swing.JLabel labelValor;
    private javax.swing.JPanel painelDados;
    private javax.swing.JScrollPane painelObs;
    private javax.swing.JPanel painelPagamentoRealizado;
    private javax.swing.JPanel painelParcelamento;
    private javax.swing.JTextArea textoObs;
    private javax.swing.JTextField textoOutros;
    private javax.swing.JTextField textoParcelas;
    private javax.swing.JTextField textoValor;
    // End of variables declaration//GEN-END:variables
}
