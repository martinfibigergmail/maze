package cz.pf;

import java.io.File;
import java.io.InputStream;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Sound {
	public String file;
	public void play() {
		//File zvuk = new File(file);
		InputStream soundInputStream = Sound.class.getResourceAsStream(file);
	
		try {
			Clip clip = AudioSystem.getClip();
			clip.open (AudioSystem.getAudioInputStream(soundInputStream));
			clip.start();
		
		
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}


