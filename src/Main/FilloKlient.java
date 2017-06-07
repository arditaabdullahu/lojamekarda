package Main;

import javax.swing.JOptionPane;

import Pamja.Imazhet;
import Pamja.KornizaELojes;
import Rrjeta.Server;

public class FilloKlient {
	
	public static void main (String[] Args)
	{

		if(Args.length != 0 && Args[0].equals("-Server"))
		{
			try
			{

				Server s = new Server(12345);
				Thread serverThread = new Thread(s);
				serverThread.start();
				
			}catch (Exception e) {
				// TODO: handle exception
				System.out.println(e.getMessage());
			}
			
		}
		else
		{
    		String PlayerName = JOptionPane.showInputDialog("Emri juaj:");
			String IPAddress = JOptionPane.showInputDialog("IP e serverit");
			
			try
			{
				Imazhet.BufferImages();
				KornizaELojes mainWindow = new KornizaELojes(IPAddress, 12345,PlayerName);
				mainWindow.setVisible(true);
			}
			catch(Exception ex)
			{
				ex.printStackTrace();
			}
			
			
		}

	}

}
