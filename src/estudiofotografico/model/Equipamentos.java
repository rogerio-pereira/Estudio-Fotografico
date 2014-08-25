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

import java.util.Calendar;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;



/**
 *
 * @author Rogério Eduardo Pereira
 * Data Criação: Feb 13, 2014
 */
@Entity
public class Equipamentos
{
    @Id
    @GeneratedValue
    private Long codigo;
    
    @Column(length=70, nullable=false)
    private String equipamento;
    
    @OneToOne
    private TiposEquipamentos tipo;
    
    @OneToOne
    private MarcasEquipamentos marca;
    
    @Column(length=100)
    private String localCompra;
    
    @Column(scale=2)
    private double preco;
    
    @Column(length=30)
    private String numeroSerie;
    
    @Temporal (TemporalType.DATE)
    private Calendar dataCompra;
    
    @Column(length=10)
    private String garantia;
    
    private int situacao;
    
    @Column(length=50)
    private String obsSituacao;
	
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


    public Long getCodigo ()
    {
        return codigo;
    }


    public void setCodigo (Long codigo)
    {
        this.codigo = codigo;
    }


    public Calendar getDataCompra ()
    {
        return dataCompra;
    }


    public void setDataCompra (Calendar dataCompra)
    {
        this.dataCompra = dataCompra;
    }


    public String getEquipamento ()
    {
        return equipamento;
    }


    public void setEquipamento (String equipamento)
    {
        this.equipamento = equipamento;
    }


    public String getGarantia ()
    {
        return garantia;
    }


    public void setGarantia (String garantia)
    {
        this.garantia = garantia;
    }


    public String getLocalCompra ()
    {
        return localCompra;
    }


    public void setLocalCompra (String localCompra)
    {
        this.localCompra = localCompra;
    }


    public MarcasEquipamentos getMarca ()
    {
        return marca;
    }


    public void setMarca (MarcasEquipamentos marca)
    {
        this.marca = marca;
    }


    public String getNumeroSerie ()
    {
        return numeroSerie;
    }


    public void setNumeroSerie (String numeroSerie)
    {
        this.numeroSerie = numeroSerie;
    }


    public String getObs ()
    {
        return obs;
    }


    public void setObs (String obs)
    {
        this.obs = obs;
    }


    public String getObsSituacao ()
    {
        return obsSituacao;
    }


    public void setObsSituacao (String obsSituacao)
    {
        this.obsSituacao = obsSituacao;
    }


    public double getPreco ()
    {
        return preco;
    }


    public void setPreco (double preco)
    {
        this.preco = preco;
    }


    public int getSituacao ()
    {
        return situacao;
    }


    public void setSituacao (int situacao)
    {
        this.situacao = situacao;
    }


    public TiposEquipamentos getTipo ()
    {
        return tipo;
    }


    public void setTipo (TiposEquipamentos tipo)
    {
        this.tipo = tipo;
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
