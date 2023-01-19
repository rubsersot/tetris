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
        int[][] tauler = new int[fila][columna];
        
        for(int i = 0; i<fila; i++){
            for(int j = 0; j<columna; j++){
                tauler[i][j] = 0;
            }
        }
        for(int i = 0; i<fila; i++){
            for(int j = 0; j<columna; j++){
                System.out.print(tauler[i][j]);
            }
            System.out.println(" ");
        }
    }
    
}
