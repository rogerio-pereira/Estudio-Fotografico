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
import java.awt.event.ContainerEvent;
import java.awt.event.ContainerListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 *
 * @author farofa
 */
public class RelatorioVendas extends javax.swing.JFrame implements KeyListener, ContainerListener{

    /**
     * Creates new form cadastroMarcas
     */
    public RelatorioVendas() {
        initComponents();
        addKeyAndContainerListenerRecursively(this);
        this.setLocationRelativeTo(null);
        radioOrdenaNenhum.setVisible(false);
        radioOrdenaNenhum.doClick();
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
            botaoGerarRelatorio.doClick();
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
    private void initComponents() {

        grupoRecebimentos = new javax.swing.ButtonGroup();
        grupoOrdena = new javax.swing.ButtonGroup();
        jPanel3 = new javax.swing.JPanel();
        labelCliente = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        labelTipoEvento = new javax.swing.JLabel();
        comboTipoEvento = new javax.swing.JComboBox();
        labelRecebimentos = new javax.swing.JLabel();
        radioRecebimentosTodasVendas = new javax.swing.JRadioButton();
        radioRecebimentosFinalizados = new javax.swing.JRadioButton();
        radioRecebimentosPendentes = new javax.swing.JRadioButton();
        labelOrdena = new javax.swing.JLabel();
        radioOrdenaCliente = new javax.swing.JRadioButton();
        radioOrdenaTipoEvento = new javax.swing.JRadioButton();
        radioSituacao = new javax.swing.JRadioButton();
        radioOrdenaNenhum = new javax.swing.JRadioButton();
        botaoPesquisarCliente = new javax.swing.JButton();
        botaoGerarRelatorio = new javax.swing.JButton();
        botaoCancelar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Tipos de Eventos");
        setBounds(new java.awt.Rectangle(250, 230, 0, 0));

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(95, 133, 169), null), "Filtros", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.TOP));

        labelCliente.setText("Cliente");

        labelTipoEvento.setText("Tipo do Evento");

        labelRecebimentos.setText("Recebimentos");

        grupoRecebimentos.add(radioRecebimentosTodasVendas);
        radioRecebimentosTodasVendas.setSelected(true);
        radioRecebimentosTodasVendas.setText("Todas as Vendas");

        grupoRecebimentos.add(radioRecebimentosFinalizados);
        radioRecebimentosFinalizados.setText("Vendas com Recebimentos Finalizados");

        grupoRecebimentos.add(radioRecebimentosPendentes);
        radioRecebimentosPendentes.setText("Vendas com Recebimentos Pendentes");

        labelOrdena.setText("Ordenar por:");

        grupoOrdena.add(radioOrdenaCliente);
        radioOrdenaCliente.setText("Cliente");

        grupoOrdena.add(radioOrdenaTipoEvento);
        radioOrdenaTipoEvento.setText("Tipo de Evento");

        grupoOrdena.add(radioSituacao);
        radioSituacao.setText("Situação");

        radioOrdenaNenhum.setSelected(true);

        botaoPesquisarCliente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/estudiofotografico/view/img/procurarCliente.png"))); // NOI18N

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelTipoEvento)
                    .addComponent(labelCliente)
                    .addComponent(labelRecebimentos)
                    .addComponent(labelOrdena))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(comboTipoEvento, 0, 239, Short.MAX_VALUE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jTextField1, javax.swing.GroupLayout.DEFAULT_SIZE, 184, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(botaoPesquisarCliente))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(radioRecebimentosPendentes)
                            .addComponent(radioRecebimentosFinalizados)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(radioRecebimentosTodasVendas)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(radioOrdenaNenhum))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(radioOrdenaCliente)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(radioOrdenaTipoEvento)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(radioSituacao)))
                        .addGap(0, 16, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(labelCliente)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(botaoPesquisarCliente))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelTipoEvento)
                    .addComponent(comboTipoEvento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelRecebimentos)
                    .addComponent(radioRecebimentosTodasVendas)
                    .addComponent(radioOrdenaNenhum))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(radioRecebimentosFinalizados)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(radioRecebimentosPendentes)
                        .addGap(30, 30, 30))
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(labelOrdena)
                        .addComponent(radioOrdenaCliente)
                        .addComponent(radioOrdenaTipoEvento)
                        .addComponent(radioSituacao)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        botaoGerarRelatorio.setIcon(new javax.swing.ImageIcon(getClass().getResource("/estudiofotografico/view/img/report_go.png"))); // NOI18N
        botaoGerarRelatorio.setText("Gerar Relatório");

        botaoCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/estudiofotografico/view/img/cancel.png"))); // NOI18N
        botaoCancelar.setText("Cancelar");
        botaoCancelar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                botaoCancelarMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(50, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 73, Short.MAX_VALUE)
                        .addComponent(botaoGerarRelatorio)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(botaoCancelar)
                        .addGap(112, 112, 112))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(botaoGerarRelatorio)
                    .addComponent(botaoCancelar))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void botaoCancelarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_botaoCancelarMouseClicked
        // TODO add your handling code here:
        dispose();
    }//GEN-LAST:event_botaoCancelarMouseClicked

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
            java.util.logging.Logger.getLogger(RelatorioVendas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(RelatorioVendas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(RelatorioVendas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(RelatorioVendas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /*
         * Create and display the form
         */
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new RelatorioVendas().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botaoCancelar;
    private javax.swing.JButton botaoGerarRelatorio;
    private javax.swing.JButton botaoPesquisarCliente;
    private javax.swing.JComboBox comboTipoEvento;
    private javax.swing.ButtonGroup grupoOrdena;
    private javax.swing.ButtonGroup grupoRecebimentos;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JLabel labelCliente;
    private javax.swing.JLabel labelOrdena;
    private javax.swing.JLabel labelRecebimentos;
    private javax.swing.JLabel labelTipoEvento;
    private javax.swing.JRadioButton radioOrdenaCliente;
    private javax.swing.JRadioButton radioOrdenaNenhum;
    private javax.swing.JRadioButton radioOrdenaTipoEvento;
    private javax.swing.JRadioButton radioRecebimentosFinalizados;
    private javax.swing.JRadioButton radioRecebimentosPendentes;
    private javax.swing.JRadioButton radioRecebimentosTodasVendas;
    private javax.swing.JRadioButton radioSituacao;
    // End of variables declaration//GEN-END:variables
}
