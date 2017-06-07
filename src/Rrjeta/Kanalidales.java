package Rrjeta;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import Logjika.Derguesi;


public class Kanalidales implements Runnable,Derguesi {

	private boolean SendingFlag = false;
	private String Data = "";
	private Socket lidhja;
	private DataOutputStream dalja;
	
	public Kanalidales(Socket Connection) throws IOException {
		this.lidhja = Connection;
		dalja = new DataOutputStream(this.lidhja.getOutputStream());
	}
	
	public void dergoje(String data)
	{
		this.Data = data;
		this.SendingFlag = true;
	}
	
	@Override
	public void run() {
	
		while (true)
		{
			try
			{
				if(SendingFlag)
				{
					//System.out.println("Dergim : " + Data);
					dalja.writeUTF(Data);	
					dalja.flush();
					SendingFlag = false;
				}
				
				
				Thread.sleep(1000);
			}
			catch (Exception e)
			{
				
			}
		}
	}
}
