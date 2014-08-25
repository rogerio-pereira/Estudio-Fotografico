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


import estudiofotografico.model.Fornecedores;
import estudiofotografico.view.erros.ErroBuscar;
import estudiofotografico.view.janelas.CadastroFornecedores;
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
public class ControladorFornecedores 
{
	/*
	 * Método getFornecedorByCodigo
	 * Obtem o Fornecedor pelo Código
	 */
	public Fornecedores getFornecedorByCodigo(Long codigo)
    {
        try
        {
            this.factory = Persistence.createEntityManagerFactory	(	
																		"estudioFotografico", 
																		new ControladorBancodeDados().getConfigBD()
																	);
            this.manager = this.factory.createEntityManager();
            
            this.fornecedor = manager.find(Fornecedores.class, codigo);
            
            return this.fornecedor;
        }
        catch (Exception e)
        {
            this.erroBuscar = new ErroBuscar(this.cadastroFornecedor, true);
            this.erroBuscar.setVisible(true);
            return null;
        }
    }
	
	/*
	 * Método getClientes
	 * Obtem o cliente
	 */
	public List<Fornecedores> getFornecedores(Long codigo, String nome, String cpf, String cnpj)
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
			
			String qr = "Select f FROM Fornecedores f WHERE 1=1 " + filtro + " ORDER BY f.codigo";

			//Obtendo o Codigo do item
			Query query = this.manager.createQuery(qr);
			this.fornecedores = query.getResultList();
			return this.fornecedores;
		}
		catch (Exception e)
		{
			return null;
		}
	}
	
	private EntityManagerFactory			factory;
    private EntityManager					manager;
	
	private ErroBuscar						erroBuscar;
	private CadastroFornecedores			cadastroFornecedor;
	
	private Fornecedores					fornecedor;
	private List<Fornecedores>				fornecedores;
}
