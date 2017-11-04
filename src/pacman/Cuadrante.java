/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pacman;

import java.awt.Rectangle;
import java.util.Objects;

/**
 *
 * @author nicolasbuitrago
 */
public class Cuadrante {
    
    private Rectangle rect;
    private boolean is;
    private int name;

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

    public int getName() {
        return name;
    }

    public void setName(int name) {
        this.name = name;
    }
    
    public boolean intersects(int x, int y){
        return this.rect.intersects(x,y,Personaje.RADIO,Personaje.RADIO);
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 31 * hash + (this.is ? 1 : 0);
        hash = 31 * hash + this.name;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Cuadrante other = (Cuadrante) obj;
        if (this.is != other.is) {
            return false;
        }
        if (this.name != other.name) {
            return false;
        }
        return Objects.equals(this.rect, other.rect);
    }
    
    
}
