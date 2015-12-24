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

import estudiofotografico.EstudioFotografico;
import estudiofotografico.control.ControladorBancodeDados;
import estudiofotografico.control.ControladorClientes;
import estudiofotografico.control.ControladorEventos;
import estudiofotografico.control.ControladorProdutos;
import estudiofotografico.control.ControladorServicos;
import estudiofotografico.control.ControladorVendas;
import estudiofotografico.model.Clientes;
import estudiofotografico.model.LocaisEventos;
import estudiofotografico.model.PagamentoVendas;
import estudiofotografico.model.Produtos;
import estudiofotografico.model.ProdutosVendas;
import estudiofotografico.model.Servicos;
import estudiofotografico.model.ServicosVendas;
import estudiofotografico.model.TiposEventos;
import estudiofotografico.view.erros.ErroCampoEmBranco;
import java.awt.Component;
import java.awt.Container;
import java.awt.Toolkit;
import java.awt.event.ContainerEvent;
import java.awt.event.ContainerListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultCellEditor;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;


/**
 *
 * @author farofa
 */
public class Vendas extends javax.swing.JFrame implements KeyListener, ContainerListener{

    /**
     * Creates new form cadastroMarcas
     */
    public Vendas() {
		initComponents();
		addKeyAndContainerListenerRecursively(this);
		this.setLocationRelativeTo(null);
		this.textoCodigo.setText("** NOVO **");
		//labelNomeUsuario.setText(estudio.user.getNome());
		this.configuraTabelas();
		//Adiciona Listener nas tabelas
		this.setListernerTabelas();
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
            
        }
        
        //TECLA ESC
        if(e.getKeyCode() == KeyEvent.VK_ESCAPE)
        {
            dispose();
        }
    }
    
    //ALTERA TAMANHO TABELAS
    public void configuraTabelas()
    {
		//Alinhamento
		DefaultTableCellRenderer alinhamento = new DefaultTableCellRenderer();
		alinhamento.setHorizontalAlignment(SwingConstants.LEFT);
		tabelaProdutos.getColumnModel().getColumn(0).setCellRenderer(alinhamento);
		tabelaProdutos.getColumnModel().getColumn(1).setCellRenderer(alinhamento);
		tabelaProdutos.getColumnModel().getColumn(2).setCellRenderer(alinhamento);
		tabelaProdutos.getColumnModel().getColumn(3).setCellRenderer(alinhamento);
		
		//Tabela Produtos
		tabelaProdutos.getColumnModel().getColumn(0).setPreferredWidth(50);
		tabelaProdutos.getColumnModel().getColumn(1).setPreferredWidth(189);
		tabelaProdutos.getColumnModel().getColumn(2).setPreferredWidth(80);
		tabelaProdutos.getColumnModel().getColumn(3).setPreferredWidth(40);
		tabelaProdutos.getColumnModel().getColumn(4).setPreferredWidth(80);
		
		//Tabela Serviços
		tabelaServicos.getColumnModel().getColumn(0).setPreferredWidth(50);
		tabelaServicos.getColumnModel().getColumn(1).setPreferredWidth(229);
		tabelaServicos.getColumnModel().getColumn(2).setPreferredWidth(80);
		tabelaServicos.getColumnModel().getColumn(3).setPreferredWidth(80);
		
        //TABELA RECEBIMENTOS
        tabelaRecebimentos.getColumnModel().getColumn(0).setPreferredWidth(90);
        tabelaRecebimentos.getColumnModel().getColumn(1).setPreferredWidth(80);
        tabelaRecebimentos.getColumnModel().getColumn(2).setPreferredWidth(50);
        tabelaRecebimentos.getColumnModel().getColumn(3).setPreferredWidth(95);
        tabelaRecebimentos.getColumnModel().getColumn(4).setPreferredWidth(155);
        tabelaRecebimentos.getColumnModel().getColumn(5).setPreferredWidth(300);
		
		
		
		//Produtos
		TableColumn colunaProdutoValor = tabelaProdutos.getColumnModel().getColumn(2);
        colunaProdutoValor.setCellEditor(new DefaultCellEditor(textoValorTabela));
		TableColumn colunaProdutoDesconto = tabelaProdutos.getColumnModel().getColumn(4);
        colunaProdutoDesconto.setCellEditor(new DefaultCellEditor(textoValorTabela));
		
		tabelaProdutos.setRowHeight(26);
		
		
		
		
		//Serviços
		TableColumn colunaServicosValor = tabelaServicos.getColumnModel().getColumn(2);
        colunaServicosValor.setCellEditor(new DefaultCellEditor(textoValorTabela));
		TableColumn colunaServicosDesconto = tabelaServicos.getColumnModel().getColumn(3);
        colunaServicosDesconto.setCellEditor(new DefaultCellEditor(textoValorTabela));
		
		tabelaServicos.setRowHeight(26);
		
		
		
		
		//Recebimento
		TableColumn colunaRecebimentoVencimento = tabelaRecebimentos.getColumnModel().getColumn(0);
        colunaRecebimentoVencimento.setCellEditor(new DefaultCellEditor(txtVencimento));
		TableColumn colunaRecebimentoValor = tabelaRecebimentos.getColumnModel().getColumn(1);
        colunaRecebimentoValor.setCellEditor(new DefaultCellEditor(textoValorTabela));
		TableColumn colunaRecebimentoRecebimento = tabelaRecebimentos.getColumnModel().getColumn(3);
        colunaRecebimentoRecebimento.setCellEditor(new DefaultCellEditor(textoValorTabela));
		TableColumn colunaRecebimentoFormaPagamento = tabelaRecebimentos.getColumnModel().getColumn(4);
		colunaRecebimentoFormaPagamento.setCellEditor(new DefaultCellEditor(comboPagamento));
		
		tabelaRecebimentos.setRowHeight(26);
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

        checkPago = new javax.swing.JCheckBox();
        comboPagamento = new javax.swing.JComboBox();
        txtVencimento = new formattedFields.JTextData();
        textoValorTabela = new formattedFields.jTextMoeda();
        painelPesquisa = new javax.swing.JPanel();
        labelCodigo = new javax.swing.JLabel();
        textoCodigo = new javax.swing.JTextField();
        painelAbas = new javax.swing.JTabbedPane();
        painelVendas = new javax.swing.JPanel();
        painelInternoProdutos1 = new javax.swing.JPanel();
        labelData = new javax.swing.JLabel();
        labelHora = new javax.swing.JLabel();
        labelCliente = new javax.swing.JLabel();
        textoCliente = new javax.swing.JTextField();
        botaoCadastrarCliente = new javax.swing.JButton();
        botaoPesquisarCliente = new javax.swing.JButton();
        labelTipoEvento = new javax.swing.JLabel();
        botaoCadastrarTipoEvento = new javax.swing.JButton();
        labelLocalEvento = new javax.swing.JLabel();
        botaoCadastrarLocalEvento = new javax.swing.JButton();
        labelObs = new javax.swing.JLabel();
        painelObs = new javax.swing.JScrollPane();
        textoObs = new javax.swing.JTextArea();
        textoData = new com.toedter.calendar.JDateChooser();
        botaoPesquisarTipoEvento = new javax.swing.JButton();
        botaoPesquisarLocalEvento = new javax.swing.JButton();
        textoTipoEvento = new javax.swing.JTextField();
        textoLocalEvento = new javax.swing.JTextField();
        textoHora = new formattedFields.JTextHora();
        painelProdutos = new javax.swing.JPanel();
        painelInternoProdutos = new javax.swing.JPanel();
        botaoProdutoInserir = new javax.swing.JButton();
        botaoProdutoRemover = new javax.swing.JButton();
        botaoProdutoCadastrar = new javax.swing.JButton();
        painelTabelaProdutos = new javax.swing.JScrollPane();
        tabelaProdutos = new javax.swing.JTable();
        painelServicos = new javax.swing.JPanel();
        painelInternoServicos = new javax.swing.JPanel();
        botaoServicosInserir = new javax.swing.JButton();
        botaoServicosRemover = new javax.swing.JButton();
        botaoServicosCadastrar = new javax.swing.JButton();
        painelTabelaServicos = new javax.swing.JScrollPane();
        tabelaServicos = new javax.swing.JTable();
        painelRecebimentos = new javax.swing.JPanel();
        painelInternoRecebimentos = new javax.swing.JPanel();
        botaoRecebimentosInserir = new javax.swing.JButton();
        botaoRecebimentosRemoverTodos = new javax.swing.JButton();
        painelTabelaRecebimentos = new javax.swing.JScrollPane();
        tabelaRecebimentos = new javax.swing.JTable();
        labelParcelas = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        textoMelhorDia = new javax.swing.JFormattedTextField();
        jLabel2 = new javax.swing.JLabel();
        textoEntrada = new formattedFields.jTextMoeda();
        textoParcelas = new javax.swing.JFormattedTextField();
        labelUsuarioAtivo = new javax.swing.JLabel();
        labelNomeUsuario = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        labelValorTotal = new javax.swing.JLabel();
        labelValorDesconto = new javax.swing.JLabel();
        labelPorcentagemDesconto = new javax.swing.JLabel();
        textoPorcentagemDesconto = new javax.swing.JTextField();
        labelValorPago = new javax.swing.JLabel();
        labelTotalReceber = new javax.swing.JLabel();
        textoValorTotal = new formattedFields.jTextMoeda();
        textoValorDesconto = new formattedFields.jTextMoeda();
        textoValorPago = new formattedFields.jTextMoeda();
        textoValorReceber = new formattedFields.jTextMoeda();
        botaoSalvar = new javax.swing.JButton();
        botaoPesquisar = new javax.swing.JButton();
        botaoAlterar = new javax.swing.JButton();
        botaoExcluir = new javax.swing.JButton();
        botaoCancelar = new javax.swing.JButton();

        checkPago.setText("jCheckBox1");

        comboPagamento.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Cartão de Crédito", "Cartão de Débito", "Cheque", "Cheque de Terceiros", "Depósito Bancario", "Dinheiro", "Transferencia Bancária", "Outros" }));

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Vendas");
        setBounds(new java.awt.Rectangle(0, 0, 0, 0));
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/estudiofotografico/view/img/camera-photo-5.png")));
        setMinimumSize(null);

        painelPesquisa.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(95, 133, 169), null), "", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.TOP));

        labelCodigo.setText("Codigo");

        textoCodigo.setEditable(false);
        textoCodigo.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        textoCodigo.setForeground(java.awt.Color.red);
        textoCodigo.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        textoCodigo.setFocusable(false);

        javax.swing.GroupLayout painelPesquisaLayout = new javax.swing.GroupLayout(painelPesquisa);
        painelPesquisa.setLayout(painelPesquisaLayout);
        painelPesquisaLayout.setHorizontalGroup(
            painelPesquisaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelPesquisaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelCodigo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(textoCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        painelPesquisaLayout.setVerticalGroup(
            painelPesquisaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelPesquisaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(painelPesquisaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelCodigo)
                    .addComponent(textoCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        painelAbas.setMaximumSize(null);

        painelInternoProdutos1.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(95, 133, 169), null));
        painelInternoProdutos1.setMaximumSize(null);

        labelData.setForeground(java.awt.Color.red);
        labelData.setText("Data");

        labelHora.setForeground(java.awt.Color.red);
        labelHora.setText("Hora");

        labelCliente.setForeground(java.awt.Color.red);
        labelCliente.setText("Cliente");

        textoCliente.setEditable(false);

        botaoCadastrarCliente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/estudiofotografico/view/img/user_add.png"))); // NOI18N
        botaoCadastrarCliente.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                botaoCadastrarClienteActionPerformed(evt);
            }
        });

        botaoPesquisarCliente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/estudiofotografico/view/img/procurarCliente.png"))); // NOI18N
        botaoPesquisarCliente.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                botaoPesquisarClienteActionPerformed(evt);
            }
        });

        labelTipoEvento.setForeground(java.awt.Color.red);
        labelTipoEvento.setText("Tipo Evento");

        botaoCadastrarTipoEvento.setIcon(new javax.swing.ImageIcon(getClass().getResource("/estudiofotografico/view/img/cake_add.png"))); // NOI18N
        botaoCadastrarTipoEvento.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                botaoCadastrarTipoEventoActionPerformed(evt);
            }
        });

        labelLocalEvento.setForeground(java.awt.Color.red);
        labelLocalEvento.setText("Local Evento");

        botaoCadastrarLocalEvento.setIcon(new javax.swing.ImageIcon(getClass().getResource("/estudiofotografico/view/img/map_add.png"))); // NOI18N
        botaoCadastrarLocalEvento.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                botaoCadastrarLocalEventoActionPerformed(evt);
            }
        });

        labelObs.setText("Observações");

        textoObs.setColumns(20);
        textoObs.setLineWrap(true);
        textoObs.setRows(4);
        textoObs.addKeyListener(new java.awt.event.KeyAdapter()
        {
            public void keyTyped(java.awt.event.KeyEvent evt)
            {
                textoObsKeyTyped(evt);
            }
        });
        painelObs.setViewportView(textoObs);

        botaoPesquisarTipoEvento.setIcon(new javax.swing.ImageIcon(getClass().getResource("/estudiofotografico/view/img/cake_magnify.png"))); // NOI18N
        botaoPesquisarTipoEvento.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                botaoPesquisarTipoEventoActionPerformed(evt);
            }
        });

        botaoPesquisarLocalEvento.setIcon(new javax.swing.ImageIcon(getClass().getResource("/estudiofotografico/view/img/map_magnify.png"))); // NOI18N
        botaoPesquisarLocalEvento.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                botaoPesquisarLocalEventoActionPerformed(evt);
            }
        });

        textoTipoEvento.setEditable(false);

        textoLocalEvento.setEditable(false);

        javax.swing.GroupLayout painelInternoProdutos1Layout = new javax.swing.GroupLayout(painelInternoProdutos1);
        painelInternoProdutos1.setLayout(painelInternoProdutos1Layout);
        painelInternoProdutos1Layout.setHorizontalGroup(
            painelInternoProdutos1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, painelInternoProdutos1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(painelInternoProdutos1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(painelObs, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 440, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, painelInternoProdutos1Layout.createSequentialGroup()
                        .addComponent(labelObs)
                        .addGap(0, 377, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, painelInternoProdutos1Layout.createSequentialGroup()
                        .addGroup(painelInternoProdutos1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labelTipoEvento)
                            .addComponent(labelLocalEvento)
                            .addComponent(labelCliente)
                            .addComponent(labelData))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(painelInternoProdutos1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(painelInternoProdutos1Layout.createSequentialGroup()
                                .addComponent(textoData, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(labelHora)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(textoHora, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                            .addComponent(textoTipoEvento)
                            .addComponent(textoCliente, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 265, Short.MAX_VALUE)
                            .addComponent(textoLocalEvento))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(painelInternoProdutos1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, painelInternoProdutos1Layout.createSequentialGroup()
                                .addComponent(botaoPesquisarCliente)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(botaoCadastrarCliente))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, painelInternoProdutos1Layout.createSequentialGroup()
                                .addGroup(painelInternoProdutos1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(botaoPesquisarTipoEvento)
                                    .addComponent(botaoPesquisarLocalEvento))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(painelInternoProdutos1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(botaoCadastrarTipoEvento)
                                    .addComponent(botaoCadastrarLocalEvento))))))
                .addContainerGap())
        );
        painelInternoProdutos1Layout.setVerticalGroup(
            painelInternoProdutos1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelInternoProdutos1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(painelInternoProdutos1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelData)
                    .addComponent(textoData, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(painelInternoProdutos1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(textoHora, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(labelHora)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(painelInternoProdutos1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(painelInternoProdutos1Layout.createSequentialGroup()
                        .addGroup(painelInternoProdutos1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(painelInternoProdutos1Layout.createSequentialGroup()
                                .addGroup(painelInternoProdutos1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(botaoCadastrarCliente)
                                    .addComponent(botaoPesquisarCliente)
                                    .addGroup(painelInternoProdutos1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(labelCliente)
                                        .addComponent(textoCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(painelInternoProdutos1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(labelTipoEvento)
                                    .addComponent(botaoCadastrarTipoEvento)
                                    .addComponent(textoTipoEvento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(botaoPesquisarTipoEvento))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(painelInternoProdutos1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(labelLocalEvento)
                            .addComponent(botaoCadastrarLocalEvento)
                            .addComponent(textoLocalEvento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(botaoPesquisarLocalEvento))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(labelObs)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(painelObs, javax.swing.GroupLayout.DEFAULT_SIZE, 114, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout painelVendasLayout = new javax.swing.GroupLayout(painelVendas);
        painelVendas.setLayout(painelVendasLayout);
        painelVendasLayout.setHorizontalGroup(
            painelVendasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelVendasLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(painelInternoProdutos1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        painelVendasLayout.setVerticalGroup(
            painelVendasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelVendasLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(painelInternoProdutos1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        painelAbas.addTab("Vendas", painelVendas);

        painelInternoProdutos.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(95, 133, 169), null));
        painelInternoProdutos.setMaximumSize(null);

        botaoProdutoInserir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/estudiofotografico/view/img/add.png"))); // NOI18N
        botaoProdutoInserir.setText("Inserir");
        botaoProdutoInserir.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                botaoProdutoInserirActionPerformed(evt);
            }
        });

        botaoProdutoRemover.setIcon(new javax.swing.ImageIcon(getClass().getResource("/estudiofotografico/view/img/delete.png"))); // NOI18N
        botaoProdutoRemover.setText("Remover");
        botaoProdutoRemover.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                botaoProdutoRemoverActionPerformed(evt);
            }
        });

        botaoProdutoCadastrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/estudiofotografico/view/img/pencil.png"))); // NOI18N
        botaoProdutoCadastrar.setText("Cadastrar");
        botaoProdutoCadastrar.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                botaoProdutoCadastrarActionPerformed(evt);
            }
        });

        painelTabelaProdutos.setPreferredSize(new java.awt.Dimension(100, 100));

        tabelaProdutos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][]
            {

            },
            new String []
            {
                "Código", "Produto", "Valor", "Qtde", "Desconto"
            }
        )
        {
            Class[] types = new Class []
            {
                java.lang.Long.class, java.lang.Object.class, java.lang.String.class, java.lang.Integer.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean []
            {
                false, false, true, true, true
            };

            public Class getColumnClass(int columnIndex)
            {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex)
            {
                return canEdit [columnIndex];
            }
        });
        tabelaProdutos.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        painelTabelaProdutos.setViewportView(tabelaProdutos);

        javax.swing.GroupLayout painelInternoProdutosLayout = new javax.swing.GroupLayout(painelInternoProdutos);
        painelInternoProdutos.setLayout(painelInternoProdutosLayout);
        painelInternoProdutosLayout.setHorizontalGroup(
            painelInternoProdutosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelInternoProdutosLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(painelInternoProdutosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(painelTabelaProdutos, javax.swing.GroupLayout.DEFAULT_SIZE, 440, Short.MAX_VALUE)
                    .addGroup(painelInternoProdutosLayout.createSequentialGroup()
                        .addComponent(botaoProdutoInserir, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(botaoProdutoRemover, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 32, Short.MAX_VALUE)
                        .addComponent(botaoProdutoCadastrar, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        painelInternoProdutosLayout.setVerticalGroup(
            painelInternoProdutosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelInternoProdutosLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(painelInternoProdutosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(botaoProdutoInserir)
                    .addComponent(botaoProdutoCadastrar)
                    .addComponent(botaoProdutoRemover))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(painelTabelaProdutos, javax.swing.GroupLayout.DEFAULT_SIZE, 222, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout painelProdutosLayout = new javax.swing.GroupLayout(painelProdutos);
        painelProdutos.setLayout(painelProdutosLayout);
        painelProdutosLayout.setHorizontalGroup(
            painelProdutosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelProdutosLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(painelInternoProdutos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        painelProdutosLayout.setVerticalGroup(
            painelProdutosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelProdutosLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(painelInternoProdutos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        painelAbas.addTab("Produtos", painelProdutos);

        painelInternoServicos.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(95, 133, 169), null));
        painelInternoServicos.setMaximumSize(null);
        painelInternoServicos.setPreferredSize(new java.awt.Dimension(430, 289));

        botaoServicosInserir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/estudiofotografico/view/img/add.png"))); // NOI18N
        botaoServicosInserir.setText("Inserir");
        botaoServicosInserir.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                botaoServicosInserirActionPerformed(evt);
            }
        });

        botaoServicosRemover.setIcon(new javax.swing.ImageIcon(getClass().getResource("/estudiofotografico/view/img/delete.png"))); // NOI18N
        botaoServicosRemover.setText("Remover");
        botaoServicosRemover.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                botaoServicosRemoverActionPerformed(evt);
            }
        });

        botaoServicosCadastrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/estudiofotografico/view/img/pencil.png"))); // NOI18N
        botaoServicosCadastrar.setText("Cadastrar");
        botaoServicosCadastrar.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                botaoServicosCadastrarActionPerformed(evt);
            }
        });

        painelTabelaServicos.setPreferredSize(new java.awt.Dimension(100, 100));

        tabelaServicos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][]
            {

            },
            new String []
            {
                "Código", "Serviço", "Valor", "Desconto"
            }
        )
        {
            boolean[] canEdit = new boolean []
            {
                true, false, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex)
            {
                return canEdit [columnIndex];
            }
        });
        tabelaServicos.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        painelTabelaServicos.setViewportView(tabelaServicos);

        javax.swing.GroupLayout painelInternoServicosLayout = new javax.swing.GroupLayout(painelInternoServicos);
        painelInternoServicos.setLayout(painelInternoServicosLayout);
        painelInternoServicosLayout.setHorizontalGroup(
            painelInternoServicosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelInternoServicosLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(painelInternoServicosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(painelTabelaServicos, javax.swing.GroupLayout.DEFAULT_SIZE, 440, Short.MAX_VALUE)
                    .addGroup(painelInternoServicosLayout.createSequentialGroup()
                        .addComponent(botaoServicosInserir, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(botaoServicosRemover, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 32, Short.MAX_VALUE)
                        .addComponent(botaoServicosCadastrar, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        painelInternoServicosLayout.setVerticalGroup(
            painelInternoServicosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelInternoServicosLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(painelInternoServicosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(botaoServicosInserir)
                    .addComponent(botaoServicosCadastrar)
                    .addComponent(botaoServicosRemover))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(painelTabelaServicos, javax.swing.GroupLayout.DEFAULT_SIZE, 222, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout painelServicosLayout = new javax.swing.GroupLayout(painelServicos);
        painelServicos.setLayout(painelServicosLayout);
        painelServicosLayout.setHorizontalGroup(
            painelServicosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelServicosLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(painelInternoServicos, javax.swing.GroupLayout.DEFAULT_SIZE, 464, Short.MAX_VALUE)
                .addContainerGap())
        );
        painelServicosLayout.setVerticalGroup(
            painelServicosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelServicosLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(painelInternoServicos, javax.swing.GroupLayout.DEFAULT_SIZE, 279, Short.MAX_VALUE)
                .addContainerGap())
        );

        painelAbas.addTab("Serviços", painelServicos);

        painelInternoRecebimentos.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(95, 133, 169), null));
        painelInternoRecebimentos.setMaximumSize(null);

        botaoRecebimentosInserir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/estudiofotografico/view/img/money_add.png"))); // NOI18N
        botaoRecebimentosInserir.setText("Inserir");
        botaoRecebimentosInserir.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                botaoRecebimentosInserirActionPerformed(evt);
            }
        });

        botaoRecebimentosRemoverTodos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/estudiofotografico/view/img/money_delete.png"))); // NOI18N
        botaoRecebimentosRemoverTodos.setText("Remover Todos");
        botaoRecebimentosRemoverTodos.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                botaoRecebimentosRemoverTodosActionPerformed(evt);
            }
        });

        painelTabelaRecebimentos.setPreferredSize(new java.awt.Dimension(100, 100));

        tabelaRecebimentos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][]
            {

            },
            new String []
            {
                "Vencimento", "Valor (R$)", "Pago?", "Recebimento", "Forma Pagamento", "Observações"
            }
        )
        {
            Class[] types = new Class []
            {
                java.lang.Object.class, java.lang.Object.class, java.lang.Boolean.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean []
            {
                true, false, true, true, true, true
            };

            public Class getColumnClass(int columnIndex)
            {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex)
            {
                return canEdit [columnIndex];
            }
        });
        tabelaRecebimentos.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        painelTabelaRecebimentos.setViewportView(tabelaRecebimentos);

        labelParcelas.setText("Parcelas");

        jLabel1.setText("Melhor Dia");

        textoMelhorDia.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0"))));

        jLabel2.setText("Entrada");

        textoParcelas.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0"))));

        javax.swing.GroupLayout painelInternoRecebimentosLayout = new javax.swing.GroupLayout(painelInternoRecebimentos);
        painelInternoRecebimentos.setLayout(painelInternoRecebimentosLayout);
        painelInternoRecebimentosLayout.setHorizontalGroup(
            painelInternoRecebimentosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelInternoRecebimentosLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(painelInternoRecebimentosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(painelTabelaRecebimentos, javax.swing.GroupLayout.DEFAULT_SIZE, 440, Short.MAX_VALUE)
                    .addGroup(painelInternoRecebimentosLayout.createSequentialGroup()
                        .addGroup(painelInternoRecebimentosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(painelInternoRecebimentosLayout.createSequentialGroup()
                                .addComponent(botaoRecebimentosInserir, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(166, 166, 166))
                            .addGroup(painelInternoRecebimentosLayout.createSequentialGroup()
                                .addComponent(labelParcelas)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(textoParcelas, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(textoMelhorDia, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(44, 44, 44)))
                        .addGroup(painelInternoRecebimentosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(painelInternoRecebimentosLayout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(textoEntrada, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(botaoRecebimentosRemoverTodos, javax.swing.GroupLayout.DEFAULT_SIZE, 144, Short.MAX_VALUE))))
                .addContainerGap())
        );
        painelInternoRecebimentosLayout.setVerticalGroup(
            painelInternoRecebimentosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelInternoRecebimentosLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(painelInternoRecebimentosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelParcelas)
                    .addComponent(jLabel1)
                    .addComponent(textoMelhorDia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(textoEntrada, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(textoParcelas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(painelInternoRecebimentosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(botaoRecebimentosInserir)
                    .addComponent(botaoRecebimentosRemoverTodos))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(painelTabelaRecebimentos, javax.swing.GroupLayout.DEFAULT_SIZE, 196, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout painelRecebimentosLayout = new javax.swing.GroupLayout(painelRecebimentos);
        painelRecebimentos.setLayout(painelRecebimentosLayout);
        painelRecebimentosLayout.setHorizontalGroup(
            painelRecebimentosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelRecebimentosLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(painelInternoRecebimentos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        painelRecebimentosLayout.setVerticalGroup(
            painelRecebimentosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelRecebimentosLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(painelInternoRecebimentos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        painelAbas.addTab("Recebimento", painelRecebimentos);

        labelUsuarioAtivo.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        labelUsuarioAtivo.setText(" Usuario Ativo:");

        labelNomeUsuario.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        labelNomeUsuario.setForeground(new java.awt.Color(255, 21, 21));

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(95, 133, 169), null), "Valores", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.TOP));

        labelValorTotal.setText("Valor Total");

        labelValorDesconto.setText("Valor Desconto");

        labelPorcentagemDesconto.setText("Porcentagem Desconto");

        textoPorcentagemDesconto.addFocusListener(new java.awt.event.FocusAdapter()
        {
            public void focusLost(java.awt.event.FocusEvent evt)
            {
                textoPorcentagemDescontoFocusLost(evt);
            }
        });

        labelValorPago.setText("Valor Pago");

        labelTotalReceber.setText("Total a Receber");

        textoValorTotal.setEditable(false);

        textoValorPago.setEditable(false);

        textoValorReceber.setEditable(false);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelPorcentagemDesconto)
                    .addComponent(labelTotalReceber)
                    .addComponent(labelValorDesconto)
                    .addComponent(labelValorTotal)
                    .addComponent(labelValorPago))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(textoPorcentagemDesconto, javax.swing.GroupLayout.DEFAULT_SIZE, 147, Short.MAX_VALUE)
                    .addComponent(textoValorTotal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(textoValorDesconto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(textoValorPago, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(textoValorReceber, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(labelValorTotal)
                    .addComponent(textoValorTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(labelValorDesconto)
                    .addComponent(textoValorDesconto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelPorcentagemDesconto)
                    .addComponent(textoPorcentagemDesconto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(textoValorPago, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelValorPago))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(textoValorReceber, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelTotalReceber))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        botaoSalvar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/estudiofotografico/view/img/accept.png"))); // NOI18N
        botaoSalvar.setText("Salvar");
        botaoSalvar.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                botaoSalvarActionPerformed(evt);
            }
        });

        botaoPesquisar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/estudiofotografico/view/img/magnifier.png"))); // NOI18N
        botaoPesquisar.setText("Pesquisar");
        botaoPesquisar.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                botaoPesquisarActionPerformed(evt);
            }
        });

        botaoAlterar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/estudiofotografico/view/img/pencil.png"))); // NOI18N
        botaoAlterar.setText("Alterar");
        botaoAlterar.setEnabled(false);
        botaoAlterar.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                botaoAlterarActionPerformed(evt);
            }
        });

        botaoExcluir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/estudiofotografico/view/img/delete.png"))); // NOI18N
        botaoExcluir.setText("Excluir");
        botaoExcluir.setEnabled(false);
        botaoExcluir.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                botaoExcluirActionPerformed(evt);
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
            .addComponent(painelAbas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(painelPesquisa, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(labelUsuarioAtivo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(labelNomeUsuario, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(botaoSalvar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(botaoPesquisar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(botaoAlterar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(botaoExcluir)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(botaoCancelar)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(painelPesquisa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(painelAbas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(labelUsuarioAtivo)
                    .addComponent(labelNomeUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(botaoSalvar)
                    .addComponent(botaoPesquisar)
                    .addComponent(botaoAlterar)
                    .addComponent(botaoExcluir)
                    .addComponent(botaoCancelar))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

private void botaoCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoCancelarActionPerformed
    this.dispose();
}//GEN-LAST:event_botaoCancelarActionPerformed

private void botaoCadastrarClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoCadastrarClienteActionPerformed
    janelaCadastroClientes = new CadastroClientes();
    janelaCadastroClientes.setVisible(true);
}//GEN-LAST:event_botaoCadastrarClienteActionPerformed

private void botaoCadastrarTipoEventoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoCadastrarTipoEventoActionPerformed
    janelaCadastroTipoEvento = new CadastroTipoEvento();
    janelaCadastroTipoEvento.setVisible(true);
}//GEN-LAST:event_botaoCadastrarTipoEventoActionPerformed

private void botaoCadastrarLocalEventoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoCadastrarLocalEventoActionPerformed
    janelaCadastroLocaisEventos = new CadastroLocaisEventos();
    janelaCadastroLocaisEventos.setVisible(true);
}//GEN-LAST:event_botaoCadastrarLocalEventoActionPerformed

private void botaoProdutoCadastrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoProdutoCadastrarActionPerformed
    janelaCadastroProdutos = new CadastroProdutos();
    janelaCadastroProdutos.setVisible(true);
}//GEN-LAST:event_botaoProdutoCadastrarActionPerformed

private void botaoServicosCadastrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoServicosCadastrarActionPerformed
    janelaCadastroServicos = new CadastroServicos();
    janelaCadastroServicos.setVisible(true);
}//GEN-LAST:event_botaoServicosCadastrarActionPerformed

    private void textoObsKeyTyped(java.awt.event.KeyEvent evt)//GEN-FIRST:event_textoObsKeyTyped
    {//GEN-HEADEREND:event_textoObsKeyTyped
		int t = textoObs.getText().length();
		if(t == 999999999)
			 evt.setKeyChar((char) KeyEvent.VK_CLEAR);
    }//GEN-LAST:event_textoObsKeyTyped

    private void botaoPesquisarClienteActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_botaoPesquisarClienteActionPerformed
    {//GEN-HEADEREND:event_botaoPesquisarClienteActionPerformed
		this.janelaProcuraClientes = new ProcuraClientes(this, true);
		this.janelaProcuraClientes.setVisible(true);
    }//GEN-LAST:event_botaoPesquisarClienteActionPerformed

    private void botaoPesquisarLocalEventoActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_botaoPesquisarLocalEventoActionPerformed
    {//GEN-HEADEREND:event_botaoPesquisarLocalEventoActionPerformed
		this.janelaProcuraLocaisEvento = new ProcuraLocaisEvento(this, true);
		this.janelaProcuraLocaisEvento.setVisible(true);
    }//GEN-LAST:event_botaoPesquisarLocalEventoActionPerformed

    private void botaoProdutoInserirActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_botaoProdutoInserirActionPerformed
    {//GEN-HEADEREND:event_botaoProdutoInserirActionPerformed
		this.janelaProcuraProduto = new ProcuraProduto(this, true);
		this.janelaProcuraProduto.setVisible(true);
    }//GEN-LAST:event_botaoProdutoInserirActionPerformed

    private void botaoServicosInserirActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_botaoServicosInserirActionPerformed
    {//GEN-HEADEREND:event_botaoServicosInserirActionPerformed
		this.janelaProcuraServico = new ProcuraServico(this, true);
		this.janelaProcuraServico.setVisible(true);
    }//GEN-LAST:event_botaoServicosInserirActionPerformed

    private void botaoPesquisarTipoEventoActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_botaoPesquisarTipoEventoActionPerformed
    {//GEN-HEADEREND:event_botaoPesquisarTipoEventoActionPerformed
		this.janelaProcuraTiposEvento = new ProcuraTiposEvento(this, true);
		this.janelaProcuraTiposEvento.setVisible(true);
    }//GEN-LAST:event_botaoPesquisarTipoEventoActionPerformed

    private void botaoSalvarActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_botaoSalvarActionPerformed
    {//GEN-HEADEREND:event_botaoSalvarActionPerformed
		if(this.validaCampos())
		{
			this.controlBD			= new ControladorBancodeDados();
			this.controlVendas		= new ControladorVendas();
			this.controlProdutos	= new ControladorProdutos();
			this.controlServicos	= new ControladorServicos();
			
			if(this.textoCodigo.getText().equals("** NOVO **"))
				controlBD.vendas = new estudiofotografico.model.Vendas();
			else
				controlBD.vendas = controlVendas.getVendasByCodigo(Long.parseLong(this.textoCodigo.getText()));
			
			//Definindo os dados
			this.controlBD.vendas.setDia(this.textoData.getCalendar());
			this.controlBD.vendas.setHora(this.textoHora.getText());
			this.controlBD.vendas.setCliente(this.cliente);
			this.controlBD.vendas.setTipoEvento(this.tipoEvento);
			this.controlBD.vendas.setLocalEvento(this.localEvento);
			this.controlBD.vendas.setObs(this.textoObs.getText());
			this.controlBD.vendas.setDesconto(this.textoValorDesconto.getValue());
			this.controlBD.vendas.setPorcentagemDesconto(Double.parseDouble(this.textoPorcentagemDesconto.getText().replace(" %", "").replace(",", ".")));
			
			//Definindo os produtos
			if(tabelaProdutos.isEditing())
				tabelaProdutos.getCellEditor().stopCellEditing();
			List<ProdutosVendas> produtos = new ArrayList<ProdutosVendas>();
			for(int i=0; i<tabelaProdutos.getRowCount(); i++)
			{
				ProdutosVendas	produto		= new ProdutosVendas();
				String			valor		= new String();
				String			desconto	= new String();
				
				
				valor						= this.tabelaProdutos.getValueAt(i, 2).toString();
				valor						= valor.replace("R$ ", "");
				valor						= valor.replace(".", "");
				valor						= valor.replace(",", ".");
				
				desconto					= this.tabelaProdutos.getValueAt(i, 4).toString();
				desconto					= desconto.replace("R$ ", "");
				desconto					= desconto.replace(".", "");
				desconto					= desconto.replace(",", ".");
				
				
				produto.setProduto(this.controlProdutos.getProdutosByCodigo(Long.parseLong(this.tabelaProdutos.getValueAt(i, 0).toString())));
				produto.setValor(Double.parseDouble(valor));
				produto.setQuantidade(Integer.parseInt(this.tabelaProdutos.getValueAt(i, 3).toString()));
				produto.setDesconto(Double.parseDouble(desconto));
				
				produtos.add(produto);
			}
			this.controlBD.vendas.setProdutos(produtos);
			
			//Definindo os Servicos
			if(tabelaServicos.isEditing())
				tabelaServicos.getCellEditor().stopCellEditing();
			List<ServicosVendas> servicos = new ArrayList<ServicosVendas>();
			for(int i=0; i<tabelaServicos.getRowCount(); i++)
			{
				ServicosVendas servico = new ServicosVendas();
				String			valor		= new String();
				String			desconto	= new String();
				
				
				valor						= this.tabelaServicos.getValueAt(i, 2).toString();
				valor						= valor.replace("R$ ", "");
				valor						= valor.replace(".", "");
				valor						= valor.replace(",", ".");
				
				desconto					= this.tabelaServicos.getValueAt(i, 3).toString();
				desconto					= desconto.replace("R$ ", "");
				desconto					= desconto.replace(".", "");
				desconto					= desconto.replace(",", ".");
				
				servico.setServico(this.controlServicos.getServicoByCodigo(Long.parseLong(this.tabelaServicos.getValueAt(i, 0).toString())));
				servico.setValor(Double.parseDouble(valor));
				servico.setDesconto(Double.parseDouble(desconto));
				
				servicos.add(servico);
			}
			this.controlBD.vendas.setServicos(servicos);
			
			//Definindo o pagamento
			if(tabelaRecebimentos.isEditing())
				tabelaRecebimentos.getCellEditor().stopCellEditing();
			List<PagamentoVendas> pagamentos = new ArrayList<PagamentoVendas>();
			for(int i=0; i<tabelaRecebimentos.getRowCount(); i++)
			{
				PagamentoVendas	pagamento	= new PagamentoVendas();
				String			valor		= new String();
				String			pago		= new String();
				String			obs			= new String();
				Double			recebido	= 0.00;
				Boolean			pg			= false;
				
				String[] data = tabelaRecebimentos.getValueAt(i, 0).toString().split("/");
				Calendar vencimento = new GregorianCalendar	(
																Integer.parseInt(data[2]), 
																Integer.parseInt(data[1]), 
																Integer.parseInt(data[0])
															);
				
				valor = this.tabelaRecebimentos.getValueAt(i, 1).toString();
				if(valor.contains("R$"))
				valor = valor.replace("R$ ", "");
				valor = valor.replace(".", "");
				valor = valor.replace(",", ".");
				
				try
				{
					if((Boolean) tabelaRecebimentos.getModel().getValueAt(i, 2))
						pg = true;
					else
						pg = false;
				}
				catch(Exception e)
				{
					pg = false;
				}
				
				if(pg)
				{
					try
					{
						pago = this.tabelaRecebimentos.getValueAt(i, 3).toString();
						pago = pago.replace("R$ ", "");
						pago = pago.replace(".", "");
						pago = pago.replace(",", ".");
						
						recebido = Double.parseDouble(pago);
					}
					catch(Exception e)
					{
						recebido = 0.00;
					}
				}
				
				try
				{
					obs = this.tabelaRecebimentos.getValueAt(i, 5).toString();
				}
				catch(Exception e)
				{
					obs = "";
				}
				
				
				pagamento.setVencimento(vencimento);
				pagamento.setValor(Double.parseDouble(valor));
				pagamento.setPago(pg);
				pagamento.setRecebido(recebido);
				pagamento.setObs(obs);
				
				try
				{
					if (this.tabelaRecebimentos.getValueAt(i, 4).toString().isEmpty())
						pagamento.setFormaPagamento(0);
					else if(this.tabelaRecebimentos.getValueAt(i, 4).toString().equals("Cartão de Crédito"))
						pagamento.setFormaPagamento(1);
					else if(this.tabelaRecebimentos.getValueAt(i, 4).toString().equals("Cartão de Débito"))
						pagamento.setFormaPagamento(2);
					else if(this.tabelaRecebimentos.getValueAt(i, 4).toString().equals("Cheque"))
						pagamento.setFormaPagamento(3);
					else if(this.tabelaRecebimentos.getValueAt(i, 4).toString().equals("Cheque de Terceiros"))
						pagamento.setFormaPagamento(4);
					else if(this.tabelaRecebimentos.getValueAt(i, 4).toString().equals("Depósito Bancario"))
						pagamento.setFormaPagamento(5);
					else if(this.tabelaRecebimentos.getValueAt(i, 4).toString().equals("Dinheiro"))
						pagamento.setFormaPagamento(6);
					else if(this.tabelaRecebimentos.getValueAt(i, 4).toString().equals("Transferencia Bancária"))
						pagamento.setFormaPagamento(7);
					else if(this.tabelaRecebimentos.getValueAt(i, 4).toString().equals("Outros"))
						pagamento.setFormaPagamento(8);
				}
				catch(Exception e)
				{
					pagamento.setFormaPagamento(0);
				}
				
				
				pagamentos.add(pagamento);
			}
			this.controlBD.vendas.setPagamentos(pagamentos);
			if(!textoMelhorDia.getText().equals(""))
				this.controlBD.vendas.setMelhorDia(Integer.parseInt(textoMelhorDia.getText()));
			else
				this.controlBD.vendas.setMelhorDia(-1);
			
			//Definido o quitado
			if(textoValorReceber.getValue() == 0)
				this.controlBD.vendas.setQuitado(true);
			else
				this.controlBD.vendas.setQuitado(false);
			
			//Atualizando banco
			if(this.textoCodigo.getText().equals("** NOVO **"))
				controlBD.cadastrar(controlBD.vendas, (JFrame)this);
			else
				controlBD.atualizar(controlBD.vendas, (JFrame)this);

			this.limpaTela();

			this.controlBD			= null;
			this.controlVendas		= null;
			this.controlProdutos	= null;
			this.controlServicos	= null;
		}
    }//GEN-LAST:event_botaoSalvarActionPerformed

    private void botaoProdutoRemoverActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_botaoProdutoRemoverActionPerformed
    {//GEN-HEADEREND:event_botaoProdutoRemoverActionPerformed
		this.removerLinhaTabela(this.tabelaProdutos);
		
    }//GEN-LAST:event_botaoProdutoRemoverActionPerformed

    private void botaoServicosRemoverActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_botaoServicosRemoverActionPerformed
    {//GEN-HEADEREND:event_botaoServicosRemoverActionPerformed
		this.removerLinhaTabela(this.tabelaServicos);
		
		
    }//GEN-LAST:event_botaoServicosRemoverActionPerformed

    private void botaoRecebimentosRemoverTodosActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_botaoRecebimentosRemoverTodosActionPerformed
    {//GEN-HEADEREND:event_botaoRecebimentosRemoverTodosActionPerformed
		this.apagaTabela(this.tabelaRecebimentos);
    }//GEN-LAST:event_botaoRecebimentosRemoverTodosActionPerformed

    private void botaoRecebimentosInserirActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_botaoRecebimentosInserirActionPerformed
    {//GEN-HEADEREND:event_botaoRecebimentosInserirActionPerformed
		Date				data			= new Date(System.currentTimeMillis());
		String				formato			= "dd/MM/yyyy";  
		DecimalFormat		fmt				= new DecimalFormat("#,##0.00");   //limita o número de casas decimais
		SimpleDateFormat	dataFormatada	= new SimpleDateFormat(formato);
		Double				valorParcela	= 0.0;
		Double				valorTotal		= 0.0;
		Double				valorDesconto	= 0.0;
		Double				valorPago		= 0.0;
		int					parcelasPagas	= 0;
		
		if(!textoMelhorDia.getText().equals(""))
		{
			Integer diaAtual;
			
			Calendar c	= Calendar.getInstance();
			diaAtual	= c.get(Calendar.DAY_OF_MONTH);
			
			c.set(Calendar.DAY_OF_MONTH, Integer.parseInt(textoMelhorDia.getText()));
			
			//Se o dia atual for maior do que o dia da parcela, aumenta 1 mes
			if(diaAtual > Integer.parseInt(textoMelhorDia.getText()))
				c.set(Calendar.MONTH, c.get(Calendar.MONTH)+1);
			
			data		=	c.getTime();
		}
		
		//Entrada
		if(textoEntrada.getValue() != 0.0)
		{
			this.adicionaLinhaTabela(tabelaRecebimentos);

			tabelaRecebimentos.setValueAt(dataFormatada.format(new Date(System.currentTimeMillis())), this.tabelaRecebimentos.getRowCount()-1, 0);
			tabelaRecebimentos.setValueAt("R$ "+fmt.format(textoEntrada.getValue()), this.tabelaRecebimentos.getRowCount()-1, 1);
			tabelaRecebimentos.setValueAt(true, this.tabelaRecebimentos.getRowCount()-1, 2);
			tabelaRecebimentos.setValueAt("R$ "+fmt.format(textoEntrada.getValue()), this.tabelaRecebimentos.getRowCount()-1, 3);
			tabelaRecebimentos.setValueAt("Entrada", this.tabelaRecebimentos.getRowCount()-1, 5);
		}
		
		apagaTabela(tabelaRecebimentos);
		
		try
		{
			//Não há valor para receber
			if	(this.textoValorReceber.getValue() == 0)
			{
				JOptionPane.showMessageDialog(null, "Não precisa adicionar pagamento, não há valores há receber!", "Venda Paga!",JOptionPane.INFORMATION_MESSAGE);
				this.textoParcelas.setText("");
			}
			//Existem valores a receber
			else
			{
				//Percorre a tabela Procurando valores pagos
				for(int i=0; i<this.tabelaRecebimentos.getRowCount(); i++)
				{
					//Encontrou parcela paga
					if((Boolean) tabelaRecebimentos.getModel().getValueAt(i, 2))
					{
						parcelasPagas++;
						data = dataFormatada.parse(this.tabelaRecebimentos.getValueAt(i, 0).toString());
						data.setDate(data.getDate()+30);
					}
				}
				
				//Se o numero de parcelas for menor do que o numero de parcelas Pagas +1
				if(Integer.parseInt(this.textoParcelas.getText()) < parcelasPagas+1)
					JOptionPane.showMessageDialog	(
														null, 
														"O valor das parecelas é invalido.\nO mínimo de parcelas são "+(parcelasPagas+1), 
														"Parcelas inválidas!",
														JOptionPane.INFORMATION_MESSAGE
													);
				//Pode inserir as parcelas
				else
				{
					//Insere as parcelas na tabela
					for(int i=0; i<Integer.parseInt(textoParcelas.getText())-parcelasPagas; i++)
					{
						//Se tiver alguma coisa no campo melhor dia
						if(!textoMelhorDia.getText().equals(""))
						{
							Calendar c = Calendar.getInstance();
							c.setTime(data);
							c.set(Calendar.DAY_OF_MONTH, Integer.parseInt(textoMelhorDia.getText()));
							data = c.getTime();
						}
						
						this.adicionaLinhaTabela(tabelaRecebimentos);

						tabelaRecebimentos.setValueAt(dataFormatada.format(data), this.tabelaRecebimentos.getRowCount()-1, 0);
						tabelaRecebimentos.setValueAt(false, this.tabelaRecebimentos.getRowCount()-1, 2);
						data.setDate(data.getDate() + 30);

						//Obtem o valor total
						try
						{
							valorTotal		= this.textoValorTotal.getValue();
							valorDesconto	= this.textoValorDesconto.getValue();
						}
						catch(Exception e)
						{
						}
						
						//Obtem o valor pago
						try
						{
							valorPago = this.textoValorPago.getValue();
						}
						catch (Exception e)
						{
							valorPago = 0.0;
						}

						try
						{
							valorParcela = (valorTotal-valorDesconto-valorPago) / (Integer.parseInt(this.textoParcelas.getText())-parcelasPagas);
							tabelaRecebimentos.setValueAt("R$ "+fmt.format(valorParcela), this.tabelaRecebimentos.getRowCount()-1, 1);
						}
						catch (Exception e)
						{
							tabelaRecebimentos.setValueAt("R$ 0,00", this.tabelaRecebimentos.getRowCount()-1, 1);
						}
					}
				}
			}
		}
		catch(NumberFormatException e)
		{
			JOptionPane.showMessageDialog(null, "Não precisa adicionar pagamento, não há valores há receber!", "Venda Paga!",JOptionPane.INFORMATION_MESSAGE);
		}
		catch (ParseException ex)
		{
			Logger.getLogger(Vendas.class.getName()).log(Level.SEVERE, null, ex);
		}
    }//GEN-LAST:event_botaoRecebimentosInserirActionPerformed

    private void textoPorcentagemDescontoFocusLost(java.awt.event.FocusEvent evt)//GEN-FIRST:event_textoPorcentagemDescontoFocusLost
    {//GEN-HEADEREND:event_textoPorcentagemDescontoFocusLost
        /*DecimalFormat fmt = new DecimalFormat("##0.00");   //limita o número de casas decimais
		Double porcentagem = 0.0;
		
		porcentagem = Double.parseDouble(this.textoPorcentagemDesconto.getText().replace(" %", ""));
		
		if(this.cliente.getCadastrado() == true)
		{
			porcentagem += 5;
		}*/
		
		this.textoPorcentagemDesconto.setText(this.textoPorcentagemDesconto.getText() + " %");
		
		this.calculaValorDesconto();
    }//GEN-LAST:event_textoPorcentagemDescontoFocusLost

    private void botaoPesquisarActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_botaoPesquisarActionPerformed
    {//GEN-HEADEREND:event_botaoPesquisarActionPerformed
		janelaProcuraVendas = new ProcuraVendas();
		janelaProcuraVendas.setJanelaVendas(this);
		janelaProcuraVendas.setVisible(true);
    }//GEN-LAST:event_botaoPesquisarActionPerformed

    private void botaoAlterarActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_botaoAlterarActionPerformed
    {//GEN-HEADEREND:event_botaoAlterarActionPerformed
		this.habilitaCampos(true);
		botaoSalvar.setEnabled(true);
        botaoAlterar.setEnabled(false);
    }//GEN-LAST:event_botaoAlterarActionPerformed

    private void botaoExcluirActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_botaoExcluirActionPerformed
    {//GEN-HEADEREND:event_botaoExcluirActionPerformed
		this.controlBD		= new ControladorBancodeDados();
		this.controlVendas	= new ControladorVendas();
		
		Long		codigo	= Long.parseLong(this.textoCodigo.getText());
		
		controlBD.vendas	= new estudiofotografico.model.Vendas();
		controlBD.vendas	= controlVendas.getVendasByCodigo(Long.parseLong(this.textoCodigo.getText()));
		
		//Apagando Local de evento
		controlBD.apagar(codigo, controlBD.vendas, (JFrame)this);
		
		controlBD		= null;
		controlVendas	= null;
		this.limpaTela();
    }//GEN-LAST:event_botaoExcluirActionPerformed

	/*
	 * Método setListernerTabelas
	 * Adiciona os listeners nas tabelas
	 */
	private void setListernerTabelas()
	{
		//Produtos
		tabelaProdutos.getModel().addTableModelListener(new TableModelListener()
		{
			@Override
			public void tableChanged (TableModelEvent tme)
			{
				DecimalFormat	fmt		= new DecimalFormat("#,##0.00");
				
				if(tabelaProdutos.getRowCount()> 0)
				{
					try
					{
						if
						(
							!(tabelaProdutos.getValueAt(tabelaProdutos.getRowCount()-1, 2).toString().isEmpty()) ||
							!(tabelaProdutos.getValueAt(tabelaProdutos.getRowCount()-1, 3).toString().isEmpty()) ||
							!(tabelaProdutos.getValueAt(tabelaProdutos.getRowCount()-1, 4).toString().isEmpty()) 
						)	
						{	
							for(int i=0; i<tabelaProdutos.getRowCount(); i++)
							{
								Double preco;
								Double desconto;
								//Valor
								if(!tabelaProdutos.getValueAt(i, 2).toString().contains("R$"))
								{
									preco = Double.parseDouble(tabelaProdutos.getValueAt(i, 2).toString().replace(",", "."));
									tabelaProdutos.setValueAt("R$ "+fmt.format(preco), i, 2);
								}
								//Desconto
								if(!tabelaProdutos.getValueAt(i, 4).toString().contains("R$"))
								{
									desconto = Double.parseDouble(tabelaProdutos.getValueAt(i, 4).toString().replace(",", "."));
									tabelaProdutos.setValueAt("R$ "+fmt.format(desconto), i, 4);
								}
							}
							calculaTotal();	
						}
					}
					catch(Exception e)
					{
					}
				}
			}
		});
		
		//Serviços
		tabelaServicos.getModel().addTableModelListener(new TableModelListener()
		{
			@Override
			public void tableChanged (TableModelEvent tme)
			{
				DecimalFormat	fmt		= new DecimalFormat("#,##0.00");
				
				if(tabelaServicos.getRowCount()> 0)
					try
					{
						if
						(
							!(tabelaServicos.getValueAt(tabelaServicos.getRowCount()-1, 2).toString().isEmpty()) ||
							!(tabelaServicos.getValueAt(tabelaServicos.getRowCount()-1, 3).toString().isEmpty()) 
						)
						{
							for(int i=0; i<tabelaServicos.getRowCount(); i++)
							{
								Double preco;
								Double desconto;
								//Valor
								if(!tabelaServicos.getValueAt(i, 2).toString().contains("R$"))
								{
									preco = Double.parseDouble(tabelaServicos.getValueAt(i, 2).toString().replace(",", "."));
									tabelaServicos.setValueAt("R$ "+fmt.format(preco), i, 2);
								}
								//Desconto
								if(!tabelaServicos.getValueAt(i, 3).toString().contains("R$"))
								{
									desconto = Double.parseDouble(tabelaServicos.getValueAt(i, 3).toString().replace(",", "."));
									tabelaServicos.setValueAt("R$ "+fmt.format(desconto), i, 3);
								}
							}
							
							calculaTotal();
						}
					}
					catch(Exception e)
					{
						
					}
			}
		});
		
		
		//Recebimentos
		tabelaRecebimentos.getModel().addTableModelListener(new TableModelListener()
		{
			@Override
			public void tableChanged (TableModelEvent tme)
			{
				DecimalFormat	fmt		= new DecimalFormat("#,##0.00");
				
				if(tabelaRecebimentos.getRowCount()> 0)
				{
					try
					{
						for(int i=0; i<tabelaRecebimentos.getRowCount(); i++)
						{
							Double preco;
							Double pagamento;
							//Valor
							if(!tabelaRecebimentos.getValueAt(i, 1).toString().contains("R$"))
							{
								preco = Double.parseDouble(tabelaRecebimentos.getValueAt(i, 1).toString().replace(",", "."));
								tabelaRecebimentos.setValueAt("R$ "+fmt.format(preco), i, 1);
							}
							//Desconto
							if(!tabelaRecebimentos.getValueAt(i, 3).toString().contains("R$"))
							{
								pagamento = Double.parseDouble(tabelaRecebimentos.getValueAt(i, 3).toString().replace(",", "."));
								tabelaRecebimentos.setValueAt("R$ "+fmt.format(pagamento), i, 3);
							}
						}
					}
					catch(Exception e)
					{
						
					}
				
					calculaTotal();
				}
			}
		});
	}

	/*
	 * Método setClienteByCod
	 * Busca o Cliente pelo codigo
	 */
	public void setClienteByCod (Long codigo)
	{
		this.controlClientes	= new ControladorClientes();
		this.cliente			= this.controlClientes.getClientesByCodigo(codigo);
		this.textoCliente.setText(this.cliente.getNome());
		this.controlClientes = null;
	}
	
	/*
	 * Método setLocalByCod
	 * Busca o Local pelo codigo
	 */
	public void setLocalByCod(Long codigo)
	{
		this.controladorEventos = new ControladorEventos();
		this.localEvento		= this.controladorEventos.getLocalByCodigo(codigo);
		this.textoLocalEvento.setText(this.localEvento.getNome());
		this.controladorEventos = null;
	}
	
	/*
	 * Método setTipoEventoByCod
	 * Busca o Tipo de Evento pelo codigo
	 */
	public void setTipoEventoByCod(Long codigo)
	{
		this.controladorEventos = new ControladorEventos();
		this.tipoEvento			= this.controladorEventos.getTiposEventosByCod(codigo);
		this.textoTipoEvento.setText(this.tipoEvento.getTipo());
		this.controladorEventos	= null;
	}

	/*
	 * Método validaCampos
	 * Verifica se todos os campos estão preenchidos
	 */
	private Boolean validaCampos()
	{
		Boolean valida = true;
		
		if(this.textoData.getCalendar() == null)
			valida = false;
		if(this.textoHora.getText().equals("  :  :  "))
			valida = false;
		if(this.cliente == null)
			valida = false;
		if(this.tipoEvento == null)
			valida = false;
		if(this.localEvento == null)
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
		this.textoData.setCalendar(null);
		this.textoHora.setText(null);
		this.textoCliente.setText(null);
		this.textoTipoEvento.setText(null);
		this.textoLocalEvento.setText(null);
		this.textoObs.setText(null);
		this.apagaTabela(this.tabelaProdutos);
		this.apagaTabela(this.tabelaServicos);
		this.textoParcelas.setText("");
		this.apagaTabela(this.tabelaRecebimentos);
		this.textoValorTotal.setText("");
		this.textoValorDesconto.setText("");
		this.textoPorcentagemDesconto.setText("");
		this.textoValorPago.setText("");
		this.textoValorReceber.setText("");
		this.textoMelhorDia.setText("");
		this.textoEntrada.setText("");
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
	 * Método apagaTabela
	 * Apaga a tabela selecionada
	 */
	private void apagaTabela(JTable tabela)
	{
		DefaultTableModel modelo = (DefaultTableModel)tabela.getModel();
		//Se não for a tabela recebimentos
		if(tabela != tabelaRecebimentos)
		{
			while(tabela.getRowCount() > 0)
				modelo.removeRow(0);
		}
		//Tabela Recebimentos
		else
		{
			//Percorre a tabela
			for(int i=0; i<tabelaRecebimentos.getRowCount(); i++)
			{
				//Se não estiver marcado como paga, ou não tiver valor definido
				if
					(
						((Boolean) tabelaRecebimentos.getModel().getValueAt(i, 2) == false) ||
							(
								tabela.getValueAt(i, 3) == null ||
								tabela.getValueAt(i, 3).toString().equals("")
							)
					)
				{
					//Apaga a linha
					modelo.removeRow(i);
					//Reinicia for
					i = -1;
				}
			}
		}
	}
	
	/*
	 * Método calculaTotal
	 * Calcula o total da Nota e define os campos com os valores
	 */
	private void calculaTotal()
	{
		this.calculaValores();
		this.calculaPorcentagemDesconto();
		this.calculaPagamento();
		this.calculaValorReceber();
	}
	
	/*
	 * Método CalculaValores
	 * Calcula os valores de Produtos e Serviços
	 */
	private void calculaValores()
	{
		Double			total			= 0.0;
		Double			desconto		= 0.0;
		Double			val				= 0.0;
		Double			desc			= 0.0;
		Double			recebido		= 0.0;
		int				parcelasPagas	= 0;
		String			convert			= new String();
		DecimalFormat	fmt				= new DecimalFormat("#,##0.00");   //limita o número de casas decimais
		
		//Produtos
		for(int i=0; i<tabelaProdutos.getRowCount(); i++)
		{
			//Valor
			convert = this.tabelaProdutos.getValueAt(i, 2).toString();
			convert = convert.replace("R$ ", "");
			convert = convert.replace(".", "");
			convert	= convert.replace(",", ".");
			val		= Double.parseDouble(convert);
			total	+= val * Integer.parseInt(this.tabelaProdutos.getValueAt(i, 3).toString());
			//Desconto
			convert = this.tabelaProdutos.getValueAt(i, 4).toString();
			convert = convert.replace("R$ ", "");
			convert = convert.replace(".", "");
			convert	= convert.replace(",", ".");
			desc	= Double.parseDouble(convert);
			desconto += desc;
		}
		
		//Serviços
		for(int i=0; i<tabelaServicos.getRowCount(); i++)
		{
			//Valor
			convert = this.tabelaServicos.getValueAt(i, 2).toString();
			convert = convert.replace("R$ ", "");
			convert = convert.replace(".", "");
			convert	= convert.replace(",", ".");
			val		= Double.parseDouble(convert);
			total	+= val;
			//Desconto
			convert = this.tabelaServicos.getValueAt(i, 3).toString();
			convert = convert.replace("R$ ", "");
			convert = convert.replace(".", "");
			convert	= convert.replace(",", ".");
			desc	= Double.parseDouble(convert);
			desconto += desc;
		}
		
		this.textoValorTotal.setText(fmt.format(total));
		this.textoValorDesconto.setText(fmt.format(desconto));
	}
	
	/*
	 * Método calculaValorDesconto
	 * Calcula o valor do desconto de acordo com a porcentagem
	 */
	private void calculaValorDesconto()
	{
		Double porcentagem	= null;
		Double valor		= null;
		DecimalFormat fmt	= new DecimalFormat("##0.00");   //limita o número de casas decimais
		
		//Calcula Valor do Desconto e VAlor a pagar
		porcentagem = Double.parseDouble(this.textoPorcentagemDesconto.getText().replace(" %", "").replace(",", "."));
        valor = this.textoValorTotal.getValue() * (porcentagem/100);
        this.textoValorDesconto.setText(valor);
        this.calculaValorReceber();
		
		//Insere mascara
		/*
		 * if(!this.textoPorcentagemDesconto.getText().contains(" %"))
			this.textoPorcentagemDesconto.setText(fmt.format(Double.parseDouble(this.textoPorcentagemDesconto.getText())) + " %");*/
	}
	
	/*
	 * Método calculaPorcentagemDesconto
	 * Calcula o valor da porcentagem do desconto
	 */
	private void calculaPorcentagemDesconto()
	{
		Double			porcentagem = 0.0;
		DecimalFormat	fmt			= new DecimalFormat("##0.00");
		
		//o try é So para garantir que existe o cliente selecionado, se caso nao tiver ele nao aborta
		try
		{
			if(this.cliente.getCadastrado() == true)
				porcentagem += 5;
		}
		catch (Exception e)
		{
		}
		
		porcentagem += (this.textoValorDesconto.getValue() * 100) / this.textoValorTotal.getValue();
		this.textoPorcentagemDesconto.setText(fmt.format(porcentagem) + " %");
		this.calculaValorDesconto();
	}
	
	/*
	 * Método calculaPagamento
	 * Calcula o valor do pagamento
	 */
	private void calculaPagamento()
	{
		Double			total			= 0.0;
		String			convert			= new String();
		DecimalFormat	fmt				= new DecimalFormat("#,##0.00");   //limita o número de casas decimais
		
		for(int i=0; i<tabelaRecebimentos.getRowCount(); i++)
		{
			try
			{		
				//Verifica se foi pago
				if((Boolean) tabelaRecebimentos.getModel().getValueAt(i, 2))
				{
					convert = this.tabelaRecebimentos.getValueAt(i, 3).toString();
					convert = convert.replace("R$ ", "");
					convert = convert.replace(".", "");
					convert	= convert.replace(",", ".");
					total+= Double.parseDouble(convert);
				}
			}
			catch (Exception e)
			{
				total += 0.0;
			}
		}
		
		this.textoValorPago.setText(total);
	}
	
	/*
	 * Método calculaValorReceber
	 * Calcula o valor que ainda resta para receber
	 */
	private void calculaValorReceber()
	{
		Double valor =	0.0;
		Double total;
		Double desconto;
		Double pago;
		
		
		//Valor Total
		try
		{
			total = this.textoValorTotal.getValue();
		}
		catch (Exception e)
		{
			total = 0.0;
		}
		//Desconto
		try
		{
			desconto = this.textoValorDesconto.getValue();
		}
		catch (Exception e)
		{
			desconto = 0.0;
		}
		//Pago
		try
		{
			pago = this.textoValorPago.getValue();
		}
		catch (Exception e)
		{
			pago = 0.0;
		}
		
		valor	=	total - desconto - pago;
		
		this.textoValorReceber.setText(valor);
	}
	
	/*
	 * Método insertProduto
	 * Busca um Produto e insere na tabela
	 */
	public void insertProduto(Long codigo)
	{
		this.controlProdutos = new ControladorProdutos();
		Produtos prod = this.controlProdutos.getProdutosByCodigo(codigo);
		
		DecimalFormat fmt = new DecimalFormat("#,##0.00");   //limita o número de casas decimais
		String valor = "R$ "+fmt.format(prod.getValor());
		valor.replace(".", ",");
		
		this.adicionaLinhaTabela(tabelaProdutos);
		tabelaProdutos.setValueAt(prod.getCodigo(), tabelaProdutos.getRowCount()-1, 0);
		tabelaProdutos.setValueAt(prod.getProduto(), tabelaProdutos.getRowCount()-1, 1);
		tabelaProdutos.setValueAt(valor, tabelaProdutos.getRowCount()-1, 2);
		tabelaProdutos.setValueAt(1, tabelaProdutos.getRowCount()-1, 3);
		tabelaProdutos.setValueAt("R$ 0,00", tabelaProdutos.getRowCount()-1, 4);
		
		controlProdutos = null;
	}
	
	/*
	 * Método insertServico
	 * Insere servico na tabela
	 */
	public void insertServico(Long codigo)
	{
		this.controlServicos = new ControladorServicos();
		Servicos serv = this.controlServicos.getServicoByCodigo(codigo);
		
		DecimalFormat fmt = new DecimalFormat("#,##0.00");   //limita o número de casas decimais
		String valor = "R$ "+fmt.format(serv.getValor());
		valor.replace(".", ",");
		
		this.adicionaLinhaTabela(tabelaServicos);
		tabelaServicos.setValueAt(serv.getCodigo(), tabelaServicos.getRowCount()-1, 0);
		tabelaServicos.setValueAt(serv.getServico(), tabelaServicos.getRowCount()-1, 1);
		tabelaServicos.setValueAt(valor, tabelaServicos.getRowCount()-1, 2);
		tabelaServicos.setValueAt("R$ 0,00", tabelaServicos.getRowCount()-1, 3);
	}
	
	/*
	 * Método getVendaByCodigo
	 * Obtem a Venda pelo Codigo
	 */
	public void getVendaByCodigo()
	{
		DecimalFormat		fmt				= new DecimalFormat("#,##0.00");   //limita o número de casas decimais
		SimpleDateFormat	fmtData			= new SimpleDateFormat("dd/MM/yyyy");
		
		this.controlVendas					= new ControladorVendas();
        this.venda							= controlVendas.getVendasByCodigo(this.codigo);
        
        //Insere os dados na janela
		this.textoCodigo.setText(this.venda.getCodigo().toString());
		this.textoData.setCalendar(this.venda.getDia());
		this.textoHora.setText(this.venda.getHora());
		this.cliente = this.venda.getCliente();
		this.textoCliente.setText(this.venda.getCliente().getNome());
		this.tipoEvento = this.venda.getTipoEvento();
		this.textoTipoEvento.setText(this.venda.getTipoEvento().getTipo());
		this.localEvento = this.venda.getLocalEvento();
		this.textoLocalEvento.setText(this.venda.getLocalEvento().getNome());
		this.textoObs.setText(this.venda.getObs());
		
		//Produtos
		for (ProdutosVendas produto: this.venda.getProdutos())
		{
			this.adicionaLinhaTabela(tabelaProdutos);
			
			this.tabelaProdutos.setValueAt(produto.getProduto().getCodigo(),		this.tabelaProdutos.getRowCount()-1, 0);
			this.tabelaProdutos.setValueAt(produto.getProduto().getProduto(),		this.tabelaProdutos.getRowCount()-1, 1);
			this.tabelaProdutos.setValueAt("R$ "+fmt.format(produto.getValor()),	this.tabelaProdutos.getRowCount()-1, 2);
			this.tabelaProdutos.setValueAt(produto.getQuantidade(),					this.tabelaProdutos.getRowCount()-1, 3);
			this.tabelaProdutos.setValueAt("R$ "+fmt.format(produto.getDesconto()),	this.tabelaProdutos.getRowCount()-1, 4);
		}
		
		//Servicos
		for(ServicosVendas servico: this.venda.getServicos())
		{
			this.adicionaLinhaTabela(tabelaServicos);
			
			this.tabelaServicos.setValueAt(servico.getServico().getCodigo(),		this.tabelaServicos.getRowCount()-1, 0);
			this.tabelaServicos.setValueAt(servico.getServico().getServico(),		this.tabelaServicos.getRowCount()-1, 1);
			this.tabelaServicos.setValueAt("R$ "+fmt.format(servico.getValor()),	this.tabelaServicos.getRowCount()-1, 2);
			this.tabelaServicos.setValueAt("R$ "+fmt.format(servico.getDesconto()),	this.tabelaServicos.getRowCount()-1, 3);
		}
		
		//Pagamento
		for(PagamentoVendas pagamento: this.venda.getPagamentos())
		{
			this.adicionaLinhaTabela(tabelaRecebimentos);
			
			this.tabelaRecebimentos.setValueAt(fmtData.format(pagamento.getVencimento().getTime()),	this.tabelaRecebimentos.getRowCount()-1, 0);
			this.tabelaRecebimentos.setValueAt(fmt.format(pagamento.getValor()),					this.tabelaRecebimentos.getRowCount()-1, 1);
			this.tabelaRecebimentos.setValueAt(pagamento.getPago(),									this.tabelaRecebimentos.getRowCount()-1, 2);
			this.tabelaRecebimentos.setValueAt(fmt.format(pagamento.getRecebido()),					this.tabelaRecebimentos.getRowCount()-1, 3);
			this.tabelaRecebimentos.setValueAt(pagamento.getObs(),									this.tabelaRecebimentos.getRowCount()-1, 5);
			
			if(pagamento.getFormaPagamento() == 0)
				this.tabelaRecebimentos.setValueAt("", this.tabelaRecebimentos.getRowCount()-1, 4);
			else if(pagamento.getFormaPagamento() == 1)
				this.tabelaRecebimentos.setValueAt("Cartão de Crédito", this.tabelaRecebimentos.getRowCount()-1, 4);
			else if(pagamento.getFormaPagamento() == 2)
				this.tabelaRecebimentos.setValueAt("Cartão de Débito", this.tabelaRecebimentos.getRowCount()-1, 4);
			else if(pagamento.getFormaPagamento() == 3)
				this.tabelaRecebimentos.setValueAt("Cheque", this.tabelaRecebimentos.getRowCount()-1, 4);
			else if(pagamento.getFormaPagamento() == 4)
				this.tabelaRecebimentos.setValueAt("Cheque de Terceiros", this.tabelaRecebimentos.getRowCount()-1, 4);
			else if(pagamento.getFormaPagamento() == 5)
				this.tabelaRecebimentos.setValueAt("Depósito Bancario", this.tabelaRecebimentos.getRowCount()-1, 4);
			else if(pagamento.getFormaPagamento() == 6)
				this.tabelaRecebimentos.setValueAt("Dinheiro", this.tabelaRecebimentos.getRowCount()-1, 4);
			else if(pagamento.getFormaPagamento() == 7)
				this.tabelaRecebimentos.setValueAt("Transferencia Bancária", this.tabelaRecebimentos.getRowCount()-1, 4);
			else if(pagamento.getFormaPagamento() == 8)
				this.tabelaRecebimentos.setValueAt("Outros", this.tabelaRecebimentos.getRowCount()-1, 4);
		}
		
		if(this.venda.getMelhorDia() != -1)
			textoMelhorDia.setText(String.valueOf(this.venda.getMelhorDia()));
		
		this.textoValorDesconto.setText(this.venda.getDesconto());
		this.textoPorcentagemDesconto.setText(this.venda.getPorcentagemDesconto().toString()+" %");
		this.calculaValorReceber();
		
		
		//Desabilita/Habilita Botões
		botaoSalvar.setEnabled(false);
		botaoAlterar.setEnabled(true);
		botaoExcluir.setEnabled(true);

		//Desabilita Campos
		this.habilitaCampos(false);
		
		controlVendas = null;
	}
	
	/*
	 * Método habilitaCampos
	 * Habilita/Desabilita Campos
	 */
	private void habilitaCampos(Boolean habilita)
	{
		this.textoCodigo.setEnabled(habilita);;
		this.textoData.setEnabled(habilita);
		this.textoHora.setEnabled(habilita);
		this.textoCliente.setEnabled(habilita);
		this.botaoPesquisarCliente.setEnabled(habilita);
		this.textoTipoEvento.setEnabled(habilita);
		this.botaoPesquisarTipoEvento.setEnabled(habilita);
		this.textoLocalEvento.setEnabled(habilita);
		this.botaoPesquisarLocalEvento.setEnabled(habilita);
		this.textoObs.setEnabled(habilita);
		this.tabelaProdutos.setEnabled(habilita);
		this.botaoProdutoInserir.setEnabled(habilita);
		this.botaoProdutoRemover.setEnabled(habilita);
		this.tabelaServicos.setEnabled(habilita);
		this.botaoServicosInserir.setEnabled(habilita);
		this.botaoServicosRemover.setEnabled(habilita);
		this.tabelaRecebimentos.setEnabled(habilita);
		this.textoParcelas.setEnabled(habilita);
		this.botaoRecebimentosInserir.setEnabled(habilita);
		this.botaoRecebimentosRemoverTodos.setEnabled(habilita);
		this.textoValorTotal.setEnabled(habilita);
		this.textoValorDesconto.setEnabled(habilita);
		this.textoPorcentagemDesconto.setEnabled(habilita);
		this.textoValorPago.setEnabled(habilita);
		this.textoValorReceber.setEnabled(habilita);
		this.textoMelhorDia.setEnabled(habilita);
		this.textoEntrada.setEnabled(habilita);
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
            java.util.logging.Logger.getLogger(Vendas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Vendas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Vendas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Vendas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /*
         * Create and display the form
         */
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new Vendas().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botaoAlterar;
    private javax.swing.JButton botaoCadastrarCliente;
    private javax.swing.JButton botaoCadastrarLocalEvento;
    private javax.swing.JButton botaoCadastrarTipoEvento;
    private javax.swing.JButton botaoCancelar;
    private javax.swing.JButton botaoExcluir;
    private javax.swing.JButton botaoPesquisar;
    private javax.swing.JButton botaoPesquisarCliente;
    private javax.swing.JButton botaoPesquisarLocalEvento;
    private javax.swing.JButton botaoPesquisarTipoEvento;
    private javax.swing.JButton botaoProdutoCadastrar;
    private javax.swing.JButton botaoProdutoInserir;
    private javax.swing.JButton botaoProdutoRemover;
    private javax.swing.JButton botaoRecebimentosInserir;
    private javax.swing.JButton botaoRecebimentosRemoverTodos;
    private javax.swing.JButton botaoSalvar;
    private javax.swing.JButton botaoServicosCadastrar;
    private javax.swing.JButton botaoServicosInserir;
    private javax.swing.JButton botaoServicosRemover;
    private javax.swing.JCheckBox checkPago;
    private javax.swing.JComboBox comboPagamento;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel labelCliente;
    private javax.swing.JLabel labelCodigo;
    private javax.swing.JLabel labelData;
    private javax.swing.JLabel labelHora;
    private javax.swing.JLabel labelLocalEvento;
    private javax.swing.JLabel labelNomeUsuario;
    private javax.swing.JLabel labelObs;
    private javax.swing.JLabel labelParcelas;
    private javax.swing.JLabel labelPorcentagemDesconto;
    private javax.swing.JLabel labelTipoEvento;
    private javax.swing.JLabel labelTotalReceber;
    private javax.swing.JLabel labelUsuarioAtivo;
    private javax.swing.JLabel labelValorDesconto;
    private javax.swing.JLabel labelValorPago;
    private javax.swing.JLabel labelValorTotal;
    private javax.swing.JTabbedPane painelAbas;
    private javax.swing.JPanel painelInternoProdutos;
    private javax.swing.JPanel painelInternoProdutos1;
    private javax.swing.JPanel painelInternoRecebimentos;
    private javax.swing.JPanel painelInternoServicos;
    private javax.swing.JScrollPane painelObs;
    private javax.swing.JPanel painelPesquisa;
    private javax.swing.JPanel painelProdutos;
    private javax.swing.JPanel painelRecebimentos;
    private javax.swing.JPanel painelServicos;
    private javax.swing.JScrollPane painelTabelaProdutos;
    private javax.swing.JScrollPane painelTabelaRecebimentos;
    private javax.swing.JScrollPane painelTabelaServicos;
    private javax.swing.JPanel painelVendas;
    private javax.swing.JTable tabelaProdutos;
    private javax.swing.JTable tabelaRecebimentos;
    private javax.swing.JTable tabelaServicos;
    private javax.swing.JTextField textoCliente;
    private javax.swing.JTextField textoCodigo;
    private com.toedter.calendar.JDateChooser textoData;
    private formattedFields.jTextMoeda textoEntrada;
    private formattedFields.JTextHora textoHora;
    private javax.swing.JTextField textoLocalEvento;
    private javax.swing.JFormattedTextField textoMelhorDia;
    private javax.swing.JTextArea textoObs;
    private javax.swing.JFormattedTextField textoParcelas;
    private javax.swing.JTextField textoPorcentagemDesconto;
    private javax.swing.JTextField textoTipoEvento;
    private formattedFields.jTextMoeda textoValorDesconto;
    private formattedFields.jTextMoeda textoValorPago;
    private formattedFields.jTextMoeda textoValorReceber;
    private formattedFields.jTextMoeda textoValorTabela;
    private formattedFields.jTextMoeda textoValorTotal;
    private formattedFields.JTextData txtVencimento;
    // End of variables declaration//GEN-END:variables
    //Janelas
    private CadastroClientes				janelaCadastroClientes;
    private CadastroLocaisEventos			janelaCadastroLocaisEventos;
    private CadastroTipoEvento				janelaCadastroTipoEvento;
    private CadastroProdutos				janelaCadastroProdutos;
    private CadastroServicos				janelaCadastroServicos;
	private ProcuraClientes					janelaProcuraClientes;
	private ProcuraLocaisEvento				janelaProcuraLocaisEvento;
	private ProcuraProduto					janelaProcuraProduto;
	private ProcuraServico					janelaProcuraServico;
	private ProcuraTiposEvento				janelaProcuraTiposEvento;
	private ProcuraVendas					janelaProcuraVendas;
	private ErroCampoEmBranco				janelaErroCampoEmBranco;
	private EstudioFotografico				estudio;

	//Controladores
	private ControladorClientes				controlClientes;
	private ControladorEventos				controladorEventos;
	private ControladorBancodeDados			controlBD;
	private ControladorVendas				controlVendas;
	private ControladorProdutos				controlProdutos;
	private ControladorServicos				controlServicos;
	
	//Variaveis
	private	Clientes						cliente;
	private LocaisEventos					localEvento;
	private TiposEventos					tipoEvento;
	public  Long							codigo;
	private estudiofotografico.model.Vendas	venda;
}
