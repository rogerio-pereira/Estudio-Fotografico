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


import estudiofotografico.model.Vendas;
import estudiofotografico.view.erros.ErroBuscar;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;


/**
 *
 * @author Rogério Eduardo Pereira
 * Data Criação: Mar 10, 2014
 */
public class ControladorVendas 
{
	/*
     * Método getVendasByCodigo
     * Obtem a venda através do Código
     */
    public Vendas getVendasByCodigo(Long codigo)
    {
        try
        {
            this.factory = Persistence.createEntityManagerFactory	(	
																		"estudioFotografico", 
																		new ControladorBancodeDados().getConfigBD()
																	);
            this.manager = this.factory.createEntityManager();
            
            this.venda = manager.find(Vendas.class, codigo);
            
            return this.venda;
        }
        catch (Exception e)
        {
            this.erroBuscar = new ErroBuscar(this.janelaVendas, true);
            this.erroBuscar.setVisible(true);
            return null;
        }
    }
	
	/*
	 * Método getVendasBusca
	 * Retorna as vendas de acordo com os filtross
	 */
	public List<Vendas> getVendasBusca	(
											Long		codigo,
											Long		codigoCliente,
											Long		codigoTipoEvento,
											Long		codigoLocalEvento,
											Calendar	data
										)
	{
		String filtro = new String();
		try
		{
			if(codigo != null)
				filtro += " AND codigo = "+codigo;
			if(codigoCliente != null)
				filtro += " AND v.cliente.codigo ="+codigoCliente;
			if(codigoTipoEvento != null)
				filtro += " AND v.tipoEvento.codigo ="+codigoTipoEvento;
			if(codigoLocalEvento != null)
				filtro += " AND v.localEvento.codigo ="+codigoLocalEvento;
			if(data != null)
			{
				DateFormat df		= new SimpleDateFormat("dd/MM/yyyy");
				String dataArray[]	= df.format(data.getTime()).split("/");
				String dataSql		= dataArray[2]+"/"+dataArray[1]+"/"+dataArray[0];
				filtro += " AND v.dia = '"+dataSql+"'";
			}
			
			this.factory = Persistence.createEntityManagerFactory	(	
																		"estudioFotografico", 
																		new ControladorBancodeDados().getConfigBD()
																	);
			this.manager = this.factory.createEntityManager();
			
			String qr = "Select v FROM Vendas v WHERE 1=1 " + filtro + " ORDER BY v.codigo";
			
			System.out.println(qr);

			//Obtendo o Codigo do item
			Query query = this.manager.createQuery(qr);
			this.vendas = query.getResultList();
			return this.vendas;
		}
		catch(Exception e)
		{
			return null;
		}
	}
	
	private EntityManagerFactory					factory;
    private EntityManager							manager;
	
	private Vendas									venda;
	private List<Vendas>							vendas;
	
	private ErroBuscar								erroBuscar;
	private estudiofotografico.view.janelas.Vendas	janelaVendas;
}
