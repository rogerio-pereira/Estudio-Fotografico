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


import estudiofotografico.model.Usuario;
import estudiofotografico.view.erros.ErroLogin;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;


/**
 *
 * @author Rogério Eduardo Pereira
 * Data Criação: Feb 19, 2014
 */
public class Login extends Usuario
{
	public Usuario ValidaLogin(String usuario, String senha)
	{
		try
		{
			this.factory = Persistence.createEntityManagerFactory	(	
																		"estudioFotografico", 
																		new ControladorBancodeDados().getConfigBD()
																	);
			this.manager		= factory.createEntityManager();
			String usuarioBD	= "";
			String senhaBD		= "";

			Query query = manager.createNamedQuery("Usuario.login", Usuario.class);
			query.setParameter(1, usuario);
			Usuario		user	= (Usuario)query.getSingleResult();
			usuarioBD			= user.getUsuario();
			senhaBD				= user.getSenha();
			
			if((usuario.equals(usuarioBD)) && (senha.equals(senhaBD)))
				return  user;
			else
			{
				janeErroLogin = new ErroLogin(janeLogin, true);
				janeErroLogin.setVisible(true);
				return null;
			}
		}
		catch(Exception e)
		{
			janeErroLogin = new ErroLogin(janeLogin, true);
			janeErroLogin.setVisible(true);
			return null;
		}
			
	}
	
	
	private EntityManagerFactory	factory;
    private EntityManager			manager;
	
	private estudiofotografico.view.erros.ErroLogin janeErroLogin;
	private estudiofotografico.view.janelas.Login	janeLogin;
}
