/*
 * Classe : Partie 
 * Crée le 22/11/2023
 */
package Jeu;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author marti
 */
public class Partie {
    
    // Initialisation des variables
    Grille grille = new Grille();
    Scanner scan = new Scanner(System.in);
    ArrayList<Integer> deckTotal = new ArrayList<>();
    // Initialisation du deck de cartes
        
    // Initialisation des decks des deux joueurs
    
    // Contructeur de partie
    public Partie(){
 
    }
    
    
    
    // - > DECKS CARTES
    
    // - > LISTE DE PIONS DES JOUEURS
    // - > TRAITEMENT FICHIERS .txt
    // Méthode pour récupérer le contenu de la ligne n d'un fichier texte
    int[][] coups = {{0,1},{1,0},{0,-1},{0,0}};
    Carte carte = new Carte("Sanglier",coups);
    //deckTotal.add(carte);
    
    
    // - > DEPLACEMENT PION
    // Choix du déplacement sur une carte
    int[] choixDeplacement(Carte carte){
        // Initialisation des variables
        boolean choixValide = false;
        int[] deplacement = {0, 0};
        
        while(choixValide == false){
            // On demande au joueur de choisir un des 4 déplacements offerts par la carte dont il dispose.
            int choix = scan.nextInt();
            switch (choix) {
                case 1 -> {
                    deplacement = carte.mouvement(1);
                    choixValide = true;     
                        }
                case 2 -> {
                    deplacement = carte.mouvement(2);
                    choixValide = true;
                        }
                case 3 -> {
                    deplacement = carte.mouvement(3);
                    choixValide = true;
                        }
                case 4 -> {
                    deplacement = carte.mouvement(4);
                    choixValide = true;
                        }
                default ->{
                    System.out.println("Choix incorrect.Veuillez entrer un choix entre 1 et 4.");
                }
            }
        }   
        return deplacement;
    }
    
    // Deplacement d'un pion
    void deplacement(Pion pion, Carte carte){
        
        // On additionne les coordonnees de la position du pion à celles du déplacement relatif pour obtenir 
        // les coordonnées du déplacement réel
        int XdeplacementReel = pion.donnePosition()[1] + choixDeplacement(carte)[1];
        int YdeplacementReel = pion.donnePosition()[0] + choixDeplacement(carte)[0];
        int[] deplacement = {YdeplacementReel, XdeplacementReel};
        
        // On change la position du pion grâce à la méthode correspondante
        pion.changePosition(deplacement);
    }
}
