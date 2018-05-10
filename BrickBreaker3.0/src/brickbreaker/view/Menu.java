package brickbreaker.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JSlider;
import javax.swing.JTextField;

import brickbreaker.model.Ball;
import brickbreaker.model.Brick;
import brickbreaker.model.Paddle;
import brickbreaker.sound.Sound;

public class Menu  implements ActionListener{
	private MenuButton singleBtn;
	private MenuButton multiBtn;
	private MenuButton computerBtn;
	private MenuButton settingsBtn;
	private MenuButton infoBtn;
	private ImageIcon singleIcon;
	private ImageIcon multiIcon;
	private ImageIcon computerIcon;
	private ImageIcon infoIcon;
	private ImageIcon logoIcon;
	private ImageIcon settingsIcon;
	private ImageIcon bgIcon;
	private Game jf;
	private JLabel bgLogo;
	private JLabel bg;
	private JLabel label = new JLabel("TOPOMEDICS ©");
	private JLabel label1 = new JLabel("<html>&nbsp;&nbsp;&nbsp;Creator:<br/>Savas&nbsp;Celik</html>");
	private Sound menuSound;
	
	public Menu(Game jf) {
		this.jf = jf;
		//jf.getConte
		label.setForeground(new Color(0x83a5db));
		label.setFont(new Font("Charlemagne Std", Font.BOLD, 12));
		label.setBounds(10,666,200,20);
		
		
		label1.setForeground(new Color(0x83a5db));
		label1.setFont(new Font("Charlemagne Std", Font.BOLD, 12));
		label1.setBounds(620,620,90,100);
		
		singleIcon = new ImageIcon(this.getClass().getResource("/res/images/buttons/onePerson.png"));
		multiIcon = new ImageIcon(this.getClass().getResource("/res/images/buttons/twoPerson.png"));
		computerIcon = new ImageIcon(this.getClass().getResource("/res/images/buttons/computer.png"));
		settingsIcon = new ImageIcon(this.getClass().getResource("/res/images/buttons/settings.png"));
		infoIcon = new ImageIcon(this.getClass().getResource("/res/images/buttons/about1.png"));
		logoIcon = new ImageIcon(this.getClass().getResource("/res/images/background/logo.png"));
		//bgIcon = new ImageIcon("res/background/bg1.jpg");
		bgIcon = new ImageIcon(this.getClass().getResource("/res/images/background/bg1.jpg"));
		bg = new JLabel(bgIcon);
		bg.setBounds(0, 0, 720, 720);
		
		bgLogo = new JLabel(logoIcon);
		bgLogo.setBounds(0, 100, 720, 190);
		
		singleBtn = new MenuButton("Einzelspieler", 210, 400, 300, 30, new Color(0x738ebc), singleIcon, this);
		multiBtn = new MenuButton("Spieler vs. Spieler", 210, 450, 300, 30, new Color(0x738ebc), multiIcon, this);
		computerBtn = new MenuButton("Spieler vs. PC", 210, 500, 300, 30, new Color(0x738ebc), computerIcon, this);
		settingsBtn = new MenuButton("Einstellungen", 210, 550, 300, 30, new Color(0x738ebc), settingsIcon, this);
		infoBtn = new MenuButton("Info", 210, 600, 300, 30, new Color(0x738ebc), infoIcon, this);
		
		jf.add(singleBtn);
		jf.add(multiBtn);
		jf.add(computerBtn);
		jf.add(settingsBtn);
		jf.add(infoBtn);
		jf.add(label);
		jf.add(label1);
		jf.add(bgLogo);
		jf.add(bg);
		menuSound = new Sound("/res/sounds/op.wav",-30.0f);
		menuSound.loop();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == settingsBtn) {
			JSlider[] SoundSliders = new JSlider[2];
			Object[] buttons = {"Anwenden", "Werkeinstellung", "Abbrechen"}; 
			int selected;
			
			for (int i = 0; i < SoundSliders.length; i++) {
				JSlider SoundSlider = new JSlider();
				SoundSliders[i] = SoundSlider;
				SoundSliders[i].setMaximum(0);
				SoundSliders[i].setMinimum(-60);
			}
			SoundSliders[0].setValue((int)menuSound.getVolume());
			SoundSliders[1].setValue((int)Ball.getBallSound().getVolume());
			
			Object[] message = {"Hintergrundmusik", SoundSliders[0], "Ball: Abprallung", SoundSliders[1]};
			
	        selected = JOptionPane.showOptionDialog(null, message, "Einstellungen", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, settingsIcon, buttons, null);

	        if (selected == 0) {
	        	menuSound.setVolume(SoundSliders[0].getValue());
	        	Ball.getBallSound().setVolume(SoundSliders[1].getValue());
	        	Brick.getBrickToBallSound0().setVolume(SoundSliders[1].getValue());
	        	Brick.getBrickToBallSound1().setVolume(SoundSliders[1].getValue());
	        	Paddle.getPaddleToBallSound().setVolume(SoundSliders[1].getValue());
	        } else if (selected == 1) {
	        	menuSound.setVolume(menuSound.getDefVolume());
	        	Ball.getBallSound().setVolume(Ball.getBallSound().getDefVolume());
	        	Brick.getBrickToBallSound0().setVolume(Brick.getBrickToBallSound0().getDefVolume());
	        	Brick.getBrickToBallSound1().setVolume(Brick.getBrickToBallSound1().getDefVolume());
	        	Paddle.getPaddleToBallSound().setVolume(Paddle.getPaddleToBallSound().getDefVolume());
	        }
			
		}
		
		if ( e.getSource() == infoBtn) {
			
			JOptionPane.showMessageDialog(null, "<html>Creator: Savas Celik<hr/>"
					+ "Powered By: TOPOMEDICS ©<hr/>"
					+ "Ort: WISS, Zürich<hr/><img src=\"http://cultofthepartyparrot.com/parrots/hd/parrot.gif\"></html>", "Info", 1);
			
		}
		
		if ( e.getSource() == singleBtn) {
			do {
				Game.spieler1 = JOptionPane.showInputDialog(null, "<html>Namen eingeben: <br/><i style=\"color:red\"><sub>maximal 8 Zeichen</sub></i></html>", "Spielername", JOptionPane.PLAIN_MESSAGE);
				if(Game.spieler1 == null) {
					Game.spieler1 = "abbruch";
				}
			} while (Game.spieler1.equals("") || Game.spieler1.length() > 8);
			
			if(!Game.spieler1.equals("abbruch")) {
				Game.singleplayerGameStarted = true;
				jf.removeAll();
			}
		}
		
		if ( e.getSource() == multiBtn) {
			int selected;
			JTextField player1 = new JTextField();
			JTextField player2 = new JTextField();
			String player1Warning = "";
			String player2Warning = "";
			
			do {
				Object[] message = {"<html>Bitte Namen eingeben!<br/><i style=\"color:red\"><sub>maximal 8 Zeichen</sub></i></html>","Spieler 1:", player1, player1Warning, 
		        				"Spieler 2:", player2,player2Warning};
			selected = JOptionPane.showConfirmDialog(null, message, "Spielernamen", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
			
			if (!player1.getText().isEmpty() && !player1.getText().matches("^[a-zA-Z]+$")) {
				player1.setText("");
				player1Warning = "<html><p style=\"color:red\">Nur Buchstaben erlaubt!</p></html>";
			} else {
				player1Warning = "";
			}
			
			if (!player2.getText().isEmpty() && !player2.getText().matches("^[a-zA-Z]+$")) {
				player2.setText("");
				player2Warning = "<html><p style=\"color:red\">Nur Buchstaben erlaubt!</p></html>";
			} else {
				player2Warning = "";
			}
			
			} while (selected == 0 && (player1.getText().isEmpty() || player1.getText().length() > 8 || player2.getText().isEmpty() || player2.getText().length() > 8));

			
			if (selected == 10) {
				Game.spieler1 = player1.getText();
				Game.spieler2 = player2.getText();
				Game.multiplayerGameStarted = true;
				jf.removeAll();
			}
//			JTextField spieler1 = new JTextField();
//			JTextField spieler2 = new JTextField();
//			JOptionPane pane;
//			do {
//	                Object[] message = {"<html>Bitte Namen eingeben!<br/><i style=\"color:red\"><sub>maximal 8 Zeichen</sub></i></html>","Spieler 1:", spieler1, 
//	        		"Spieler 2:", spieler2,};
//
//	                pane = new JOptionPane( message, 
//	                                                JOptionPane.PLAIN_MESSAGE, 
//	                                                JOptionPane.OK_CANCEL_OPTION);
//	                pane.createDialog(null, "Spielernamen").setVisible(true);
//	                if (pane.getValue() == null || pane.getValue().hashCode() == 2) {
//	                	Game.spieler1 = "abbruch";
//	                	Game.spieler2 = "abbruch";
//	                }else {
//	                	Game.spieler1 = spieler1.getText();
//	                	Game.spieler2 = spieler2.getText();
//	                }
//	                
//	                
//			} while(Game.spieler1.equals("") || Game.spieler1.length() > 8);
//			
//			if(!Game.spieler1.equals("abbruch") && !Game.spieler2.equals("abbruch")) {
//				Game.multiplayerGameStarted = true;
//				jf.removeAll();
//			}
		}
		
		if (e.getSource() == computerBtn) {
//			Object[] options = {"Einfach", "Mittel", "Schwer", "God Like", "Abbrechen"};
//          int selected = JOptionPane.showOptionDialog(null,
//                                                      "Treffen Sie eine Auswahl",
//                                                      "Schwierigkeistgrad",
//						    JOptionPane.DEFAULT_OPTION, 
//                                                      JOptionPane.INFORMATION_MESSAGE, 
//						    computerIcon, options, options[0]);
			
			// oder
			
			String[] options = {"Einfach", "Mittel", "Schwer", "God Like"};
			Object gewe = JOptionPane.showInputDialog(null, "Schwierigkeistgrad", "Title", 1, computerIcon, options, options);
		}
	}

}
