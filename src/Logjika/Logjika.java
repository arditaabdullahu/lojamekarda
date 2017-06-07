package Logjika;



import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

import Pamja.ShenimetEKartes;

public class Logjika {
		
	private static ArrayList<ShenimetEKartes> gjeneroKartatEVendosura()
	{
		ArrayList<ShenimetEKartes>  cards = new ArrayList<ShenimetEKartes>();
		
		for(int i = 0 ; i < 4 ; i ++)
		{
			for (int j = 0; j < 13; j++) {
				cards.add( new ShenimetEKartes(j+2,i+1));
				
			}
		}
		return cards;
	}
		
	
		
	public  static ArrayList<ArrayList<ShenimetEKartes>> gjeneroKartatEVendosurateLojtareve()
	{
		ArrayList<ArrayList<ShenimetEKartes>> playersSets = new ArrayList<ArrayList<ShenimetEKartes>>();
		ArrayList<ShenimetEKartes> allCards  = gjeneroKartatEVendosura();
		
		
		boolean [] selected = new boolean[52];
		
		for(int i = 0 ;i<52; i ++) selected[i]= false;
		
		Random rnd = new Random(new Date().getTime());
		int choice = rnd.nextInt(52);
		
		for(int i = 0; i < 4 ; i ++ )
		{
			ArrayList<ShenimetEKartes> tmp = new ArrayList<ShenimetEKartes>();
			
			for(int j = 0 ; j<13 ; j++)
			{	
				while(selected[choice])
				{
					choice = rnd.nextInt(52);
				}
				
				tmp.add(allCards.get(choice));
				selected[choice]= true;
			}
			
			playersSets.add(SortToSuits(renditjeEShpejte(tmp)));
		}
		
		
		return playersSets;
		
		
	}
	
	private static ArrayList<ShenimetEKartes> renditjeEShpejte(ArrayList<ShenimetEKartes> lst)

	{
		if(lst.size() <=1) return lst;
		
		ArrayList<ShenimetEKartes> less = new ArrayList<ShenimetEKartes>();
		ArrayList<ShenimetEKartes> more = new ArrayList<ShenimetEKartes>();
		ShenimetEKartes me = lst.get(0);
		
		for(int i = 1 ; i < lst.size() ; i++)
		{
			if   (me.merrValue() > lst.get(i).merrValue()) less.add(lst.get(i));
			else more.add(lst.get(i));
		}
		
		more = renditjeEShpejte(more);
		less = renditjeEShpejte(less);
		
		more.add(me);
		more.addAll(less);
		
		return (more);
	}
	
	public static ArrayList<ShenimetEKartes> SortToSuits(ArrayList<ShenimetEKartes> unSorted)
	{
		// Dirty Sorting Better algorithm needed.
			
		ArrayList<ShenimetEKartes> Sorted = new ArrayList<ShenimetEKartes>();
		int indeks = 0;
		
		for(int i = 0 ; i < 4; i ++)
		{
			for(int j = 0 ; j < unSorted.size();j++)
			{
				if((i + 1) == unSorted.get(j).merrShenjat())
				{
					Sorted.add( unSorted.get(j));
				}
			}		
			
			indeks++;
		}
		
		
		return Sorted;
		
	}
	
	public static ArrayList<ShenimetEKartes> DeSerializeCards(String StrCards)
	{
		ArrayList<ShenimetEKartes> Cards = new ArrayList<ShenimetEKartes>();
		
		String[] clst = StrCards.split("-");
		
		for(int i = 0 ; i<clst.length;i++)
		{
			int value = Integer.parseInt(clst[i].split(",")[0]);
			int suit = Integer.parseInt(clst[i].split(",")[1]);
			Cards.add(new ShenimetEKartes(value, suit));
		}
		
		return Cards;
	}
	

	public static String SerializeCards(ArrayList<ShenimetEKartes> Cards)
	{
		String strCards = "";
		for(ShenimetEKartes card : Cards)
		{
			strCards += "" + card.merrValue() + "," + card.merrShenjat()+ "-";
		}
		return strCards;
	}
	
	public static void sleep(long time)
	{
		try
		{
			Thread.sleep(time);
		}
		catch(Exception ex)
		{
			
		}
	}
	
	public static boolean has(int suit , ArrayList<ShenimetEKartes> cards )
	{
		boolean result = false;
		for(ShenimetEKartes card :cards)
		{
			result = result || (card.merrShenjat() ==  suit);
		}
		return result;
	}	
	
	public static boolean Dokk(ArrayList<ShenimetEKartes> cards , ArrayList<Integer> Modes)
	{
		
		if(Modes.contains(new Integer(2)))//King of Hearts
		{
			int heartCount = 0;
			boolean hasKing = false;
			for(ShenimetEKartes card :cards) if(card.merrShenjat() == 2)
				{
					heartCount ++;
					hasKing = (card.merrValue()==13);
					
				}
			if(heartCount<= 2 && hasKing) return true;
			
		}
		
		if(Modes.contains(new Integer(5)))//Suns
		{
			
			int points = 0;
			for(ShenimetEKartes card :cards)
				{
					if(card.merrValue()> 10)
						points += card.merrValue()-10;
				}
			if(points >=16) return true;
		}
		
		return false;
	}
	
	public static int HandWinner(int tableSuit , ShenimetEKartes[]Cards)
	{
		int max = 0;
		int indx =0;
		for(int i = 0 ; i<Cards.length;i++)
		{
			if(Cards[i].merrShenjat() == tableSuit)
			{
				if(Cards[i].merrValue() > max)
				{
					max = Cards[i].merrValue();
					indx = i;
				}
			}
		}
		return indx;
	}
}
