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


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 *
 * @author Rogério Eduardo Pereira
 * Data Criação: Mar 7, 2014
 */
@Entity
public class Vendas 
{
	@Id
	@GeneratedValue
    private Long codigo;
	
	@Temporal(TemporalType.DATE)
	@Column(nullable=false)
    private Calendar dia;
	
	@Temporal(TemporalType.TIME)
	@Column(nullable=false)
	private Date hora;
	
	@OneToOne
	private Clientes cliente;
	
	@OneToOne
	private TiposEventos tipoEvento;
	
	@OneToOne
	private LocaisEventos localEvento;
	
	@Column(length=999999999)
    private String obs;
	
	@Column(scale=2)
	private Double desconto;
	
	@Column(scale=2)
	private Double porcentagemDesconto;
	
	@OneToMany(cascade={CascadeType.ALL}, orphanRemoval=true)
	private Collection<ProdutosVendas> produtos;
	
	@OneToMany(cascade={CascadeType.ALL}, orphanRemoval=true)
	private Collection<ServicosVendas> servicos;
	
	@OneToMany(cascade={CascadeType.ALL}, orphanRemoval=true)
	private Collection<PagamentoVendas> pagamentos;
	
	@Column(nullable=false)
	private Boolean quitado;
	
	private int melhorDia;


	public Long getCodigo ()
	{
		return codigo;
	}


	public void setCodigo (Long codigo)
	{
		this.codigo = codigo;
	}


	public Calendar getDia ()
	{
		return dia;
	}


	public void setDia (Calendar dia)
	{
		this.dia = dia;
	}


	public String getHora ()
	{
		String horaString = new String();
		horaString =	String.format("%02d", hora.getHours())		+ ":" +
						String.format("%02d", hora.getMinutes());
		
		return horaString;
	}


	public void setHora (String horaString)
	{
		try
		{
			SimpleDateFormat formatoHora = new SimpleDateFormat("HH:mm");
			this.hora = formatoHora.parse(horaString);
		}
		catch (ParseException ex)
		{
			Logger.getLogger(estudiofotografico.view.janelas.Vendas.class.getName()).log(Level.SEVERE, null, ex);
		}
	}


	public Clientes getCliente ()
	{
		return cliente;
	}


	public void setCliente (Clientes cliente)
	{
		this.cliente = cliente;
	}


	public TiposEventos getTipoEvento ()
	{
		return tipoEvento;
	}


	public void setTipoEvento (TiposEventos tipoEvento)
	{
		this.tipoEvento = tipoEvento;
	}


	public LocaisEventos getLocalEvento ()
	{
		return localEvento;
	}


	public void setLocalEvento (LocaisEventos localEvento)
	{
		this.localEvento = localEvento;
	}


	public String getObs ()
	{
		return obs;
	}


	public void setObs (String obs)
	{
		this.obs = obs;
	}


	public Double getDesconto ()
	{
		return desconto;
	}


	public void setDesconto (Double desconto)
	{
		this.desconto = desconto;
	}


	public Double getPorcentagemDesconto ()
	{
		return porcentagemDesconto;
	}


	public void setPorcentagemDesconto (Double porcentagemDesconto)
	{
		this.porcentagemDesconto = porcentagemDesconto;
	}


	public Collection<ProdutosVendas> getProdutos ()
	{
		return produtos;
	}


	public void setProdutos (Collection<ProdutosVendas> produtos)
	{
		this.produtos = produtos;
	}


	public Collection<ServicosVendas> getServicos ()
	{
		return servicos;
	}


	public void setServicos (Collection<ServicosVendas> servicos)
	{
		this.servicos = servicos;
	}


	public Collection<PagamentoVendas> getPagamentos ()
	{
		return pagamentos;
	}


	public void setPagamentos (Collection<PagamentoVendas> pagamentos)
	{
		this.pagamentos = pagamentos;
	}


	public Boolean getQuitado ()
	{
		return quitado;
	}


	public void setQuitado (Boolean quitado)
	{
		this.quitado = quitado;
	}


	public int getMelhorDia ()
	{
		return melhorDia;
	}


	public void setMelhorDia (int melhorDia)
	{
		this.melhorDia = melhorDia;
	}
	
	
	
}
