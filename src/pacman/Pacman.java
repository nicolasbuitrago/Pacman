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
public class Pacman extends Personaje{
    
    public Pacman (int x, int y, int vx, int vy, String path){
        super(x, y, vx, vy, path);
    }
    
    public Runnable getMovieLoop(Canvas c, Tablero tablero){
        Pacman J1 = this;
        
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
                            case Pacman.RIGTH:{ J1.moveRigth(tablero,currentTime); break;}
                            case Pacman.DOWN:{  J1.moveDown (tablero,currentTime); break;}
                            case Pacman.LEFT:{  J1.moveLeft (tablero,currentTime); break;}
                            case Pacman.UP:{    J1.moveUp   (tablero,currentTime); break;}
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
    
    @Override
    public void moveRigth(Tablero tablero, long time){
        if (tablero.isCamino(x + vx, y)) {
            x += vx;
            if(x>Tablero.WIDTH) x = -56;
            currentAnimation = Personaje.RIGTH;
            animations[Personaje.RIGTH].update(time);
        }
    }
    
    @Override
    public void moveLeft(Tablero tablero, long time){
        if (tablero.isCamino(x - vx, y)) {
            x -= vx;
            if(x<-25) x = Tablero.WIDTH;
            currentAnimation = Personaje.LEFT;
            animations[Personaje.LEFT].update(time);
        }
    }
    
    @Override
    public void moveUp(Tablero tablero, long time) {
        if (tablero.isCamino(x, y - vy)) {
            y -= vy;
            if(y<-25) y = Tablero.HEIGHT;
            currentAnimation = Personaje.UP;
            animations[Personaje.UP].update(time);
        }
    }
     
    @Override
    public void moveDown(Tablero tablero, long time) {
        if (tablero.isCamino(x, y + vy)) {
            y += vy;
            if(y>Tablero.HEIGHT) y = -56;
            currentAnimation = Personaje.DOWN;
            animations[Personaje.DOWN].update(time);
        }
    }
    
}
