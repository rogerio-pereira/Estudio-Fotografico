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


import estudiofotografico.model.LocaisEventos;
import estudiofotografico.model.TelefonesLocaisEventos;
import estudiofotografico.model.TiposEventos;
import estudiofotografico.view.erros.ErroBuscar;
import estudiofotografico.view.janelas.CadastroLocaisEventos;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;


/**
 *
 * @author Rogério Eduardo Pereira
 * Data Criação: Feb 26, 2014
 */
public class ControladorEventos 
{
	/*
	 * Método getCategorias
	 * Obtem o nome de todas as categorias cadastradas
	 */
	public List<String> getCategorias()
	{
		this.factory = Persistence.createEntityManagerFactory	(	
																	"estudioFotografico", 
																	new ControladorBancodeDados().getConfigBD()
																);
        this.manager			= factory.createEntityManager();
		
		Query query				= manager.createNamedQuery("TiposEventos.findAll");
		this.tiposString		= query.getResultList();
		
		return this.tiposString;
	}
	
	/*
	 * Método getCodigoByTipo
	 * Obtem o código da Categoria com o nome passado por paremetros
	 */
	public Long getCodigoByTipo(String categoria)
	{
		this.factory = Persistence.createEntityManagerFactory	(	
																	"estudioFotografico", 
																	new ControladorBancodeDados().getConfigBD()
																);
		this.manager = this.factory.createEntityManager();

		//Obtendo o Codigo do item
		TypedQuery<Long> query = manager.createNamedQuery("TiposEventos.findOne", Long.class);
		query.setParameter(0, categoria);
		
		this.codigoTipoEvento = query.getSingleResult();
		
		return this.codigoTipoEvento;
	}
	
	/*
     * Método getProdutosByCodigo
     * Obtem o produto atraves do codigo
     */
    public LocaisEventos getLocalByCodigo(Long codigo)
    {
        try
        {
            this.factory = Persistence.createEntityManagerFactory	(	
																		"estudioFotografico", 
																		new ControladorBancodeDados().getConfigBD()
																	);
            this.manager = this.factory.createEntityManager();
            
            this.local = manager.find(LocaisEventos.class, codigo);
            
            return this.local;
        }
        catch (Exception e)
        {
            this.erroBuscar = new ErroBuscar(this.cadastroLocaisEventos, true);
            this.erroBuscar.setVisible(true);
            return null;
        }
    }
	
	/*
	 * Método getLocaisEventoBusca
	 * Retorna os Locais de Evento de acordo com o filtro
	 */
	public List<LocaisEventos> getLocaisEventoBusca(Long codigo, String nome, String responsavel, String site)
	{
		String filtro = new String();
		try
		{
			if(codigo != null)
				filtro += " AND codigo = "+codigo;
			if(nome != null)
				filtro += " AND produto LIKE '%"+nome+"%'";
			if(responsavel != null)
				filtro += " AND responsavel LIKE '%"+responsavel+"%'";
			if(site != null)
				filtro += " AND sie LIKE '%"+site+"%'";
			
			this.factory = Persistence.createEntityManagerFactory	(	
																		"estudioFotografico", 
																		new ControladorBancodeDados().getConfigBD()
																	);
			this.manager = this.factory.createEntityManager();
			
			String qr = "Select l FROM LocaisEventos l WHERE 1=1 " + filtro + " ORDER BY l.codigo";

			//Obtendo o Codigo do item
			Query query = this.manager.createQuery(qr);
			this.locais = query.getResultList();
			return this.locais;
		}
		catch(Exception e)
		{
			return null;
		}
	}
	
	/*
	 * Método getFornecedorAgenda
	 * Obtem lista de locais de Eventos
	 */
	public List<LocaisEventos> getLocaisEventoAgenda(String nome)
	{
		String filtro = new String();
		try
		{
			if(nome != null)
				filtro += " AND nome LIKE '%"+nome+"%'";
			
			this.factory = Persistence.createEntityManagerFactory	(	
																		"estudioFotografico", 
																		new ControladorBancodeDados().getConfigBD()
																	);
			this.manager = this.factory.createEntityManager();
			
			String qr = "Select l FROM LocaisEventos l WHERE 1=1 " + filtro + " ORDER BY l.codigo";

			//Obtendo o Codigo do item
			Query query = this.manager.createQuery(qr);
			this.locais = query.getResultList();
			return this.locais;
		}
		catch (Exception e)
		{
			return null;
		}
	}
	
	/*
	 * Método getLocaisEventoBusca
	 * Retorna os Locais de Evento de acordo com o filtro
	 */
	public List<TiposEventos> getTiposEventoBusca(Long codigo, String nome)
	{
		String filtro = new String();
		try
		{
			if(codigo != null)
				filtro += " AND codigo = "+codigo;
			if(nome != null)
				filtro += " AND tipo LIKE '%"+nome+"%'";
			
			this.factory = Persistence.createEntityManagerFactory	(	
																		"estudioFotografico", 
																		new ControladorBancodeDados().getConfigBD()
																	);
			this.manager = this.factory.createEntityManager();
			
			String qr = "Select t FROM TiposEventos t WHERE 1=1 " + filtro + " ORDER BY t.codigo";

			//Obtendo o Codigo do item
			Query query = this.manager.createQuery(qr);
			this.tipos = query.getResultList();
			return this.tipos;
		}
		catch(Exception e)
		{
			return null;
		}
	}
	
	/*
	 * Método getTiposEventosByCod
	 * Obtem o Tipo de Evento de Acordo com o codigo
	 */
	public TiposEventos getTiposEventosByCod(Long codigo)
	{
		try
        {
            this.factory = Persistence.createEntityManagerFactory	(	
																		"estudioFotografico", 
																		new ControladorBancodeDados().getConfigBD()
																	);
            this.manager = this.factory.createEntityManager();
            
            this.tipo = manager.find(TiposEventos.class, codigo);
            
            return this.tipo;
        }
        catch (Exception e)
        {
            this.erroBuscar = new ErroBuscar(this.cadastroLocaisEventos, true);
            this.erroBuscar.setVisible(true);
            return null;
        }
	}
	
	/*
     * Método getTipoEventos
     * Retorna o nome dos tipos de equipamentos
     */
    public List<String> getTiposEventos() 
	{
		List<String>	tipoString;
		this.factory = Persistence.createEntityManagerFactory	(	
																	"estudioFotografico", 
																	new ControladorBancodeDados().getConfigBD()
																);
        this.manager = factory.createEntityManager();
		
		Query query = manager.createNamedQuery("TiposEventos.findAll");
		tiposString = query.getResultList();
		
		return tiposString;
	}
	
	private EntityManagerFactory			factory;
    private EntityManager					manager;
	
	private List<String>					tiposString;
	private List<Long>						telefonesCodigos;
	private List<Long>						emailsCodigos;
	private List<TelefonesLocaisEventos>	telefones;
	private List<LocaisEventos>				locais;
	private List<TiposEventos>				tipos;
	private Long							codigoTipoEvento;
	private LocaisEventos					local;
	private TiposEventos					tipo;
	
	private ErroBuscar						erroBuscar;
	private CadastroLocaisEventos			cadastroLocaisEventos;
}
