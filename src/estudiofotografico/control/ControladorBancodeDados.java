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
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package estudiofotografico.control;


import estudiofotografico.model.CategoriasProdutos;
import estudiofotografico.model.CategoriasServicos;
import estudiofotografico.model.Clientes;
import estudiofotografico.model.EmailsLocaisEventos;
import estudiofotografico.model.Equipamentos;
import estudiofotografico.model.Fornecedores;
import estudiofotografico.model.LocaisEventos;
import estudiofotografico.model.MarcasEquipamentos;
import estudiofotografico.model.Produtos;
import estudiofotografico.model.Empresa;
import estudiofotografico.model.Servicos;
import estudiofotografico.model.TelefonesLocaisEventos;
import estudiofotografico.model.TiposEquipamentos;
import estudiofotografico.model.TiposEventos;
import estudiofotografico.model.Usuario;
import estudiofotografico.model.Vendas;
import estudiofotografico.view.erros.ErroAbrirConexao;
import estudiofotografico.view.erros.ErroApagar;
import estudiofotografico.view.erros.ErroAtualizar;
import estudiofotografico.view.erros.ErroBuscar;
import estudiofotografico.view.erros.ErroInserir;
import estudiofotografico.view.erros.SucessoAlterar;
import estudiofotografico.view.erros.SucessoApagar;
import estudiofotografico.view.erros.SucessoInserir;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.JFrame;


/**
 *
 * @author Rogério Eduardo Pereira
 * Data Criação: Feb 25, 2014
 */
public class ControladorBancodeDados 
{
	/*
	 * Método criaTabelas
	 * Cria as tabelas do banco
	 */
	public Boolean criaTabelas()
	{
		try
        {
            this.factory    = Persistence.createEntityManagerFactory("estudioFotografico", this.getConfigBD()); 
            factory.close();
            return true;
        }
        catch(Exception e)
        {
            return false;
        }
	}
	
	/*
	 * Método cadastrar
	 * Cadastra o objeto no banco de dados
	 */
	public Boolean cadastrar(Object o, JFrame janela)
	{
		try
        {
			this.factory    = Persistence.createEntityManagerFactory("estudioFotografico", this.getConfigBD());
            this.manager    = factory.createEntityManager();

            this.manager.getTransaction().begin();
            
            this.manager.persist(o);

            this.manager.getTransaction().commit();

            this.manager.close();
            this.factory.close();
            
			sucessoInserir = new SucessoInserir(janela, true);
			sucessoInserir.setVisible(true);
			
            return true;
        }
        catch (Exception e)
        {
			System.out.println(e.getCause());
			System.out.println(e.getMessage());
            erroInserir = new ErroInserir(janela, true);
            erroInserir.setVisible(true);
            return false;
        }
	}
	
	/*
	 * Método atualiza
	 * Atualiza o objeto no banco de dados
	 */
	public Boolean atualizar(Object o, JFrame janela)
	{
		try
        {
			this.factory    = Persistence.createEntityManagerFactory("estudioFotografico", this.getConfigBD());
            this.manager     = factory.createEntityManager();

            this.manager.getTransaction().begin();
            
			this.manager.merge(o);

            this.manager.getTransaction().commit();

            this.manager.close();
            this.factory.close();
			
			sucessoAlterar = new SucessoAlterar(janela, true);
			sucessoAlterar.setVisible(true);
            
            return true;
        }
        catch (Exception e)
        {
			System.out.println(e.getMessage());
            erroAtualizar = new ErroAtualizar(janela, true);
            erroAtualizar.setVisible(true);
            return false;
        }
	}
	
	/*
	 * Método apagar
	 * Apaga o objeto do banco de dados
	 * PARAMETROS
	 *		Long	codigo	= Codigo do objeto que sera buscado
	 *		Object	o		= Objeto que sera deletado
	 *		JFrame	janela	= janela que chamou o método
	 *							Usado em caso de erro para setar a janela de erro como modal
	 */
	public Boolean apagar(Long codigo, Object o,  JFrame janela)
	{
		try
        {
            this.factory    = Persistence.createEntityManagerFactory("estudioFotografico", this.getConfigBD());
			this.manager = this.factory.createEntityManager();
			
			this.manager.getTransaction().begin();
			
			//Apagando
			o = manager.find(o.getClass(), codigo);
			this.manager.remove(o);
			
			this.manager.getTransaction().commit();

			this.manager.close();
			this.factory.close();
            
			sucessoApagar = new SucessoApagar(janela, true);
			sucessoApagar.setVisible(true);
			
			return true;
        }
        catch (Exception e)
        {
			System.out.println(e.getMessage());
			erroApagar = new ErroApagar(janela, true);
            erroApagar.setVisible(true);
            return false;
        }
	}
	
	/*
	 * Método getConfigBD
	 * Obtem as configuracoes do Banco de dados
	 */
	public HashMap getConfigBD()
	{
		Map			cfg			= new HashMap();
		Properties	prop		= new Properties();
		InputStream input		= null;
		String		show_sql	= new String();
		String		user		= new String();
		String		password	= new String();
		String		server		= new String();
		String		port		= new String();
		String		database	= new String();
		
		try
		{
			//Abre arquivo de configurações
			input = new FileInputStream("config/bd.config.properties");
			
			//Obtem as informações do arquivo
			prop.load(input);
			
			//Seta as propriedades
			show_sql	= prop.getProperty("show_sql");
			user		= prop.getProperty("user");
			password	= prop.getProperty("password");
			server		= prop.getProperty("server");
			port		= prop.getProperty("port");
			database	= prop.getProperty("database");
			
			//Mapeia as propriedades
			cfg.put("hibernate.show_sql",				show_sql);
			cfg.put("javax.persistence.jdbc.user",		user);
			cfg.put("javax.persistence.jdbc.password",	password);
			cfg.put("javax.persistence.jdbc.url",		"jdbc:mysql://"+server+":"+port+"/"+database);
		}
		catch (IOException ex)
		{
			return null;
		}
		finally
		{
			if (input != null) 
			{
				try 
				{
					input.close();
				} 
				catch (IOException e) 
				{
					return null;
				}
			}
		}
		
		return (HashMap)cfg;
	}
	
	public Connection getConexaoReport()
	{
		Properties	prop		= new Properties();
		InputStream	input	= null;
		String		user		= new String();
		String		password	= new String();
		String		server	= new String();
		String		port		= new String();
		String		database	= new String();
		
		try
		{
			//Abre arquivo de configurações
			input = new FileInputStream("config/bd.config.properties");
			
			//Obtem as informações do arquivo
			prop.load(input);
			
			//Seta as propriedades
			user		= prop.getProperty("user");
			password	= prop.getProperty("password");
			server	= prop.getProperty("server");
			port		= prop.getProperty("port");
			database	= prop.getProperty("database");
			
			Class.forName("com.mysql.jdbc.Driver");
			Connection conexao = null;
			conexao = DriverManager.getConnection("jdbc:mysql://"+server+":"+port+"/"+database+"",user, password);
			return conexao;
		}
		catch(Exception e)
		{
			return null;
		}
	}
	
	private Map						config;
	private EntityManagerFactory	factory;
    private EntityManager			manager;
	
	private ErroAbrirConexao        erroAbrirConexao;
    private ErroInserir             erroInserir;
	private ErroAtualizar			erroAtualizar;
	private ErroApagar              erroApagar;
    private ErroBuscar              erroBuscar;
	private SucessoInserir			sucessoInserir;
	private SucessoAlterar			sucessoAlterar;
	private SucessoApagar			sucessoApagar;
	
	//Equipamento
	public	MarcasEquipamentos		marcasEquipamento;
	public	TiposEquipamentos		tiposEquipamentos;
	public	Equipamentos			equipamentos;
	//Produtos
	public	CategoriasProdutos		categoriasProdutos;
	public	Produtos				produtos;
	//Servicos
	public CategoriasServicos		categoriasServicos;
	public Servicos					servicos;
	//Eventos
	public TiposEventos				tiposEventos;
	public LocaisEventos			locaisEventos;
	public TelefonesLocaisEventos	telefonesLocaisEventos;
	public EmailsLocaisEventos		emailsLocaisEventos;
	//Contatos
	public Clientes					clientes;
	public Fornecedores				fornecedores;
	//Vendas
	public Vendas					vendas;
	//Usuario
	public Usuario					usuario;
	//Empresa
	public Empresa					empresa;
}
