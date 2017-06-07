package Pamja;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.*;

import Logjika.Derguesi;
import Logjika.Logjika;
import Logjika.ProcesoriIKlientit;
import Logjika.ThirrPerseri;
import Rrjeta.Lidhja;

public class KornizaELojes extends JFrame implements ThirrPerseri,Derguesi
{

	private static final long serialVersionUID = 1L;

	private Paneli gPanel;
	private Panelianesor sidePanel;
	private Lidhja lidhja;
	
	private String emriILojtarit;
	private int indeksi;
	private int lojtariAktual = 0;
	private int shenjatETabeleve = -1;
	
	public int merrShenjatETabelave() {
		return shenjatETabeleve;
	}

	
    public int  merrlojtarinAktual()
    {return this.lojtariAktual;}
    
    public void vendoslojtarinAktual(int i)
    {this.lojtariAktual = i;}
    
    public int merrIndeksin()
    {return this.indeksi;}
    
    @Override
    public void dergoje(String Data) {
    	this.lidhja.dergoje(Data);    	
    }
    
	
	public KornizaELojes(String IP , int Port,String PlayerName) throws IOException {

		this.setBackground(Color.white);
		this.emriILojtarit = PlayerName;
		this.lidhja = new Lidhja(IP, Port, this,new ProcesoriIKlientit());
		
		this.setTitle(this.emriILojtarit);
		this.setSize(1300, 1000);
				
		this.setLayout( new BorderLayout(5, 5) );
		
		this.gPanel = new Paneli(this);
		gPanel.setBackground(Color.white);
		
		this.sidePanel = new Panelianesor(this);
		this.sidePanel.setBackground(Color.white);		
		
		this.add(sidePanel,BorderLayout.SOUTH);
		
		this.add(gPanel,BorderLayout.CENTER);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		System.out.println("Korniza u krijua!");
	}	
	
	public Lidhja getConnection() {
		return lidhja;
	}
	
	@Override
	public void ThirrPerseri(String Data, Object Caller) {
		
		String Flag = Data.substring(0,5);
		String Msg = Data.substring(5);
		
		if(Flag.equals("S_RNM"))
		{
			lidhja.dergoje("C_RNM" + this.emriILojtarit);
		}	
		
		if(Flag.equals("S_SIN"))
		{
			this.indeksi = Integer.parseInt(Msg);
			//System.out.println("Index u ruajt");
		}
		
		
		else if(Flag.equals("S_PCN"))
		{
			int indx = Integer.parseInt(Msg.split(",")[0]);
			String name =(Msg.split(",")[1]);
			this.sidePanel.AddPlayer(name, indx);
			
		}
		
		else if(Flag.equals("S_PLS"))
		{
			System.out.println("Lista e lojtareve u pranua");
			
			String[] Players = Msg.split("-");
			for(int i =0;i<Players.length;i++)
			{
				try
				{
					String name =(Players[i].split(",")[1]);
					int indx = Integer.parseInt(Players[i].split(",")[0]);
					this.sidePanel.AddPlayer(name, indx);
				}
				catch(Exception ex)
				{
					
				}
			}
			this.sidePanel.AddPlayer(this.emriILojtarit,0);
			
			
		}
		else if(Flag.equals("S_TBC"))
		{
			//this.setVisible(true);
		}
		
		else if (Flag.equals("S_CUP"))
		{
			int Indx = Integer.parseInt(Msg);
			this.lojtariAktual = Indx;
			this.sidePanel.SelectPlayer(Indx);
			
		}

		else if (Flag.equals("S_SGM"))
		{
			String[] Modes = Msg.split(",");
			ArrayList<Integer> modes = new ArrayList<Integer>();
			for(int i=0;i<Modes.length;i++)modes.add(new Integer(Integer.parseInt(Modes[i])));
			if(Logjika.Dokk(this.gPanel.merrKarta(), modes))dergoje("C_GMA0");
			else dergoje("C_GMA1");
			
		}
		
		else if (Flag.equals("S_CLS"))
		{
			this.gPanel.ReDistributeCards(Logjika.DeSerializeCards(Msg));
		}
		
		else if (Flag.equals("C_CHT"))
		{
			this.lidhja.dergoje("C_CHT" + this.emriILojtarit + ":" + Msg);
		}
		
		else if (Flag.equals("S_CHT"))
		{
			System.out.println("mesazh nga chati");
			this.sidePanel.AppendToChat(Msg);
		}
		else if (Flag.equals("S_CPD"))
		{
			System.out.println("Duke i procesuar letrat" + Msg);
			int indx = Integer.parseInt(Msg.split(",")[0]);
			int value = Integer.parseInt(Msg.split(",")[1]);
			int suit = Integer.parseInt(Msg.split(",")[2]);			
			ShenimetEKartes cd = new ShenimetEKartes(value,suit);
			Karta c = new Karta(cd);
			c.vendosIndeksineTabeles(indx);
			this.gPanel.shtoKarta(c);
			
			
		}
		else if (Flag.equals("S_STS"))
		{
			this.shenjatETabeleve = Integer.parseInt(Msg);
		}
		
		else if (Flag.equals("S_PWN"))
		{
			this.shenjatETabeleve = -1;
			int indx = Integer.parseInt(Msg.split(",")[0]);
			String Name = Msg.split(",")[1];
			int score = Integer.parseInt(Msg.split(",")[2]);
			this.gPanel.tablePanel.removeAll();
			this.gPanel.tablePanel.repaint();
			this.gPanel.tablePanel.revalidate();
			
			
			this.sidePanel.getPlayersList().setSelectedIndex(-1);
		if(indx == this.indeksi)
			JOptionPane.showMessageDialog(null,"Ti fitove "  + this.emriILojtarit);
		else
			JOptionPane.showMessageDialog(null,   Name + " fitoj lojen");
		
		}
		else if (Flag.equals("S_ROE"))
		{	
			JOptionPane.showMessageDialog(null, "Raundi perfundoj\n\n" + Msg);
		
		}
		
		else if (Flag.equals("S_GME"))
		{
			JOptionPane.showMessageDialog(null, "Loja perfundoj\n\n" + Msg);
			System.exit(0);
		}
		
	}
	
}