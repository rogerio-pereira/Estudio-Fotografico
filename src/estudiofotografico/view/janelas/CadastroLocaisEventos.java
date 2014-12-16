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
import estudiofotografico.control.ControladorEventos;
import estudiofotografico.model.EmailsLocaisEventos;
import estudiofotografico.model.LocaisEventos;
import estudiofotografico.model.TelefonesLocaisEventos;
import estudiofotografico.view.erros.ErroCampoEmBranco;
import java.awt.Component;
import java.awt.Container;
import java.awt.Toolkit;
import java.awt.event.ContainerEvent;
import java.awt.event.ContainerListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.swing.DefaultCellEditor;
import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

/**
 *
 * @author farofa
 */
public class CadastroLocaisEventos extends javax.swing.JFrame implements KeyListener, ContainerListener{

    /**
     * Creates new form cadastroMarcas
     */
    public CadastroLocaisEventos() {
        initComponents();
        addKeyAndContainerListenerRecursively(this);
        this.setLocationRelativeTo(null);
        this.defineModeloTabelas();
		this.textoCodigo.setText("** NOVO **");
    }
    
    
    /*====================================================================================================
      LISTENERS DO FRAME
    ====================================================================================================*/
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
				if(tabelaTelefone.isEditing() || tabelaEmail.isEditing())
				{
					if(tabelaTelefone.isEditing())
						tabelaTelefone.getCellEditor().stopCellEditing();
					if(tabelaEmail.isEditing())
						tabelaEmail.getCellEditor().stopCellEditing();
				}
				else
					this.botaoSalvar.doClick();
            }

            //TECLA ESC
            if(e.getKeyCode() == KeyEvent.VK_ESCAPE)
            {
                dispose();
            }
            
            //TAB
            if(e.getKeyCode() == KeyEvent.VK_TAB)
            {
                if(tabelaTelefone.hasFocus())
                    if(tabelaTelefone.getSelectedColumn() == 3)
                        adicionaLinhaTabela(tabelaTelefone);
               
                if(tabelaEmail.hasFocus())
					if(!tabelaEmail.isEditing())
					{
						if(tabelaEmail.getSelectedColumn() == 0)
							adicionaLinhaTabela(tabelaEmail);
					}
					else
					{
						tabelaEmail.getCellEditor().stopCellEditing();
						if(!this.textoEmail.validaEmail(this.tabelaEmail.getValueAt(this.tabelaEmail.getSelectedRow(), 0).toString()))
							this.tabelaEmail.setValueAt("", tabelaEmail.getSelectedRow(), 0);
					}
            }
        }

        
        /*
         * Método defineModeloTabelas
         * Define os componentes na colula Tipo e Telefone
         */
        private void defineModeloTabelas()
        {
            /*
             * TABELA TELEFONE
             */
            //Mascara Telefone
            TableColumn colunaTelefone = tabelaTelefone.getColumnModel().getColumn(0);
            colunaTelefone.setCellEditor(new DefaultCellEditor(textoTelefone));
            
            //Tipo de Telefone
            TableColumn colunaTipo = tabelaTelefone.getColumnModel().getColumn(1);
            colunaTipo.setCellEditor(new DefaultCellEditor(comboTipoTelefone));
            
            //Operadora
            TableColumn colunaOperadora = tabelaTelefone.getColumnModel().getColumn(2);
            colunaOperadora.setCellEditor(new DefaultCellEditor(textoOperadora));
			
			tabelaTelefone.setRowHeight(26);
            
            
            /*
             * TABELA E-MAIL
             */
            TableColumn colunaEmail = tabelaEmail.getColumnModel().getColumn(0);
            colunaEmail.setCellEditor(new DefaultCellEditor(textoEmail));
			
			tabelaEmail.setRowHeight(26);
        }
        
        /*
         * Método adicionaLinhaTabela
         * Adiciona uma linha na tabela passada por parametro
         */
        private void adicionaLinhaTabela(JTable tabela)
        {
            DefaultTableModel modelo = (DefaultTableModel) tabela.getModel();
            modelo.addRow(new Object[]{null,null});
        }
        
        /*
         * Método removerLinhaTabela
         * Remove a linha selecionada da tabela
         */
        private void removerLinhaTabela(JTable tabela)
        {
            DefaultTableModel modelo = (DefaultTableModel) tabela.getModel();
            modelo.removeRow(tabela.getSelectedRow());
        }
		
		
		
	/*
	 * Valida os campos antes de inserir ou alterar
	 */
	public Boolean validaCampos()
	{
		Boolean valida = true;
		
		if(this.textoLocal.getText().isEmpty())
			valida = false;
		if(this.textoEndereco.getText().isEmpty())
			valida = false;
		if(this.textoNumero.getText().isEmpty())
			valida = false;
		if(this.textoBairro.getText().isEmpty())
			valida = false;
		if(this.textoCidade.getText().isEmpty())
			valida = false;
		if(this.comboEstado.getSelectedIndex() == -1)
			valida = false;
		if(this.textoCEP.getText().isEmpty())
			valida = false;
		
		if(valida == false)
		{
			janelaErroCampoEmBranco = new ErroCampoEmBranco(this, true);
			janelaErroCampoEmBranco.setVisible(true);
		}
		
		return valida;
	}
	
	/*
	 * Método limpaTela
	 * Limpa a tela
	 */
	public void limpaTela()
	{
		this.textoCodigo.setText("** NOVO **");
		this.textoLocal.setText("");
		this.textoResponsavel.setText("");
		this.textoSite.setText("");
		this.textoEndereco.setText("");
		this.textoNumero.setText("");
		this.textoBairro.setText("");
		this.textoComplemento.setText("");
		this.textoCidade.setText("");
		this.comboEstado.setSelectedIndex(-1);
		this.textoCEP.setText("");
		this.textoObservacoes.setText("");
		
		this.apagaTabela(tabelaEmail);
		this.apagaTabela(tabelaTelefone);
	}
	
	/*
	 * Método apagaTabela
	 * Apaga a Tabela Selecionada
	 */
	private void apagaTabela(JTable tabela)
	{
		DefaultTableModel modelo = (DefaultTableModel)tabela.getModel();
		while(tabela.getRowCount() > 0)
			modelo.removeRow(0);
	} 
	
	/*
	 * Método habilitaCampos
	 * Habilita/Desabilita campos
	 */
	private void habilitaCampos(Boolean habilita)
	{
		this.textoCodigo.setEnabled(habilita);
		this.textoLocal.setEnabled(habilita);
		this.textoResponsavel.setEnabled(habilita);
		this.textoSite.setEnabled(habilita);
		this.textoEndereco.setEnabled(habilita);
		this.textoNumero.setEnabled(habilita);
		this.textoBairro.setEnabled(habilita);
		this.textoComplemento.setEnabled(habilita);
		this.textoCidade.setEnabled(habilita);
		this.comboEstado.setEnabled(habilita);
		this.textoCEP.setEnabled(habilita);
		this.textoObservacoes.setEnabled(habilita);
		
		this.tabelaTelefone.setEnabled(habilita);
		this.tabelaEmail.setEnabled(habilita);
		
		this.botaoAdicionarTelefone.setEnabled(habilita);
		this.botaoRemoverTelefone.setEnabled(habilita);
		this.botaoAdicionarEmail.setEnabled(habilita);
		this.botaoRemoverEmail.setEnabled(habilita);
	}
	/*====================================================================================================
      FIM METODOS MEUS
    ====================================================================================================*/
    
        
     /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents()
    {

        comboTipoTelefone = new javax.swing.JComboBox();
        textoTelefone = new javax.swing.JFormattedTextField();
        textoOperadora = new javax.swing.JTextField();
        textoEmail = new formattedFields.JTextEmail();
        botaoSalvar = new javax.swing.JButton();
        botaoAlterar = new javax.swing.JButton();
        botaoLocalizar = new javax.swing.JButton();
        botaoExcluir = new javax.swing.JButton();
        botaoCancelar = new javax.swing.JButton();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        painelEquipamento = new javax.swing.JPanel();
        labelDescricao = new javax.swing.JLabel();
        textoLocal = new javax.swing.JTextField();
        labelCodigo = new javax.swing.JLabel();
        textoCodigo = new javax.swing.JTextField();
        labelResponsavel = new javax.swing.JLabel();
        textoResponsavel = new javax.swing.JTextField();
        textoSite = new javax.swing.JTextField();
        labelSite = new javax.swing.JLabel();
        labelEndereco = new javax.swing.JLabel();
        textoEndereco = new javax.swing.JTextField();
        labelNumero = new javax.swing.JLabel();
        textoNumero = new javax.swing.JTextField();
        labelComplemento = new javax.swing.JLabel();
        textoComplemento = new javax.swing.JTextField();
        labelBairro = new javax.swing.JLabel();
        textoBairro = new javax.swing.JTextField();
        labelCidade = new javax.swing.JLabel();
        textoCidade = new javax.swing.JTextField();
        labelEstado = new javax.swing.JLabel();
        comboEstado = new javax.swing.JComboBox();
        labelCEP = new javax.swing.JLabel();
        textoCEP = new formattedFields.JTextCep();
        painelContato = new javax.swing.JPanel();
        labelTelefone = new javax.swing.JLabel();
        ScrollTelefone = new javax.swing.JScrollPane();
        tabelaTelefone = new javax.swing.JTable();
        botaoAdicionarTelefone = new javax.swing.JButton();
        botaoRemoverTelefone = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        labelEmail = new javax.swing.JLabel();
        ScrollEmail = new javax.swing.JScrollPane();
        tabelaEmail = new javax.swing.JTable();
        botaoRemoverEmail = new javax.swing.JButton();
        botaoAdicionarEmail = new javax.swing.JButton();
        painelObservacao = new javax.swing.JPanel();
        labelObservacoes = new javax.swing.JLabel();
        scrollObservacoes = new javax.swing.JScrollPane();
        textoObservacoes = new javax.swing.JTextArea();

        comboTipoTelefone.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Comercial", "Celular", "Fax", "Recado", "Residencial", "Outro" }));

        try
        {
            textoTelefone.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("(##) ##### - ####")));
        } catch (java.text.ParseException ex)
        {
            ex.printStackTrace();
        }

        textoOperadora.setText("jTextField1");
        textoOperadora.addKeyListener(new java.awt.event.KeyAdapter()
        {
            public void keyTyped(java.awt.event.KeyEvent evt)
            {
                textoOperadoraKeyTyped(evt);
            }
        });

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Cadastro Locais de Eventos");
        setBounds(new java.awt.Rectangle(250, 112, 0, 0));
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/estudiofotografico/view/img/camera-photo-5.png")));

        botaoSalvar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/estudiofotografico/view/img/accept.png"))); // NOI18N
        botaoSalvar.setText("Salvar");
        botaoSalvar.setPreferredSize(new java.awt.Dimension(89, 30));
        botaoSalvar.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                botaoSalvarActionPerformed(evt);
            }
        });

        botaoAlterar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/estudiofotografico/view/img/pencil.png"))); // NOI18N
        botaoAlterar.setText("Alterar");
        botaoAlterar.setEnabled(false);
        botaoAlterar.setPreferredSize(new java.awt.Dimension(89, 30));
        botaoAlterar.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                botaoAlterarActionPerformed(evt);
            }
        });

        botaoLocalizar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/estudiofotografico/view/img/magnifier.png"))); // NOI18N
        botaoLocalizar.setText("Localizar");
        botaoLocalizar.setPreferredSize(new java.awt.Dimension(89, 30));
        botaoLocalizar.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                botaoLocalizarActionPerformed(evt);
            }
        });

        botaoExcluir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/estudiofotografico/view/img/delete.png"))); // NOI18N
        botaoExcluir.setText("Excluir");
        botaoExcluir.setEnabled(false);
        botaoExcluir.setPreferredSize(new java.awt.Dimension(89, 30));
        botaoExcluir.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                botaoExcluirActionPerformed(evt);
            }
        });

        botaoCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/estudiofotografico/view/img/cancel.png"))); // NOI18N
        botaoCancelar.setText("Cancelar");
        botaoCancelar.setPreferredSize(new java.awt.Dimension(89, 30));
        botaoCancelar.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                botaoCancelarActionPerformed(evt);
            }
        });

        painelEquipamento.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(95, 133, 169), null), "Locais", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.TOP));

        labelDescricao.setForeground(java.awt.Color.red);
        labelDescricao.setText("Local");

        textoLocal.addKeyListener(new java.awt.event.KeyAdapter()
        {
            public void keyTyped(java.awt.event.KeyEvent evt)
            {
                textoLocalKeyTyped(evt);
            }
        });

        labelCodigo.setText("Codigo");

        textoCodigo.setEditable(false);
        textoCodigo.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        textoCodigo.setForeground(java.awt.Color.red);
        textoCodigo.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        textoCodigo.setFocusable(false);

        labelResponsavel.setText("Responsavel");

        textoResponsavel.addKeyListener(new java.awt.event.KeyAdapter()
        {
            public void keyTyped(java.awt.event.KeyEvent evt)
            {
                textoResponsavelKeyTyped(evt);
            }
        });

        textoSite.addKeyListener(new java.awt.event.KeyAdapter()
        {
            public void keyTyped(java.awt.event.KeyEvent evt)
            {
                textoSiteKeyTyped(evt);
            }
        });

        labelSite.setText("Site");

        labelEndereco.setForeground(java.awt.Color.red);
        labelEndereco.setText("Endereço");

        textoEndereco.addKeyListener(new java.awt.event.KeyAdapter()
        {
            public void keyTyped(java.awt.event.KeyEvent evt)
            {
                textoEnderecoKeyTyped(evt);
            }
        });

        labelNumero.setForeground(java.awt.Color.red);
        labelNumero.setText("Numero");

        labelComplemento.setText("Complemento");

        textoComplemento.addKeyListener(new java.awt.event.KeyAdapter()
        {
            public void keyTyped(java.awt.event.KeyEvent evt)
            {
                textoComplementoKeyTyped(evt);
            }
        });

        labelBairro.setForeground(java.awt.Color.red);
        labelBairro.setText("Bairro");

        textoBairro.addKeyListener(new java.awt.event.KeyAdapter()
        {
            public void keyTyped(java.awt.event.KeyEvent evt)
            {
                textoBairroKeyTyped(evt);
            }
        });

        labelCidade.setForeground(java.awt.Color.red);
        labelCidade.setText("Cidade");

        textoCidade.addKeyListener(new java.awt.event.KeyAdapter()
        {
            public void keyTyped(java.awt.event.KeyEvent evt)
            {
                textoCidadeKeyTyped(evt);
            }
        });

        labelEstado.setForeground(java.awt.Color.red);
        labelEstado.setText("Estado");

        comboEstado.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "AC", "AL", "AP", "AM", "BA", "CE", "DF", "ES", "GO", "MA", "MT", "MS", "MG", "PR", "PB", "PA", "PE", "PI", "RJ", "RN", "RS", "RO", "RR", "SC", "SE", "SP", "TO" }));
        comboEstado.setSelectedIndex(-1);

        labelCEP.setForeground(java.awt.Color.red);
        labelCEP.setText("CEP");
        labelCEP.addMouseListener(new java.awt.event.MouseAdapter()
        {
            public void mouseClicked(java.awt.event.MouseEvent evt)
            {
                labelCEPMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout painelEquipamentoLayout = new javax.swing.GroupLayout(painelEquipamento);
        painelEquipamento.setLayout(painelEquipamentoLayout);
        painelEquipamentoLayout.setHorizontalGroup(
            painelEquipamentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelEquipamentoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(painelEquipamentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(painelEquipamentoLayout.createSequentialGroup()
                        .addComponent(labelBairro)
                        .addGap(0, 471, Short.MAX_VALUE))
                    .addGroup(painelEquipamentoLayout.createSequentialGroup()
                        .addGroup(painelEquipamentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labelSite)
                            .addComponent(labelResponsavel)
                            .addComponent(labelDescricao)
                            .addComponent(labelEndereco)
                            .addComponent(labelCidade))
                        .addGap(9, 9, 9)
                        .addGroup(painelEquipamentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, painelEquipamentoLayout.createSequentialGroup()
                                .addComponent(textoLocal, javax.swing.GroupLayout.DEFAULT_SIZE, 294, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(labelCodigo)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(textoCodigo, javax.swing.GroupLayout.DEFAULT_SIZE, 94, Short.MAX_VALUE))
                            .addComponent(textoResponsavel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 429, Short.MAX_VALUE)
                            .addComponent(textoSite, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 429, Short.MAX_VALUE)
                            .addGroup(painelEquipamentoLayout.createSequentialGroup()
                                .addGroup(painelEquipamentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(textoEndereco)
                                    .addGroup(painelEquipamentoLayout.createSequentialGroup()
                                        .addComponent(textoCidade)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(labelEstado)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(comboEstado, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(textoBairro, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(painelEquipamentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(painelEquipamentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(painelEquipamentoLayout.createSequentialGroup()
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(labelComplemento))
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, painelEquipamentoLayout.createSequentialGroup()
                                            .addGap(46, 46, 46)
                                            .addComponent(labelCEP)))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, painelEquipamentoLayout.createSequentialGroup()
                                        .addGap(28, 28, 28)
                                        .addComponent(labelNumero)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(painelEquipamentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(textoNumero)
                                    .addComponent(textoComplemento, javax.swing.GroupLayout.DEFAULT_SIZE, 126, Short.MAX_VALUE)
                                    .addComponent(textoCEP, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))))
                .addContainerGap())
        );
        painelEquipamentoLayout.setVerticalGroup(
            painelEquipamentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelEquipamentoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(painelEquipamentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelDescricao)
                    .addComponent(textoLocal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(textoCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelCodigo))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(painelEquipamentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelResponsavel)
                    .addComponent(textoResponsavel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(painelEquipamentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelSite)
                    .addComponent(textoSite, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(painelEquipamentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelEndereco)
                    .addComponent(textoEndereco, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelNumero)
                    .addComponent(textoNumero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(painelEquipamentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelBairro)
                    .addComponent(textoBairro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelComplemento)
                    .addComponent(textoComplemento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(painelEquipamentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(painelEquipamentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(labelCidade)
                        .addComponent(textoCidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(labelEstado)
                        .addComponent(comboEstado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(labelCEP))
                    .addComponent(textoCEP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(58, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Locais", painelEquipamento);

        painelContato.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(95, 133, 169), null), "Telefones", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.TOP));

        labelTelefone.setText("Telefones");

        tabelaTelefone.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][]
            {

            },
            new String []
            {
                "Telefone", "Tipo", "Operadora", "Ramal"
            }
        )
        {
            Class[] types = new Class []
            {
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Integer.class
            };

            public Class getColumnClass(int columnIndex)
            {
                return types [columnIndex];
            }
        });
        ScrollTelefone.setViewportView(tabelaTelefone);

        botaoAdicionarTelefone.setIcon(new javax.swing.ImageIcon(getClass().getResource("/estudiofotografico/view/img/add.png"))); // NOI18N
        botaoAdicionarTelefone.setText("Adicionar");
        botaoAdicionarTelefone.setMaximumSize(new java.awt.Dimension(77, 16));
        botaoAdicionarTelefone.setMinimumSize(new java.awt.Dimension(77, 16));
        botaoAdicionarTelefone.setPreferredSize(new java.awt.Dimension(77, 20));
        botaoAdicionarTelefone.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                botaoAdicionarTelefoneActionPerformed(evt);
            }
        });

        botaoRemoverTelefone.setIcon(new javax.swing.ImageIcon(getClass().getResource("/estudiofotografico/view/img/delete.png"))); // NOI18N
        botaoRemoverTelefone.setText("Remover");
        botaoRemoverTelefone.setMaximumSize(new java.awt.Dimension(77, 16));
        botaoRemoverTelefone.setMinimumSize(new java.awt.Dimension(77, 16));
        botaoRemoverTelefone.setPreferredSize(new java.awt.Dimension(77, 20));
        botaoRemoverTelefone.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                botaoRemoverTelefoneActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout painelContatoLayout = new javax.swing.GroupLayout(painelContato);
        painelContato.setLayout(painelContatoLayout);
        painelContatoLayout.setHorizontalGroup(
            painelContatoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelContatoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(painelContatoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(ScrollTelefone, javax.swing.GroupLayout.DEFAULT_SIZE, 499, Short.MAX_VALUE)
                    .addGroup(painelContatoLayout.createSequentialGroup()
                        .addComponent(labelTelefone)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 218, Short.MAX_VALUE)
                        .addComponent(botaoAdicionarTelefone, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(botaoRemoverTelefone, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        painelContatoLayout.setVerticalGroup(
            painelContatoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelContatoLayout.createSequentialGroup()
                .addGroup(painelContatoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelTelefone)
                    .addComponent(botaoAdicionarTelefone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(botaoRemoverTelefone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ScrollTelefone, javax.swing.GroupLayout.DEFAULT_SIZE, 183, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Telefones", painelContato);
        painelContato.getAccessibleContext().setAccessibleName("");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(95, 133, 169), null), "E-mail", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.TOP));

        labelEmail.setText("Email");

        tabelaEmail.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][]
            {

            },
            new String []
            {
                "E-mail"
            }
        ));
        ScrollEmail.setViewportView(tabelaEmail);

        botaoRemoverEmail.setIcon(new javax.swing.ImageIcon(getClass().getResource("/estudiofotografico/view/img/delete.png"))); // NOI18N
        botaoRemoverEmail.setText("Remover");
        botaoRemoverEmail.setMaximumSize(new java.awt.Dimension(77, 16));
        botaoRemoverEmail.setMinimumSize(new java.awt.Dimension(77, 16));
        botaoRemoverEmail.setPreferredSize(new java.awt.Dimension(77, 20));
        botaoRemoverEmail.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                botaoRemoverEmailActionPerformed(evt);
            }
        });

        botaoAdicionarEmail.setIcon(new javax.swing.ImageIcon(getClass().getResource("/estudiofotografico/view/img/add.png"))); // NOI18N
        botaoAdicionarEmail.setText("Adicionar");
        botaoAdicionarEmail.setMaximumSize(new java.awt.Dimension(77, 16));
        botaoAdicionarEmail.setMinimumSize(new java.awt.Dimension(77, 16));
        botaoAdicionarEmail.setPreferredSize(new java.awt.Dimension(77, 20));
        botaoAdicionarEmail.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                botaoAdicionarEmailActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(labelEmail)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(botaoAdicionarEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(botaoRemoverEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(ScrollEmail, javax.swing.GroupLayout.DEFAULT_SIZE, 499, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelEmail)
                    .addComponent(botaoAdicionarEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(botaoRemoverEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ScrollEmail, javax.swing.GroupLayout.DEFAULT_SIZE, 183, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("E-mail", jPanel1);

        painelObservacao.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(95, 133, 169), null), "Observações"));

        labelObservacoes.setText("Observações");

        textoObservacoes.setColumns(20);
        textoObservacoes.setLineWrap(true);
        textoObservacoes.setRows(5);
        textoObservacoes.setWrapStyleWord(true);
        textoObservacoes.addKeyListener(new java.awt.event.KeyAdapter()
        {
            public void keyTyped(java.awt.event.KeyEvent evt)
            {
                textoObservacoesKeyTyped(evt);
            }
        });
        scrollObservacoes.setViewportView(textoObservacoes);

        javax.swing.GroupLayout painelObservacaoLayout = new javax.swing.GroupLayout(painelObservacao);
        painelObservacao.setLayout(painelObservacaoLayout);
        painelObservacaoLayout.setHorizontalGroup(
            painelObservacaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelObservacaoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(painelObservacaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(scrollObservacoes, javax.swing.GroupLayout.DEFAULT_SIZE, 499, Short.MAX_VALUE)
                    .addComponent(labelObservacoes))
                .addContainerGap())
        );
        painelObservacaoLayout.setVerticalGroup(
            painelObservacaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelObservacaoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelObservacoes)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(scrollObservacoes, javax.swing.GroupLayout.DEFAULT_SIZE, 178, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Observação", painelObservacao);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTabbedPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(botaoSalvar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(botaoLocalizar, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(botaoAlterar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(botaoExcluir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(botaoCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(botaoSalvar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(botaoLocalizar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(botaoAlterar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(botaoExcluir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(botaoCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.getAccessibleContext().setAccessibleName("");

        pack();
    }// </editor-fold>//GEN-END:initComponents

private void botaoAdicionarTelefoneActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoAdicionarTelefoneActionPerformed
    this.adicionaLinhaTabela(tabelaTelefone);
}//GEN-LAST:event_botaoAdicionarTelefoneActionPerformed

private void botaoRemoverTelefoneActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoRemoverTelefoneActionPerformed
    this.removerLinhaTabela(tabelaTelefone);
}//GEN-LAST:event_botaoRemoverTelefoneActionPerformed

private void botaoRemoverEmailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoRemoverEmailActionPerformed
    this.removerLinhaTabela(tabelaEmail);
}//GEN-LAST:event_botaoRemoverEmailActionPerformed

private void botaoAdicionarEmailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoAdicionarEmailActionPerformed
    this.adicionaLinhaTabela(tabelaEmail);
}//GEN-LAST:event_botaoAdicionarEmailActionPerformed

private void textoObservacoesKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textoObservacoesKeyTyped
    int t = textoObservacoes.getText().length();
    if(t == 999999999)
         evt.setKeyChar((char) KeyEvent.VK_CLEAR);
}//GEN-LAST:event_textoObservacoesKeyTyped

private void textoLocalKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textoLocalKeyTyped
    int t = textoLocal.getText().length();
    if(t == 100)
         evt.setKeyChar((char) KeyEvent.VK_CLEAR);
}//GEN-LAST:event_textoLocalKeyTyped

private void textoResponsavelKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textoResponsavelKeyTyped
    int t = textoResponsavel.getText().length();
    if(t == 100)
         evt.setKeyChar((char) KeyEvent.VK_CLEAR);
}//GEN-LAST:event_textoResponsavelKeyTyped

private void textoSiteKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textoSiteKeyTyped
    int t = textoSite.getText().length();
    if(t == 100)
         evt.setKeyChar((char) KeyEvent.VK_CLEAR);
}//GEN-LAST:event_textoSiteKeyTyped

private void textoEnderecoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textoEnderecoKeyTyped
    int t = textoEndereco.getText().length();
    if(t == 100)
         evt.setKeyChar((char) KeyEvent.VK_CLEAR);
}//GEN-LAST:event_textoEnderecoKeyTyped

private void textoBairroKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textoBairroKeyTyped
    int t = textoBairro.getText().length();
    if(t == 40)
         evt.setKeyChar((char) KeyEvent.VK_CLEAR);
}//GEN-LAST:event_textoBairroKeyTyped

private void textoComplementoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textoComplementoKeyTyped
    int t = textoComplemento.getText().length();
    if(t == 15)
         evt.setKeyChar((char) KeyEvent.VK_CLEAR);
}//GEN-LAST:event_textoComplementoKeyTyped

private void textoCidadeKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textoCidadeKeyTyped
    int t = textoCidade.getText().length();
    if(t == 50)
         evt.setKeyChar((char) KeyEvent.VK_CLEAR);
}//GEN-LAST:event_textoCidadeKeyTyped

private void textoOperadoraKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textoOperadoraKeyTyped
    int t = textoOperadora.getText().length();
    if(t == 15)
         evt.setKeyChar((char) KeyEvent.VK_CLEAR);
}//GEN-LAST:event_textoOperadoraKeyTyped

    private void textoCepFocusLost(java.awt.event.FocusEvent evt)//GEN-FIRST:event_textoCepFocusLost
    {//GEN-HEADEREND:event_textoCepFocusLost
		
    }//GEN-LAST:event_textoCepFocusLost

    private void labelCEPMouseClicked(java.awt.event.MouseEvent evt)//GEN-FIRST:event_labelCEPMouseClicked
    {//GEN-HEADEREND:event_labelCEPMouseClicked
		if(this.textoCEP.buscaCep())
		{
			this.textoEndereco.setText(this.textoCEP.getEndereco());
			this.textoBairro.setText(this.textoCEP.getBairro());
			this.textoCidade.setText(this.textoCEP.getCidade());
			this.comboEstado.setSelectedItem(this.textoCEP.getEstado());
		}
    }//GEN-LAST:event_labelCEPMouseClicked

    private void botaoSalvarActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_botaoSalvarActionPerformed
    {//GEN-HEADEREND:event_botaoSalvarActionPerformed
		if(this.validaCampos())
		{
			controlBD	= new ControladorBancodeDados();
			control		= new ControladorEventos();

			if(this.textoCodigo.getText().equals("** NOVO **"))
				controlBD.locaisEventos = new LocaisEventos();
			else
				controlBD.locaisEventos = control.getLocalByCodigo(Long.parseLong(this.textoCodigo.getText()));

			//Definindo os dados
			controlBD.locaisEventos.setNome(this.textoLocal.getText());
			controlBD.locaisEventos.setResponsavel(this.textoResponsavel.getText());
			controlBD.locaisEventos.setSite(this.textoSite.getText());
			controlBD.locaisEventos.setEndereco(this.textoEndereco.getText());
			controlBD.locaisEventos.setNumero(Integer.parseInt(this.textoNumero.getText()));
			controlBD.locaisEventos.setBairro(this.textoBairro.getText());
			controlBD.locaisEventos.setComplemento(this.textoComplemento.getText());
			controlBD.locaisEventos.setCidade(this.textoCidade.getText());
			controlBD.locaisEventos.setEstado(this.comboEstado.getSelectedIndex());
			controlBD.locaisEventos.setCep(this.textoCEP.getText());
			controlBD.locaisEventos.setObservacao(this.textoObservacoes.getText());

			//Definindo os telefones
			if(tabelaTelefone.isEditing())
				tabelaTelefone.getCellEditor().stopCellEditing();
			List<TelefonesLocaisEventos> telefones = new ArrayList<TelefonesLocaisEventos>();
			for(int i=0; i<tabelaTelefone.getRowCount(); i++)
			{
				TelefonesLocaisEventos telefone = new TelefonesLocaisEventos();
				String				   ramal	= new String("");

				//Telefone
				telefone.setTelefone(tabelaTelefone.getValueAt(i, 0).toString());
				//Tipo
				if((tabelaTelefone.getValueAt(i, 1) == null) || (tabelaTelefone.getValueAt(i, 1).toString().isEmpty()))
					telefone.setTipo(-1);
				else
				{
					if(tabelaTelefone.getValueAt(i, 1).toString().equals("Comercial"))
						telefone.setTipo(0);
					else if(tabelaTelefone.getValueAt(i, 1).toString().equals("Celular"))
						telefone.setTipo(1);
					else if(tabelaTelefone.getValueAt(i, 1).toString().equals("Fax"))
						telefone.setTipo(2);
					else if(tabelaTelefone.getValueAt(i, 1).toString().equals("Recado Residencial"))
						telefone.setTipo(3);
					else if(tabelaTelefone.getValueAt(i, 1).toString().equals("Outro"))
						telefone.setTipo(4);
				}
				//Operadora
				try
				{
					telefone.setOperadora(tabelaTelefone.getValueAt(i, 2).toString().toUpperCase());
				}
				catch (Exception e)
				{
					telefone.setOperadora("");
				}
				//Ramal
				try
				{
					telefone.setRamal(Integer.parseInt(tabelaTelefone.getValueAt(i, 3).toString()));
				}
				catch(Exception e)
				{
					telefone.setRamal(-1);
				}
				

				telefones.add(telefone);
			}
			controlBD.locaisEventos.setTelefones(telefones);

			//Definindo Emails
			if(tabelaEmail.isEditing())
				tabelaEmail.getCellEditor().stopCellEditing();
			List<EmailsLocaisEventos> emails = new ArrayList<EmailsLocaisEventos>();
			for(int i=0; i<tabelaEmail.getRowCount(); i++)
			{
				EmailsLocaisEventos email = new EmailsLocaisEventos();
				email.setEmail(tabelaEmail.getValueAt(i, 0).toString());

				emails.add(email);
			}
			controlBD.locaisEventos.setEmails(emails);
			
			//Atualizando banco
			if(this.textoCodigo.getText().equals("** NOVO **"))
				controlBD.cadastrar(controlBD.locaisEventos, (JFrame)this);
			else
				controlBD.atualizar(controlBD.locaisEventos, (JFrame)this);

			this.limpaTela();

			controlBD	= null;
			control		= null;
		}
    }//GEN-LAST:event_botaoSalvarActionPerformed

    private void botaoAlterarActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_botaoAlterarActionPerformed
    {//GEN-HEADEREND:event_botaoAlterarActionPerformed
		this.habilitaCampos(true);
		botaoSalvar.setEnabled(true);
        botaoAlterar.setEnabled(false);
    }//GEN-LAST:event_botaoAlterarActionPerformed

    private void botaoExcluirActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_botaoExcluirActionPerformed
    {//GEN-HEADEREND:event_botaoExcluirActionPerformed
		controlBD	= new ControladorBancodeDados();
		control		= new ControladorEventos();
		
		Long   codigo		= Long.parseLong(this.textoCodigo.getText());
		
		controlBD.locaisEventos = new LocaisEventos();
		controlBD.locaisEventos = control.getLocalByCodigo(Long.parseLong(this.textoCodigo.getText()));
		
		//Apagando Local de evento
		controlBD.apagar(codigo, controlBD.locaisEventos, (JFrame)this);
		
		controlBD	= null;
		control		= null;
		this.limpaTela();
    }//GEN-LAST:event_botaoExcluirActionPerformed

    private void botaoLocalizarActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_botaoLocalizarActionPerformed
    {//GEN-HEADEREND:event_botaoLocalizarActionPerformed
		this.janeProcuraLocaisEvento = new ProcuraLocaisEvento(this, true);
		this.janeProcuraLocaisEvento.setVisible(true);
    }//GEN-LAST:event_botaoLocalizarActionPerformed

    private void botaoCancelarActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_botaoCancelarActionPerformed
    {//GEN-HEADEREND:event_botaoCancelarActionPerformed
		this.dispose();
    }//GEN-LAST:event_botaoCancelarActionPerformed

	/*
	 * Método getProdutoByCodigo
	 * Obtem o produto de acordo com o codigo
	 */
	public void getProdutoByCodigo()
	{
		this.control    = new ControladorEventos();
        this.local		= control.getLocalByCodigo(this.codigo);
        
        //Insere os dados na janela
		this.textoCodigo.setText(this.codigo.toString());
		this.textoLocal.setText(this.local.getNome());
		this.textoResponsavel.setText(this.local.getResponsavel());
		this.textoSite.setText(this.local.getSite());
		this.textoEndereco.setText(this.local.getEndereco());
		this.textoNumero.setText(String.valueOf(this.local.getNumero()));
		this.textoBairro.setText(this.local.getBairro());
		this.textoComplemento.setText(this.local.getComplemento());
		this.textoCidade.setText(this.local.getCidade());
		this.comboEstado.setSelectedIndex(this.local.getEstado());
		this.textoCEP.setText(this.local.getCep());
		this.textoObservacoes.setText(this.local.getObservacao());
		
		//Telefones
		this.telefones = this.local.getTelefones();
		for(TelefonesLocaisEventos telefone : telefones)
		{
			String tipo			= new String();
			String ramal		= new String();
			
			if(telefone.getTipo() == -1)
				tipo = "";
			if(telefone.getTipo() == 0)
				tipo = "Comercial";
			else if(telefone.getTipo() == 1)
				tipo = "Celular";
			else if(telefone.getTipo() == 2)
				tipo = "Fax";
			else if(telefone.getTipo() == 3)
				tipo = "Recado Residencial";
			else if(telefone.getTipo() == 4)
				tipo = "Outro";
			
			if(telefone.getRamal() == -1)
				ramal = "";
			else
				ramal = String.valueOf(telefone.getRamal());
			
			
			DefaultTableModel modelo = (DefaultTableModel) tabelaTelefone.getModel();
			modelo.addRow(new String[]	{
											telefone.getTelefone(),
											tipo,
											telefone.getOperadora(),
											ramal
										});
		}
		
		//E-mails
		this.emails = this.local.getEmails();
		for(EmailsLocaisEventos email : emails)
		{
			DefaultTableModel modelo = (DefaultTableModel) tabelaEmail.getModel();
			modelo.addRow(new String[]	{ email.getEmail() });
		}
		
		//Desabilita/Habilita Botões
		botaoSalvar.setEnabled(false);
		botaoAlterar.setEnabled(true);
		botaoExcluir.setEnabled(true);

		//Desabilita Campos
		this.habilitaCampos(false);
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
            java.util.logging.Logger.getLogger(CadastroLocaisEventos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CadastroLocaisEventos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CadastroLocaisEventos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CadastroLocaisEventos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /*
         * Create and display the form
         */
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new CadastroLocaisEventos().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane ScrollEmail;
    private javax.swing.JScrollPane ScrollTelefone;
    private javax.swing.JButton botaoAdicionarEmail;
    private javax.swing.JButton botaoAdicionarTelefone;
    private javax.swing.JButton botaoAlterar;
    private javax.swing.JButton botaoCancelar;
    private javax.swing.JButton botaoExcluir;
    private javax.swing.JButton botaoLocalizar;
    private javax.swing.JButton botaoRemoverEmail;
    private javax.swing.JButton botaoRemoverTelefone;
    private javax.swing.JButton botaoSalvar;
    private javax.swing.JComboBox comboEstado;
    private javax.swing.JComboBox comboTipoTelefone;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel labelBairro;
    private javax.swing.JLabel labelCEP;
    private javax.swing.JLabel labelCidade;
    private javax.swing.JLabel labelCodigo;
    private javax.swing.JLabel labelComplemento;
    private javax.swing.JLabel labelDescricao;
    private javax.swing.JLabel labelEmail;
    private javax.swing.JLabel labelEndereco;
    private javax.swing.JLabel labelEstado;
    private javax.swing.JLabel labelNumero;
    private javax.swing.JLabel labelObservacoes;
    private javax.swing.JLabel labelResponsavel;
    private javax.swing.JLabel labelSite;
    private javax.swing.JLabel labelTelefone;
    private javax.swing.JPanel painelContato;
    private javax.swing.JPanel painelEquipamento;
    private javax.swing.JPanel painelObservacao;
    private javax.swing.JScrollPane scrollObservacoes;
    private javax.swing.JTable tabelaEmail;
    private javax.swing.JTable tabelaTelefone;
    private javax.swing.JTextField textoBairro;
    private formattedFields.JTextCep textoCEP;
    private javax.swing.JTextField textoCidade;
    private javax.swing.JTextField textoCodigo;
    private javax.swing.JTextField textoComplemento;
    private formattedFields.JTextEmail textoEmail;
    private javax.swing.JTextField textoEndereco;
    private javax.swing.JTextField textoLocal;
    private javax.swing.JTextField textoNumero;
    private javax.swing.JTextArea textoObservacoes;
    private javax.swing.JTextField textoOperadora;
    private javax.swing.JTextField textoResponsavel;
    private javax.swing.JTextField textoSite;
    private javax.swing.JFormattedTextField textoTelefone;
    // End of variables declaration//GEN-END:variables
	private ControladorBancodeDados				controlBD;
	private ControladorEventos					control;
	public	Long								codigo;
	private LocaisEventos						local;
	private Collection<TelefonesLocaisEventos>	telefones;
	private Collection<EmailsLocaisEventos>		emails;
	
	private ErroCampoEmBranco					janelaErroCampoEmBranco;
	private ProcuraLocaisEvento					janeProcuraLocaisEvento;
}
