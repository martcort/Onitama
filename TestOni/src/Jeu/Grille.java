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
    public Cellule[][] grille; // Initialisation de la grille

    
    // Contructeur de grille
    public Grille(){
        grille = new Cellule[5][5];
        
        for (int j=0;j<5;j++){
            for (int i=0;i<5;i++){
                int[] position = {j,i};
                grille[j][i]= new Cellule("vide",position); // On remplit la grille de cellules lumineuses avec false comme valeur par défaut
            }
        }
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
    
    // Vérifie la légalité d'un déplacement voulu par un pion
    boolean deplacementLegal(int[] position, int[] deplacement){
        int xp = position[0];
        int yp = position[1];
        //int xRelatif = deplacement[1];
        //int yRelatif = deplacement[0];
        
        int xd = deplacement[1];
        int yd = deplacement[0];
        
        //int xd = xp + xRelatif;
        //int yd = yp + yRelatif;
        // Observer
        //System.out.println("yp:"+yp+" xp:"+xp+" yd:"+yd+" xd:"+xd);
        
        
        if( xd<0 || yd<0 || xd>4 || yd >4 ){ // Si le déplacement sort de la grille, il est illégal
            return false;
        }
        String statutPion = grille[yp][xp].donneStatut();
        String statutDep = grille[yd][xd].donneStatut();
        // Observer
        System.out.println("1- Statut de y:"+yp+", x:"+xp+" ="+statutPion);
        System.out.println("2- Statut de y:"+yd+", x:"+xd+" ="+statutDep);
        
        
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
    
    // Méthodes de changement de variables
    // Initialiser la grille pour débuter une nouvelle partie
    public void initialiser()  {
        for(int i=0;i<5;i++){
            grille[i][0].changeStatut("eb");
            grille[2][0].changeStatut("mb");  
        }
                for(int i=0;i<5;i++){
            grille[i][4].changeStatut("er");
            grille[2][4].changeStatut("mr");  
        }
    }
    boolean victoireGauche(){
        
        boolean victoire = true;
        
        for(int i=0;i<5;i++){
            for(int j=0;j<5;j++){
                System.out.println(grille[i][j].donneStatut());
                if("er".equals(grille[i][j].donneStatut()) || "mr".equals(grille[i][j].donneStatut()) ){
                    victoire = false;
                }
            }
        }
        // TEST
        //victoire = true;
        //---
        return victoire;
    }
    boolean victoireDroite(){
        boolean victoire = true;
        for(int i=0;i<5;i++){
            for(int j=0;j<5;j++){
                System.out.println(grille[i][j].donneStatut());
                if("eb".equals(grille[i][j].donneStatut()) || "mb".equals(grille[i][j].donneStatut()) ){
                    
                    victoire = false;
                }
            }
        }
        // TEST
        victoire = true;
        return victoire;
    }
    
    public void toutNormal(){
        for(int i=0;i<5;i++){
            for(int j=0;j<5;j++){
                grille[i][j].changeFond("normal");
            }
        }
}
    
    @Override
    public String toString(){
        String txt="";
        String stt;
        for (int i=0;i<5;i++){
            for(int j=0;j<5;j++){
                stt = grille[i][j].donneStatut();
                txt = txt + stt+" : ";
            }
            txt = txt +"\n";
        }
        return txt;
    }
}



