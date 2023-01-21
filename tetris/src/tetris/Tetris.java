package tetris;

import utils.Utils;

public class Tetris {

    private static boolean[][] tauler;
    
    private static peca pecaactual;

     

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
            
            pecaactual = new peca();

            Mostrarpeca();

            Mourepeca();

            FerCaureLaPeca();

            MostrarTaulell(tauler, fila, columna);
        }

    }

    public static void Mostrarpeca() {

    }

    public static void MourePeca(boolean tauler[][]) {

        int direccio = Utils.LlegirInt("Introduce la dirección a mover la pieza (1/0): ");

        if (direccio == 1) {
            pecaactual.moveLeft();
        } else if (direccio == 0) {
            pecaactual.moveRight();
        }

    }

    public static void FerCaureLaPeca(boolean tauler[][]) {

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
    
    public static boolean comprobarfinal(){
        boolean gameover = false;
        
        for(int i = 0; i<tauler[0].length; i++){
            if(tauler[0][i]){
                gameover = true;
            }
        }
        return gameover;
       
    }
    
    
}
