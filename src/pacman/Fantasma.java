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
    
    public Runnable getMovieLoop(Tablero tablero){
        Fantasma F = this;
        Pacman J = tablero.getPacman();
        return new Runnable() {

            @Override
            public void run() {
                long startTime = System.currentTimeMillis();
                long currentTime = 0;
                int Jcd = J.currentDirection;
                int ant = F.currentDirection;
//                boolean sw = false;
                while(true){
                    try{
                        if(J.currentDirection != Personaje.NONE){
                            tablero.setCuadrante(F);
                            Camino camino = tablero.dijkstra(cuadrante, J.getCuadrante(), tablero);
//                            tablero.paintCamino(camino, cuadrante);
                            if (camino.getDistancia() != Integer.MAX_VALUE) {
                                F.currentDirection = tablero.getDirection(camino.get(0), camino.get(1));
                            }
                            if (F.currentDirection != ant) {
                                ant = F.currentDirection;
                                F.moveToCenterCuarante(tablero);
                            }
//                            System.out.println("FD = " + F.currentDirection);

                            currentTime = System.currentTimeMillis() - startTime;
                            switch (F.currentDirection) {
                                case Personaje.RIGTH: {
                                    F.moveRigth(tablero, currentTime);
                                    break;
                                }
                                case Personaje.DOWN: {
                                    F.moveDown(tablero, currentTime);
                                    break;
                                }
                                case Personaje.LEFT: {
                                    F.moveLeft(tablero, currentTime);
                                    break;
                                }
                                case Personaje.UP: {
                                    F.moveUp(tablero, currentTime);
                                    break;
                                }
                            }
                        }
//                        if(sw){
//                            J.addPunto();
//                        }else if(J.currentDirection != Personaje.NONE && F.currentStatus != Personaje.MUERTO){
//                            sw = true;
//                        }
                        Thread.sleep(550);
                    } catch (IndexOutOfBoundsException ie) {
                    } catch (Exception e) {
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
            if(tablero.validComePunto(this,x,y)) {}
            if(tablero.isComePacman()) tablero.getPacman().currentStatus = Personaje.MUERTO;
            currentAnimation = Personaje.RIGTH;
            animations[Personaje.RIGTH].update(time); //tablero.setCuadrante(this);
        }
    }
    
    @Override
    public void moveLeft(Tablero tablero, long time){
        if (tablero.isCamino(x - vx, y)) {
            x -= vx;
            if(tablero.validComePunto(this,x,y)){}
            if(tablero.isComePacman()) tablero.getPacman().currentStatus = Personaje.MUERTO;
            currentAnimation = Personaje.LEFT;
            animations[Personaje.LEFT].update(time); //tablero.setCuadrante(this);
        }
    }
    
    @Override
    public void moveUp(Tablero tablero, long time) {
        if (tablero.isCamino(x, y - vy)) {
            y -= vy;
            if(tablero.validComePunto(this,x,y)) {}
            if(tablero.isComePacman()) tablero.getPacman().currentStatus = Personaje.MUERTO;
            currentAnimation = Personaje.UP;
            animations[Personaje.UP].update(time);//tablero.setCuadrante(this);
        }
    }
     
    @Override
    public void moveDown(Tablero tablero, long time) {
        if (tablero.isCamino(x, y + vy)) {
            y += vy;
            if(tablero.validComePunto(this,x,y)) {}
            if(tablero.isComePacman()) tablero.getPacman().currentStatus = Personaje.MUERTO;
            currentAnimation = Personaje.DOWN;
            animations[Personaje.DOWN].update(time);//tablero.setCuadrante(this);
        }
    }

    private void moveToCenterCuarante(Tablero tablero) {
        tablero.setCuadrante(this); 
        this.x = cuadrante.getX()+Tablero.TAM_CUADRANTE/2-Personaje.DIAMETRO/2;
        this.y = cuadrante.getY()+Tablero.TAM_CUADRANTE/2-Personaje.DIAMETRO/2;
    }
 
}
