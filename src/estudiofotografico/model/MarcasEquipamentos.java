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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;



/**
 *
 * @author Rogério Eduardo Pereira
 * Data Criação: Feb 13, 2014
 */
@Entity
@NamedQueries
({
	@NamedQuery(name = "MarcaEquipamento.findAll", query = "SELECT marca FROM MarcasEquipamentos ORDER BY marca ASC"),
	@NamedQuery(name = "MarcaEquipamento.findOne", query = "SELECT m.codigo FROM MarcasEquipamentos m WHERE m.marca = ?0")
})
public class MarcasEquipamentos
{
    @Id
    @GeneratedValue
    private Long codigo;
    
    @Column(nullable=false, length=50, unique=true)
    private String marca;
    
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


    public String getMarca ()
    {
        return marca;
    }


    public void setMarca (String marca)
    {
        this.marca = marca.toUpperCase();
    }

}
