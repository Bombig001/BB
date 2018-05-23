package brickbreaker.controller;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.Timer;

import brickbreaker.view.Game;
import brickbreaker.view.Menu;

public class GameController {
	private Game game;
	private static JFrame window;
	private static Menu mainMenu;
	public static Integer defWidth;
	public static Integer width;
	public static Integer height;
	
	public static void main(String[] args) {
		GameController gc = new GameController();
		gc.initGameController();
	}
	
	private void initGameController() {
		window = new JFrame("Brick Breaker Ultimate");
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		defWidth = (int)screenSize.getWidth();
		width = (int)(screenSize.getWidth() -16) / 2;
		height = 720;
		game = new Game(window);
		mainMenu = new Menu(game);
		window.setSize(width, height);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setResizable(false);
		window.getContentPane().setBackground(Color.darkGray);
		window.add(game);
		mainMenu.start();
		window.setVisible(true);
		Timer t = new Timer(15,game);
		t.start();
	}

	public static JFrame getWindow() {
		return window;
	}

	public static Menu getMainMenu() {
		return mainMenu;
	}

}