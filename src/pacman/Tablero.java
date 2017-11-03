/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pacman;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Objects;
import java.util.Queue;

/**
 *
 * @author nicolasbuitrago
 */
public class Tablero {
    
    private Cuadrante[][] tablero;
    private ArrayList<Cuadrante> grafo;
    private int[][] adyacencia;
    private int m,n;
    private final int TAM_CUADRANTE = 50;
    public static final int WIDTH = 1100;
    public static final int HEIGHT = 725;

    public Tablero(int[][] mundo, int m, int n) {
        this.tablero = new Cuadrante[m][n];
        this.adyacencia = new int[grafo.size()][grafo.size()];inicializarAdyacencia();
        this.grafo = new ArrayList();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (mundo[i][j] == 1) {
                    tablero[i][j] = new Cuadrante(TAM_CUADRANTE * j, TAM_CUADRANTE * i, TAM_CUADRANTE, false);
                } else {
                    Cuadrante c = new Cuadrante(TAM_CUADRANTE * j, TAM_CUADRANTE * i, TAM_CUADRANTE, true);
                    c.setName(grafo.size());
                    tablero[i][j] = c;
                    grafo.add(c);
                }
            }
        }
        getAdyacencia();
        this.m = m;
        this.n = n;
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
    
    public void paintTablero(Graphics g){
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
        
        // <editor-fold defaultstate="collapsed" desc="Primer intento por encontrar camino (Busqueda binaria)">
        /*
        int inicio = 0, fin = 7 - 1, centro;
        while (inicio <= fin) {
            centro = (fin + inicio) / 2;
            if (tablero[1][centro].getX() == x) {
                break;
            } else if (x < tablero[1][centro].getX()) {
                fin = centro - 1;
            } else {
                inicio = centro + 1;
            }
        }

        Cuadrante c = tablero[x/100][y/100];
        boolean in = c.intersects(x, y);
        
        return tablero[x/100][y/100].isIs();
    */
        // </editor-fold> 
        
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
    
    public void setCuadrante(Personaje J){
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                Cuadrante c = tablero[i][j];
                if(c.intersects(J.getX(), J.getY())&& !c.isIs()){
                    J.setCuadrante(c);
                }
            }
        }
    }
    
    private void inicializarAdyacencia(){
        for (int i = 0; i < grafo.size(); i++) {
            for (int j = 0; j < grafo.size(); j++) {
                adyacencia[i][j] = 0;
            }
        }
    }
    
    private void getAdyacencia(int i, int j){
        if(grafo.contains(tablero[i+1][j])){
            adyacencia[tablero[i][j].getName()][tablero[i+1][j].getName()] = 1;
        }
        if(grafo.contains(tablero[i-1][j])){
            adyacencia[tablero[i][j].getName()][tablero[i-1][j].getName()] = 1;
        }
        if(grafo.contains(tablero[i][j+1])){
            adyacencia[tablero[i][j].getName()][tablero[i][j+1].getName()] = 1;
        }
        if(grafo.contains(tablero[i][j-1])){
            adyacencia[tablero[i][j].getName()][tablero[i][j-1].getName()] = 1;
        }
    }
    
    private void getAdyacencia(){
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (tablero[i][j].isIs()){
                    getAdyacencia(i,j);
                }
            }
        }
    }

    private class Camino implements Comparable{
        
        private ArrayList<Cuadrante> ruta;
        private int distancia;

        public Camino() {
            this.distancia = 0;
            this.ruta = new ArrayList();
        }
        
        public Camino(int distancia) {
            this.distancia = distancia;
        }
        
        void add(Cuadrante cuadrante){
            ruta.add(cuadrante);
            distancia++;
        }
        
        boolean contains(Cuadrante cuadrante){
            return this.ruta.contains(cuadrante);
        }
        
        @Override
        public int compareTo(Object o) {
            return this.distancia - ((Camino) o).distancia; //Si this es mayor que o return es positivo >0
        }

        private Camino dijkstra(Cuadrante inicio, Cuadrante  fin, Tablero tablero) {
            int[] distancia = new int[grafo.size()];
            boolean[] visto = new boolean[grafo.size()];
            ArrayList<Camino> caminos = new ArrayList();
            for (Cuadrante cuadrante : grafo) {
                int i = cuadrante.getName();
                distancia[i] = Integer.MAX_VALUE;
                visto[i++] = false;
            }
            distancia[inicio.getName()] = 0;Queue<Cuadrante> cola = new LinkedList();
//            ArrayList<Cuadrante> cola = new ArrayList();  // cola de prioridad
            Camino camino = new Camino();
            camino.add(inicio);
            cola.add(inicio);
            caminos.add(camino);
            while (!cola.isEmpty()) {
//                Cuadrante nod = extraerPrimero(cola);
                Cuadrante cuad = cola.poll();
                int u = cuad.getName();
                visto[cuad.getName()] = true;
                for (int j = 0; j < grafo.size(); j++) {
                    if (adyacencia[u][j] == 1 && distancia[j] > distancia[u] + 1) {
                        distancia[j] = distancia[u] + 1;
                        add(caminos, cuad, grafo.get(j));
                        cola.add(grafo.get(j)); //  if(j==nf.getName()){ imprimirRuta(Ruta.rutaMasCorta(rutas,nf));System.out.println("+++++");}
                    }
                }
            }
            return Ruta.rutaMasCorta(caminos, nf);
        }

        private void add(ArrayList<Camino> caminos, Cuadrante cuad, Cuadrante cu) {
            Camino camino = subRutaMasCorta(caminos, cuad);
            camino.add(cu);
            camino.addDistancia(distancia(cuad, cu));
            caminos.add(camino);
        }
        
        private Camino subCamino(Cuadrante cuad) {
            
        }
        
        private void addDistancia(int distancia){
            this.distancia += distancia;
        }
        
//        private Cuadrante extraerPrimero(ArrayList<Cuadrante> n) {
//            Cuadrante nod = n.get(0);
//            n.remove(nod);
//            return nod;
//        }
        
        private Camino subRutaMasCorta(ArrayList<Camino> rutas, Cuadrante cuad) {
            Camino min = new Camino(Integer.MAX_VALUE);
            for (Camino camino : rutas) {
                Camino c = camino.subCamino(cuad);
                if (camino.contains(cuad) && min.compareTo(c) > 0) {
                    min = c;
                    min.addDistancia(min.ruta.size());
                }
            }
            return min;
        }

        @Override
        public int hashCode() {
            int hash = 7;
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
            final Camino other = (Camino) obj;
            if (this.distancia != other.distancia) {
                return false;
            }
            if(this.ruta.size() != other.ruta.size()){
                return false;
            }
            for (int i = 0; i < this.ruta.size(); i++) {
                if (!this.ruta.get(i).equals(other.ruta.get(i))) {
                    return false;
                }
            }
            return true;
        }

        
        
        
    }
    
    
    
    
//    private Ruta dijkstra(Cuadrante ni,Cuadrante nf){
//        int[] distancia = new int[nodos.size()];
//        boolean[] visto = new boolean[nodos.size()];
//        ArrayList<Ruta> rutas = new ArrayList();
//        for (Cuadrante nodo : nodos) {
//            int i = nodo.getName();
//            distancia[i] = Integer.MAX_VALUE;
//            visto[i] = false;
//        }
//        distancia[ni.getName()] = 0;
//        ArrayList<Cuadrante> cola =  new ArrayList();  // cola de prioridad
//        Ruta ruta = new Ruta();
//        ruta.add(ni);
//        cola.add(ni);  
//        rutas.add(ruta);
//        while (!cola.isEmpty()) {
//            Cuadrante nod = extraerPrimero(cola);
//            int u = nod.getName();
//            visto[nod.getName()] = true;
//            for (int j = 0; j < nodos.size(); j++) {
//                if(adyacencia[u][j] == 1 && distancia[j]>distancia[u]+distancia(nod,nodos.get(j))){
//                    distancia[j] = distancia[u]+distancia(nod,nodos.get(j));
//                    add(rutas,nod,nodos.get(j));
//                    cola.add(nodos.get(j)); //  if(j==nf.getName()){ imprimirRuta(Ruta.rutaMasCorta(rutas,nf));System.out.println("+++++");}
//                }
//            }
//        }
//        return Ruta.rutaMasCorta(rutas,nf);
//    }
//    
//    private class Ruta implements Comparable {
//
//        private ArrayList<Cuadrante> ruta;
//        private int distancia;
//
//        Ruta() {
//            this.ruta = new ArrayList();
//            this.distancia = 0;
//        }
//       
//        private Ruta(int dist) {
//            ruta = new ArrayList();
//            this.distancia = dist;
//        }
//
//        Cuadrante getLast() {
//            return this.ruta.get(ruta.size() - 1);
//        }
//
//        int getDistancia() {
//            return this.distancia;
//        }
//
//        void addDistancia(int dist) {
//            this.distancia += dist;
//        }
//
//        boolean contains(Cuadrante nodo) {
//            return ruta.contains(nodo);
//        }
//
//        int indexOf(Cuadrante nodo) {
//            return ruta.indexOf(nodo);
//        }
//
//        Cuadrante get(int index) {
//            return ruta.get(index);
//        }
//
//        ArrayList<Cuadrante> getRuta() {
//            return ruta;
//        }
//
//        Ruta rutaMasCorta(ArrayList<Ruta> rutas, Cuadrante nf) {
//            Ruta min = new Ruta(Integer.MAX_VALUE);
//            for (Ruta ruta : rutas) {
//                if (ruta.compareTo(min) < 0) {
//
//                    if (ruta.getLast().equals(nf)) {
//                        if (!ruta.ruta.isEmpty()) {
//                            min = ruta;
//                        }
//                    } else if (ruta.contains(nf)) {
//                        Ruta r = ruta.subRuta(nf);
//                        if (min.compareTo(r) > 0) {
//                            min = r;
//                            min.addDistancia(Grafo.distancia(min));
//                        }
//                    }
//
//                }//else if(min.compareTo(ruta)==0) System.out.println("SON IGUALES LAS RUTAS =0");
//            }
//            return min;
//        }
//
//        @Override
//        public int compareTo(Object o) {
//            return this.distancia - ((Ruta) o).distancia; //Si this es mayor que o return es positivo >0
//        }
//
//        @Override
//        public int hashCode() {
//            int hash = 5;
//            hash = 79 * hash + Objects.hashCode(this.ruta);
//            hash = 79 * hash + this.distancia;
//            return hash;
//        }
//
//        @Override
//        public boolean equals(Object obj) {
//            if (this == obj) {
//                return true;
//            }
//            if (obj == null) {
//                return false;
//            }
//            if (getClass() != obj.getClass()) {
//                return false;
//            }
//            final Ruta other = (Ruta) obj;
//            if (this.distancia != other.distancia) {
//                return false;
//            }
//            if (this.ruta.size() != other.ruta.size()) {
//                return false;
//            }
//            for (int i = 0; i < this.ruta.size(); i++) {
//                if (!this.ruta.get(i).equals(other.ruta.get(i))) {
//                    return false;
//                }
//            }
//            return true;
//        }
//    }
}
