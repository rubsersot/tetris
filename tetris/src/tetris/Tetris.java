package tetris;

import utils.Utils;
import java.util.Random;

public class Tetris {

    private static int[][] tauler;
    private static peca pecaActual;

    public static void main(String[] args) {
        //Demanem la mida del tauler en files i columnes, el mínim del tauler serà de 8x4 
        int fila = Utils.LlegirInt("Quantes files vols: ");
        boolean n_files_valid = false;
        while (!n_files_valid) {
            if (fila >= 8) {
                n_files_valid = true;
            } else {
                fila = Utils.LlegirInt("El tamany mínim del tauler ha de ser de 8x4, torna a introduir les files: ");
            }
        }
        int columna = Utils.LlegirInt("Quantes columnes vols: ");
        boolean n_columnes_valid = false;
        while (!n_columnes_valid) {
            if (columna >= 4) {
                n_columnes_valid = true;
            } else {
                columna = Utils.LlegirInt("El tamany mínim del tauler ha de ser de 8x4, torna a introduir les columnes: ");
            }
        }
        /*Li afegim +4 a la fila perque aquest serà l'espai on es mostaran les peces*/
        tauler = new int[fila + 4][columna];

        boolean jugant = comprobarfinal();

        while (jugant) {
            pecaActual = new peca();
            Mostrarpeca(tauler, fila, columna);
            MostrarTaulell(tauler, fila, columna);
            Mourepeca(tauler);
            FerCaureLaPeca(tauler);
            MostrarTaulell(tauler, fila, columna);
        }

    }

    public static boolean comprobarfinal() {
        boolean jugant = true;

        for (int i = 0; i < tauler[0].length; i++) {
            if (tauler[0][i] == 1) {
                jugant = false;
            }
        }
        return jugant;

    }

    public static void Mostrarpeca(int tauler[][], int fila, int columna) {
        
    }

    public static void MourePeca(int tauler[][]) {

        int direccion = Utils.LlegirInt("Introduce la dirección a mover la pieza (1/0): ");

        if (direccion == 1) {
            pecaActual.moveLeft();
        } else if (direccion == 0) {
            pecaActual.moveRight();
        }

    }

    public static void FerCaureLaPeca(int tauler[][]) {
        // Obtiene las posiciones actuales de la pieza
        int[][] posicions = pecaActual.getPosicions();
        boolean piezaEnMovimiento = true;
        // Mientras la pieza esté en movimiento
        while (piezaEnMovimiento) {
            piezaEnMovimiento = false;
            // Recorre las posiciones de la pieza
            for (int i = 0; i < posicions.length; i++) {
                int fila = posicions[i][0];
                int columna = posicions[i][1];
                // Si la posición de abajo está vacía
                if (fila < tauler.length - 1 && !tauler[fila + 1][columna]) {
                    // Actualiza la posición de la pieza para que caiga
                    posicions[i][0]++;
                    piezaEnMovimiento = true;
                }
            }
            // Si la pieza no está en movimiento, significa que ha llegado al fondo
            if (!piezaEnMovimiento) {
                // Recorre las posiciones de la pieza
                for (int i = 0; i < posicions.length; i++) {
                    int fila = posicions[i][0];
                    int columna = posicions[i][1];
                    // Marca las posiciones de la pieza como ocupadas en el tablero
                    tauler[fila][columna] = true;
                }
                // Comprueba si se ha formado una línea completa
                comprobarLineaCompleta(tauler);
            }
        }
    }

    public static void comprobarLineaCompleta(int tauler[][]) {
        for (int fila = 0; fila < tauler.length; fila++) {
            boolean lineaCompleta = true;
            // Recorre las posiciones de la fila
            for (int columna = 0; columna < tauler[fila].length; columna++) {
                // Si encuentra una posición vacía, la línea no esta completa
                if (tauler[fila][columna]==1) {
                    lineaCompleta = false;
                    break;
                }
            }
            // Si la línea está completa
            if (lineaCompleta) {
                // Recorre las filas desde la fila completa hasta el final
                for (int i = fila; i > 0; i--) {
                    // Mueve las filas una posición hacia abajo
                    for (int j = 0; j < tauler[i].length; j++) {
                        tauler[i][j] = tauler[i - 1][j];
                    }
                }
                // Reinicia la primera fila
                for (int j = 0; j < tauler[0].length; j++) {
                    tauler[0][j] = 0;
                }
            }
        }
    }

    public static void MostrarTaulell(int tauler[][], int fila, int columna) {

        for (int i = 0; i < fila; i++) {
            for (int j = 0; j < columna; j++) {
                System.out.print(tauler[i][j] + " ");
            }
            System.out.println(" ");
        }

    }

    public class peca {

        private int[][] posicions;
        private int tipus;

        public peca() {
            Random rand = new Random();
            tipus = rand.nextInt(4) + 1;
            switch (tipus) {
                case 1: {//Pieza I
                    posicions = new int[][]{
                        {0, 1, 0},
                        {0, 1, 0},
                        {0, 1, 0},
                        {0, 1, 0}
                    };
                    break;
                }
                case 2: {//Pieza T
                    posicions = new int[][]{{0, 0, 0},
                    {1, 1, 1},
                    {0, 1, 0}
                    };
                    break;
                }
                case 3: {//Pieza cuadrado
                    posicions = new int[][]{
                        {1, 1},
                        {1, 1},};
                    break;
                }
                case 4: {//Pieza L
                    posicions = new int[][]{
                        {0, 1, 0},
                        {0, 1, 0},
                        {0, 1, 1}
                    };
                    break;
                }
                case 5: {//Pieza L tumbada
                    posicions = new int[][]{
                        {0, 0, 0},
                        {1, 1, 1},
                        {1, 0, 0}
                    };
                    break;
                }
            }
        }

        public void moveLeft(int[][] tauler) {
            // recorre las posiciones de la pieza
            for (int i = 0; i < posicions.length; i++) {
                // obtiene las coordenadas de la posición actual
                int fila = posicions[i][0];
                int columna = posicions[i][1];
                // si la posición a la izquierda está vacía, se mueve la posición actual hacia la izquierda
                if (columna > 0 && tauler[fila][columna - 1] == 0) {
                    posicions[i][1]--;
                }
            }
        }

        public void moveRight(int[][] tauler) {
            // recorre las posiciones de la pieza
            for (int i = 0; i < posicions.length; i++) {
                // obtiene las coordenadas de la posición actual
                int fila = posicions[i][0];
                int columna = posicions[i][1];
                // si la posición a la derecha está vacía, se mueve la posición actual hacia la derecha
                if (columna < tauler[0].length - 1 && tauler[fila][columna + 1] == 0) {
                    posicions[i][1]++;
                }
            }
        }

        public int[][] getPosicions() {
            return posicions;
        }

    }
}
