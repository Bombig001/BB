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
//	public static int windowWidth = 675;
//	public static int windowHeight = 720;
	public static int defWidth;
	public static int width;
	public static int height;
	
	public static void main(String[] args) {
		GameController gc = new GameController();
		gc.initWindow();
	}
	
	private void initWindow() {
		window = new JFrame("Brick Breaker Ultimate");
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		defWidth = (int)screenSize.getWidth();
		System.out.println(defWidth);
		width = (int)(screenSize.getWidth() -16) / 2;
		System.out.println(width);
		height = 720;
		game = new Game(window);
		mainMenu = new Menu(game);
		window.setSize(width, height);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setResizable(false);
		window.getContentPane().setBackground(Color.darkGray);
		window.add(game);
		//computerIcon = new ImageIcon(GameController.getClass().getResource("/res/images/buttons/computer.png"));
		Image computerIcon = new ImageIcon(this.getClass().getResource("/res/images/luffy1.png")).getImage();
		window.setIconImage(computerIcon);
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