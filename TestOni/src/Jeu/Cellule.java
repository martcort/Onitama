/*
 * Classe : Cellule
 * Crée le 22/11/2023
 */
package Jeu;

/**
 *
 * @author marti
 */
public class Cellule {
    // Initialisation des variables
    private String statut; // pourra prendre la valeur (vide, eb, er, mb, mr) respectivement vide, eleve rouge, eleve bleu, maitre rouge, maitre bleu

    // Contructeur de cellule
    public Cellule(String stt){
        statut = stt;
    }
    
    
    // Méthodes donnant des informations
    String donneStatut(){
        return statut;
    }
    
    
    // Méthodes de changement de varaibles
    void changeStatut(String stt){
        statut = stt;
    }
}
