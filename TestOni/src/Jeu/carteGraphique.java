
package Jeu;

import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JButton;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author marti
 */
public class carteGraphique extends JButton{
    Carte carteAssociee;
    int largeur;
    int hauteur;
    String image;
    
    public carteGraphique(Carte carte, int l, int h){
        this.carteAssociee = carte;
        this.largeur = l;
        this.hauteur = h;
        this.image = carte.donneNom();
        setSize(largeur, hauteur);
    }
    
    // Méthode gérant le dessin de la cellule
    @Override
    protected void paintComponent(Graphics g) {
    Image imageADessiner = null;

    imageADessiner = new ImageIcon("src/Jeu/images/"+image+".jpg").getImage();
    
   
    
    // Dessin de l'image dans le composant
    if (imageADessiner != null) {
    System.out.println("ok");
    g.drawImage(imageADessiner,0,0,300,180,this);
}
}
}
