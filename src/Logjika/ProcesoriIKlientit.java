package Logjika;

import javax.swing.JOptionPane;


public class ProcesoriIKlientit implements Procesori {
	
	private ThirrPerseri thirrPerseri;
	private Derguesi   dergues;
	
	public ProcesoriIKlientit() {}
	public ProcesoriIKlientit(ThirrPerseri callBack,Derguesi Sender)
	{
		this.thirrPerseri = callBack;
		this.dergues = Sender;
	}
	
	@Override
	public void Process (String Data)
	{
		Process(Data,this.thirrPerseri,this.dergues);
	}
	
	@Override
	public void Process(String Data, ThirrPerseri callBack,Derguesi Sender) {

		String Flag = Data.substring(0,5);
		String Msg = Data.substring(5);
		
		if (Flag.equals("S_SHD"))
		{
			Sender.dergoje("C_SHD");
		}
		else if(Flag.equals("S_RNM"))
		{
			callBack.ThirrPerseri(Flag, Sender);
		}
		
		else if(Flag.equals("S_SIN"))
		{
			//System.out.println("Indeksi im " +Msg );
			callBack.ThirrPerseri(Data, Sender);
		}
		
		else if(Flag.equals("S_PCN"))
		{
			//System.out.println("Lojtari i lidhur : " + Msg);
			callBack.ThirrPerseri(Data, Sender);
		}
		
		else if(Flag.equals("S_PLS"))
		{
			String[] Players = Msg.split("-");
			for(int i =0;i<Players.length;i++)//System.out.println(Players[i]);
			callBack.ThirrPerseri(Data, Sender);
		}
		
		
		else if (Flag.equals("S_TBC"))
		{
			callBack.ThirrPerseri("S_TBC", this);
			//System.out.println("Tabela u kompletua\nDuke filluar loja");
		}
		

		else if (Flag.equals("S_CUP"))
		{
			callBack.ThirrPerseri(Data, this);
			//System.out.println("Lojtari u ndryshua" + Msg);
		}
		
		
		
		else if(Flag.equals("S_CLS"))
		{
			callBack.ThirrPerseri(Data, Sender);
		}
		
		else if (Flag.equals("S_STH"))
		{
			String Modes = "";
			while(Modes.length() ==0)
			{ 
				Modes = "1";//JOptionPane.showInputDialog("Enter Game Modes Spearated by ',' \n\b 1-Jacks\n2-King Of Hearts\n3-Queens\n4-Diamonds\n5-Suns");
			}
			
			if (Modes.length() == 1)Modes += ",";
			Sender.dergoje("C_SGM" + Modes);
		}
		
		else if (Flag.equals("S_SGM"))
		{
			callBack.ThirrPerseri(Data, Sender);
			//System.out.println("Checking Game Modes" + Msg);
		}
		
		
		else if (Flag.equals("S_CHT"))
		{
			callBack.ThirrPerseri(Data, Sender);
		}
		else if (Flag.equals("S_CPD"))
		{
			callBack.ThirrPerseri(Data, Sender);
			//System.out.println("Card Recived" + Msg);
		}
		
		else if (Flag.equals("S_STS"))
		{
			callBack.ThirrPerseri(Data, Sender);
			//System.out.println("Table Suite Recived" + Msg);
		}
		
		else if (Flag.equals("S_PWN"))
		{
			callBack.ThirrPerseri(Data, Sender);
			//System.out.println("Player Won " + Msg);
		}
		
		else if (Flag.equals("S_ROE"))
		{
			callBack.ThirrPerseri(Data, Sender);
			//System.out.println("Round Ended" + Msg);
		}
		
		else if (Flag.equals("S_GME"))
		{
			callBack.ThirrPerseri(Data, Sender);
			//System.out.println("Game Ended" + Msg);
		}
		
		
		
		
	}

}
