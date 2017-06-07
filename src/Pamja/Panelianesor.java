package Pamja;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.Font;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import Logjika.ThirrPerseri;

public class Panelianesor extends JPanel implements KeyListener {

	private JTextField dergoTekstin; 
	private JTextArea  hapesiaeChatit;
	private ThirrPerseri callBack;
	private JScrollPane chatScroll;
	private JList listaELojtareve;
	private DefaultListModel emriILojtareve;
	
	public Panelianesor(ThirrPerseri CallBack) {
	
		this.callBack = CallBack;
		this.setLayout(new BorderLayout(5,5));
		
		this.setPreferredSize(new Dimension(180, 400));
		this.dergoTekstin = new JTextField();
		this.dergoTekstin.addKeyListener(this);
		this.dergoTekstin.setPreferredSize(new Dimension(180,30));
		this.dergoTekstin.setBackground(Color.LIGHT_GRAY);
		this.dergoTekstin.setForeground(Color.BLACK);
		this.dergoTekstin.setBorder(null);
		this.dergoTekstin.setFont(new Font("Arial Black", Font.BOLD, 14));
		
		this.hapesiaeChatit = new JTextArea();
		this.hapesiaeChatit.setEditable(false);
		//this.chatArea.setPreferredSize(new Dimension(200,400));
		this.hapesiaeChatit.setBackground(Color.DARK_GRAY);
		this.hapesiaeChatit.setForeground(Color.orange);
		this.hapesiaeChatit.setLineWrap(true);
		this.hapesiaeChatit.setFont(new Font("Arial Black", Font.BOLD, 14));
		this.chatScroll = new JScrollPane(hapesiaeChatit);
		this.chatScroll.setBorder(null);
		this.chatScroll.getVerticalScrollBar().setBackground(Color.black);
		this.emriILojtareve  = new DefaultListModel();
		this.listaELojtareve = new JList(emriILojtareve);
		this.listaELojtareve.setPreferredSize(new Dimension(200, 80));
		this.listaELojtareve.setBorder(null);
		this.listaELojtareve.setBackground(Color.DARK_GRAY);
		this.listaELojtareve.setForeground(Color.white);
		this.listaELojtareve.setSelectionForeground(Color.DARK_GRAY);
		this.listaELojtareve.setSelectionBackground(Color.orange);
		
		
		this.add(chatScroll,BorderLayout.CENTER);
		this.add(listaELojtareve,BorderLayout.NORTH);
		this.add(dergoTekstin,BorderLayout.SOUTH);
		
	}

	public void AppendToChat(String Str)
	{
		this.hapesiaeChatit.append(Str + "\n");
		this.hapesiaeChatit.setCaretPosition(hapesiaeChatit.getText().length());
	}
	
	public void AddPlayer(String Name , int indx)
	{   //System.out.println("Adding Players" + Name);
		//this.playersNames.insertElementAt(Name, indx);
		this.emriILojtareve.addElement(Name);
		this.repaint();
	}
	
	public void SelectPlayer(int indx)
	{
		this.listaELojtareve.setSelectedIndex(indx);
		this.repaint();
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == e.VK_ENTER)
		{
			this.callBack.ThirrPerseri("C_CHT" + dergoTekstin.getText(), this);
			dergoTekstin.setText("");
			
		}
		
	}
	public JList getPlayersList() {
		return listaELojtareve;
	}
	public void keyReleased(KeyEvent e) {};
	public void keyTyped(KeyEvent e) {};
}
