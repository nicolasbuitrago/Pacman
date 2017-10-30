/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pacman;

import java.awt.Canvas;
import java.awt.Graphics;

/**
 *
 * @author nicolasbuitrago
 */
public class Fantasma extends Personaje{
    
    public Fantasma (int x, int y, int vx, int vy, String path){
        super(x, y, vx, vy, path);
    }
    
    public Runnable getMovieLoop(Canvas c, Tablero tablero){
        Fantasma J1 = this;
        
        return new Runnable() {

            @Override
            public void run() {
                c.createBufferStrategy(2);
                Graphics g = c.getBufferStrategy().getDrawGraphics();
                long startTime = System.currentTimeMillis();
                long currentTime = 0;
                while(true){
                    try{
                        
                        tablero.paitTablero(g);
                        
                        currentTime = System.currentTimeMillis() - startTime;
                        switch(J1.currentDirection){
                            case Personaje.RIGTH:{ J1.moveRigth(tablero,currentTime); break;}
                            case Personaje.DOWN:{  J1.moveDown (tablero,currentTime); break;}
                            case Personaje.LEFT:{  J1.moveLeft (tablero,currentTime); break;}
                            case Personaje.UP:{    J1.moveUp   (tablero,currentTime); break;}
                        }
                        J1.draw(g);
                        Thread.sleep(30);
                        c.getBufferStrategy().show();
                    }catch(Exception e){
                        e.printStackTrace();
                    }
                }
            }
        };
    }
 
    private class Camino{
        
    }
}