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
        
        // On définit coups
        for (int i=0;i<4;i++){
            tab[i][0] = -tab[i][0];
        }
        String txt = "";
        for(int i=0;i<4;i++){
            for (int j=0;j<2;j++){
                txt = txt+tab[i][j];
            }
            txt = txt+" ,";
        }
        System.out.println(txt);
        coups = tab;
        
        // On définit coupsGauche (adapté au référentiel)
        int[][] tabG = new int[4][2];
        for(int i=0;i<4;i++){
            for(int j=0;j<2;j++){
                tabG[i][j] = tab[i][j];
            }
        }
        
        for(int i=0;i<4;i++){
            int n = tabG[i][0];
            tabG[i][0] = tabG[i][1];
            tabG[i][1] = -n;
        }
        coupsGauche = tabG;
        
        
        // On définit coupsGauche (adapté au référentiel)
        int[][] tabD = new int[4][2];
        for(int i=0;i<4;i++){
            for(int j=0;j<2;j++){
                tabD[i][j] = tab[i][j];
            }
        }
        
        for(int i=0;i<4;i++){
            int n = tabD[i][0];
            tabD[i][0] = -tabD[i][1];
            tabD[i][1] = n;
        }
        coupsDroite = tabD;
        
        
        
        }
       
        
        
    
    
    //Méthodes d'information
    String donneNom(){
        return nom;
        
    }
    
    int[][] donneCoups(){
        return coups;
    }
    int[][] donneCoupsGauche(){
        // Observer
        String txt = "";
        for(int i=0;i<4;i++){
            txt = txt+"{";
            for(int j=0;j<2;j++){
                txt = txt + coupsGauche[i][j];
            }
            txt = txt+"}";
        }
        System.out.println("Il s'agit d'observer :"+txt);
        
        return coupsGauche;
    }
    int[][] donneCoupsDroite(){
        return coupsDroite;
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
        txt =txt + "// ";
        for(int i=0;i<4;i++){
            txt = txt+"{";
            for(int j=0;j<2;j++){
                txt = txt + coupsGauche[i][j];
            }
            txt = txt+"}";
        }
        return txt;
        
    }
}
