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
	private String path;
	private AudioInputStream audioInputStream;
	private float volume;
	private float defVolume;
	
	public Sound(String path, float volume) {
		this.path = path;
		this.volume = volume;
		defVolume = volume;
		initClip();
	}
	
	private void initClip() {
		try {
			audioInputStream = AudioSystem.getAudioInputStream(Sound.class.getResource(path));
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
		clip.loop(-1);
	}
	
	public void stop() {
		clip.stop();
	}
}
