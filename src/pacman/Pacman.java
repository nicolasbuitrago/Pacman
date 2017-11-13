/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pacman;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
//import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 *
 * @author nicolasbuitrago
 */
public class Pacman extends Personaje{
    
    private Puntaje puntaje;
//    private int vidas;
//    ImageIcon img;
    
    public Pacman (int x, int y, int vx, int vy, String path){
        super(x, y, vx, vy, path);
//        this.img = new ImageIcon(getClass().getResource("/Sprites"+path+"//adelante1.png"));
        this.puntaje = new Puntaje();
//        this.vidas = 2;
    }

    public JLabel getPuntaje() {
        return puntaje.lblPuntaje;
    }

//    public int getVidas() {
//        return vidas;
//    }
//    
//    public void quitarVidas(){
//        vidas--;
//    }

//    public ImageIcon getImg() {
//        return img;
//    }
    
    
     public void addPunto(){
            this.puntaje.addPunto();
        }
        
        public void addPuntos(){
            this.puntaje.addPuntos();
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
                        
                        tablero.paintTablero(g);
                        
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
            if(tablero.validComePunto(this,x,y)) this.puntaje.addPuntos();
            if(x>Tablero.WIDTH) x = -50;
            currentAnimation = Personaje.RIGTH;
            animations[Personaje.RIGTH].update(time);
            tablero.setCuadrante(this);
        }
    }
    
    @Override
    public void moveLeft(Tablero tablero, long time){
        if (tablero.isCamino(x - vx, y)) {
            x -= vx;
            if(tablero.validComePunto(this,x,y)) this.puntaje.addPuntos();
            if(x<-25) x = Tablero.WIDTH;
            currentAnimation = Personaje.LEFT;
            animations[Personaje.LEFT].update(time);
            tablero.setCuadrante(this);
        }
    }
    
    @Override
    public void moveUp(Tablero tablero, long time) {
        if (tablero.isCamino(x, y - vy)) {
            y -= vy;
            if(tablero.validComePunto(this,x,y)) this.puntaje.addPuntos();
            if(y<-25) y = Tablero.HEIGHT;
            currentAnimation = Personaje.UP;
            animations[Personaje.UP].update(time);
            tablero.setCuadrante(this);
        }
    }
     
    @Override
    public void moveDown(Tablero tablero, long time) {
        if (tablero.isCamino(x, y + vy)) {
            y += vy;
            if(tablero.validComePunto(this,x,y)) this.puntaje.addPuntos();
            if(y>Tablero.HEIGHT) y = -50;
            currentAnimation = Personaje.DOWN;
            animations[Personaje.DOWN].update(time);
            tablero.setCuadrante(this);
        }
    }
    
    private class Puntaje{
        private JLabel lblPuntaje;
        private int puntaje;
        private final String PUNTAJE = "PUNTAJE: ";

        public Puntaje() {
            this.lblPuntaje = new JLabel(PUNTAJE+"0");
            this.lblPuntaje.setFont(new java.awt.Font("Tahoma", 1, 25));
            this.lblPuntaje.setForeground(Color.WHITE);
            this.lblPuntaje.setBounds(15, 12, 200, 30);
            this.puntaje = 0;
        }
        
        public void addPunto(){
            this.puntaje++;
            this.lblPuntaje.setText(PUNTAJE+this.puntaje);
        }
        
        public void addPuntos(){
            this.puntaje += 10;
            this.lblPuntaje.setText(PUNTAJE+this.puntaje);
        }
        
    }
    
}
