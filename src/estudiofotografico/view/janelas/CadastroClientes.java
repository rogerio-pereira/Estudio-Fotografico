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
import estudiofotografico.control.ControladorClientes;
import estudiofotografico.model.Clientes;
import estudiofotografico.model.EmailsClientes;
import estudiofotografico.model.TelefonesClientes;
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
public class CadastroClientes extends javax.swing.JFrame implements KeyListener, ContainerListener{

    /**
     * Creates new form cadastroMarcas
     */
    public CadastroClientes() {
        initComponents();
        addKeyAndContainerListenerRecursively(this);
        this.setLocationRelativeTo(null);
        this.defineModeloTabelas();
        radioNenhumaPessoa.setVisible(false);
        radioNenhumaPessoa.doClick();
        radioNenhumSexo.setVisible(false);
        radioNenhumSexo.doClick();
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
		 * Métod habilitaCampos
		 * Habilita/Desabilita Campos
		 */
		private void habilitaCampos(Boolean habilita)
		{
			this.textoCodigo.setEnabled(habilita);
			this.textoNome.setEnabled(habilita);
			this.habilitaPaineis(false, false);
			this.textoEndereco.setEnabled(habilita);
			this.textoNumero.setEnabled(habilita);
			this.textoBairro.setEnabled(habilita);
			this.textoComplemento.setEnabled(habilita);
			this.textoCidade.setEnabled(habilita);
			this.comboEstado.setEnabled(habilita);
			this.textoCEP.setEnabled(habilita);
			this.textoObs.setEnabled(habilita);
			this.checkCadastrado.setEnabled(habilita);
			
			this.tabelaTelefone.setEnabled(habilita);
			this.tabelaEmail.setEnabled(habilita);

			this.botaoAdicionarTelefone.setEnabled(habilita);
			this.botaoRemoverTelefone.setEnabled(habilita);
			this.botaoAdicionarEmail.setEnabled(habilita);
			this.botaoRemoverEmail.setEnabled(habilita);
		}
		
	/*
	 * Método Valida Campos
	 * Verifica se todos os campos obrigatorios estão marcados
	 */
	private Boolean validaCampos()
	{
		Boolean valida = true;
		
		if(this.textoNome.getText().isEmpty())
			valida = false;
		//Tipo Pessoa Selecionado
		if((this.radioPessoaFisica.isSelected()) || (this.radioPessoaJuridica.isSelected()))
		{
			//Pessoa Fisica
			if(this.radioPessoaFisica.isSelected())
			{
				if(this.textoRG.getText().isEmpty())
					valida = false;
				if(this.textoCPF.getText().equals("   .   .   -  "))
					valida = false;
			}
			//Pessoa Juridica
			if(this.radioPessoaJuridica.isSelected())
			{
				if(this.textoCNPJ.getText().equals("  .   .   /    -  "))
					valida = false;
				if(this.textoCPFResponsavel.equals("   .   .   -  "))
					valida = false;
			}
		}
		//Tipo Pessoa não selecionado
		else
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
	 * Limpta todos os campos e tabelas
	 */
	public void limpaTela()
	{
		this.textoCodigo.setText("** NOVO **");
		this.textoNome.setText("");
		
		this.radioNenhumaPessoa.doClick();
		this.textoRG.setText("");
		this.textoCPF.setText("");
		this.textoNascimento.setCalendar(null);
		this.radioNenhumSexo.doClick();
		
		this.textoCNPJ.setText("");
		this.textoCPFResponsavel.setText("");
		this.textoRGResponsavel.setText("");
		this.textoResponsavel.setText("");
		
		this.textoEndereco.setText("");
		this.textoNumero.setText("");
		this.textoBairro.setText("");
		this.textoComplemento.setText("");
		this.textoCidade.setText("");
		this.comboEstado.setSelectedIndex(-1);
		this.textoCEP.setText("");
		this.textoObs.setText("");
		this.checkCadastrado.setSelected(false);
		
		this.apagaTabela(tabelaEmail);
		this.apagaTabela(tabelaTelefone);
	}
	
	/*
	 * Método apagaTabela
	 * Apaga a tabela selecionada
	 */
	private void apagaTabela(JTable tabela)
	{
		DefaultTableModel modelo = (DefaultTableModel)tabela.getModel();
		while(tabela.getRowCount() > 0)
			modelo.removeRow(0);
	}
	
	/*
	 * Método getClienteByCodigo
	 * Obtem o cliente pelo Codigo
	 */
	public void getClienteByCodigo()
	{
		this.control    = new ControladorClientes();
        this.cliente	= control.getClientesByCodigo(this.codigo);
        
        //Insere os dados na janela
		this.textoCodigo.setText(this.codigo.toString());
		this.textoNome.setText(this.cliente.getNome());
		if(this.cliente.getPessoa() == true)
		{
			this.radioPessoaFisica.doClick();
			this.textoRG.setText(this.cliente.getRg());
			this.textoCPF.setText(this.cliente.getCpf());
			this.textoNascimento.setCalendar(this.cliente.getNascimento());
			if(this.cliente.getSexo() == true)
				this.radioMasculino.doClick();
			else if(this.cliente.getSexo() == false)
				this.radioFeminino.doClick();
		}
		else if(this.cliente.getPessoa() == false)
		{
			this.radioPessoaJuridica.doClick();
			this.textoCNPJ.setText(this.cliente.getCnpj());
			this.textoCPFResponsavel.setText(this.cliente.getCpfResponsavel());
			this.textoRGResponsavel.setText(this.cliente.getRgResponsavel());
			this.textoResponsavel.setText(this.cliente.getResponsavel());
		}
		this.textoEndereco.setText(this.cliente.getEndereco());
		this.textoNumero.setText(String.valueOf(this.cliente.getNumero()));
		this.textoBairro.setText(this.cliente.getBairro());
		this.textoComplemento.setText(this.cliente.getComplemento());
		this.textoCidade.setText(this.cliente.getCidade());
		this.comboEstado.setSelectedIndex(this.cliente.getEstado());
		this.textoCEP.setText(this.cliente.getCep());
		this.textoObs.setText(this.cliente.getObservacao());
		this.checkCadastrado.setSelected(this.cliente.getCadastrado());
		
		//Telefones
		this.telefones = this.cliente.getTelefones();
		for(TelefonesClientes telefone : telefones)
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
		this.emails = this.cliente.getEmails();
		for(EmailsClientes email : emails)
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

        grupoPessoa = new javax.swing.ButtonGroup();
        grupoSexo = new javax.swing.ButtonGroup();
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
        painelClientes = new javax.swing.JPanel();
        labelNome = new javax.swing.JLabel();
        textoNome = new javax.swing.JTextField();
        labelCodigo = new javax.swing.JLabel();
        textoCodigo = new javax.swing.JTextField();
        radioPessoaFisica = new javax.swing.JRadioButton();
        radioPessoaJuridica = new javax.swing.JRadioButton();
        radioNenhumaPessoa = new javax.swing.JRadioButton();
        painelPessoaFisica = new javax.swing.JPanel();
        labelCPF = new javax.swing.JLabel();
        labelNascimento = new javax.swing.JLabel();
        labelRG = new javax.swing.JLabel();
        textoRG = new javax.swing.JTextField();
        labelSexo = new javax.swing.JLabel();
        radioMasculino = new javax.swing.JRadioButton();
        radioFeminino = new javax.swing.JRadioButton();
        textoCPF = new formattedFields.JTextCPF();
        textoNascimento = new com.toedter.calendar.JDateChooser();
        painelPessoaJuridica = new javax.swing.JPanel();
        labelCNPJ = new javax.swing.JLabel();
        labelResponsavel = new javax.swing.JLabel();
        textoResponsavel = new javax.swing.JTextField();
        textoRGResponsavel = new javax.swing.JTextField();
        labelCPFResponsavel = new javax.swing.JLabel();
        labelRGResponsavel = new javax.swing.JLabel();
        textoCNPJ = new formattedFields.JTextCNPJ();
        textoCPFResponsavel = new formattedFields.JTextCPF();
        radioNenhumSexo = new javax.swing.JRadioButton();
        painelEndereco = new javax.swing.JPanel();
        labelEndereco = new javax.swing.JLabel();
        textoEndereco = new javax.swing.JTextField();
        labelNumero = new javax.swing.JLabel();
        textoNumero = new javax.swing.JTextField();
        textoComplemento = new javax.swing.JTextField();
        labelComplemento = new javax.swing.JLabel();
        textoBairro = new javax.swing.JTextField();
        labelBairro = new javax.swing.JLabel();
        labelCidade = new javax.swing.JLabel();
        textoCidade = new javax.swing.JTextField();
        labelEstado = new javax.swing.JLabel();
        comboEstado = new javax.swing.JComboBox();
        labelCEP = new javax.swing.JLabel();
        textoCEP = new formattedFields.JTextCep();
        painelTelefones = new javax.swing.JPanel();
        labelTelefone = new javax.swing.JLabel();
        ScrollTelefone = new javax.swing.JScrollPane();
        tabelaTelefone = new javax.swing.JTable();
        botaoAdicionarTelefone = new javax.swing.JButton();
        botaoRemoverTelefone = new javax.swing.JButton();
        PainelEmail = new javax.swing.JPanel();
        labelEmail = new javax.swing.JLabel();
        ScrollEmail = new javax.swing.JScrollPane();
        tabelaEmail = new javax.swing.JTable();
        botaoAdicionarEmail = new javax.swing.JButton();
        botaoRemoverEmail = new javax.swing.JButton();
        PainelObs = new javax.swing.JPanel();
        labelObs = new javax.swing.JLabel();
        painelObs = new javax.swing.JScrollPane();
        textoObs = new javax.swing.JTextArea();
        checkCadastrado = new javax.swing.JCheckBox();

        comboTipoTelefone.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Comercial", "Celular", "Fax", "Recado ", "Residencial", "Outro" }));

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
        setTitle("Cadastro Clientes");
        setBounds(new java.awt.Rectangle(250, 0, 0, 0));
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

        painelClientes.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(95, 133, 169), null), "Clientes", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.TOP));

        labelNome.setForeground(java.awt.Color.red);
        labelNome.setText("Nome");

        textoNome.addKeyListener(new java.awt.event.KeyAdapter()
        {
            public void keyTyped(java.awt.event.KeyEvent evt)
            {
                textoNomeKeyTyped(evt);
            }
        });

        labelCodigo.setText("Codigo");

        textoCodigo.setEditable(false);
        textoCodigo.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        textoCodigo.setForeground(java.awt.Color.red);
        textoCodigo.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        textoCodigo.setFocusable(false);

        grupoPessoa.add(radioPessoaFisica);
        radioPessoaFisica.setForeground(java.awt.Color.red);
        radioPessoaFisica.setText("Pessoa Fisica");
        radioPessoaFisica.setActionCommand("");
        radioPessoaFisica.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                radioPessoaFisicaActionPerformed(evt);
            }
        });

        grupoPessoa.add(radioPessoaJuridica);
        radioPessoaJuridica.setForeground(java.awt.Color.red);
        radioPessoaJuridica.setText("Pessoa Juridica");
        radioPessoaJuridica.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                radioPessoaJuridicaActionPerformed(evt);
            }
        });

        grupoPessoa.add(radioNenhumaPessoa);
        radioNenhumaPessoa.setSelected(true);
        radioNenhumaPessoa.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                radioNenhumaPessoaActionPerformed(evt);
            }
        });

        painelPessoaFisica.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(95, 133, 169), null), "Pessoa Física", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Andale Mono", 0, 12))); // NOI18N

        labelCPF.setForeground(java.awt.Color.red);
        labelCPF.setText("CPF");

        labelNascimento.setText("Nascimento");

        labelRG.setForeground(java.awt.Color.red);
        labelRG.setText("RG");

        textoRG.addKeyListener(new java.awt.event.KeyAdapter()
        {
            public void keyTyped(java.awt.event.KeyEvent evt)
            {
                textoRGKeyTyped(evt);
            }
        });

        labelSexo.setText("Sexo");

        grupoSexo.add(radioMasculino);
        radioMasculino.setText("Masculino");

        grupoSexo.add(radioFeminino);
        radioFeminino.setText("Feminino");

        javax.swing.GroupLayout painelPessoaFisicaLayout = new javax.swing.GroupLayout(painelPessoaFisica);
        painelPessoaFisica.setLayout(painelPessoaFisicaLayout);
        painelPessoaFisicaLayout.setHorizontalGroup(
            painelPessoaFisicaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelPessoaFisicaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(painelPessoaFisicaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(painelPessoaFisicaLayout.createSequentialGroup()
                        .addGroup(painelPessoaFisicaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labelCPF)
                            .addComponent(labelRG))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, painelPessoaFisicaLayout.createSequentialGroup()
                        .addGroup(painelPessoaFisicaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labelSexo)
                            .addComponent(labelNascimento))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(painelPessoaFisicaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(textoNascimento, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(textoCPF, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(textoRG)
                            .addGroup(painelPessoaFisicaLayout.createSequentialGroup()
                                .addGroup(painelPessoaFisicaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(radioFeminino)
                                    .addComponent(radioMasculino))
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addContainerGap())
        );
        painelPessoaFisicaLayout.setVerticalGroup(
            painelPessoaFisicaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelPessoaFisicaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(painelPessoaFisicaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelRG)
                    .addComponent(textoRG, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(painelPessoaFisicaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(labelCPF)
                    .addComponent(textoCPF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(painelPessoaFisicaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(labelNascimento)
                    .addComponent(textoNascimento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(painelPessoaFisicaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelSexo)
                    .addComponent(radioMasculino))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(radioFeminino)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        painelPessoaJuridica.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(95, 133, 169), null), "Pessoa Juridica", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Andale Mono", 0, 12))); // NOI18N

        labelCNPJ.setForeground(java.awt.Color.red);
        labelCNPJ.setText("CNPJ");

        labelResponsavel.setText("Responsavel");

        textoResponsavel.addKeyListener(new java.awt.event.KeyAdapter()
        {
            public void keyTyped(java.awt.event.KeyEvent evt)
            {
                textoResponsavelKeyTyped(evt);
            }
        });

        textoRGResponsavel.addKeyListener(new java.awt.event.KeyAdapter()
        {
            public void keyTyped(java.awt.event.KeyEvent evt)
            {
                textoRGResponsavelKeyTyped(evt);
            }
        });

        labelCPFResponsavel.setForeground(java.awt.Color.red);
        labelCPFResponsavel.setText("CPF Responsável");

        labelRGResponsavel.setText("RG Responsável");

        javax.swing.GroupLayout painelPessoaJuridicaLayout = new javax.swing.GroupLayout(painelPessoaJuridica);
        painelPessoaJuridica.setLayout(painelPessoaJuridicaLayout);
        painelPessoaJuridicaLayout.setHorizontalGroup(
            painelPessoaJuridicaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelPessoaJuridicaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(painelPessoaJuridicaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(textoResponsavel)
                    .addGroup(painelPessoaJuridicaLayout.createSequentialGroup()
                        .addComponent(labelCNPJ)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(textoCNPJ, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(painelPessoaJuridicaLayout.createSequentialGroup()
                        .addComponent(labelCPFResponsavel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 11, Short.MAX_VALUE)
                        .addComponent(textoCPFResponsavel, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(painelPessoaJuridicaLayout.createSequentialGroup()
                        .addComponent(labelResponsavel)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(painelPessoaJuridicaLayout.createSequentialGroup()
                        .addComponent(labelRGResponsavel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(textoRGResponsavel, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        painelPessoaJuridicaLayout.setVerticalGroup(
            painelPessoaJuridicaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelPessoaJuridicaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(painelPessoaJuridicaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(labelCNPJ)
                    .addComponent(textoCNPJ, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(painelPessoaJuridicaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(labelCPFResponsavel)
                    .addComponent(textoCPFResponsavel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(painelPessoaJuridicaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelRGResponsavel)
                    .addComponent(textoRGResponsavel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(labelResponsavel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(textoResponsavel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        grupoSexo.add(radioNenhumSexo);
        radioNenhumSexo.setSelected(true);

        painelEndereco.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(95, 133, 169), null), "Endereço", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.TOP));

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

        textoComplemento.addKeyListener(new java.awt.event.KeyAdapter()
        {
            public void keyTyped(java.awt.event.KeyEvent evt)
            {
                textoComplementoKeyTyped(evt);
            }
        });

        labelComplemento.setText("Complemento");

        textoBairro.addKeyListener(new java.awt.event.KeyAdapter()
        {
            public void keyTyped(java.awt.event.KeyEvent evt)
            {
                textoBairroKeyTyped(evt);
            }
        });

        labelBairro.setForeground(java.awt.Color.red);
        labelBairro.setText("Bairro");

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

        javax.swing.GroupLayout painelEnderecoLayout = new javax.swing.GroupLayout(painelEndereco);
        painelEndereco.setLayout(painelEnderecoLayout);
        painelEnderecoLayout.setHorizontalGroup(
            painelEnderecoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelEnderecoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(painelEnderecoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(painelEnderecoLayout.createSequentialGroup()
                        .addComponent(labelBairro)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(painelEnderecoLayout.createSequentialGroup()
                        .addGroup(painelEnderecoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labelEndereco)
                            .addComponent(labelCidade))
                        .addGap(9, 9, 9)
                        .addGroup(painelEnderecoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(textoEndereco)
                            .addGroup(painelEnderecoLayout.createSequentialGroup()
                                .addComponent(textoCidade)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(labelEstado)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(comboEstado, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(textoBairro, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(painelEnderecoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(painelEnderecoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(painelEnderecoLayout.createSequentialGroup()
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(labelComplemento))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, painelEnderecoLayout.createSequentialGroup()
                                    .addGap(46, 46, 46)
                                    .addComponent(labelCEP)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, painelEnderecoLayout.createSequentialGroup()
                                .addGap(28, 28, 28)
                                .addComponent(labelNumero)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(painelEnderecoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(textoNumero)
                            .addComponent(textoComplemento)
                            .addComponent(textoCEP, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        painelEnderecoLayout.setVerticalGroup(
            painelEnderecoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelEnderecoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(painelEnderecoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelEndereco)
                    .addComponent(textoEndereco, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelNumero)
                    .addComponent(textoNumero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(painelEnderecoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelBairro)
                    .addComponent(textoBairro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelComplemento)
                    .addComponent(textoComplemento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(painelEnderecoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(painelEnderecoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(labelCidade)
                        .addComponent(textoCidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(labelEstado)
                        .addComponent(comboEstado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(labelCEP))
                    .addComponent(textoCEP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout painelClientesLayout = new javax.swing.GroupLayout(painelClientes);
        painelClientes.setLayout(painelClientesLayout);
        painelClientesLayout.setHorizontalGroup(
            painelClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelClientesLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(painelClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(painelClientesLayout.createSequentialGroup()
                        .addGroup(painelClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(painelClientesLayout.createSequentialGroup()
                                .addComponent(painelPessoaFisica, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                            .addGroup(painelClientesLayout.createSequentialGroup()
                                .addGap(110, 110, 110)
                                .addComponent(radioPessoaFisica)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGroup(painelClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(painelClientesLayout.createSequentialGroup()
                                .addGap(96, 96, 96)
                                .addComponent(radioPessoaJuridica)
                                .addGap(47, 47, 47)
                                .addComponent(radioNenhumaPessoa)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(radioNenhumSexo))
                            .addComponent(painelPessoaJuridica, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(painelClientesLayout.createSequentialGroup()
                        .addComponent(labelNome)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(textoNome, javax.swing.GroupLayout.PREFERRED_SIZE, 393, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(labelCodigo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(textoCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(painelEndereco, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        painelClientesLayout.setVerticalGroup(
            painelClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelClientesLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(painelClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelNome)
                    .addComponent(textoNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelCodigo)
                    .addComponent(textoCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(painelClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(painelClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(radioPessoaJuridica)
                        .addComponent(radioPessoaFisica))
                    .addGroup(painelClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(radioNenhumaPessoa)
                        .addComponent(radioNenhumSexo)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(painelClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(painelPessoaJuridica, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(painelPessoaFisica, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(painelEndereco, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(12, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Cliente", painelClientes);

        painelTelefones.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(95, 133, 169), null), "Telefones", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.TOP));

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

        javax.swing.GroupLayout painelTelefonesLayout = new javax.swing.GroupLayout(painelTelefones);
        painelTelefones.setLayout(painelTelefonesLayout);
        painelTelefonesLayout.setHorizontalGroup(
            painelTelefonesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelTelefonesLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(painelTelefonesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(ScrollTelefone, javax.swing.GroupLayout.DEFAULT_SIZE, 576, Short.MAX_VALUE)
                    .addGroup(painelTelefonesLayout.createSequentialGroup()
                        .addComponent(labelTelefone)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(botaoAdicionarTelefone, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(botaoRemoverTelefone, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        painelTelefonesLayout.setVerticalGroup(
            painelTelefonesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelTelefonesLayout.createSequentialGroup()
                .addGroup(painelTelefonesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelTelefone)
                    .addComponent(botaoAdicionarTelefone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(botaoRemoverTelefone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ScrollTelefone, javax.swing.GroupLayout.DEFAULT_SIZE, 320, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Telefones", painelTelefones);

        PainelEmail.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(95, 133, 169), null), "E-mail", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.TOP));

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

        javax.swing.GroupLayout PainelEmailLayout = new javax.swing.GroupLayout(PainelEmail);
        PainelEmail.setLayout(PainelEmailLayout);
        PainelEmailLayout.setHorizontalGroup(
            PainelEmailLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PainelEmailLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PainelEmailLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(ScrollEmail, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 576, Short.MAX_VALUE)
                    .addGroup(PainelEmailLayout.createSequentialGroup()
                        .addComponent(labelEmail)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 318, Short.MAX_VALUE)
                        .addComponent(botaoAdicionarEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(botaoRemoverEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        PainelEmailLayout.setVerticalGroup(
            PainelEmailLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PainelEmailLayout.createSequentialGroup()
                .addGroup(PainelEmailLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelEmail)
                    .addComponent(botaoAdicionarEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(botaoRemoverEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ScrollEmail, javax.swing.GroupLayout.DEFAULT_SIZE, 320, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("E-mail", PainelEmail);

        PainelObs.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(95, 133, 169), null), "Observações"));

        labelObs.setText("Observações");

        textoObs.setColumns(20);
        textoObs.setLineWrap(true);
        textoObs.setRows(5);
        textoObs.setWrapStyleWord(true);
        textoObs.addKeyListener(new java.awt.event.KeyAdapter()
        {
            public void keyTyped(java.awt.event.KeyEvent evt)
            {
                textoObsKeyTyped(evt);
            }
        });
        painelObs.setViewportView(textoObs);

        checkCadastrado.setText("Cadastrado no Site");

        javax.swing.GroupLayout PainelObsLayout = new javax.swing.GroupLayout(PainelObs);
        PainelObs.setLayout(PainelObsLayout);
        PainelObsLayout.setHorizontalGroup(
            PainelObsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PainelObsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PainelObsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(painelObs, javax.swing.GroupLayout.DEFAULT_SIZE, 576, Short.MAX_VALUE)
                    .addGroup(PainelObsLayout.createSequentialGroup()
                        .addGroup(PainelObsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(checkCadastrado)
                            .addComponent(labelObs))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        PainelObsLayout.setVerticalGroup(
            PainelObsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PainelObsLayout.createSequentialGroup()
                .addComponent(checkCadastrado)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 14, Short.MAX_VALUE)
                .addComponent(labelObs)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(painelObs, javax.swing.GroupLayout.PREFERRED_SIZE, 289, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Observações", PainelObs);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
            .addGroup(layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(botaoSalvar, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(botaoLocalizar, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(botaoAlterar, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(botaoExcluir, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(botaoCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(botaoAlterar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(botaoSalvar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(botaoExcluir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(botaoLocalizar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(botaoCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        jTabbedPane1.getAccessibleContext().setAccessibleName("E-mail");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void radioNenhumaPessoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radioNenhumaPessoaActionPerformed
        //DESBILITA PAINEIS PESSOA FISICA E JURIDICA
        this.habilitaPaineis(false, false);
        this.limpaCamposPessoa(true, true);
    }//GEN-LAST:event_radioNenhumaPessoaActionPerformed

    private void radioPessoaFisicaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radioPessoaFisicaActionPerformed
        //HABILITA PAINEL PESSOA FISICA E DESABILITA PAINEL PESSOA JURIDICA
        this.habilitaPaineis(true, false);
        this.limpaCamposPessoa(false, true);
    }//GEN-LAST:event_radioPessoaFisicaActionPerformed

    private void radioPessoaJuridicaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radioPessoaJuridicaActionPerformed
        //HABILITA PAINEL PESSOA JURIDICA E DESABILITA PAINEL PESSOA FISICA
        this.habilitaPaineis(false, true);
        this.limpaCamposPessoa(true, false);
    }//GEN-LAST:event_radioPessoaJuridicaActionPerformed

private void botaoAdicionarTelefoneActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoAdicionarTelefoneActionPerformed
    this.adicionaLinhaTabela(tabelaTelefone);
}//GEN-LAST:event_botaoAdicionarTelefoneActionPerformed

private void botaoRemoverTelefoneActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoRemoverTelefoneActionPerformed
    this.removerLinhaTabela(tabelaTelefone);
}//GEN-LAST:event_botaoRemoverTelefoneActionPerformed

private void botaoAdicionarEmailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoAdicionarEmailActionPerformed
    this.adicionaLinhaTabela(tabelaEmail);
}//GEN-LAST:event_botaoAdicionarEmailActionPerformed

private void botaoRemoverEmailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoRemoverEmailActionPerformed
    this.removerLinhaTabela(tabelaEmail);
}//GEN-LAST:event_botaoRemoverEmailActionPerformed

private void textoOperadoraKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textoOperadoraKeyTyped
    int t = textoOperadora.getText().length();
    if(t == 15)
         evt.setKeyChar((char) KeyEvent.VK_CLEAR);
}//GEN-LAST:event_textoOperadoraKeyTyped

private void textoNomeKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textoNomeKeyTyped
    int t = textoNome.getText().length();
    if(t == 100)
         evt.setKeyChar((char) KeyEvent.VK_CLEAR);
}//GEN-LAST:event_textoNomeKeyTyped

private void textoRGKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textoRGKeyTyped
    int t = textoRG.getText().length();
    if(t == 13)
         evt.setKeyChar((char) KeyEvent.VK_CLEAR);
}//GEN-LAST:event_textoRGKeyTyped

private void textoResponsavelKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textoResponsavelKeyTyped
    int t = textoResponsavel.getText().length();
    if(t == 100)
         evt.setKeyChar((char) KeyEvent.VK_CLEAR);
}//GEN-LAST:event_textoResponsavelKeyTyped

private void textoRGResponsavelKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textoRGResponsavelKeyTyped
    int t = textoRGResponsavel.getText().length();
    if(t == 13)
         evt.setKeyChar((char) KeyEvent.VK_CLEAR);
}//GEN-LAST:event_textoRGResponsavelKeyTyped

private void textoObsKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textoObsKeyTyped
    int t = textoObs.getText().length();
    if(t == 999999999)
         evt.setKeyChar((char) KeyEvent.VK_CLEAR);
}//GEN-LAST:event_textoObsKeyTyped

    private void textoEnderecoKeyTyped(java.awt.event.KeyEvent evt)//GEN-FIRST:event_textoEnderecoKeyTyped
    {//GEN-HEADEREND:event_textoEnderecoKeyTyped
        int t = textoEndereco.getText().length();
        if(t == 100)
        evt.setKeyChar((char) KeyEvent.VK_CLEAR);
    }//GEN-LAST:event_textoEnderecoKeyTyped

    private void textoComplementoKeyTyped(java.awt.event.KeyEvent evt)//GEN-FIRST:event_textoComplementoKeyTyped
    {//GEN-HEADEREND:event_textoComplementoKeyTyped
        int t = textoComplemento.getText().length();
        if(t == 15)
        evt.setKeyChar((char) KeyEvent.VK_CLEAR);
    }//GEN-LAST:event_textoComplementoKeyTyped

    private void textoBairroKeyTyped(java.awt.event.KeyEvent evt)//GEN-FIRST:event_textoBairroKeyTyped
    {//GEN-HEADEREND:event_textoBairroKeyTyped
        int t = textoBairro.getText().length();
        if(t == 40)
        evt.setKeyChar((char) KeyEvent.VK_CLEAR);
    }//GEN-LAST:event_textoBairroKeyTyped

    private void textoCidadeKeyTyped(java.awt.event.KeyEvent evt)//GEN-FIRST:event_textoCidadeKeyTyped
    {//GEN-HEADEREND:event_textoCidadeKeyTyped
        int t = textoCidade.getText().length();
        if(t == 50)
        evt.setKeyChar((char) KeyEvent.VK_CLEAR);
    }//GEN-LAST:event_textoCidadeKeyTyped

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

    private void botaoCancelarActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_botaoCancelarActionPerformed
    {//GEN-HEADEREND:event_botaoCancelarActionPerformed
		this.dispose();
    }//GEN-LAST:event_botaoCancelarActionPerformed

    private void botaoAlterarActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_botaoAlterarActionPerformed
    {//GEN-HEADEREND:event_botaoAlterarActionPerformed
		this.habilitaCampos(true);
		botaoSalvar.setEnabled(true);
        botaoAlterar.setEnabled(false);
		
		if(radioPessoaFisica.isSelected())
			this.habilitaPaineis(true, false);
		if(radioPessoaJuridica.isSelected())
			this.habilitaPaineis(false, true);
    }//GEN-LAST:event_botaoAlterarActionPerformed

    private void botaoSalvarActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_botaoSalvarActionPerformed
    {//GEN-HEADEREND:event_botaoSalvarActionPerformed
        if(this.validaCampos())
		{
			controlBD	= new ControladorBancodeDados();
			control		= new ControladorClientes();

			if(this.textoCodigo.getText().equals("** NOVO **"))
				controlBD.clientes = new Clientes();
			else
				controlBD.clientes = control.getClientesByCodigo(Long.parseLong(this.textoCodigo.getText()));

			//Definindo os dados
			controlBD.clientes.setNome(this.textoNome.getText());
			if(this.radioPessoaFisica.isSelected())
				controlBD.clientes.setPessoa(true);
			else if(this.radioPessoaJuridica.isSelected())
				controlBD.clientes.setPessoa(false);
			controlBD.clientes.setRg(this.textoRG.getText());
			if(!this.textoCPF.getText().equals("   .   .   -  "))
				controlBD.clientes.setCpf(this.textoCPF.getText());
			else
				controlBD.clientes.setCpf(null);
			controlBD.clientes.setNascimento(this.textoNascimento.getCalendar());
			if(this.radioMasculino.isSelected())
				controlBD.clientes.setSexo(true);
			if(this.radioFeminino.isSelected())
				controlBD.clientes.setSexo(false);
			if(!this.textoCNPJ.getText().equals("  .   .   /    -  "))
				controlBD.clientes.setCnpj(this.textoCNPJ.getText());
			else
				controlBD.clientes.setCnpj(null);
			controlBD.clientes.setCpfResponsavel(this.textoCPFResponsavel.getText());
			controlBD.clientes.setRgResponsavel(this.textoRGResponsavel.getText());
			controlBD.clientes.setResponsavel(this.textoResponsavel.getText());
			controlBD.clientes.setEndereco(this.textoEndereco.getText());
			controlBD.clientes.setNumero(Integer.parseInt(this.textoNumero.getText()));
			controlBD.clientes.setBairro(this.textoBairro.getText());
			controlBD.clientes.setComplemento(this.textoComplemento.getText());
			controlBD.clientes.setCidade(this.textoCidade.getText());
			controlBD.clientes.setEstado(this.comboEstado.getSelectedIndex());
			controlBD.clientes.setCep(this.textoCEP.getText());
			controlBD.clientes.setObservacao(this.textoObs.getText());
			controlBD.clientes.setCadastrado(this.checkCadastrado.isSelected());

			//Definindo os telefones
			if(tabelaTelefone.isEditing())
				tabelaTelefone.getCellEditor().stopCellEditing();
			List<TelefonesClientes> telefones = new ArrayList<TelefonesClientes>();
			for(int i=0; i<tabelaTelefone.getRowCount(); i++)
			{
				TelefonesClientes telefone = new TelefonesClientes();
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
			controlBD.clientes.setTelefones(telefones);

			//Definindo Emails
			if(tabelaEmail.isEditing())
				tabelaEmail.getCellEditor().stopCellEditing();
			List<EmailsClientes> emails = new ArrayList<EmailsClientes>();
			for(int i=0; i<tabelaEmail.getRowCount(); i++)
			{
				EmailsClientes email = new EmailsClientes();
				email.setEmail(tabelaEmail.getValueAt(i, 0).toString());

				emails.add(email);
			}
			controlBD.clientes.setEmails(emails);
			
			//Atualizando banco
			if(this.textoCodigo.getText().equals("** NOVO **"))
				controlBD.cadastrar(controlBD.clientes, (JFrame)this);
			else
				controlBD.atualizar(controlBD.clientes, (JFrame)this);

			this.limpaTela();

			controlBD	= null;
			control		= null;
		}
    }//GEN-LAST:event_botaoSalvarActionPerformed

    private void botaoLocalizarActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_botaoLocalizarActionPerformed
    {//GEN-HEADEREND:event_botaoLocalizarActionPerformed
		this.janelaProcuraClientes = new ProcuraClientes(this, true);
		this.janelaProcuraClientes.setVisible(true);
    }//GEN-LAST:event_botaoLocalizarActionPerformed

    private void botaoExcluirActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_botaoExcluirActionPerformed
    {//GEN-HEADEREND:event_botaoExcluirActionPerformed
		this.controlBD		= new ControladorBancodeDados();
		this.control		= new ControladorClientes();
		
		Long		codigo	= Long.parseLong(this.textoCodigo.getText());
		
		controlBD.clientes	= new Clientes();
		controlBD.clientes	= control.getClientesByCodigo(Long.parseLong(this.textoCodigo.getText()));
		
		//Apagando Local de evento
		controlBD.apagar(codigo, controlBD.clientes, (JFrame)this);
		
		controlBD	= null;
		control		= null;
		this.limpaTela();
    }//GEN-LAST:event_botaoExcluirActionPerformed

    
    /*
     * Metodo habilitaPaineis
     * Habilita/desabilita painel pessoaFisica, Juridica
     */
    private void habilitaPaineis(Boolean pessoaFisica, Boolean pessoJuridica)
    {
        //PAINEL PESSOA FISICA
        painelPessoaFisica.setEnabled(pessoaFisica);
        labelRG.setEnabled(pessoaFisica);
        textoRG.setEnabled(pessoaFisica);
        labelCPF.setEnabled(pessoaFisica);
        textoCPF.setEnabled(pessoaFisica);
        labelNascimento.setEnabled(pessoaFisica);
        textoNascimento.setEnabled(pessoaFisica);
        labelSexo.setEnabled(pessoaFisica);
        radioFeminino.setEnabled(pessoaFisica);
        radioMasculino.setEnabled(pessoaFisica);

        //PAINEL PESSOA JURIDICA
        painelPessoaJuridica.setEnabled(pessoJuridica);
        labelCNPJ.setEnabled(pessoJuridica);
        textoCNPJ.setEnabled(pessoJuridica);
        labelResponsavel.setEnabled(pessoJuridica);
        textoResponsavel.setEnabled(pessoJuridica);
        labelRGResponsavel.setEnabled(pessoJuridica);
        textoRGResponsavel.setEnabled(pessoJuridica);
        labelCPFResponsavel.setEnabled(pessoJuridica);
        textoCPFResponsavel.setEnabled(pessoJuridica);
    }
    
    /*
     * Método limpaCampos
     * Limpa os campos dos paineis PessoaFisica e PessoaJuridica
     */
    private void limpaCamposPessoa(Boolean pessoaFisica,Boolean pessoaJuridica)
    {
        //PessoaFisica
        if(pessoaFisica)
        {
            textoRG.setText("");
            textoCPF.setText("");
            textoNascimento.setDate(null);
            radioNenhumSexo.doClick();
        }
        
        //Pessoa Juridica
        if(pessoaJuridica)
        {
            textoCNPJ.setText("");
            textoResponsavel.setText("");
            textoRGResponsavel.setText("");
            textoCPFResponsavel.setText("");
        }
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
            java.util.logging.Logger.getLogger(CadastroClientes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CadastroClientes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CadastroClientes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CadastroClientes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /*
         * Create and display the form
         */
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new CadastroClientes().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel PainelEmail;
    private javax.swing.JPanel PainelObs;
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
    private javax.swing.JCheckBox checkCadastrado;
    private javax.swing.JComboBox comboEstado;
    private javax.swing.JComboBox comboTipoTelefone;
    private javax.swing.ButtonGroup grupoPessoa;
    private javax.swing.ButtonGroup grupoSexo;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel labelBairro;
    private javax.swing.JLabel labelCEP;
    private javax.swing.JLabel labelCNPJ;
    private javax.swing.JLabel labelCPF;
    private javax.swing.JLabel labelCPFResponsavel;
    private javax.swing.JLabel labelCidade;
    private javax.swing.JLabel labelCodigo;
    private javax.swing.JLabel labelComplemento;
    private javax.swing.JLabel labelEmail;
    private javax.swing.JLabel labelEndereco;
    private javax.swing.JLabel labelEstado;
    private javax.swing.JLabel labelNascimento;
    private javax.swing.JLabel labelNome;
    private javax.swing.JLabel labelNumero;
    private javax.swing.JLabel labelObs;
    private javax.swing.JLabel labelRG;
    private javax.swing.JLabel labelRGResponsavel;
    private javax.swing.JLabel labelResponsavel;
    private javax.swing.JLabel labelSexo;
    private javax.swing.JLabel labelTelefone;
    private javax.swing.JPanel painelClientes;
    private javax.swing.JPanel painelEndereco;
    private javax.swing.JScrollPane painelObs;
    private javax.swing.JPanel painelPessoaFisica;
    private javax.swing.JPanel painelPessoaJuridica;
    private javax.swing.JPanel painelTelefones;
    private javax.swing.JRadioButton radioFeminino;
    private javax.swing.JRadioButton radioMasculino;
    private javax.swing.JRadioButton radioNenhumSexo;
    private javax.swing.JRadioButton radioNenhumaPessoa;
    private javax.swing.JRadioButton radioPessoaFisica;
    private javax.swing.JRadioButton radioPessoaJuridica;
    private javax.swing.JTable tabelaEmail;
    private javax.swing.JTable tabelaTelefone;
    private javax.swing.JTextField textoBairro;
    private formattedFields.JTextCep textoCEP;
    private formattedFields.JTextCNPJ textoCNPJ;
    private formattedFields.JTextCPF textoCPF;
    private formattedFields.JTextCPF textoCPFResponsavel;
    private javax.swing.JTextField textoCidade;
    private javax.swing.JTextField textoCodigo;
    private javax.swing.JTextField textoComplemento;
    private formattedFields.JTextEmail textoEmail;
    private javax.swing.JTextField textoEndereco;
    private com.toedter.calendar.JDateChooser textoNascimento;
    private javax.swing.JTextField textoNome;
    private javax.swing.JTextField textoNumero;
    private javax.swing.JTextArea textoObs;
    private javax.swing.JTextField textoOperadora;
    private javax.swing.JTextField textoRG;
    private javax.swing.JTextField textoRGResponsavel;
    private javax.swing.JTextField textoResponsavel;
    private javax.swing.JFormattedTextField textoTelefone;
    // End of variables declaration//GEN-END:variables
	private ControladorBancodeDados				controlBD;
	private ControladorClientes					control;
	public	Long								codigo;
	private Clientes							cliente;
	private Collection<TelefonesClientes>		telefones;
	private Collection<EmailsClientes>			emails;
	
	private ErroCampoEmBranco					janelaErroCampoEmBranco;
	private ProcuraClientes						janelaProcuraClientes;
}
