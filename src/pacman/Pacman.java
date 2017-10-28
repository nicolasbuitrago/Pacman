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
public class Pacman {

    public static final int RIGTH=0;
    public static final int UP=1;
    public static final int DOWN=2;
    public static final int LEFT=3;
    public static final int NONE=-1;
    
    Animation[] animations;
    int x;
    int y;
    int vx;
    int vy;
    String path;
    int currentAnimation;
    int currentDirection;
    
    public Pacman(int x, int y, int vx, int vy, String path){
        this.path=path;
        this.x=x;
        this.y=y;
        this.vx=vx;
        this.vy=vy;
        this.currentDirection=-1;
        animations=new Animation[4];
    }
    
    public void loadPics(String[] names)throws Exception{
        for (int j=0;j<4;j++) {
            String name=names[j];
            animations[j]=new Animation();
            for (int i = 1; i <= 2; i++) {
                System.out.println("/" +path+"//"+name+i+".png");
                animations[j].addScene(
                new ImageIcon(getClass().getResource("/" +path+"//"+name+i+".png")).getImage()    , 100);
            }
        }
       
    }
    public void moveRigth(long time){
        x+=vx;
        currentAnimation=Pacman.RIGTH;
        animations[Pacman.RIGTH].update(time);
    }
    
    public void moveLeft(long time){
        x-=vx;
        currentAnimation=Pacman.LEFT;
        animations[Pacman.LEFT].update(time);
    }
    
     public void moveUp(long time){
        y-=vy;
        currentAnimation=Pacman.UP;
        animations[Pacman.UP].update(time);
    }
     
     public void moveDown(long time){
        y+=vy;
        currentAnimation=Pacman.DOWN;
        animations[Pacman.DOWN].update(time);
    }
     
     public void draw(Graphics g){
         g.drawImage(animations[currentAnimation].getImage(),x,y,null);
     }
    
}
