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


import estudiofotografico.model.Clientes;
import estudiofotografico.view.erros.ErroBuscar;
import estudiofotografico.view.janelas.CadastroClientes;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;


/**
 *
 * @author Rogério Eduardo Pereira
 * Data Criação: Mar 5, 2014
 */
public class ControladorClientes 
{
	/*
     * Método getClientesByCodigo
     * Obtem o cliente atraves do codigo
     */
    public Clientes getClientesByCodigo(Long codigo)
    {
        try
        {
            this.factory = Persistence.createEntityManagerFactory	(	
																		"estudioFotografico", 
																		new ControladorBancodeDados().getConfigBD()
																	);
            this.manager = this.factory.createEntityManager();
            
            this.cliente = manager.find(Clientes.class, codigo);
            
            return this.cliente;
        }
        catch (Exception e)
        {
            this.erroBuscar = new ErroBuscar(this.cadastroClientes, true);
            this.erroBuscar.setVisible(true);
            return null;
        }
    }
	
	/*
	 * Método getClientes
	 * Obtem o cliente
	 */
	public List<Clientes> getClientes(Long codigo, String nome, String cpf, String cnpj)
	{
		String filtro = new String();
		try
		{
			if(codigo != null)
				filtro += " AND codigo = "+codigo;
			if(nome != null)
				filtro += " AND nome LIKE '%"+nome+"%'";
			if(cpf != null)
				filtro += " AND cpf = '" + cpf +"'";
			if(cnpj != null)
				filtro += " AND cnpj = " + cnpj + "'";
			
			this.factory = Persistence.createEntityManagerFactory	(	
																		"estudioFotografico", 
																		new ControladorBancodeDados().getConfigBD()
																	);
			this.manager = this.factory.createEntityManager();
			
			String qr = "Select c FROM Clientes c WHERE 1=1 " + filtro + " ORDER BY c.codigo";

			//Obtendo o Codigo do item
			Query query = this.manager.createQuery(qr);
			this.clientes = query.getResultList();
			return this.clientes;
		}
		catch (Exception e)
		{
			return null;
		}
	}
	
	/*
	 * Método getClientesAgenda
	 * Obtem lista de clientes
	 */
	public List<Clientes> getClientesAgenda(String nome, String pessoa, String cpf, String cnpj)
	{
		String filtro = new String();
		try
		{
			if(nome != null)
				filtro += " AND nome LIKE '%"+nome+"%'";
			if(pessoa != null)
				filtro += " AND pessoa ="+pessoa;
			if(cpf != null)
				filtro += " AND cpf = '" + cpf +"'";
			if(cnpj != null)
				filtro += " AND cnpj = " + cnpj + "'";
			
			this.factory = Persistence.createEntityManagerFactory	(	
																		"estudioFotografico", 
																		new ControladorBancodeDados().getConfigBD()
																	);
			this.manager = this.factory.createEntityManager();
			
			String qr = "Select c FROM Clientes c WHERE 1=1 " + filtro + " ORDER BY c.codigo";

			//Obtendo o Codigo do item
			Query query = this.manager.createQuery(qr);
			this.clientes = query.getResultList();
			return this.clientes;
		}
		catch (Exception e)
		{
			return null;
		}
	}
	
	private EntityManagerFactory			factory;
    private EntityManager					manager;
	
	private ErroBuscar						erroBuscar;
	private CadastroClientes				cadastroClientes;
	
	private Clientes						cliente;
	private List<Clientes>					clientes;
}
