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
    ArrayList<Carte> deckTotal = new ArrayList<>();
    int[][] coups = new int[4][2];
    Carte carte;
    // Initialisation du deck de cartes
        
    // Initialisation des decks des deux joueurs
    
    // Contructeur de partie
    public Partie(){
    }
    
    
    
    // - > DECKS CARTES
    
    // - > LISTE DE PIONS DES JOUEURS
    // - > TRAITEMENT FICHIERS .txt
    // Méthode pour récupérer le contenu de la ligne n d'un fichier texte
    public static int recupererValeurLigne(String cheminFichier, int numeroLigne) {
        int valeur = 0;

        try (BufferedReader lecteur = new BufferedReader(new FileReader(cheminFichier))) {
            String ligne;
            int numeroLigneActuelle = 1;

            // Parcourir le fichier jusqu'à atteindre la ligne souhaitée
            while ((ligne = lecteur.readLine()) != null) {
                if (numeroLigneActuelle == numeroLigne) {
                    // Vous avez trouvé la ligne souhaitée, convertissez la valeur en int
                    valeur = Integer.parseInt(ligne.trim());
                    break; // Sortir de la boucle une fois la ligne trouvée
                }

                numeroLigneActuelle++;
            }
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
            // Gérer les erreurs liées à la lecture du fichier ou à la conversion en int
        }

        // Retourner la valeur de la ligne
        return valeur;
    }
    private static String recupererTexteLigne(String cheminFichier, int numeroLigne) {
        String ligne = null;

        try (BufferedReader lecteur = new BufferedReader(new FileReader(cheminFichier))) {
            int numeroLigneActuelle = 1;

            // Parcourir le fichier jusqu'à atteindre la ligne souhaitée
            while ((ligne = lecteur.readLine()) != null) {
                if (numeroLigneActuelle == numeroLigne) {
                    // Vous avez trouvé la ligne souhaitée
                    break; // Sortir de la boucle une fois la ligne trouvée
                }

                numeroLigneActuelle++;
            }
        } catch (IOException e) {
            e.printStackTrace();
            // Gérer les erreurs liées à la lecture du fichier
        }

        // Retourner la ligne du fichier en tant que String
        return ligne;
    }
    ArrayList<Carte> creerDeckTotal(){
        ArrayList<Carte> deck = new ArrayList<>();
        String nom;
        String image;
        int[][]coups = new int[4][2];
        
        for(int i=0;i<16;i++){
            nom = recupererTexteLigne("src/Jeu/noms.txt",i+1);
            image = nom + ".png";
            for(int j=0;j<8;j++){
                
                   coups[j/2][1]= recupererValeurLigne("src/Jeu/coups.txt", (i*16+j+1));
                   System.out.println(recupererValeurLigne("src/Jeu/coups.txt", (i*16+j+1))); 
            }
            carte = new Carte(nom, coups);
            deck.add(carte);
        }
        return deck;
    }
            
    
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
