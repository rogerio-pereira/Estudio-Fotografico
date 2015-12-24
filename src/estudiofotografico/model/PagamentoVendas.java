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


import java.util.Calendar;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 *
 * @author Rogério Eduardo Pereira
 * Data Criação: Mar 7, 2014
 */
@Entity
public class PagamentoVendas 
{
	@Id
	@GeneratedValue
	private Long codigo;
	
	@Temporal(TemporalType.DATE)
	@Column(nullable=false)
	private Calendar vencimento;
	
	@Column(scale=2, nullable=false)
	private Double valor;
	
	@Column(nullable=false)
	private Boolean pago;
	
	@Column(scale=2)
	private Double recebido;
	
	private int formaPagamento;
	
	@Column(length=1000)
	private String obs;


	public Long getCodigo ()
	{
		return codigo;
	}


	public void setCodigo (Long codigo)
	{
		this.codigo = codigo;
	}


	public Calendar getVencimento ()
	{
		return vencimento;
	}


	public void setVencimento (Calendar vencimento)
	{
		this.vencimento = vencimento;
	}


	public Double getValor ()
	{
		return valor;
	}


	public void setValor (Double valor)
	{
		this.valor = valor;
	}


	public Boolean getPago ()
	{
		return pago;
	}


	public void setPago (Boolean pago)
	{
		this.pago = pago;
	}


	public Double getRecebido ()
	{
		return recebido;
	}


	public void setRecebido (Double recebido)
	{
		this.recebido = recebido;
	}


	public int getFormaPagamento ()
	{
		return formaPagamento;
	}


	public void setFormaPagamento (int formaPagamento)
	{
		this.formaPagamento = formaPagamento;
	}


	public String getObs ()
	{
		return obs;
	}


	public void setObs (String obs)
	{
		this.obs = obs;
	}
	
}
