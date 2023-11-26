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
}
