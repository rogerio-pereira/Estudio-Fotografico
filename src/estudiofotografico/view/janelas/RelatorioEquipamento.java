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

import estudiofotografico.control.ControladorEquipamentos;
import estudiofotografico.control.ControladorRelatorios;
import java.awt.Component;
import java.awt.Container;
import java.awt.Toolkit;
import java.awt.event.ContainerEvent;
import java.awt.event.ContainerListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.List;

/**
 *
 * @author farofa
 */
public class RelatorioEquipamento extends javax.swing.JFrame implements KeyListener, ContainerListener{

    /**
     * Creates new form cadastroMarcas
     */
    public RelatorioEquipamento() {
        initComponents();
        addKeyAndContainerListenerRecursively(this);
        this.setLocationRelativeTo(null);
        radioOrdenaNenhum.setVisible(false);
        radioOrdenaNenhum.doClick();
		
		this.getTipos();
		this.getMarcas();
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
    private void initComponents()
    {

        grupoSituacao = new javax.swing.ButtonGroup();
        grupoOrdenar = new javax.swing.ButtonGroup();
        jPanel3 = new javax.swing.JPanel();
        labelTipo = new javax.swing.JLabel();
        comboTipoEquipamento = new javax.swing.JComboBox();
        labelMarca = new javax.swing.JLabel();
        comboMarca = new javax.swing.JComboBox();
        labelSituacao = new javax.swing.JLabel();
        radioSituacaoInativo = new javax.swing.JRadioButton();
        radioSituacaoAmbos = new javax.swing.JRadioButton();
        radioOrdenaNome = new javax.swing.JRadioButton();
        radioOrdenaMarca = new javax.swing.JRadioButton();
        radioOrdenaValor = new javax.swing.JRadioButton();
        radioOrdenaNenhum = new javax.swing.JRadioButton();
        radioSituacaoAtivo = new javax.swing.JRadioButton();
        labelOrdenar = new javax.swing.JLabel();
        radioSituacao = new javax.swing.JRadioButton();
        progresso = new javax.swing.JProgressBar();
        botaoGerarRelatorio = new javax.swing.JButton();
        botaoCancelar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Relatório de Equipamentos");
        setBounds(new java.awt.Rectangle(250, 230, 0, 0));
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/estudiofotografico/view/img/camera-photo-5.png")));

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(95, 133, 169), null), "Filtros", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.TOP));

        labelTipo.setText("Tipo");

        labelMarca.setText("Marca");

        labelSituacao.setText("Situação");

        grupoSituacao.add(radioSituacaoInativo);
        radioSituacaoInativo.setText("Inativo");

        grupoSituacao.add(radioSituacaoAmbos);
        radioSituacaoAmbos.setSelected(true);
        radioSituacaoAmbos.setText("Ambos");

        radioOrdenaNome.setText("Nome");

        radioOrdenaMarca.setText("Marca");

        radioOrdenaValor.setText("Valor");

        radioOrdenaNenhum.setSelected(true);

        grupoSituacao.add(radioSituacaoAtivo);
        radioSituacaoAtivo.setText("Ativo");

        labelOrdenar.setText("Ordenar por:");

        radioSituacao.setText("Situação");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(progresso, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(labelMarca)
                                    .addComponent(labelTipo))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(comboTipoEquipamento, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(comboMarca, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(labelSituacao)
                                    .addComponent(labelOrdenar))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addComponent(radioSituacaoAtivo)
                                        .addGap(1, 1, 1))
                                    .addComponent(radioOrdenaNome, javax.swing.GroupLayout.Alignment.TRAILING))
                                .addGap(11, 11, 11)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(radioSituacaoInativo)
                                    .addComponent(radioOrdenaMarca))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(radioSituacao)
                                    .addComponent(radioSituacaoAmbos))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addGap(3, 3, 3)
                                        .addComponent(radioOrdenaNenhum))
                                    .addComponent(radioOrdenaValor))))
                        .addGap(12, 12, 12))))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelTipo)
                    .addComponent(comboTipoEquipamento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelMarca)
                    .addComponent(comboMarca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(labelSituacao)
                        .addComponent(radioSituacaoInativo)
                        .addComponent(radioSituacaoAmbos)
                        .addComponent(radioSituacaoAtivo))
                    .addComponent(radioOrdenaNenhum))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(radioOrdenaNome)
                    .addComponent(radioOrdenaMarca)
                    .addComponent(radioOrdenaValor)
                    .addComponent(labelOrdenar)
                    .addComponent(radioSituacao))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(progresso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        botaoGerarRelatorio.setIcon(new javax.swing.ImageIcon(getClass().getResource("/estudiofotografico/view/img/report_go.png"))); // NOI18N
        botaoGerarRelatorio.setText("Gerar Relatório");
        botaoGerarRelatorio.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                botaoGerarRelatorioActionPerformed(evt);
            }
        });

        botaoCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/estudiofotografico/view/img/cancel.png"))); // NOI18N
        botaoCancelar.setText("Cancelar");
        botaoCancelar.addMouseListener(new java.awt.event.MouseAdapter()
        {
            public void mouseClicked(java.awt.event.MouseEvent evt)
            {
                botaoCancelarMouseClicked(evt);
            }
        });
        botaoCancelar.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                botaoCancelarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(55, 55, 55)
                        .addComponent(botaoGerarRelatorio)
                        .addGap(18, 18, 18)
                        .addComponent(botaoCancelar)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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

	private void getTipos()
	{
		List<String> tipos = new ControladorEquipamentos().getTipoEquipamento();
		for(String tipo : tipos)
		{
			comboTipoEquipamento.addItem(tipo);
		}
		comboTipoEquipamento.setSelectedIndex(-1);
	}
	
	private void getMarcas()
	{
		List<String> marcas = new ControladorEquipamentos().getMarcaEquipamento();
		for(String marca : marcas)
		{
			comboMarca.addItem(marca);
		}
		comboMarca.setSelectedIndex(-1);
	}
	
    private void botaoGerarRelatorioActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_botaoGerarRelatorioActionPerformed
    {//GEN-HEADEREND:event_botaoGerarRelatorioActionPerformed
		progresso.setIndeterminate(true);
				
		ControladorRelatorios relatorio	= new ControladorRelatorios();
		if(comboTipoEquipamento.getSelectedIndex() != -1)
			relatorio.parametros.put("TipoEquipamento", comboTipoEquipamento.getSelectedItem());
		else
			relatorio.parametros.put("TipoEquipamento", "");
		
		if(comboMarca.getSelectedIndex() != -1)
			relatorio.parametros.put("MarcaEquipamento", comboMarca.getSelectedItem());
		else
			relatorio.parametros.put("MarcaEquipamento", "");
			if(radioSituacaoAtivo.isSelected())
			{
				relatorio.parametros.put("Situacao0", 1);
				relatorio.parametros.put("Situacao1", 1);
				relatorio.parametros.put("Situacao2", 1);
				relatorio.parametros.put("Situacao3", 1);
				relatorio.parametros.put("Situacao4", 1);
				relatorio.parametros.put("Situacao5", 1);
			}
			else if(radioSituacaoInativo.isSelected())
			{
				relatorio.parametros.put("Situacao0", 1);
				relatorio.parametros.put("Situacao1", 1);
				relatorio.parametros.put("Situacao2", 2);
				relatorio.parametros.put("Situacao3", 3);
				relatorio.parametros.put("Situacao4", 4);
				relatorio.parametros.put("Situacao5", 5);
			}
			else if(radioSituacaoAmbos.isSelected())
			{
				relatorio.parametros.put("Situacao0", 0);
				relatorio.parametros.put("Situacao1", 1);
				relatorio.parametros.put("Situacao2", 2);
				relatorio.parametros.put("Situacao3", 3);
				relatorio.parametros.put("Situacao4", 4);
				relatorio.parametros.put("Situacao5", 5);
			}
		relatorio.parametros.put("Order", getOrder());
		
		progresso.setIndeterminate(false);
		
		relatorio.GeraRelatório("relatorios/Equipamentos.jasper");
    }//GEN-LAST:event_botaoGerarRelatorioActionPerformed

    private void botaoCancelarActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_botaoCancelarActionPerformed
    {//GEN-HEADEREND:event_botaoCancelarActionPerformed
		this.dispose();
    }//GEN-LAST:event_botaoCancelarActionPerformed

	private String getOrder()
	{
		if(radioOrdenaMarca.isSelected())
			return "tiposequipamentos.`tipo`, marcasequipamentos.`marca`";
		else if (radioOrdenaNome.isSelected())
			return "tiposequipamentos.`tipo`, equipamentos.`equipamento`";
		else if (radioSituacao.isSelected())
			return "tiposequipamentos.`tipo`, equipamentos.`situacao``";
		
		return "tiposequipamentos.`tipo`";
	}
	
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
            java.util.logging.Logger.getLogger(RelatorioEquipamento.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(RelatorioEquipamento.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(RelatorioEquipamento.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(RelatorioEquipamento.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /*
         * Create and display the form
         */
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new RelatorioEquipamento().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botaoCancelar;
    private javax.swing.JButton botaoGerarRelatorio;
    private javax.swing.JComboBox comboMarca;
    private javax.swing.JComboBox comboTipoEquipamento;
    private javax.swing.ButtonGroup grupoOrdenar;
    private javax.swing.ButtonGroup grupoSituacao;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JLabel labelMarca;
    private javax.swing.JLabel labelOrdenar;
    private javax.swing.JLabel labelSituacao;
    private javax.swing.JLabel labelTipo;
    private javax.swing.JProgressBar progresso;
    private javax.swing.JRadioButton radioOrdenaMarca;
    private javax.swing.JRadioButton radioOrdenaNenhum;
    private javax.swing.JRadioButton radioOrdenaNome;
    private javax.swing.JRadioButton radioOrdenaValor;
    private javax.swing.JRadioButton radioSituacao;
    private javax.swing.JRadioButton radioSituacaoAmbos;
    private javax.swing.JRadioButton radioSituacaoAtivo;
    private javax.swing.JRadioButton radioSituacaoInativo;
    // End of variables declaration//GEN-END:variables
}
