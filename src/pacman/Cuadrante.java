/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pacman;

import java.awt.Rectangle;

/**
 *
 * @author Nicolás
 */
public class Cuadrante {
    
    private Rectangle rect;
    private boolean is;

    public Cuadrante(int x, int y, int size, boolean is){
        this.rect = new Rectangle(x, y, size, size);  //if(rect.intersects(100, 120, 56,56)) System.out.println(y/100+","+x/100);
        this.is = is;
    }

    public int getX() {
        return rect.x;
    }
    
    public int getY() {
        return rect.y;
    }

    public boolean isIs() {
        return is;
    }
    
    public boolean intersects(int x, int y){
        return this.rect.intersects(x,y,56,56);
    }
}
