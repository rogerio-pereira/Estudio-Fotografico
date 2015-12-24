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

import Utilidades.MD5Encoder;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;



/**
 *
 * @author Rogério Eduardo Pereira
 * Data Criação: Feb 14, 2014
 */
@NamedQueries
({
	@NamedQuery(name="Usuario.login",	query = "SELECT u FROM Usuario u WHERE usuario = ?1"),
	@NamedQuery(name="Usuario.findAll", query = "SELECT u FROM Usuario u ORDER BY u.nome ASC"),
	@NamedQuery(name="Usuario.findOne", query = "SELECT u.codigo FROM Usuario u WHERE u.usuario = ?0")
})
@Entity
public class Usuario
{
    @Id
    @GeneratedValue
    private Long codigo;
    
    @Column(nullable=false, length=100)
    private String nome;
    
    @Column(nullable=false, length=15, unique=true)
    private String usuario;
    
    @Column(nullable=false, length=32)
    private String senha;
    
    /*
     * 0 - Nivel padrao
     * 1 - Administrador
     */
    @Column(nullable=false, columnDefinition="bit default 0")
    private Boolean nivel;
    
    @Column(nullable=false, columnDefinition="bit default 1", insertable=false)
    private Boolean ativo;


    public String getNome ()
    {
        return nome;
    }


    public void setNome (String Nome)
    {
        this.nome = Nome;
    }


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


    public Boolean getNivel ()
    {
        return nivel;
    }


    public void setNivel (Boolean nivel)
    {
        this.nivel = nivel;
    }


    public String getSenha ()
    {
        return senha;
    }


    public void setSenha (String senha)
    {
		MD5Encoder md5	= new MD5Encoder();
		
		try
		{
			this.senha = md5.MD5Encode(senha+"EstudioFotografico");
		}
		catch (Exception e)
		{
			this.senha = "";
		}
    }


    public String getUsuario ()
    {
        return usuario;
    }


    public void setUsuario (String usuario)
    {
        this.usuario = usuario;
    }
    
    
}
