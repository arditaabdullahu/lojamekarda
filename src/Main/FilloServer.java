package Main;

import javax.swing.JOptionPane;

import Pamja.Imazhet;
import Pamja.KornizaELojes;
import Rrjeta.Server;

public class FilloServer {
	
	public static void main (String[] Args)
	{
		

		Args = new String[1];
		Args[0] = "-Server";
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
    		String PlayerName = JOptionPane.showInputDialog("Shkruani emrin");
//			String IPAddress = JOptionPane.showInputDialog("Enter Server Address");
//			
			//String PlayerName = "Player1";
			String IPAddress = "localhost";
			
			
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
