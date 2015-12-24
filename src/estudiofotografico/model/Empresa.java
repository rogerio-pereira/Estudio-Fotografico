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
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;



/**
 *
 * @author Rogério Eduardo Pereira
 * Data Criação: Feb 14, 2014
 */
@Entity
@NamedQueries
({
	@NamedQuery(name="Empresa.getOne",	query = "SELECT e FROM Empresa e"),
	@NamedQuery(name="Empresa.count",	query = "SELECT COUNT (*) AS c FROM Empresa e")
})
public class Empresa
{
    @Column(nullable = false, length=100)
    private String nome;
    
    @Id
    @Column(nullable = false, length=18)
    private String cnpj;
    
    @Column(nullable = false, length=17)
    private String telefone;
    
    @Column(length=17)
    private String fax;
    
    @Column(length=100)
    private String email;
    
    @Column(length=100)
    private String site;
    
    @Column(nullable=false, length=100)
    private String Endereco;
    
    @Column(nullable=false)
    private int numero;
    
    @Column(nullable=false, length=40)
    private String bairro;
    
    @Column(length=15)
    private String complemento;
    
    @Column(nullable=false, length=50)
    private String cidade;
    
    @Column(nullable=false)
    private int estado;
    
    @Column(nullable=false, length=9)
    private String cep;


    public String getEndereco ()
    {
        return Endereco;
    }


    public void setEndereco (String Endereco)
    {
        this.Endereco = Endereco;
    }


    public String getBairro ()
    {
        return bairro;
    }


    public void setBairro (String bairro)
    {
        this.bairro = bairro;
    }


    public String getCep ()
    {
        return cep;
    }


    public void setCep (String cep)
    {
        this.cep = cep;
    }


    public String getCidade ()
    {
        return cidade;
    }


    public void setCidade (String cidade)
    {
        this.cidade = cidade;
    }


    public String getCnpj ()
    {
        return cnpj;
    }


    public void setCnpj (String cnpj)
    {
        this.cnpj = cnpj;
    }


    public String getComplemento ()
    {
        return complemento;
    }


    public void setComplemento (String complemento)
    {
        this.complemento = complemento;
    }


    public String getEmail ()
    {
        return email;
    }


    public void setEmail (String email)
    {
        this.email = email;
    }


    public int getEstado ()
    {
        return estado;
    }


    public void setEstado (int estado)
    {
        this.estado = estado;
    }


    public String getFax ()
    {
        return fax;
    }


    public void setFax (String fax)
    {
        this.fax = fax;
    }


    public String getNome ()
    {
        return nome;
    }


    public void setNome (String nome)
    {
        this.nome = nome;
    }


    public int getNumero ()
    {
        return numero;
    }


    public void setNumero (int numero)
    {
        this.numero = numero;
    }


    public String getSite ()
    {
        return site;
    }


    public void setSite (String site)
    {
        this.site = site;
    }


    public String getTelefone ()
    {
        return telefone;
    }


    public void setTelefone (String telefone)
    {
        this.telefone = telefone;
    }
    
    
}
