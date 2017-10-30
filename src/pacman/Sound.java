/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pacman;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 *
 * @author nicolasbuitrago
 */
public class Sound implements Runnable {

    private Clip start, chomp, death;
    private Personaje personaje;

    public Sound(Personaje personaje) {
        // specify the sound to play
        // (assuming the sound can be played by the audio system)
        // from a wave File
        this.start = getClip("src/Audio/start.wav");
        this.chomp = getClip("src/Audio/chomp.wav");
        this.death = getClip("src/Audio/death.wav");

        this.personaje = personaje;

        new Thread(this).start();
        // play, stop, loop the sound clip
    }

    @Override
    public void run() {
        play(start);
        try {
            Thread.sleep(4500);
        } catch (InterruptedException ex) {
            System.out.println("OOPS");
        }
        while (true) {
            if (!start.isRunning() && !chomp.isActive()) {
                loop(chomp);

            }
        }
    }

    private Clip getClip(String fileName) {
        Clip clip;
        try {
            File file = new File(fileName);//"src/Audio/start.wav"
            if (file.exists()) {
                AudioInputStream sound = AudioSystem.getAudioInputStream(file);
                // load the sound into memory (a Clip)
                clip = AudioSystem.getClip();
                clip.open(sound);
            } else {
                throw new RuntimeException("Sound: file not found: ");
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
            throw new RuntimeException("Sound: Malformed URL: " + e);
        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
            throw new RuntimeException("Sound: Unsupported Audio File: " + e);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Sound: Input/Output Error: " + e);
        } catch (LineUnavailableException e) {
            e.printStackTrace();
            throw new RuntimeException("Sound: Line Unavailable Exception Error: " + e);
        }
        return clip;
    }

    public void play(Clip clip) {
        clip.setFramePosition(0);  // Must always rewind!
        clip.start();
    }

    public void loop(Clip clip) {
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }

    public void stop(Clip clip) {
        clip.stop();
    }
}
