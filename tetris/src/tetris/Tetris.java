package tetris;

import utils.Utils;
import java.util.Random;

public class Tetris {

    private static boolean[][] tauler;
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
        tauler = new boolean[fila + 4][columna];

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
            if (tauler[0][i]) {
                jugant = false;
            }
        }
        return jugant;

    }

    public static void Mostrarpeca(boolean tauler[][], int fila, int columna) {
        for (int i = 0; i < fila; i++) {
            for (int j = 0; j < columna; j++) {
                if (tauler[i][j]) {
                    System.out.print("X ");
                } else {
                    System.out.print(" ");
                }
            }
            System.out.println();
        }
    }

    public static void MourePeca(boolean tauler[][]) {

        int direccion = Utils.LlegirInt("Introduce la dirección a mover la pieza (1/0): ");

        if (direccion == 1) {
            pecaActual.moveLeft();
        } else if (direccion == 0) {
            pecaActual.moveRight();
        }

    }

    public static void FerCaureLaPeca(boolean tauler[][]) {
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

    public static void comprobarLineaCompleta(boolean tauler[][]) {
        for (int fila = 0; fila < tauler.length; fila++) {
            boolean lineaCompleta = true;
            // Recorre las posiciones de la fila
            for (int columna = 0; columna < tauler[fila].length; columna++) {
                // Si encuentra una posición vacía, la línea no esta completa
                if (!tauler[fila][columna]) {
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
                    tauler[0][j] = false;
                }
            }
        }
    }

    public static void MostrarTaulell(boolean tauler[][], int fila, int columna) {

        for (int i = 0; i < fila; i++) {
            for (int j = 0; j < columna; j++) {
                if (tauler[i][j]) {
                    System.out.print("X ");
                } else {
                    System.out.print(" ");
                }
            }
            System.out.println();
        }

    }

    public class peca {

        private boolean[][] posicions;
        private int tipus;

        public peca() {
            Random rand = new Random();
            tipus = rand.nextInt(4) + 1;
            switch (tipus) {
                case 1: {//Pieza I
                    posicions = new boolean[][]{
                        {false, true, false},
                        {false, true, false},
                        {false, true, false},
                        {false, true, false}
                    };
                    break;
                }
                case 2: {//Pieza T
                    posicions = new boolean[][]{
                        {false, false, false},
                        {true, true, true},
                        {false, true, false}
                    };
                    break;
                }
                case 3: {//Pieza cuadrado
                    posicions = new boolean[][]{
                        {true, true},
                        {true, true},};
                    break;
                }
                case 4: {//Pieza L
                    posicions = new boolean[][]{
                        {false, true, false},
                        {false, true, false},
                        {false, true, true}
                    };
                    break;
                }
                case 5: {//Pieza L tumbada
                    posicions = new boolean[][]{
                        {false, false, false},
                        {true, true, true},
                        {false, false, true}
                    };
                    break;
                }
            }
        }

        public void moveLeft(boolean[][] tauler) {
            // recorre las posiciones de la pieza
            for (int i = 0; i < posicions.length; i++) {
                // obtiene las coordenadas de la posición actual
                int fila = posicions[i][0];
                int columna = posicions[i][1];
                // si la posición a la izquierda está vacía, se mueve la posición actual hacia la izquierda
                if (columna > 0 && !tauler[fila][columna - 1]) {
                    posicions[i][1]--;
                }
            }
        }

        public void moveRight(boolean[][] tauler) {
            // recorre las posiciones de la pieza
            for (int i = 0; i < posicions.length; i++) {
                // obtiene las coordenadas de la posición actual
                int fila = posicions[i][0];
                int columna = posicions[i][1];
                // si la posición a la derecha está vacía, se mueve la posición actual hacia la derecha
                if (columna < tauler[0].length - 1 && !tauler[fila][columna + 1]) {
                    posicions[i][1]++;
                }
            }
        }

        public boolean[][] getPosicions() {
            return posicions;
        }

    }

}
