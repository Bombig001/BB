package brickbreaker.controller;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.Timer;

import brickbreaker.view.Game;
import brickbreaker.view.Menu;

public class GameController {
	public static String spieler1;
	public static String spieler2;
	public static int windowWidth = 720;
	public static int windowHeight = 720;
	
	public static void main(String[] args) {
		GameController gc = new GameController();
		gc.InitWindow();
	}
	
	public void InitWindow() {
		Game game;
		JFrame window = new JFrame("Brick Breaker Ultimate");
		game = new Game(window);
		window.setSize(windowWidth, windowHeight);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setResizable(false);
		window.setLocationRelativeTo(null);
		window.getContentPane().setBackground(Color.darkGray);
		window.add(game);
		//computerIcon = new ImageIcon(GameController.getClass().getResource("/res/images/buttons/computer.png"));
		Image computerIcon = new ImageIcon(this.getClass().getResource("/res/images/luffy1.png")).getImage();
		window.setIconImage(computerIcon);
		window.setVisible(true);
		Timer t = new Timer(10,game);
		t.start();
	}

}