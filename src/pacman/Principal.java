/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pacman;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author nicolasbuitrago
 */
public class Principal extends JFrame {

    public Thread movLoop, movFant;
    public Canvas canvas;
    public Pacman pacman;
    public Fantasma fantasma;
    public Tablero tablero;
    public JLabel estado, reiniciar;
    JLabel[] vidas;
    public Inicio inicio;
    public int[][] mundo = {
        
//            {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
//            {1,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,1},
//            {1,0,1,1,1,1,1,0,1,1,1,0,1,0,1,1,1,0,1,1,1,0,1,0,1},
//            {1,0,0,0,0,0,0,0,1,0,0,0,1,0,0,0,0,0,1,0,0,0,1,0,1},
//            {1,1,1,1,1,0,1,1,1,0,1,1,1,0,1,1,1,0,1,0,1,0,1,0,1},
//            {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,1,0,1,0,1},
//            {1,0,1,1,1,0,1,1,0,1,1,0,0,1,1,0,1,1,1,0,1,0,1,0,1},
//            {1,0,0,0,1,0,0,0,0,1,0,0,0,0,1,0,0,0,0,0,0,0,0,0,1},
//            {1,0,1,0,1,1,1,1,0,1,1,1,1,1,1,0,1,1,1,1,0,1,1,1,1},
//            {1,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
//            {1,0,1,0,1,0,1,0,1,1,1,0,1,0,1,1,1,1,1,1,1,0,1,0,1},
//            {1,0,0,0,1,0,1,0,0,0,0,0,1,0,0,0,1,0,0,0,0,0,1,0,1},
//            {1,0,1,1,1,0,1,0,1,0,1,1,1,0,1,0,1,0,1,1,1,1,1,0,1},
//            {1,0,0,0,0,0,0,0,1,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,1},
//            {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1}
            
            {1,1,1,1,1,1,1,1,1,1,1,1,0,1,1,1,1,1,1,1,1,1,1,1,1},
            {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
            {1,0,1,1,1,1,1,0,1,1,1,0,1,0,1,1,1,0,1,1,1,0,1,0,1},
            {1,0,0,0,0,0,0,0,1,0,0,0,1,0,0,0,0,0,1,0,0,0,1,0,1},
            {1,1,1,1,1,0,1,1,1,0,1,1,1,0,1,1,1,0,1,0,1,0,1,0,1},
            {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,1,0,1,0,1},
            {1,0,1,1,1,0,1,1,0,1,1,0,0,1,1,0,1,1,1,0,1,0,1,0,1},
            {0,0,0,0,1,0,0,0,0,1,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0},
            {1,0,1,0,1,1,1,1,0,1,1,1,1,1,1,0,1,1,1,1,0,1,1,1,1},
            {1,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
            {1,0,1,0,1,0,1,0,1,1,1,0,1,0,1,1,1,1,1,1,1,0,1,0,1},
            {1,0,0,0,1,0,1,0,0,0,0,0,1,0,0,0,1,0,0,0,0,0,1,0,1},
            {1,0,1,1,1,0,1,0,1,0,1,1,1,0,1,0,1,0,1,1,1,1,1,0,1},
            {1,0,0,0,0,0,0,0,1,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,1},
            {1,1,1,1,1,1,1,1,1,1,1,1,0,1,1,1,1,1,1,1,1,1,1,1,1}

    };
    
    public Principal(Inicio inicio, int w, int h)throws Exception{
        this.setTitle("Pacman"); 
        canvas = new Canvas(); this.inicio = inicio;
        pacman = new Pacman(523, 320, 8, 8, "/Pacman");//Los ultimos dos son velocidad
        fantasma = new Fantasma(406, 590, 5, 5, "/Fantasma");
        String[] names = {"adelante","arriba","abajo","atras"};
        pacman.loadPics(names);
        fantasma.loadPics(names);
        tablero = new Tablero(canvas,pacman,fantasma,mundo, 15, 25);
        
        this.setSize(w, h+85);
        JPanel panel = new JPanel();
        panel.setBackground(Color.BLACK);
        panel.setSize(w,h+85);
        panel.setLayout(null);
        
        
        vidas = new JLabel[3];
        getVidas(panel,200);
        
        estado = new JLabel();
        estado.setFont(new java.awt.Font("Tahoma", 1, 25));
        estado.setHorizontalAlignment(JLabel.CENTER);
        estado.setForeground(Color.WHITE);
        estado.setBounds(320, 12, w-200-150, 30);
        
        reiniciar = new JLabel("REINICIAR");
        reiniciar.setFont(new java.awt.Font("Tahoma", 1, 25));
        reiniciar.setForeground(Color.WHITE);
        reiniciar.setBounds(w-150, 12, 150, 30);
//        reiniciar.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        reiniciar.addMouseListener(getMouseListener());
        
        canvas.setLocation(0, 55);
        canvas.setSize(w, h);
        panel.add(pacman.getPuntaje());
        panel.add(estado);
        panel.add(reiniciar);
        panel.add(canvas);
        this.add(panel);
        
        this.addKeyListener(getKeyListener());
        
        movLoop = new Thread( getMovieLoop(),"Movimientos"); 
//        movLoop.setPriority(Thread.MAX_PRIORITY);
//        Sound sound = new Sound(pacman,fantasma);
        movFant = new Thread(((Fantasma)fantasma).getMovieLoop(tablero),"MovFant");
    }
    
    public KeyListener getKeyListener(){
        return new KeyListener() {

            @Override
            public void keyTyped(KeyEvent e) {
              
            }

            @Override
            public void keyPressed(KeyEvent e) {
               switch(e.getKeyCode()){
                   case KeyEvent.VK_UP   :{ pacman.currentDirection = Personaje.UP; break;}
                   case KeyEvent.VK_DOWN :{ pacman.currentDirection = Personaje.DOWN; break;}
                   case KeyEvent.VK_LEFT :{ pacman.currentDirection = Personaje.LEFT; break;}
                   case KeyEvent.VK_RIGHT:{ pacman.currentDirection = Personaje.RIGTH; break;}
               }
//               switch(e.getKeyChar()){
//                   case 'w':{fantasma.currentDirection = Personaje.UP; break;}
//                   case 'a':{fantasma.currentDirection = Personaje.LEFT; break;}
//                   case 's':{fantasma.currentDirection = Personaje.DOWN;break;}
//                   case 'd':{fantasma.currentDirection = Personaje.RIGTH; break;}
////                   case 'm':{J1.currentStatus = Personaje.MUERTO; break;}
////                   case 'z':{J1.currentStatus = Personaje.COMIENDO; break;}//                   case 'm':{J1.currentStatus = Personaje.MUERTO; break;}
////                   case 'z':{J1.currentStatus = Personaje.COMIENDO; break;}
//               }
            }

            @Override
            public void keyReleased(KeyEvent e) {
               switch(e.getKeyCode()){
//                    case KeyEvent.VK_UP   :{ pacman.currentDirection = Personaje.NONE; break;}
//                    case KeyEvent.VK_DOWN :{ pacman.currentDirection = Personaje.NONE; break;}
//                    case KeyEvent.VK_LEFT :{ pacman.currentDirection = Personaje.NONE; break;}
//                    case KeyEvent.VK_RIGHT:{ pacman.currentDirection = Personaje.NONE; break;}
               }
//               switch(e.getKeyChar()){
//                   case 'w':{fantasma.currentDirection = Personaje.NONE; break;}
//                   case 'a':{fantasma.currentDirection = Personaje.NONE; break;}
//                   case 's':{fantasma.currentDirection = Personaje.NONE; break;}
//                   case 'd':{fantasma.currentDirection = Personaje.NONE; break;}
////                   case 'z':{J1.currentStatus = Personaje.NONE; break;}//                   case 'z':{J1.currentStatus = Personaje.NONE; break;}
//               }
            }
            
        };
    }
    
    private MouseListener getMouseListener() {
        JLabel reini =  this.reiniciar;
        return new MouseListener(){
            @Override
            public void mouseClicked(MouseEvent e) {
                reiniciar();
            }

            @Override
            public void mousePressed(MouseEvent e) {
                
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                reini.setForeground(Color.BLUE);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                reini.setForeground(Color.WHITE);
            }
            
        };
    }
    
    private void reiniciar(){
        if(pacman.getVidas()>=0){
            this.estado.setText("");
            this.pacman.reiniciar(523, 320);
            this.fantasma.reiniciar(406, 590);
            movLoop = new Thread( getMovieLoop(),"Movimientos");
            movFant = new Thread(((Fantasma)fantasma).getMovieLoop(tablero),"MovFant");
            
            movLoop.start();
            movFant.start();
        }else{
            if(this.estado.getText().equals("GAME OVER")){
                inicio.reiniciar();
                this.dispose();
            }else
                this.estado.setText("GAME OVER");System.out.println("***********"+pacman.getVidas());
        }
            
//        inicio.reiniciar();
//        this.dispose();
    }
    
    public Runnable getMovieLoop(){
        JLabel estado = this.estado;
        
        return new Runnable() {

            @Override
            public void run() {
                canvas.createBufferStrategy(2);
            Graphics g = canvas.getBufferStrategy().getDrawGraphics();
            long startTime = System.currentTimeMillis();
            long currentTime = 0; pacman.currentStatus = Personaje.NORMAL;
            while(true){
                try{
                        
                    tablero.paintTablero(g);
                    
                    currentTime = System.currentTimeMillis() - startTime;
                    switch(pacman.currentDirection){
                        case Personaje.RIGTH:{ pacman.moveRigth(tablero,currentTime); break;}
                        case Personaje.DOWN:{  pacman.moveDown (tablero,currentTime); break;}
                        case Personaje.LEFT:{  pacman.moveLeft (tablero,currentTime); break;}
                        case Personaje.UP:{    pacman.moveUp   (tablero,currentTime); break;}
                    } //System.out.println("J1:  x = "+J1.x+",   y = "+J1.y);
                    pacman.draw(g);
                    switch(fantasma.currentDirection){
                        case Personaje.RIGTH:{ fantasma.moveRigth(tablero,currentTime); break;}
                        case Personaje.DOWN:{  fantasma.moveDown (tablero,currentTime); break;}
                        case Personaje.LEFT:{  fantasma.moveLeft (tablero,currentTime); break;}
                        case Personaje.UP:{    fantasma.moveUp   (tablero,currentTime); break;}
                    }
                    fantasma.draw(g);
                    
                    Thread.sleep(30);
                    canvas.getBufferStrategy().show();
                    
                    if (tablero.isEmptyPuntos() || pacman.currentStatus == Personaje.MUERTO) {
                        tablero.paintTablero(g);
                        if (pacman.currentStatus == Personaje.MUERTO) {
                            pacman.muerte(currentTime);
                            vidas[pacman.getVidas()].setVisible(false);
                            pacman.quitarVidas();
                            estado.setText("PERDISTE  :(");
                            if(pacman.getVidas() == -1) estado.setText("GAME OVER");System.out.println("***********"+pacman.getVidas());
                        }else{
                            estado.setText("GANASTE!! :)");
//                            fantasma.muerte(currentTime);
                        }
                        pacman.draw(g);
                        fantasma.draw(g);
                        canvas.getBufferStrategy().show();
                        break;
                    }
                }catch(Exception e){
                    e.printStackTrace();
                }
            }
            }
        };
    }
    
    public static void main(String[] args) {
//        try{
//            Principal p = new Principal(Tablero.WIDTH,Tablero.HEIGHT);
//            p.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//            p.setResizable(false);
//            p.setLocationRelativeTo(null);
//            p.setVisible(true);
//            p.movLoop.start();
//            p.movFant.start();
//        }catch(Exception e){
//            e.printStackTrace();
//        }
    }

    private void getVidas(JPanel panel, int posi) {
        for (int i = 0; i < 3; i++) {
            vidas[i] = new JLabel();
            vidas[i].setIcon(this.pacman.getImg());
            vidas[i].setBounds(posi, 12, Personaje.DIAMETRO, Personaje.DIAMETRO);
            posi += Personaje.DIAMETRO+5;
            panel.add(vidas[i]);
        }
    }

    
}
