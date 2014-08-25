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

import estudiofotografico.control.ControladorBancodeDados;
import estudiofotografico.control.ControladorUsuario;
import estudiofotografico.model.Usuario;
import estudiofotografico.view.erros.ErroCampoEmBranco;
import estudiofotografico.view.erros.ErroConfirmacaoSenha;
import java.awt.Component;
import java.awt.Container;
import java.awt.event.ContainerEvent;
import java.awt.event.ContainerListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author farofa
 */
public class CadastroUsuario extends javax.swing.JFrame implements KeyListener, ContainerListener{

    /**
     * Creates new form cadastroMarcas
     */
    public CadastroUsuario() {
        initComponents();
        addKeyAndContainerListenerRecursively(this);
        this.setLocationRelativeTo(null);
		this.configuraTabelas();
		this.getUsuarios();
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
            this.botaoGravar.doClick();
        }
        
        //TECLA ESC
        if(e.getKeyCode() == KeyEvent.VK_ESCAPE)
        {
            dispose();
        }
    }
	
	//ALTERA TAMANHO TABELAS
    private void configuraTabelas()
    {
		//TABELA RECEBIMENTOS
        tabelaUsuarios.getColumnModel().getColumn(0).setPreferredWidth(127);
        tabelaUsuarios.getColumnModel().getColumn(1).setPreferredWidth(190);
        tabelaUsuarios.getColumnModel().getColumn(2).setPreferredWidth(90);
    }
	
	/*
	 * Método getUsuarios
	 * Obtem os usuarios cadastrados
	 */
	private void getUsuarios()
	{
		controlador					= new ControladorUsuario();
		List<Usuario>	usuarios	= controlador.getUsuarios();
		String			nivel		= new String();
		
		DefaultTableModel modelo = (DefaultTableModel) tabelaUsuarios.getModel();
		
		//Limpa tabela
		while(tabelaUsuarios.getRowCount() > 0)
			modelo.removeRow(0);
		
		//Insere Novamente
		for(Usuario usuario : usuarios)
		{
			if(!usuario.getUsuario().equals("SUPORTE"))
			{
				if(usuario.getNivel())
					nivel = "Administrador";
				else
					nivel = "Simples";
				
				modelo.addRow(new String[] {usuario.getNome(), usuario.getUsuario(), nivel});
			}
		}
		controlador = null;
	}
	
	/*
	 * Método validaCampos
	 * Valida se todos os campos obrigatorios estão preenchidos
	 */
	private Boolean validaCampos()
	{
		/*
		 * Erro
		 *		0 -> OK
		 *		1 -> Erro de campo em branco
		 *		2 -> erro de senha incompativel
		 */
		int erro = 0;
		
		//Senha
		if(!this.textoSenha.getText().equals(this.textoConfirmacao.getText()))
			erro = 2;
		
		//Campos em branco
		if(this.textoNome.getText().isEmpty())
			erro = 1;
		if(this.textoLogin.getText().isEmpty())
			erro = 1;
		if(this.textoSenha.getText().isEmpty())
			erro = 1;
		if(this.textoConfirmacao.getText().isEmpty())
			erro = 1;
		if(this.comboNivel.getSelectedIndex() == -1)
			erro = 1;
		
		
		
		
		if(erro == 0)
			return true;
		if(erro == 1)
		{
			janelaErroCampoEmBranco = new ErroCampoEmBranco(this, true);
			janelaErroCampoEmBranco.setVisible(true);
		}
		if(erro == 2)
		{
			janelaConfirmacaoSenha = new ErroConfirmacaoSenha(this, true);
			janelaConfirmacaoSenha.setVisible(true);
		}
			
		return false;
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
        painelCadastro = new javax.swing.JPanel();
        labelLogin = new javax.swing.JLabel();
        textoLogin = new javax.swing.JTextField();
        labelSenha = new javax.swing.JLabel();
        textoSenha = new javax.swing.JPasswordField();
        labelConfirmacao = new javax.swing.JLabel();
        textoConfirmacao = new javax.swing.JPasswordField();
        labelNivel = new javax.swing.JLabel();
        comboNivel = new javax.swing.JComboBox();
        labelNome = new javax.swing.JLabel();
        textoNome = new javax.swing.JTextField();
        botaoGravar = new javax.swing.JButton();
        painelExclusao = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabelaUsuarios = new javax.swing.JTable();
        botaoCancelar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Cadastro de Usuario");
        setBounds(new java.awt.Rectangle(0, 0, 0, 0));

        painelCadastro.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(95, 133, 169), null), "Usuarios", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.TOP));

        labelLogin.setForeground(java.awt.Color.red);
        labelLogin.setText("Login");

        textoLogin.addKeyListener(new java.awt.event.KeyAdapter()
        {
            public void keyTyped(java.awt.event.KeyEvent evt)
            {
                textoLoginKeyTyped(evt);
            }
        });

        labelSenha.setForeground(java.awt.Color.red);
        labelSenha.setText("Senha");

        labelConfirmacao.setForeground(java.awt.Color.red);
        labelConfirmacao.setText("Confirme a Senha");

        labelNivel.setForeground(java.awt.Color.red);
        labelNivel.setText("Nível Usuario");

        comboNivel.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Administrador", "Padrão" }));
        comboNivel.setSelectedIndex(-1);

        labelNome.setForeground(java.awt.Color.red);
        labelNome.setText("Nome");

        textoNome.addKeyListener(new java.awt.event.KeyAdapter()
        {
            public void keyTyped(java.awt.event.KeyEvent evt)
            {
                textoNomeKeyTyped(evt);
            }
        });

        botaoGravar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/estudiofotografico/view/img/accept.png"))); // NOI18N
        botaoGravar.setText("Cadastrar");
        botaoGravar.setMaximumSize(new java.awt.Dimension(95, 25));
        botaoGravar.setMinimumSize(new java.awt.Dimension(95, 25));
        botaoGravar.setPreferredSize(new java.awt.Dimension(95, 25));
        botaoGravar.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                botaoGravarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout painelCadastroLayout = new javax.swing.GroupLayout(painelCadastro);
        painelCadastro.setLayout(painelCadastroLayout);
        painelCadastroLayout.setHorizontalGroup(
            painelCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelCadastroLayout.createSequentialGroup()
                .addGroup(painelCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(painelCadastroLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(painelCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labelSenha)
                            .addComponent(labelConfirmacao)
                            .addComponent(labelNivel)
                            .addComponent(labelLogin)
                            .addComponent(labelNome))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(painelCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(textoLogin)
                            .addComponent(textoSenha)
                            .addComponent(textoConfirmacao)
                            .addComponent(comboNivel, 0, 310, Short.MAX_VALUE)
                            .addComponent(textoNome)))
                    .addGroup(painelCadastroLayout.createSequentialGroup()
                        .addGap(153, 153, 153)
                        .addComponent(botaoGravar, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        painelCadastroLayout.setVerticalGroup(
            painelCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelCadastroLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(painelCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelNome)
                    .addComponent(textoNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(painelCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(textoLogin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelLogin))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(painelCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelSenha)
                    .addComponent(textoSenha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(painelCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelConfirmacao)
                    .addComponent(textoConfirmacao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(painelCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelNivel)
                    .addComponent(comboNivel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(botaoGravar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        painelExclusao.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(95, 133, 169), null), "Excluir", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.TOP));

        tabelaUsuarios.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][]
            {

            },
            new String []
            {
                "Nome", "Usuario", "Nivel"
            }
        ));
        tabelaUsuarios.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        jScrollPane1.setViewportView(tabelaUsuarios);

        botaoCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/estudiofotografico/view/img/delete.png"))); // NOI18N
        botaoCancelar.setText("Excluir");
        botaoCancelar.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                botaoCancelarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout painelExclusaoLayout = new javax.swing.GroupLayout(painelExclusao);
        painelExclusao.setLayout(painelExclusaoLayout);
        painelExclusaoLayout.setHorizontalGroup(
            painelExclusaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
            .addGroup(painelExclusaoLayout.createSequentialGroup()
                .addGap(158, 158, 158)
                .addComponent(botaoCancelar)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        painelExclusaoLayout.setVerticalGroup(
            painelExclusaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, painelExclusaoLayout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 177, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(botaoCancelar)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(painelExclusao, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(painelCadastro, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(painelCadastro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(painelExclusao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void botaoGravarActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_botaoGravarActionPerformed
    {//GEN-HEADEREND:event_botaoGravarActionPerformed
		if(this.validaCampos())
		{
			controladorBD			= new ControladorBancodeDados();
			controladorBD.usuario	= new Usuario();
			
			//Define os valores
			controladorBD.usuario.setNome(this.textoNome.getText());
			controladorBD.usuario.setUsuario(this.textoLogin.getText());
			controladorBD.usuario.setSenha(this.textoSenha.getText());
			if(this.comboNivel.getSelectedIndex() == 0);
				controladorBD.usuario.setNivel(true);
			if(this.comboNivel.getSelectedIndex() == 1)
				controladorBD.usuario.setNivel(false);
			
			//Cadastra
			controladorBD.cadastrar(controladorBD.usuario, (JFrame)this);
			
			controlador = null;

			//Limpa os campos
			this.textoNome.setText("");
			this.textoLogin.setText("");
			this.textoSenha.setText("");
			this.textoConfirmacao.setText("");
			this.comboNivel.setSelectedIndex(-1);
			
			//Busca todos
			this.getUsuarios();
		}
    }//GEN-LAST:event_botaoGravarActionPerformed

    private void textoNomeKeyTyped(java.awt.event.KeyEvent evt)//GEN-FIRST:event_textoNomeKeyTyped
    {//GEN-HEADEREND:event_textoNomeKeyTyped
		int t = textoNome.getText().length();
		if(t == 100)
			 evt.setKeyChar((char) KeyEvent.VK_CLEAR);
    }//GEN-LAST:event_textoNomeKeyTyped

    private void textoLoginKeyTyped(java.awt.event.KeyEvent evt)//GEN-FIRST:event_textoLoginKeyTyped
    {//GEN-HEADEREND:event_textoLoginKeyTyped
		int t = textoLogin.getText().length();
		if(t == 15)
			 evt.setKeyChar((char) KeyEvent.VK_CLEAR);
    }//GEN-LAST:event_textoLoginKeyTyped

    private void botaoCancelarActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_botaoCancelarActionPerformed
    {//GEN-HEADEREND:event_botaoCancelarActionPerformed
		controlador				= new ControladorUsuario();
		controladorBD			= new ControladorBancodeDados();
		
		String usuario			= tabelaUsuarios.getValueAt(tabelaUsuarios.getSelectedRow(), 0).toString();
		Long   codigo			= controlador.getCodigoByUser(usuario);
		
		controladorBD.usuario	= new Usuario();
		controladorBD.apagar(codigo, controladorBD.usuario, (JFrame)this);
		
		controladorBD	= null;
		controlador		= null;
		this.getUsuarios();
    }//GEN-LAST:event_botaoCancelarActionPerformed

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
            java.util.logging.Logger.getLogger(CadastroUsuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CadastroUsuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CadastroUsuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CadastroUsuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /*
         * Create and display the form
         */
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new CadastroUsuario().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botaoCancelar;
    private javax.swing.JButton botaoGravar;
    private javax.swing.JComboBox comboNivel;
    private javax.swing.ButtonGroup grupoPessoa;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel labelConfirmacao;
    private javax.swing.JLabel labelLogin;
    private javax.swing.JLabel labelNivel;
    private javax.swing.JLabel labelNome;
    private javax.swing.JLabel labelSenha;
    private javax.swing.JPanel painelCadastro;
    private javax.swing.JPanel painelExclusao;
    private javax.swing.JTable tabelaUsuarios;
    private javax.swing.JPasswordField textoConfirmacao;
    private javax.swing.JTextField textoLogin;
    private javax.swing.JTextField textoNome;
    private javax.swing.JPasswordField textoSenha;
    // End of variables declaration//GEN-END:variables
	private ControladorUsuario		controlador;
	private ControladorBancodeDados	controladorBD;
	private ErroCampoEmBranco		janelaErroCampoEmBranco;
	private ErroConfirmacaoSenha	janelaConfirmacaoSenha;
}
