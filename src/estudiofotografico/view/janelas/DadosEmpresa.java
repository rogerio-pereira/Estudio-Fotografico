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
import estudiofotografico.control.ControladorEmpresa;
import estudiofotografico.model.Empresa;
import java.awt.Component;
import java.awt.Container;
import java.awt.event.ContainerEvent;
import java.awt.event.ContainerListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author farofa
 */
public class DadosEmpresa extends javax.swing.JFrame implements KeyListener, ContainerListener{

    /**
     * Creates new form cadastroMarcas
     */
    public DadosEmpresa() {
        initComponents();
        addKeyAndContainerListenerRecursively(this);
        this.setLocationRelativeTo(null);
		this.getEmpresa();
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
	 * Método getEmpresa
	 * verifica se tem empresa cadastrada
	 */
	private void getEmpresa()
	{
		this.control	= new ControladorEmpresa();
		this.empresa	= this.control.getEmpresa();
		
		//Não encontrou proprietario
		if(empresa == null)
		{
			botaoAlterar.setEnabled(false);
			botaoSalvar.setEnabled(true);
		}
		else
		{
			this.textoNome.setText(empresa.getNome());
			this.textoCNPJ.setText(empresa.getCnpj());
			this.textoTelefone.setText(empresa.getTelefone());
			this.textoFax.setText(empresa.getFax());
			this.textoEmail.setText(empresa.getEmail());
			this.textoSite.setText(empresa.getSite());
			this.textoEndereco.setText(empresa.getEndereco());
			this.textoNumero.setText(String.valueOf(empresa.getNumero()));
			this.textoBairro.setText(empresa.getBairro());
			this.textoComplemento.setText(empresa.getComplemento());
			this.textoCidade.setText(empresa.getCidade());
			this.comboEstado.setSelectedIndex(empresa.getEstado());
			this.textoCEP.setText(empresa.getCep());
			
			
			botaoAlterar.setEnabled(true);
			botaoSalvar.setEnabled(false);
		}
		
		control = null;
	}
	
	/*
	 * Método Salva
	 * Salva / Altera no banco
	 * @param 
	 *		opcao = 1	-> Salvar
	 *		opcao = 2	-> Alterar
	 */
	private void salvar(int opcao)
	{
		if(this.validaCampos())
		{
			controlBD	= new ControladorBancodeDados();
			control		= new ControladorEmpresa();

			if(opcao == 1)
				controlBD.empresa = new Empresa();
			else
				controlBD.empresa = this.empresa;
			
			//Definindo os dados
			controlBD.empresa.setNome(this.textoNome.getText());
			controlBD.empresa.setCnpj(this.textoCNPJ.getText());
			controlBD.empresa.setTelefone(this.textoTelefone.getText());
			controlBD.empresa.setFax(this.textoFax.getText());
			controlBD.empresa.setEmail(this.textoEmail.getText());
			controlBD.empresa.setSite(this.textoSite.getText());
			controlBD.empresa.setEndereco(this.textoEndereco.getText());
			controlBD.empresa.setNumero(Integer.parseInt(this.textoNumero.getText()));
			controlBD.empresa.setBairro(this.textoBairro.getText());
			controlBD.empresa.setComplemento(this.textoComplemento.getText());
			controlBD.empresa.setCidade(this.textoCidade.getText());
			controlBD.empresa.setEstado(this.comboEstado.getSelectedIndex());
			controlBD.empresa.setCep(this.textoCEP.getText());
			
			//Atualizando banco
			if(opcao == 1)
				controlBD.cadastrar(controlBD.empresa, (JFrame)this);
			else
				controlBD.atualizar(controlBD.empresa, (JFrame)this);

			
			
			//Logotipo
			if(!this.textoLogotipo.getText().isEmpty())
				control.criaLogotipo(this.textoLogotipo.getText(), this);
				
			botaoAlterar.setEnabled(true);
			botaoSalvar.setEnabled(false);
			
			controlBD	= null;
			control		= null;
		}
	}
	
	/*
	 * Método validaCampos
	 * Verifica se todos os campos obrigatorios estao preenchidos
	 */
	private Boolean validaCampos()
	{
		if(this.textoNome.getText().isEmpty())
			return false;
		if(this.textoCNPJ.getText().equals("  .   .   /    -  "))
			return false;
		if(this.textoTelefone.getText().equals("(  )       -     "))
			return false;
		if(this.textoEndereco.getText().isEmpty())
			return false;
		if(this.textoNumero.getText().isEmpty())
			return false;
		if(this.textoBairro.getText().isEmpty())
			return false;
		if(this.textoCidade.getText().isEmpty())
			return false;
		if(this.comboEstado.getSelectedIndex() == -1)
			return false;
		if(this.textoCEP.getText().equals("     -   "))
			return false;
		
		return true;
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

        procuraImagem = new javax.swing.JFileChooser();
        botaoSalvar = new javax.swing.JButton();
        botaoAlterar = new javax.swing.JButton();
        painelEquipamento = new javax.swing.JPanel();
        labelNome = new javax.swing.JLabel();
        textoNome = new javax.swing.JTextField();
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
        labelSite = new javax.swing.JLabel();
        textoSite = new javax.swing.JTextField();
        labelCNPJ = new javax.swing.JLabel();
        labelTelefone = new javax.swing.JLabel();
        labelFax = new javax.swing.JLabel();
        textoFax = new javax.swing.JFormattedTextField();
        labelEmail = new javax.swing.JLabel();
        textoCNPJ = new formattedFields.JTextCNPJ();
        textoEmail = new formattedFields.JTextEmail();
        textoCEP = new formattedFields.JTextCep();
        textoTelefone = new javax.swing.JFormattedTextField();
        textoLogotipo = new javax.swing.JTextField();
        labelLogotipo = new javax.swing.JLabel();
        botaoProcurar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Dados da Empresa");
        setBounds(new java.awt.Rectangle(250, 0, 0, 0));

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

        painelEquipamento.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(95, 133, 169), null), "Dados da Empresa", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.TOP));

        labelNome.setForeground(java.awt.Color.red);
        labelNome.setText("Nome");

        textoNome.addKeyListener(new java.awt.event.KeyAdapter()
        {
            public void keyTyped(java.awt.event.KeyEvent evt)
            {
                textoNomeKeyTyped(evt);
            }
        });

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

        labelCEP.setForeground(java.awt.Color.red);
        labelCEP.setText("CEP");
        labelCEP.addMouseListener(new java.awt.event.MouseAdapter()
        {
            public void mouseClicked(java.awt.event.MouseEvent evt)
            {
                labelCEPMouseClicked(evt);
            }
        });

        labelSite.setText("Site");

        textoSite.addKeyListener(new java.awt.event.KeyAdapter()
        {
            public void keyTyped(java.awt.event.KeyEvent evt)
            {
                textoSiteKeyTyped(evt);
            }
        });

        labelCNPJ.setForeground(java.awt.Color.red);
        labelCNPJ.setText("CNPJ");

        labelTelefone.setForeground(java.awt.Color.red);
        labelTelefone.setText("Telefone");

        labelFax.setText("Fax");

        try
        {
            textoFax.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("(##) ##### - ####")));
        } catch (java.text.ParseException ex)
        {
            ex.printStackTrace();
        }
        textoFax.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                textoFaxActionPerformed(evt);
            }
        });

        labelEmail.setText("E-mail");

        try
        {
            textoTelefone.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("(##) *#### - ####")));
        } catch (java.text.ParseException ex)
        {
            ex.printStackTrace();
        }

        textoLogotipo.setEditable(false);
        textoLogotipo.setFocusable(false);

        labelLogotipo.setText("Logotipo");

        botaoProcurar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/estudiofotografico/view/img/magnifier.png"))); // NOI18N
        botaoProcurar.setText("Procurar");
        botaoProcurar.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                botaoProcurarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout painelEquipamentoLayout = new javax.swing.GroupLayout(painelEquipamento);
        painelEquipamento.setLayout(painelEquipamentoLayout);
        painelEquipamentoLayout.setHorizontalGroup(
            painelEquipamentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelEquipamentoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(painelEquipamentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelNome)
                    .addComponent(labelCNPJ)
                    .addComponent(labelTelefone)
                    .addComponent(labelEmail)
                    .addComponent(labelSite)
                    .addComponent(labelBairro)
                    .addComponent(labelEndereco)
                    .addComponent(labelCidade)
                    .addComponent(labelLogotipo))
                .addGap(15, 15, 15)
                .addGroup(painelEquipamentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(textoSite)
                    .addComponent(textoCNPJ, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(textoNome)
                    .addComponent(textoEmail, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(painelEquipamentoLayout.createSequentialGroup()
                        .addGroup(painelEquipamentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(painelEquipamentoLayout.createSequentialGroup()
                                .addGroup(painelEquipamentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(painelEquipamentoLayout.createSequentialGroup()
                                        .addComponent(textoCidade, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(labelEstado)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(comboEstado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(textoBairro)
                                    .addComponent(textoEndereco))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(painelEquipamentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(labelComplemento)
                                    .addComponent(labelCEP, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(labelNumero, javax.swing.GroupLayout.Alignment.TRAILING))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(painelEquipamentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(textoComplemento, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(textoNumero)
                                    .addComponent(textoCEP, javax.swing.GroupLayout.DEFAULT_SIZE, 82, Short.MAX_VALUE)))
                            .addGroup(painelEquipamentoLayout.createSequentialGroup()
                                .addComponent(textoTelefone, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 35, Short.MAX_VALUE)
                                .addComponent(labelFax)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(textoFax, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(painelEquipamentoLayout.createSequentialGroup()
                        .addComponent(textoLogotipo, javax.swing.GroupLayout.PREFERRED_SIZE, 273, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(botaoProcurar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        painelEquipamentoLayout.setVerticalGroup(
            painelEquipamentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelEquipamentoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(painelEquipamentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelNome)
                    .addComponent(textoNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(painelEquipamentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelCNPJ)
                    .addComponent(textoCNPJ, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(painelEquipamentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelTelefone)
                    .addComponent(textoFax, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelFax)
                    .addComponent(textoTelefone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(painelEquipamentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelEmail)
                    .addComponent(textoEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(painelEquipamentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelSite)
                    .addComponent(textoSite, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6)
                .addGroup(painelEquipamentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelEndereco)
                    .addComponent(textoEndereco, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(textoNumero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelNumero))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(painelEquipamentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelBairro)
                    .addComponent(textoBairro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelComplemento)
                    .addComponent(textoComplemento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(painelEquipamentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelCidade)
                    .addComponent(textoCidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelEstado)
                    .addComponent(comboEstado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelCEP)
                    .addComponent(textoCEP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(painelEquipamentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(textoLogotipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelLogotipo)
                    .addComponent(botaoProcurar))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(painelEquipamento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(131, 131, 131)
                        .addComponent(botaoSalvar, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(botaoAlterar, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(painelEquipamento, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(botaoSalvar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(botaoAlterar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

private void textoNomeKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textoNomeKeyTyped
    int t = textoNome.getText().length();
    if(t == 100)
         evt.setKeyChar((char) KeyEvent.VK_CLEAR);
}//GEN-LAST:event_textoNomeKeyTyped

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

    private void textoFaxActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_textoFaxActionPerformed
    {//GEN-HEADEREND:event_textoFaxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textoFaxActionPerformed

    private void botaoSalvarActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_botaoSalvarActionPerformed
    {//GEN-HEADEREND:event_botaoSalvarActionPerformed
		this.salvar(1);
    }//GEN-LAST:event_botaoSalvarActionPerformed

    private void botaoAlterarActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_botaoAlterarActionPerformed
    {//GEN-HEADEREND:event_botaoAlterarActionPerformed
		this.salvar(2);
    }//GEN-LAST:event_botaoAlterarActionPerformed

    private void botaoProcurarActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_botaoProcurarActionPerformed
    {//GEN-HEADEREND:event_botaoProcurarActionPerformed
        FileNameExtensionFilter imageFilter = new FileNameExtensionFilter("Image files", ImageIO.getReaderFileSuffixes());
		
		this.procuraImagem.setVisible(true);  
		this.procuraImagem.setFileFilter(imageFilter);
		
		int ret = this.procuraImagem.showOpenDialog(null);
		
		if (ret == JFileChooser.APPROVE_OPTION) 
		{
			this.textoLogotipo.setText(this.procuraImagem.getSelectedFile().toString());
		} 
		else 
		{
		}
    }//GEN-LAST:event_botaoProcurarActionPerformed

	
	
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
            java.util.logging.Logger.getLogger(DadosEmpresa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DadosEmpresa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DadosEmpresa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DadosEmpresa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /*
         * Create and display the form
         */
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new DadosEmpresa().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botaoAlterar;
    private javax.swing.JButton botaoProcurar;
    private javax.swing.JButton botaoSalvar;
    private javax.swing.JComboBox comboEstado;
    private javax.swing.JLabel labelBairro;
    private javax.swing.JLabel labelCEP;
    private javax.swing.JLabel labelCNPJ;
    private javax.swing.JLabel labelCidade;
    private javax.swing.JLabel labelComplemento;
    private javax.swing.JLabel labelEmail;
    private javax.swing.JLabel labelEndereco;
    private javax.swing.JLabel labelEstado;
    private javax.swing.JLabel labelFax;
    private javax.swing.JLabel labelLogotipo;
    private javax.swing.JLabel labelNome;
    private javax.swing.JLabel labelNumero;
    private javax.swing.JLabel labelSite;
    private javax.swing.JLabel labelTelefone;
    private javax.swing.JPanel painelEquipamento;
    private javax.swing.JFileChooser procuraImagem;
    private javax.swing.JTextField textoBairro;
    private formattedFields.JTextCep textoCEP;
    private formattedFields.JTextCNPJ textoCNPJ;
    private javax.swing.JTextField textoCidade;
    private javax.swing.JTextField textoComplemento;
    private formattedFields.JTextEmail textoEmail;
    private javax.swing.JTextField textoEndereco;
    private javax.swing.JFormattedTextField textoFax;
    private javax.swing.JTextField textoLogotipo;
    private javax.swing.JTextField textoNome;
    private javax.swing.JTextField textoNumero;
    private javax.swing.JTextField textoSite;
    private javax.swing.JFormattedTextField textoTelefone;
    // End of variables declaration//GEN-END:variables
	private ControladorEmpresa		control;
	private ControladorBancodeDados	controlBD;
	private Empresa					empresa;
}
