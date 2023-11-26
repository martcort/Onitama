/*
 * Classe : Pion 
 * Crée le 22/11/2023
 */
package Jeu;

/**
 *
 * @author marti
 */
public class Pion {
    // Initialisation des variables
    private int[] position = new int[2];
    private String statut;

    // Contructeur de pion
    public Pion(String stt, int x, int y){
        position[1] = x;
        position[0] = y;
        statut = stt; 
    }
    
    
    // Méthodes d'information
    // Renvoie la position du pion
    int[] donnePosition(){
        return position;
    }
    
    // Renvoie le statut du pion
    String donneStatut(){
        return statut;
    }
    
    // Méthodes de changement de variables
    // Déplacement du pion
    void changePosition(int[] cooDeplacement){
        position[1] = cooDeplacement[1];
        position[0] = cooDeplacement[0];
    }
}
