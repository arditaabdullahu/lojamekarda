package Pamja;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.LayoutManager;

public class TabeleEShenjave    implements LayoutManager{
	
	private int gap;
	boolean jacksMode = false;
	
	public TabeleEShenjave(int gap , boolean jacksMode) {
		this.gap = gap;
		this.jacksMode = jacksMode;
	}
	public TabeleEShenjave(int gap) {
		this(gap,false);
	}
	
	public TabeleEShenjave() {
	this(5,false);
	}
	

	@Override
	public void addLayoutComponent(String name, Component comp) {
		// TODO Auto-generated method stub
		//System.out.println(name + " " + comp.toString());
	}

	@Override
	public void layoutContainer(Container parent) {
		if(jacksMode)
		{
			int[] row = new int[4];
			int v = 15;
			for(int i = 0 ;i < parent.getComponentCount();i++)
			{
				Karta c = (Karta) parent.getComponent(i);
				int x = (parent.getWidth() - (5 * (gap +  c.getWidth() )) + c.getWidth())  / 2;
				int y = ( (parent.getHeight())  - ((13 * v) + c.getHeight() )  )/2;
				
				
				c.setLocation(x + ((gap + c.getWidth()) * ( c.getCardData().merrShenjat() -1)) ,y + (v * row[c.getCardData().merrShenjat() - 1]));
		
				row[c.getCardData().merrShenjat() - 1] ++ ;
				
			}
		}
		else
		{
			for(int i = 0 ;i < parent.getComponentCount();i++)
			{
				Karta c = (Karta) parent.getComponent(i);
				
				int x = (parent.getWidth() - c.getWidth()) / 2;			
				int y = (parent.getHeight() - c.getHeight()) /2;
				
				
				if(c.merrIndeksineTabeles() == 0)
				{
					c.setLocation(x,y + c.getHeight() + gap);
				}
				else if(c.merrIndeksineTabeles() == 2)
				{
					c.setLocation(x,y - (c.getHeight() + gap));
				}
				else if(c.merrIndeksineTabeles() == 1)
				{
					c.setLocation(x  + c.getWidth() + gap ,y);
				}
				else if(c.merrIndeksineTabeles() == 3)
				{
					c.setLocation(x  -( c.getWidth() + gap) ,y);
				}
			}
		}
	}

	@Override
	public Dimension minimumLayoutSize(Container parent) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Dimension preferredLayoutSize(Container parent) {
		// TODO Auto-generated method stub
		int dimx = parent.getWidth() / 2;
		int dimy = parent.getHeight()/2;
		return new Dimension(dimx,dimy);
	}

	@Override
	public void removeLayoutComponent(Component comp) {
		// TODO Auto-generated method stub
		
	}
	
	

}
