/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pacman;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import javax.swing.ImageIcon;

/**
 *
 * @author nicolasbuitrago
 */
public class Pacman {

    public static final int RIGTH=0;
    public static final int UP=1;
    public static final int DOWN=2;
    public static final int LEFT=3;
    public static final int NONE=-1;
    
    public static final int RADIO = 28;
    
    Animation[] animations;
    int x;
    int y;
    int vx;
    int vy;
    String path;
    int currentAnimation;
    int currentDirection;
    
    public Pacman (int x, int y, int vx, int vy, String path){
        this.path = path;
        this.x = x;
        this.y = y;
        this.vx = vx;
        this.vy = vy;
        this.currentDirection = -1;
        this.animations = new Animation[4];
    }
    
    public void loadPics(String[] names)throws Exception{
        for (int j=0;j<4;j++) {
            String name=names[j];
            animations[j]=new Animation();
            for (int i = 1; i <= 2; i++) {
                System.out.println("/Sprites"+path+"//"+name+i+".png");
                animations[j].addScene(
                new ImageIcon(getClass().getResource("/Sprites"+path+"//"+name+i+".png")).getImage()    , 100);
            }
//            animations[j].addScene( new ImageIcon(getClass().getResource(path+"//muerte1.png")).getImage()    , 100);
        }
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
//                        g.setColor(Color.BLACK);
//                        g.fillRect(0,0, c.getWidth(), c.getHeight());                        
                        for (int i = 0; i < tablero.getM(); i++) {
                            for (int j = 0; j < tablero.getN(); j++) {
                                if(tablero.getTablero()[i][j].isIs()){
                                    g.setColor(Color.BLACK);
                                    g.fillRect(100*j,100*i, 100, 100); 
                                }else{
                                    g.setColor(Color.BLUE);
                                    g.fillRect(100*j,100*i, 100, 100);
                                }
                            }
                        }
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
    
    private double getDistancia(int x, int y){
        return Math.sqrt(Math.pow(Math.abs(x - this.x),2) + Math.pow(Math.abs(y - this.y),2));
    }
    
    public void moveRigth(Tablero tablero, long time){
        if (tablero.isCamino(x + vx, y)) {
            x += vx;
            currentAnimation = Pacman.RIGTH;
            animations[Pacman.RIGTH].update(time);
        }
    }
    
    public void moveLeft(Tablero tablero, long time){
        if (tablero.isCamino(x - vx, y)) {
            x -= vx;
            currentAnimation = Pacman.LEFT;
            animations[Pacman.LEFT].update(time);
        }
    }
    
    public void moveUp(Tablero tablero, long time) {
        if (tablero.isCamino(x, y - vy)) {
            y -= vy;
            currentAnimation = Pacman.UP;
            animations[Pacman.UP].update(time);
        }
    }
     
    public void moveDown(Tablero tablero, long time) {
        if (tablero.isCamino(x, y + vy)) {
            y += vy;
            currentAnimation = Pacman.DOWN;
            animations[Pacman.DOWN].update(time);
        }
    }
     
    public void draw(Graphics g) {
        g.drawImage(animations[currentAnimation].getImage(), x, y, null);
    }

}
