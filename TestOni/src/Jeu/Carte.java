/*
 * Classe : Carte 
 * Crée le 22/11/2023
 */
package Jeu;

/**
 *
 * @author marti
 */
public class Carte {
    // Initialisation des variables
    private String nom;
    private int[][] coups = new int[4][2];

    // Contructeur de carte
    public Carte(String name, int[][] tab){
        nom = name;
        coups = tab;
    }
    
    //Méthodes d'information
    String donneNom(){
        return nom;
        
    }
    // Donne les coordonnées relatives du coup souhaité en un tableau int de forme [y x].
    int[] mouvement(int choix){
        return coups[choix-1];
    }
    
    @Override
    public String toString(){
        String txt = "";
        txt =txt+nom;
        for(int i=0;i<4;i++){
            for(int j=0;j<2;j++){
                txt = txt + coups[i][j];
            }
        }
        return txt;
        
    }
}
