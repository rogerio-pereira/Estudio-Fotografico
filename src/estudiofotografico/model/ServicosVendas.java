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

package estudiofotografico.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;


/**
 *
 * @author Rogério Eduardo Pereira
 * Data Criação: Mar 7, 2014
 */
@Entity
public class ServicosVendas 
{
	@Id
	@GeneratedValue
	private Long codigo;
	
	@OneToOne
	private Servicos servico;
	
	@Column(scale=2,nullable=false)
	private Double valor;
	
	@Column(scale=2)
	private Double desconto;


	public Long getCodigo ()
	{
		return codigo;
	}


	public void setCodigo (Long codigo)
	{
		this.codigo = codigo;
	}


	public Servicos getServico ()
	{
		return servico;
	}


	public void setServico (Servicos servico)
	{
		this.servico = servico;
	}


	public Double getValor ()
	{
		return valor;
	}


	public void setValor (Double valor)
	{
		this.valor = valor;
	}


	public Double getDesconto ()
	{
		return desconto;
	}


	public void setDesconto (Double desconto)
	{
		this.desconto = desconto;
	}
	
}
