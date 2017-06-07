package Rrjeta;
import java.io.IOException;
import java.net.Socket;

import Logjika.Procesori;
import Logjika.ThirrPerseri;

public class NodeConnection extends Lidhja {
	private String playerName;
	private int playerIndex;
	private int Score =0;
	
	
	
	public NodeConnection(Socket connection,ThirrPerseri CallBack,Procesori Processor) throws IOException {
		this.thirrPerseri = CallBack;
		this.lidhja = connection;
		this.kanalidales = new Kanalidales(this.lidhja);
		this.kanalihyrës = new KanaliHyrës(this.lidhja, this);
		
		Thread outputThread = new Thread(kanalidales);
		Thread inputThread   = new Thread(kanalihyrës);
		
		this.Processor = Processor;
		
		outputThread.start();
		inputThread.start();
		
		
	}
	
	public void shakeHands()
	{
		this.kanalidales.dergoje("S_SHD");
		//System.out.println("Shake Hands Invoked");
	}

	
	public String getPlayerName() {
		return playerName;
	}
	
	public int getPlayerIndex() {
		return playerIndex;
	}
	
	public void setPlayerIndex(int playerIndex) {
		this.playerIndex = playerIndex;
	}
	
	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}
	
	public void dergoje (String Data)
	{
		this.kanalidales.dergoje(Data);
	}
	
	public void inputRecived(String Input)
	{
		String Flag = Input.substring(0, 5);
		String Data = Input.substring(5);
		
		
		if(Flag.equals("C_SHD"))
		{
			//System.out.println("Shake Hands Successfull");
			//System.out.println("Requesting Name");
			this.kanalidales.dergoje("S_RNM");
			
		}
		else if (Flag.equals("C_RNM"))
		{
			this.playerName = Data;
			//System.out.println("PlayerName Recived : " + Data);
			thirrPerseri.ThirrPerseri(Flag,this);
		}
		
		
		
	}

	public void AddScore(int Score)
	{
		this.Score += Score;
	}
	
	public int getScore()
	{
		return this.Score;
	}
	
	@Override
	public void ThirrPerseri(String Data, Object Caller) {
		Processor.Process(Data,this.thirrPerseri,this);
		
	}
}
