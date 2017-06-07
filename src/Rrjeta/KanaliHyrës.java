package Rrjeta;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;

import Logjika.Logjika;
import Logjika.ThirrPerseri;


public class KanaliHyrës implements Runnable {

	private Socket lidhja;
	private ThirrPerseri thirrPerseri;
	private DataInputStream hyrja;
	
	public KanaliHyrës(Socket Lidhja,ThirrPerseri ThirrPerseri) throws IOException {
		this.lidhja = Lidhja;
		this.thirrPerseri = ThirrPerseri;
		this.hyrja = new DataInputStream(lidhja.getInputStream());
	}
	
	@Override
	public void run() {
	
		while (true)
		{
			try
			{
				
				String Input = hyrja.readUTF();
				thirrPerseri.ThirrPerseri(Input,this);
				Logjika.sleep(1000);
				
			}
			catch (Exception e)
			{
				
			}
		}
	}
}
