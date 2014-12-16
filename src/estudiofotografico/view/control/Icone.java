/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package estudiofotografico.view.control;


import java.awt.Toolkit;
import javax.swing.JFrame;


/*
 *	Arquivo  Icone.java
 *	Altera o Icone da aplicação
 *	
 *	Sistema:	EstudioFotografico
 *	Autor:      Rogério Eduardo Pereira
 *	Data:       16/12/2014
 */
public class Icone
{
	public void setIcon(JFrame janela)
	{
		janela.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/estudiofotografico/view/img/camera-photo-5.png")));
	}
}
