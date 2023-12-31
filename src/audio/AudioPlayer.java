package audio;

import java.io.IOException;
import java.net.URL;
import java.util.Random;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.BooleanControl;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class AudioPlayer {

    public static int BGM = 0;

    private Clip[] songs, effects;
    private int currentSongId;
    private boolean songMute, effectMute;
    private Random rand = new Random();

    public AudioPlayer() {
        loadSong();
        loadEffects();
        this.currentSongId = BGM;
        playSong(BGM);
    }

    private void loadSong() {
    	songs = new Clip[1];
        songs[BGM] = getClip("bgm");
    }

    private void loadEffects() {
        String[] effectNames = {"teleport"};
        effects = new Clip[effectNames.length];
        for (int i = 0; i < effects.length; i++)
            effects[i] = getClip(effectNames[i]);
    }

    private Clip getClip(String name) {
        URL url = AudioPlayer.class.getResource("/"+name + ".wav");
        if (url == null) {
            System.out.println("Error loading audio file: " + name);
            return null;
        }
        AudioInputStream audio;
        try {
            audio = AudioSystem.getAudioInputStream(url);
            Clip c = AudioSystem.getClip();
            c.open(audio);
            return c;
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            System.out.println("Error opening audio file: " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    public void stopSong() {
        if (songs[currentSongId].isActive())
            songs[currentSongId].stop();
    }

    public void playAttackSound() {
        int start = 4;
        start += rand.nextInt(3);
        playEffect(start);
    }

    public void playEffect(int effect) {
        effects[effect].setMicrosecondPosition(0);
        effects[effect].start();
    }

    public void playSong(int song) {
        stopSong();
        currentSongId = song;
        songs[currentSongId].setMicrosecondPosition(0);
        songs[currentSongId].loop(Clip.LOOP_CONTINUOUSLY);
    }

    public void toggleBGMMute() {
        this.songMute = !songMute;
        BooleanControl booleanControl = (BooleanControl) songs[0].getControl(BooleanControl.Type.MUTE);
        booleanControl.setValue(songMute);
    }

    public void toggleEffectMute() {
        this.effectMute = !effectMute;
        for (Clip c : effects) {
            BooleanControl booleanControl = (BooleanControl) c.getControl(BooleanControl.Type.MUTE);
            booleanControl.setValue(effectMute);
        }
    }
}