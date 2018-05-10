package Music;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;

public class BadonkaMusic {
    private String path;
    private Music music;
    private FadeThread fader;

    public BadonkaMusic(String path) {
        this.path = path;
    }

    public void play() {
        if(music == null) {
            music = Gdx.audio.newMusic(Gdx.files.internal(path));
        }
        music.setVolume(1f);
        music.setLooping(true);
        music.play();

        while(music.isPlaying() == false) {
            music.play();
        }
    }

    public void fadeOut() {
        if(fader == null) {
            fader = new FadeThread();
            fader.start();
        }
    }

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
