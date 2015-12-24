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
import estudiofotografico.control.ControladorFornecedores;
import estudiofotografico.control.ControladorProdutos;
import estudiofotografico.model.Fornecedores;
import estudiofotografico.model.Produtos;
import estudiofotografico.view.erros.ErroCampoEmBranco;
import java.awt.Component;
import java.awt.Container;
import java.awt.Toolkit;
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
public class CadastroProdutos extends javax.swing.JFrame implements KeyListener, ContainerListener{

    /**
     * Creates new form cadastroMarcas
     */
    public CadastroProdutos() {
        initComponents();
        addKeyAndContainerListenerRecursively(this);
        this.setLocationRelativeTo(null);
        this.getCategoriaProdutos();
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
        }

	
	/*
	 * Obtem a categoria dos Produtos
	 */
	private void getCategoriaProdutos()
	{
		control						= new ControladorProdutos();
		List<String> categoriasString	= control.getCategorias();
		
		for(String categoria: categoriasString)
			comboCategoria.addItem(categoria);
		
		comboCategoria.setSelectedIndex(-1);
		
		control = null;
	}
	
	/*
	 * Método validaCampos
	 * Valida os campos para ver se não tem nenhum em branco
	 */
	private Boolean validaCampos()
	{
		Boolean valida = true;
		
		if(this.textoNome.getText().isEmpty())
			valida = false;
		if(this.comboCategoria.getSelectedIndex() == -1)
			valida = false;
		if(this.textoValor.getText().isEmpty())
			valida = false;
		
		if(valida == false)
		{
			janelaErroCampoEmBranco = new ErroCampoEmBranco(this, true);
			janelaErroCampoEmBranco.setVisible(true);
		}
		
		return valida;
	}
	
	/*
	 * Método limpaTela()
	 */
	public void limpaTela()
	{
		this.textoCodigo.setText("** NOVO **");
		this.textoCodigo.setEnabled(true);
		this.textoNome.setText("");
		this.comboCategoria.setSelectedIndex(-1);
		this.textoValor.setText("");
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
		this.comboCategoria.setEnabled(habilita);
		this.textoValor.setEnabled(habilita);
		this.textoObs.setEnabled(habilita);
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
    }
	
	/*
	 * Método getProdutoByCodigo
	 * Obtem o produto de acordo com o codigo
	 */
	public void getProdutoByCodigo()
	{
		this.control    = new ControladorProdutos();
        this.produto    = control.getProdutosByCodigo(this.codigo);
        
        //Insere os dados na janela
		this.textoCodigo.setText(this.codigo.toString());
        this.textoNome.setText(produto.getProduto());
		this.comboCategoria.setSelectedItem(produto.getCategoria().getCategoria());
		this.textoValor.setText(produto.getValor());
		fornecedor = produto.getFornecedor();
		if(fornecedor != null)
			textoFornecedor.setText(fornecedor.getNome());
		else
			textoFornecedor.setText("");
		this.textoObs.setText(produto.getObs());
        
        //Desabilita/Habilita Botões
        botaoSalvar.setEnabled(false);
        botaoAlterar.setEnabled(true);
        botaoExcluir.setEnabled(true);
        
        //Desabilita Campos
        this.habilitaCampos(false);
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
        labelNome = new javax.swing.JLabel();
        textoNome = new javax.swing.JTextField();
        labelCodigo = new javax.swing.JLabel();
        textoCodigo = new javax.swing.JTextField();
        labelCategoria = new javax.swing.JLabel();
        comboCategoria = new javax.swing.JComboBox();
        labelValor = new javax.swing.JLabel();
        labelObs = new javax.swing.JLabel();
        painelObs = new javax.swing.JScrollPane();
        textoObs = new javax.swing.JTextArea();
        textoValor = new formattedFields.jTextMoeda();
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
        setTitle("Cadastro de Produtos");
        setBounds(new java.awt.Rectangle(250, 230, 0, 0));
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/estudiofotografico/view/img/camera-photo-5.png")));

        painelEquipamento.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(95, 133, 169), null), "Produtos", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.TOP));

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

        labelCategoria.setForeground(java.awt.Color.red);
        labelCategoria.setText("Categoria");

        labelValor.setForeground(java.awt.Color.red);
        labelValor.setText("Valor");

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
                    .addComponent(painelObs)
                    .addComponent(labelObs)
                    .addGroup(painelEquipamentoLayout.createSequentialGroup()
                        .addGroup(painelEquipamentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labelCategoria)
                            .addComponent(labelNome)
                            .addComponent(labelValor)
                            .addComponent(jLabel1))
                        .addGap(18, 18, 18)
                        .addGroup(painelEquipamentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(textoValor, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(textoFornecedor, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(textoNome)
                            .addComponent(comboCategoria, javax.swing.GroupLayout.Alignment.TRAILING, 0, 278, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(painelEquipamentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labelCodigo)
                            .addGroup(painelEquipamentoLayout.createSequentialGroup()
                                .addGap(2, 2, 2)
                                .addComponent(botaoPesquisarFornecedor)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(botaoRemoverFornecedor))
                            .addComponent(textoCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(22, 22, 22)))
                .addContainerGap())
        );
        painelEquipamentoLayout.setVerticalGroup(
            painelEquipamentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelEquipamentoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(painelEquipamentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(painelEquipamentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(labelNome)
                        .addComponent(textoNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(labelCodigo, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(painelEquipamentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelCategoria)
                    .addComponent(comboCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(textoCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(painelEquipamentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(labelValor)
                    .addComponent(textoValor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(painelEquipamentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(painelEquipamentoLayout.createSequentialGroup()
                        .addGroup(painelEquipamentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(painelEquipamentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel1)
                                .addComponent(textoFornecedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(botaoPesquisarFornecedor))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(labelObs))
                    .addComponent(botaoRemoverFornecedor))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(painelObs, javax.swing.GroupLayout.DEFAULT_SIZE, 103, Short.MAX_VALUE)
                .addContainerGap())
        );

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
                    .addComponent(botaoLocalizar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(botaoAlterar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(botaoExcluir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(botaoCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

private void textoNomeKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textoNomeKeyTyped
    int t = textoNome.getText().length();
    if(t == 70)
         evt.setKeyChar((char) KeyEvent.VK_CLEAR);
}//GEN-LAST:event_textoNomeKeyTyped

private void textoObsKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textoObsKeyTyped
    int t = textoObs.getText().length();
    if(t == 999999999)
         evt.setKeyChar((char) KeyEvent.VK_CLEAR);
}//GEN-LAST:event_textoObsKeyTyped

    private void botaoCancelarActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_botaoCancelarActionPerformed
    {//GEN-HEADEREND:event_botaoCancelarActionPerformed
		this.dispose();
    }//GEN-LAST:event_botaoCancelarActionPerformed

    private void botaoSalvarActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_botaoSalvarActionPerformed
    {//GEN-HEADEREND:event_botaoSalvarActionPerformed
		if(this.validaCampos())
		{
			controlBD	= new ControladorBancodeDados();
			control		= new ControladorProdutos();
			
			//Cadastro
			if(this.textoCodigo.getText().equals("** NOVO **"))
				controlBD.produtos = new Produtos();
			//Alteração
			else
				controlBD.produtos = control.getProdutosByCodigo(Long.parseLong(this.textoCodigo.getText()));
			
			
			//Definindo os dados
			controlBD.produtos.setProduto(this.textoNome.getText());
			controlBD.produtos.setCategoria(control.getCategoriaByNome(this.comboCategoria.getSelectedItem().toString()));
			controlBD.produtos.setValor(this.textoValor.getValue());
			controlBD.produtos.setFornecedor(this.fornecedor);
			controlBD.produtos.setObs(this.textoObs.getText());
			
			
			//Atualizando banco
			//Cadastro
			if(this.textoCodigo.getText().equals("** NOVO **"))
			{
				if(controlBD.cadastrar(controlBD.produtos, (JFrame)this))
					this.limpaTela();
			}
			else
				if(controlBD.atualizar(controlBD.produtos, (JFrame)this))
                    this.limpaTela();
			
			controlBD = null;
		}
    }//GEN-LAST:event_botaoSalvarActionPerformed

    private void botaoLocalizarActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_botaoLocalizarActionPerformed
    {//GEN-HEADEREND:event_botaoLocalizarActionPerformed
		janelaProcuraProduto = new ProcuraProduto(this, true);
		janelaProcuraProduto.setVisible(true);
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
		
		controlBD.produtos = new Produtos();
		controlBD.apagar(codigo, controlBD.produtos, (JFrame)this);
		
		controlBD	= null;
		this.limpaTela();
    }//GEN-LAST:event_botaoExcluirActionPerformed

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
            java.util.logging.Logger.getLogger(CadastroProdutos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CadastroProdutos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CadastroProdutos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CadastroProdutos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /*
         * Create and display the form
         */
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new CadastroProdutos().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botaoAlterar;
    private javax.swing.JButton botaoCancelar;
    private javax.swing.JButton botaoExcluir;
    private javax.swing.JButton botaoLocalizar;
    private javax.swing.JButton botaoPesquisarFornecedor;
    private javax.swing.JButton botaoRemoverFornecedor;
    private javax.swing.JButton botaoSalvar;
    private javax.swing.JComboBox comboCategoria;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel labelCategoria;
    private javax.swing.JLabel labelCodigo;
    private javax.swing.JLabel labelNome;
    private javax.swing.JLabel labelObs;
    private javax.swing.JLabel labelValor;
    private javax.swing.JPanel painelEquipamento;
    private javax.swing.JScrollPane painelObs;
    private javax.swing.JTextField textoCodigo;
    private javax.swing.JTextField textoFornecedor;
    private javax.swing.JTextField textoNome;
    private javax.swing.JTextArea textoObs;
    private formattedFields.jTextMoeda textoValor;
    // End of variables declaration//GEN-END:variables
	
	private ControladorProdutos			control;
	private ControladorBancodeDados		controlBD;
	private ControladorFornecedores		controladorFornecedor;
	
	public	Long						codigo;
	private Produtos					produto;
	
	private ErroCampoEmBranco			janelaErroCampoEmBranco;
	private ProcuraProduto				janelaProcuraProduto;
	private ProcuraFornecedores			janelaPesquisaFornecedor;
	private Fornecedores				fornecedor;
}
