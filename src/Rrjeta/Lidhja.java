package Rrjeta;

import java.io.IOException;
import java.net.Socket;

import Logjika.Derguesi;
import Logjika.Procesori;
import Logjika.ThirrPerseri;


public class Lidhja implements  ThirrPerseri,Derguesi {
	
	protected ThirrPerseri thirrPerseri;
	protected Socket lidhja;
	protected KanaliHyr�s kanalihyr�s;
	protected Kanalidales kanalidales;	
	protected Procesori Processor;
	
	public Lidhja(){}
	public Lidhja(String IP,int Port,ThirrPerseri ThirrPerseri,Procesori Processor) throws IOException {
		this.thirrPerseri = ThirrPerseri;
		
		this.lidhja = new Socket(IP, Port);
		this.kanalidales = new Kanalidales(this.lidhja);
		this.kanalihyr�s = new KanaliHyr�s(this.lidhja, this);
		
		Thread outputThread = new Thread(kanalidales);
		Thread inputThread   = new Thread(kanalihyr�s);
		
		this.Processor = Processor;
		
		outputThread.start();
		inputThread.start();
		
		
	}


	public void dergoje(String Data)
	{
		this.kanalidales.dergoje(Data);
	}
	

	@Override
	public void ThirrPerseri(String Data,Object Caller) {
		Processor.Process(Data, this.thirrPerseri,this);
	}
}
