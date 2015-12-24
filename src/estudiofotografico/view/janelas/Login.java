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


import Utilidades.MD5Encoder;
import estudiofotografico.model.Usuario;
import estudiofotografico.EstudioFotografico;
import java.awt.Component;
import java.awt.Container;
import java.awt.Toolkit;
import java.awt.event.ContainerEvent;
import java.awt.event.ContainerListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author Rogério Eduardo Pereira
 * Data Criação: Feb 15, 2014
 */
public class Login extends javax.swing.JFrame implements KeyListener, ContainerListener
{
    /** Creates new form Login */
    public Login ()
    {
        initComponents();

        //Adiciona Listeners de Botões
        addKeyAndContainerListenerRecursively(this);
        
        //Centraliza Tela
        this.setLocationRelativeTo(null);
    }


    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings ("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents()
    {

        labelUsuario = new javax.swing.JLabel();
        textoUsuario = new javax.swing.JTextField();
        labelSenha = new javax.swing.JLabel();
        textoSenha = new javax.swing.JPasswordField();
        botaoLogin = new javax.swing.JButton();
        botaoCancelar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Login");
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/estudiofotografico/view/img/camera-photo-5.png")));

        labelUsuario.setText("Usuario");

        labelSenha.setText("Senha");

        botaoLogin.setIcon(new javax.swing.ImageIcon(getClass().getResource("/estudiofotografico/view/img/accept.png"))); // NOI18N
        botaoLogin.setText("Login");
        botaoLogin.setMaximumSize(new java.awt.Dimension(95, 25));
        botaoLogin.setMinimumSize(new java.awt.Dimension(95, 25));
        botaoLogin.setPreferredSize(new java.awt.Dimension(95, 25));
        botaoLogin.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                botaoLoginActionPerformed(evt);
            }
        });

        botaoCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/estudiofotografico/view/img/cancel.png"))); // NOI18N
        botaoCancelar.setText("Cancelar");
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
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labelUsuario)
                            .addComponent(labelSenha))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(textoSenha)
                            .addComponent(textoUsuario, javax.swing.GroupLayout.DEFAULT_SIZE, 179, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addComponent(botaoLogin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(botaoCancelar)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelUsuario)
                    .addComponent(textoUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelSenha)
                    .addComponent(textoSenha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(botaoLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(botaoCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

private void botaoCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoCancelarActionPerformed
    this.dispose();
}//GEN-LAST:event_botaoCancelarActionPerformed

    private void botaoLoginActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_botaoLoginActionPerformed
    {//GEN-HEADEREND:event_botaoLoginActionPerformed
		try
		{
			controladorLogin	= new estudiofotografico.control.Login();
			md5					= new MD5Encoder();
		
			System.out.println(md5.MD5Encode(this.textoSenha.getText()+"EstudioFotografico"));
			
			//Login valido
			user = controladorLogin.ValidaLogin(	this.textoUsuario.getText(),
													md5.MD5Encode(this.textoSenha.getText()+"EstudioFotografico"));
			if(user != null)
			{
				estudio = new EstudioFotografico();
				estudio.user = new Usuario();
				estudio.user = user;
				this.janePrincipal = new Principal();
				this.janePrincipal.setVisible(true);
				this.dispose();
			}
			//Login invalido
			else
			{
				this.textoUsuario.setText("");
				this.textoSenha.setText("");
				textoUsuario.requestFocus();
			}
		}
		catch (NoSuchAlgorithmException ex)
		{
			Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
		}
    }//GEN-LAST:event_botaoLoginActionPerformed


    /*
     * Métodos da Classe
     */
    //METODOS KEYLISTENER
    //KEYLISTENER RECURSIVO (COLOCA EM TODOS OS COMPONENTES)
    private void addKeyAndContainerListenerRecursively (Component c)    
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
        }
        catch (Exception e)
        {
            //Anuncie Aqui  
        }        
    }

    //EVENTOS KEYLISTENER

    public void keyTyped (KeyEvent e)
    {
    }    


    public void keyReleased (KeyEvent e)
    {
    }    


    public void componentAdded (ContainerEvent e)
    {
    }    


    public void componentRemoved (ContainerEvent e)
    {
    }    


    public void keyPressed (KeyEvent e)    
    {
        //TECLA ENTER
        if (e.getKeyCode() == KeyEvent.VK_ENTER)
        {
           botaoLogin.doClick();
        }

        //TECLA ESC
        if (e.getKeyCode() == KeyEvent.VK_ESCAPE)
        {
            this.Fechar();
        }
    }


    /*
     * Método Fechar()
     * Fecha Janela
     */
    private void Fechar ()
    {
        this.dispose();
    }


    /**
     * @param args the command line arguments
     */
    public static void main (String args[])
    {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try
        {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels())
            {
                if ("Nimbus".equals(info.getName()))
                {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        }
        catch (ClassNotFoundException ex)
        {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        catch (InstantiationException ex)
        {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        catch (IllegalAccessException ex)
        {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        catch (javax.swing.UnsupportedLookAndFeelException ex)
        {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable()
        {
            public void run ()
            {
                new Login().setVisible(true);
            }


        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botaoCancelar;
    private javax.swing.JButton botaoLogin;
    private javax.swing.JLabel labelSenha;
    private javax.swing.JLabel labelUsuario;
    private javax.swing.JPasswordField textoSenha;
    private javax.swing.JTextField textoUsuario;
    // End of variables declaration//GEN-END:variables
	private estudiofotografico.control.Login			controladorLogin;
	private MD5Encoder									md5;
	private estudiofotografico.view.janelas.Principal	janePrincipal;
	private Usuario										user;
	private EstudioFotografico							estudio;
}
