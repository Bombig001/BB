package brickbreaker.controller;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.Timer;

import brickbreaker.view.Game;
import brickbreaker.view.Menu;

/**
 * Controllerklasse, hier wird das Spielfenster erstellt .
 * @author Savas Celik
 *
 */
public class GameController {
	
	/**
	 * Die Konstante fuer die Spielversion.
	 */
	private static final float gameVersion = 4.7f;
	
	/**
	 * Klassenvariable, Das Game Objekt ein JComponent
	 * Alles wird hier darauf gezeichnet.
	 */
	private static Game game;
	
	/**
	 * Klassenvariable. Das JFrame Objekt, um ein Fenster zu erzeugen.
	 */
	private static JFrame window;
	
	/**
	 * Klassenvariable, fuer die Breite des Monitors. 
	 */
	private static Integer defWidth;
	
	/**
	 * Klassenvariable, fuer die Breite des Spielfensters.
	 */
	private static Integer width;
	
	/**
	 * Klassenvariable, fuer die Hoehe des Spielfensters.
	 */
	private static Integer height;
	
	/**
	 * Die Main-Methode
	 * @param args
	 */
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
	
	/**
	 * Klassenmethode, Liefert den wert der Spielversion als float.
	 * 
	 * @return gameVersion als float.
	 */
	public static float getGameVersion() {
		return gameVersion;
	}
	
	/**
	 * Klassenmethode, Liefert das Fenster als JFrame.
	 * 
	 * @return window als JFrame.
	 */
	public static JFrame getWindow() {
		return window;
	}
	
	/**
	 * Klassenmethode, Liefert die Standardbreite des Monitors als Integer.
	 * 
	 * @return defWidth als Integer.
	 */
	public static Integer getDefWidth() {
		return defWidth;
	}
	
	/**
	 * Klassenmethode, Liefert die Breite des Spielfensters ( Hauptmenue wird nicht als Spielfenster angesehen) als Integer.
	 * 
	 * @return width als Integer.
	 */
	public static Integer getWidth() {
		return width;
	}
	
	/**
	 * Klassenmethode
	 * 
	 * @param width - Ein Integer, welcher die Breite des Spielfensters verändert.
	 */
	public static void setWidth(Integer width) {
		GameController.width = width;
	}
	
	/**
	 * Klassenmethode, Liefert die Hoehe des Spielfensters als Integer.
	 * 
	 * @return height als Integer.
	 */
	public static Integer getHeight() {
		return height;
	}

}