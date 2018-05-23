package brickbreaker.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import org.w3c.dom.Text;

import brickbreaker.controller.GameController;
import brickbreaker.model.Ball;
import brickbreaker.model.Brick;
import brickbreaker.model.Paddle;
import brickbreaker.player.Player;
import brickbreaker.player.Players;
import brickbreaker.sound.Sound;

public class Menu  implements ActionListener{
	private MenuButton singleBtn;
	private MenuButton multiBtn;
	private MenuButton computerBtn;
	private MenuButton howToPlayBtn;
	private MenuButton settingsBtn;
	private MenuButton impressumBtn;
	private ImageIcon singleIcon;
	private ImageIcon multiIcon;
	private ImageIcon computerIcon;
	private ImageIcon howToPlayIcon;
	private ImageIcon settingsIcon;
	private ImageIcon impressumIcon;
	private ImageIcon logoIcon;
	private ImageIcon bgIcon;
	private ImageIcon homeIcon;
	private MenuButton mainMenuButton;
	private Game game;
	private JLabel bgLogo;
	private JLabel bg;
	private JLabel topomedicsLabel = new JLabel("TOPOMEDICS © Copyright 2018");
	private JLabel creatorLabel = new JLabel("<html>&nbsp;&nbsp;&nbsp;Creator:<br/>Savas&nbsp;Celik</html>");
	private JLabel versionLabel = new JLabel("Version " + GameController.getGameVersion());
	private static Sound menuSound = new Sound("/res/sounds/op.wav",-30.0f);
	
	public Menu(Game game) {
		this.game = game;
		homeIcon = new ImageIcon(this.getClass().getResource("/res/images/buttons/home.png"));
		mainMenuButton = new MenuButton("Main Menu", GameController.getWidth().intValue()/2-85, 3, 170, 30, Color.ORANGE, homeIcon, this);
		
		topomedicsLabel.setForeground(new Color(0x83a5db));
		topomedicsLabel.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 12));
		topomedicsLabel.setBounds(10,666,200,20);
		
		
		creatorLabel.setForeground(new Color(0x83a5db));
		creatorLabel.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 12));
		creatorLabel.setBounds(640,620,90,100);
		
		versionLabel.setForeground(new Color(0x83a5db));
		versionLabel.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 12));
		versionLabel.setBounds(330,666,80,20);
		
		singleIcon = new ImageIcon(this.getClass().getResource("/res/images/buttons/onePerson.png"));
		multiIcon = new ImageIcon(this.getClass().getResource("/res/images/buttons/twoPerson.png"));
		computerIcon = new ImageIcon(this.getClass().getResource("/res/images/buttons/computer.png"));
		howToPlayIcon = new ImageIcon(this.getClass().getResource("/res/images/buttons/game.png"));
		settingsIcon = new ImageIcon(this.getClass().getResource("/res/images/buttons/settings.png"));
		impressumIcon = new ImageIcon(this.getClass().getResource("/res/images/buttons/about1.png"));
		logoIcon = new ImageIcon(this.getClass().getResource("/res/images/background/logo.png"));
		bgIcon = new ImageIcon(this.getClass().getResource("/res/images/background/bg1.jpg"));
		bg = new JLabel(bgIcon);
		bg.setBounds(0, 0, 720, 720);
		
		bgLogo = new JLabel(logoIcon);
		bgLogo.setBounds(0, 100, 720, 190);
		
		singleBtn = new MenuButton("Einzelspieler", 210, 350, 300, 30, new Color(0x738ebc), singleIcon, this);
		multiBtn = new MenuButton("Spieler vs. Spieler", 210, 400, 300, 30, new Color(0x738ebc), multiIcon, this);
		computerBtn = new MenuButton("Spieler vs. PC", 210, 450, 300, 30, new Color(0x738ebc), computerIcon, this);
		howToPlayBtn = new MenuButton("Spielanleitung", 210, 500, 300, 30, new Color(0x738ebc), howToPlayIcon, this);
		settingsBtn = new MenuButton("Einstellungen", 210, 550, 300, 30, new Color(0x738ebc), settingsIcon, this);
		impressumBtn = new MenuButton("Impressum", 210, 600, 300, 30, new Color(0x738ebc), impressumIcon, this);
		menuSound.loop();
	}
	
	public void start() {
		game.removeAll();
		GameController.getWindow().setSize(720, 720);
		GameController.getWindow().setLocationRelativeTo(null);
		GameController.setWidth((GameController.getDefWidth().intValue() - 16) / 2);
		game.setMultiplayerGame(false);
		game.setSingleplayerGame(false);
		game.add(singleBtn);
		game.add(multiBtn);
		game.add(computerBtn);
		game.add(howToPlayBtn);
		game.add(settingsBtn);
		game.add(impressumBtn);
		game.add(topomedicsLabel);
		game.add(creatorLabel);
		game.add(versionLabel);
		game.add(bgLogo);
		game.add(bg);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		// Single button
		if ( e.getSource() == singleBtn) {
			int selected;
			Integer[] levels = {1, 2, 3, 4, 5, 6};
			JTextField player1Name = new JTextField();
			JComboBox<Integer> levelChoise = new JComboBox<Integer>(levels);
			String player1Warning = "";
			
			do {
				Object[] message = {"<html>Namen eingeben(*): <br/><i style=\"color:red;\"><sub>maximal 8 Zeichen</sub></i></html>", player1Name,player1Warning,
						"<html>Level auswählen: <br/></html>",levelChoise, "<html><i style=\"font-size:8.5px;\">Felder mit (*) sind Pflichtfelder!</sub></i></html>"};
				selected = JOptionPane.showConfirmDialog(null, message, "Einzelspieler", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
				
				if(player1Name.getText().isEmpty()) {
					player1Warning = "<html><p style=\"color:red;font-size:8.5px;\">Bitte Namen eingeben!</p></html>";
				}
				
				if (!player1Name.getText().isEmpty() && !player1Name.getText().matches("^[a-zA-Züèöéäàç]+$")) {
					player1Name.setText("");
					player1Warning = "<html><p style=\"color:red;font-size:8.5px;\">Nur Buchstaben erlaubt!</p></html>";
				} else if (!player1Name.getText().isEmpty()){
					player1Warning = "";
				}
				
			} while (selected == 0 && (player1Name.getText().isEmpty() || player1Name.getText().length() > 8));
			
			if(selected == 0) {
				game.setLevel((int)levelChoise.getSelectedItem());
				game.getPlayer1().getCurrentLevel().setCurrentLevel(game.getLevel());
				game.getPlayer1().loadUpStage();
				game.getPlayer1().setName(player1Name.getText());
				game.setSingleplayerGame(true);
				game.removeAll();
				GameController.getWindow().setSize((GameController.getDefWidth().intValue() - 16) / 2, 720);
				game.add(mainMenuButton);
				
			}
		}
		

		if (e.getSource() == mainMenuButton) {
			GameController.getMainMenu().start();
		}
		
		// Multiplayer button
		if ( e.getSource() == multiBtn) {
			int selected;
			JTextField player1Name = new JTextField();
			JTextField player2Name = new JTextField();
			String player1Warning = "";
			String player2Warning = "";
			
			do {
				Object[] message = {"<html>Bitte Namen eingeben!<br/><i style=\"color:red\"><sub>maximal 8 Zeichen</sub></i></html>","Spieler 1(*):", player1Name, player1Warning, 
		        				"Spieler 2(*):", player2Name,player2Warning,"<html><i style=\"font-size:8.5px;\">Felder mit (*) sind Pflichtfelder!</sub></i></html>"};
			selected = JOptionPane.showConfirmDialog(null, message, "Spieler vs. Spieler", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
			
			if(player1Name.getText().isEmpty()) {
				player1Warning = "<html><p style=\"color:red;font-size:8.5px;\">Bitte Namen eingeben!</p></html>";
			}
			
			if(player2Name.getText().isEmpty()) {
				player2Warning = "<html><p style=\"color:red;font-size:8.5px;\">Bitte Namen eingeben!</p></html>";
			}
			
			if (!player1Name.getText().isEmpty() && !player1Name.getText().matches("^[a-zA-Züèöéäàç]+$")) {
				player1Name.setText("");
				player1Warning = "<html><p style=\"color:red;font-size:8.5px;\">Nur Buchstaben erlaubt!</p></html>";
			} else if (!player1Name.getText().isEmpty()){
				player1Warning = "";
			}
			
			if (!player2Name.getText().isEmpty() && !player2Name.getText().matches("^[a-zA-Züèöéäàç]+$")) {
				player2Name.setText("");
				player2Warning = "<html><p style=\"color:red;font-size:8.5px;\">Nur Buchstaben erlaubt!</p></html>";
			} else if (!player2Name.getText().isEmpty()){
				player2Warning = "";
			}
			
			} while (selected == 0 && (player1Name.getText().isEmpty() || player1Name.getText().length() > 8 || player2Name.getText().isEmpty() || player2Name.getText().length() > 8));

			
			if (selected == 0) {
				game.setLevel(1);
				game.getPlayer1().getCurrentLevel().setCurrentLevel(game.getLevel());
				game.getPlayer1().loadUpStage();
				game.getPlayer1().setName(player1Name.getText());
				
				game.getPlayer2().setPlayertyp(Players.PLAYER2);
				game.getPlayer2().getCurrentLevel().setCurrentLevel(game.getLevel());
				game.getPlayer2().loadUpStage();
				game.getPlayer2().setName(player2Name.getText());
				
				game.setMultiplayerGame(true);
				
				game.removeAll();
				GameController.getWindow().setSize(GameController.getDefWidth().intValue(), 720);
				GameController.getWindow().setLocationRelativeTo(null);
				game.add(mainMenuButton);
			}
		}
		
		// computer button
		if (e.getSource() == computerBtn) {
			String[] options = {"Einfach", "Mittel", "Schwer", "Profi"};
			int selected;
			JTextField player1Name = new JTextField();
			String player1Warning = "";
			JComboBox<String> choise = new JComboBox<String>(options);
			
			do {
				Object[] message = {"<html>Bitte Namen eingeben!<br/><i style=\"color:red;\"><sub>maximal 8 Zeichen</sub></i></html>","Spieler 1(*):", player1Name, player1Warning, 
	    				"Schwierigkeistgrad: ", choise, "<html><i style=\"font-size:8.5px;\">Felder mit (*) sind Pflichtfelder!</sub></i></html>"};
				selected = JOptionPane.showConfirmDialog(null, message, "Spieler vs. PC", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
				
				if(player1Name.getText().isEmpty()) {
					player1Warning = "<html><p style=\"color:red;font-size:8.5px;\">Bitte Namen eingeben!</p></html>";
				}
				
				if (!player1Name.getText().isEmpty() && !player1Name.getText().matches("^[a-zA-Züèöéäàç]+$")) {
					player1Name.setText("");
					player1Warning = "<html><p style=\"color:red;font-size:8.5px;\">Nur Buchstaben erlaubt!</p></html>";
				} else if (!player1Name.getText().isEmpty()){
					player1Warning = "";
				}
				
			} while (selected == 0 && (player1Name.getText().isEmpty() || player1Name.getText().length() > 8));
			
			if (selected == 0) {
				game.setLevel(1);
				game.getPlayer1().getCurrentLevel().setCurrentLevel(game.getLevel());
				game.getPlayer1().loadUpStage();
				game.getPlayer1().setName(player1Name.getText());
				
				game.getPlayer2().setPlayertyp(Players.COMPUTER);
				game.getPlayer2().getCurrentLevel().setCurrentLevel(game.getLevel());
				game.getPlayer2().loadUpStage();
				game.getPlayer2().setName("Computer");
				
				game.setMultiplayerGame(true);
				
				if (choise.getSelectedItem().equals("Einfach")) {
					game.getPlayer2().getStrgy().setChance(70);
				} else if (choise.getSelectedItem().equals("Mittel")) {
					game.getPlayer2().getStrgy().setChance(80);
				} else if (choise.getSelectedItem().equals("Schwer")) {
					game.getPlayer2().getStrgy().setChance(90);
				} else if (choise.getSelectedItem().equals("Profi")) {
					game.getPlayer2().getStrgy().setChance(100);
				}
				
				game.setMultiplayerGame(true);
				game.removeAll();
				GameController.getWindow().setSize(GameController.getDefWidth().intValue(), 720);
				game.add(mainMenuButton);
			}
		}
		
		// Spielanleitung Button
		if ( e.getSource() == howToPlayBtn) {
			ImageIcon paddleIcon = new ImageIcon(this.getClass().getResource("/res/images/paddle/paddle1state0.png"));
			ImageIcon brickStandardIcon = new ImageIcon(this.getClass().getResource("/res/images/bricks/green1.png"));
			ImageIcon brickMetalIcon = new ImageIcon(this.getClass().getResource("/res/images/bricks/grey2.png"));
			ImageIcon ballIcon = new ImageIcon(this.getClass().getResource("/res/images/ball/ball0.png"));
			ImageIcon gameIcon = new ImageIcon(this.getClass().getResource("/res/images/spielsteuerung/gameplay.png"));
			ImageIcon rightKey = new ImageIcon(this.getClass().getResource("/res/images/spielsteuerung/rightKey.png"));
			ImageIcon leftKey = new ImageIcon(this.getClass().getResource("/res/images/spielsteuerung/leftKey.png"));
			ImageIcon enterKey = new ImageIcon(this.getClass().getResource("/res/images/spielsteuerung/enter2.png"));
			ImageIcon dKey = new ImageIcon(this.getClass().getResource("/res/images/spielsteuerung/dKey.png"));
			ImageIcon aKey = new ImageIcon(this.getClass().getResource("/res/images/spielsteuerung/aKey.png"));
			ImageIcon spaceKey = new ImageIcon(this.getClass().getResource("/res/images/spielsteuerung/spaceKey.png"));
			ImageIcon paddleExtendedIcon = new ImageIcon(this.getClass().getResource("/res/images/powerups/extendpaddle.png"));
			ImageIcon paddleShortenedIcon = new ImageIcon(this.getClass().getResource("/res/images/powerups/shortpaddle.png"));
			ImageIcon ballFastIcon = new ImageIcon(this.getClass().getResource("/res/images/powerups/fastball.png"));
			ImageIcon ballSlowIcon = new ImageIcon(this.getClass().getResource("/res/images/powerups/slowball.png"));
			ImageIcon ballMeltingIcon = new ImageIcon(this.getClass().getResource("/res/images/powerups/meltingball.png"));
			ImageIcon paddleMissileIcon = new ImageIcon(this.getClass().getResource("/res/images/powerups/paddlemissle.png"));

			Object[] message = {"<html><h2>Lass den Ball vom Blaken abprallen und <br/> zerbreche die Blöcke um das Level abzuschliessen</h2></html>",gameIcon,
					"<html><h1>Spiel Items</h1></html>",
					paddleIcon,"<html>Das ist der Balken, mit dem man den Ball mittels Abprallungen steuern kann<br/><br/></html>",
					brickStandardIcon,"<html>Standard Block, wird bei der ersten Kollision mit dem Ball zerstört<br/><br/></html>",
					brickMetalIcon,"<html>Metal Block, wird erst bei der dritten Kollision mit dem Ball zerstört<br/><br/></html>",
					ballIcon,"<html>Das ist der Ball, benutze sie um Blöcke zu zerstören<br/><br/></html>",
					"<html><h1>Spielsteuerung</h1></html>",
					"<html><h3 style=\"color:green;\">Spieler 1</h3></html>",
					rightKey,"<html>Bewegt den Balken nach rechts<br/><br/></html>",
					leftKey,"<html>Bewegt den Balken nach links<br/><br/></html>",
					enterKey,"<html>Setz den Ball in Bewegung<br/><br/></html>",
					"<html><h3 style=\"color:green;\">Spieler 2</h3></html>",
					dKey,"<html>Bewegt den Balken nach rechts<br/><br/></html>",
					aKey,"<html>Bewegt den Balken nach links<br/><br/></html>",
					spaceKey,"<html>Setz den Ball in Bewegung<br/><br/></html>",
					"<html><h1>Power Ups</h1></html>",
					paddleExtendedIcon,"<html>[ Power Up - Langer Balken ]<br/>Verlängert den Balken<br/><br/></html>",
					paddleShortenedIcon,"<html>[ Power Up - Kurzer Balken ]<br/>Verkleinert den Balken<br/><br/></html>",
					ballFastIcon,"<html>[ Power Up - Schneller Ball ]<br/>Erhöht die Geschwindigkeit des Balles<br/><br/></html>",
					ballSlowIcon,"<html>[ Power Up - Langsamer Ball ]<br/>Verlangsamt die Geschwindigkeit des Balles<br/><br/></html>",
					ballMeltingIcon,"<html>[ Power Up - Schmelzender Ball ]<br/>Der Ball durchbohrt alle Blöcke und prallt davon nicht ab<br/><br/></html>",
					paddleMissileIcon,"<html>[ Power Up - Rakete ]<br/>Der Balken feuert Raketen ab<br/><br/></html>"};
			
			JList<Object> gameplayList = new JList<Object>(message);
			gameplayList.setForeground(Color.black);
			JScrollPane scrollPane = new JScrollPane(gameplayList);
			scrollPane.setPreferredSize( new Dimension( 500, 500 ) );
			JOptionPane.showMessageDialog(null, scrollPane, "Spielanleitung",  
			                                       JOptionPane.PLAIN_MESSAGE);
		}
		
		// Settings button
		if (e.getSource() == settingsBtn) {
			JSlider[] soundSliders = new JSlider[2];
			Object[] buttons = {"Anwenden", "Werkeinstellung", "Abbrechen"}; 
			int selected;
			
			for (int i = 0; i < soundSliders.length; i++) {
				JSlider SoundSlider = new JSlider();
				soundSliders[i] = SoundSlider;
				soundSliders[i].setMaximum(0);
				soundSliders[i].setMinimum(-60);
			}
			soundSliders[0].setValue((int)menuSound.getVolume());
			soundSliders[1].setValue((int)Ball.getBallSound().getVolume());
			
			Object[] message = {"Hintergrundmusik", soundSliders[0], "Ball: Abprallung", soundSliders[1]};
			
	        selected = JOptionPane.showOptionDialog(null, message, "Einstellungen", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, settingsIcon, buttons, null);

	        if (selected == 0) {
	        	menuSound.setVolume(soundSliders[0].getValue());
	        	Ball.getBallSound().setVolume(soundSliders[1].getValue());
	        	Brick.getBrickToBallSound0().setVolume(soundSliders[1].getValue());
	        	Brick.getBrickToBallSound1().setVolume(soundSliders[1].getValue());
	        	Paddle.getPaddleToBallSound().setVolume(soundSliders[1].getValue());
	        } else if (selected == 1) {
	        	menuSound.setVolume(menuSound.getDefVolume());
	        	Ball.getBallSound().setVolume(Ball.getBallSound().getDefVolume());
	        	Brick.getBrickToBallSound0().setVolume(Brick.getBrickToBallSound0().getDefVolume());
	        	Brick.getBrickToBallSound1().setVolume(Brick.getBrickToBallSound1().getDefVolume());
	        	Paddle.getPaddleToBallSound().setVolume(Paddle.getPaddleToBallSound().getDefVolume());
	        }
		}
		

		// impressum button
		if ( e.getSource() == impressumBtn) {
			
			JOptionPane.showMessageDialog(null, "<html><h1>Impressum</h1><br/>"
					+ "<h3>Kontakt:</h3>"
					+ "Savas Celik<br/>"
					+ "Ifangstrasse 58<br/>"
					+ "8153, Rümlang<br/>"
					+ "savas.celik000@gmail.com<br/><hr/>"
					+ "<h3>Unterstützt von:</h3>"
					+ "TOPOMEDICS<br/>"
					+ "Markus Ruggiero<br/>"
					+ "WISS, Zürich<br/><hr/><br/>" 
					+ "Version: "+ GameController.getGameVersion(), "Impressum", JOptionPane.INFORMATION_MESSAGE);
			
		}
		
	}

}
