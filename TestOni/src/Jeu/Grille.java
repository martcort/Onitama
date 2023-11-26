/*
 * Classe : Grille 
 * Crée le 22/11/2023
 */
package Jeu;

/**
 *
 * @author marti
 */
public class Grille {
    // Initialisation des variables
    int taille = 5;
    Cellule[][] grille = new Cellule[taille][taille];
    
    
    // Contructeur de grille
    public Grille(){
 
    }
   
    
    // Méthodes d'information
    // Vérifie la non présence de pions bleus dans la grille
    boolean queRouge(){ 
        for(int i=0; i<taille; i++){
            for(int j=0; j<taille; j++){
                if(grille[i][j].donneStatut() =="eb" || grille[i][j].donneStatut() =="mb"){
                    return false; // Si il détecte des pions bleus, return false
                }
            }   
        }
        return true;
    }
    
    // Vérifie la non présence de pions rouges dans la grille
    boolean queBleu(){ 
        for(int i=0; i<taille; i++){
            for(int j=0; j<taille; j++){
                if(grille[i][j].donneStatut() =="er" || grille[i][j].donneStatut() =="mr"){
                    return false; // Si il détecte des pions bleus, return false
                }
            }   
        }
        return true;
    }
    
    // Déplacement la légalité d'un déplacement voulu par un pion
    boolean deplacementLegal(int[] position, int[] deplacement){
        int xp = position[1];
        int yp = position[0];
        int xd = deplacement[1];
        int yd = deplacement[0];
        String statutPion = grille[yp][xp].donneStatut();
        String statutDep = grille[yd][xd].donneStatut();
        if( xd<0 || yd<0 || xd>4 || yd >4 ){ // Si le déplacement sort de la grille, il est illégal
            return false;
        }
        
        if("vide".equals(statutDep)){ // Un déplacement sur une case vide est forcément légal
            return true;
        }
        if("mb".equals(statutPion) || "eb".equals(statutPion)){// Si le pion concerné est bleu, le déplacement sera possible sur les pions rouge uniquement.
            if ("mr".equals(statutDep) || "er".equals(statutDep)){
                return true;     
            }
            else{
                return false;
            }
        }
        if("mr".equals(statutPion) || "er".equals(statutPion)){ // Si le pion concerné est rouge, le déplacement sera possible sur les pions bleus uniquement.
            if ("mb".equals(statutDep) || "eb".equals(statutDep)){
                return true;
            }
            else{
                return false;
            }
        }
        return false; // Au cas ou il ne s'agirait des cas (supposément impossible) on return false
        
    }
}
