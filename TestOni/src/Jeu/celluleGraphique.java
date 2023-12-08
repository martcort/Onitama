/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Jeu;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JButton;

/**
 *
 * @author marti
 */
public class celluleGraphique extends JButton{
    Cellule celluleAssociee;
    int largeur;
    int hauteur;
    
    public celluleGraphique(Cellule cell, int l, int h){
        this.celluleAssociee = cell;
        this.largeur = l;
        this.hauteur = h;
        setSize(largeur, hauteur);
    }
    
    // Méthode gérant le dessin de la cellule
    @Override
    protected void paintComponent(Graphics g) {
    Image imageADessiner = null;
    Image backgroundImage = null;
    if("gris".equals(celluleAssociee.donneFond())){
        backgroundImage = new ImageIcon("src/Jeu/images/fond_gris.png").getImage();
    }
    else{
        backgroundImage = new ImageIcon("src/Jeu/images/fond.png").getImage();
    }
    
    if ("eb".equals(celluleAssociee.donneStatut())) {
    imageADessiner = new ImageIcon("src/Jeu/images/eb.png").getImage();
    }
    else if ("er".equals(celluleAssociee.donneStatut())) {
    imageADessiner = new ImageIcon("src/Jeu/images/er.png").getImage();
    }
    else if ("mb".equals(celluleAssociee.donneStatut())) {
    imageADessiner = new ImageIcon("src/Jeu/images/mb.png").getImage();
    }
    else if ("mr".equals(celluleAssociee.donneStatut())) {
    imageADessiner = new ImageIcon("src/Jeu/images/mr.png").getImage();
    } 
    else {
    imageADessiner = new ImageIcon("src/Jeu/images/vide.png").getImage();
    }
    
    // Dessin de l'image dans le composant
    if (imageADessiner != null) {
    System.out.println("ok");
    g.drawImage(backgroundImage,0,0,100,100,this);
    g.drawImage(imageADessiner, 0, 0,100,100, this);
}
}
}
