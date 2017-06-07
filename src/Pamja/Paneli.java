package Pamja;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JPanel;

import Logjika.Logjika;

public class Paneli extends JPanel implements ActionListener {
	private static final long serialVersionUID = 1L;
	JPanel cardsPanel,tablePanel;
	ArrayList<ShenimetEKartes> cards;
	KornizaELojes gameFrame;
	private boolean button_u_klikua = false;
	JButton shperndajButton = new JButton("Gati per loje");
	
	
	public void klikoniKarten(ActionEvent e)
	{
		Karta cTmp = (Karta)e.getSource();
		this.cardsPanel.remove(cTmp);
		this.cardsPanel.repaint();
		this.tablePanel.repaint();
		this.revalidate();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		if(button_u_klikua && gameFrame.merrlojtarinAktual() == gameFrame.merrIndeksin())
		{
			Karta cTmp = (Karta)e.getSource();
			
			if(   gameFrame.merrShenjatETabelave() == -1 ||
				  cTmp.getCardData().merrShenjat() == gameFrame.merrShenjatETabelave()||
				  !Logjika.has(gameFrame.merrShenjatETabelave(), this.merrKarta())					
					)
			{
				
				this.cardsPanel.remove(cTmp);
				this.cardsPanel.repaint();
				this.tablePanel.repaint();
				this.revalidate();
				gameFrame.dergoje("C_CPD"+ cTmp.toString());
				Logjika.sleep(1000);
				gameFrame.vendoslojtarinAktual(-1);
			}
		}
		else if(e.getActionCommand().equals("Gati per loje"))
		{
			button_u_klikua = true;
			this.remove(shperndajButton);
			this.add(cardsPanel,BorderLayout.NORTH);
			this.cardsPanel.repaint();
			this.tablePanel.repaint();
			this.revalidate();
		}
	}
	
	public Paneli(KornizaELojes gmfrm ) {
		
		this.gameFrame = gmfrm;
		this.setLayout(new BorderLayout(5, 5));
		
		this.cardsPanel = new JPanel();
		this.cardsPanel.setBackground(new Color(94,57,91));
		this.cardsPanel.setLayout(new FlowLayout(FlowLayout.CENTER,3,10));
		
		this.tablePanel = new JPanel();
		this.tablePanel.setBackground(new Color(94,57,91));
		this.tablePanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 10));		
		
		shperndajButton.addActionListener(this);
		this.add(shperndajButton,BorderLayout.NORTH);
		//this.add(cardsPanel,BorderLayout.NORTH);
		this.add(tablePanel,BorderLayout.CENTER);
		
		System.out.println("Paneli i lojes u krijua");
	}
	
	public void ReDistributeCards(ArrayList<ShenimetEKartes> cards)
	{
		this.cardsPanel.removeAll();
		this.cards = cards;
		
		for(ShenimetEKartes card : this.cards)
		{
			Karta c = new Karta(card);
			this.cardsPanel.add(c);
			c.addActionListener(this);
		}
		this.revalidate();
	}
	
	public JPanel merrKartenePanelit() {
		return cardsPanel;
	}
	
	public void shtoKarta(Karta card)
	{
		
		this.tablePanel.add(card);
		tablePanel.repaint();
		tablePanel.revalidate();
		this.repaint();
		this.revalidate();
		System.out.println("Karta u shtua");
	}
	

	public ArrayList<ShenimetEKartes> merrKarta()
	{
		ArrayList<ShenimetEKartes> cards = new ArrayList<ShenimetEKartes>();
		for(int i = 0;i<cardsPanel.getComponentCount();i++)
		{
			if(cardsPanel.getComponent(i) instanceof Karta)
			{
				cards.add(((Karta)cardsPanel.getComponent(i)).getCardData());
			}
		}
		return cards;
	}	
}


