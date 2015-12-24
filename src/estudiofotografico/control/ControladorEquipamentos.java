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


import estudiofotografico.model.Equipamentos;
import estudiofotografico.model.MarcasEquipamentos;
import estudiofotografico.model.TiposEquipamentos;
import estudiofotografico.view.erros.ErroAbrirConexao;
import estudiofotografico.view.erros.ErroApagar;
import estudiofotografico.view.erros.ErroBuscar;
import estudiofotografico.view.erros.ErroInserir;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.swing.JFrame;


/**
 *
 * @author Rogério Eduardo Pereira
 * Data Criação: Feb 25, 2014
 */
public class ControladorEquipamentos 
{
    /*
	 * Método getTipoEquipamento
	 * Obtem todos os tipos equipamentos
	 */
	public List<String> getTipoEquipamento()
	{
		List<String> l = this.getTipoEquipamentos();
		return l;
	}
	
	/*
	 * Método getMarcaEquipamento
	 * Obtem todos os tipos equipamentos
	 */
	public List<String> getMarcaEquipamento()
	{
		this.factory = Persistence.createEntityManagerFactory	(	
																	"estudioFotografico", 
																	new ControladorBancodeDados().getConfigBD()
																);
        this.manager = factory.createEntityManager();
		
		Query query = manager.createNamedQuery("MarcaEquipamento.findAll");
		this.marcasString = query.getResultList();
		
		return this.marcasString;
	}
	
	public List<Equipamentos> getEquipamentos(Long codigo, String nome, String marca, String tipo, Long codFornecedor)
	{
		String filtro = new String();
		try
		{
			if(codigo != null)
				filtro += " AND codigo = "+codigo;
			if(nome != null)
				filtro += " AND equipamento LIKE '%"+nome+"%'";
			if(marca != null)
			{
                this.getCodigoByMarca(marca);
				filtro += " AND marca_codigo = " +this.codigoMarca;
			}
			if(tipo != null)
			{
                this.getCodigoByTipo(tipo);
				filtro += " AND tipo_codigo = " +this.codigoTipo;
			}
			if(codFornecedor != null)
				filtro += " AND fornecedor_codigo = "+codFornecedor;
			
			this.factory = Persistence.createEntityManagerFactory	(	
																		"estudioFotografico", 
																		new ControladorBancodeDados().getConfigBD()
																	);
			this.manager = this.factory.createEntityManager();
			
			String qr = "Select e FROM Equipamentos e WHERE 1=1 " + filtro + " ORDER BY e.codigo";

			//Obtendo o Codigo do item
			Query query = this.manager.createQuery(qr);
			equipamentos = query.getResultList();
			return equipamentos;
		}
		catch (Exception e)
		{
			return null;
		}
	}
    
    /*
     * Método getEquipamentoByCodigo
     * Obtem o equipamente atraves do codigo
     */
    public Equipamentos getEquipamentoByCodigo(Long codigo)
    {
        try
        {
            this.factory = Persistence.createEntityManagerFactory	(	
																		"estudioFotografico", 
																		new ControladorBancodeDados().getConfigBD()
																	);
            this.manager = this.factory.createEntityManager();
            
            this.equipamento = manager.find(Equipamentos.class, codigo);
            
            return this.equipamento;
        }
        catch (Exception e)
        {
            this.erroBuscar = new ErroBuscar(this.cadastraEquipamento, true);
            this.erroBuscar.setVisible(true);
            return null;
        }
    }
    
    /*
	 * Método getMarcasEquipamentos
	 * Obtem o item marcaEquipamento
	 */
	public MarcasEquipamentos getMarcasEquipamentos(String marca)
	{
		//Obtendo Marca
		Long codigo = this.getCodigoByMarca(marca);
		this.marca = manager.find(MarcasEquipamentos.class, this.codigoMarca);
		
		return this.marca;
	}
    
    /*
	 * Método getCodigoByMarca
	 * Obtem o codigo do equipemento pela marca
	 */
	public Long getCodigoByMarca(String marca)
	{
		this.factory = Persistence.createEntityManagerFactory	(	
																	"estudioFotografico", 
																	new ControladorBancodeDados().getConfigBD()
																);
		this.manager = this.factory.createEntityManager();

		//Obtendo o Codigo do item
		TypedQuery<Long> query = this.manager.createNamedQuery("MarcaEquipamento.findOne", Long.class);
		query.setParameter(0, marca);
		
		this.codigoMarca = query.getSingleResult();
		
		return this.codigoMarca;
	}
    
    /*
     * Método getTipoEquipamentos
     * Retorna o nome dos tipos de equipamentos
     */
    public List<String> getTipoEquipamentos() 
	{
		this.factory = Persistence.createEntityManagerFactory	(	
																	"estudioFotografico", 
																	new ControladorBancodeDados().getConfigBD()
																);
        this.manager = factory.createEntityManager();
		
		Query query = manager.createNamedQuery("TipoEquipamento.findAll");
		this.tiposString = query.getResultList();
		
		return tiposString;
	}
    
    /*
	 * Método getMarcasEquipamentos
	 * Obtem o item marcaEquipamento
	 */
	public TiposEquipamentos getTipoEquipamentos(String tipo)
	{
		Long codigo = this.getCodigoByTipo(tipo);
		
		this.tipo = manager.find(TiposEquipamentos.class, codigo);
		
		return this.tipo;
	}
    
    /*
	 * Método getCodigoByMarca
	 * Obtem o codigo do equipemento pela marca
	 */
	public Long getCodigoByTipo(String tipo)
	{
		this.factory = Persistence.createEntityManagerFactory	(	
																	"estudioFotografico", 
																	new ControladorBancodeDados().getConfigBD()
																);
		this.manager = this.factory.createEntityManager();

		//Obtendo o Codigo do item
		TypedQuery<Long> query = manager.createNamedQuery("TipoEquipamento.findOne", Long.class);
		query.setParameter(0, tipo);
		
		this.codigoTipo = query.getSingleResult();
		
		return this.codigoTipo;
	}
	
	/*
	 * método findMarcaByCodigo
	 * Busca Marca de Equipamentos pelo Codigo
	 */
	public void findMarcaEquipamentoByCodigo(Long codigo, JFrame janela)
	{
		try
		{
			this.factory = Persistence.createEntityManagerFactory	(	
																		"estudioFotografico", 
																		new ControladorBancodeDados().getConfigBD()
																	);
            this.manager = this.factory.createEntityManager();
            
            this.marca = manager.find(MarcasEquipamentos.class, codigo);
		}
		catch (Exception e)
		{
			erroBuscar = new ErroBuscar(janela, true);
            erroBuscar.setVisible(true);	
		}
	}
    
    public  Long                                                        codigo;
    public  Long                                                        codigoMarca;
    public  Long                                                        codigoTipo;
    
    public  Equipamentos                                                equipamento;
    public  MarcasEquipamentos                                          marca;
    public  TiposEquipamentos                                           tipo;
    
    private List<String>                                                marcasString;
    private List<Equipamentos>                                          equipamentos;
    private List<String>                                                tiposString;
	
	private EntityManagerFactory                                        factory;
    private EntityManager                                               manager;
    
    private estudiofotografico.view.janelas.CadastroEquipamentos        cadastraEquipamento;
    private estudiofotografico.view.janelas.CadastroTipoEquipamentos    cadastraTipoEquipamento;
    private estudiofotografico.view.janelas.CadastroMarcasEquipamentos  cadastroMarcaEquipamento;
    private ErroAbrirConexao                                            erroAbrirConexao;
    private ErroInserir                                                 erroInserir;
	private ErroApagar                                                  erroApagar;
    private ErroBuscar                                                  erroBuscar;
}
