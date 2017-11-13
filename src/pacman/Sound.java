/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pacman;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
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
    private Clip start, chomp, death, eat, win;
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
        this.win   = getClip("src/Audio/win.wav");

        this.pacman = pacman;
        this.cp = Personaje.NONE;
        this.fantasma = fantasma;
        this.cf = Personaje.NONE;

        t = new Thread(this,"Sonidos");
        t.setPriority(6);
        t.start();
        // play, stop, loop the sound clip
    }

    @Override
    public void run() {
        
        play(start,4500);
        start.stop();
        start.close();
        while (true) {
            try {
                System.out.println(this.pacman.currentStatus);
                
                if (cp != this.pacman.currentStatus) {//System.out.println("cs = " + this.pacman.currentStatus);
                    switch (this.pacman.currentStatus) {
                        case Personaje.NORMAL: {
                            stop();
//                            System.out.println("dddddddd");
                            if (!isActive() && !chomp.isActive()) {
                                loop(chomp);
                            }
//                            System.out.println("wwwww");
                            this.cp = Personaje.NORMAL;
//                            System.out.println("ssss");
                            break;
                        }
//                        case Personaje.GANO: {
//                            chomp.stop();System.out.println("*************GANO");
//                            play(win, 1500);
//                            this.cp = Personaje.GANO;
//                            close();
//                            t.suspend();
//                            break;
//                        }
                        case Personaje.MUERTO: {
                            chomp.stop();
                            play(death, 1500);
                            this.cp = Personaje.MUERTO;
                            close();
                            t.suspend();
                            break;
                        }
                        case Personaje.GANO: {
//                            chomp.stop();
                            play(win, 5000);
                            this.cp = Personaje.GANO;
                            stop();
                            close();
                            t.suspend();
                            break;
                        }
                        case Personaje.COMIENDO: {
//                            chomp.flush();//stop(chomp);
                             play(eat,1000);
                            this.cp = Personaje.COMIENDO;
                            this.pacman.currentStatus = Personaje.NORMAL;
                            break;
                        }
                        
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            if (cp != this.pacman.currentStatus) {
                switch (this.fantasma.currentStatus) {
                    case Personaje.MUERTO: {
                        chomp.stop();
                        play(win, 15000);
                        this.cp = Personaje.GANO;
                        this.cf = Personaje.MUERTO;
                        stop();
                        close();
                        t.suspend();

                        break;
                    }
                }
            }
        }
        
    }
    
    public void win(){
        chomp.stop();
        chomp.close();
        play(win,3500);
        this.cp = Personaje.GANO;
        stop();
        close();
        t.suspend();
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
        win.close();
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
        if(this.win.isActive()){
            win.stop();
        }
    }
}
