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
    if ("eb".equals(celluleAssociee.donneStatut())) {
    imageADessiner = new ImageIcon("images/eb.png").getImage();
    }
    if ("er".equals(celluleAssociee.donneStatut())) {
    imageADessiner = new ImageIcon("images/er.png").getImage();
    }
    if ("mb".equals(celluleAssociee.donneStatut())) {
    imageADessiner = new ImageIcon("images/mb.png").getImage();
    }
    if ("mr".equals(celluleAssociee.donneStatut())) {
    imageADessiner = new ImageIcon("images/mr.png").getImage();
    } 
    else {
    imageADessiner = new ImageIcon("images/vide.png").getImage();
    }
    
    // Dessin de l'image dans le composant
    if (imageADessiner != null) {
    g.drawImage(imageADessiner, 0, 0, this);
}
}
}
