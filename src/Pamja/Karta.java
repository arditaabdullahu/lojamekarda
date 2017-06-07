package Pamja;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.ComponentEvent;
import java.awt.image.BufferedImage;

import javax.swing.JButton;

public class Karta extends JButton{

	private static final long serialVersionUID = 1L;
	
	private ShenimetEKartes shenimeteKartes;
	private BufferedImage imazhi;
	private String emertimi;
	
	int tableIndx = -1;
	
	private Font fontiIKartave  = new Font("Arial",1,15);
	private int siperfaqjaRrethit = 10;
	int EDGE_SHIFT = 1;
	
	public Karta(ShenimetEKartes shenimeteKartes) {
		this.shenimeteKartes = shenimeteKartes;
		
		procesiIImazhit();		
		procesiIEmertimit();
	}
	
	private void procesiIImazhit()
	{	if      ( this.shenimeteKartes.merrShenjat() <= 1)this.imazhi = Imazhet.merrSpade();
		else if ( this.shenimeteKartes.merrShenjat() == 2)this.imazhi = Imazhet.merrtHeart();
		else if ( this.shenimeteKartes.merrShenjat() == 3)this.imazhi = Imazhet.merrTref();
		else if ( this.shenimeteKartes.merrShenjat() >= 4)this.imazhi = Imazhet.merrDiamond();
	}
	
	private void procesiIEmertimit()
	{
		if       (shenimeteKartes.merrValue() == 14) this.emertimi = "A";
		else if  (shenimeteKartes.merrValue() == 11) this.emertimi = "J";
		else if  (shenimeteKartes.merrValue() == 12) this.emertimi = "Q";
		else if  (shenimeteKartes.merrValue() == 13) this.emertimi = "K";
		else     			   this.emertimi = "" + shenimeteKartes.merrValue();
	}
	
	
	public String merrEmertimin() {
		return emertimi;
	}
	
	@Override
	public String getText() {
		// TODO Auto-generated method stub
	return this.emertimi;
	}
	
	public int merrIndeksineTabeles() {
		return tableIndx;
	}
	
	public void vendosIndeksineTabeles(int tableIndx) {
		this.tableIndx = tableIndx;
	}
	
	
	public BufferedImage getSuitImge() {
		return imazhi;
	}
	
	public ShenimetEKartes getCardData() {
		return shenimeteKartes;
	}
	
	
	
	
	@Override
	protected void paintComponent(Graphics gOld) {
		
		Graphics2D g = (Graphics2D) gOld;		
	 	paintCardBody(g);
		
	}
	
	@Override
	protected void paintBorder(Graphics gOld) {
		// TODO Auto-generated method stub
		//Frame
		Graphics2D g = (Graphics2D) gOld;
		
		Point p = (this.getMousePosition());
		if(p != null){
			if(this.contains(p) && this.tableIndx ==-1)
			{
				g.setColor(Color.yellow);		
				g.drawRoundRect(0, 0, this.getWidth() - (EDGE_SHIFT), this.getHeight() - (EDGE_SHIFT ), siperfaqjaRrethit, siperfaqjaRrethit);
		
			}
			
		}
		
	}
	
	private void paintCardBody(Graphics2D g)
	{	
		
		//Body
		GradientPaint bodyGradient = new GradientPaint(200 - this.getWidth()/2,0, Color.WHITE, 200- this.getWidth()/2, this.getHeight() + 250, Color.yellow);
		g.setPaint(bodyGradient);
		g.fillRoundRect(0, 0, this.getWidth() - (EDGE_SHIFT), this.getHeight() - (EDGE_SHIFT), siperfaqjaRrethit, siperfaqjaRrethit);
		
		//Frame
		g.setColor(Color.BLACK);		
		g.drawRoundRect(0, 0, this.getWidth() - (EDGE_SHIFT), this.getHeight() - (EDGE_SHIFT ), siperfaqjaRrethit, siperfaqjaRrethit);
	
		//Image
		double factor = 75/100.0;
		int imgDim;
		if   (this.getWidth() < this.getHeight()) imgDim =(int) (this.getWidth() * factor);
		else imgDim =(int) (this.getHeight() * factor);
		
		int xPos =  1 + (this.getWidth() - imgDim) / 2;
		int yPos =  1 + (this.getHeight() - imgDim) / 2;
		g.drawImage(this.imazhi, xPos, yPos, imgDim, imgDim, this);
		
		//Label		
		int sxPos1  = 5;
		int syPos1 = 15;
		int sxPos2  = this.getWidth() - (15 + ((emertimi.length()-1) * 5)) ;
		int syPos2 = this.getHeight() - 5;
		g.setFont(fontiIKartave);
		g.setColor(Color.black);
		g.drawString(this.emertimi, sxPos1, syPos1);
		g.drawString(this.emertimi, sxPos2, syPos2);
		
	}


	@Override
	public Dimension getPreferredSize() {
		return new Dimension(70, 100);
	}
	
	@Override
	public String toString() {
		return this.shenimeteKartes.merrValue() +","+ this.shenimeteKartes.merrShenjat();
	}
	
	@Override
	protected void processComponentEvent(ComponentEvent e) {
		
		if(e.getID() == ComponentEvent.COMPONENT_RESIZED)
		{
				
		}
	}

}
