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
    private String fond; // pourra prendre la valeur gris ou normal, pour l'interface graphique
    // Contructeur de cellule
    public Cellule(String stt){
        statut = stt;
        statut = "normal";
    }
    
    
    // Méthodes donnant des informations
    String donneStatut(){
        return statut;
    }
    String donneFond(){
        return fond;
    }
    
    // Méthodes de changement de varaibles
    void changeStatut(String stt){
        statut = stt;
    }
    void changeFond(String fd){
        statut = fd;
    }
}
