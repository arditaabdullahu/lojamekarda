package Logjika;

import Rrjeta.NodeConnection;


public class LidhjaEProcesorit implements Procesori {
	private ThirrPerseri callBack;
	private Derguesi   sender;
	
	public LidhjaEProcesorit() {}
	public LidhjaEProcesorit(ThirrPerseri callBack,Derguesi Sender)
	{
		this.callBack = callBack;
		this.sender = Sender;
	}
	
	@Override
	public void Process (String Data)
	{
		Process(Data,this.callBack,this.sender);
	}
	
	@Override
	public void Process(String Data, ThirrPerseri callBack,Derguesi Sender) {
		String Flag = Data.substring(0, 5);
		String Msg = Data.substring(5);
		
		if(Flag.equals("C_SHD"))
		{
			//System.out.println("Shake Hands Successfull");
			//System.out.println("Requesting Name");
			Sender.dergoje("S_RNM");
			
		}
		else if (Flag.equals("C_RNM"))
		{
			((NodeConnection)Sender).setPlayerName(Msg);
			//System.out.println("PlayerName Recived : " + Msg);
			callBack.ThirrPerseri(Flag,Sender);
		}
		else if (Flag.equals("C_CHT"))
		{
			callBack.ThirrPerseri(Data, Sender);
			//System.out.println("Chat Msg Recived " +Msg );
		}
		else if (Flag.equals("C_CPD"))
		{
			callBack.ThirrPerseri(Data, Sender);
			//System.out.println("Card Recived " +Msg );
		}
		
		else if (Flag.equals("C_GMA"))
		{
			callBack.ThirrPerseri(Data, Sender);
		}
		else if (Flag.equals("C_SGM"))
		{
			callBack.ThirrPerseri(Data, Sender);
		}
		
		
		
		
	}

}
