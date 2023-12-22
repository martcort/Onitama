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
    private String image;
    private int[][] coups = new int[4][2];
    private int[][] coupsGauche = new int[4][2];
    private int[][] coupsDroite = new int[4][2];
    // Contructeur de carte
    public Carte(String name,String im, int[][] tab){
        nom = name;
        image = im;
        coups = tab;
        
        coupsGauche = coups;
        for(int i=0;i<4;i++){
            int n;
            n = coupsGauche[i][0];
            coupsGauche[i][0] = coupsGauche[i][1];
            coupsGauche[i][1] = -n;
        }
        
        coupsDroite = coups;
        for(int i=0;i<4;i++){
            int n;
            n = coupsGauche[i][0];
            coupsGauche[i][0] = -coupsGauche[i][1];
            coupsGauche[i][1] = n;
        
        }
        }
    
    //Méthodes d'information
    String donneNom(){
        return nom;
        
    }
    
    int[][] donneCoups(){
        return coups;
    }

    
    // Donne les coordonnées relatives du coup souhaité en un tableau int de forme [y x].
    int[] mouvement(int choix){
        return coups[choix-1];
    }
    
    @Override
    public String toString(){
        String txt = "";
        txt =txt+nom+" "+image+" ";
        for(int i=0;i<4;i++){
            txt = txt+"{";
            for(int j=0;j<2;j++){
                txt = txt + coups[i][j];
            }
            txt = txt+"}";
        }
        return txt;
        
    }
}
