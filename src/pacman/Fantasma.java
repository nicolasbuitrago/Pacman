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
                        tablero.paintCamino(camino);
                        F.currentDirection = tablero.getDirection(camino.get(0), camino.get(1));
                            System.out.println("FD = "+F.currentDirection);
                        currentTime = System.currentTimeMillis() - startTime;
                        switch(F.currentDirection){
                            case Personaje.RIGTH:{ F.moveRigth(tablero,currentTime); break;}
                            case Personaje.DOWN:{  F.moveDown (tablero,currentTime); break;}
                            case Personaje.LEFT:{  F.moveLeft (tablero,currentTime); break;}
                            case Personaje.UP:{    F.moveUp   (tablero,currentTime); break;}
                        }}
                        Thread.sleep(500);
                    }catch(Exception e){
                        e.printStackTrace();
                    }
                }
            }
        };
    }
 
//    private class Camino {
//        
//        private ArrayList<Cuadrante> ruta;
//        private int distancia;
//
//        public Camino() {
//            this.distancia = 0;
//            this.ruta = new ArrayList();
//        }
//        
//        
//
//        private Camino dijkstra(Cuadrante inicio, Cuadrante  fin, Tablero tablero) {
//            int[][] distancia = new int[tablero.getM()][tablero.getN()];
//            boolean[][] visto = new boolean[tablero.getM()][tablero.getN()];
//            ArrayList<Camino> rutas = new ArrayList();
//            for (int i = 0; i < tablero.getM(); i++) {
//                for (int j = 0; j < tablero.getN(); j++) {
//                    distancia[i][j] = Integer.MAX_VALUE;
//                    visto[i][j] = false;
//                }
//            }
//            distancia[ni.getName()] = 0;
//            ArrayList<Nodo> cola = new ArrayList();  // cola de prioridad
//            Ruta ruta = new Ruta();
//            ruta.add(ni);
//            cola.add(ni);
//            rutas.add(ruta);
//            while (!cola.isEmpty()) {
//                Nodo nod = extraerPrimero(cola);
//                int u = nod.getName();
//                visto[nod.getName()] = true;
//                for (int j = 0; j < nodos.size(); j++) {
//                    if (adyacencia[u][j] == 1 && distancia[j] > distancia[u] + distancia(nod, nodos.get(j))) {
//                        distancia[j] = distancia[u] + distancia(nod, nodos.get(j));
//                        add(rutas, nod, nodos.get(j));
//                        cola.add(nodos.get(j)); //  if(j==nf.getName()){ imprimirRuta(Ruta.rutaMasCorta(rutas,nf));System.out.println("+++++");}
//                    }
//                }
//            }
//            return Ruta.rutaMasCorta(rutas, nf);
//        }
//    }
}