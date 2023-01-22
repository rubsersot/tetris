package tetris;

import utils.Utils;
import java.util.Random;

public class Tetris {

    private static boolean[][] tauler;
    private static pecaActual pecaActual;

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

        while (!jugant) {
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

    static void MourePeca(boolean tauler[][]) {

        int direccio = Utils.LlegirInt("Introduce la dirección a mover la pieza (1/0): ");

        if (direccio == 1) {
            pecaActual.moveLeft();
        } else if (direccio == 0) {
            pecaActual.moveRight();
        }

    }

    static void FerCaureLaPeca(boolean tauler[][]) {
        // Obtiene las posiciones actuales de la pieza
        int[][] posicions = pecaActual.getPosicions();
        // Recorre las posiciones de la pieza
        for (int i = 0; i < posicions.length; i++) {
            int fila = posicions[i][0];
            int columna = posicions[i][1];
            // Si la posición de abajo está ocupada o es el final del tablero
            if (fila == tauler.length - 1 || tauler[fila + 1][columna]) {
                // Marca las posiciones de la pieza como ocupadas en el tablero
                for (int j = 0; j < posicions.length; j++) {
                    int filaPeca = posicions[j][0];
                    int columnaPeca = posicions[j][1];
                    tauler[filaPeca][columnaPeca] = true;
                }
            }
        }
        // Hace caer la pieza una posición
        pecaActual.moveDown();

    }

    static void MostrarTaulell(boolean tauler[][], int fila, int columna) {

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

    public class pecaActual {

        private boolean[][] posicions;
        private int tipus;

        public pecaActual() {
            Random rand = new Random();
            tipus = rand.nextInt(4)+1;

            switch (tipus) {
                case 0: {//Pieza I
                    posicions = new boolean[][]{
                        {false, true, false},
                        {false, true, false},
                        {false, true, false},
                        {false, true, false}
                    };
                }
                case 1: {//Pieza T
                    posicions = new boolean[][]{
                        {false, false, false},
                        {true, true, true},
                        {false, true, false}
                    };
                }
                case 2: {//Pieza cuadrado
                    posicions = new boolean[][]{
                        {true, true},
                        {true, true},};
                }
                case 3:{//Pieza L
                    posicions = new boolean[][]{                        
                        {false, true, false},
                        {false, true, false},
                        {false, true, true}
                    };        
                }
                case 4:{//Pieza L tumbada
                    posicions = new boolean[][]{                        
                        {false, false, false},
                        {true, true, true},
                        {false, false, true}
                    };        
                }

            }
        }
    }

}


