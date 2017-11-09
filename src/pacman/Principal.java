/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pacman;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JFrame;

/**
 *
 * @author nicolasbuitrago
 */
public class Principal extends JFrame {

    public Thread movLoop, movFant;
    public Canvas c;
    public Pacman J1;
    public Fantasma F;
    public Tablero tablero;
    public int[][] mundo = {
        
            {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
            {1,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,1},
            {1,0,1,1,1,1,1,0,1,1,1,0,1,0,1,1,1,0,1,1,1,0,1,0,1},
            {1,0,0,0,0,0,0,0,1,0,0,0,1,0,0,0,0,0,1,0,0,0,1,0,1},
            {1,1,1,1,1,0,1,1,1,0,1,1,1,0,1,1,1,0,1,0,1,0,1,0,1},
            {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,1,0,1,0,1},
            {1,0,1,1,1,0,1,1,0,1,1,0,0,1,1,0,1,1,1,0,1,0,1,0,1},
            {1,0,0,0,1,0,0,0,0,1,0,0,0,0,1,0,0,0,0,0,0,0,0,0,1},
            {1,0,1,0,1,1,1,1,0,1,1,1,1,1,1,0,1,1,1,1,0,1,1,1,1},
            {1,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
            {1,0,1,0,1,0,1,0,1,1,1,0,1,0,1,1,1,1,1,1,1,0,1,0,1},
            {1,0,0,0,1,0,1,0,0,0,0,0,1,0,0,0,1,0,0,0,0,0,1,0,1},
            {1,0,1,1,1,0,1,0,1,0,1,1,1,0,1,0,1,0,1,1,1,1,1,0,1},
            {1,0,0,0,0,0,0,0,1,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,1},
            {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1}
            
//            {1,1,1,1,1,1,1,1,1,1,1,1,0,1,1,1,1,1,1,1,1,1,1,1,1},
//            {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
//            {1,0,1,1,1,1,1,0,1,1,1,0,1,0,1,1,1,0,1,1,1,0,1,0,1},
//            {1,0,0,0,0,0,0,0,1,0,0,0,1,0,0,0,0,0,1,0,0,0,1,0,1},
//            {1,1,1,1,1,0,1,1,1,0,1,1,1,0,1,1,1,0,1,0,1,0,1,0,1},
//            {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,1,0,1,0,1},
//            {1,0,1,1,1,0,1,1,0,1,1,0,0,1,1,0,1,1,1,0,1,0,1,0,1},
//            {0,0,0,0,1,0,0,0,0,1,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0},
//            {1,0,1,0,1,1,1,1,0,1,1,1,1,1,1,0,1,1,1,1,0,1,1,1,1},
//            {1,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
//            {1,0,1,0,1,0,1,0,1,1,1,0,1,0,1,1,1,1,1,1,1,0,1,0,1},
//            {1,0,0,0,1,0,1,0,0,0,0,0,1,0,0,0,1,0,0,0,0,0,1,0,1},
//            {1,0,1,1,1,0,1,0,1,0,1,1,1,0,1,0,1,0,1,1,1,1,1,0,1},
//            {1,0,0,0,0,0,0,0,1,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,1},
//            {1,1,1,1,1,1,1,1,1,1,1,1,0,1,1,1,1,1,1,1,1,1,1,1,1}

    };
    
    public Principal(int w, int h)throws Exception{
        c = new Canvas();
        J1 = new Pacman(523, 320, 8, 8, "/Pacman");//Los ultimos dos son velocidad
        F = new Fantasma(406, 590, 6, 6, "/Fantasma");
        String[] names = {"adelante","arriba","abajo","atras"};
        J1.loadPics(names);
        F.loadPics(names);
        tablero = new Tablero(c,J1,F,mundo, 15, 25);
        
        this.setLayout(null);
        this.setSize(w, h+60);
        c.setLocation(0, 35);
        c.setSize(w, h);
        this.add(J1.getPuntaje());
        this.add(c);
        this.addKeyListener(new KeyListener() {

            @Override
            public void keyTyped(KeyEvent e) {
              
            }

            @Override
            public void keyPressed(KeyEvent e) {
               switch(e.getKeyCode()){
                   case KeyEvent.VK_UP   :{ J1.currentDirection = Personaje.UP; break;}
                   case KeyEvent.VK_DOWN :{ J1.currentDirection = Personaje.DOWN; break;}
                   case KeyEvent.VK_LEFT :{ J1.currentDirection = Personaje.LEFT; break;}
                   case KeyEvent.VK_RIGHT:{ J1.currentDirection = Personaje.RIGTH; break;}
               }
               switch(e.getKeyChar()){
                   case 'w':{F.currentDirection = Personaje.UP; break;}
                   case 'a':{F.currentDirection = Personaje.LEFT; break;}
                   case 's':{F.currentDirection = Personaje.DOWN;break;}
                   case 'd':{F.currentDirection = Personaje.RIGTH; break;}
//                   case 'm':{J1.currentStatus = Personaje.MUERTO; break;}
//                   case 'z':{J1.currentStatus = Personaje.COMIENDO; break;}
               }
            }

            @Override
            public void keyReleased(KeyEvent e) {
               switch(e.getKeyCode()){
                    case KeyEvent.VK_UP   :{ J1.currentDirection = Personaje.NONE; break;}
                    case KeyEvent.VK_DOWN :{ J1.currentDirection = Personaje.NONE; break;}
                    case KeyEvent.VK_LEFT :{ J1.currentDirection = Personaje.NONE; break;}
                    case KeyEvent.VK_RIGHT:{ J1.currentDirection = Personaje.NONE; break;}
               }
               switch(e.getKeyChar()){
                   case 'w':{F.currentDirection = Personaje.NONE; break;}
                   case 'a':{F.currentDirection = Personaje.NONE; break;}
                   case 's':{F.currentDirection = Personaje.NONE; break;}
                   case 'd':{F.currentDirection = Personaje.NONE; break;}
//                   case 'z':{J1.currentStatus = Personaje.NONE; break;}
               }
            }
            
        });
        
        movLoop = new Thread( () -> {
            c.createBufferStrategy(2);
            Graphics g = c.getBufferStrategy().getDrawGraphics();
            long startTime = System.currentTimeMillis();
            long currentTime = 0;// boolean sw = true;
            while(true){
                try{
                        
                    tablero.paintTablero(g);
                    
                    currentTime = System.currentTimeMillis() - startTime;
                    switch(J1.currentDirection){
                        case Pacman.RIGTH:{ J1.moveRigth(tablero,currentTime); break;}
                        case Pacman.DOWN:{  J1.moveDown (tablero,currentTime); break;}
                        case Pacman.LEFT:{  J1.moveLeft (tablero,currentTime); break;}
                        case Pacman.UP:{    J1.moveUp   (tablero,currentTime); break;}
                    } //System.out.println("J1:  x = "+J1.x+",   y = "+J1.y);
                    J1.draw(g);
                    switch(F.currentDirection){
                        case Personaje.RIGTH:{ F.moveRigth(tablero,currentTime); break;}
                        case Personaje.DOWN:{  F.moveDown (tablero,currentTime); break;}
                        case Personaje.LEFT:{  F.moveLeft (tablero,currentTime); break;}
                        case Personaje.UP:{    F.moveUp   (tablero,currentTime); break;}
                    }
                    F.draw(g);
                    
                    Thread.sleep(30);
                    c.getBufferStrategy().show();
                    
                    if (tablero.isEmptyPuntos() || J1.currentStatus == Personaje.MUERTO) {
                        tablero.paintTablero(g);
                        if (J1.currentStatus == Personaje.MUERTO) {
                            J1.muerte(currentTime);
                        }
                        J1.draw(g);
                        F.draw(g);
                        c.getBufferStrategy().show();
                        break;
                    }
                }catch(Exception e){
                    e.printStackTrace();
                }
            }// System.out.println(tablero.isEmptyPuntos());
        },"Movimientos"); 
//        movLoop.setPriority(Thread.MAX_PRIORITY);
       //Sound sound = new Sound(J1,F);
        movFant = new Thread(((Fantasma)F).getMovieLoop(tablero),"MovFant");
    }
    
    public static void main(String[] args) {
        try{
            Principal p = new Principal(Tablero.WIDTH,Tablero.HEIGHT);
            p.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            p.setResizable(false);
            p.setLocationRelativeTo(null);
            p.setVisible(true);
            p.movLoop.start();
            p.movFant.start();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
}
