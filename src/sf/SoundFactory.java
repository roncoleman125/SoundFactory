package sf;

import java.util.HashMap;

/**
 * This is a helper class for playing sounds.<br>
 * Among its features are it primes the Java sound API, caches sounds, and
 * can play sounds in the background with or without a loop.
 * @author Ron.Coleman
 *
 */
public class SoundFactory {
	protected static HashMap<String, Sound> sounds = new HashMap<>();
	protected static Boolean primed = false;
	
	/**
	 * Constructor
	 */
	private SoundFactory() {
		
	}
	
	/**
	 * Makes a sound and caches it by the path.
	 * @param path File path
	 * @return Sound
	 */
	public static Sound getInstance(String path) {
		Sound sound = sounds.get(path);
		
		if(sound != null)
			return sound;
		
		sound = new Sound(path);
		
		sounds.put(path, sound);
		
		if(!primed) {
			sound.setVolume(-80.0f);
			
			sound.play();
			
			sound.setVolume(6.0f);
			
			primed = true;
		}
		
		return sound;
	}
	
	/**
	 * Plays a sound n times in a loop in the background.
	 * @param sound Sound to play
	 * @param n Loop times
	 */
	public static void play(Sound sound, int n) {
		new Thread(new Runnable() {
			public void run() {
				for(int loop=0; loop < n; loop++) {
					sound.play();
				}
			}
		}).start();
	}
	
	/**
	 * Plays a sound one time in the background.
	 * @param sound Sound
	 */
	public static void play(Sound sound) {
		play(sound, 1);
	}
	
	/**
	 * Plays a sound n times in a loop in the background. 
	 * @param path Sound file path
	 * @param n Loop times
	 */
	public static void play(String path, int n) {
		Sound sound = getInstance(path);
		
		play(sound, n);
	}
	
	/**
	 * Plays a sound one time in the background.
	 * @param path Sound file path
	 */
	public static void play(String path) {
		play(path, 1);
	}
	
	public static void main(String[] args) {
		Sound sound = SoundFactory.getInstance("src/res/goodshot-2.wav");
		
		sound.play();
	}

}
