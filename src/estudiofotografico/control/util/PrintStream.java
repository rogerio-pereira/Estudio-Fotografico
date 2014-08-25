/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package estudiofotografico.control.util;

/*
 * Classe desenvolvida pela Microsoft para nao bloquear o processo waitFor
 */
public class PrintStream extends Thread 
{
	java.io.InputStream __is = null;

	public PrintStream(java.io.InputStream is) 
	{
		__is = is;
	}

	public void run() 
	{
		try 
		{
			while (this != null) 
			{
				int _ch = __is.read();
				if (_ch != -1)
					System.out.print((char) _ch);
				else
				break;
			}
		}
		catch(Exception e) 
		{
			e.printStackTrace();
		}
	}
}
