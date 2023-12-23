package Jeu;

import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.SwingUtilities;

/**
 *
 * @author marti
 */
public class fenetrePrincipale extends javax.swing.JFrame {
    
    Carte carte;
    Carte carteCliquee;
    Grille grille = new Grille();
    Cellule celluleJouee;
    String joueurJouant;
    Carte carteJouee;
    
    
    
    
    ArrayList<Carte> deckTotal;
    ArrayList<Carte> deckJrGauche;
    ArrayList<Carte> deckJrDroite;
    ArrayList<Carte> defausse;
    
    
    // Dimensions fenetre
    int largeurCarte = 300;
    int hauteurCarte = 180;
    int largeurGrille = 500;
    /**
     * Creates new form fenetrePrincipale
     */
    public fenetrePrincipale() {
        initComponents();
        // Initialisation des variables
        // Positionnement des JButton
        getContentPane().add(logo, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 40,
        266, 113));
        
        getContentPane().add(texte, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 40,
        266, 113));
        
        getContentPane().add(coteGauche, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 250,
        largeurCarte, hauteurCarte*2));
        
        getContentPane().add(jPanelGrille, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 200,
        largeurGrille, largeurGrille));
        
        getContentPane().add(coteDroit, new org.netbeans.lib.awtextra.AbsoluteConstraints(largeurGrille+largeurCarte+40, 250,
        largeurCarte, hauteurCarte*2));
        
        getContentPane().add(defausseJPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(425, 10,
        largeurCarte, hauteurCarte));
        this.pack();
        this.revalidate();
    
    
        
        
        jPanelGrille.setLayout(new GridLayout(5, 5));
        coteDroit.setLayout( new GridLayout(2,1));
        coteGauche.setLayout( new GridLayout(2,1));
        defausseJPanel.setLayout( new GridLayout(1,1));
        logo.setLayout( new GridLayout(1,1));
        texte.setLayout( new GridLayout(1,1));
        
        
        // -----------------------------Initialisation deck et défausse-------------------------------------------------------
        deckTotal = creerDeckTotal();
        deckJrGauche = initDeckJr();
        deckJrDroite = initDeckJr();
        defausse = initDefausse();
        
        // Pour le test
        carteJouee = null;
        joueurJouant = "gauche";
        // -----------------------------Création boutons ---------------------------------------------------------------------
        // Initialisation defausse
        carteGraphique bouton_carte = new carteGraphique(defausse.get(0),150,90);
        defausseJPanel.add(bouton_carte);
        
        texte boutonTexte = new texte(joueurJouant);
        texte.add(boutonTexte);
        
        // Création des bouton Cases et des actions à effectuer
        for (int j=0; j < 5; j++ ) {
            for (int i=0;i<5; i++){
                int position[] = {j,i};
                celluleGraphique boutonCellule = new celluleGraphique(grille.grille[j][i], largeurGrille/5,largeurGrille/5); // création d'un bouton
                
                ActionListener ecouteurClick;
                final int yCell = j;
                final int xCell = i;
                ecouteurClick = new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    
                    //TESTS
                    //changerDeck();
                    //Action à effectuer lorsque le bouton est cliqué
                    //System.out.println(yCell+""+xCell+" cliqué");
                    int [] deplacement = {yCell,xCell};
                    String stt = grille.grille[yCell][xCell].donneStatut();
                    String fond = grille.grille[yCell][xCell].donneFond();
                    
                    // On vérifie si une carte est en train d'être jouée
                    if(carteJouee != null){
                        if("gris".equals(fond)){
                            
                            System.out.println("aigri");
                            deplacerPion(celluleJouee,deplacement);
                            grille.toutNormal();
                            celluleJouee = null;
                            echangerDefausse(joueurJouant,carteJouee);
                            carteJouee = null;
                            
                            joueurSuivant();
                            System.out.println("-------->"+joueurJouant+"<-----------");      
                        }
                        else if("normal".equals(fond)){
                            System.out.println("considéré normal");
                            if(celluleJouee == null){
                                if("eb".equals(stt) || "mb".equals(stt)){
                                    if("gauche".equals(joueurJouant)){
                                        celluleJouee = grille.grille[yCell][xCell];
                                        griseCasesMv(joueurJouant,carteJouee,grille.grille[xCell][yCell]);
                                    }

                                }
                                else if("er".equals(stt) || "mr".equals(stt)){
                                    if("droite".equals(joueurJouant)){
                                        celluleJouee = grille.grille[yCell][xCell];
                                        griseCasesMv(joueurJouant,carteJouee,grille.grille[xCell][yCell]);
                                    }

                                }
                            } 
                        else if("marron".equals(fond)){
                            grille.toutNormal();
                            System.out.println("re init");
                        }
                    }
                }
                    repaint();
                    actualiser();
                    // On vérifie si il y a victoire d'un joueur
                    victoire();
                    //victoire();
                }
            };
            boutonCellule.addActionListener(ecouteurClick);
                jPanelGrille.add(boutonCellule); // ajout au Jpanel PanneauGrille
            }
        }
        
        
        // Création des boutons Cartes et des actions à effectuer
        //Cartes de gauche
        for (int i=0;i<2;i++){
            carteGraphique boutonCarte = new carteGraphique(deckJrGauche.get(i),150,90);
            final Carte carte = deckJrGauche.get(i);
            ActionListener ecouteurClick;
            ecouteurClick = new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if("gauche".equals(joueurJouant));
                    System.out.println(defausse.get(0).donneNom());
                    carteJouee = carte;
                    System.out.println(defausse.get(0).donneNom());
                    repaint();
                    //victoire();
                }
            };
            boutonCarte.addActionListener(ecouteurClick);
            
            coteGauche.add(boutonCarte);
        }
        // Cartes de droite
        for (int i=0;i<2;i++){
            carteGraphique boutonCarte = new carteGraphique(deckJrDroite.get(i),150,90);
            final Carte carte = deckJrDroite.get(i);
            ActionListener ecouteurClick;
            ecouteurClick = new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if("droite".equals(joueurJouant));
                    carteJouee = carte;
                    repaint();
                    //victoire();
                }
            };
            boutonCarte.addActionListener(ecouteurClick);
            coteDroit.add(boutonCarte);
        }
        
        
        // Création du logo
        logo boutonLogo = new logo();
            /**
            ActionListener ecouteurClick;
            ecouteurClick = new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if("droite".equals(joueurJouant));
                    carteJouee = carte;
                    repaint();
                    //victoire();
                }
            };
            boutonCarte.addActionListener(ecouteurClick);
            ***/
            //boutonLogo.setContentAreaFilled(false);

            logo.add(boutonLogo);
            
        
        initialiserPartie();

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanelGrille = new javax.swing.JPanel();
        coteDroit = new javax.swing.JPanel();
        coteGauche = new javax.swing.JPanel();
        defausseJPanel = new javax.swing.JPanel();
        logo = new javax.swing.JPanel();
        texte = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanelGrille.setBackground(new java.awt.Color(200, 63, 65));

        javax.swing.GroupLayout jPanelGrilleLayout = new javax.swing.GroupLayout(jPanelGrille);
        jPanelGrille.setLayout(jPanelGrilleLayout);
        jPanelGrilleLayout.setHorizontalGroup(
            jPanelGrilleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 172, Short.MAX_VALUE)
        );
        jPanelGrilleLayout.setVerticalGroup(
            jPanelGrilleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 149, Short.MAX_VALUE)
        );

        getContentPane().add(jPanelGrille, new org.netbeans.lib.awtextra.AbsoluteConstraints(111, 145, -1, -1));

        coteDroit.setBackground(new java.awt.Color(200, 63, 65));

        javax.swing.GroupLayout coteDroitLayout = new javax.swing.GroupLayout(coteDroit);
        coteDroit.setLayout(coteDroitLayout);
        coteDroitLayout.setHorizontalGroup(
            coteDroitLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        coteDroitLayout.setVerticalGroup(
            coteDroitLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        getContentPane().add(coteDroit, new org.netbeans.lib.awtextra.AbsoluteConstraints(289, 145, -1, 149));

        coteGauche.setBackground(new java.awt.Color(200, 63, 65));

        javax.swing.GroupLayout coteGaucheLayout = new javax.swing.GroupLayout(coteGauche);
        coteGauche.setLayout(coteGaucheLayout);
        coteGaucheLayout.setHorizontalGroup(
            coteGaucheLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        coteGaucheLayout.setVerticalGroup(
            coteGaucheLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        getContentPane().add(coteGauche, new org.netbeans.lib.awtextra.AbsoluteConstraints(5, 145, -1, 149));

        javax.swing.GroupLayout defausseJPanelLayout = new javax.swing.GroupLayout(defausseJPanel);
        defausseJPanel.setLayout(defausseJPanelLayout);
        defausseJPanelLayout.setHorizontalGroup(
            defausseJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 200, Short.MAX_VALUE)
        );
        defausseJPanelLayout.setVerticalGroup(
            defausseJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 120, Short.MAX_VALUE)
        );

        getContentPane().add(defausseJPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 20, 200, 120));

        javax.swing.GroupLayout logoLayout = new javax.swing.GroupLayout(logo);
        logo.setLayout(logoLayout);
        logoLayout.setHorizontalGroup(
            logoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        logoLayout.setVerticalGroup(
            logoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        getContentPane().add(logo, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, -1, -1));

        javax.swing.GroupLayout texteLayout = new javax.swing.GroupLayout(texte);
        texte.setLayout(texteLayout);
        texteLayout.setHorizontalGroup(
            texteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        texteLayout.setVerticalGroup(
            texteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        getContentPane().add(texte, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 20, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    
    
    
    // --------------------------------------------------------------------------
    // -------------------- METHODES POUR CREER LES DECKS -----------------------
    // --------------------------------------------------------------------------
  
    
    // - Méthodes pour récupérer le texte ou le int de la i-ème ligne d'un fichier .txt  
    private static int recupererValeurLigne(String cheminFichier, int numeroLigne) {
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
    
  // - Méthode permettant de créer le deck de carte total, contentant 16 cartes (Sanglier, Oie etc...)
    ArrayList<Carte> creerDeckTotal(){
        ArrayList<Carte> deck = new ArrayList<>();
        String nom;
        String image;
        //int[][]coups = new int[4][2];
        int[][]coupsCopie = new int[4][2]; // Pour palier au problème de référence objet
        int l;
        int indice;
        for(int i=0;i<16;i++){
            int[][]coups = new int[4][2];
            nom = recupererTexteLigne("src/Jeu/noms.txt",i+1);
            image = nom+".jpg";
            for(int j=0;j<4;j++){
                
                for(int k=0;k<2;k++){
                    l = j/2;
                    indice = i*8+j*2+k+1;
                    coups[j][k]= recupererValeurLigne("src/Jeu/coups.txt", (indice));
                    //System.out.println("ligne"+ (indice));
                    //System.out.println("i="+i+" j="+j+" k="+k +" l="+l);
                    //System.out.println(recupererValeurLigne("src/Jeu/coups.txt", (indice)));
                }
            }
            
            carte = new Carte(nom,image, coups);
            System.out.println(carte);
            deck.add(carte);
            System.out.println(deck);
            
        }
        return deck;
    }
    
    ArrayList<Carte> initDeckJr(){
        Random random = new Random();
        int n ;
        ArrayList<Carte> deck = new ArrayList<>();
        int taille = deckTotal.size();
        for(int i=0;i<2;i++){
            taille = deckTotal.size();
            n = random.nextInt(taille);
            deck.add(deckTotal.get(n));
            deckTotal.remove(n);
        }
        return deck;
    }
    
    ArrayList<Carte> initDefausse(){
        Random random = new Random();
        int n = random.nextInt(deckTotal.size());
        ArrayList<Carte> defausse = new ArrayList<>();
        
        defausse.add(deckTotal.get(n));
        deckTotal.remove(n);
        
        return defausse;
        
        
    }
    
    // --------------------------------------------------------------------------
    // -------------------- METHODES POUR JOUER  --------------------------------
    // --------------------------------------------------------------------------
    
    void victoire(){
        if(grille.victoireDroite()==true){
            fenetreVictoire f = new fenetreVictoire("droite") ;
            f.setVisible(true) ;
        }
        if(grille.victoireGauche()==true){
            fenetreVictoire f = new fenetreVictoire("gauche") ;
            f.setVisible(true) ;
        }
    }
    
    
    void griseCasesMv(String joueur, Carte carte, Cellule cell){
        System.out.println(deckJrGauche.get(0).donneNom()+deckJrGauche.get(1).donneNom());
        System.out.println(grille);
        int[][] mouvementsCarte = carte.donneCoups(); // On initialise juste( ça ne sert à rien), la suite est intéressante
        
        if("gauche".equals(joueur)){
           mouvementsCarte = carte.donneCoupsGauche(); 
        }
        else if("droite".equals(joueur)){
           mouvementsCarte = carte.donneCoupsDroite(); 
        }
        
        int[] positionPion = cell.donnePosition();
        int[] zero = {0,0};
        int[][] mouvements = new int[4][2];
        
        
        // On combine les déplacements relatifs de la carte et la position de la cellule pour obtenir les deplacements absolus
        for(int i=0;i<4;i++){
            mouvements[i][0] = positionPion[1]+mouvementsCarte[i][0];
            mouvements[i][1] = positionPion[0]+mouvementsCarte[i][1];   
        }
        // Observer
        String txt = "";
        for (int i=0;i<4;i++){
            txt = txt+"{";
                for(int j=0;j<2;j++){
                    txt = txt+" "+mouvementsCarte[i][j];
                }
            txt = txt+"}";
        }
        //System.out.println("Mouvements = "+txt);
        txt = "";
        for (int i=0;i<4;i++){
            txt = txt+"{";
                for(int j=0;j<2;j++){
                    txt = txt+" "+mouvements[i][j];
                    
                }
            txt = txt+"}";
        }
        //System.out.println("POSITION -> "+positionPion[1] + " "+ positionPion[0]);
        //System.out.println("DEPLACEMENTS ->"+carte);
        //System.out.println(txt);
        
        for(int i=0;i<4;i++){
            // Observer
            System.out.println(grille.deplacementLegal(positionPion, mouvements[i]));
            //---
            
            if(grille.deplacementLegal(positionPion, mouvements[i]) == false){
                mouvements[i] = zero;
            }
            if(mouvements[i][0]!=0 || mouvements[i][1]!= 0){
            grille.grille[mouvements[i][0]][mouvements[i][1]].devientGris();
            }
        }
        grille.grille[positionPion[1]][positionPion[0]].devientMarron();
        repaint();
    }
    
    void deplacerPion(Cellule cell, int[] deplacement){
        // On part du principe que les déplacements proposés sont tous légaux
        String statut = cell.donneStatut();
        int[] positionCell = cell.donnePosition();
        int[] cooDeplacement = new int[2];
        cooDeplacement[0] =deplacement[0];
        cooDeplacement[1] =deplacement[1];
        grille.grille[positionCell[0]][positionCell[1]].changeStatut("vide");
        grille.grille[cooDeplacement[0]][cooDeplacement[1]].changeStatut(statut);
        repaint();
        
    }
    
    void joueurSuivant(){
        if("gauche".equals(joueurJouant)){
            joueurJouant = "droite";
            System.out.println("euhhhhhh"+joueurJouant);
        }
        else{
            joueurJouant = "gauche";
        }
        actualiserTexte();
        repaint();
    }
    
    public void initialiserPartie(){
        grille.initialiser();
        repaint();
    }
    
    void echangerDefausse(String joueur, Carte carte) {
    if ("gauche".equals(joueur)) {
        deckJrGauche.add(defausse.get(0));
        deckJrGauche.remove(carte);
        defausse.remove(0);
        System.out.println(carte);
        defausse.add(new Carte(carte.donneNom(), carte.donneNom() + ".jpg", carte.donneCoups()));
        //defausse.add(carte);
    } else {
        deckJrDroite.remove(carte);
        deckJrDroite.add(defausse.get(0));
        defausse.remove(0);
        defausse.add(new Carte(carte.donneNom(), carte.donneNom() + ".jpg", carte.donneCoups()));
        //defausse.add(carte);
    }

    actualiserCoteGauche();
    actualiserCoteDroit();
    actualiserDefausse();
}
    
    // ---------------------------------------------------------------
    // -------------POUR REACTUALISER LES COMPOSANTS GRAPHIQUES-------
    // ---------------------------------------------------------------
    
    
void actualiserDefausse(){
        // Actualiser le composant graphique pour la défausse
    defausseJPanel.removeAll();
    carteGraphique bouton_carte = new carteGraphique(defausse.get(0), 150, 90);
    defausseJPanel.add(bouton_carte);

    // Mettre à jour l'interface graphique
    defausseJPanel.revalidate();
    defausseJPanel.repaint();
    }
    
 void actualiserCoteGauche() {
    // Actualiser le composant graphique pour le deck du joueur gauche
    coteGauche.removeAll();
    for (int i = 0; i < 2; i++) {
        carteGraphique boutonCarte = new carteGraphique(deckJrGauche.get(i), 150, 90);
        final Carte carte = deckJrGauche.get(i);
        ActionListener ecouteurClick = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if ("gauche".equals(joueurJouant)) ;
                carteJouee = carte;
            }
        };
        boutonCarte.addActionListener(ecouteurClick);
        coteGauche.add(boutonCarte);
    }

    // Mettre à jour l'interface graphique
    coteGauche.revalidate();
    coteGauche.repaint();
}

void actualiserCoteDroit() {
    // Actualiser le composant graphique pour le deck du joueur droit
    coteDroit.removeAll();
    for (int i = 0; i < 2; i++) {
        carteGraphique boutonCarte = new carteGraphique(deckJrDroite.get(i), 150, 90);
        final Carte carte = deckJrDroite.get(i);
        ActionListener ecouteurClick = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if ("droite".equals(joueurJouant)) ;
                carteJouee = carte;
            }
        };
        boutonCarte.addActionListener(ecouteurClick);
        coteDroit.add(boutonCarte);
    }

    // Mettre à jour l'interface graphique
    coteDroit.revalidate();
    coteDroit.repaint();
}

void actualiserJPanelGrille(){
    jPanelGrille.revalidate();
    jPanelGrille.repaint();
}
void actualiserLogo(){
    logo.revalidate();
    logo.repaint();
}
void actualiserTexte(){
    texte.removeAll();
    texte boutonTexte = new texte(joueurJouant);
    texte.add(boutonTexte); 
    texte.revalidate();
    texte.repaint();
}

void actualiser(){
    actualiserCoteGauche();
    actualiserCoteDroit();
    actualiserDefausse();
    actualiserJPanelGrille();
    actualiserLogo();
    actualiserTexte();
    
}
/**
 * @param args the command line arguments
 */
    
    
    
    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(fenetrePrincipale.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(fenetrePrincipale.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(fenetrePrincipale.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(fenetrePrincipale.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new fenetrePrincipale().setVisible(true);
            }
        });
        
        
        
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel coteDroit;
    private javax.swing.JPanel coteGauche;
    private javax.swing.JPanel defausseJPanel;
    private javax.swing.JPanel jPanelGrille;
    private javax.swing.JPanel logo;
    private javax.swing.JPanel texte;
    // End of variables declaration//GEN-END:variables

// Méthode gérant le fond de la JFrame
    @Override
    public void paint(Graphics g) {
    Image imageADessiner = null;
            
    imageADessiner = new ImageIcon("src/Jeu/images/vague.jpg").getImage();
    super.paint(g);
    // Dessin de l'image dans le composant
    if (imageADessiner != null) {
        //System.out.println("ok");
        g.drawImage(imageADessiner,0,0,getWidth(),getHeight(),this);
    }
    actualiser();
}
}
