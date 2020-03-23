package cz.pf;

import java.io.File;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Sound {
	public String file;
	public void ff() {
		File zvuk = new File(file);
	
		try {
			Clip clip = AudioSystem.getClip();
			clip.open (AudioSystem.getAudioInputStream(zvuk));
			clip.start();
		
		
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}


