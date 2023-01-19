package tetris;

import utils.Utils;

public class Tetris {

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
                columna = Utils.LlegirInt("El tamany mínim del tauler ha de ser de 4x8, torna a introduir les columnes: ");
            }
        }
        boolean[][] tauler = new boolean[fila][columna];

    }

    static boolean Mostrarpeca() {

    }

    static boolean MourePecaCostats(boolean tauler[][]) {

    }

    static boolean FerCaureLaPeca(boolean tauler[][]) {

    }

    static void MostrarTaulell(boolean tauler[][]) {

        for (int i = tauler.length - 1; i >= 0; --i) {
            for (int j = 0; j < tauler[0].length; ++j) {
                if (tauler[i][j]) {
                    System.out.print("X");
                }
            }
        }
        

    }

}
