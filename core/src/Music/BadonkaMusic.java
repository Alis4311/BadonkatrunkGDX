package Music;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;

/**
 * Handles a music resource and provides methods for playing the music and fading out the music.
 *
 * @aurthor Tim Normark
 */
public class BadonkaMusic {
    private String path;
    private Music music;
    private FadeThread fader;

    /**
     * Creates a BadonkaMusic object that will handle a music file that should be found on the given path (String).
     * @param path String that represents the path of the music file.
     */
    public BadonkaMusic(String path) {
        this.path = path;
    }

    /**
     * Plays the music file that this object handles.
     */
    public void play() {
        if(music == null) {
            music = Gdx.audio.newMusic(Gdx.files.internal(path));
        }
        music.setVolume(1f);
        music.setLooping(true);

        music.play();

        //Protection code to make sure that the music will definitely start playing.
        while(music.isPlaying() == false) {
            music.play();
        }
    }

    /**
     * Fades out the playing music, which means that a thread starts that will continuously lower the volume of the
     * playing music with short time intervals and finally stop the music completely. If the music is already fading
     * the method call does nothing.
     */
    public void fadeOut() {
        if(fader == null) {
            fader = new FadeThread();
            fader.start();
        }
    }

    /**
     * Private class that is a Thread and is used by BadonkaMusic for the task of fading out the music.
     */
    private class FadeThread extends Thread {
        public void run() {
            float volume = music.getVolume();
            while(music.getVolume() > 0) {
                volume -= 0.02f;
                music.setVolume(volume);
                try {
                    Thread.sleep(15);
                } catch (InterruptedException e) {
                }
            }
            music.stop();
            fader = null;
        }
    }

}
