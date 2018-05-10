package brickbreaker.view;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import brickbreaker.position.Position;
import brickbreaker.sound.Sound;

public class MenuButton extends JButton implements MouseListener{
	private Color bColor;
	private Position pos;
	private Sound buttonSound = new Sound("/res/sounds/hover.wav",-15.0f);
	
	public MenuButton(String text, int x, int y, int w, int h, Color bColor, ImageIcon img, ActionListener al) {
		pos = new Position(x, y, w, h);
		this.setCursor(new Cursor(12));
		this.setFocusable(false);
		this.setText(text);
		this.setIcon(img);
		this.setFont(new Font("Charlemagne Std", Font.BOLD, 20));
		this.setForeground(Color.black);
		this.setBackground(bColor);
		this.setBounds(x, y, w, h);
		this.addActionListener(al);
		this.setVisible(true);
		this.addMouseListener(this);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		this.setBounds(pos.getPosX()-20,pos.getPosY(),pos.getWidth()+40,30);
		//Sound.startHover();
		buttonSound.start();
	}

	@Override
	public void mouseExited(MouseEvent e) {
		this.setBounds(pos.getPosX(),pos.getPosY(),pos.getWidth(),30);
	}

	@Override
	public void mousePressed(MouseEvent e) {
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		
	}
	
}
