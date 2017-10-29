/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pacman;

/**
 *
 * @author nicolasbuitrago
 */
public class Tablero {
    
    Cuadrante[][] tablero;

    public Tablero(int[][] mundo) {
        this.tablero = new Cuadrante[7][11];
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 11; j++) {
                if (mundo[i][j] == 1) {
                    tablero[i][j] = new Cuadrante(100 * j, 100 * i, 100, false);
                } else {
                    tablero[i][j] = new Cuadrante(100 * j, 100 * i, 100, true);
                }
            }
        }
    }
    
//    public void add(int i, int j, int size){
//        
//        tablero[i][j] = ;
//    }
    
}
