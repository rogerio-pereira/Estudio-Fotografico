/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package estudiofotografico.control.util;


import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;


/**
 *
 * @author Rogério Eduardo Pereira
 * Data Criação: Jun 18, 2014
 */
public class ControladorTabelas 
{
	/*
	 * Método apaga tabela
	 * Apaga a tabela
	 */
	private void apagaTabela(JTable tabela)
	{
		DefaultTableModel modelo = (DefaultTableModel) tabela.getModel();
		while(tabela.getRowCount() > 0)
			modelo.removeRow(0);
	}
}
