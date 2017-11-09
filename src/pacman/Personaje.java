/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pacman;

import java.awt.Graphics;
import javax.swing.ImageIcon;

/**
 *
 * @author nicolasbuitrago
 */
public abstract class Personaje {
    
    public static final int RIGTH = 0;
    public static final int UP = 1;
    public static final int DOWN = 2;
    public static final int LEFT = 3;
    public static final int NONE = -1;
    
    public static final int RADIO = 35;
    
    protected Animation[] animations;
    protected int x;
    protected int y;
    protected Cuadrante cuadrante;
    protected int vx;
    protected int vy;
    protected String path;
    protected int currentAnimation;
    protected int currentDirection;
    protected int currentStatus;
    
    public static final int NORMAL = 0;
    public static final int MUERTO = 4;
    public static final int COMIENDO = 2;
    
    public Personaje (int x, int y, int vx, int vy, String path){
        this.path = path;
        this.x = x;
        this.y = y;
        this.vx = vx;
        this.vy = vy;
        this.currentDirection = NONE;
        this.currentStatus = NORMAL;
        this.animations = new Animation[5];
    }
    
    /**
     * Se encarga de cargar los sprites del personaje dada su ubicacion
     * @param names
     * @throws Exception 
     */
    public void loadPics(String[] names)throws Exception{
        for (int j=0;j<4;j++) {
            String name = names[j];
            animations[j]=new Animation();
            for (int i = 1; i <= 2; i++) {
                //System.out.println("/Sprites"+path+"//"+name+i+".png");
                animations[j].addScene(
                new ImageIcon(getClass().getResource("/Sprites"+path+"//"+name+i+".png")).getImage()    , 100);
            }
        }
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setCuadrante(Cuadrante cuadrante) {
        this.cuadrante = cuadrante;
    }
    
    public Cuadrante getCuadrante() {
        return this.cuadrante;
    }
    
//    public abstract Runnable getMovieLoop(Canvas c, Tablero tablero);
    
//    private double getDistancia(int x, int y){
//        return Math.sqrt(Math.pow(Math.abs(x - this.x),2) + Math.pow(Math.abs(y - this.y),2));
//    }   
    
    public abstract void moveRigth(Tablero tablero, long time);
    
    public abstract void moveLeft(Tablero tablero, long time);
    
    public abstract void moveUp(Tablero tablero, long time);
     
    public abstract void moveDown(Tablero tablero, long time);
     
    public void draw(Graphics g) {
        g.drawImage(animations[currentAnimation].getImage(), x, y, null);
    }
}
