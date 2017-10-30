/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pacman;

import java.awt.Color;
import java.awt.Graphics;

/**
 *
 * @author nicolasbuitrago
 */
public class Tablero {
    
    private Cuadrante[][] tablero;
    private int m,n;
    private final int TAM_CUADRANTE = 50;

    public Tablero(int[][] mundo, int m, int n) {
        this.tablero = new Cuadrante[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (mundo[i][j] == 1) {
                    tablero[i][j] = new Cuadrante(TAM_CUADRANTE * j, TAM_CUADRANTE * i, TAM_CUADRANTE, false);
                } else {
                    tablero[i][j] = new Cuadrante(TAM_CUADRANTE * j, TAM_CUADRANTE * i, TAM_CUADRANTE, true);
                }
            }
        }
        this.m = m;
        this.n = n;
    }
    
    public void paitTablero(Graphics g){
//        g.setColor(Color.BLACK);
//        g.fillRect(0,0, c.getWidth(), c.getHeight());                        
        for (int i = 0; i < this.m; i++) {
            for (int j = 0; j < this.n; j++) {
                if (tablero[i][j].isIs()) {
                    g.setColor(Color.BLACK);
                    g.fillRect(TAM_CUADRANTE * j, TAM_CUADRANTE * i, TAM_CUADRANTE, TAM_CUADRANTE);
                } else {
                    g.setColor(Color.BLUE);
                    g.fillRect(TAM_CUADRANTE * j, TAM_CUADRANTE * i, TAM_CUADRANTE, TAM_CUADRANTE);
                }
            }
        }
    }

    public boolean isCamino(int x, int y) {
//        int inicio = 0, fin = 7 - 1, centro;
//        while (inicio <= fin) {
//            centro = (fin + inicio) / 2;
//            if (tablero[1][centro].getX() == x) {
//                break;
//            } else if (x < tablero[1][centro].getX()) {
//                fin = centro - 1;
//            } else {
//                inicio = centro + 1;
//            }
//        }

//        Cuadrante c = tablero[x/100][y/100];
//        boolean in = c.intersects(x, y);
//        
//        return tablero[x/100][y/100].isIs();
        
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                Cuadrante c = tablero[i][j];
                if(c.intersects(x, y)&& !c.isIs()){
                    return false;
                }
            }
        }
        return true;
    }

    public Cuadrante[][] getTablero() {
        return tablero;
    }

    public int getM() {
        return m;
    }

    public int getN() {
        return n;
    }
    
}
