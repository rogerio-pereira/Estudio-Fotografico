/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package estudiofotografico.control;


import java.sql.Connection;
import java.util.HashMap;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;


/**
 *
 * @author Rogério Eduardo Pereira
 * Data Criação: 25/08/2014
 */
public class ControladorRelatorios 
{
	public ControladorRelatorios ()
	{
		this.conexao		= new ControladorBancodeDados().getConexaoReport();
		this.parametros	= new HashMap();
	}
	
	
	public void GeraRelatório(String relatorio)
	{
		try
		{
			JasperPrint	jp			= JasperFillManager.fillReport(relatorio, this.parametros, this.conexao);
			JasperViewer	jv			= new JasperViewer(jp);
			jv.setVisible(true);
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(null, "Erro ao gerar Relatorio", "Erro ao gerar o relatorio!", JOptionPane.INFORMATION_MESSAGE);
		}
	}
	
	public Connection	conexao;
	public HashMap	parametros;
}
