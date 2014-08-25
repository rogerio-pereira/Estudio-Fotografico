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


import estudiofotografico.view.erros.ErroBuscar;
import estudiofotografico.view.janelas.MalaDireta;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;


/**
 *
 * @author Rogério Eduardo Pereira
 * Data Criação: Mar 6, 2014
 */
public class ControladorMalaDireta 
{
	/*
	 * Método getEmailsMalaDireta
	 * Obtem os emails de Clientes Fisicos
	 */
	public String getEmailsMalaDireta(Boolean fisico, Boolean juridico, Boolean fornecedor, Boolean locais)
	{
		try
		{
			this.emails		= new String("");
			this.factory = Persistence.createEntityManagerFactory	(	
																		"estudioFotografico", 
																		new ControladorBancodeDados().getConfigBD()
																	);
			this.manager	= factory.createEntityManager();

			//Fisico
			if(fisico)
			{
				Query query		= manager.createNamedQuery("EmailsClientesFisicos.findAll");
				this.ListEmails	= query.getResultList();

				for(String email: this.ListEmails)
					this.emails += email+"; ";
			}
			//Juridico
			if(juridico)
			{
				Query query		= manager.createNamedQuery("EmailsClientesJuridicos.findAll");
				this.ListEmails	= query.getResultList();

				for(String email: this.ListEmails)
					this.emails += email+"; ";
			}
			//Fornecedores
			if(fornecedor)
			{
				Query query		= manager.createNamedQuery("EmailsFornecedores.findAll");
				this.ListEmails	= query.getResultList();

				for(String email: this.ListEmails)
					this.emails += email+"; ";
			}
			//Locais
			if(locais)
			{
				Query query		= manager.createNamedQuery("EmailsLocais.findAll");
				this.ListEmails	= query.getResultList();

				for(String email: this.ListEmails)
					this.emails += email+"; ";
			}

			return this.emails;
		}
		catch(Exception e)
		{
			this.erroBuscar = new ErroBuscar(this.janelaMalaDireta, true);
            this.erroBuscar.setVisible(true);
            return null;
		}
	}
	
	private EntityManagerFactory	factory;
    private EntityManager			manager;
	
	private ErroBuscar				erroBuscar;
	
	private String					emails;
	private List<String>			ListEmails;
	private MalaDireta				janelaMalaDireta;
}
