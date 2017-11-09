/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pacman;

import pacman.Tablero.Camino;

/**
 *
 * @author nicolasbuitrago
 */
public class Fantasma extends Personaje{
    
    public Fantasma (int x, int y, int vx, int vy, String path){
        super(x, y, vx, vy, path);
    }
    
    public Runnable getMovieLoop(Pacman J, Tablero tablero){
        Fantasma F = this;
        
        return new Runnable() {

            @Override
            public void run() {
                long startTime = System.currentTimeMillis();
                long currentTime = 0; int Jcd = J.currentDirection;
                while(true){
                    try{
                        if(J.currentDirection != Jcd){tablero.setCuadrante(F);
                        Camino camino = tablero.dijkstra(cuadrante, J.getCuadrante(), tablero);
                        tablero.paintCamino(camino,cuadrante);
                        if(camino.getDistancia()!=Integer.MAX_VALUE)F.currentDirection = tablero.getDirection(camino.get(0), camino.get(1));
                            
                        System.out.println("FD = "+F.currentDirection);
                        
                        currentTime = System.currentTimeMillis() - startTime;
                        switch(F.currentDirection){
                            case Personaje.RIGTH:{ F.moveRigth(tablero,currentTime); break;}
                            case Personaje.DOWN:{  F.moveDown (tablero,currentTime); break;}
                            case Personaje.LEFT:{  F.moveLeft (tablero,currentTime); break;}
                            case Personaje.UP:{    F.moveUp   (tablero,currentTime); break;}
                        }}
                        Thread.sleep(500);
                    } catch (IndexOutOfBoundsException ie) {
                        System.out.println("Problema 34");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        };
    }
 
}