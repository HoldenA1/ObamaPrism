package obama.prism.engine.sound;

import java.net.URL;
import javax.sound.sampled.*;

public class Player {
	
	private Clip clip;

	/**
	 * Song file name example: "obama.wav" 
	 * @param song
	 */
	public void play (String song) {
		URL url = Player.class.getResource(song);
		
		if (url == null) {
			System.err.println("Sorry, the sound clip could not be found");
		} else {
			try {
				AudioInputStream audio = AudioSystem.getAudioInputStream(url);
				clip = AudioSystem.getClip();
				clip.open(audio);
				clip.start();
	            clip.loop(Clip.LOOP_CONTINUOUSLY);
			} catch (Exception e) {
				System.err.println("Sorry, there was an error processing the sound clip");
			}
		}
	}

	public void stop () {
		clip.stop();
	}
}
