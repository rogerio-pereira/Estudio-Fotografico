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
import estudiofotografico.control.ControladorEquipamentos;
import estudiofotografico.control.ControladorFornecedores;
import estudiofotografico.model.Equipamentos;
import estudiofotografico.model.Fornecedores;
import estudiofotografico.view.erros.ErroCampoEmBranco;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.event.ContainerEvent;
import java.awt.event.ContainerListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.List;
import javax.swing.JFrame;

/**
 *
 * @author farofa
 */
public class CadastroEquipamentos extends javax.swing.JFrame implements KeyListener, ContainerListener{

    /**
     * Creates new form cadastroMarcas
     */
    public CadastroEquipamentos() {
        initComponents();
        addKeyAndContainerListenerRecursively(this);
        this.setLocationRelativeTo(null);
		this.getMarcaEquipamento();
		this.getTipoEquipamento();
		this.textoCodigo.setText("** NOVO **");
		this.textoMotivo.setEnabled(false);
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
        }

    /*
	 * Método getTipoEquipamento
	 * Obtem todos os tipos equipamentos e insere nos combobox
	 */
	private void getTipoEquipamento()
	{
		control = new estudiofotografico.control.ControladorEquipamentos();
		List<String> lista = control.getTipoEquipamento();
		
		for(String item: lista)
			comboTipo.addItem(item);
		
		comboTipo.setSelectedIndex(-1);
		control = null;
	}
	
	/*
	 * Método getMarcaEquipamento
	 * Obtem todos as marcas de equipamentos e insere nos combobox
	 */
	private void getMarcaEquipamento()
	{
		control = new estudiofotografico.control.ControladorEquipamentos();
		List<String> lista = control.getMarcaEquipamento();
		
		for(String item: lista)
		{
			comboMarca.addItem(item);
			comboMarca.setSelectedIndex(-1);
		}
		control = null;
	}    
	
	/*
	 * Método validaCampos()
	 * Verifica os campos que nao podem ser nulos
	 */
	private Boolean validaCampos()
	{
		Boolean valida = true;
		
		if(textoNome.getText().isEmpty())
		{
			textoNome.requestFocus();
			valida = false;
		}
		if(comboTipo.getSelectedIndex() == -1)
			valida = false;
		if(comboMarca.getSelectedIndex() == -1)
			valida = false;
		if(textoValorCompra.getText().isEmpty())
		{
			textoValorCompra.requestFocus();
			valida = false;
		}
		if(comboSituacao.getSelectedIndex() == -1)
			valida = false;
		if((comboSituacao.getSelectedIndex() == 5) && textoMotivo.getText().isEmpty())
		{
			textoMotivo.requestFocus();
			valida = false;
		}
		
		if(valida == false)
		{
			janelaErroCampoEmBranco = new ErroCampoEmBranco(this, true);
			janelaErroCampoEmBranco.setVisible(true);
		}
		
		return valida;
	}
    
    /*
     * Método getEquipamentoByCodigo
     * Obtem o equipamento selecionado na tela de Busca
     */
    public void getEquipamentoByCodigo()
    {
        this.control        = new estudiofotografico.control.ControladorEquipamentos();
        this.equipamento    = control.getEquipamentoByCodigo(this.codigo);
        
        //Insere os dados na janela
        textoCodigo.setText(equipamento.getCodigo().toString());
        textoNome.setText(equipamento.getEquipamento());
        comboMarca.setSelectedItem(equipamento.getMarca().getMarca());
        comboTipo.setSelectedItem(equipamento.getTipo().getTipo());
        textoLocalCompra.setText(equipamento.getLocalCompra());
        textoValorCompra.setText(equipamento.getPreco());
        textoNumeroSerie.setText(equipamento.getNumeroSerie());
        textoDataCompra.setCalendar(equipamento.getDataCompra());
        textoGarantia.setText(equipamento.getGarantia());
        comboSituacao.setSelectedIndex(equipamento.getSituacao());
        textoMotivo.setText(equipamento.getObsSituacao());
		fornecedor = equipamento.getFornecedor();
		if(fornecedor != null)
			textoFornecedor.setText(fornecedor.getNome());
		else
			textoFornecedor.setText("");
        textoObs.setText(equipamento.getObs());
        
        //Desabilita/Habilita Botões
        botaoSalvar.setEnabled(false);
        botaoAlterar.setEnabled(true);
        botaoExcluir.setEnabled(true);
        
        //Desabilita Campos
        this.habilitaCampos(false);
    }
    
    /*
     * Método limpaTela
     * Limpa a tela
     */
    public void limpaTela()
    {
        this.textoCodigo.setText("** NOVO **");
		this.textoCodigo.setEnabled(true);
        this.textoNome.setText("");
        this.comboMarca.setSelectedIndex(-1);
        this.comboTipo.setSelectedIndex(-1);
        this.textoLocalCompra.setText("");
        this.textoValorCompra.setText("");
        this.textoNumeroSerie.setText("");
        this.textoDataCompra.setCalendar(null);
        this.textoGarantia.setText("");
        this.comboSituacao.setSelectedIndex(-1);
        this.textoMotivo.setText("");
		this.textoFornecedor.setText("");
        this.textoObs.setText("");
    }
    
    /*
     * habilitaCampos
     */
    private void habilitaCampos(Boolean habilita)
    {
        this.textoCodigo.setEnabled(habilita);
        this.textoNome.setEnabled(habilita);
        this.comboMarca.setEnabled(habilita);
        this.comboTipo.setEnabled(habilita);
        this.textoLocalCompra.setEnabled(habilita);
        this.textoValorCompra.setEnabled(habilita);
        this.textoNumeroSerie.setEnabled(habilita);
        this.textoDataCompra.setEnabled(habilita);
        this.textoGarantia.setEnabled(habilita);
        this.comboSituacao.setEnabled(habilita);
		this.textoFornecedor.setEnabled(habilita);
		this.botaoPesquisarFornecedor.setEnabled(habilita);
		if(habilita)
		{
			if(this.fornecedor == null)
				this.botaoRemoverFornecedor.setEnabled(false);
			else
				this.botaoRemoverFornecedor.setEnabled(true);
		}
		else
			this.botaoRemoverFornecedor.setEnabled(false);
        this.textoObs.setEnabled(habilita);
    }
	
	/*
	 * Método getFornecedorByCod
	 * Obtem o fornecedor pelo codigo
	 */
	public void getFornecedorByCod(long codigo)
	{
		this.controladorFornecedor	= new ControladorFornecedores();
		this.fornecedor				= this.controladorFornecedor.getFornecedorByCodigo(codigo);
		this.textoFornecedor.setText(this.fornecedor.getNome());
		this.controladorFornecedor	= null;
		this.botaoRemoverFornecedor.setEnabled(true);
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

        painelEquipamento = new javax.swing.JPanel();
        labelEquipamento = new javax.swing.JLabel();
        textoNome = new javax.swing.JTextField();
        labelCodigo = new javax.swing.JLabel();
        textoCodigo = new javax.swing.JTextField();
        labelTipo = new javax.swing.JLabel();
        comboTipo = new javax.swing.JComboBox();
        labelMarca = new javax.swing.JLabel();
        comboMarca = new javax.swing.JComboBox();
        labelLocalCompra = new javax.swing.JLabel();
        textoLocalCompra = new javax.swing.JTextField();
        labelValor = new javax.swing.JLabel();
        labelNumeroSerie = new javax.swing.JLabel();
        textoNumeroSerie = new javax.swing.JTextField();
        labelDataCompra = new javax.swing.JLabel();
        labelDataCompra1 = new javax.swing.JLabel();
        labelSituacao = new javax.swing.JLabel();
        comboSituacao = new javax.swing.JComboBox();
        textoMotivo = new javax.swing.JTextField();
        labelObs = new javax.swing.JLabel();
        PainelObs = new javax.swing.JScrollPane();
        textoObs = new javax.swing.JTextArea();
        textoDataCompra = new com.toedter.calendar.JDateChooser();
        textoGarantia = new javax.swing.JTextField();
        textoValorCompra = new formattedFields.jTextMoeda();
        jLabel1 = new javax.swing.JLabel();
        textoFornecedor = new javax.swing.JTextField();
        botaoPesquisarFornecedor = new javax.swing.JButton();
        botaoRemoverFornecedor = new javax.swing.JButton();
        botaoSalvar = new javax.swing.JButton();
        botaoAlterar = new javax.swing.JButton();
        botaoLocalizar = new javax.swing.JButton();
        botaoExcluir = new javax.swing.JButton();
        botaoCancelar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Cadastro de Equipamento");
        setBounds(new java.awt.Rectangle(250, 230, 0, 0));

        painelEquipamento.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(95, 133, 169), null), "Equipamento", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.TOP));

        labelEquipamento.setForeground(java.awt.Color.red);
        labelEquipamento.setText("Nome");

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

        labelTipo.setForeground(java.awt.Color.red);
        labelTipo.setText("Tipo");

        labelMarca.setForeground(java.awt.Color.red);
        labelMarca.setText("Marca");

        labelLocalCompra.setText("Local Compra");

        textoLocalCompra.addKeyListener(new java.awt.event.KeyAdapter()
        {
            public void keyTyped(java.awt.event.KeyEvent evt)
            {
                textoLocalCompraKeyTyped(evt);
            }
        });

        labelValor.setForeground(java.awt.Color.red);
        labelValor.setText("Valor");

        labelNumeroSerie.setText("Numero de Serie");

        textoNumeroSerie.addKeyListener(new java.awt.event.KeyAdapter()
        {
            public void keyTyped(java.awt.event.KeyEvent evt)
            {
                textoNumeroSerieKeyTyped(evt);
            }
        });

        labelDataCompra.setText("Data Compra");

        labelDataCompra1.setText("Garantia");

        labelSituacao.setForeground(java.awt.Color.red);
        labelSituacao.setText("Situacao");

        comboSituacao.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Ativo", "Inativo por Roubo", "Inativo por Quebra", "Inativo por Venda", "Inativo por Doação", "Inativo por Outros Motivos" }));
        comboSituacao.setSelectedIndex(-1);
        comboSituacao.addItemListener(new java.awt.event.ItemListener()
        {
            public void itemStateChanged(java.awt.event.ItemEvent evt)
            {
                comboSituacaoItemStateChanged(evt);
            }
        });

        textoMotivo.setEditable(false);
        textoMotivo.setBackground(java.awt.Color.white);
        textoMotivo.setEnabled(false);
        textoMotivo.setFocusable(false);
        textoMotivo.addKeyListener(new java.awt.event.KeyAdapter()
        {
            public void keyTyped(java.awt.event.KeyEvent evt)
            {
                textoMotivoKeyTyped(evt);
            }
        });

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
        PainelObs.setViewportView(textoObs);

        textoGarantia.addKeyListener(new java.awt.event.KeyAdapter()
        {
            public void keyTyped(java.awt.event.KeyEvent evt)
            {
                textoGarantiaKeyTyped(evt);
            }
        });

        jLabel1.setText("Fornecedor");

        botaoPesquisarFornecedor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/estudiofotografico/view/img/procurarFornecedor.png"))); // NOI18N
        botaoPesquisarFornecedor.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                botaoPesquisarFornecedorActionPerformed(evt);
            }
        });

        botaoRemoverFornecedor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/estudiofotografico/view/img/FornecedorRemover.png"))); // NOI18N
        botaoRemoverFornecedor.setEnabled(false);
        botaoRemoverFornecedor.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                botaoRemoverFornecedorActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout painelEquipamentoLayout = new javax.swing.GroupLayout(painelEquipamento);
        painelEquipamento.setLayout(painelEquipamentoLayout);
        painelEquipamentoLayout.setHorizontalGroup(
            painelEquipamentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelEquipamentoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(painelEquipamentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(PainelObs)
                    .addGroup(painelEquipamentoLayout.createSequentialGroup()
                        .addGroup(painelEquipamentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labelLocalCompra)
                            .addComponent(labelEquipamento)
                            .addComponent(labelTipo)
                            .addComponent(labelValor)
                            .addComponent(labelSituacao)
                            .addComponent(labelDataCompra)
                            .addComponent(jLabel1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(painelEquipamentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(painelEquipamentoLayout.createSequentialGroup()
                                .addComponent(textoNome)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(labelCodigo)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(textoCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(painelEquipamentoLayout.createSequentialGroup()
                                .addComponent(comboTipo, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(labelMarca)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(comboMarca, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(textoLocalCompra)
                            .addGroup(painelEquipamentoLayout.createSequentialGroup()
                                .addGroup(painelEquipamentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(comboSituacao, 0, 149, Short.MAX_VALUE)
                                    .addComponent(textoDataCompra, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(textoValorCompra, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(18, 18, 18)
                                .addGroup(painelEquipamentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(painelEquipamentoLayout.createSequentialGroup()
                                        .addGroup(painelEquipamentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(labelNumeroSerie)
                                            .addComponent(labelDataCompra1))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(painelEquipamentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(textoNumeroSerie)
                                            .addComponent(textoGarantia)))
                                    .addComponent(textoMotivo)))
                            .addGroup(painelEquipamentoLayout.createSequentialGroup()
                                .addComponent(textoFornecedor)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(botaoPesquisarFornecedor)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(botaoRemoverFornecedor))))
                    .addGroup(painelEquipamentoLayout.createSequentialGroup()
                        .addComponent(labelObs)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        painelEquipamentoLayout.setVerticalGroup(
            painelEquipamentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelEquipamentoLayout.createSequentialGroup()
                .addGroup(painelEquipamentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelEquipamento)
                    .addComponent(textoNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelCodigo)
                    .addComponent(textoCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(painelEquipamentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelTipo)
                    .addComponent(comboTipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelMarca)
                    .addComponent(comboMarca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(painelEquipamentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelLocalCompra)
                    .addComponent(textoLocalCompra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(painelEquipamentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(painelEquipamentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(labelNumeroSerie)
                        .addComponent(textoNumeroSerie, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(labelValor))
                    .addComponent(textoValorCompra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(painelEquipamentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(painelEquipamentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(labelDataCompra)
                        .addComponent(labelDataCompra1)
                        .addComponent(textoGarantia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(textoDataCompra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(painelEquipamentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(comboSituacao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(textoMotivo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelSituacao))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(painelEquipamentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(painelEquipamentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel1)
                        .addComponent(textoFornecedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(botaoRemoverFornecedor)
                    .addComponent(botaoPesquisarFornecedor))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(labelObs)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(PainelObs, javax.swing.GroupLayout.DEFAULT_SIZE, 122, Short.MAX_VALUE)
                .addContainerGap())
        );

        botaoSalvar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/estudiofotografico/view/img/accept.png"))); // NOI18N
        botaoSalvar.setText("Salvar");
        botaoSalvar.setPreferredSize(new java.awt.Dimension(85, 30));
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
        botaoAlterar.setPreferredSize(new java.awt.Dimension(85, 30));
        botaoAlterar.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                botaoAlterarActionPerformed(evt);
            }
        });

        botaoLocalizar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/estudiofotografico/view/img/magnifier.png"))); // NOI18N
        botaoLocalizar.setText("Localizar");
        botaoLocalizar.setPreferredSize(new java.awt.Dimension(85, 30));
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
        botaoExcluir.setPreferredSize(new java.awt.Dimension(85, 30));
        botaoExcluir.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                botaoExcluirActionPerformed(evt);
            }
        });

        botaoCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/estudiofotografico/view/img/cancel.png"))); // NOI18N
        botaoCancelar.setText("Cancelar");
        botaoCancelar.setPreferredSize(new java.awt.Dimension(85, 30));
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
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(painelEquipamento, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(botaoSalvar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(botaoLocalizar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(botaoAlterar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(botaoExcluir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(botaoCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(painelEquipamento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(botaoSalvar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(botaoExcluir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(botaoCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(botaoLocalizar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(botaoAlterar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void comboSituacaoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_comboSituacaoItemStateChanged
        // TODO add your handling code here:
        if(comboSituacao.getSelectedIndex() == 5)
        {
			this.textoMotivo.setEditable(true);
			this.textoMotivo.setEnabled(true);
			this.textoMotivo.setFocusable(true);
			this.textoMotivo.setBackground(Color.RED);
			if(this.textoMotivo.isEnabled())
				this.textoMotivo.setForeground(Color.WHITE);
			else
				this.textoMotivo.setForeground(Color.BLACK);
			this.textoMotivo.requestFocus();
        }
        else
        {
			this.textoMotivo.setText("");
			this.textoMotivo.setEditable(false);
			this.textoMotivo.setEnabled(false);
			this.textoMotivo.setFocusable(false);
			this.textoMotivo.setBackground(Color.WHITE);
			if(this.textoMotivo.isEnabled())
				this.textoMotivo.setForeground(Color.WHITE);
			else
				this.textoMotivo.setForeground(Color.BLACK);
			textoMotivo.setForeground(Color.BLACK);
        }
    }//GEN-LAST:event_comboSituacaoItemStateChanged

private void textoNomeKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textoNomeKeyTyped
    int t = textoNome.getText().length();
    if(t == 70)
         evt.setKeyChar((char) KeyEvent.VK_CLEAR);
}//GEN-LAST:event_textoNomeKeyTyped

private void textoLocalCompraKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textoLocalCompraKeyTyped
    int t = textoLocalCompra.getText().length();
    if(t == 100)
         evt.setKeyChar((char) KeyEvent.VK_CLEAR);
}//GEN-LAST:event_textoLocalCompraKeyTyped

private void textoNumeroSerieKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textoNumeroSerieKeyTyped
    int t = textoNumeroSerie.getText().length();
    if(t == 30)
         evt.setKeyChar((char) KeyEvent.VK_CLEAR);
}//GEN-LAST:event_textoNumeroSerieKeyTyped

private void textoMotivoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textoMotivoKeyTyped
    int t = textoNumeroSerie.getText().length();
    if(t == 50)
         evt.setKeyChar((char) KeyEvent.VK_CLEAR);
}//GEN-LAST:event_textoMotivoKeyTyped

private void textoObsKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textoObsKeyTyped
    int t = textoNumeroSerie.getText().length();
    if(t == 999999999)
         evt.setKeyChar((char) KeyEvent.VK_CLEAR);
}//GEN-LAST:event_textoObsKeyTyped

    private void botaoSalvarActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_botaoSalvarActionPerformed
    {//GEN-HEADEREND:event_botaoSalvarActionPerformed
		if(this.validaCampos())
        {
			controlBD	= new ControladorBancodeDados();
			control		= new ControladorEquipamentos();
			
			//Cadastro
			if(this.textoCodigo.getText().equals("** NOVO **"))
				controlBD.equipamentos = new Equipamentos();
			else
				controlBD.equipamentos = control.getEquipamentoByCodigo(Long.parseLong(this.textoCodigo.getText()));
			
			controlBD.equipamentos.setEquipamento(this.textoNome.getText());
			controlBD.equipamentos.setMarca(control.getMarcasEquipamentos(this.comboMarca.getSelectedItem().toString()));
			controlBD.equipamentos.setTipo(control.getTipoEquipamentos(this.comboTipo.getSelectedItem().toString()));
			controlBD.equipamentos.setLocalCompra(textoLocalCompra.getText());
			controlBD.equipamentos.setPreco(textoValorCompra.getValue());
			controlBD.equipamentos.setNumeroSerie(textoNumeroSerie.getText());
			controlBD.equipamentos.setDataCompra(textoDataCompra.getCalendar());
			controlBD.equipamentos.setGarantia(textoGarantia.getText());
			controlBD.equipamentos.setSituacao(comboSituacao.getSelectedIndex());
			controlBD.equipamentos.setFornecedor(this.fornecedor);
			controlBD.equipamentos.setObsSituacao(textoMotivo.getText());
			controlBD.equipamentos.setObs(textoObs.getText());
			
			//Cadastro
			if(this.textoCodigo.getText().equals("** NOVO **"))
			{
				if(controlBD.cadastrar(controlBD.equipamentos, (JFrame)this))
					this.limpaTela();
			}
			else
				if(controlBD.atualizar(controlBD.equipamentos, (JFrame)this))
                    this.limpaTela();
			
			controlBD = null;
        }
    }//GEN-LAST:event_botaoSalvarActionPerformed

    private void botaoCancelarActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_botaoCancelarActionPerformed
    {//GEN-HEADEREND:event_botaoCancelarActionPerformed
		this.dispose();
    }//GEN-LAST:event_botaoCancelarActionPerformed

    private void botaoLocalizarActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_botaoLocalizarActionPerformed
    {//GEN-HEADEREND:event_botaoLocalizarActionPerformed
		janelaProcurarEquipamento = new ProcuraEquipamento(this, true);
		janelaProcurarEquipamento.setVisible(true);
    }//GEN-LAST:event_botaoLocalizarActionPerformed

    private void botaoAlterarActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_botaoAlterarActionPerformed
    {//GEN-HEADEREND:event_botaoAlterarActionPerformed
		this.habilitaCampos(true);
	    botaoSalvar.setEnabled(true);
        botaoAlterar.setEnabled(false);
    }//GEN-LAST:event_botaoAlterarActionPerformed

    private void botaoExcluirActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_botaoExcluirActionPerformed
    {//GEN-HEADEREND:event_botaoExcluirActionPerformed
        controlBD			= new ControladorBancodeDados();
		
		Long   codigo		= Long.parseLong(this.textoCodigo.getText());
		
		controlBD.equipamentos = new Equipamentos();
		controlBD.apagar(codigo, controlBD.equipamentos, (JFrame)this);
		
		controlBD	= null;
		this.limpaTela();
    }//GEN-LAST:event_botaoExcluirActionPerformed

    private void textoGarantiaKeyTyped(java.awt.event.KeyEvent evt)//GEN-FIRST:event_textoGarantiaKeyTyped
    {//GEN-HEADEREND:event_textoGarantiaKeyTyped
		int t = textoMotivo.getText().length();
		if(t == 10)
			 evt.setKeyChar((char) KeyEvent.VK_CLEAR);
    }//GEN-LAST:event_textoGarantiaKeyTyped

    private void botaoPesquisarFornecedorActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_botaoPesquisarFornecedorActionPerformed
    {//GEN-HEADEREND:event_botaoPesquisarFornecedorActionPerformed
		janelaPesquisaFornecedor = new ProcuraFornecedores(this, true);
		janelaPesquisaFornecedor.setVisible(true);
    }//GEN-LAST:event_botaoPesquisarFornecedorActionPerformed

    private void botaoRemoverFornecedorActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_botaoRemoverFornecedorActionPerformed
    {//GEN-HEADEREND:event_botaoRemoverFornecedorActionPerformed
		this.fornecedor = null;
		this.botaoRemoverFornecedor.setEnabled(false);
		this.textoFornecedor.setText("");
    }//GEN-LAST:event_botaoRemoverFornecedorActionPerformed

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
            java.util.logging.Logger.getLogger(CadastroEquipamentos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CadastroEquipamentos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CadastroEquipamentos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CadastroEquipamentos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /*
         * Create and display the form
         */
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new CadastroEquipamentos().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane PainelObs;
    private javax.swing.JButton botaoAlterar;
    private javax.swing.JButton botaoCancelar;
    private javax.swing.JButton botaoExcluir;
    private javax.swing.JButton botaoLocalizar;
    private javax.swing.JButton botaoPesquisarFornecedor;
    private javax.swing.JButton botaoRemoverFornecedor;
    private javax.swing.JButton botaoSalvar;
    private javax.swing.JComboBox comboMarca;
    private javax.swing.JComboBox comboSituacao;
    private javax.swing.JComboBox comboTipo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel labelCodigo;
    private javax.swing.JLabel labelDataCompra;
    private javax.swing.JLabel labelDataCompra1;
    private javax.swing.JLabel labelEquipamento;
    private javax.swing.JLabel labelLocalCompra;
    private javax.swing.JLabel labelMarca;
    private javax.swing.JLabel labelNumeroSerie;
    private javax.swing.JLabel labelObs;
    private javax.swing.JLabel labelSituacao;
    private javax.swing.JLabel labelTipo;
    private javax.swing.JLabel labelValor;
    private javax.swing.JPanel painelEquipamento;
    private javax.swing.JTextField textoCodigo;
    private com.toedter.calendar.JDateChooser textoDataCompra;
    private javax.swing.JTextField textoFornecedor;
    private javax.swing.JTextField textoGarantia;
    private javax.swing.JTextField textoLocalCompra;
    private javax.swing.JTextField textoMotivo;
    private javax.swing.JTextField textoNome;
    private javax.swing.JTextField textoNumeroSerie;
    private javax.swing.JTextArea textoObs;
    private formattedFields.jTextMoeda textoValorCompra;
    // End of variables declaration//GEN-END:variables

    private ControladorEquipamentos		control;
	private ControladorFornecedores		controladorFornecedor;
	private ControladorBancodeDados		controlBD;
	private ErroCampoEmBranco			janelaErroCampoEmBranco;
	private ProcuraEquipamento			janelaProcurarEquipamento;
	private ProcuraFornecedores			janelaPesquisaFornecedor;
    public  Equipamentos				equipamento;
    public  Long						codigo;
	private Fornecedores				fornecedor;
}