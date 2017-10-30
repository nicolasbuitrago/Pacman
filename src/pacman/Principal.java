/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pacman;

import java.awt.Canvas;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JFrame;

/**
 *
 * @author nicolasbuitrago
 */
public class Principal extends JFrame {

    public Thread movieLoop;
    public Canvas c;
    public Personaje J1;
    public Tablero tablero;
    public int[][] mundo = {{1,1,1,1,1,0,1,1,1,1,1},
                            {1,0,0,0,0,0,0,0,0,0,1},
                            {1,1,0,1,0,0,1,1,1,0,1},
                            {0,0,0,0,0,0,0,1,0,0,0},
                            {1,1,1,1,0,1,1,1,0,0,1},
                            {1,0,0,0,0,0,0,0,0,0,1},
                            {1,1,1,1,1,0,1,1,1,1,1}
//            {1,1,1,1,1,0,1,1,1,1,1,1,1,1,1,1,0,1,1,1,1,1},
//            {1,0,0,0,0,0,0,0,0,0,1,1,0,0,0,0,0,0,0,0,0,1},
//            {1,1,0,1,0,0,1,1,1,0,1,1,1,0,1,0,0,1,1,1,0,1},
//            {0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,1,0,0,0},
//            {1,1,1,1,0,1,1,1,0,0,1,1,1,1,1,0,1,1,1,0,0,1},
//            {1,0,0,0,0,0,0,0,0,0,1,1,0,0,0,0,0,0,0,0,0,1},
//            {1,1,1,1,1,0,1,1,1,1,1,1,1,1,1,1,0,1,1,1,1,1},
//            {1,1,1,1,1,0,1,1,1,1,1,1,1,1,1,1,0,1,1,1,1,1},
//            {1,0,0,0,0,0,0,0,0,0,1,1,0,0,0,0,0,0,0,0,0,1},
//            {1,1,0,1,0,0,1,1,1,0,1,1,1,0,1,0,0,1,1,1,0,1},
//            {0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,1,0,0,0},
//            {1,1,1,1,0,1,1,1,0,0,1,1,1,1,1,0,1,1,1,0,0,1},
//            {1,0,0,0,0,0,0,0,0,0,1,1,0,0,0,0,0,0,0,0,0,1},
//            {1,1,1,1,1,0,1,1,1,1,1,1,1,1,1,1,0,1,1,1,1,1}

    };
    
    public Principal(int w, int h)throws Exception{
        c = new Canvas();
        tablero = new Tablero(mundo, 7, 11);
        this.setSize(w, h);
        c.setSize(w, h);
        this.add(c);
        this.addKeyListener(new KeyListener() {

            @Override
            public void keyTyped(KeyEvent e) {
              
            }

            @Override
            public void keyPressed(KeyEvent e) {
               switch(e.getKeyCode()){
                   case KeyEvent.VK_UP   :{ J1.currentDirection=Pacman.UP; break;}
                   case KeyEvent.VK_DOWN :{ J1.currentDirection=Pacman.DOWN; break;}
                   case KeyEvent.VK_LEFT :{ J1.currentDirection=Pacman.LEFT; break;}
                   case KeyEvent.VK_RIGHT:{ J1.currentDirection=Pacman.RIGTH; break;}
               }
            }

            @Override
            public void keyReleased(KeyEvent e) {
               switch(e.getKeyCode()){
                    case KeyEvent.VK_UP   :{ J1.currentDirection=Pacman.NONE; break;}
                    case KeyEvent.VK_DOWN :{ J1.currentDirection=Pacman.NONE; break;}
                    case KeyEvent.VK_LEFT :{ J1.currentDirection=Pacman.NONE; break;}
                    case KeyEvent.VK_RIGHT:{ J1.currentDirection=Pacman.NONE; break;}
               }
            }
            
        });
        J1 = new Pacman(100, 120, 9, 9, "/Pacman");//Los ultimos dos son velocidad
//        J1 = new Fantasma(100, 120, 9, 9, "/Fantasma");
        String[] names = {"adelante","arriba","abajo","atras"};
        J1.loadPics(names);
        movieLoop = new Thread( J1.getMovieLoop(c, tablero));
        
    }
    
    public static void main(String[] args) {
        try{
            Principal p= new Principal(1100,700);
            p.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            p.setResizable(false);
            p.setLocationRelativeTo(null);
            p.setVisible(true);
            p.movieLoop.start();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
}
