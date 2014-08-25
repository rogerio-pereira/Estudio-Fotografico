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
package estudiofotografico.view.erros;


import java.awt.Component;
import java.awt.Container;
import java.awt.event.ContainerEvent;
import java.awt.event.ContainerListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


/**
 *
 * @author Rogério Eduardo Pereira
 * Data Criação: Feb 17, 2014
 */
public class SucessoBackup extends javax.swing.JDialog implements KeyListener, ContainerListener
{
    /** Creates new form ErroAbrirConexao */
    public SucessoBackup (java.awt.Frame parent, boolean modal)
    {
        super(parent, modal);
        initComponents();

        //Adiciona Listeners de Botões
        addKeyAndContainerListenerRecursively(this);
        
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

        jLabel1 = new javax.swing.JLabel();
        botaoOK = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Sucesso!");
        setAlwaysOnTop(true);
        setModal(true);

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/estudiofotografico/view/img/accept-grande.png"))); // NOI18N
        jLabel1.setText("O backup foi realizado com sucesso!");

        botaoOK.setIcon(new javax.swing.ImageIcon(getClass().getResource("/estudiofotografico/view/img/accept.png"))); // NOI18N
        botaoOK.setText("OK");
        botaoOK.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                botaoOKActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(botaoOK)
                .addGap(80, 80, 80))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(botaoOK)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

private void botaoOKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoOKActionPerformed
    this.dispose();
}//GEN-LAST:event_botaoOKActionPerformed


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
            botaoOK.doClick();
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
            java.util.logging.Logger.getLogger(SucessoBackup.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }        catch (InstantiationException ex)
        {
            java.util.logging.Logger.getLogger(SucessoBackup.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }        catch (IllegalAccessException ex)
        {
            java.util.logging.Logger.getLogger(SucessoBackup.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }        catch (javax.swing.UnsupportedLookAndFeelException ex)
        {
            java.util.logging.Logger.getLogger(SucessoBackup.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable()
        {
            public void run ()
            {
                SucessoBackup dialog = new SucessoBackup(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter()
                {
                    @Override
                    public void windowClosing (java.awt.event.WindowEvent e)
                    {
                        System.exit(0);
                    }


                });
                dialog.setVisible(true);
            }


        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botaoOK;
    private javax.swing.JLabel jLabel1;
    // End of variables declaration//GEN-END:variables

}
