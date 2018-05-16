package brickbreaker.sound;

import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class Sound {
	private Clip clip;
	private FloatControl clipControl;
	private String pfad;
	private AudioInputStream audioInputStream;
	private float volume;
	private float defVolume;
	
	public Sound(String pfad, float volume) {
		this.pfad = pfad;
		this.volume = volume;
		defVolume = volume;
		//initClip();
	}
	
	private void initClip() {
		try {
			audioInputStream = AudioSystem.getAudioInputStream(Sound.class.getResource(pfad));
			clip = AudioSystem.getClip();
			clip.open(audioInputStream);
		} catch (UnsupportedAudioFileException | LineUnavailableException | IOException e) {
			e.printStackTrace();
		}
		clipControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
		clipControl.setValue(volume);
	}

	public float getVolume() {
		return volume;
	}
	
	public void setVolume(float volume) {
		this.volume = volume;
		clipControl.setValue(volume);
	}
	
	public float getDefVolume() {
		return defVolume;
	}

	public void start() {
		initClip();
		clip.start();
	}
	
	public void loop() {
		initClip();
		clip.loop(clip.LOOP_CONTINUOUSLY);
	}
	
	public void stop() {
		clip.stop();
	}
	
//	public static void start(){
//		AudioInputStream audioInputStream = null;
//		try {
//			audioInputStream = AudioSystem.getAudioInputStream(Sound.class.getResource("/res/sounds/op.wav"));
//		} catch (UnsupportedAudioFileException | IOException e) {
//			e.printStackTrace();
//		}
//		
//		
//		try {
//			clip = AudioSystem.getClip();
//		} catch (LineUnavailableException e) {
//			e.printStackTrace();
//		}
//		
//		
//		try {
//			clip.open(audioInputStream);
//		} catch (LineUnavailableException | IOException e) {
//			e.printStackTrace();
//		}
//		
//		
//		FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
//		gainControl.setValue(-30.0f); // Reduce volume by 10 decibels.
//		clip.loop(clip.LOOP_CONTINUOUSLY);
//	}
//	
//	public static void startHover(){
//		AudioInputStream audioInputStream = null;
//		try {
//			audioInputStream = AudioSystem.getAudioInputStream(Sound.class.getResource("/res/sounds/hover.wav"));
//		} catch (UnsupportedAudioFileException | IOException e) {
//			e.printStackTrace();
//		}
//		
//		
//		try {
//			clip = AudioSystem.getClip();
//		} catch (LineUnavailableException e) {
//			e.printStackTrace();
//		}
//		
//		
//		try {
//			clip.open(audioInputStream);
//		} catch (LineUnavailableException | IOException e) {
//			e.printStackTrace();
//		}
//		
//		
//		FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
//		gainControl.setValue(-15.0f); // Reduce volume by 10 decibels.
//		clip.start();
//	}
//	
//	public static void startBounce(){
//		AudioInputStream audioInputStream = null;
//		try {
//			audioInputStream = AudioSystem.getAudioInputStream(Sound.class.getResource("/res/sounds/bounce.wav"));
//		} catch (UnsupportedAudioFileException | IOException e) {
//			e.printStackTrace();
//		}
//		
//		
//		try {
//			clip = AudioSystem.getClip();
//		} catch (LineUnavailableException e) {
//			e.printStackTrace();
//		}
//		
//		
//		try {
//			clip.open(audioInputStream);
//		} catch (LineUnavailableException | IOException e) {
//			e.printStackTrace();
//		}
//		
//		
//		FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
//		gainControl.setValue(-15.0f); // Reduce volume by 10 decibels.
//		clip.start();
//	}
//	
//	public static void startPaddle(){
//		AudioInputStream audioInputStream = null;
//		try {
//			audioInputStream = AudioSystem.getAudioInputStream(Sound.class.getResource("/res/sounds/bouncep1.wav"));
//			clip = AudioSystem.getClip();
//			clip.open(audioInputStream);
//		} catch (UnsupportedAudioFileException | LineUnavailableException | IOException e) {
//			e.printStackTrace();
//		}
//		
//		
//		FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
//		gainControl.setValue(-10.0f); // Reduce volume by 10 decibels.
//		clip.start();
//	}
//	
//	
//	public static void startBrick(){
//		AudioInputStream audioInputStream = null;
//		try {
//			audioInputStream = AudioSystem.getAudioInputStream(Sound.class.getResource("/res/sounds/bounce1.wav"));
//			clip = AudioSystem.getClip();
//			clip.open(audioInputStream);
//		} catch (UnsupportedAudioFileException | LineUnavailableException | IOException e) {
//			e.printStackTrace();
//		}
//		
//		
//		FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
//		gainControl.setValue(-10.0f); // Reduce volume by 10 decibels.
//		clip.start();
//	}
//	
//	public void stop() {
//		clip.stop();
//	}
}
