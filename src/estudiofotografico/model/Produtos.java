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
 * To change this template, choose Tools | Templates
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
 * Data Criação: Feb 13, 2014
 */
@Entity
public class Produtos
{
    @Id
    @GeneratedValue
    private Long codigo;
    
    @Column(length=70, nullable=false)
    private String produto;
    
    @OneToOne
    private CategoriasProdutos categoria;
    
    @Column(scale=2)
    private double valor;
	
	@OneToOne
	private Fornecedores fornecedor;
    
    @Column(length=999999999)
    private String obs;

    @Column(nullable=false, columnDefinition="bit default 1", insertable=false)
    private Boolean ativo;


    public Boolean getAtivo ()
    {
        return ativo;
    }


    public void setAtivo (Boolean ativo)
    {
        this.ativo = ativo;
    }


    public CategoriasProdutos getCategoria ()
    {
        return categoria;
    }


    public void setCategoria (CategoriasProdutos categoria)
    {
        this.categoria = categoria;
    }


    public Long getCodigo ()
    {
        return codigo;
    }


    public void setCodigo (Long codigo)
    {
        this.codigo = codigo;
    }


    public String getObs ()
    {
        return obs;
    }


    public void setObs (String obs)
    {
        this.obs = obs;
    }


    public String getProduto ()
    {
        return produto;
    }


    public void setProduto (String produto)
    {
        this.produto = produto;
    }


    public double getValor ()
    {
        return valor;
    }


    public void setValor (double valor)
    {
        this.valor = valor;
    }
    
    public Fornecedores getFornecedor ()
	{
		return fornecedor;
	}


	public void setFornecedor (Fornecedores fornecedor)
	{
		this.fornecedor = fornecedor;
	}
}
