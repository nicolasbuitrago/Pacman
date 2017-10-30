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

    Thread t;
    private Clip start, chomp, death, eat;
    private Personaje pacman, fantasma;
    private int cp, cf;

    public Sound(Personaje pacman, Personaje fantasma) {
        // specify the sound to play
        // (assuming the sound can be played by the audio system)
        // from a wave File
        this.start = getClip("src/Audio/start.wav");
        this.chomp = getClip("src/Audio/chomp.wav");
        this.death = getClip("src/Audio/death.wav");
        this.eat   = getClip("src/Audio/eat.wav");

        this.pacman = pacman;
        this.cp = -1;
        this.fantasma = fantasma;
        this.cf = -1;

        t = new Thread(this);
        t.start();
        // play, stop, loop the sound clip
    }

    @Override
    public void run() {
        
        play(start,4500);
        start.close();
        
        while (true) {
            System.out.println("cs = "+this.pacman.currentStatus);
            if (cp != this.pacman.currentStatus) {
                switch (this.pacman.currentStatus) {
                    case Personaje.NORMAL: {
                        stop();
                        if(!isActive())loop(chomp);
                        this.cp = Personaje.NORMAL;
                        break;
                    }
                    case Personaje.MUERTO: {
                        stop(chomp);
                        play(death,1500);
                        this.cp = Personaje.MUERTO; close();
                        t.suspend();
                        break;
                    }
                    case Personaje.COMIENDO: {
                        stop(chomp);
                        play(eat,1000);
                        this.cp = Personaje.COMIENDO;
                        this.pacman.currentStatus = Personaje.NORMAL;
                        break;
                    }
                }
            }

            if (cp != this.pacman.currentStatus) {
                switch (this.fantasma.currentStatus) {
                    case Personaje.MUERTO: {
                        
                        this.cf = Personaje.MUERTO;
                        break;
                    }
                }
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
    
    public boolean isActive(){
        return death.isActive() || eat.isActive();
    }

    public void play(Clip clip) {
        clip.setFramePosition(0);  // Must always rewind!
        clip.start();
    }

    public void loop(Clip clip) {
        clip.loop(Clip.LOOP_CONTINUOUSLY);
        clip.drain();
    }

    public void stop(Clip clip) {
        clip.stop();
//        clip.drain();
    }
    
    public void play(Clip clip, int time){
        play(clip);
        try {
            Thread.sleep(time);
        } catch (InterruptedException ex) {
            System.out.println("OOPS");
        }
    }
    
    public void close(){
        chomp.close();
        eat.close();
        death.close();
    }
    
    public void stop(){
        if(this.start.isActive()){
            start.stop();
        }
        if(this.chomp.isActive()){
            chomp.isActive();
        }
        if(this.death.isActive()){
            death.stop();
        }
    }
}
