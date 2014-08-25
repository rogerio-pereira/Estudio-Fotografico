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


import estudiofotografico.model.CategoriasServicos;
import estudiofotografico.model.Servicos;
import estudiofotografico.view.erros.ErroBuscar;
import estudiofotografico.view.janelas.CadastroServicos;
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
public class ControladorServicos 
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
		
		Query query				= manager.createNamedQuery("CategoriaServicos.findAll");
		this.categoriasString	= query.getResultList();
		
		return this.categoriasString;
	}
	
	/*
	 * Método getCodigoByCategoria
	 * Obtem o código da Categoria com o nome passado por paremetros
	 */
	public Long getCodigoByCategoria(String categoria)
	{
		this.factory = Persistence.createEntityManagerFactory	(	
																	"estudioFotografico", 
																	new ControladorBancodeDados().getConfigBD()
																);
		this.manager = this.factory.createEntityManager();

		//Obtendo o Codigo do item
		TypedQuery<Long> query = manager.createNamedQuery("CategoriaServicos.findOne", Long.class);
		query.setParameter(0, categoria);
		
		this.codigoCategoria = query.getSingleResult();
		
		return this.codigoCategoria;
	}
	
	/*
	 * Método getCategoriaByNome
	 * Obtem a categoria de acordo com o nome
	 */
	public CategoriasServicos getCategoriaByNome(String nome)
	{
		try
        {
            this.factory = Persistence.createEntityManagerFactory	(	
																		"estudioFotografico", 
																		new ControladorBancodeDados().getConfigBD()
																	);
            this.manager = this.factory.createEntityManager();
			
			Long codigo = this.getCodigoByCategoria(nome);
            
            this.categoria = manager.find(CategoriasServicos.class, codigo);
            
            return this.categoria;
        }
        catch (Exception e)
        {
            this.erroBuscar = new ErroBuscar(this.cadastroServicos, true);
            this.erroBuscar.setVisible(true);
            return null;
        }
	}
	
	/*
     * Método getServicoByCodigo
     * Obtem o produto atraves do codigo
     */
    public Servicos getServicoByCodigo(Long codigo)
    {
        try
        {
            this.factory = Persistence.createEntityManagerFactory	(	
																		"estudioFotografico", 
																		new ControladorBancodeDados().getConfigBD()
																	);
            this.manager = this.factory.createEntityManager();
            
            this.servico = manager.find(Servicos.class, codigo);
            
            return this.servico;
        }
        catch (Exception e)
        {
            this.erroBuscar = new ErroBuscar(this.cadastroServicos, true);
            this.erroBuscar.setVisible(true);
            return null;
        }
    }
	
	/*
	 * Método getServicosBusca
	 * Retorna os produtos de acordo com o filtro
	 */
	public List<Servicos> getServicosBusca(Long codigo, String nome, String categoria)
	{
		String filtro = new String();
		try
		{
			if(codigo != null)
				filtro += " AND codigo = "+codigo;
			if(nome != null)
				filtro += " AND servico LIKE '%"+nome+"%'";
			if(categoria != null)
			{
                this.getCodigoByCategoria(categoria);
				filtro += " AND categoria_codigo = " +this.codigoCategoria;
			}
			
			this.factory = Persistence.createEntityManagerFactory	(	
																		"estudioFotografico", 
																		new ControladorBancodeDados().getConfigBD()
																	);
			this.manager = this.factory.createEntityManager();
			
			String qr = "Select s FROM Servicos s WHERE 1=1 " + filtro + " ORDER BY s.codigo";
			
			System.out.println(qr);

			//Obtendo o Codigo do item
			Query query = this.manager.createQuery(qr);
			this.servicos = query.getResultList();
			return this.servicos;
		}
		catch(Exception e)
		{
			return null;
		}
	}
	
	private EntityManagerFactory	factory;
    private EntityManager			manager;
	
	private List<String>			categoriasString;
	private List<Servicos>			servicos;
	private Long					codigoCategoria;
	public	CategoriasServicos		categoria;
	public  Servicos				servico;
	
	private ErroBuscar				erroBuscar;
	private CadastroServicos		cadastroServicos;
}
