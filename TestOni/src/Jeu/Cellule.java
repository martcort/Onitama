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
    private String fond; // pourra prendre la valeur gris,normal ou marron, pour l'interface graphique
    int[] position;
    // Contructeur de cellule
    public Cellule(String stt,int[] posit){
        statut = stt;
        position = posit;
        fond = "normal";
        
    }
    
    
    // Méthodes donnant des informations
    String donneStatut(){
        return statut;
    }
    String donneFond(){
        return fond;
    }
    
   int[] donnePosition(){
       return position;
   }
   
    
    // Méthodes de changement de varaibles
    void changeStatut(String stt){
        statut = stt;
    }
    void changeFond(String fd){
        fond = fd;
    }
    void devientGris(){
        fond = "gris";
    }
    void devientMarron(){
        fond = "marron";
    }
    void devientNormal(){
        fond = "normal";
    }
}
