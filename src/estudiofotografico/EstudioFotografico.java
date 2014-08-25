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
package estudiofotografico;

import estudiofotografico.control.CriaTabelas;
import estudiofotografico.model.Usuario;
import estudiofotografico.view.erros.ConfiguraBanco;
import estudiofotografico.view.janelas.Login;



/**
 *
 * @author Rogério Eduardo Pereira
 * Data Criação: 03/02/2014
 */
public class EstudioFotografico
{
    /**
     * @param args the command line arguments
     */
    public static void main (String[] args)    
    {
		lookandfeel.LookAndFeel.setLookAndFeel();
		
		janelaConfiguraBanco = new ConfiguraBanco();
		janelaConfiguraBanco.setVisible(true);
		janelaConfiguraBanco.progresso.setIndeterminate(true);
        criaTabelas = new CriaTabelas();
		criaTabelas.criaBanco();
        criaTabelas.criaTabelas();
        criaTabelas.insereUsuarioSuporte();
		janelaConfiguraBanco.progresso.setIndeterminate(false);
		janelaConfiguraBanco.Fechar();
        
        janelaLogin = new Login();
        janelaLogin.setVisible(true);
    }
    
    private static	CriaTabelas		criaTabelas;
	private static	Login			janelaLogin;
	private static	ConfiguraBanco	janelaConfiguraBanco;
	public  static	Usuario			user;
}
