package brickbreaker.controller;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.Timer;

import brickbreaker.view.Game;
import brickbreaker.view.Menu;

public class GameController {
	private static final float gameVersion = 4.7f;
	private static Game game;
	private static JFrame window;
	private static Integer defWidth;
	private static Integer width;
	private static Integer height;
	
	
	public static void main(String[] args) {
		Menu mainMenu; // Lokale Variable, fuer das Menue.
		
		window = new JFrame("Brick Breaker v"+gameVersion);	// Hier wird das Fenster erstellt und ein Titel bestimmt.
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();	// Die Breite und die Hoehe des Monitors heraus gefunden und abgespeichert.
		defWidth = (int)screenSize.getWidth();	// Die Breite des Monitors wird ins defWidth gespeichert.
		width = (int)(screenSize.getWidth() - 16) / 2;	// Die Breite des Spielfensters wird ausgerechnet.
		height = 720;
		game = new Game(window);	// game wird instanziiert.
		mainMenu = new Menu(game);	// mainMenu wird instanziiert.
		window.setSize(width, height);	// Die Breite und die Hoehe des Fensters wird verändert.
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	// Beim Close Operation also die x im JFrame fenster soll das Programm schlissen.
		window.setResizable(false);	// Wird bestimmt ob das Fenster verkleinert oder vergroessert werden kann.
		window.add(game);	// game wird ins JFrame Fenster hinzugefuegt.
		mainMenu.start();	// mainMenu wird gestartet.
		window.setVisible(true);	// JFrame Fenster wird sichtbar.
		Timer t = new Timer(15,game);	// In welcher Zeitspanne der Actionlistener vom game ausgefuehrt wird.
		t.start();	// timer wird gestartet.
	}
	
	public static float getGameVersion() {
		return gameVersion;
	}
	
	public static JFrame getWindow() {
		return window;
	}
	
	public static Integer getDefWidth() {
		return defWidth;
	}
	
	public static Integer getWidth() {
		return width;
	}
	
	public static void setWidth(Integer width) {
		GameController.width = width;
	}
	
	public static Integer getHeight() {
		return height;
	}

}